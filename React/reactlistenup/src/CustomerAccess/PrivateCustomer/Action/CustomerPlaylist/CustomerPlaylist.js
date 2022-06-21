import React, {useContext, useState} from 'react';
import CustomerPlaylistContext from "../../../../Context/CustomerPlaylistDataContext";
import useAuth from "../../../../Hooks/useAuth";
import axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

const CustomerPlaylist = () => {
    const {setUpdate, searchPlaylist, setSearchPlaylist, searchPlaylistsResults} = useContext(CustomerPlaylistContext);

    const [id, setId] = useState(0);
    const [name, setName] = useState('');
    const [isUpdate, setIsUpdate] = useState(false);
    const {auth} = useAuth();
    const config = {
        headers: {
            Authorization: `Bearer ${auth.accessToken}`
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updatePlaylist = {
                    id: id,
                    customer: {
                        id: auth.id
                    }
                }

                const response = await axios.put(`http://localhost:8080/playlists/${id}`, updatePlaylist, config);
                setUpdate(prev => !prev)
                setId(null);
                setName('');
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newPlaylist = {
                    name: name,
                    customer: {
                        id: auth.id
                    }
                }

                const response = await axios.post('http://localhost:8080/playlists', newPlaylist, config);
                setUpdate(prev => !prev)
                setName('');

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    const handleDelete = async (playlistID) => {
        try {
            const response = await axios.delete(`http://localhost:8080/playlists/${playlistID}`, config);
            setUpdate(prev => !prev)
        } catch
            (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    return (
        <main className="main">
            <div className="form">
                <section>
                    {isUpdate ? <h1>Update Playlist</h1> :
                        <h1>New Playlist</h1>
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
                        {isUpdate ? <button>Update Playlist</button> :
                            <button>Create Playlist</button>
                        }
                        {isUpdate ? <div className="add-new" onClick={() => {
                                setIsUpdate(false);
                                setName('');
                            }}>Add new playlist?</div> :
                            <div></div>
                        }
                    </form>
                </section>
            </div>
            <div className="admin-list-container">
                <div className="admin-box">
                    <h3>Click a playlist to update it</h3>
                    <input
                        className="search"
                        type="text"
                        required
                        value={searchPlaylist}
                        placeholder="Search your playlist"
                        onChange={(e) => setSearchPlaylist(e.target.value)}
                    />
                    <ul>
                        <>
                            {searchPlaylistsResults?.map(playlist => (
                                <div className="admin-list">
                                    <div className="delete" onClick={() => {
                                        handleDelete(playlist.id)
                                    }}><FontAwesomeIcon icon={faTrash}/>
                                    </div>
                                    <li key={playlist.id} onClick={() => {
                                        setId(playlist.id);
                                        setName(playlist.name);
                                        setIsUpdate(true);
                                    }}>{playlist.name}</li>
                                </div>
                            ))}
                        </>
                    </ul>
                </div>
            </div>
        </main>
    )
}

export default CustomerPlaylist
