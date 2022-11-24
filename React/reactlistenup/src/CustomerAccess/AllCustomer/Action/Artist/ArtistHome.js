import {useContext} from "react";
import ArtistDataContext from "../../../../Context/ArtistDataContext";
import {Link} from "react-router-dom";

function ArtistHome() {
    const {searchArtistsResults} = useContext(ArtistDataContext);

    return (
        <div>
            <ul>
                <>
                    {searchArtistsResults?.slice(0,5).map(artist => (
                        <div className="lists">
                            <Link to={`/artists/${artist?.id}`}>
                                <li key={artist?.id} className="obj-card">{artist?.name}</li>
                            </Link>
                        </div>
                    ))}
                </>
            </ul>
        </div>
    )
}

export default ArtistHome;