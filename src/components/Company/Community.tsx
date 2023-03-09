import { useState } from "react"
import Comment from "./components/Comment";

const Community = () => {

  const [active, setActive] = useState(0);

  return (
    <div className="community-container">
      <h3>Community</h3>
      <div className="community-tabs-con">
        <button 
          className={active === 0 ? 'community-tabs active' : 'community-tabs'}
          onClick={() => setActive(0)}
        >Top</button>
        <button 
          className={active === 1 ? 'community-tabs active' : 'community-tabs'}
          onClick={() => setActive(1)}
        >Latest</button>
      </div>
      <div className="comments-con">
        <Comment />
        <Comment />
        <Comment />
      </div>
      <button id="see-more-btn">See more</button>
    </div>
  )
}

export default Community