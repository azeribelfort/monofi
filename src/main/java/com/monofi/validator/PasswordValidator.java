package com.monofi.validator;

import com.monofi.annotation.PasswordValid;
import com.monofi.dto.RegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, Object> {

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        boolean hasNumber=false,hasSpecialSymbol=false;
        RegistrationDto registrationDto = (RegistrationDto) obj;
        String password = registrationDto.getPassword();
        char[] passwordChars = password.toCharArray();
        for (int i=0;i<passwordChars.length;i++){
            if (passwordChars[i]>47 && passwordChars[i]<58){
                hasNumber=true;
            }
            if ((passwordChars[i]>32 && passwordChars[i]<48) || (passwordChars[i]>57 && passwordChars[i]<65) ||
                    (passwordChars[i]>90 && passwordChars[i]<97) || (passwordChars[i]>122 && passwordChars[i]<127)){
                hasSpecialSymbol=true;
            }
        }
        if (!(hasNumber && hasSpecialSymbol)){
            throw new IllegalArgumentException("Password not valid");
        }
        return hasNumber && hasSpecialSymbol;
    }
}
