import {useContext} from 'react';
import CustomerLikedSongDataContext from "../../../../Context/CustomerLikedSongDataContext";

const CustomerLikedSong = () => {
    const {searchSong, setSearchSong, searchSongsResults} = useContext(CustomerLikedSongDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Liked Songs</h2>
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
                        {searchSongsResults?.map(song => (
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

export default CustomerLikedSong
