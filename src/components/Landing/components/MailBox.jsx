import images from '../../../assets/Landing/index';
import Image from 'next/image';

const MailBox = () => 
{
    return (<div className="MailBox">
        <div className="container forMbox">
            <div className="mailBox">
                <div className="image">
                    <Image src={images.NewMail} alt="" />
                </div>
                <span className='sub'>
                    It is never late to become an investor
                </span>
                <span className="head">
                    Start Investing Now!
                </span>
                <div className="emailContainer">
                    <input type="email" className='email' placeholder='Enter your email' />
                </div>
                
                <button className='submit'> Become an Investor</button>

            </div>
            
        </div>
        

    </div>)
}
export default MailBox;