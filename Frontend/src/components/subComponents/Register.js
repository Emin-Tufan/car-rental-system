import React, { useState } from 'react';
import axios from 'axios';
import {IconButton, InputAdornment, InputLabel, OutlinedInput, TextField} from "@mui/material";
import {Select} from "@mui/material";
import {MenuItem} from "@mui/material";
import Textarea from '@mui/joy/Textarea';
import Button from '@mui/joy/Button';
import {getBackendUrl} from "../../api";

const Register = () => {

    const [nameError,setNameError]=useState(false)
    const [surnameError,setSurnameError]=useState(false)
    const [identityError,setIdentityError]=useState(false)
    const [phoneError,setPhoneError]=useState(false)
    const [emailError,setEmailError]=useState(false)
    const [passwordError,setPasswordError]=useState(false)
    const [genderError,setGenderError]=useState(false)
    const [addressError,setAddressError]=useState(false)
    const [axiosCondition,setAxiosCondition]=useState(false)

  const [formValues, setFormValues] = useState({
    name: '',
    surName: '',
    identityNo: '',
    phone: '',
    email: '',
    password: '',
    passwordValidation:'',
    gender: 'Cinsiyet',
      address:''
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (formValues.name.trim().length <=0){
        setNameError(true)
        setAxiosCondition(true)
    }
      if (formValues.surName.trim().length <=0){
          setSurnameError(true)
          setAxiosCondition(true)
      }
      if (formValues.identityNo.trim().length !=11){
          setIdentityError(true)
          setAxiosCondition(true)
      }
      if (formValues.phone.trim().length !=11){
          setPhoneError(true)
          setAxiosCondition(true)
      }
      if (!formValues.email.includes("@")){
          setEmailError(true)
          setAxiosCondition(true)
      }
      if (formValues.password != formValues.passwordValidation || formValues.password.length ==0 ||formValues.passwordValidation.length ==0){
          setPasswordError(true)
          setAxiosCondition(true)
      }
      if (formValues.gender != "Erkek" && formValues.gender != "Kadın" ){
          setGenderError(true)
          setAxiosCondition(true)
      }
      if (formValues.address.trim().length <=0){
          setAddressError(true)
          setAxiosCondition(true)
      }
      if (axiosCondition){
          console.log("Gönderilemedi")
      }else{
          axios
              .post(getBackendUrl.register, formValues)
              .then((response) => {
                  console.log(response.data);
              })
              .catch((error) => {
                  console.log(error);
              });
      }
  };
  return (
      <div className="flex-col  gap-x-48  w-screen h-screen bg-gradient-to-tr from-gray-50 to-gray-100 justify-center items-stretch ">
        <div className="flex gap-x-16 mt-6 h-96 w-screen justify-center items-end ">
        <div className="flex flex-col mt-16 h-72 gap-4" >
        <TextField
            required
            id="name"
            name="name"
            label="Adınız"
            variant="outlined"
            error={nameError}
            value={formValues.name}
            onChange={handleChange}
        />
        <TextField
            required
            id="surName"
            name="surName"
            label="Soyadınız"
            error={surnameError}
            variant="outlined"
            value={formValues.surName}
            onChange={handleChange}
        />
          <TextField
              required
              id="email"
              name="email"
              label="E-Mail"
              error={emailError}
              variant="outlined"
              value={formValues.email}
              onChange={handleChange}
          />
          <TextField
              id="outlined-password-input"
              label="Şifre"
              type="password"
              name="password"
              error={passwordError}
              autoComplete="current-password"
              onChange={handleChange}
          />
        </div>
        <div className="flex flex-col mt-16 h-72 gap-4">
          <TextField
              required
              id="identityNo"
              name="identityNo"
              type="number"
              label="T.C Kimlik no"
              variant="outlined"
              error={identityError}
              value={formValues.identityNo}
              onChange={handleChange}
          />
          <TextField
              required
              id="phone"
              name="phone"
              type="number"
              label="Cep Telefonu"
              error={phoneError}
              variant="outlined"
              size="medium"
              value={formValues.phone}
              onChange={handleChange}
          />
          <Select
              labelId="gender"
              id="gender"
              value={formValues.gender}
              label="Cinsiyet"
              name="gender"
              style={!genderError ? {color:"grey",fontSize:"15px"}:{color:"#d32f2f",fontSize:"15px"}}
              onChange={handleChange}
              error={genderError}
          >
            <MenuItem value="Cinsiyet">Cinsiyet</MenuItem>
            <MenuItem value="Erkek" >Erkek</MenuItem>
            <MenuItem value="Kadın">Kadın</MenuItem>
          </Select>
          <TextField
              id="passwordValidation"
              label="Şifre (Tekrar)"
              type="password"
              name="passwordValidation"
              autoComplete="current-password"
              error={passwordError}
              onChange={handleChange}
          />
        </div>
        </div>
          <div className="flex justify-center ">
              <Textarea
                  placeholder="Adresinizi giriniz"
                  minRows={3}
                  name="address"
                  variant="soft"
                  error={addressError}
                  color="neutral"
                  className="w-[455px]"
                  onChange={handleChange}
              />
          </div>
          <div className="flex justify-center mt-3">
              <Button
                  className="w-[455px] h-12"
                  onClick={handleSubmit}
              >
                  Kayıt Ol
              </Button>
          </div>
      </div>
  );
};

export default Register;

