import { useState } from "react"
import { ResponsiveContainer, Area, XAxis, YAxis, AreaChart, Tooltip, CartesianGrid } from "recharts";
import { convertUnixTimestampToDate, convertDateToUnixTimestamp, createDate } from '../../../helpers/date-helper'
import { chartConfig } from '../../../constants/config'
import ChartFilter from "./ChartFilter";
import { mockHistoricalData } from '../../../constants/mock';

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

const PriceChart = ({ item }: props) => {

  const [data, setData] = useState(mockHistoricalData);
  const [filter, setFilter] = useState("1W");

  const formatData = (data) => {
    return data.c.map((item, index) => {
      return {
        value: item.toFixed(2),
        date: convertUnixTimestampToDate(data.t[index]),
      };
    });
  };

  return (
    <div className="middle-container">
      <span className="chart-title">{item.name} Price over Time</span>

      <ul className="chart-filter-links">
        {Object.keys(chartConfig).map((item) => (
          <li className="chart-filter-link" key={item}>
            <ChartFilter text={item} active={filter === item} onClick={() => setFilter(item)} />
          </li>
        ))}
      </ul>
      
      <div className="chart-container">
        <ResponsiveContainer>
          <AreaChart data={formatData(data)}>
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
            <YAxis domain={["dataMin", "dataMax"]} />
            <CartesianGrid strokeDasharray="3 3" />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </div>
  )
}

export default PriceChart