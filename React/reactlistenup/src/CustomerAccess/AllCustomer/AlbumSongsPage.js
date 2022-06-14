import {AlbumSongDataProvider} from "../../Context/AlbumSongDataContext";
import AlbumSongs from "./AlbumSongs";

function AlbumSongsPage() {
    return (
        <AlbumSongDataProvider>
            <AlbumSongs/>
        </AlbumSongDataProvider>
    )
}

export default AlbumSongsPage;