package com.monofi.controller;

import com.monofi.dto.CreatePaymentRequest;
import com.monofi.dto.CreatePaymentResponse;
import com.monofi.model.Payment;
import com.monofi.model.User;
import com.monofi.model.enums.PaymentState;
import com.monofi.service.PaymentService;
import com.monofi.service.UserService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    String endpointSecret = "whsec_c916775cee430a19c5fe521a27102f3f73ded201f2a7935245defdff5cbddba8";


    @PostMapping("/create-payment-intent")
    public ResponseEntity<CreatePaymentResponse> createPaymentIntent(@RequestBody CreatePaymentRequest createPaymentRequest, Authentication authentication){
        Long price = new Long(createPaymentRequest.getPrice());
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        PaymentIntentCreateParams paymentIntentCreateParams = PaymentIntentCreateParams.builder()
                .setCurrency("usd")
                .setAmount(price * 100L)
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentCreateParams);
            LOGGER.info("{} create payment intent",username);
            Payment payment = new Payment();
            payment.setId(paymentIntent.getId());
            payment.setAccountId(user.getId());
            payment.setPrice(paymentIntent.getAmount());
            payment.setPaymentState(PaymentState.UNSUCCESSFUL);
            paymentService.save(payment);
            return new ResponseEntity<>(new CreatePaymentResponse(paymentIntent.getClientSecret()), HttpStatus.CREATED);
        } catch (StripeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/payment/events")
    public ResponseEntity<String> handlePaymentEvents(@RequestBody String payload,@RequestHeader("Stripe-Signature")String header){
        if(header==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Event event;
        try{
            event= Webhook.constructEvent(payload,header,endpointSecret);
        }catch (SignatureVerificationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
        switch (event.getType()) {
            case "payment_intent.succeeded": {
                Payment payment = paymentService.findById(paymentIntent.getId());
                payment.setPaymentState(PaymentState.SUCCESSFUL);
                paymentService.save(payment);
                LOGGER.info("{} payment intent succeeded",paymentIntent.getId());
                break;
            }
            default:
                LOGGER.info("{} payment intent unsucceeded",paymentIntent.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
