import {useState, useContext} from 'react';
import "../../Style/NewSong.css";
import AlbumSongDataContext from "../../Context/AlbumSongDataContext";

const AlbumSongs = () => {
    const {album, searchSong, setSearchSong, searchSongResults} = useContext(AlbumSongDataContext);

    return (
        <main className="main">
            <div className="list-container">
                <div className="list">
                    <h3>{album.name}</h3>
                    <input
                        className="search-bar"
                        type="text"
                        required
                        value={searchSong}
                        placeholder="Search song"
                        onChange={(e) => setSearchSong(e.target.value)}
                    />
                    <>
                        {searchSongResults.map(song => (
                            <li key={song.id}>{song.name}</li>
                        ))}
                    </>
                </div>
            </div>
        </main>

    )
}

export default AlbumSongs
