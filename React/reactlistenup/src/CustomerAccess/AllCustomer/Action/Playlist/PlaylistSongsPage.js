import {PlaylistSongDataProvider} from "../../../../Context/PlaylistSongDataContext";
import PlaylistSong from "./PlaylistSong";

function PlaylistSongsPage() {
    return (
        <PlaylistSongDataProvider>
            <PlaylistSong/>
        </PlaylistSongDataProvider>
    )
}

export default PlaylistSongsPage;