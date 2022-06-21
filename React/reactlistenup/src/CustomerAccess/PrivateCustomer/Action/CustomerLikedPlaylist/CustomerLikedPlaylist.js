import React, {useContext} from 'react';
import CustomerLikedPlaylistDataContext from "../../../../Context/CustomerLikedPlaylistContext";
import axios from "axios";
import useAuth from "../../../../Hooks/useAuth";
import {Link} from "react-router-dom";

const CustomerPlaylist = () => {
    const {
        setUpdate,
        searchPlaylist,
        setSearchPlaylist,
        searchPlaylistsResults
    } = useContext(CustomerLikedPlaylistDataContext);

    const {auth} = useAuth();
    const config = {
        headers: {
            Authorization: `Bearer ${auth.accessToken}`
        }
    }

    return (
        <div className="list-container">
            <div className="box">
                <h2>Your Liked Playlists</h2>
                <hr/>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchPlaylist}
                    placeholder="Search song"
                    onChange={(e) => setSearchPlaylist(e.target.value)}
                />
                <ul>
                    <>
                        {searchPlaylistsResults?.map(playlist => (
                            <div className="lists">
                                <Link to={`/playlists/${playlist?.id}`}>
                                    <li key={playlist.id} className="obj-card">{playlist.name}
                                        <div className="artist-name">{playlist.customer?.username}</div>
                                    </li>
                                </Link>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default CustomerPlaylist
