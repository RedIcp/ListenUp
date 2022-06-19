import {CustomerLikedSongDataProvider} from "../../../../Context/CustomerLikedSongDataContext";
import CustomerLikedSong from "./CustomerLikedSong";

function CustomerLikedSongPage() {
    return (
        <CustomerLikedSongDataProvider>
            <CustomerLikedSong/>
        </CustomerLikedSongDataProvider>
    )
}

export default CustomerLikedSongPage;