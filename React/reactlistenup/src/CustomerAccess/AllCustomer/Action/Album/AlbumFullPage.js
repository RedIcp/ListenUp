import {AlbumDataProvider} from "../../../../Context/AlbumDataContext";
import AlbumFull from "./AlbumFull";

function AlbumFullPage() {
    return (
        <AlbumDataProvider>
            <AlbumFull/>
        </AlbumDataProvider>
    )
}

export default AlbumFullPage;