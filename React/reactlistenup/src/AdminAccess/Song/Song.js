import {useState, useContext} from 'react';
import axios from "axios";
import GenreDataContext from '../../Context/GenreDataContext';
import ArtistDataContext from '../../Context/ArtistDataContext';
import AlbumDataContext from '../../Context/AlbumDataContext';
import "../../Style/NewSong.css";

const Song = () => {
    const [name, setName] = useState('');

    const {searchGenre, setSearchGenre, searchGenresResults} = useContext(GenreDataContext);
    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);
    const {searchAlbum, setSearchAlbum, searchAlbumsResults} = useContext(AlbumDataContext);

    const [genre, setGenre] = useState(null);
    const [artist, setArtist] = useState(null);
    const [album, setAlbum] = useState(null);

    const [isSingleSong, setIsSingleSong] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (isSingleSong) {
                const newSong = {
                    name: name,
                    genre: genre,
                    artist: artist,
                    releasedDate: new Date(),
                    uploadedDate: new Date()
                };

                const response = await axios.post('http://localhost:8080/songs/singlesong', newSong);

                setName('');
                setArtist(null);
                setAlbum(null);
                setGenre(null);

                console.log(response)
            }
            else {
                const newSong = {name: name, genre: genre, album: album}

                const response = await axios.post('http://localhost:8080/songs/albumsong', newSong);

                setName('');
                setArtist(null);
                setAlbum(null);
                setGenre(null);

                console.log(response.status)
            }
        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    return (
        <main className="main">
            <div className="form-container">
                <h2>New Song</h2>
                <div className="radio-button" >
                    <input
                        type="radio"
                        checked={isSingleSong}
                        onClick={() => setIsSingleSong(true)}
                    />
                    <label onClick={() => setIsSingleSong(true)}>Single Song</label>
                    <input
                        type="radio"
                        checked={!isSingleSong}
                        onClick={() => setIsSingleSong(false)}
                    />
                    <label onClick={() => setIsSingleSong(false)}>Album Song</label>
                </div>
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
                            <label htmlFor="genre">Genre: </label>
                        </div>
                        <div className="col-75">
                            <input
                                disabled id="genre"
                                type="text"
                                value={genre != null ? genre.name : "Select a genre"}/>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-25">
                            <label htmlFor="album">Album: </label>
                        </div>
                        <div className="col-75">
                            <input
                                disabled id="album"
                                type="text"
                                value={album != null ? album.name : "Select a album"}/>
                        </div>
                    </div>
                    {isSingleSong ?
                        <div className="row">
                            <div className="col-25">
                                <label htmlFor="artist">Artist: </label>
                            </div>
                            <div className="col-75">
                                <input
                                    disabled id="artist"
                                    type="text"
                                    value={artist != null ? artist.name : "Select a artist"}
                                />
                            </div>
                        </div> : <div></div>
                    }
                    <button>Create Song</button>
                </form>
            </div>
            <div className="list-container">
                <div className="list">
                    <h3>Genre</h3>
                    <input
                        className="search-bar"
                        type="text"
                        required
                        value={searchGenre}
                        placeholder="Search genre"
                        onChange={(e) => setSearchGenre(e.target.value)}
                    />
                    <>
                        {searchGenresResults.map(genre => (
                            <li key={genre.id} onClick={() => {
                                setGenre(genre)
                            }}>{genre.name}</li>
                        ))}
                    </>
                </div>
                <div className="list">
                    <h3>Album</h3>
                    <input
                        className="search-bar"
                        id="name"
                        type="text"
                        required
                        value={searchAlbum}
                        placeholder="Search album"
                        onChange={(e) => setSearchAlbum(e.target.value)}
                    />
                    <>
                        {searchAlbumsResults.map(album => (
                            <li key={album.id} onClick={() => {
                                setAlbum(album)
                            }}>{album.name}</li>
                        ))}
                    </>
                </div>
                {isSingleSong ? <div className="list">
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
                                    setArtist(artist)
                                }}>{artist.name}</li>
                            ))}
                        </>
                    </div> :
                    <div></div>
                }

            </div>
        </main>

    )
}

export default Song
