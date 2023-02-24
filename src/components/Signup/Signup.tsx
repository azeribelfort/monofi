import Link from "next/link"
import icons from "@/assets/icons"
import { Google } from '@/components/Signup/Google'
import { Facebook } from '@/components/Signup/Facebook'



const ArrowLeft = icons.Arrow
const MailIcon = icons.Mail
const UserIcon = icons.User
const LockIcon = icons.Lock
const ArrowRight = icons.ArrowRight

const SignupPage = () => {
  return (
    <div className="Signup">
      <div className='top'>
        <Link href={'/'}>
          <div className='back'>
            <ArrowLeft/>
            <p>Back</p>
          </div>
        </Link>
        
        <p>Already have an account?<span><Link href={'/signin'}>Sign in</Link></span></p>
      </div>
      <div className='topSignup'>
        <h1>Sign Up</h1>
        <div className='signupWith'>
          <Google/>
          <Facebook/>
        </div>
        <div className='toptoForm'>
          <div/>
          <p>or create your MonoFi account</p>
          <div/>
        </div>
      </div>
      
      <form>
        <div className='forms'>
          <label htmlFor='mail'>E-mail address</label>
          <div className='Input'>
            <MailIcon/>
            <input type="text" id="mail" placeholder='Your email' />
          </div>

          <label htmlFor='user'>Username</label>
          <div className='Input'>
            <UserIcon/>
            <input type="text" id="user" placeholder='Your username' />
          </div>

          <label htmlFor='password'>Password</label>
          <div className='Input'>
            <LockIcon/>
            <input type="password" id="password" placeholder='*********' />
          </div>

          <label htmlFor='r-password'>Repeat Password</label>
          <div className='Input'>
            <LockIcon/>
            <input type="password" id="r-password" placeholder='*********' />
          </div>
          <div className='agreement'>
            <label htmlFor="checkbox">
              <input type={'checkbox'} id="checkbox"/>
              <p>I’ve read and agree with the <a href='#' className='terms'>terms and conditions</a> of MonoFi.</p>
            </label>
          </div>
          <button>
            Sign Up <ArrowRight/>
          </button>
        </div>
      </form>
    </div>
  )
}

export default SignupPage