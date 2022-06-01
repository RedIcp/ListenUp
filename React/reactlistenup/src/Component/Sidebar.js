import React from "react";
import '../Style/Sidebar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBook, faSearch, faHome, faAdd, faHeart, faHeadphones} from '@fortawesome/free-solid-svg-icons'

function Sidebar(){
    return (
        <div className={"sidenav"}>
            <div to="/Home" className={"logo"}>
                <FontAwesomeIcon icon={faHeadphones} />
            </div>

            <div className={"content"}>
                <li to="/home"><FontAwesomeIcon icon={faHome} /> Home</li>
                <li to="/search"><FontAwesomeIcon icon={faSearch} /> Search</li>
                <li to="/library"><FontAwesomeIcon icon={faBook} /> Your Library</li>
                <br/>
                <li to="/playlist"><FontAwesomeIcon icon={faAdd} /> Create Playlist</li>
                <li to="/collection"><FontAwesomeIcon icon={faHeart} /> Liked Songs</li>
                <hr/>
            </div>
        </div>
    )
}

export default Sidebar;