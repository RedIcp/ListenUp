import {GenreDataProvider} from "../../../Context/GenreDataContext";
import Genre from "./Genre";

function GenrePage() {
    return (
        <GenreDataProvider>
            <Genre/>
        </GenreDataProvider>
    )
}

export default GenrePage;