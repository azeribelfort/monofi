import React from 'react';


import {footerData} from '../data/footer'
import Newsletter from './Newsletter';
const Footer = () =>
{
    return (
        <div className="Footer">
            <div className='forFooter container'>
                <div className="logo">
                    <a to='/'><span>Mono</span>Fi</a>
                </div>
                {
                    footerData.map((data,index)=>(
                        <div key={index} className='footer-links'>
                            <p>{data.head}</p>
                            <ul className='ful'>
                                {
                                    data.foots.map((foot,index)=>(
                                        <>
                                            <a  to={`${foot.link}`}>
                                                <li key={index}>{foot.title}</li>
                                            </a>
                                        </>
                                        
                                    ))
                                }
                            </ul>
                        </div>
                    ))
                }
                <Newsletter/>
            </div>
            
        </div>
    )
}
export default Footer; 
