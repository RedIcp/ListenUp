import {useState, useEffect, useContext} from 'react';
import jwt_decode from "jwt-decode";
import axios from 'axios';
import {Link, useLocation, useNavigate} from "react-router-dom";
import useAuth from "../Hooks/useAuth";
import "../Style/form.css"

const Login = () => {
    const {auth, setAuth} = useAuth();

    const navigate = useNavigate();
    //const location = useLocation();
    //const from = location.state?.from?.pathname || "/";

    const [email, setEmail] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        setErrMsg('');
    }, [email, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const request = {
                email: email,
                password: pwd
            }

            const response = await axios.post('http://localhost:8080/login', request);

            const accessToken = response?.data?.accessToken;
            const decodedAccessToken = jwt_decode(accessToken)
            const roles = decodedAccessToken.roles;
            const id = decodedAccessToken.userID;

            setAuth({id, roles, accessToken});

            setEmail('');
            setPwd('');
            setErrMsg('');

            setSuccess(true);

            if(roles.includes("ADMIN")){
                navigate("/creator/home")
            }
            else if (roles.includes("CUSTOMER")){
                navigate("/customer/home")
            }

        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 400) {
                setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
            }
        }
    }

    return (
        <div className="form-container">
            <div className="single-form">
                <section>
                    <p className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Login</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="email">Email:</label>
                        <input
                            type="text"
                            id="email"
                            autoComplete="off"
                            onChange={(e) => setEmail(e.target.value)}
                            value={email}
                            required
                        />

                        <label htmlFor="password">Password:</label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                        />
                        <button>Login</button>
                    </form>
                    <p>
                        Need an Account?<br/>
                        <span className="line">
                            {/*put router link here*/}
                            <Link to="/register">Sign Up</Link>
                        </span>
                    </p>
                </section>
            </div>
            }
        </div>
    )
}

export default Login
