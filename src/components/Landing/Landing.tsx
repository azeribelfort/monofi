import Header from './components/Header.js'
import Navbar from './components/Navbar'
import CompanySlider from './components/CompanySlider'
import Testimonials from './components/Testimonials'
import Footer from './components/Footer'
import MailBox from './components/MailBox'
export default function Landing ()
{
    return (<div className='LandingBackground' style={{
        backgroundColor: '#421679'}}>
        <Navbar/>
        <Header />
        <CompanySlider />
        <Testimonials />
        <MailBox/>
        <Footer/>
    </div>)
 }