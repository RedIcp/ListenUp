import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';

const ArtistDataContext = createContext({});

export const ArtistDataProvider = ({ children }) => {
    const [artists, setArtists] = useState([])
    const [searchArtist, setSearchArtist] = useState('');
    const [searchArtistsResults, setSearchArtistsResults] = useState([]);

    const { setUpdate, data, fetchError, isLoading } = useAxiosFetch('http://localhost:8080/artists');

    useEffect(() => {
        setArtists(data);
    }, [data])

    useEffect(() => {
        const filteredResults = artists.filter((artist) =>
            ((artist.name).toLowerCase()).includes(searchArtist.toLowerCase()));

        setSearchArtistsResults(filteredResults.reverse());
    }, [artists, searchArtist])

    return (
        <ArtistDataContext.Provider value={{
            searchArtist, setSearchArtist,
            searchArtistsResults, fetchError, isLoading, setUpdate
        }}>
            {children}
        </ArtistDataContext.Provider>
    )
}

export default ArtistDataContext;