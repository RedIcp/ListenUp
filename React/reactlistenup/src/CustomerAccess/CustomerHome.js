import AlbumHomePage from "./AllCustomer/Action/Album/AlbumHomePage";
import GenreHomePage from "./AllCustomer/Action/Genre/GenreHomePage";
import ArtistHomePage from "./AllCustomer/Action/Artist/ArtistHomePage";
import PlaylistHomePage from "./AllCustomer/Action/Playlist/PlaylistHomePage";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

function CustomerHome() {

    return (
        <div className="home-list">
            <div >
                <h1>Albums</h1>
                <Link to="/albums"><div className="view">View all</div></Link>
                <AlbumHomePage/>
            </div>
            <div>
                <h1>Genres</h1>
                <Link to="/genres"><div className="view">View all</div></Link>
                <GenreHomePage/>
            </div>
            <div>
                <h1>Artists</h1>
                <Link to="/artists"><div className="view">View all</div></Link>
                <ArtistHomePage/>
            </div>
            <div>
                <h1>Playlists</h1>
                <Link to="/playlists"><div className="view">View all</div></Link>
                <PlaylistHomePage/>
            </div>
        </div>
    )
}

export default CustomerHome;