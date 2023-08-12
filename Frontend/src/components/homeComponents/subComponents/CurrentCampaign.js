import React from 'react'
import {useParams} from "react-router-dom";
import campaignDatas from "../../../componentServices/CampaignDatas";

export default function CurrentCampaign(){
    const {id} = useParams()
    const currentCampaign = campaignDatas.filter((e) => e.id == id)
    return(
        currentCampaign.map((e,key) =>
        <div className="flex justify-center mt-16 w-screen h-screen bg-gradient-to-tr from-gray-50 to-gray-100 ">
          <div className="mt-10 w-full h-5/6 bg-gray-200 shadow-2xl">
              <ol className="text-red-600  list-disc ml-24 text-3xl font-bold ">
                 <li> <h1 className="text-3xl text-stone-900 mt-8  ">{e.title}</h1></li>
              </ol>
                  <div className=" flex justify-center items-center shadow-2xl bg-white rounded-lg w-11/12 ml-16 mt-16 p-6 ">
                      <div className="h-11/12 w-11/12 ">
              <h1 className="ml-8 text-3xl text-blue-900 font-bold">Kampanya Koşulları:</h1>
              {e.conditions.map((condition)=>(
              <ol className="list-disc mt-6 ml-8 text-lg">
                  <li>{condition}</li>
              </ol>
              ))}
                      </div>
                      <div className="flex bg-red-200 w-[400px] h-[400px] justify-center items-center ml-4">
                          <img className="w-full h-full" src={e.src} />
                      </div>
                  </div>
          </div>
        </div>
        )
    )
}
