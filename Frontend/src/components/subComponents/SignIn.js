import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import {useState} from "react";

const theme = createTheme();

export default function SignIn() {

    const [isRemember,setIsRemember]=useState(false)
    const [formValues,setFormValues]=useState({
        email:'',
        password:'',
    })
    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(formValues.email,formValues.password,isRemember)
    };
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormValues((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    }
    return (
        <div className="flex-col mt-16 w-screen h-screen bg-gradient-to-tr from-gray-50 to-gray-100 justify-center items-stretch ">
        <ThemeProvider theme={theme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar sx={{ m: 4, bgcolor: 'secondary.main' }}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <Typography component="h1" variant="h5">
                        Giriş Yap
                    </Typography>
                    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="E-mail Adresiniz"
                            name="email"
                            autoComplete="email"
                            autoFocus
                            value={formValues.email}
                            onChange={handleChange}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Şifreniz"
                            type="password"
                            id="password"
                            value={formValues.password}
                            onChange={handleChange}
                            autoComplete="current-password"
                        />
                        <FormControlLabel
                            control={<Checkbox value="remember" color="primary" />}
                            value={isRemember}
                            onChange={()=>setIsRemember(!isRemember)}
                        />
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign In
                        </Button>
                        <Grid container>
                            <Grid item xs>
                                <Link href="sifremiunuttum" variant="body2">
                                    Şifremi unuttum
                                </Link>
                            </Grid>
                            <Grid item>
                                <Link href="kayitol" variant="body2">
                                    {"Hesabınız yok mu? Kayıt Olun"}
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
        </div>
    );
}
