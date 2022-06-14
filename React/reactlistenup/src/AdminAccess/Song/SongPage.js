import {SongDataProvider} from "../../Context/SongDataContext";
import {ArtistDataProvider} from "../../Context/ArtistDataContext";
import {GenreDataProvider} from "../../Context/GenreDataContext";
import Song from "./Song";
import {AlbumDataProvider} from "../../Context/AlbumDataContext";

function SongPage() {
    return (
        <AlbumDataProvider>
            <SongDataProvider>
                <ArtistDataProvider>
                    <GenreDataProvider>
                        <Song/>
                    </GenreDataProvider>
                </ArtistDataProvider>
            </SongDataProvider>
        </AlbumDataProvider>
    )
}

export default SongPage;