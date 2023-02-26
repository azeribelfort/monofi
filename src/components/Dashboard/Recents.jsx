import DownCircle from '../../assets/Dashboard/down.svg'
import UpCircle from '../../assets/Dashboard/up.svg'
import { useState, useEffect } from 'react'

export const Recents = () => {
    const [stocks,setStock] = useState([])
    async function fetchData() {
        const options = {
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': 'c9dc44a9eemsh307e2412333a4fap13b1c6jsnce35f26b6121',
                'X-RapidAPI-Host': 'apidojo-yahoo-finance-v1.p.rapidapi.com'
            }
        };
        try {
            const response = await fetch("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/get-recent-updates?symbol=AMRN&region=US&lang=en-US",options)
            const data = await response.json()
            setStock(data.quoteSummary.result[0].upgradeDowngradeHistory.history)
            console.log(data.quoteSummary.result[0].upgradeDowngradeHistory.history)
            // setStock(response.)
        } catch (error) {
          console.error(error);
        }
    }
    
    useEffect(() => {
        fetchData();
    },[])

  return (
    <div>
        <h4>Recents 🤑</h4>
        {
            stocks.map((stock,index)=>(
                <div className='stock-all' key={index}>
                    <p className='date'>01.02.2023 3:47 am</p>
                    <div className='stock-info'>
                        <div className='stock-firm'>
                            {stock.action==="up" ? (<UpCircle/>) : <DownCircle/>}
                            <p>{stock.firm}</p>
                        </div>
                        <p className={stock.action === "up" ? "green" : "red"}>{stock.action==="up" ? '$549' : '$318'}</p>
                    </div>
                    
                </div>
            ))
        }
    </div>
  )
}
