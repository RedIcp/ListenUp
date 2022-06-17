import {Link} from "react-router-dom";

function Home() {
    return(
        <div>
            <Link to="/login"><button>Login</button></Link>
            <Link to="/register"><button>Signup</button></Link>
        </div>
    )
}

export default Home;