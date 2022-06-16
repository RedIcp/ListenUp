import {ArtistDataProvider} from "../../../../Context/ArtistDataContext";
import ArtistList from "./ArtistList";

function ArtistPage() {
    return (
        <ArtistDataProvider>
            <ArtistList/>
        </ArtistDataProvider>
    )
}

export default ArtistPage;