import Image from 'next/image';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation } from 'swiper';
import "swiper/css";
import "swiper/css/navigation";
import Apple from '../../../assets/companies/apple.png';
import Birbank from '../../../assets/companies/birbank.png';
import Dersevi from '../../../assets/companies/dersevi.png';
import Yelobank from '../../../assets/companies/yelobank.png';

const Slider = () => {
  return (
    <div className="company-slider-container">
      <Swiper
        slidesPerView={3}
        spaceBetween={2}
        navigation={true}
        grabCursor={true}
        modules={[Navigation]}
        className='company-slider'
      >
        <SwiperSlide className='company-slide'>
          <div className='company-slide-card'>
            <Image src={Apple} alt='company' className='company-img'/>
            <div className='info-container'>
              <div className='info-top'>
                <h3 className='info-title'>Apple</h3>
                <button className='info-btn'>Place a Bid</button>
              </div>
              <div className='info-bottom'>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Current Bid</span>
                  <span className='primary-span'>$4 bn.</span>
                </div>
                <div className='info-bottom-div right'>
                  <span className='secondary-span'>Ending In</span>
                  <span className='primary-span'>5d 18h 12m 3s</span>
                </div>
              </div>
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide className='company-slide'>
          <div className='company-slide-card'>
            <Image src={Birbank} alt='company' className='company-img' />
            <div className='info-container'>
              <div className='info-top'>
                <h3 className='info-title'>Birbank</h3>
                <button className='info-btn'>Place a Bid</button>
              </div>
              <div className='info-bottom'>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Current Bid</span>
                  <span className='primary-span'>$2.1 mil.</span>
                </div>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Ending In</span>
                  <span className='primary-span'>3d 12h 44m 12s</span>
                </div>
              </div>
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide className='company-slide'>
          <div className='company-slide-card'>
            <Image src={Dersevi} alt='company' className='company-img' />
            <div className='info-container'>
              <div className='info-top'>
                <h3 className='info-title'>Ders Evi</h3>
                <button className='info-btn'>Place a Bid</button>
              </div>
              <div className='info-bottom'>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Current Bid</span>
                  <span className='primary-span'>$4 bn.</span>
                </div>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Ending In</span>
                  <span className='primary-span'>1d 19h 30m 4s</span>
                </div>
              </div>
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide className='company-slide'>
          <div className='company-slide-card'>
            <Image src={Yelobank} alt='company' className='company-img' />
            <div className='info-container'>
              <div className='info-top'>
                <h3 className='info-title'>yelobank</h3>
                <button className='info-btn'>Place a Bid</button>
              </div>
              <div className='info-bottom'>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Current Bid</span>
                  <span className='primary-span'>$4 bn.</span>
                </div>
                <div className='info-bottom-div'>
                  <span className='secondary-span'>Ending In</span>
                  <span className='primary-span'>5d 18h 12m 3s</span>
                </div>
              </div>
            </div>
          </div>
        </SwiperSlide>
      </Swiper>
    </div>
  )
}

export default Slider