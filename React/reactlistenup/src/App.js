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
                <Route path="login" element={<Login/>}/>
                <Route path="register" element={<Register/>}/>
                <Route path="unauthorized" element={<Unauthorized/>}/>

                <Route element={<RequireAuth allowedRoles={["ADMIN"]}/>}>

                </Route>

                <Route element={<RequireAuth allowedRoles={["CUSTOMER"]}/>}>

                </Route>

                <Route path="*" element={<Missing/>}/>
            </Route>
        </Routes>
    )
}

export default App;