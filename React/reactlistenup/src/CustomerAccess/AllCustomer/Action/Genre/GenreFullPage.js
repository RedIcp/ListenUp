import {GenreDataProvider} from "../../../../Context/GenreDataContext";
import GenreFull from "./GenreFull";

function GenreFullPage() {
    return (
        <GenreDataProvider>
            <GenreFull/>
        </GenreDataProvider>
    )
}

export default GenreFullPage;