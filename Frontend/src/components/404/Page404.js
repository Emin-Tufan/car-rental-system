import {Link} from 'react-router-dom'

export default function Page404(){

    return (
        <div className="mt-16 h-screen ">
            <h1 className="flex pt-4 text-3xl font-black">Sayfa Bulunamadı</h1>
            <Link to="/" className="text-blue-800 " >Anasayfaya Dön</Link>
        </div>
    )
}
