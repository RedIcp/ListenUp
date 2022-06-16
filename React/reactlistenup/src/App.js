import Login from "./ThirdParty/Login";
import Register from "./ThirdParty/Register";
import {Route, Routes} from "react-router-dom";
import Layout from "./Component/Layout";
import Unauthorized from "./ThirdParty/Unauthorized";
import RequireAuth from "./Component/RequireAuth";
import Missing from "./ThirdParty/Missing";
import SongPage from "./AdminAccess/Action/Song/SongPage";
import GenrePage from "./AdminAccess/Action/Genre/GenrePage";
import ArtistPage from "./AdminAccess/Action/Artist/ArtistPage";
import AlbumPage from "./AdminAccess/Action/Album/AlbumPage";
import AlbumSongsPage from "./CustomerAccess/AllCustomer/Action/Album/AlbumSongsPage";
import ArtistSongsPage from "./CustomerAccess/AllCustomer/Action/Artist/ArtistSongsPage";
import GenreSongsPage from "./CustomerAccess/AllCustomer/Action/Genre/GenreSongsPage";
import PlaylistSongsPage from "./CustomerAccess/AllCustomer/Action/Playlist/PlaylistSongsPage";
import Main from "./CustomerAccess/Component/Main";
import "./Style/app.css"

function App() {
    return (
        <Routes>
            <Route path="/" element={<Layout/>}>

                {/*public routes */}
                <Route path="login" element={<Login/>}/>
                <Route path="register" element={<Register/>}/>
                <Route path="unauthorized" element={<Unauthorized/>}/>

                {/* we want to protect these routes */}
                <Route element={<RequireAuth allowedRoles={["ADMIN"]}/>}>
                    <Route path="creator/song" element={<SongPage/>}/>
                    <Route path="creator/genre" element={<GenrePage/>}/>
                    <Route path="creator/artist" element={<ArtistPage/>}/>
                    <Route path="creator/album" element={<AlbumPage/>}/>
                </Route>

                <Route path="albums/:id" element={<AlbumSongsPage/>}/>
                <Route path="artists/:id" element={<ArtistSongsPage/>}/>
                <Route path="genres/:id" element={<GenreSongsPage/>}/>
                <Route path="playlists/:id" element={<PlaylistSongsPage/>}/>

                <Route path="/" element={<Main/>}/>

                <Route element={<RequireAuth allowedRoles={["CUSTOMER"]}/>}>


                </Route>

                <Route path="*" element={<Missing/>}/>
            </Route>
        </Routes>
    )
}

export default App;