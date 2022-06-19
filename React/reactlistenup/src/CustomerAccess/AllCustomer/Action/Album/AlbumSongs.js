import {useState, useContext} from 'react';
import AlbumSongDataContext from "../../../../Context/AlbumSongDataContext";

const AlbumSongs = () => {
    const {album, searchSong, setSearchSong, searchSongResults} = useContext(AlbumSongDataContext);

    return (
            <div className="list-container">
                <div className="box">
                    <h2>Album</h2>
                    <h3>{album.name}</h3>
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
                            {searchSongResults?.map(song => (
                                <div className="list">
                                    <li key={song.id}>{song.name}<div className="artist-name">{song.artist?.name}</div></li>
                                </div>
                            ))}
                        </>
                    </ul>
                </div>
            </div>
    )
}

export default AlbumSongs
