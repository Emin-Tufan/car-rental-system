import * as React from 'react';
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getBackendUrl } from "../../api/api";
import axios from "axios";

export default function AdminSignIn({ setIsAdminLogin, isAdminLogin }) {
  const navigate = useNavigate();
  const [formValues, setFormValues] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    const { value, name } = e.target;
    setFormValues((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = () => {
    console.log(formValues);
    axios.post(getBackendUrl.adminLogin, formValues)
      .then(res => {
        const token = res.data.token; // Tokenin API yanıtından alındığını varsayıyoruz
  
        // Tokeni localStorage'e kaydetme işlemi
        localStorage.setItem('token', token);
  
        setIsAdminLogin(true);
        navigate("/adminpage");
      })
      .catch(error => {
        // Hata işlemleri
      });
  };

  console.log(isAdminLogin);

  return (
    <div className="mt-16 flex flex-col items-center justify-center h-screen w-screen bg-gray-100">
      <input type="text" placeholder="administrator" className="h-10 p-2 mb-1 w-52 rounded-lg text-sm" name="email" value={formValues.email} onChange={handleChange} />
      <input type="password" placeholder="password" className="h-10 p-2 mb-1 w-52 rounded-lg text-sm" name="password" value={formValues.password} name="password" onChange={handleChange} />
      <button onClick={handleSubmit} className="h-10 w-52 bg-blue-500 text-white rounded-lg border-2 hover:bg-blue-300">Giriş Yap</button>
    </div>
  );
}
