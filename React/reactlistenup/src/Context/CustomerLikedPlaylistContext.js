import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';
import useAuth from "../Hooks/useAuth";

const CustomerLikedPlaylistDataContext = createContext({});

export const CustomerLikedPlaylistDataProvider = ({ children }) => {
    const [playlists, setPlaylists] = useState([]);
    const [searchPlaylist, setSearchPlaylist] = useState('');
    const [searchPlaylistsResults, setSearchPlaylistsResults] = useState([]);
    const [update, setUpdate] = useState();

    const {auth} = useAuth();

    const { data, fetchError, isLoading } = useAxiosFetch(`http://localhost:8080/users/${auth.id}/likedplaylists`);

    useEffect(() => {
        setPlaylists(data?.playlists);
    }, [data, update])

    useEffect(() => {
        const filteredResults = playlists?.filter((playlist) =>
            ((playlist.name).toLowerCase()).includes(searchPlaylist.toLowerCase()));

        setSearchPlaylistsResults(filteredResults?.reverse());
    }, [playlists, searchPlaylist])

    return (
        <CustomerLikedPlaylistDataContext.Provider value={{
            searchPlaylist, setSearchPlaylist,
            searchPlaylistsResults, fetchError, isLoading, setUpdate
        }}>
            {children}
        </CustomerLikedPlaylistDataContext.Provider>
    )
}

export default CustomerLikedPlaylistDataContext;