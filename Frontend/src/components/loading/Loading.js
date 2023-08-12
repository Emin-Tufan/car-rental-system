import ClipLoader from "react-spinners/ClipLoader";
import {useEffect, useState} from "react";


export default function Loading({setIsLoading}){
    useEffect(()=>{
        setTimeout(()=> {
            setIsLoading(false)
        },400)
    },[])
    return(
        <div className="flex justify-center items-center h-screen ">
            <ClipLoader color="#1E90FF"  />
        </div>
    )
}
