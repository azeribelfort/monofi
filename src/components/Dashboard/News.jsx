import React from 'react'
import { useState, useEffect } from 'react'

export const News = () => {
    const [news,setNews] = useState([])

    async function fetchData() {
        const options = {
            method: 'GET',
            headers: {
                'X-RapidAPI-Key': 'c9dc44a9eemsh307e2412333a4fap13b1c6jsnce35f26b6121',
                'X-RapidAPI-Host': 'ms-finance.p.rapidapi.com'
            }
        };

        try{
            const res = await fetch('https://ms-finance.p.rapidapi.com/news/list?performanceId=0P0000OQN8',options)
            const data = await res.json();
            setNews(data)
            console.log(data)
        } catch (error) {
            console.error(error);
          }

    }

    useEffect(()=>{
        fetchData();
    },[])
  return (
    <div>
        <h4>News 🗞️</h4>
        {
            news.map((item)=>(
                <div className='news-all' key={item.id}>
                    <p className='date'>{item.publishedDate.slice(0,10)}</p>
                    <p className='title'>
                        {item.title.slice(0,30)}..
                    </p>
                </div>
            ))
        }
    </div>
  )
}
