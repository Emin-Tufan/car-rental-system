import Slider from "react-slick";
import firstPic from '../../images/1111.png'
import thirdPic from '../../images/33333.jpeg'
import fourthPic from '../../images/444.webp'
import fifthPic from '../../images/555.png'
export default function HomeSlider(){

    const settings = {
        dots: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows:false,
        autoplay: true,
        autoplaySpeed: 3500,
        cssEase: "linear"
    };
    return(
        <div className="w-screen h-[500px] mt-16">
            <Slider {...settings}>
                <div>
                    <img className="pic" src={firstPic} />
                </div>
                <div>
                    <img className="pic" src={thirdPic} />
                </div>
                <div>
                    <img className="pic" src={fourthPic} />
                </div>
                <div>
                    <img className="pic" src={fifthPic} />
                </div>

            </Slider>
        </div>
    )
}
