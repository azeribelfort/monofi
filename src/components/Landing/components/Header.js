import images from '../../../assets/Landing';
import Image from 'next/image'

import { motion, Variants, useScroll } from "framer-motion";
const Header = () => {
    const articleVariants = {
        offscreen: {
            x: -100,
            opacity: 0
        },
        onscreen: {
            x: 0,
            opacity: 1,
            transition: {
                type: "spring",
                bounce: 0.4,
                duration: 0.8
            }
        }
    };
    const imageVariants = {
        offscreen: {
            x: 100, opacity: 0
        },
        onscreen: {
            x: 0, opacity: 1,
            transition: {
                type: "spring",
                bounce: 0.4,
                duration: 0.8
            }
        }
    };

    return <div className="Header">
        <div className="container">
            <div className="light1">
            </div>

            <div className="section num1">
                <motion.div variants={articleVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="article">
                    <div className="Heading">
                        Bringing Startups and investors together
                    </div>
                    <div className="text">
                        Finding investors for your startup or investing in soon-to-be unicors has never been easier
                    </div>
                </motion.div>
                <motion.div variants={imageVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="image ofsection">
                    <Image alt='' width={400} src={images.Stacks} />
                </motion.div>
            </div>
            <div className="section num2">
                <motion.div variants={imageVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="article">
                    <div className="short">
                        ANALYTICS
                    </div>
                    <div className="Heading">
                        analytics system
                        To help you make
                        correct choices
                    </div>
                    <div className="text">
                        Our Built-In Analytics dashboard will be your number one assistant and give valuable information about your assets over any specified time.
                    </div>
                    <button>VIEW OUR PLANS</button>
                </motion.div>
                <motion.div variants={articleVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="image ofsection">
                    <Image alt='' width={400} src={images.LaptopChart} />
                </motion.div>

            </div>
            <div className="section num3">
                <motion.div variants={articleVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="article">
                    <div className="short">
                        GET OUT APP
                    </div>
                    <div className="Heading">
                        Can’t access your computer?
                        no problem!
                        Start Negotiating as you go!
                    </div>
                    <div className="text">
                        MonoFi App will help you stay in touch with startups and investors anywhere anytime, even if you are away from your computer.
                    </div>
                    <div className="Links">
                        <a>
                            <Image alt='' width={228}
                                src={images.appstore} />
                        </a>
                        <a>
                            <Image alt='' width={228} src={images.playstore} />
                        </a>

                    </div>
                </motion.div>
                <motion.div variants={imageVariants} initial={'offscreen'} whileInView={"onscreen"} viewport={{ once: false, amount: 0.6 }} className="image ofsection">
                    <Image alt='' width={500} src={images.Phone} />
                </motion.div>

            </div>
            <div className="middleText">
                OWN a share of the leading companies around the globe
            </div>
        </div>


    </div>
}
export default Header;