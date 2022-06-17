import {useContext} from 'react';
import PlaylistDataContext from "../../../../Context/PlaylistDataContext";
import {Link} from "react-router-dom";

const PlaylistFull = () => {
    const {searchPlaylist, setSearchPlaylist, searchPlaylistsResults} = useContext(PlaylistDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Playlists</h2>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchPlaylist}
                    placeholder="Search playlist"
                    onChange={(e) => setSearchPlaylist(e.target.value)}
                />
                <ul>
                    <>
                        {searchPlaylistsResults.map(playlist => (
                            <div className="list">
                                <Link className="link"  to={"/playlists/"+playlist.id}><li key={playlist?.id}>{playlist?.name}</li>
                                    <div className="artist-name">{playlist?.customer?.username}</div></Link>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default PlaylistFull
