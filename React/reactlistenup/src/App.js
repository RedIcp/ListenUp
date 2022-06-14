import Song from "./AdminAccess/Song/Song";
import Sidebar from "./Component/Sidebar";
import {SongDataProvider} from "./Context/SongDataContext";
import {AlbumDataProvider} from "./Context/AlbumDataContext";
import {ArtistDataProvider} from "./Context/ArtistDataContext";
import {GenreDataProvider} from "./Context/GenreDataContext";
import Login from "./ThirdParty/Login";
import Register from "./ThirdParty/Register";
import {Route, Routes} from "react-router-dom";
import Layout from "./Component/Layout";
import Unauthorized from "./ThirdParty/Unauthorized";
import RequireAuth from "./Component/RequireAuth";
import Missing from "./ThirdParty/Missing";
import Home from "./ThirdParty/Home";
import SongPage from "./AdminAccess/Song/SongPage";
import GenrePage from "./AdminAccess/Genre/GenrePage";
import ArtistPage from "./AdminAccess/Artist/ArtistPage";
import AlbumPage from "./AdminAccess/Album/AlbumPage";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Layout/>}>
                {/* public routes */}
                <Route path="login" element={<Login/>}/>
                <Route path="register" element={<Register/>}/>
                <Route path="unauthorized" element={<Unauthorized/>}/>

                <Route path="creator/song" element={<SongPage/>}/>
                <Route path="creator/genre" element={<GenrePage/>}/>
                <Route path="creator/artist" element={<ArtistPage/>}/>
                <Route path="creator/album" element={<AlbumPage/>}/>


                {/* we want to protect these routes */}
                <Route element={<RequireAuth allowedRoles={["CUSTOMER"]}/>}>
                    <Route path="/" element={<Home/>}/>
                </Route>


                {/* catch all */}
                <Route path="*" element={<Missing/>}/>
            </Route>
        </Routes>
    )
}

export default App;