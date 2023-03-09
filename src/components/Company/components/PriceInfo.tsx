import Image from "next/image"
import Apple from '../../../assets/companies/apple.png'
import StarFav from '../../../assets/main/star-yellow.png'

interface props{
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
}

const PriceInfo = ({ item }: props) => {
  return (
      <div className="top-container">
          <div className="top-left">
              <div className="company-img">
                  <Image src={item.imgSrc} alt="company" />
              </div>
              <div className="info-container">
                  <div className="info-top">
                      <h3>{item.name}</h3>
                      <span>{item.abbrv}</span>
                      <button className="info-btns"><Image src={StarFav} alt="btn" /></button>
                      <button className="info-btns"><Image src={StarFav} alt="btn" /></button>
                      <button className="info-btns"><Image src={StarFav} alt="btn" /></button>
                  </div>
                  <div className="info-select">
                      <div className="select">
                          <select id="standard-select">
                              <option value="Option 1">Option 1</option>
                              <option value="Option 2">Option 2</option>
                              <option value="Option 3">Option 3</option>
                              <option value="Option 4">Option 4</option>
                              <option value="Option 5">Option 5</option>
                          </select>
                      </div>
                  </div>
                  <div className="info-bottom">
                      <div>
                          <span className="left-title">Total Intake</span>
                          <span className="right-value">1500k</span>
                      </div>
                      <div>
                          <span className="left-title">New Customers</span>
                          <span className="right-value">7k <span id="change-info-span">+1k</span></span>
                      </div>
                      <div>
                          <span className="left-title">Repeat Customers</span>
                          <span className="right-value">1.5k</span>
                      </div>
                      <div>
                          <span className="left-title">Total Sold</span>
                          <span className="right-value">130k</span>
                      </div>
                  </div>
              </div>
          </div>

          <div className="top-right">
              <span className="price-title">{item.name} Price ({item.abbrv})</span>
              <div className="price-con">
                  <span className="price">$146.0421</span>
                  <span className="price-change">7.26%</span>
              </div>
              <div className="min-max-con">
                  <span><span className="min-max-title">Low:</span>$144.46547</span>
                  {/* progress bar */}
                  <span><span className="min-max-title">High:</span>$152.13746</span>
              </div>
              <div className="price-info-con">
                  <div className="price-info-col">
                      <span className="col-title">Market Cap</span>
                      <span>$4 bn.</span>
                  </div>
                  <div className="price-info-col">
                      <span className="col-title">Volume</span>
                      <span>$7,135,134</span>
                  </div>
                  <div className="price-info-col">
                      <span className="col-title">Ending in</span>
                      <span>5d 18h 12m 3s</span>
                  </div>
              </div>
          </div>
      </div>
  )
}

export default PriceInfo