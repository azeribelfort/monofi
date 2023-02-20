
import { Swiper, SwiperSlide } from "swiper/react";
import { Autoplay } from "swiper";
import Image from "next/image";
import images from '../../../assets/Landing';
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
const ComplanySlider = () => {
    return <div className="CompanySlider">
        <div className="container">
            <Swiper
                slidesPerView={3}
                centeredSlides={true}
                spaceBetween={10}
                loop={true}
                autoplay={{
                    delay: 2000,
                    disableOnInteraction: false,
                }}
                modules={[Autoplay]}
                className="mySwiper"
            >
                {images.slider.map(e =>

                    <SwiperSlide><Image src={e} alt="" /></SwiperSlide>
                )}

            </Swiper>

        </div>

    </div>
}

export default ComplanySlider;