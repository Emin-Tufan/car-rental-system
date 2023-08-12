

export default function Orders({ordered,basket}){


    return(
        <div className="mt-16 w-screen h-screen bg-gray-300 flex flex-col  items-center ">
            {ordered ? (
                <div className="flex pl-2 hover:border-2 mt-16 hover:border-blue-700 text-white  items-center w-3/4 h-1/4 bg-gray-400 rounded-lg shadow-2xl">
                    {basket.map(e=> e.map(car => (
                        <>
                        <div className="flex flex-col ml-8 justify-center items-center gap-y-2 ">
                            <img src={car.src} className="h-32 w-40" />
                            <p>{car.name}</p>
                        </div>
                            <div className="flex flex-col justify-center items-center ml-16  w-1/3 h-full  gap-y-2  ">
                                <p className="w-auto  flex justify-center items-center">Kiralama Başlangıç Tarihi</p>
                                <p className="w-auto  flex justify-center items-center">10.05.2023</p>
                            </div>
                            <div className="flex flex-col justify-center items-center  w-1/3 h-full  gap-y-2  ">
                                <p className="w-auto  flex justify-center items-center">Kiralama Bitiş Tarihi</p>
                                <p className="w-auto  flex justify-center items-center">10.05.2023</p>
                            </div>
                        </>
                    )))}
                </div>
            ):(
                <div>

                </div>
            )}
        </div>
    )
}
