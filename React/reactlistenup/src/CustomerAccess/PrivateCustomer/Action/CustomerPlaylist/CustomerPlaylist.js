import {useContext} from 'react';
import CustomerPlaylistContext from "../../../../Context/CustomerPlaylistDataContext";

const CustomerPlaylist = () => {
    const {searchPlaylist, setSearchPlaylist, searchPlaylistsResults} = useContext(CustomerPlaylistContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Your Playlists</h2>
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
                                <li key={playlist.id} className="obj-card">{playlist.name}<div className="artist-name">{playlist.artist?.name}</div></li>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default CustomerPlaylist
