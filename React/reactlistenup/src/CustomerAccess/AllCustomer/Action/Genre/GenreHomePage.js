import {GenreDataProvider} from "../../../../Context/GenreDataContext";
import GenreHome from "./GenreHome";

function GenreHomePage() {
    return (
        <GenreDataProvider>
            <GenreHome/>
        </GenreDataProvider>
    )
}

export default GenreHomePage;