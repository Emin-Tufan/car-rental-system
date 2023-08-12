import React, {useState} from 'react'
import {Outlet} from "react-router-dom";
import {BsFilter,BsFillFuelPumpFill } from 'react-icons/bs'
import {FaSuitcase} from 'react-icons/fa'
import {MdPerson} from 'react-icons/md'
import {categories} from "../../componentServices/CarFilterDatas";
import{fuelType} from "../../componentServices/CarFilterDatas";
import {gearboxType} from "../../componentServices/CarFilterDatas";
import {cars} from "../../componentServices/CarDatas";
import {TbManualGearbox} from 'react-icons/tb'
import {Link} from 'react-router-dom'

export default function Cars(){
    const [selectedCategories, setSelectedCategories] = useState(categories);
    const [selectedFuelType,setSelectedFuelType] = useState(fuelType)
    const [selectedGearBoxType,setSelectedGearBoxType]=useState(gearboxType)


    const filteredCars = categories.filter(e => e.isSelected == true)
    const filteredCategories=filteredCars.map(e=>e.name)
    const filteredFuelTypes=fuelType.filter(e => e.isSelected==true)
    const filteredFuelCategories =filteredFuelTypes.map(e=>e.name)
    const gearBoxTypes=gearboxType.filter(e=>e.isSelected==true)
    const filteredGearBox=gearBoxTypes.map(e=>e.name)


    let carArray
    if (!filteredCategories.includes("Tümü")){
        carArray=cars.filter(e =>filteredCategories.includes(e.categories))
    }else{
        carArray=cars
    }
    if (!filteredFuelCategories.includes("Tümü")){
        const carArray2=carArray.filter(e=>filteredFuelCategories.includes(e.fuelType))
        carArray=carArray2

    }else {

    }
    if (!filteredGearBox.includes("Tümü")){
        const carArray3=carArray.filter(e=>filteredGearBox.includes(e.gearBoxType))
        carArray=carArray3

    }else{

    }
    const handleCategoryClick = (index) => {
        const updatedCategories = [...selectedCategories];
        if (index != 0 ){
        updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
        updatedCategories[0].isSelected = false
        }else{
            updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
            updatedCategories.map((value,index) => {
                if (index !== 0){
                    updatedCategories[index].isSelected = false;
                }
            })
        }
        setSelectedCategories(updatedCategories);
    };
    const handleFuelType = (index) => {
        const updatedCategories = [...selectedFuelType];
        if (index != 0 ){
            updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
            updatedCategories[0].isSelected = false
        }else{
            updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
            updatedCategories.map((value,index) => {
                if (index !== 0){
                    updatedCategories[index].isSelected = false;
                }
            })
        }
        setSelectedFuelType(updatedCategories);
    };
    const handleGearBoxType = (index) => {
        const updatedCategories = [...selectedGearBoxType];
        if (index != 0 ){
            updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
            updatedCategories[0].isSelected = false
        }else{
            updatedCategories[index].isSelected = !updatedCategories[index].isSelected;
            updatedCategories.map((value,index) => {
                if (index !== 0){
                    updatedCategories[index].isSelected = false;
                }
            })
        }
        setSelectedGearBoxType(updatedCategories);
    };

    return(
        <div className="Background flex flex-wrap gap-x-1 gap-y-8 justify-around ">
            <div className=" w-[300px] h-[600px] flex-col mt-6 justify-start items-center  ml-6 shadow-2xl rounded-lg bg-white ">
                <div className="flex mt-2 h-20 w-full items-center justify-center border-b-2  ">
                <h2 className="flex text-lg gap-x-1 font-black text-blue-700" >
                    <BsFilter size="26" /> FİLTRELE
                </h2>
                </div>
                <div>
                <h3 className="flex font-medium text-lg justify-center items-center mt-4">Segment</h3>
                <div className="w-full h-36 mt-2">
                    {selectedCategories.map((category, index) => (
                        <button
                            key={index}
                            onClick={() => handleCategoryClick(index)}
                            className={`align-middle w-auto rounded h-7 ml-2 mt-1 text-sm p-1 hover:opacity-30 border-2 ${
                                category.isSelected ? 'text-red-400 border-red-400 ' : ''
                            } `}
                        >
                            {category.name}
                        </button>
                    ))}
                </div>
                </div>
                <div className="h-20">
                <h3 className="flex font-medium text-lg justify-center items-center mt-4">Yakıt Tipi</h3>
                {selectedFuelType.map((category, index) => (
                    <button
                        key={index}
                        onClick={() => handleFuelType(index)}
                        className={`align-middle w-auto rounded h-7 ml-2 mt-2 text-sm p-1 hover:opacity-30 border-2 ${
                            category.isSelected ? 'text-red-400 border-red-400 ' : ''
                        } `}
                    >
                        {category.name}
                    </button>
                ))}
                </div>
                <div className="h-20 mt-10">
                <h3 className="flex font-medium text-lg justify-center items-center mt-4">Şanzıman Tipi</h3>
                {selectedGearBoxType.map((category, index) => (
                    <button
                        key={index}
                        onClick={() => handleGearBoxType(index)}
                        className={`align-middle w-auto rounded h-7 ml-2 mt-2 text-sm p-1 hover:opacity-30 border-2 ${
                            category.isSelected ? 'text-red-400 border-red-400 ' : ''
                        } `}
                    >
                        {category.name}
                    </button>
                ))}
                </div>
            </div>
            <Outlet />
                {carArray.map((e,index)=> (
            <div className="bg-white h-[600px] w-96 rounded-lg shadow-2xl mb-4 hover:border-2 mt-6 hover:border-blue-700" >
                    <div>
                    <div className="h-28 flex flex-col items-center pt-2 border-b-2 ">
                        <h5 className="text-green-500 text-sm">{e.categories}</h5>
                        <h1 className="text-lg font-black">{e.name}</h1>
                        <h4 className="text-sm mt-5">{e.model}</h4>
                    </div>
                        <div className="h-72 flex flex-col justify-center  ">
                            <div className="flex justify-around items-center">
                                <div className="h-8 w-16  mt-6 ">
                                    <h4 className="text-xs flex justify-center items-center font-thin">SINIFI</h4>
                                    <h4 className="flex justify-center items-center font-black text-sm">{e.class}</h4>
                                </div>
                                <div className="h-8 w-32 border-l-2 border-r-2 mt-6 ">
                                    <h4 className="text-xs flex justify-center items-center font-thin">MİN.SÜRÜCÜ</h4>
                                    <h4 className="flex text-sm justify-center items-center font-black">{e.minAge}</h4>
                                </div>
                                <div className="h-8 w-16 mt-6 ">
                                    <h4 className="text-xs flex justify-center items-center font-thin">MİN.EHLİYET</h4>
                                    <h4 className="flex text-sm justify-center items-center font-black">{e.minDriving}</h4>
                                </div>
                            </div>
                            <div className="flex justify-center w-full h-52 items-center mt-4 bg-contain ">
                                <img src={e.src} className="w-[318px] h-[200px] object-cover  " />
                        </div>
                            </div>
                            <div className="h-14 flex justify-around gap-x-2 items-end pt-4">
                                <div className="flex gap-x-1">
                                <TbManualGearbox size="18" />
                                <h4 className="flex text-sm justify-center items-center font-black">{e.gearBoxType}</h4>
                                </div>
                                <div className="flex gap-x-1">
                                <BsFillFuelPumpFill size="18" />
                                <h4 className="flex text-sm justify-center items-center font-black">{e.fuelType}</h4>
                                </div>
                            </div>
                                <div className="h-14 border-b-2 flex justify-around items-start gap-x-2 pt-3">
                                <div className="flex gap-x-1">
                                    <FaSuitcase size="18" />
                                    <h4 className="flex text-sm justify-center items-center font-black">{e.luggage}</h4>
                                </div>
                                    <div className="flex gap-x-1">
                                        <MdPerson size="20" />
                                        <h4 className="flex text-sm justify-center items-center font-black">{e.capacity}</h4>
                                    </div>
                                </div>
                        <div className="flex justify-center items-center h-20">
                        <Link to={`${e.id}`} className="flex justify-center items-center bg-blue-600 rounded-lg h-12 text-lg font-black text-white w-11/12">DETAYLI BİLGİ</Link>
                        </div>
                    </div>

                    </div>
                ))}

        </div>
    )
}
