import React from "react";
import {Link, Route, Routes} from "react-router-dom";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Footer from "./Footer";
import Home from "../Home";
import AlbumPage from "../Action/Album/AlbumPage";
import ArtistPage from "../Action/Artist/ArtistPage";
import GenrePage from "../Action/Genre/GenrePage";
import "../../Style/admin.css"
import SongPage from "../Action/Song/SongPage";


function Main() {
    return (
        <div className="container">
            <main>
                <Sidebar/>
                <article>
                    <Header/>
                    <div className="content">
                        <Routes>
                            <Route path="home" element={<Home/>}/>
                            <Route path="creator/albums" element={<AlbumPage/>}/>
                            <Route path="creator/artists" element={<ArtistPage/>}/>
                            <Route path="creator/genres" element={<GenrePage/>}/>
                            <Route path="creator/songs" element={<SongPage/>}/>
                        </Routes>
                    </div>
                </article>
            </main>
            <Footer/>
        </div>
    )
}

export default Main;