import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import {useParams} from "react-router-dom";

const ArtistSongDataContext = createContext({});

export const ArtistSongDataProvider = ({ children }) => {
    const [artist, setArtist] = useState({});
    const [songs, setSongs] = useState([]);
    const [searchSong, setSearchSong] = useState('');
    const [searchSongResults, setSearchSongResults] = useState([]);

    const { id } = useParams();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/artists/${id}`);

    useEffect(() => {
        setSongs(data.songs);
    }, [data])

    useEffect(() => {
        setArtist(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs!=null?songs.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase())):[];

        setSearchSongResults(filteredResults.reverse());
    }, [songs, searchSong])


    return (
        <ArtistSongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongResults, fetchError, isLoading,
            artist
        }}>
            {children}
        </ArtistSongDataContext.Provider>
    )
}

export default ArtistSongDataContext;