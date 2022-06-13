import React, {useEffect, useState} from "react";
import axios from "axios";
function User() {

    const [users, setUsers] = useState([]);

    useEffect(() => {
        GetAllUsers();
    },[users]);

    const GetAllUsers =() => {
        axios.get(`http://localhost:8080/users`)
            .then(res => {
                setUsers(res.data);
            })
            .catch(err => {
                console.log(err.message);
            });
    }

    return (
        <ul>
            {
                users
                    .map(user =>
                        <li key={user.id}>{user.username}</li>
                    )
            }
        </ul>
    );
}
export default User;