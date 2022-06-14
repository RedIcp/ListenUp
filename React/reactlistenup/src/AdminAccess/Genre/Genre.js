import {useState, useContext, useEffect} from 'react';
import axios from "axios";
import "../../Style/NewSong.css";
import GenreDataContext from "../../Context/GenreDataContext";

const Genre = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');

    const [isUpdate, setIsUpdate] = useState(false);
    const [isValid, setIsValid] = useState(false);

    const {searchGenre, setSearchGenre, searchGenresResults} = useContext(GenreDataContext);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updateGenre = {
                    id: id,
                    name: name
                }

                const response = await axios.put(`http://localhost:8080/genres/${id}`, updateGenre);

                setId(null);
                setName('');
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newGenre = {name: name}

                const response = await axios.post('http://localhost:8080/genres', newGenre);

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
                {isUpdate ? <h2>Update Genre</h2> :
                    <h2>New Genre</h2>
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
                        <button>Create Genre</button>
                    }
                    {isUpdate ? <div onClick={() => {
                            setIsUpdate(false);
                            setName('');
                        }}>Add new genre?</div> :
                        <div></div>
                    }
                </form>
            </div>
            <div className="list-container">
                <div className="list">
                    <h3>Click a genre to update it</h3>
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
                                setId(genre.id);
                                setName(genre.name);
                                setIsUpdate(true);
                            }}>{genre.name}</li>
                        ))}
                    </>
                </div>
            </div>
        </main>
    )
}

export default Genre
