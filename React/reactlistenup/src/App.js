import NewSong from "./AdminAccess/Song/NewSong";
import Sidebar from "./Component/Sidebar";
import {SongDataProvider} from "./Context/SongDataContext";
import {AlbumDataProvider} from "./Context/AlbumDataContext";
import {ArtistDataProvider} from "./Context/ArtistDataContext";
import {GenreDataProvider} from "./Context/GenreDataContext";
import Login from "./ThirdParty/Login";

function App() {
    return (
        <div>
            <SongDataProvider>
                <AlbumDataProvider>
                    <ArtistDataProvider>
                        <GenreDataProvider>
                            <AlbumDataProvider>
                                <Login/>
                            </AlbumDataProvider>
                        </GenreDataProvider>
                    </ArtistDataProvider>
                </AlbumDataProvider>
            </SongDataProvider>
        </div>
    )
}

export default App;