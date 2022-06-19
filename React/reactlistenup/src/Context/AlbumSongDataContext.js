import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import {useParams} from "react-router-dom";

const AlbumSongDataContext = createContext({});

export const AlbumSongDataProvider = ({ children }) => {
    const [album, setAlbum] = useState({});
    const [songs, setSongs] = useState([]);
    const [searchSong, setSearchSong] = useState('');
    const [searchSongResults, setSearchSongResults] = useState([]);

    const { id } = useParams();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/albums/${id}`);

    useEffect(() => {
        setSongs(data.songs);
    }, [data])

    useEffect(() => {
        setAlbum(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs!=null?songs?.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase())):[];

        setSearchSongResults(filteredResults.reverse());
    }, [songs, searchSong])


    return (
        <AlbumSongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongResults, fetchError, isLoading,
            album
        }}>
            {children}
        </AlbumSongDataContext.Provider>
    )
}

export default AlbumSongDataContext;