import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faList, faBook, faHeadphones, faHeart, faHome, faSearch} from "@fortawesome/free-solid-svg-icons";
import {Link, Route, Routes} from "react-router-dom";
import Header from "../Component/Header";
import React, {useEffect, useState} from "react";
import CustomerHome from "./CustomerHome";
import AlbumSongsPage from "./AllCustomer/Action/Album/AlbumSongsPage";
import ArtistSongsPage from "./AllCustomer/Action/Artist/ArtistSongsPage";
import GenreSongsPage from "./AllCustomer/Action/Genre/GenreSongsPage";
import PlaylistSongsPage from "./AllCustomer/Action/Playlist/PlaylistSongsPage";
import PlaylistFullPage from "./AllCustomer/Action/Playlist/PlaylistFullPage";
import GenreFullPage from "./AllCustomer/Action/Genre/GenreFullPage";
import ArtistFullPage from "./AllCustomer/Action/Artist/ArtistFullPage";
import AlbumFullPage from "./AllCustomer/Action/Album/AlbumFullPage";
import SearchSongPage from "./AllCustomer/SearchSongPage";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import CustomerPlaylistPage from "./PrivateCustomer/Action/CustomerPlaylist/CustomerPlaylistPage";
import CustomerLikedSongPage from "./PrivateCustomer/Action/CustomerLikedSong/CustomerLikedSongPage";
import CustomerLikedPlaylistPage from "./PrivateCustomer/Action/CustomerLikedPlaylist/CustomerLikedPlaylistPage";

function CustomerMain() {

    const [stompClient, setStompClient] = useState(null);
    useEffect(() => {
        const socket = SockJS("http://localhost:8080/ws");
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/greetings', (data) => {
                console.log(data);
                onMessageReceived(data);
            });
        });
        setStompClient(stompClient);
    }, []);

    function onMessageReceived(data)
    {
        const result=  JSON.parse(data.body);
        console.log(result.text);
        alert(result.text)
    };

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
                    <Link to="/search">
                        <li><FontAwesomeIcon icon={faSearch}/> Search</li>
                    </Link>
                    <Link to="/library">
                        <li><FontAwesomeIcon icon={faBook}/> Your Library</li>
                    </Link>
                    <Link to="/collection">
                        <li><FontAwesomeIcon icon={faHeart}/> Liked Songs</li>
                    </Link>
                    <Link to="/likedplaylists">
                        <li><FontAwesomeIcon icon={faList}/> Liked Playlists</li>
                    </Link>
                    <hr/>
                </div>
            </aside>
            <article>
                <Header/>
                <div className="content">
                    <Routes>
                        <Route path="customer/home" element={<CustomerHome/>}/>
                        <Route path="albums" element={<AlbumFullPage/>}/>
                        <Route path="albums/:id" element={<AlbumSongsPage/>}/>
                        <Route path="artists" element={<ArtistFullPage/>}/>
                        <Route path="artists/:id" element={<ArtistSongsPage/>}/>
                        <Route path="genres" element={<GenreFullPage/>}/>
                        <Route path="genres/:id" element={<GenreSongsPage/>}/>
                        <Route path="playlists" element={<PlaylistFullPage/>}/>
                        <Route path="playlists/:id" element={<PlaylistSongsPage/>}/>
                        <Route path="search" element={<SearchSongPage/>}/>
                        <Route path="library" element={<CustomerPlaylistPage/>}/>
                        <Route path="collection" element={<CustomerLikedSongPage/>}/>
                        <Route path="likedplaylists" element={<CustomerLikedPlaylistPage/>}/>
                    </Routes>
                </div>
            </article>
        </>
    )
}

export default CustomerMain;