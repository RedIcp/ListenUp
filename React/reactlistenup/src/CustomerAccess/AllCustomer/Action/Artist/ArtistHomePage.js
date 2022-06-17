import {ArtistDataProvider} from "../../../../Context/ArtistDataContext";
import ArtistHome from "./ArtistHome";

function ArtistHomePage() {
    return (
        <ArtistDataProvider>
            <ArtistHome/>
        </ArtistDataProvider>
    )
}

export default ArtistHomePage;