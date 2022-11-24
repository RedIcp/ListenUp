import CustomerLikedPlaylist from "./CustomerLikedPlaylist";
import {CustomerLikedPlaylistDataProvider} from "../../../../Context/CustomerLikedPlaylistContext";

function CustomerLikedPlaylistPage() {
    return (
        <CustomerLikedPlaylistDataProvider>
            <CustomerLikedPlaylist/>
        </CustomerLikedPlaylistDataProvider>
    )
}

export default CustomerLikedPlaylistPage;