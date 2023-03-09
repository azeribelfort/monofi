import Link from "next/link";

const Navbar = () => {
  return <div className="Navbar">
    <div className="container forNavbar">
    <div className="light">

    </div>
    <div className="logo">
      <a to='/'><span>Mono</span>Fi</a>
      
    </div>
    <div className="Links">
      
      <a>Product</a>
      <a>Pricing</a>
      <a>About</a>
      <Link href='/signup'><button>Get Started</button></Link>
    </div>
      </div>
  </div>;
};
export default Navbar;
