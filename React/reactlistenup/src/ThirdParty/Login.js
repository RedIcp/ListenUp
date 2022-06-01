
function Login(){
    return(
        <section>
            <div className="login">
                <h1>Login</h1>
                <form>
                    <p>Email</p>
                    <input type="text" name="" placeholder="Enter your email."/><br/>
                    <p>Password</p>
                    <input type="password" name="" placeholder="Enter your password"/><br/>
                    <input type="submit" value="Login"/><br/>
                    <input type="submit" value="Cancel"/>
                </form>
            </div>
        </section>
    )
}

export default Login;