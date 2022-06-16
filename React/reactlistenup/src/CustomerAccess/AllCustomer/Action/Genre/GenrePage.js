import {GenreDataProvider} from "../../../../Context/GenreDataContext";
import GenreList from "./GenreList";

function GenrePage() {
    return (
        <GenreDataProvider>
            <GenreList/>
        </GenreDataProvider>
    )
}

export default GenrePage;