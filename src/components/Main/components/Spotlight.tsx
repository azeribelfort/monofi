import Image from 'next/image';
import Apple from '../../../assets/companies/apple.png';
import Birbank from '../../../assets/companies/birbank.png';
import Dersevi from '../../../assets/companies/dersevi.png';
import Yelobank from '../../../assets/companies/yelobank.png';

const Spotlight = () => {

  return (
    <div className="spotlight-container">
      <h3 className="spotlight-title">Spotlight</h3>
      <div className="spotlight-table">
        <table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Value (Share)</th>
              <th>Change (last 24h)</th>
              <th>Investors</th>
              <th>Highest Bid</th>
              <th>Buy Outright</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><Image src={Apple} alt='company' width={40} height={40} className='name-img' /><span className='name-td'>Apple</span></td>
              <td>$220</td>
              <td className='change color-up'>
                <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M5 0L9.33013 7.5H0.669873L5 0Z" fill="#5CFF9C" />
                </svg> 14%</td>
              <td>12000</td>
              <td>$3.5 bn.</td>
              <td>$4 bn.</td>
            </tr>
            <tr>
              <td><Image src={Birbank} alt='company' width={40} height={40} className='name-img' /><span className='name-td'>Birbank</span></td>
              <td>$220</td>
              <td className='change color-down'>
                <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M5 8L0.669872 0.5H9.33013L5 8Z" fill="#F00500" />
                </svg> 8%</td>
              <td>12000</td>
              <td>$3.5 bn.</td>
              <td>$4 bn.</td>
            </tr>
            <tr>
              <td><Image src={Dersevi} alt='company' width={40} height={40} className='name-img' /><span className='name-td'>Ders Evi</span></td>
              <td>$220</td>
              <td className='change color-up'>
                <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M5 0L9.33013 7.5H0.669873L5 0Z" fill="#5CFF9C" />
                </svg> 27%</td>
              <td>12000</td>
              <td>$3.5 bn.</td>
              <td>$4 bn.</td>
            </tr>
            <tr>
              <td><Image src={Yelobank} alt='company' width={40} height={40} className='name-img' /><span className='name-td'>yelobank</span></td>
              <td>$220</td>
              <td className='change color-down'>
                <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M5 8L0.669872 0.5H9.33013L5 8Z" fill="#F00500" />
                </svg> 16%</td>
              <td>12000</td>
              <td>$3.5 bn.</td>
              <td>$4 bn.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default Spotlight