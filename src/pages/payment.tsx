import Countdown from "@/components/Payment/components/Countdown"
import Layout from "@/components/Payment/Layout"
import {RiVisaLine} from 'react-icons/ri'
import {IoCheckmarkCircleOutline} from 'react-icons/io5'
import {MdDialpad} from 'react-icons/md'
import {CiCreditCard1} from 'react-icons/ci'
import CheckoutCard from "@/components/Payment/components/CheckoutCard"
import { SetStateAction, useState } from "react"
function Payment () {
  const [show,setShow] = useState(false);
  const [show1,setShow1] = useState(false);
  const [show2,setShow2] = useState(false);
  const [date1, setDate1] =useState('')
  const [date2, setDate2] =useState('')
  const [cvv, setCVV] =useState('')
  const [number,setNumber] = useState('')

  function testNumber(e: string | SetStateAction<string>){
    setNumber(e);
    if(!isNaN(+number)){
      setShow(false)
    } else{
      setShow(true)
    }
  }

  function testCvv(e: string | SetStateAction<string>) {
    setCVV(e);
    if(!isNaN(+cvv)){
      setShow1(false)
    } else{
      setShow1(true)
    }
  }

  function testDate1(e: string | SetStateAction<string>) {
    setDate1(e);
    if(!isNaN(+date1)){
      setShow2(false)
    } else{
      setShow2(true)
    }
  }

  function testDate2(e: string | SetStateAction<string>) {
    setDate2(e);
    if(!isNaN(+date2)){
      setShow2(false)
    } else{
      setShow2(true)
    }
  }


  return (
    <Layout>
      <div className="payment-box">
        <section className="Card">
          <div className="head">
            <div className="logo">$</div>
            <h3>MonoFi<span>Pay</span></h3>
          </div>
          <div className="countdown">
            <Countdown/>
          </div>
          <div className="card-infos">
            <div className="card-number">
              <h3>Card Number</h3>
              <p>Enter the 16-digit number in front of your card</p>
              <div className="number-input">
                <RiVisaLine className="visa"/>
                <input type={'text'} placeholder="0000 0000 0000 0000" onChange={(e)=>testNumber(e.target.value)} />
                <IoCheckmarkCircleOutline className="check"/>
              </div>
              {show && <div className="red">* Please enter 16 digit number</div>}
            </div>

            <div className="cvv-number">
              <div className="texts">
                <h3>CVV Number</h3>
                <p>Enter the 3 digit number in the back of your card</p>
              </div>
              <div className="number-input">
                <input type="text" placeholder="***" onChange={(e)=>{testCvv(e.target.value)}}  />
                <MdDialpad className="dial"/>
              </div>
            </div>
              {show1 && <div className="red">* Please enter number</div>}

            <div className="expire">
              <div className="texts">
                <h3>Expire Date</h3>
                <p>Enter the expiration date of your card</p>
              </div>
              <div className="number-input">
                <div className="flex">
                  <input type="text" placeholder="MM" onChange={e=>{testDate1(e.target.value)}} />
                  {show2 && <div className="red">* Enter number</div>}
                </div>
                <p>/</p>
                <div className="flex">
                  <input type="text" placeholder="YY" onChange={e=>{testDate2(e.target.value)}} />
                  {show2 && <div className="red">* Enter number</div>}

                </div>
              </div>
            </div>

            <div className="password">
              <div className="texts">
                <h3>Password</h3>
                <p>Enter your 3D Secure Code</p>
              </div>

              <div className="number-input">
                <input type={'password'} />
                <MdDialpad className="dial"/>
              </div>
            </div>
            <button>Pay Now</button>
          </div>
        </section>
        <section className="Billing">
          <div className="Order">
            <div>
              <CheckoutCard 
              cvv={cvv} 
              date1={date1} 
              date2={date2} 
              number={number}
              />
            </div>
            <div className="order-info">
              <div className="order-num">
                <h6>Order Number</h6>
                <p>№ 5632345</p>
              </div>
              <div className="operation">
                <h6>Operation</h6>
                <p>Balance Top Up</p>
              </div>
              <div className="order-size">
                <h6>Order Size</h6>
                <p>800 MUSD</p>
              </div>
              <div className="fee">
                <h6>Fee(0%)</h6>
                <p>$0.00</p>
              </div>
              <div className="payment-due">
                <h6>Payment due</h6>
                <div>
                  <p>800<span className="s1">.00</span><span className="s2"> USD</span></p>
                  <CiCreditCard1 className="card"/>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </Layout>
  )
}

export default Payment