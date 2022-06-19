import React, {useState, useContext, useEffect} from 'react';
import axios from "axios";
import ArtistDataContext from "../../../Context/ArtistDataContext";
import AlbumDataContext from "../../../Context/AlbumDataContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTrash} from '@fortawesome/free-solid-svg-icons';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import useAuth from "../../../Hooks/useAuth";

const Album = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');
    const [artist, setArtist] = useState(null);
    const [releasedDate, setReleasedDate] = useState(null);

    const [isUpdate, setIsUpdate] = useState(false);

    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);
    const {setUpdate, searchAlbum, setSearchAlbum, searchAlbumsResults} = useContext(AlbumDataContext);

    const {auth} = useAuth();
    const config = {
        headers: {
            Authorization: `Bearer ${auth.accessToken}`
        }
    }


    const [stompClient, setStompClient] = useState(null);

    useEffect(() => {
        const socket = SockJS("http://localhost:8080/ws");
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/greetings', (data) => {
                console.log(data);
                onMessageReceived(data);
            });
        });
        setStompClient(stompClient);
    }, []);

    function sendMessage(data) {
        stompClient.send("/app/hello", {}, JSON.stringify(data));
    };

    function onMessageReceived(data)
    {
        const result=  JSON.parse(data.body);
        console.log(result.text);
        alert(result.text)
    };

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

                const response = await axios.put(`http://localhost:8080/albums/${id}`, updateAlbum, config);
                setUpdate(prev => !prev)
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

                const response = await axios.post('http://localhost:8080/albums', newAlbum, config);
                setUpdate(prev => !prev)
                setName('');
                setArtist(null);
                setReleasedDate(null);
                sendMessage(newAlbum)

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    const handleDelete = async (albumID) => {
        try {
            const response = await axios.delete(`http://localhost:8080/albums/${albumID}`, config);
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
                    {isUpdate ? <h1>Update Album</h1> :
                        <h1>New Album</h1>
                    }
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="name">Name: </label>
                        <input
                            id="name"
                            type="text"
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                        <label htmlFor="artist">Artist: </label>
                        <input
                            readOnly
                            id="artist"
                            type="text"
                            value={artist != null ? artist.name : "Select a artist"}
                        />
                        <label htmlFor="releasedDate">Released Date: </label>
                        <input
                            id="releasedDate"
                            type="date"
                            required
                            value={releasedDate != null ? releasedDate.toString() : ""}
                            onChange={(e) => setReleasedDate(e.target.value)}
                        />
                        {isUpdate ? <button>Update Album</button> :
                            <button>Create Album</button>
                        }
                        {isUpdate ? <div className="add-new" onClick={() => {
                                setIsUpdate(false);
                                setName('');
                                setArtist(null);
                                setReleasedDate("")
                            }}>Add new album?</div> :
                            <div></div>
                        }
                    </form>
                </section>
            </div>

            <div className="admin-list-container">
                <div className="admin-box">
                    <h3>Click a album to update it</h3>
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
                                <div className="admin-list">
                                    <div className="delete" onClick={() => {
                                        handleDelete(album.id)
                                    }}><FontAwesomeIcon icon={faTrash}/>
                                    </div>
                                    <li key={album.id} onClick={() => {
                                        setId(album.id);
                                        setName(album.name);
                                        setArtist(album.artist);
                                        setIsUpdate(true);
                                    }}>{album.name}</li>

                                </div>
                            ))}
                        </>
                    </ul>
                </div>
                <div className="admin-box">
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
                                        setArtist(artist);
                                    }}>{artist.name}</li>
                                </div>
                            ))}
                        </>
                    </ul>
                </div>
            </div>
        </main>
    )
}

export default Album
