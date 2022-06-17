import Footer from "./Component/Footer";
import React from "react";
import Sidebar from "./Component/Sidebar";

function App() {
    return (
        <div className="container">
            <main>
                <Sidebar/>
            </main>
            <Footer/>
        </div>
    )
}

export default App;