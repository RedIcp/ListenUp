import React, {useState, useContext, useEffect} from 'react';
import axios from "axios";
import ArtistDataContext from "../../../Context/ArtistDataContext";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Artist = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');

    const [isUpdate, setIsUpdate] = useState(false);

    const {setUpdate, searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updateArtist = {
                    id: id,
                    name: name
                }

                const response = await axios.put(`http://localhost:8080/artists/${id}`, updateArtist);
                setUpdate(prev => !prev)
                setId(null);
                setName('');
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newArtist = {name: name}

                const response = await axios.post('http://localhost:8080/artists', newArtist);
                setUpdate(prev => !prev)
                setName('');

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    const handleDelete = async (artistID) => {
        try {
            const response = await axios.delete(`http://localhost:8080/artists/${artistID}`);
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
                    {isUpdate ? <h1>Update Artist</h1> :
                        <h1>New Artist</h1>
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
                        {isUpdate ? <button>Update Artist</button> :
                            <button>Create Artist</button>
                        }
                        {isUpdate ? <div className="add-new" onClick={() => {
                                setIsUpdate(false);
                                setName('');
                            }}>Add new artist?</div> :
                            <div></div>
                        }
                    </form>
                </section>
            </div>
            <div className="admin-list-container">
                <div className="admin-box">
                    <h3>Click a artist to update it</h3>
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
                                    <div className="delete" onClick={() => {
                                        handleDelete(artist.id)
                                    }}><FontAwesomeIcon icon={faTrash}/>
                                    </div>
                                    <li key={artist.id} onClick={() => {
                                        setId(artist.id);
                                        setName(artist.name);
                                        setIsUpdate(true);
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

export default Artist
