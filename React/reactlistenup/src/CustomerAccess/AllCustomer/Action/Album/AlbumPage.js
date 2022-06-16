import {AlbumDataProvider} from "../../../../Context/AlbumDataContext";
import AlbumList from "./AlbumList";

function AlbumPage() {
    return (
        <AlbumDataProvider>
            <AlbumList/>
        </AlbumDataProvider>
    )
}

export default AlbumPage;