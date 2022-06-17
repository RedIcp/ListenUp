import React, {useState, useContext, useEffect} from 'react';
import axios from "axios";
import GenreDataContext from "../../../Context/GenreDataContext";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

const Genre = () => {
    const [id, setId] = useState(0);
    const [name, setName] = useState('');

    const [isUpdate, setIsUpdate] = useState(false);

    const {setUpdate, searchGenre, setSearchGenre, searchGenresResults} = useContext(GenreDataContext);

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {

            if (isUpdate) {
                const updateGenre = {
                    id: id,
                    name: name
                }

                const response = await axios.put(`http://localhost:8080/genres/${id}`, updateGenre);
                setUpdate(prev => !prev)
                setId(null);
                setName('');
                setIsUpdate(false);

                console.log(response.status)
            } else {
                const newGenre = {name: name}

                const response = await axios.post('http://localhost:8080/genres', newGenre);
                setUpdate(prev => !prev)
                setName('');

                console.log(response.status)
            }

        } catch (err) {
            console.log(`Error: ${err.message}`);
        }
    }

    const handleDelete = async (genreID) => {
        try {
            const response = await axios.delete(`http://localhost:8080/genres/${genreID}`);
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
                    {isUpdate ? <h1>Update Genre</h1> :
                        <h1>New Genre</h1>
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
                        {isUpdate ? <button>Update Genre</button> :
                            <button>Create Genre</button>
                        }
                        {isUpdate ? <div className="add-new" onClick={() => {
                                setIsUpdate(false);
                                setName('');
                            }}>Add new genre?</div> :
                            <div></div>
                        }
                    </form>
                </section>
            </div>
            <div className="admin-list-container">
                <div className="admin-box">
                    <h3>Click a genre to update it</h3>
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
                                <div>
                                    <div className="delete" onClick={() => {
                                        handleDelete(genre.id)
                                    }}><FontAwesomeIcon icon={faTrash}/>
                                    </div>
                                    <li className="admin-list" key={genre.id} onClick={() => {
                                        setId(genre.id);
                                        setName(genre.name);
                                        setIsUpdate(true);
                                    }}>{genre.name}</li>
                                </div>
                            ))}

                        </>
                    </ul>
                </div>
            </div>
        </main>
    )
}

export default Genre
