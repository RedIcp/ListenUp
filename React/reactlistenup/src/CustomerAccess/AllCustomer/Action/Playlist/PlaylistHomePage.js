import {PlaylistDataProvider} from "../../../../Context/PlaylistDataContext";
import PlaylistHome from "./PlaylistHome";

function PlaylistHomePage() {
    return (
        <PlaylistDataProvider>
            <PlaylistHome/>
        </PlaylistDataProvider>
    )
}

export default PlaylistHomePage;