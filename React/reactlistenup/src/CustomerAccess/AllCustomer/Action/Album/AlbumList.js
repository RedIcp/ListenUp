import {useContext} from "react";
import AlbumDataContext from "../../../../Context/AlbumDataContext";
import {Link} from "react-router-dom";

function AlbumList() {
    const {searchAlbumsResults} = useContext(AlbumDataContext);

    return (
        <div>
            <ul>
                <>
                    {searchAlbumsResults?.slice(0, 5).map(album => (
                        <div className="lists">
                            <Link to={`/albums/${album?.id}`}>
                                <li key={album?.id} className="obj-card">{album?.name}
                                    <div className="artist-name">{album.artist?.name}</div>
                                </li>
                            </Link>
                        </div>
                    ))}
                </>
            </ul>
        </div>
    )
}

export default AlbumList;