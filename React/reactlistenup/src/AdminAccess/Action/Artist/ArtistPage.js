import Artist from "./Artist";
import {ArtistDataProvider} from "../../../Context/ArtistDataContext";

function ArtistPage() {
    return (
        <ArtistDataProvider>
            <Artist/>
        </ArtistDataProvider>
    )
}

export default ArtistPage;