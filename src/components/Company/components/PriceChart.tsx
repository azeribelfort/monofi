import { useEffect, useState } from "react"
import { ResponsiveContainer, Area, XAxis, YAxis, AreaChart, Tooltip, CartesianGrid } from "recharts";
import ChartFilter from "./ChartFilter";
import axios from "axios";

interface props {
  item: {
    id: number,
    name: string,
    abbrv: string,
    price: string,
    isIncreased: boolean,
    change: string,
    investors: number,
    highestBid: string,
    outright: string,
    imgSrc: any,
  }
};

const chartConfig = ['1h', '1d', '1wk', '1mo', '3mo'];

const PriceChart = ({ item }: props) => {

  const [data, setData] = useState({});
  const [filter, setFilter] = useState("1d");

  useEffect(() => {

    const fetchHistoricalData = async () => {

      const options = {
        method: 'GET',
        url: `https://yahoo-finance15.p.rapidapi.com/api/yahoo/hi/history/AAPL/${filter}`,
        headers: {
          'X-RapidAPI-Key': process.env.NEXT_PUBLIC_API_KEY,
          'X-RapidAPI-Host': 'yahoo-finance15.p.rapidapi.com'
        }
      };

      try {
        const response = await axios.request(options);

        setData(Object.values(response.data.items).map((item) => {
          return {
            value: item.open,
            date: item.date,
          };
        }));

      } catch (err) {
        console.log(err);
      }
    };

    fetchHistoricalData();
  }, [filter])

  console.log(data);
  return (
    <div className="middle-container">
      <span className="chart-title">{item.name} Price over Time</span>

      <ul className="chart-filter-links">
        {chartConfig.map((item) => (
          <li className="chart-filter-link" key={item}>
            <ChartFilter text={item} active={filter === item} onClick={() => setFilter(item)} />
          </li>
        ))}
      </ul>
      
      <div className="chart-container">
        <ResponsiveContainer>
          <AreaChart data={data}>
            <defs>
              <linearGradient id="chartColor" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#8884d8" stopOpacity={0} />
              </linearGradient>
            </defs>
            <Area 
              type="monotone" 
              dataKey="value" 
              stroke="#312e81" 
              fillOpacity={1} 
              strokeWidth={0.5}
              fill="url(#chartColor)"
            />
            <Tooltip />
            <XAxis dataKey={"date"} />
            <YAxis orientation="right" domain={["dataMin", "dataMax"]} />
            <CartesianGrid strokeDasharray="3 3" />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </div>
  )
}

export default PriceChart