
import fb from '../../images/Facebook-logo-blue-circle-large-transparent-png.png'
import insta from '../../images/iconmonstr-instagram-14-240.png'
import tw from '../../images/iconmonstr-twitter-4-240.png'
import {Link} from "react-router-dom";

function Footer(){

    return (
        <div className="w-screen h-80 pt-8 flex absolute h-48 bg-white  border-t-2">
            <ul className="ml-16 mt-8 p-2 text-blue-800 font-black text-lg list-disc">
                <li className="mb-4 hover:text-red-500"><Link to="/hakkımızda">Hakkımızda</Link></li>
                <li className="mb-4 hover:text-red-500"><Link to="/ofislerimiz">Ofislerimiz</Link></li>
                <li className="mb-4 hover:text-red-500"><Link to="/araclar">Araçlar</Link></li>
            </ul>
            <div className="absolute right-0 mr-16 mt-6 font-black text-lg text-blue-800">
                <h3 className="text-blue-800 hover:text-blue-600">Sosyal Medya Hesaplarımız</h3>
                <div className="justify-items-start flex">
                    <div>
                        <button ><a href="https://tr-tr.facebook.com/" target="_blank"><img src={fb} alt={"logo"} className="h-8 w-8 mt-2 ml-4 rounded-full hover:bg-blue-100" /></a></button>
                    </div>
                    <div>
                        <button ><a href="https://www.instagram.com/" target="_blank"><img src={insta} alt={"logo"} className="h-8 w-8 mt-2 ml-6  rounded-full hover:bg-red-200 " /></a></button>
                    </div>
                    <div >
                        <button ><a href="https://twitter.com/?lang=tr" target="_blank"><img src={tw} alt={"logo"} className="h-8 w-8 mt-2 ml-6  rounded-full hover:bg-blue-500" /></a></button>
                    </div>
                </div>
            </div>



        </div>
    )
}

export default Footer;
