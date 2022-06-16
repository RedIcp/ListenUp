import React from "react";
import {Link, Route, Routes} from "react-router-dom";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Footer from "./Footer";
import AdminHome from "../AdminHome";
import AlbumPage from "../Action/Album/AlbumPage";
import ArtistPage from "../Action/Artist/ArtistPage";
import GenrePage from "../Action/Genre/GenrePage";
import "../../Style/admin.css"
import SongPage from "../Action/Song/SongPage";


function AdminMain() {
    return (
        <div className="container">
            <main className="main">
                <Sidebar/>
                <article>
                    <Header/>
                    <div className="content">
                        <Routes>
                            <Route path="creator/home" element={<AdminHome/>}/>
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

export default AdminMain;