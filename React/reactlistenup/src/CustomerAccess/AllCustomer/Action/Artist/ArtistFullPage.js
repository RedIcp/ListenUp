import {ArtistDataProvider} from "../../../../Context/ArtistDataContext";
import ArtistFull from "./ArtistFull";

function ArtistFullPage() {
    return (
        <ArtistDataProvider>
            <ArtistFull/>
        </ArtistDataProvider>
    )
}

export default ArtistFullPage;