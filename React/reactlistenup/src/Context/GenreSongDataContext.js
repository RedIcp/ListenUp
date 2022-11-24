import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import {useParams} from "react-router-dom";

const GenreSongDataContext = createContext({});

export const GenreSongDataProvider = ({ children }) => {
    const [genre, setGenre] = useState({});
    const [songs, setSongs] = useState([]);
    const [searchSong, setSearchSong] = useState('');
    const [searchSongResults, setSearchSongResults] = useState([]);

    const { id } = useParams();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/genres/${id}`);

    useEffect(() => {
        setSongs(data.songs);
    }, [data])

    useEffect(() => {
        setGenre(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs!=null?songs.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase())):[];

        setSearchSongResults(filteredResults.reverse());
    }, [songs, searchSong])


    return (
        <GenreSongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongResults, fetchError, isLoading,
            genre
        }}>
            {children}
        </GenreSongDataContext.Provider>
    )
}

export default GenreSongDataContext;