import Layout from "@/components/Payment/Layout"
import {SlClose} from'react-icons/sl'
function Success() {
  return (
    <Layout>
        <div className="failure-box">
            <section className="Card">
                <div className="head">
                    <div className="logo">$</div>
                    <h3>MonoFi<span>Pay</span></h3>
                </div>
                <div className="message">
                    <SlClose className="fail-close"/>
                    <p className="mx1">Payment Failed!</p>
                    <p className="mx2">There was an error while we tried to process your order.</p>
                </div>
            </section>
        </div>
    </Layout>
  )
}

export default Success