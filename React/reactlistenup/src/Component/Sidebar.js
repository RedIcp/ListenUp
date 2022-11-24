import React from "react";
import {Route, Routes} from "react-router-dom";
import useAuth from "../Hooks/useAuth";
import Login from "../ThirdParty/Login";
import Register from "../ThirdParty/Register";
import Unauthorized from "../ThirdParty/Unauthorized";
import Missing from "../ThirdParty/Missing";
import "../Style/app.css"
import "../Style/form.css"
import "../Style/admin.css"
import AdminMain from "../AdminAccess/AdminMain";
import CustomerMain from "../CustomerAccess/CustomerMain";
import Home from "../ThirdParty/Home";

function Sidebar() {
    const {auth} = useAuth();
    return (
        <>
            {
                auth?.roles?.includes("ADMIN") ?
                    <>
                        <AdminMain/>
                    </>

                    : auth?.roles?.includes("CUSTOMER") ?
                        <>
                            <CustomerMain/>
                        </> : (
                            <>
                                <div>
                                    <Routes>
                                        <Route path="/" element={<Home/>}/>
                                        <Route path="/login" element={<Login/>}/>
                                        <Route path="register" element={<Register/>}/>
                                        <Route path="unauthorized" element={<Unauthorized/>}/>
                                        <Route path="*" element={<Missing/>}/>
                                    </Routes>
                                </div>
                            </>
                        )
            }
        </>
    )
}

export default Sidebar;