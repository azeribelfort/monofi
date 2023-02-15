import Slider from "./components/Slider";
import Spotlight from "./components/Spotlight";

const Layout = () => {
  return (
    <div className='layout-container'>
      <h1 className='layout-title'>Marketplace</h1>
      <div className='sort-btns'>
        <button className='sort-btn sort-active'>All</button>
        <button className='sort-btn'>Recent</button>
        <button className='sort-btn'>Favorites</button>
      </div>
      <div className='category-btns'>
        <button className="category-btn">Popular</button>
        <button className="category-btn">New in Market</button>
        <button className="category-btn">Most Expensive</button>
        <button className="category-btn">Least Expensive</button>
        <button className="category-btn">Buy Outright</button>
        <button className="category-btn">Buy Share</button>
      </div>
      <Slider />
      <Spotlight />
    </div>
  )
};

export default Layout;
