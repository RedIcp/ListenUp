import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';

const SongDataContext = createContext({});

export const SongDataProvider = ({ children }) => {
    const [songs, setSongs] = useState([])
    const [searchSong, setSearchSong] = useState('');
    const [searchSongsResults, setSearchSongsResults] = useState([]);

    const { data, fetchError, isLoading } = useAxiosFetch('http://localhost:8080/songs');

    useEffect(() => {
        setSongs(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase()));

        setSearchSongsResults(filteredResults.reverse());
    }, [songs, searchSong])

    return (
        <SongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongsResults, fetchError, isLoading,
        }}>
            {children}
        </SongDataContext.Provider>
    )
}

export default SongDataContext;