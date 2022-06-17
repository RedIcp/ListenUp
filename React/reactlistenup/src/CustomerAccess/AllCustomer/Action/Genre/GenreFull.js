import {useContext} from 'react';
import GenreDataContext from "../../../../Context/GenreDataContext";
import {Link} from "react-router-dom";

const GenreFull = () => {
    const {searchGenre, setSearchGenre, searchGenresResults} = useContext(GenreDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Genres</h2>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchGenre}
                    placeholder="Search genre"
                    onChange={(e) => setSearchGenre(e.target.value)}
                />
                <ul>
                    <>
                        {searchGenresResults.map(genre => (
                            <div className="list">
                                <Link className="link"  to={"/genres/"+genre.id}><li key={genre?.id}>{genre?.name}</li></Link>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default GenreFull
