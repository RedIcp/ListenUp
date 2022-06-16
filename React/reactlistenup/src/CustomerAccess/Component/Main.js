import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAdd, faBook, faHeadphones, faHeart, faHome, faSearch} from "@fortawesome/free-solid-svg-icons";
import React from "react";
import {Link, Route, Routes} from "react-router-dom";
import AlbumSongsPage from "../AllCustomer/Action/Album/AlbumSongsPage";
import ArtistSongsPage from "../AllCustomer/Action/Artist/ArtistSongsPage";
import GenreSongsPage from "../AllCustomer/Action/Genre/GenreSongsPage";
import PlaylistSongsPage from "../AllCustomer/Action/Playlist/PlaylistSongsPage";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Footer from "./Footer";
import CustomerHome from "../CustomerHome";

function Main() {
    return (
        <div className="container">
            <main>
                <Sidebar/>
                <article>
                    <Header/>
                    <div className="content">
                        <Routes>
                            <Route path="home" element={<CustomerHome/>}/>
                            <Route path="albums/:id" element={<AlbumSongsPage/>}/>
                            <Route path="artists/:id" element={<ArtistSongsPage/>}/>
                            <Route path="genres/:id" element={<GenreSongsPage/>}/>
                            <Route path="playlists/:id" element={<PlaylistSongsPage/>}/>
                        </Routes>
                    </div>
                </article>
            </main>
            <Footer/>
        </div>
    )
}

export default Main;