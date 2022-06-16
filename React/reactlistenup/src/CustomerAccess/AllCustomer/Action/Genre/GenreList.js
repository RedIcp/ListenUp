import {useContext} from "react";
import GenreDataContext from "../../../../Context/GenreDataContext";
import {Link} from "react-router-dom";

function GenreList() {
    const {searchGenresResults} = useContext(GenreDataContext);

    return (
        <div>
            <ul>
                <>
                    {searchGenresResults?.slice(0, 5).map(genre => (
                        <div className="lists">
                            <Link to={`/genres/${genre?.id}`}>
                                <li key={genre?.id} className="obj-card">{genre?.name}
                                    <div className="artist-name">{genre.artist?.name}</div>
                                </li>
                            </Link>
                        </div>
                    ))}
                </>
            </ul>
        </div>
    )
}

export default GenreList;