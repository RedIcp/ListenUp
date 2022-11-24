import {AlbumDataProvider} from "../../../../Context/AlbumDataContext";
import AlbumHome from "./AlbumHome";

function AlbumHomePage() {
    return (
        <AlbumDataProvider>
            <AlbumHome/>
        </AlbumDataProvider>
    )
}

export default AlbumHomePage;