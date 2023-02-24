import Link from "next/link"
import icons from "@/assets/icons"


const Arrow = icons.Arrow; 
const User = icons.User
const Lock = icons.Lock
const ArrowRight = icons.ArrowRight


const Signin = () => {
  return (
    <div className="Signin">
      <div className="Navigations">
        <Link href='#' >
          <Arrow/> Back
        </Link>
        <div>New to <span>MonoFi</span>? <Link href='#'>Sign Up</Link></div>
      </div>
      <div className="Header">
        Sign in
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
              <input type="email"  autoComplete="off"/>
            </div>
          </label>
          <label >
            <span>Password</span> <br />
            <div className="inputContainer">
            <Lock/>
            <input type="password" autoComplete="off" />
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