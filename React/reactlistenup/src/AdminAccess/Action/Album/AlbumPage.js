import Album from "./Album";
import {ArtistDataProvider} from "../../../Context/ArtistDataContext";
import {AlbumDataProvider} from "../../../Context/AlbumDataContext";

function AlbumPage() {
    return (
        <AlbumDataProvider>
            <ArtistDataProvider>
                <Album/>
            </ArtistDataProvider>
        </AlbumDataProvider>
    )
}

export default AlbumPage;