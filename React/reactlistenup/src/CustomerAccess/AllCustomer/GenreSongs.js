import {useState, useContext} from 'react';
import "../../Style/NewSong.css";
import GenreSongDataContext from "../../Context/GenreSongDataContext";

const GenreSongs = () => {
    const {genre, searchSong, setSearchSong, searchSongResults} = useContext(GenreSongDataContext);

    return (
        <main className="main">
            <div className="list-container">
                <div className="list">
                    <h3>{genre.name}</h3>
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

export default GenreSongs
