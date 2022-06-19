import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';

const GenreDataContext = createContext({});

export const GenreDataProvider = ({ children }) => {
    const [genres, setGenres] = useState([])
    const [searchGenre, setSearchGenre] = useState('');
    const [searchGenresResults, setSearchGenresResults] = useState([]);

    const { setUpdate, data, fetchError, isLoading } = useAxiosFetch('http://localhost:8080/genres');

    useEffect(() => {
        setGenres(data);
    }, [data])

    useEffect(() => {
        const filteredResults = genres?.filter((genre) =>
            ((genre.name).toLowerCase()).includes(searchGenre.toLowerCase()));

        setSearchGenresResults(filteredResults?.reverse());
    }, [genres, searchGenre])

    return (
        <GenreDataContext.Provider value={{
            searchGenre, setSearchGenre,
            searchGenresResults, fetchError, isLoading, setUpdate
        }}>
            {children}
        </GenreDataContext.Provider>
    )
}

export default GenreDataContext;