import {SongDataProvider} from "../../Context/SongDataContext";
import SearchSong from "./SearchSong";

function SearchSongPage() {
    return (
        <SongDataProvider>
            <SearchSong/>
        </SongDataProvider>
    )
}

export default SearchSongPage;