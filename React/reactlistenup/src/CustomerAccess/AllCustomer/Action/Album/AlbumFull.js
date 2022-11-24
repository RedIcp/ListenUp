import {useContext} from 'react';
import {Link} from "react-router-dom";
import AlbumDataContext from "../../../../Context/AlbumDataContext";

const AlbumFull = () => {
    const {searchAlbum, setSearchAlbum, searchAlbumsResults} = useContext(AlbumDataContext);

    return (
        <div className="list-container">
            <div className="box">
                <h2>Albums</h2>
                <input
                    className="search"
                    type="text"
                    required
                    value={searchAlbum}
                    placeholder="Search album"
                    onChange={(e) => setSearchAlbum(e.target.value)}
                />
                <ul>
                    <>
                        {searchAlbumsResults.map(album => (
                            <div className="list">
                                <Link className="link"  to={"/albums/"+album.id}><li key={album?.id}>{album?.name}</li>
                                    <div className="artist-name">{album.artist?.name}</div></Link>
                            </div>
                        ))}
                    </>
                </ul>
            </div>
        </div>
    )
}

export default AlbumFull
