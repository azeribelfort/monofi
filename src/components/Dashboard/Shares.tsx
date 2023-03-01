import icons from "@/assets/icons";


const Info = icons.Info
const Vector = icons.Vector;
const data = [{
    name: 'AAPL',
    subName:'Apple.inc',
    money: "$14.315,46",
    benefit:    2456.21 ,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(255, 251, 251, 0) 97.45%),linear-gradient(251.47deg, #FFFFFF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

},
{
    name: 'AAPL',
    subName:'Apple.inc',
    money: "$14.315,46",
    benefit:    2456.21 ,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(255, 251, 251, 0) 97.45%),linear-gradient(251.47deg, #FFFFFF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

    },
{
    name: 'AAPL',
    subName:'Apple.inc',
    money: "$14.315,46",
    benefit:    2456.21 ,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(255, 251, 251, 0) 97.45%),linear-gradient(251.47deg, #FFFFFF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

    },
{
    name: 'YELO',
    subName:'yelobank',
    money: "$11.456,67",
    benefit: -1742.14,
    
    color : " linear-gradient(67.83deg, #000000 65.28%, rgba(250, 255, 0, 0) 97.45%),linear-gradient(247.47deg, #FAFF00 11.58%, rgba(250, 255, 0, 0.0104167) 36.11%, rgba(0, 0, 0, 0) 36.11%)"

    },
{
    name: 'YELO',
    subName:'yelobank',
    money: "$11.456,67",
    benefit: -1742.14,
    color : " linear-gradient(67.83deg, #000000 65.28%, rgba(250, 255, 0, 0) 97.45%),linear-gradient(247.47deg, #FAFF00 11.58%, rgba(250, 255, 0, 0.0104167) 36.11%, rgba(0, 0, 0, 0) 36.11%)"

    },
{
    name: 'YELO',
    subName:'yelobank',
    money: "$11.456,67",
    benefit: -1742.14,
    color : " linear-gradient(67.83deg, #000000 65.28%, rgba(250, 255, 0, 0) 97.45%),linear-gradient(247.47deg, #FAFF00 11.58%, rgba(250, 255, 0, 0.0104167) 36.11%, rgba(0, 0, 0, 0) 36.11%)"

    },
{
    name: 'DRSE',
    subName:'Ders Evi',
    money: "$16.740,23",
    benefit: 2456.21,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(128, 0, 255, 0) 97.45%),linear-gradient(251.47deg, #8000FF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

    },
{
    name: 'DRSE',
    subName:'Ders Evi',
    money: "$16.740,23",
    benefit: 2456.21,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(128, 0, 255, 0) 97.45%),linear-gradient(251.47deg, #8000FF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

}, {
    name: 'DRSE',
    subName:'Ders Evi',
    money: "$16.740,23",
    benefit: 2456.21,
    color : "linear-gradient(67.83deg, #000000 65.28%, rgba(128, 0, 255, 0) 97.45%),linear-gradient(251.47deg, #8000FF 9.92%, rgba(0, 0, 0, 0) 58.34%)"

}]
const Shares = () =>
{
    return (<div className="sharesContainer">
        <div className="Header">
            Shares overview 💸 
        </div>
        <div className="Values">
            {data.map((e,index) => (
                <div className="valueBox" key={index}  style={{background:e.color}}>
                <Info />
                <div className="info">
                    <div className="name">
                        <span className="header">
                            {e.name}
                        </span>
                        <span className="sub">
                            {e.subName}
                        </span>
                    </div>
                    <span className="money">
                        {e.money}
                    </span>
                    <span className="benefit" style={e.benefit>0 ? {backgroundColor:'#00943A'}:{backgroundColor:'#940000'}}>
                        {e.benefit>0 ? ('+'+e.benefit):e.benefit}

                    </span>

                </div>
                    <div className={e.benefit>0?"curve":"curve Rotated"} >
                        <Vector/>

                </div>

            </div>
                
            ))}
            

        </div>

    </div>)
}
export default Shares; 
