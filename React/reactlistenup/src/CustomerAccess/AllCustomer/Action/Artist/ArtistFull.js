import {useContext} from 'react';
import {Link} from "react-router-dom";
import ArtistDataContext from "../../../../Context/ArtistDataContext";

const ArtistFull = () => {
    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Artists</h2>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchArtist}
                    placeholder="Search artists"
                    onChange={(e) => setSearchArtist(e.target.value)}
                />
                <ul>
                    <>
                        {searchArtistsResults.map(artist => (
                            <div className="list">
                                <Link className="link"  to={"/artists/"+artist.id}><li key={artist?.id}>{artist?.name}</li></Link>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default ArtistFull
