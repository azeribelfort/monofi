import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { mockCompanies } from '../../../data';

const Spotlight = () => {
  const router = useRouter();

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
            {mockCompanies.map((item) => (
              <tr key={item.id} onClick={() => router.push(`/companies/${item.id}`)}>
                <td><Image src={item.imgSrc} alt='company' width={40} height={40} className='name-img' />
                  <span className='name-td'>{item.name}</span>
                </td>
                <td>{item.price}</td>
                <td className='change color-up'>
                  {item.isIncreased ? (
                    <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M5 0L9.33013 7.5H0.669873L5 0Z" fill="#5CFF9C" /></svg> 
                    ) : (
                    <svg width="10" height="8" viewBox="0 0 10 8" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M5 8L0.669872 0.5H9.33013L5 8Z" fill="#F00500" /></svg>
                  )} {item.change}</td>
                <td>{item.investors}</td>
                <td>{item.highestBid}</td>
                <td>{item.outright}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default Spotlight