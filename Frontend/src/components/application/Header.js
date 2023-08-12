import {Routes,Route,Link} from "react-router-dom";
import {AiOutlineCar} from 'react-icons/ai'
import {BiUser} from 'react-icons/bi'
import {BiUserPlus} from 'react-icons/bi'
import {BiUserCircle} from 'react-icons/bi'
import {BsCart2} from 'react-icons/bs'
import {VscSignOut} from'react-icons/vsc'
import {FaCarSide} from 'react-icons/fa'
import {HiOutlineBuildingOffice2} from 'react-icons/hi2'
import Home from '../home/Home'
import Register from "../register/Register";
import SignIn from "../signIn/SignIn";
import ChangePasssword from "../changePassword/ChangePassword";
import {useEffect, useState} from "react";
import CurrentCampaign from "../homeComponents/subComponents/CurrentCampaign";
import CurrentBlog from "../homeComponents/subComponents/CurrentBlog";
import Cars from "../cars/Cars";
import CarDetails from '../cars/CarDetails'
import Page404 from "../404/Page404";
import AboutUs from "../homeComponents/AboutUs";
import Offices from '../offices/Offices'
import AdminSignIn from "../adminPages/AdminSignIn";
import AdminPage from "../adminPages/AdminPage";
import PrivateAdminRouter from "../adminPages/PrivateAdminRouter";
import AdminUsersLayout from "../adminPages/AdminUsersLayout";
import AdminUsers from "../adminPages/usersSubComponents/AdminUsers";
import AdminAddUser from "../adminPages/usersSubComponents/AdminAddUser";
import AdminDeleteUser from "../adminPages/usersSubComponents/AdminDeleteUser";
import AdminCarsLayout from "../adminPages/AdminCarsLayout";
import AdminDeleteCar from "../adminPages/carsSubComponents/AdminDeleteCar";
import AdminCars from "../adminPages/carsSubComponents/AdminCars";
import AdminAddCar from "../adminPages/carsSubComponents/AdminAddCar";
import AdminOfficeLayout from "../adminPages/AdminOfficeLayout";
import Basket from "../basket/Basket";
import * as React from 'react';
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import {useNavigate} from "react-router-dom";
import UserInformations from "../userComponents/UserInformations";
import Orders from "../userComponents/Orders";

export default function Header(){
    const navigate =useNavigate()
    const [isLogin,setIsLogin] = useState(() =>{
        return JSON.parse(localStorage.getItem('isLogin') || false)
    })

    const [isAdminLogin,setIsAdminLogin] = useState(() =>{
        return JSON.parse(localStorage.getItem('isAdminLogin') || false)
    })
    const [ordered,setOrdered] =useState(false)
    const [basket,setBasket] = useState([])
    useEffect(()=>{
        localStorage.setItem('isAdminLogin',JSON.stringify(isAdminLogin))
    },[isAdminLogin])
    useEffect(()=>{
        localStorage.setItem('isLogin',JSON.stringify(isLogin))
    },[isLogin])

    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleInformation = () => {
        navigate("/bilgilerim")
        setAnchorEl(null);
    };

    const handleOrders = () => {
        navigate("/siparislerim")
        setAnchorEl(null);
    }
    const handleClose = () =>{
        setAnchorEl(null);
    }


    return(
        <>
        <div className=" w-screen flex top-0 z-10 font-black text-base fixed justify-between items-center p-4 h-16 bg-white  font-bold border-b-2">
            {isAdminLogin ? (
                <>
                <nav className="flex justify-center items-center ml-2">
                    <Link to="/adminpage"><AiOutlineCar color="rgb(30 64 175)" size="40" /></Link>
                </nav>
                <div className="flex justify-end items-center gap-4 text-blue-800 mr-8 ">
                    <div className="flex justify-center items-center gap-4">
                        <div className="Link flex items-center justify-center gap-1">
                            <BiUserCircle size="21" />
                            <Link to="/kullanıcılar" className=" flex" >Kullanıcılar</Link>
                        </div>
                        <div className="Link flex items-center justify-center gap-1">
                            <FaCarSide size="21" />
                            <Link to="/arabalar" className=" flex" >Arabalar</Link>
                        </div>
                        <div className="Link flex items-center justify-center gap-1">
                            <HiOutlineBuildingOffice2 size="21" />
                            <Link to="/ofisler" className=" flex" >Ofisler</Link>
                        </div>
                        <div className="Link flex gap-1 items-center justify-center ">
                            <VscSignOut size={19} />
                            <button onClick={()=>setIsAdminLogin(false)}>Çıkış Yap</button>
                        </div>
                    </div>
                </div>
                </>
            ):
                <>
            <nav className="flex justify-center items-center ml-2">
            <Link to="/"><AiOutlineCar color="rgb(30 64 175)" size="40" /></Link>
            </nav>
            {isLogin ? (
                <div>
                    <div className="flex justify-end items-center gap-4 text-blue-800 mr-8 ">
                        <div className="flex justify-center items-center gap-4">
                            <div className="Link flex items-center justify-center gap-1">
                                <Button
                                    id="demo-positioned-button"
                                    aria-controls={open ? 'demo-positioned-menu' : undefined}
                                    aria-haspopup="true"
                                    aria-expanded={open ? 'true' : undefined}
                                    onClick={handleClick}
                                    className="Link flex gap-1 items-center justify-center"
                                    style={{color:"#0000A3"}}
                                >
                                    <BiUser size="19" />
                                    Hesabım
                                </Button>
                                <Menu
                                    id="demo-positioned-menu"
                                    aria-labelledby="demo-positioned-button"
                                    anchorEl={anchorEl}
                                    open={open}
                                    onClose={handleClose}
                                    anchorOrigin={{
                                        vertical: 'bottom',
                                        horizontal: 'left',
                                    }}
                                    transformOrigin={{
                                        vertical: 'top',
                                        horizontal: 'left',
                                    }}
                                >
                                    <MenuItem onClick={handleOrders}>Siparişlerim</MenuItem>
                                    <MenuItem onClick={handleInformation}>Kullanıcı Bilgilerim</MenuItem>
                                </Menu>
                            </div>
                            <div className="Link flex items-center justify-center gap-1">
                                <BsCart2 size="19" />
                                <Link to="/sepetim" className=" flex" >Sepete Git</Link>
                            </div>
                            <div className="Link flex gap-1 items-center justify-center ">
                                <VscSignOut size={19} />
                                <button onClick={()=>setIsLogin(false)}>Çıkış Yap</button>
                            </div>
                        </div>
                    </div>
                </div>
            ) : (
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
            )}
                </>
            }
        </div>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/kayitol" element={<Register />} />
            <Route path="/girisyap" element={<SignIn setIsLogin={setIsLogin} isLogin={isLogin} />} />
            <Route path="/sifremiunuttum" element={<ChangePasssword />} />
            <Route path="/kampanya/:id" element={<CurrentCampaign />} />
            <Route path="/bilgilerim" element={<UserInformations />} />
            <Route path="/siparislerim" element={<Orders basket={basket} ordered={ordered} />} />
            <Route path="/blog/:id" element={<CurrentBlog />} />
            <Route path="/araclar" element={<Cars  />} />
                <Route path="/araclar/:id" element={<CarDetails setBasket={setBasket} basket={basket} isLogin={isLogin} />} />
            <Route path="/hakkımızda" element={<AboutUs />} />
            <Route path="/ofislerimiz" element={<Offices />} />
            <Route path="/sepetim" element={<Basket basket={basket} setOrdered={setOrdered} setBasket={setBasket} />} />
            <Route path="/admin" element={<AdminSignIn isAdminLogin={isAdminLogin} setIsAdminLogin={setIsAdminLogin} />} />
            <Route path="/adminpage" element={<PrivateAdminRouter isAdminLogin={isAdminLogin}><AdminPage /></PrivateAdminRouter>} />
            <Route path="/kullanıcılar" element={<PrivateAdminRouter isAdminLogin={isAdminLogin}><AdminUsersLayout /></PrivateAdminRouter>}>
                <Route path="/kullanıcılar/listele" element={<AdminUsers />} />
                <Route path="/kullanıcılar/ekle" element={<AdminAddUser />} />
                <Route path="/kullanıcılar/sil" element={<AdminDeleteUser />} />
            </Route>
            <Route path="/arabalar" element={<PrivateAdminRouter isAdminLogin={isAdminLogin}><AdminCarsLayout /></PrivateAdminRouter>}>
                <Route path="/arabalar/listele" element={<AdminCars />} />
                <Route path="/arabalar/ekle" element={<AdminAddCar />} />
                <Route path="/arabalar/sil" element={<AdminDeleteCar />} />
            </Route>
            <Route path="/ofisler" element={<PrivateAdminRouter isAdminLogin={isAdminLogin}><AdminOfficeLayout /></PrivateAdminRouter>}>
                <Route path="/ofisler/listele" element={<AdminCars />} />
                <Route path="/ofisler/ekle" element={<AdminAddCar />} />
                <Route path="/ofisler/sil" element={<AdminDeleteCar />} />
            </Route>
            <Route path="*" element={<Page404 />} />
        </Routes>
        </>
    )
}
