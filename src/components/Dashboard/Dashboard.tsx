import { Balance } from "./Balance";
import { Recents } from "./Recents";
import { News } from './News'
import Statistics from "./Statistics";
import Shares from "./Shares";

const Dashboard = () => {
    const data = {name : 'Azer'}
  return (
    <div  className="Dashboard">
          <span className="Welcome">
              Welcome back, Mr. {data.name} 👋
              
          </span>
          <div className="AllData">
        <div className="graphs">
          <div className="Statistics">
            <Statistics/>

          </div>
          <div className="Shares">
            <Shares/>
          </div>
          
        </div>
        <div className="rightBar">
          <div className="balance">
            <Balance/>
          </div>
          <div className="recents">
            <Recents/>
          </div>
          <div className="news">
            <News/>
          </div>
        </div>
          </div>
    </div>
  )
};

export default Dashboard;