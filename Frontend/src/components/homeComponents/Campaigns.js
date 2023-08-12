import campaignDatas from "../../componentServices/CampaignDatas";
import {Link} from "react-router-dom";

export default function Campaigns(){

    return(
        <div className="w-full bg-gray-300 h-[670px]">
            <ol className="list-disc text-red-600  font-black p-4 ml-8 text-3xl">
                <li>
                <h3 className="text-stone-900 mt-5 "> Güncel Araç Kiralama Kampanyaları</h3>
                </li>
            </ol>
            <div className="flex  items-center justify-center mt-10">
            <ul className="flex gap-10">
                {campaignDatas.map((item,key) => (
                    <div className="w-[270px] h-[480px] bg-white rounded-lg flex flex-col gap-5 items-center justify-center shadow-2xl">
                        <img src={item.src} className="w-[250px] h-[360px] mt-10 rounded-lg object-cover" />
                        <p className=" text-center">{item.description}</p>
                        <Link to={`/kampanya/${item.id}`} className="w-44 h-10 bg-blue-600 mb-4 rounded-lg flex items-center justify-center text-white text-base">Detaylar</Link>
                    </div>
                ))}
            </ul>
            </div>
        </div>
    )
}
