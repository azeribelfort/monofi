import Link from "next/link"
import icons from "@/assets/icons"
import { Facebook } from "../Signup/Facebook";
import { Google } from "../Signup/Google";


const Arrow = icons.Arrow; 
const User = icons.User
const Lock = icons.Lock
const ArrowRight = icons.ArrowRight


const Signin = () => {
  return (
    <div className="Signin">
      <div className="Navigations">
        <Link href='/' >
          <Arrow/> Back
        </Link>
        <div>New to <span>MonoFi</span>? <Link href='/signup'>Sign Up</Link></div>
      </div>
      <div className="Header">
        Sign in
      </div>
      <div className="AntoherMethods">
        <Google/>
        <Facebook />
        

      </div>

      <div className="Divider">
        <div className="line">

        </div>
        <div className="text">
          or use your MonoFi credentials
        </div>
        <div className="line">

        </div>

      </div>

      <form action="">
        <div className="Inputs">
          <label >
            <span>E-mail or username</span> <br />
            <div className="inputContainer">
              
              <User />
              <input type="email"  />
            </div>
          </label>
          <label >
            <span>Password</span> <br />
            <div className="inputContainer">
            <Lock/>
            <input type="password"  />
            </div>
          </label>

        </div>
        <div className="ForgotPass">
          <label htmlFor="check">
            <input type="checkbox" id="check" />
            Remember me
          </label>
          <Link href="#">
            Forgot password ?
          </Link>

        </div>

        <button>
          Sign in <ArrowRight/>
        </button>


      </form>
        
    </div>
  )
}

export default Signin