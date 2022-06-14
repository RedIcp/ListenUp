import {useState, useContext, useEffect} from 'react';
import axios from "axios";
import "../../Style/NewSong.css";
import ArtistDataContext from "../../Context/ArtistDataContext";
import AlbumDataContext from "../../Context/AlbumDataContext";
import {format} from "date-fns";

const Album = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');
    const [artist, setArtist] = useState(null);
    const [releasedDate, setReleasedDate] = useState(null);

    const [isUpdate, setIsUpdate] = useState(false);
    const [isValid, setIsValid] = useState(false);

    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);
    const {searchAlbum, setSearchAlbum, searchAlbumsResults} = useContext(AlbumDataContext);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updateAlbum = {
                    id: id,
                    name: name,
                    artist: artist,
                    releasedDate: releasedDate,
                    uploadedDate: new Date()
                }

                const response = await axios.put(`http://localhost:8080/albums/${id}`, updateAlbum);

                setId(null);
                setName('');
                setArtist(null);
                setReleasedDate(null);
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newAlbum = {
                    name: name,
                    artist: artist,
                    releasedDate: releasedDate,
                    uploadedDate: new Date()
                }

                const response = await axios.post('http://localhost:8080/albums', newAlbum);

                setName('');
                setArtist(null);
                setReleasedDate(null);

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    return (
        <main className="main">
            <div className="form-container">
                {isUpdate ? <h2>Update Album</h2> :
                    <h2>New Album</h2>
                }
                <form onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="name">Name: </label>
                        </div>
                        <div className="col-75">
                            <input
                                id="name"
                                type="text"
                                required
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="artist">Artist: </label>
                        </div>
                        <div className="col-75">
                            <input
                                disabled
                                id="artist"
                                type="text"
                                value={artist != null ? artist.name : "Select a artist"}
                            />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="releasedDate">Artist: </label>
                        </div>
                        <div className="col-75">
                            <input
                                id="releasedDate"
                                type="date"
                                required
                                value={releasedDate != null ? releasedDate.toString() : ""}
                                onChange={(e) => setReleasedDate(e.target.value)}
                            />
                        </div>
                    </div>
                    {isUpdate ? <button>Update Album</button> :
                        <button>Create Album</button>
                    }
                    {isUpdate ? <div onClick={() => {
                            setIsUpdate(false);
                            setName('');
                            setArtist(null);
                            setReleasedDate("")
                        }}>Add new album?</div> :
                        <div></div>
                    }
                </form>
            </div>
            <div className="list-container">
                <div className="list">
                    <h3>Click a album to update it</h3>
                    <input
                        className="search-bar"
                        type="text"
                        required
                        value={searchAlbum}
                        placeholder="Search album"
                        onChange={(e) => setSearchAlbum(e.target.value)}
                    />
                    <>
                        {searchAlbumsResults.map(album => (
                            <li key={album.id} onClick={() => {
                                setId(album.id);
                                setName(album.name);
                                setArtist(album.artist);
                                setIsUpdate(true);
                            }}>{album.name}</li>
                        ))}
                    </>
                </div>
                <div className="list">
                    <h3>Artist</h3>
                    <input
                        className="search-bar"
                        type="text"
                        required
                        value={searchArtist}
                        placeholder="Search artist"
                        onChange={(e) => setSearchArtist(e.target.value)}
                    />
                    <>
                        {searchArtistsResults.map(artist => (
                            <li key={artist.id} onClick={() => {
                                setArtist(artist);
                            }}>{artist.name}</li>
                        ))}
                    </>
                </div>
            </div>
        </main>
    )
}

export default Album
