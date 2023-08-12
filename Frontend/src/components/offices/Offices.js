import {GoLocation} from 'react-icons/go'
import {BsTelephone} from 'react-icons/bs'
import {AiOutlineMail} from 'react-icons/ai'
import {BiTime} from 'react-icons/bi'
export default function Offices(){


    return(
        <div className="w-screen h-auto mt-16 bg-gray-200 flex flex-col items-center">
                <input className="mt-4 w-4/5 hover:border-2 hover:border-blue-700 bg-white rounded-lg h-12 flex justify-start items-center p-2" type="text" placeholder="Şube ara"/>
            <div className="flex flex-wrap mb-14 w-4/5 h-auto justify-around items-start shadow-xl rounded-lg bg-white ">
                <div className="flex flex-col mb-12 mt-8 h-80 w-1/4 bg-gray-100 m-4 rounded-lg shadow hover:border-blue-500 hover:border-2">
                    <h2 className="p-4 text-lg font-black">Başlık</h2>
                    <p className="flex p-3 gap-x-2 h-14 items-center">
                        <GoLocation size="19" color="red" />
                        Adres
                    </p>
                    <p className="h-14 gap-x-2 p-3 flex items-center">
                        <BsTelephone size="18" color="red" />
                        telefon
                    </p>
                    <p className="h-14 gap-x-2 p-3 flex  items-center">
                        <AiOutlineMail size="19" color="red" />
                        Mail
                    </p>
                    <p className="h-14 gap-x-2 p-3 flex  items-center">
                        <BiTime size="19" color="red" />
                        Mail
                    </p>
                    <div className="w-full flex justify-center items-center">
                    <button className="flex bg-white border-2 mb-2 hover:text-white hover:bg-blue-700 border-blue-700 rounded-lg  justify-center w-52 h-10 items-center">Detaylı Bilgi</button>
                    </div>
                </div>

            </div>
        </div>
    )
}
