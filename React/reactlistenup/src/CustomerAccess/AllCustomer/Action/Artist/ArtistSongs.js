import {useState, useContext} from 'react';
import ArtistSongDataContext from "../../../../Context/ArtistSongDataContext";

const ArtistSongs = () => {
    const {artist, searchSong, setSearchSong, searchSongResults} = useContext(ArtistSongDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Artist</h2>
                <h3>{artist.name}</h3>
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
                                <li key={song.id}>{song.name}</li>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default ArtistSongs
