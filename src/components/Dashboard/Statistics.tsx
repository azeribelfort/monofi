const Statistics = () =>
{
    const data = [{
        name: 'PORTFOLIO VALUE',
        value : '37.82M$'
    },
    {
        name: 'ALL-TIME ROI',
        value : '+48.23%'
        },
        {
            name: 'ANNUAL ROI',
                value: '-5.79%'
        }
    ]
    return (<div className="statisticsContainer">
        <span className="Header">
            Statistics 🌑
        </span>
        <div className="Values">
            {
                data.map((e,index)=>(<div key={index} className="valueBox">
                    <div className="name">
                        {e.name}

                </div>
                    <div className="value">
                        {e.value}

                </div>

            </div>))
            }
            
             


        </div>
    </div>)
}
export default Statistics;
