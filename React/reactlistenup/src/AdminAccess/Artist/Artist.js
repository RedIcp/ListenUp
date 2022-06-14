import {useState, useContext, useEffect} from 'react';
import axios from "axios";
import "../../Style/NewSong.css";
import ArtistDataContext from "../../Context/ArtistDataContext";

const Artist = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');

    const [isUpdate, setIsUpdate] = useState(false);
    const [isValid, setIsValid] = useState(false);

    const {searchArtist, setSearchArtist, searchArtistsResults} = useContext(ArtistDataContext);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updateArtist = {
                    id: id,
                    name: name
                }

                const response = await axios.put(`http://localhost:8080/artists/${id}`, updateArtist);

                setId(null);
                setName('');
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newArtist = {name: name}

                const response = await axios.post('http://localhost:8080/artists', newArtist);

                setName('');

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    return (
        <main className="main">
            <div className="form-container">
                {isUpdate ? <h2>Update Artist</h2> :
                    <h2>New Artist</h2>
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
                    {isUpdate ? <button>Update Genre</button> :
                        <button>Create Artist</button>
                    }
                    {isUpdate ? <div onClick={() => {
                            setIsUpdate(false);
                            setName('');
                        }}>Add new artist?</div> :
                        <div></div>
                    }
                </form>
            </div>
            <div className="list-container">
                <div className="list">
                    <h3>Click a artist to update it</h3>
                    <input
                        className="search-bar"
                        type="text"
                        required
                        value={searchArtist}
                        placeholder="Search genre"
                        onChange={(e) => setSearchArtist(e.target.value)}
                    />
                    <>
                        {searchArtistsResults.map(artist => (
                            <li key={artist.id} onClick={() => {
                                setId(artist.id);
                                setName(artist.name);
                                setIsUpdate(true);
                            }}>{artist.name}</li>
                        ))}
                    </>
                </div>
            </div>
        </main>
    )
}

export default Artist
