import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAdd, faBook, faHeadphones, faHeart, faHome, faSearch} from "@fortawesome/free-solid-svg-icons";
import {Link, Route, Routes} from "react-router-dom";
import Header from "../Component/Header";
import AdminHome from "../AdminAccess/AdminHome";
import AlbumPage from "../AdminAccess/Action/Album/AlbumPage";
import ArtistPage from "../AdminAccess/Action/Artist/ArtistPage";
import GenrePage from "../AdminAccess/Action/Genre/GenrePage";
import SongPage from "../AdminAccess/Action/Song/SongPage";
import React from "react";
import CustomerHome from "./CustomerHome";
import AlbumSongsPage from "./AllCustomer/Action/Album/AlbumSongsPage";
import ArtistSongsPage from "./AllCustomer/Action/Artist/ArtistSongsPage";
import GenreSongsPage from "./AllCustomer/Action/Genre/GenreSongsPage";
import PlaylistSongsPage from "./AllCustomer/Action/Playlist/PlaylistSongsPage";

function CustomerMain() {
    return (
        <>
            <aside>
                <div className="logo">
                    <FontAwesomeIcon icon={faHeadphones}/>
                </div>
                <div>
                    <Link to="customer/home">
                        <li><FontAwesomeIcon icon={faHome}/> Home</li>
                    </Link>
                    <Link to="/albums/1">
                        <li><FontAwesomeIcon icon={faSearch}/> Search</li>
                    </Link>
                    <Link to="/genre/1">
                        <li><FontAwesomeIcon icon={faBook}/> Your Library</li>
                    </Link>
                    <br/>
                    <Link to="/artists/4">
                        <li><FontAwesomeIcon icon={faAdd}/> Create Playlist</li>
                    </Link>
                    <Link to="/home">
                        <li><FontAwesomeIcon icon={faHeart}/> Liked Songs</li>
                    </Link>
                    <hr/>
                </div>
            </aside>
            <article>
                <Header/>
                <div className="content">
                    <Routes>
                        <Route path="customer/home" element={<CustomerHome/>}/>
                        <Route path="albums/:id" element={<AlbumSongsPage/>}/>
                        <Route path="artists/:id" element={<ArtistSongsPage/>}/>
                        <Route path="genres/:id" element={<GenreSongsPage/>}/>
                        <Route path="playlists/:id" element={<PlaylistSongsPage/>}/>
                    </Routes>
                </div>
            </article>
        </>
    )
}

export default CustomerMain;