import {GenreSongDataProvider} from "../../../../Context/GenreSongDataContext";
import GenreSongs from "./GenreSongs";

function GenreSongsPage() {
    return (
        <GenreSongDataProvider>
            <GenreSongs/>
        </GenreSongDataProvider>
    )
}

export default GenreSongsPage;