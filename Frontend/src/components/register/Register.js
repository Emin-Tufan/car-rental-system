import  React, { useState } from 'react';
import axios from 'axios';
import {IconButton, InputAdornment, InputLabel, OutlinedInput, TextField} from "@mui/material";
import {Select} from "@mui/material";
import {MenuItem} from "@mui/material";
import Textarea from '@mui/joy/Textarea';
import Button from '@mui/joy/Button';
import {getBackendUrl} from "../../api/api";
import FormHelperText from '@mui/material/FormHelperText';
import FormControl from '@mui/material/FormControl';
import Swal from "sweetalert2";

const Register = () => {

    const [nameError,setNameError]=useState(false)
    const [surnameError,setSurnameError]=useState(false)
    const [identityError,setIdentityError]=useState(false)
    const [phoneError,setPhoneError]=useState(false)
    const [emailError,setEmailError]=useState(false)
    const [passwordNullError,setPasswordNullError]=useState(false)
    const [passwordNullError2,setPasswordNullError2]=useState(false)
    const [passwordSameError,setPasswordSameError]=useState(false)
    const [genderError,setGenderError]=useState(false)
    const [addressError,setAddressError]=useState(false)
    const [axiosCondition,setAxiosCondition]=useState(true)
    const [mailErrorCondition,setMailErrorCondition] = useState(false)

  const [formValues, setFormValues] = useState({
    name: '',
    surName: '',
    identityNo: '',
    phone: '',
    email: '',
    password: '',
    passwordValidation:'',
    gender: '',
      address:''
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const sendForm = () => {
      if (axiosCondition===true){
          console.log("Gönderilemedi")
          setMailErrorCondition(true)
      }else if (axiosCondition===false){
          console.log("gönderildi")
          Swal.fire({
              title: 'Kayıt Başarılı!',
              text: 'Sisteme Hoşgeldiniz',
              icon: 'success',
          })
          setMailErrorCondition(false)
          axios
              .post(getBackendUrl.register, formValues)
              .then((response) => {
                  console.log(response.data);

              })
              .catch((error) => {
                  console.log(error);
              });
      }
  }

  const conditions = () => {
      if (formValues.name.trim().length <=0) {
          setNameError(true)
          setAxiosCondition(true)
          console.log("1")
      }else {
          setNameError(false)
          setAxiosCondition(false)
      }
      if (formValues.surName.trim().length <=0){
          setSurnameError(true)
          setAxiosCondition(true)
          console.log("2")
      }else{
          setSurnameError(false)
          setAxiosCondition(false)
      }
      if (formValues.identityNo.trim().length !=11){
          setIdentityError(true)
          setAxiosCondition(true)
          console.log("3")
      }else{
          setIdentityError(false)
          setAxiosCondition(false)
      }
      if (formValues.phone.trim().length !=11){
          setPhoneError(true)
          setAxiosCondition(true)
          console.log("4")
      }else{
          setPhoneError(false)
          setAxiosCondition(false)
      }
      if (!formValues.email.includes("@")){
          setEmailError(true)
          setAxiosCondition(true)
          console.log("5")
      }else{
          setEmailError(false)
          setAxiosCondition(false)
      }
      if (formValues.password != formValues.passwordValidation ){
          setPasswordSameError(true)
          setAxiosCondition(true)
          console.log("6")
      }else{
          setPasswordSameError(false)
          setAxiosCondition(false)
      }
      if (formValues.password.length ===0 ){
          setPasswordNullError(true)
          setAxiosCondition(true)
          console.log("7")
      }else{
          setPasswordNullError(false)
          setAxiosCondition(false)
      }
      if(formValues.passwordValidation.length ===0){
          setPasswordNullError2(true)
          setAxiosCondition(true)
          console.log("8")
      }else{
          setPasswordNullError2(false)
          setAxiosCondition(false)
      }
      if (formValues.gender !== "Erkek" && formValues.gender !== "Kadın" ){
          setGenderError(true)
          setAxiosCondition(true)
          console.log("9")
      }else{
          setGenderError(false)
          setAxiosCondition(false)
      }
      if (formValues.address.trim().length <=0){
          setAddressError(true)
          setAxiosCondition(true)
          console.log("10")
      }else{
          setAddressError(false)
          setAxiosCondition(false)
      }

  }
  const handleSubmit =async (e) => {
      await conditions()
      await sendForm()

  };
  return (
      <div className="flex-col mt-16  gap-x-48  w-screen h-screen bg-gradient-to-tr from-gray-50 to-gray-100 justify-center items-stretch ">
          <h1 className="flex justify-center items-end text-2xl pt-8">Kayıt Ol</h1>
        <div className="flex gap-x-16 h-84 w-screen justify-center ">
        <div className="flex flex-col mt-12 h-72 gap-4" >
        <TextField
            required
            id="name"
            name="name"
            label="Adınız"
            variant="outlined"
            error={nameError}
            value={formValues.name}
            onChange={handleChange}
            helperText={nameError ? "Ad boş olamaz" : ""}
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
            helperText={surnameError ? "Soyad boş olamaz" : ""}
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
              helperText={emailError ? "Geçersiz e-mail" : ""}
          />
          <TextField
              id="outlined-password-input"
              label="Şifre"
              type="password"
              name="password"
              error={passwordNullError||passwordSameError}
              autoComplete="current-password"
              onChange={handleChange}
              helperText={passwordSameError ? "Şifreler uyuşmuyor" :passwordNullError? "Şifre boş olamaz" :""}
          />
        </div>
        <div className="flex flex-col mt-12 h-72 gap-4">
          <TextField
              required
              id="identityNo"
              name="identityNo"
              type="number"
              label="T.C Kimlik no"
              variant="outlined"
              error={identityError}
              helperText={identityError ? "Geçersiz TC No":""}
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
              helperText={phoneError ? "Geçersiz Cep Telefonu":""}
              onChange={handleChange}
          />
            <FormControl sx={{  minWidth: 120 }} error>
                <InputLabel style={!genderError ? {color:"grey",fontSize:"15px",padding:"3px"}:{color:"#d32f2f",fontSize:"15px"}}  id="demo-simple-select-required-label">Cinsiyet</InputLabel>
          <Select
              labelId="gender"
              id="gender"
              value={formValues.gender}
              label="Cinsiyet"
              name="gender"
              style={!genderError ? {color:"grey",fontSize:"15px",height:"56px"}:{color:"#d32f2f",fontSize:"15px",height:"56px"}}
              onChange={handleChange}
              error={genderError}
          >
            <MenuItem value="Erkek" >Erkek</MenuItem>
            <MenuItem value="Kadın">Kadın</MenuItem>
          </Select>
            {genderError ? (
                <FormHelperText>Bir seçim yapınız</FormHelperText>
            ):""}
            </FormControl>
          <TextField
              id="passwordValidation"
              label="Şifre (Tekrar)"
              type="password"
              name="passwordValidation"
              autoComplete="current-password"
              error={passwordNullError2 || passwordSameError}
              helperText={passwordSameError ? "Şifreler uyuşmuyor" :passwordNullError2? "Şifre boş olamaz" :""}
              onChange={handleChange}
          />
        </div>
        </div>
          <div style={{display:"flex",justifyContent:"center"}}>
              <FormControl sx={{  minWidth: 120 }} error>
              <Textarea
                  placeholder="Adresinizi giriniz"
                  minRows={3}
                  name="address"
                  variant="soft"
                  error={addressError}
                  color="neutral"
                  className="w-[455px]"
                  style={{marginTop: mailErrorCondition===  true ? "96px" :"0px"}}
                  onChange={handleChange}
              />
                  {addressError ? (
                      <FormHelperText>Adres ekleyin</FormHelperText>
                  ):""}
              </FormControl>
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

