import {Link, useNavigate} from "react-router-dom";
import Swal from 'sweetalert2'

export default function Basket({basket,setBasket,setOrdered}){
    const navigate=useNavigate()
    const handleDelete = () => {
        setBasket([])
        navigate("/araclar")
    }
    const handleClick = () => {
        Swal.fire({
            title: 'Kiralama Başarılı!',
            text: 'Araç kiralanmıştır',
            icon: 'success',
        })
        setOrdered(true)
    }

    return(
        <div className="mt-16 w-screen h-screen bg-gray-300 flex flex-col  items-center ">
            {console.log(basket)}
            {basket.length >0 ? (
            basket.map(e=>(
                <div className="flex pl-2 hover:border-2 mt-16 hover:border-blue-700 text-white  items-center w-3/4 h-2/3 bg-gray-400 rounded-lg shadow-2xl">
                    {e.map(car=>(
                        <>
                        <div className="h-full pt-3 w-1/2 flex flex-col   ">
                            <img src={car.src} className="h-3/5 w-4/5 mt-4" />
                            <p className="flex justify-center items-center mr-20 mt-8 text-xl font-black text-white">{car.name}</p>
                        </div>
                            <div className="h-full pt-16 w-1/2 flex flex-col mt-4 gap-y-8 text-lg ">
                                <div className="flex gap-x-6 text-black">
                                    <p className="w-1/2  flex justify-center items-center">Kiralama Başlangıç Tarihi</p>
                                    <p className="w-1/2  flex justify-center items-center">Kiralama Bitiş Tarihi</p>
                                </div>
                                <div className="flex gap-x-6">
                                    <p className="w-1/2  flex justify-center items-center">10.05.2023</p>
                                    <p className="w-1/2   flex justify-center items-center">09.06.2023</p>
                                </div>
                                <div className="flex flex-col gap-y-4 mt-4 ">
                                    <p className="flex w-full h-full flex justify-center items-center text-black text-2xl">Ücret</p>
                                    <p className="flex w-full h-full flex justify-center items-center text-xl">500tl</p>
                                </div>
                                <div className="flex justify-evenly mt-8 ">
                                    <button onClick={handleClick} className="flex w-36 bg-blue-600 h-10 justify-center items-center rounded-lg text-white hover:bg-blue-500">Kirala</button>
                                <button onClick={handleDelete} className="flex w-36 bg-red-600 h-10 justify-center items-center rounded-lg text-white hover:bg-red-500">Kaldır</button>
                                </div>
                            </div>

                        </>
                    ))}
                </div>
                ))
            ):(
                <div className="flex flex-col gap-y-8 h-screen w-screen justify-center items-center">
                    <p className="text-2xl">Sepetiniz Boş</p>
                    <Link to={"/araclar"}>Araçlara Gidin</Link>
                </div>
            )}
        </div>
    )
}
