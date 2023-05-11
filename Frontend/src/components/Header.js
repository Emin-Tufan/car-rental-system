import {Routes,Route,Link} from "react-router-dom";
import {AiOutlineCar} from 'react-icons/ai'
import {BiUser} from 'react-icons/bi'
import {BiUserPlus} from 'react-icons/bi'
import Home from './subComponents/Home'
import Register from "./subComponents/Register";
import SignIn from "./subComponents/SignIn";
import ChangePasssword from "./subComponents/ChangePassword";

export default function Header(){

    return(
        <>
        <div className=" w-screen flex top-0 z-10 font-black text-base fixed justify-between items-center p-4 h-16 bg-white  font-bold border-b-2">
            <nav className="flex justify-center items-center ml-2">
            <Link to="/"><AiOutlineCar color="rgb(30 64 175)" size="40" /></Link>
            </nav>
            <div className="flex justify-end items-center gap-4 text-blue-800 mr-8 ">
                <div className="flex justify-center items-center gap-4">
                    <div className="Link flex gap-1 items-center justify-center ">
                    <BiUserPlus size="24"  />
            <Link to="/kayitol" className=" flex"> Kayıt Ol</Link>
                    </div>
                    <div className="Link flex items-center justify-center gap-1">
                    <BiUser size="19" />
            <Link to="/girisyap" className=" flex" >Giriş Yap</Link>
                    </div>
                </div>
            </div>
        </div>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="kayitol" element={<Register />} />
            <Route path="/girisyap" element={<SignIn />} />
            <Route path="/sifremiunuttum" element={<ChangePasssword />} />
        </Routes>
        </>
    )
}
