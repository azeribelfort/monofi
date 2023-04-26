import Layout from "@/components/Payment/Layout"
import {BsCheck2Circle} from 'react-icons/bs'

function Success() {
  return (
    <Layout>
        <div className="success-box">
            <section className="Card">
                <div className="head">
                    <div className="logo">$</div>
                    <h3>MonoFi<span>Pay</span></h3>
                </div>
                <div className="message">
                    <BsCheck2Circle className="check"/>
                    <p className="m1">Payment Successful!</p>
                    <p className="m2">Your  funds will be updated shortly.</p>
                </div>
            </section>
        </div>
    </Layout>
  )
}

export default Success