import AlbumPage from "./AllCustomer/Action/Album/AlbumPage";
import GenrePage from "./AllCustomer/Action/Genre/GenrePage";
import ArtistPage from "./AllCustomer/Action/Artist/ArtistPage";

function CustomerHome() {
    return (
        <div className="home-list">
            <div >
                <h1>Albums</h1>
                <div className="view">View all</div>
                <AlbumPage/>

            </div>
            <div>
                <h1>Genres</h1>
                <div className="view">View all</div>
                <GenrePage/>
            </div>
            <div>
                <h1>Artists</h1>
                <div className="view">View all</div>
                <ArtistPage/>
            </div>
        </div>
    )
}

export default CustomerHome;