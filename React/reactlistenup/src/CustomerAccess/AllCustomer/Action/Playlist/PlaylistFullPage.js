import {PlaylistDataProvider} from "../../../../Context/PlaylistDataContext";
import PlaylistFull from "./PlaylistFull";

function PlaylistFullPage() {
    return (
        <PlaylistDataProvider>
            <PlaylistFull/>
        </PlaylistDataProvider>
    )
}

export default PlaylistFullPage;