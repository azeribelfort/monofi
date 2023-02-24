const Dashboard = () => {
    const data = {name : 'Azer'}
  return (
    <div  className="Dashboard">
          <span className="Welcome">
              Welcome back, Mr. {data.name} 👋
              
          </span>
          <div className="AllData">
        <div className="graphs">
          <div className="Shares">


          </div>
          <div className="Detailed">
            
          </div>
          
        </div>
        <div className="rightBar">

        </div>
          </div>
    </div>
  )
};

export default Dashboard;