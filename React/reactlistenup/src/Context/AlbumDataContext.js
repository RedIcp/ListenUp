import { createContext, useState, useEffect } from 'react';
import useAxiosFetch from '../Hooks/useAxiosFetch.js';

const AlbumDataContext = createContext({});

export const AlbumDataProvider = ({ children }) => {
    const [albums, setAlbums] = useState([])
    const [searchAlbum, setSearchAlbum] = useState('');
    const [searchAlbumsResults, setSearchAlbumsResults] = useState([]);

    const { data, fetchError, isLoading } = useAxiosFetch('http://localhost:8080/albums');

    useEffect(() => {
        setAlbums(data);
    }, [data])

    useEffect(() => {
        const filteredResults = albums.filter((album) =>
            ((album.name).toLowerCase()).includes(searchAlbum.toLowerCase()));

        setSearchAlbumsResults(filteredResults.reverse());
    }, [albums, searchAlbum])

    return (
        <AlbumDataContext.Provider value={{
            searchAlbum, setSearchAlbum,
            searchAlbumsResults, fetchError, isLoading,
            albums, setAlbums
        }}>
            {children}
        </AlbumDataContext.Provider>
    )
}

export default AlbumDataContext;