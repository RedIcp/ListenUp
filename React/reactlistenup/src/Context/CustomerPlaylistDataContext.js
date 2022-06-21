import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import useAuth from "../Hooks/useAuth";

const CustomerPlaylistDataContext = createContext({});

export const CustomerPlaylistDataProvider = ({ children }) => {
    const [playlists, setPlaylists] = useState([])
    const [searchPlaylist, setSearchPlaylist] = useState('');
    const [searchPlaylistsResults, setSearchPlaylistsResults] = useState([]);
    const {auth} = useAuth();

    const { setUpdate, data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/users/${auth.id}/playlists`);

    useEffect(() => {
        setPlaylists(data?.playlists);
    }, [data])

    useEffect(() => {
        const filteredResults = playlists?.filter((playlist) =>
            ((playlist.name).toLowerCase()).includes(searchPlaylist.toLowerCase()));

        setSearchPlaylistsResults(filteredResults?.reverse());
    }, [playlists, searchPlaylist])

    return (
        <CustomerPlaylistDataContext.Provider value={{
            searchPlaylist, setSearchPlaylist,
            searchPlaylistsResults, fetchError, isLoading, setUpdate
        }}>
            {children}
        </CustomerPlaylistDataContext.Provider>
    )
}

export default CustomerPlaylistDataContext;