import React from "react";
import {useParams} from "react-router-dom";
import blogDatas from "../../../componentServices/BlogDatas";
export default function CurrentBlog(){
    const {id} =useParams()
    const currentBlog =blogDatas.filter((e,key)=>e.id == id)

    return(
        <div className="mt-16 w-screen bg-gray-300  ">
            {currentBlog.map((e,key) => (
                <div className="w-screen h-auto pb-4 ">
                <ol className="flex mt-4 text-red-600 w-screen list-disc ml-24 text-3xl font-bold ">
                    <li> <h1 className="text-3xl text-stone-900 mt-8  ">{e.description}</h1></li>
                </ol>
                <div className="m-auto mt-6 w-5/6 h-auto bg-white rounded-lg shadow-2xl p-4 text-base ">
                    {e.blog.map(e=>(
                        <div>
                         <div className="p-2">{e.blogWrite}</div>
                          <h1 className="p-2 text-2xl font-black text-blue-800">{e.title}</h1>
                            <img className="p-2 rounded " src={e.blogSrc} alt="" />
                        </div>
                    ))}

                </div>
                </div>
            ) )}
        </div>
    )
}
