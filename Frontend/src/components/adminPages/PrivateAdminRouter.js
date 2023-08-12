import {Navigate} from "react-router-dom";

export default function PrivateAdminRouter({children,isAdminLogin}){
    if (!isAdminLogin) {
      return <Navigate to="/admin" />
    }
    return children
}
