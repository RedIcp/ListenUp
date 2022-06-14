import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';

const PlaylistDataContext = createContext({});

export const PlaylistDataProvider = ({ children }) => {
    const [playlists, setPlaylists] = useState([])
    const [searchPlaylist, setSearchPlaylist] = useState('');
    const [searchPlaylistsResults, setSearchPlaylistsResults] = useState([]);

    const { data, fetchError, isLoading } = useAxiosFetch('http://localhost:8080/playlists');

    useEffect(() => {
        setPlaylists(data);
    }, [data])

    useEffect(() => {
        const filteredResults = playlists.filter((genre) =>
            ((genre.name).toLowerCase()).includes(searchPlaylist.toLowerCase()));

        setSearchPlaylistsResults(filteredResults.reverse());
    }, [playlists, searchPlaylist])

    return (
        <PlaylistDataContext.Provider value={{
            searchPlaylist, setSearchPlaylist,
            searchPlaylistsResults, fetchError, isLoading,
        }}>
            {children}
        </PlaylistDataContext.Provider>
    )
}

export default PlaylistDataContext;