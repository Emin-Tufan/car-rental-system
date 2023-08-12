import {useParams ,useNavigate} from "react-router-dom";
import {cars} from "../../componentServices/CarDatas";
import {MdPerson} from 'react-icons/md'
import {FaSuitcase} from "react-icons/fa";
import React from "react";
import {GiCarDoor} from 'react-icons/gi'
import {TbManualGearbox}  from'react-icons/tb'
import {BsFillFuelPumpFill} from "react-icons/bs";
import {IoMdSpeedometer} from 'react-icons/io'
export default function CarDetails({isLogin,basket,setBasket}){
    const {id} =useParams()
    const navigate = useNavigate()
    const currentCar = cars.filter(e => e.id == id)
    const handleClick = () => {
        setBasket([
            currentCar
        ])
        navigate("/sepetim")
    }
    return (
        <div className="mt-16 w-screen h-screen bg-gray-300 flex justify-center items-center">
            {currentCar.map(e => (
                <div className="flex justify-between items-start bg-white rounded-lg shadow-2xl w-3/5 h-auto">
                    <div className="w-3/4 h-3/4">
                    <h2 className="text-xl font-black pl-8 pt-8 w-auto h-auto">{e.name}</h2>
                    <h5 className="text-sm ml-8">{e.class} Sınıfı / Min. Sürücü Yaşı : {e.minAge} / Min. Ehliyet Yılı {e.minDriving}</h5>
                    <h5 className="text-sm ml-8 pt-1 text-green-500">{e.categories}</h5>
                    <div className="flex h-[300px] pt-8 ml-6 w-[500px] border-b-2 flex justify-center items-center">
                        <img src={e.src} alt="resim" className="h-[270px] object-cover w-[500px]" />
                    </div>
                    <div className="justify-center items-center text-sm font-thin  h-auto w-[400px] mt-4 ml-4 p-6">
                        <div>
                        <p className="flex justify-center mt-2 gap-x-1 items-center ">
                            <MdPerson size="20" />{e.capacity}
                            <FaSuitcase className="ml-10" size="18" />{e.luggage}
                            <GiCarDoor className="ml-10" size="18" /> {e.doors} Kapılı
                        </p>
                        </div>
                        <div>
                        <p className="flex justify-center mt-8 mr-5 items-center gap-x-1 items-center ">
                            <TbManualGearbox size="18" /> {e.gearBoxType}
                            <BsFillFuelPumpFill size="18" className="ml-10" /> {e.fuelType}
                            <IoMdSpeedometer className="ml-10" size="20" /> {e.cruiseControl}
                        </p>
                        </div>
                    </div>
                    </div>
                    <div className=" w-[350px] h-[500px]  mt-14 mr-8">
                        <div className="w-full h-[300px]  ">
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">Model</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.model}</p>
                            </div>
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">Gövde Tipi</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.categories}</p>
                            </div>
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">Motor Hacmi</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.engineCapacity}</p>
                            </div>
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">Yakıt Tüketimi</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.fuelConsumption}</p>
                            </div>
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">Hava Yastığı</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.airbag ? "Var" :"Yok"}</p>
                            </div>
                            <div className="w-full text-sm h-14 p-1  flex justify-between items-center">
                                <p className="w-full h-14 border-b-2 pl-4 flex justify-between items-center">ABS</p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center"></p>
                                <p className="w-full h-14 border-b-2 flex justify-between items-center">{e.abs ? "Var" :"Yok"}</p>
                            </div>
                            {isLogin ? (
                                <div className="flex justify-center items-center w-full h-40">
                                <button onClick={handleClick} className="flex w-5/6 bg-blue-600 h-14 justify-center items-center rounded-lg text-white hover:bg-blue-500">Sepete Ekle</button>
                                </div>
                            ):""}
                        </div>
                    </div>
                </div>
            ))}
        </div>
    )
}
