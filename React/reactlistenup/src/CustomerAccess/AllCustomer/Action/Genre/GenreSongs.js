import {useState, useContext} from 'react';
import GenreSongDataContext from "../../../../Context/GenreSongDataContext";

const GenreSongs = () => {
    const {genre, searchSong, setSearchSong, searchSongResults} = useContext(GenreSongDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Genre</h2>
                <h3>{genre.name}</h3>
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

export default GenreSongs
