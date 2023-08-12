import {Link, Outlet} from "react-router-dom";


export default function AdminCarsLayout(){


    return(
        <div className="mt-16 flex bg-gray-100 h-screen w-screen">
            <div className="h-screen w-40 bg-gray-200 flex flex-col border-r-2 ml-2 border-r-gray-300 pt-4">
                <Link to="listele" className="w-full h-10 text-blue-700 hover:opacity-70 ">Araçları Listele</Link>
                <Link to="ekle" className="w-full h-10 text-blue-700 hover:opacity-70 ">Araç Ekle</Link>
                <Link to="sil" className="w-full h-10 text-blue-700 hover:opacity-70 ">Araç Sil</Link>
            </div>
            <Outlet />
        </div>
    )
}
