import ArtistSongs from "./ArtistSongs";
import {ArtistSongDataProvider} from "../../../../Context/ArtistSongDataContext";

function ArtistSongsPage() {
    return (
        <ArtistSongDataProvider>
            <ArtistSongs/>
        </ArtistSongDataProvider>
    )
}

export default ArtistSongsPage;