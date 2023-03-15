import { useState } from "react"
import PriceChart from "./components/PriceChart";
import PriceInfo from "./components/PriceInfo"
import { mockCompanies } from "@/data";

interface props{
  companyId: any;
};

const Center = ({ companyId }: props) => {

  const [quantity, setQuantity] = useState<number>(0);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(e.target.value ? parseInt(e.target.value.replace(/\D/, '')) : 0);
  };

  return (
    <div className="center-container">
      {mockCompanies.map(item =>
        companyId === item.id.toString() && (
          <div key={item.id} className="center-wrapper">
            <PriceInfo item={item} />

            <PriceChart item={item} />

            <div className="bottom-container">
              <div className="quantity-con">
                <button className="quantity-btns" onClick={() => setQuantity(prev => (prev - 1) < 0 ? 0 : (prev - 1))}>-</button>
                <input value={quantity} onChange={handleChange} className='quantity-input' />
                <button className="quantity-btns" onClick={() => setQuantity(prev => prev + 1)}>+</button>
              </div>
              <button className="purple-btns">Buy</button>
              <button className="purple-btns">Sell</button>
              <button className="purple-btns">Exchange</button>
            </div>
          </div>
        ))}
    </div>
  )
}

export default Center