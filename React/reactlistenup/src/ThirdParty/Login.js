import {useState, useEffect, useContext} from 'react';
import AuthContext from "../Context/AuthProvider";
import "../Style/Login.css"

import axios from 'axios';

const LOGIN_URL = '/auth';

const Login = () => {
    const {setAuth} = useContext(AuthContext);

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

            console.log(response?.data);

            const accessToken = response?.data?.accessToken;
            const roles = response?.data?.roles;

            setAuth({user: email, pwd, roles, accessToken});
            setEmail('');
            setPwd('');
            setSuccess(true);
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
        <div className="login">
            <>
                {success ? (
                    <section>
                        <h1>You are logged in!</h1>
                        <br/>
                        <p>
                            <a href="#">Go to Home</a>
                        </p>
                    </section>
                ) : (
                    <section>
                        <p className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                        <h1>Sign In</h1>
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
                                <a href="#">Sign Up</a>
                        </span>
                        </p>
                    </section>
                )}
            </>
        </div>
    )
}

export default Login
