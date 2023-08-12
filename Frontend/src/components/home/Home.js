import HomeSlider from "../homeComponents/HomeSlider";
import Campaigns from "../homeComponents/Campaigns";
import Blog from "../homeComponents/Blog";
import {useState} from "react";
import Loading from "../loading/Loading";
import {Outlet} from "react-router";


export default function Home(){

    return(
        <div>
            <HomeSlider />
            <Campaigns />
            <Blog />
        </div>
    )
}
