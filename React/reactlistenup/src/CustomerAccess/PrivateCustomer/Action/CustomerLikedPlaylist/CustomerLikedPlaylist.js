import React, {useContext} from 'react';
import CustomerLikedPlaylistDataContext from "../../../../Context/CustomerLikedPlaylistContext";
import axios from "axios";
import useAuth from "../../../../Hooks/useAuth";

const CustomerPlaylist = () => {
    const { setUpdate, searchPlaylist, setSearchPlaylist, searchPlaylistsResults } = useContext(CustomerLikedPlaylistDataContext);

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

                                    <li key={playlist.id} className="obj-card">{playlist.name}
                                        <div className="artist-name">{playlist.customer?.username}</div>
                                    </li>

                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default CustomerPlaylist
