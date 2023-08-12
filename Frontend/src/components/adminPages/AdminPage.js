import {BiUserCircle} from 'react-icons/bi'
import {useEffect, useState} from "react";
import axios from "axios";
import {getBackendUrl} from "../../api/api";
import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export default function AdminPage() {
    const [customerCount, setCustomerCount] = useState();
    const [lastFiveCustomer, setLastFiveCustomer] = useState([]);
  
    const getCustomer = async () => {
      try {
        const response = await axios.get(getBackendUrl.customerLastFiveUser, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}` // Tokeni Authorization başlığına ekliyoruz
          }
        });
        setLastFiveCustomer(response.data);
      } catch (error) {
        // Hata işlemleri
      }
    };
  
    const getData = async () => {
      try {
        const response = await axios.get(getBackendUrl.customerCount, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}` // Tokeni Authorization başlığına ekliyoruz
          }
        });
        setCustomerCount(response.data);
      } catch (error) {
        // Hata işlemleri
      }
    };
  
    useEffect(() => {
      getData();
      getCustomer();
    }, []);
  
    console.log("customercount", customerCount);
    console.log("lastfivecustomer", lastFiveCustomer);

    return(
        <div className="mt-16 flex h-screen w-screen bg-gray-200">
            <div className="h-2/3 w-1/2 flex flex-col items-center">
                <h1 className="mt-8 text-xl font-black">Toplam Kullanıcı Sayısı </h1>
                <div className="flex mt-4 justify-center items-center text-2xl"><BiUserCircle size="60" color="#5262E7" />{customerCount}</div>
                <h1 className="mt-8 text-xl font-black mb-4">Son kayıtlar </h1>
                <TableContainer className="ml-16" component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">Ad</TableCell>
                                <TableCell align="left">Soyad</TableCell>
                                <TableCell align="left">Phone Number</TableCell>
                                <TableCell align="left">Cinsiyet</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {lastFiveCustomer.map((row) => (
                                <TableRow
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                >
                                    <TableCell align="left">{row.name}</TableCell>
                                    <TableCell align="left">{row.surName}</TableCell>
                                    <TableCell align="left">{row.phone}</TableCell>
                                    <TableCell align="left">{row.gender}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
            <div className="h-2/3 w-1/2 flex flex-col items-center">
                <h1 className="mt-8 text-xl font-black">Son Kiralamalar </h1>
            </div>
        </div>
    )
}
