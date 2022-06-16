import {useContext} from 'react';
import PlaylistSongDataContext from "../../../../Context/PlaylistSongDataContext";
import footer from "../../../Component/Footer";

const PlaylistSongs = () => {
    const {playlist, searchSong, setSearchSong, searchSongResults} = useContext(PlaylistSongDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Playlist</h2>
                <h3>{playlist.name}</h3>
                <h4 className="artist-name">Made by {playlist?.customer}</h4>
                <hr/>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchSong}
                    placeholder="Search song"
                    onChange={(e) => setSearchSong(e.target.value)}
                />
                <ul>
                    <>
                        {searchSongResults.map(song => (
                            <div className="list">
                                <li key={song?.id}>{song?.name}<div className="artist-name">{song.artist?.name}</div></li>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default PlaylistSongs
