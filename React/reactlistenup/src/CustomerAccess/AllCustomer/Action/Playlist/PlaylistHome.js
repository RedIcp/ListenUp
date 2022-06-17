import {useContext} from "react";
import {Link} from "react-router-dom";
import PlaylistDataContext from "../../../../Context/PlaylistDataContext";

function PlaylistHome() {
    const {searchPlaylistsResults} = useContext(PlaylistDataContext);

    return (
        <div>
            <ul>
                <>
                    {searchPlaylistsResults?.slice(0, 5).map(playlist => (
                        <div className="lists">
                            <Link to={`/playlists/${playlist?.id}`}>
                                <li key={playlist?.id} className="obj-card">{playlist?.name}
                                    <div className="artist-name">{playlist?.customer?.username}</div>
                                </li>
                            </Link>
                        </div>
                    ))}
                </>
            </ul>
        </div>
    )
}

export default PlaylistHome;