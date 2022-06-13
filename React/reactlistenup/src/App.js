import NewSong from "./AdminAccess/Song/NewSong";
import Sidebar from "./Component/Sidebar";
import {SongDataProvider} from "./Context/SongDataContext";
import {AlbumDataProvider} from "./Context/AlbumDataContext";
import {ArtistDataProvider} from "./Context/ArtistDataContext";
import {GenreDataProvider} from "./Context/GenreDataContext";
import "./Style/App.css"

function App() {
    return (
        <div>
            <SongDataProvider>
                <AlbumDataProvider>
                    <ArtistDataProvider>
                        <GenreDataProvider>
                            <AlbumDataProvider>
                                <NewSong/>
                            </AlbumDataProvider>
                        </GenreDataProvider>
                    </ArtistDataProvider>
                </AlbumDataProvider>
            </SongDataProvider>
        </div>
    )
}

export default App;