import {BsQrCode} from 'react-icons/bs'
import {FcSimCardChip} from 'react-icons/fc'
import {RiVisaLine} from 'react-icons/ri'
import { NextPage } from 'next'
import Link from 'next/link'

interface Props{
    number: string,
    cvv: string,
    date2: string,
    date1: string
}

const CheckoutCard: NextPage<Props>=(props)=> {
    const{number,cvv,date1,date2} =props
  return (
    <div className='CheckoutCard'>
        <section className='first'>
            <BsQrCode className='qr'/>
            <Link href={'#'}><p>Scan For<br/>More Info</p></Link>
        </section>
        <div className='line'></div>
        <section className='customer'>
            <div className='name'>
                <p>Card Number</p>
                <FcSimCardChip className='chip'/>
            </div>
            <div className='numberr'>{number}</div>
            <div className='dt'>
                <p>EXP DATE</p>
                <p>CVV</p>
            </div>
            <div className='exp'>
                <p><span>{date1}</span> / <span>{date2}</span></p>
                <p>{cvv}</p>
            </div>
            <RiVisaLine className='visa2'/>
        </section>
    </div>
  )
}

export default CheckoutCard