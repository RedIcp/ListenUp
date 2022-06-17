import React, {useState, useContext} from 'react';
import axios from "axios";
import GenreDataContext from '../../../Context/GenreDataContext';
import ArtistDataContext from '../../../Context/ArtistDataContext';
import AlbumDataContext from '../../../Context/AlbumDataContext';
import SongDataContext from "../../../Context/SongDataContext";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import useAuth from "../../../Hooks/useAuth";

const Song = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');
    const [genre, setGenre] = useState(null);
    const [artist, setArtist] = useState(null);
    const [album, setAlbum] = useState(null);

    const [isUpdate, setIsUpdate] = useState(false);

    const {setUpdate, searchSong, setSearchSong, searchSongsResults} = useContext(SongDataContext);
    const {searchGenre, setSearchGenre, searchGenresResults} = useContext(GenreDataContext);
    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);
    const {searchAlbum, setSearchAlbum, searchAlbumsResults} = useContext(AlbumDataContext);


    const [isSingleSong, setIsSingleSong] = useState(true);

    const [auth] = useAuth();
    const config = {
        headers: {
            Authorization: `Bearer ${auth.accessToken}`
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            if (isSingleSong) {
                if (isUpdate) {
                    const updateSong = {
                        id: id,
                        name: name,
                        genre: genre,
                        artist: artist,
                        releasedDate: new Date(),
                        uploadedDate: new Date()
                    }

                    const response = await axios.put(`http://localhost:8080/singlesong/${id}`, updateSong, config);

                    cleanup()

                    console.log(response.status)
                } else {
                    const newSong = {
                        name: name,
                        genre: genre,
                        artist: artist,
                        releasedDate: new Date(),
                        uploadedDate: new Date()
                    };

                    const response = await axios.post('http://localhost:8080/songs/singlesong', newSong, config);

                    cleanup()

                    console.log(response)
                }
            } else {
                if (isUpdate) {
                    const updateSong = {
                        id: id,
                        name: name,
                        genre: genre,
                        album: album
                    }
                    const response = await axios.put(`http://localhost:8080/albumsong/${id}`, updateSong, config);

                    cleanup()

                    console.log(response.status)
                } else {
                    const newSong = {
                        name: name,
                        genre: genre,
                        album: album
                    }

                    const response = await axios.post('http://localhost:8080/songs/albumsong', newSong, config);

                    cleanup()

                    console.log(response.status)
                }
            }
        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    const cleanup = () => {
        setUpdate(prev => !prev)
        setId(null);
        setName('');
        setGenre(null);
        setArtist(null);
        setIsUpdate(false);
    }

    const handleDelete = async (songID) => {
        try {
            const response = await axios.delete(`http://localhost:8080/songs/${songID}`, config);
            setUpdate(prev => !prev)
            console.log(response.status)
        } catch
            (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    return (
        <main className="main">
            <div className="form">
                <section>
                    <h1>New Song</h1>
                    <div className="radio-button">
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
                        <label htmlFor="name">Name: </label>
                        <input
                            id="name"
                            type="text"
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                        <label htmlFor="genre">Genre: </label>
                        <input
                            readOnly
                            id="genre"
                            type="text"
                            value={genre != null ? genre.name : "Select a genre"}/>
                        {!isSingleSong ?
                            <>
                                <label htmlFor="album">Album: </label>
                                <input
                                    readOnly
                                    id="album"
                                    type="text"
                                    value={album != null ? album.name : "Select a album"}/>
                            </> : <div></div>
                        }
                        {isSingleSong ?
                            <>
                                <label htmlFor="artist">Artist: </label>
                                <input
                                    readOnly
                                    id="artist"
                                    type="text"
                                    value={artist != null ? artist.name : "Select a artist"}
                                />
                            </> : <div></div>
                        }
                        <button>Create Song</button>
                    </form>
                </section>
            </div>
            <div className="admin-list-container">
                <div className="admin-box">
                    <h3>Genre</h3>
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
                                <div className="admin-list">
                                    <li key={genre.id} onClick={() => {
                                        setGenre(genre)
                                    }}>{genre.name}</li>
                                </div>
                            ))}
                        </>
                    </ul>
                </div>
                {!isSingleSong ? <div className="admin-box">
                        <h3>Album</h3>
                        <input
                            className="search"
                            id="name"
                            type="text"
                            required
                            value={searchAlbum}
                            placeholder="Search album"
                            onChange={(e) => setSearchAlbum(e.target.value)}
                        />
                        <ul>
                            <>
                                {searchAlbumsResults.map(album => (
                                    <div className="admin-list">
                                        <li key={album.id} onClick={() => {
                                            setAlbum(album)
                                        }}>{album.name}</li>
                                    </div>
                                ))}
                            </>
                        </ul>
                    </div> :
                    <div></div>}
                {isSingleSong ? <div className="admin-box">
                        <h3>Artist</h3>
                        <input
                            className="search"
                            type="text"
                            required
                            value={searchArtist}
                            placeholder="Search artist"
                            onChange={(e) => setSearchArtist(e.target.value)}
                        />
                        <ul>
                            <>
                                {searchArtistsResults.map(artist => (
                                    <div className="admin-list">
                                        <li key={artist.id} onClick={() => {
                                            setArtist(artist)
                                        }}>{artist.name}</li>
                                    </div>
                                ))}
                            </>
                        </ul>
                    </div> :
                    <div></div>
                }
                <div className="admin-box">
                    <h3>Songs</h3>
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
                            {searchSongsResults.map(song => (
                                <div className="admin-list">
                                    <li key={song.id}>{song.name}</li>
                                </div>
                            ))}
                        </>
                    </ul>
                </div>
            </div>
        </main>

    )
}

export default Song
