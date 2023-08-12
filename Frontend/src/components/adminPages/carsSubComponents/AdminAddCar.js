import {useState} from "react";
import vwVan from "../../../images/vwVAN.png";
import axios from "axios";
import {getBackendUrl} from "../../../api/api";

export default function AdminAddCar(){

    const [formValues,setFormValues] = useState(
        {
            categories:"",
            name:"",
            model:"",
            carClass:"",
            minAge:"",
            minDriving:"",
            gearBoxType:"",
            fuelType:"",
            luggage:"",
            capacity:"",
            engineCapacity:"",
            fuelConsumption:"",
            doors:"",
            airbag:false,
            abs:false,
            cruiseControl:false
        }
    )
    const handleChange = (e) => {
      const {name,value} = e.target
        setFormValues((prevState)=>({
            ...prevState,
                [name]:value
            }
    ))
    }
    const handleChecked =(e) =>{
        const {name,checked} = e.target
        setFormValues((prevState)=>({
            ...prevState,
            [name]:checked
        }))
    }


    const handleSubmit = () => {
console.log(formValues)
        axios.post(getBackendUrl.addCar,formValues).then((res)=>console.log("eklendi"))
    }
    return(
        <div className="h-auto w-full">
            <h1 className="flex mt-10 justify-center text-xl font-black">Araç Ekle</h1>
            <div className="flex flex-col items-center  flex-wrap m-4 h-screen gap-y-4 ">
                <div className="flex gap-x-10">
                    <input type="text" className="mb-0" placeholder="Araç Kategorisi" name="categories" onChange={handleChange} value={formValues.categories} />
                <input type="text" className="mb-0" placeholder="Araç Adı" name="name" onChange={handleChange} value={formValues.name} />
                </div>
                <div className="flex gap-x-10">
                <input type="text" className="mb-0" placeholder="Model" name="model" onChange={handleChange} value={formValues.model}/>
                <input type="text" className="mb-0" placeholder="Sınıfı" name="carClass" onChange={handleChange} value={formValues.carClass} />
                </div>
                <div className="flex gap-x-10">
                <input type="text" className="mb-0" placeholder="Min Şoför Yaşı" name="minAge" onChange={handleChange} value={formValues.minAge} />
                <input type="text" className="mb-0" placeholder="Min Ehliyet Yılı" name="minDriving" onChange={handleChange} value={formValues.minDriving} />
                </div>
                <div className="flex gap-x-10">
                <select className="w-60" onChange={handleChange} name="gearBoxType" value={formValues.gearBoxType}>
                    <option value="">Şanzıman Tipi</option>
                    <option value="Otomatik">Otomatik</option>
                    <option value="Manuel">Manuel</option>
                </select>
                <select className="w-60"onChange={handleChange} name="fuelType" value={formValues.fuelType}>
                    <option>Yakıt Tipi</option>
                    <option value="Benzinli">Benzinli</option>
                <option value="Dizel">Dizel</option>
            </select>
                </div>
                <div className="flex gap-x-10">
                <input type="text" className="mb-0" placeholder="Bagaj lt"onChange={handleChange} name="luggage" value={formValues.luggage} />
                <input type="text" className="mb-0" placeholder="Kişi Kapasitesi" onChange={handleChange} name="capacity" value={formValues.capacity} />
                </div>
                <div className="flex gap-x-10">
                <input type="text" className="mb-0" placeholder="Motor Kapasitesi" onChange={handleChange} name="engineCapacity" value={formValues.engineCapacity} />
                <input type="text" className="mb-0" placeholder="Ortalama Tüketim" onChange={handleChange} name="fuelConsumption" value={formValues.fuelConsumption} />
                </div>
                <input type="text" className="mb-0" placeholder="Kapı Sayısı" onChange={handleChange} name="doors" value={formValues.doors} />
                <div className="flex text-sm justify-center items-center">
               Airbag <input type="checkbox" className="mb-0 h-5 w-5 ml-2 mr-2" onChange={handleChecked} name="airbag" value={formValues.airbag} />
                ABS <input type="checkbox"  className="mb-0 h-5 w-5 ml-2 mr-2" onChange={handleChecked} name="abs" value={formValues.abs} />
                Hız Sabitleyici <input type="checkbox"  className="mb-0 h-5 w-5 ml-2" onChange={handleChecked} name="cruiseControl" value={formValues.cruiseControl} />
                </div>
                <button onClick={handleSubmit} className="flex bg-blue-700 text-white border-2 mb-2 hover:text-white hover:bg-blue-500 border-blue-700 rounded-lg  justify-center w-52 h-10 items-center" >Ekle</button>
            </div>
        </div>
    )
}
