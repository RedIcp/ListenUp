import React, {useState} from "react";
import Axios from "axios";

function Signup(){
    const url = "http://localhost:8080/signup";

    const [data, setData] = useState({
        username:"",
        email:"",
        password:""
    })

    function handle(e){
        const newDate = {...data};
        newDate[e.target.id] = e.target.value;
        setData(newDate);
        console.log(newDate);
    }
    function submit(e){
        e.preventDefault();

        Axios.post(url,{
            username: data.username,
            email: data.email,
            password: data.password
        })
            .then(res => {
                console.log(res.data);
            })
    }
    return(
        <div>
            <form onSubmit={(e)=>submit(e)}>
                <label>
                    Username:
                    <input onChange={(e) => handle(e)} id="username" value={data.username} type="text" placeholder="Enter your username." /><br/>
                    Email:
                    <input onChange={(e) => handle(e)} id="email" value={data.email} type="text" placeholder="Enter your email." /><br/>
                    Password:
                    <input onChange={(e) => handle(e)} id="password" value={data.password} type="password" placeholder="Enter your password." /><br/>
                </label>
                <button type="submit">Add</button>
            </form>
        </div>
    );
}
export default Signup;