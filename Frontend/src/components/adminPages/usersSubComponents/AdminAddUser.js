import {useState} from "react";
import axios from "axios";
import {getBackendUrl} from "../../../api/api";


export default function AdminAddUser(){

    const [formValues, setFormValues] = useState({
        name: '',
        surName: '',
        identityNo: '',
        phone: '',
        email: '',
        password: '',
        passwordValidation:'',
        gender: '',
        address:'',
        type:''
    });
    const handleChange = (e) => {
        const {name,value} = e.target
        setFormValues((prevState)=>({
                ...prevState,
                [name]:value
            }
        ))
    }
    const handleSubmit = () => {
        console.log(formValues)
        axios
            .post(getBackendUrl.register, formValues)
            .then((response) => {
                console.log("gönderildi");

            })
            .catch((error) => {
                console.log(error);
            });
    }
    return(
        <div className="h-auto w-full">
            <h1 className="flex justify-center text-xl font-black mt-10">Kullanıcı Ekle</h1>
            <div className="flex flex-col items-center  flex-wrap m-4 h-screen gap-y-4 ">
                <div className="flex gap-x-10">
                    <input type="text" className="mb-0" placeholder="İsim" name="name" onChange={handleChange} value={formValues.name} />
                    <input type="text" className="mb-0" placeholder="Soyisim" name="surName" onChange={handleChange} value={formValues.surName}/>
                </div>
                <div className="flex gap-x-10">
                    <input type="text" className="mb-0" placeholder="TC No" name="identityNo" onChange={handleChange} value={formValues.identityNo}/>
                    <input type="text" className="mb-0" placeholder="Telefon Numarası" name="phone" onChange={handleChange} value={formValues.phone} />
                </div>
                <div className="flex gap-x-10">
                    <input type="text" className="mb-0" placeholder="Email Adresi" name="email" onChange={handleChange} value={formValues.email} />
                    <input type="text" className="mb-0" placeholder="Şifresi" name="password" onChange={handleChange} value={formValues.password} />
                </div>
                <div className="flex gap-x-10">
                    <select className="w-60" onChange={handleChange} name="gender" value={formValues.gender}>
                        <option value="Erkek">Erkek</option>
                        <option value="Kadın">Kadın</option>
                    </select>
                    <select className="w-60" onChange={handleChange} name="type" value={formValues.type}>
                        <option value="ROLE_CUSTOMER">Customer</option>
                        <option value="ROLE_ADMIN">Admin</option>
                    </select>
                </div>

                <div className="flex gap-x-10">
                    <textarea className="w-96 h-20 p-2" name="address" value={formValues.address} onChange={handleChange} placeholder="Adres" />
                </div>
                <button onClick={handleSubmit} className="flex bg-blue-700 text-white border-2 mb-2 hover:text-white hover:bg-blue-500 border-blue-700 rounded-lg  justify-center w-52 h-10 items-center" >Ekle</button>
            </div>
        </div>
    )
}
