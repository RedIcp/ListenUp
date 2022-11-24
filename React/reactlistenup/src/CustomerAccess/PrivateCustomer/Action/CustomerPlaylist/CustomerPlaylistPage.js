import {CustomerPlaylistDataProvider} from "../../../../Context/CustomerPlaylistDataContext";
import CustomerPlaylist from "./CustomerPlaylist";

function CustomerPlaylistPage() {
    return (
        <CustomerPlaylistDataProvider>
            <CustomerPlaylist/>
        </CustomerPlaylistDataProvider>
    )
}

export default CustomerPlaylistPage;