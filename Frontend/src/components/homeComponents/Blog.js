import myImage from '../../images/travel-section-bg-min.png'
import blogDatas from "../../componentServices/BlogDatas";
import {Link} from "react-router-dom";
export default function Blog(){

    return(

            <div className="h-[800px] w-screen p-10" style={{backgroundImage: `url(${myImage})`}}>
                <ol className="list-disc text-red-600   font-black p-4 ml-4 text-3xl">
                    <li>
                        <h3 className="text-white "> Blog</h3>
                    </li>
                </ol>
                <div className="flex justify-center ml-4 items-center p-10 gap-10 ">
                    {blogDatas.map((item,key) => (
                        <div className="h-[550px] bg-white rounded-lg w-[350px] gap-8 shadow-2xl flex flex-col items-center justify-between pb-8">
                        <img src={item.src} className=" mt-10 rounded-lg object-cover shadow-2xl" />
                            <p className=" text-center font-bold text-2xl">{item.description}</p>
                            <Link to={`/blog/${item.id}`} className="w-44 h-10 bg-blue-600 rounded-lg flex items-center justify-center text-white text-base">Detaylar</Link>
                        </div>
                    ))}
                </div>
            </div>


    )
}
