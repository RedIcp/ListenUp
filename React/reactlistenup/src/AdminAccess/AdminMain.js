import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHeadphones, faHome} from "@fortawesome/free-solid-svg-icons";
import {Link, Route, Routes} from "react-router-dom";
import Header from "../Component/Header";
import AdminHome from "./AdminHome";
import AlbumPage from "./Action/Album/AlbumPage";
import ArtistPage from "./Action/Artist/ArtistPage";
import GenrePage from "./Action/Genre/GenrePage";
import SongPage from "./Action/Song/SongPage";
import React from "react";

function AdminMain() {
    return (
        <>
            <aside>
                <div className="logo">
                    <FontAwesomeIcon icon={faHeadphones}/>
                </div>
                <div>
                    <Link to="/creator/home">
                        <li><FontAwesomeIcon icon={faHome}/> Home</li>
                    </Link>
                    <Link to="/creator/artists">
                        <li id="artist">Artist</li>
                    </Link>
                    <Link to="/creator/genres">
                        <li id="genre">Genre</li>
                    </Link>
                    <Link to="/creator/albums">
                        <li id="album">Album</li>
                    </Link>
                    <Link to="/creator/songs">
                        <li id="song">Songs</li>
                    </Link>
                    <hr/>
                </div>
            </aside>
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
        </>
    )
}

export default AdminMain;