import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import {useParams} from "react-router-dom";

const PlaylistSongDataContext = createContext({});

export const PlaylistSongDataProvider = ({ children }) => {
    const [playlist, setPlaylist] = useState({});
    const [songs, setSongs] = useState([]);
    const [searchSong, setSearchSong] = useState('');
    const [searchSongResults, setSearchSongResults] = useState([]);

    const { id } = useParams();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/playlists/${id}`);

    useEffect(() => {
        setSongs(data?.songs);
    }, [data])

    useEffect(() => {
        setPlaylist(data);
    }, [data])

    useEffect(() => {
        const filteredResults = songs!=null?songs.filter((song) =>
            ((song.name).toLowerCase()).includes(searchSong.toLowerCase())):[];

        setSearchSongResults(filteredResults?.reverse());
    }, [songs, searchSong])


    return (
        <PlaylistSongDataContext.Provider value={{
            searchSong, setSearchSong,
            searchSongResults, fetchError, isLoading,
            playlist
        }}>
            {children}
        </PlaylistSongDataContext.Provider>
    )
}

export default PlaylistSongDataContext;