import React, {useState} from "react";
import Axios from "axios";

function Profile(){
    const url = "http://localhost:8080/users";

    const [data, setData] = useState({
        id:"",
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

        Axios.put(url,{
            id: parseInt(data.id),
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
                    ID:
                    <input onChange={(e) => handle(e)} id="id" value={data.id} type="number" /><br/>
                    Username:
                    <input onChange={(e) => handle(e)} id="username" value={data.username} type="text" /><br/>
                    Email:
                    <input onChange={(e) => handle(e)} id="email" value={data.email} type="text" /><br/>
                    Password:
                    <input onChange={(e) => handle(e)} id="password" value={data.password} type="password" /><br/>
                </label>
                <button type="submit">Update</button>
            </form>
        </div>
    );
}
export default Profile;