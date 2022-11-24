import React from "react";
import useAuth from "../Hooks/useAuth";
import {useNavigate} from "react-router-dom";

function Header() {
    const {setAuth} = useAuth();
    const navigate = useNavigate();

    function logout(){
        setAuth({});
        navigate("/")
    }
    return (
        <header>
            ListenUp<button onClick={logout}>Logout</button>
        </header>
    )
}

export default Header;


