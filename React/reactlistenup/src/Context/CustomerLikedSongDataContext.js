import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import useAuth from "../Hooks/useAuth";

const CustomerLikedSongDataContext = createContext({});

export const CustomerLikedSongDataProvider = ({ children }) => {
    const [songs, setSongs] = useState([])
    const [searchSong, setSearchSong] = useState('');
    const [searchSongsResults, setSearchSongsResults] = useState([]);
    const {auth} = useAuth();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/users/${auth.id}/collection`);

    useEffect(() => {
        setSongs(data?.songs);
        console.log(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs?.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase()));

        setSearchSongsResults(filteredResults?.reverse());
    }, [songs, searchSong])

    return (
        <CustomerLikedSongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongsResults, fetchError, isLoading,
        }}>
            {children}
        </CustomerLikedSongDataContext.Provider>
    )
}

export default CustomerLikedSongDataContext;