import React from "react";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faBook, faSearch, faHome, faAdd, faHeart, faHeadphones} from '@fortawesome/free-solid-svg-icons'
import {Link} from "react-router-dom";

function Sidebar() {
    return (
        <aside>
            <div className="logo">
                <FontAwesomeIcon icon={faHeadphones}/>
            </div>
            <div>
                <Link to="/home">
                    <li><FontAwesomeIcon icon={faHome}/> Home</li>
                </Link>
                <Link to="/creator/albums">
                    <li>Album</li>
                </Link>
                <Link to="/creator/artists">
                    <li>Artist</li>
                </Link>
                <Link to="/creator/genres">
                    <li>Genre</li>
                </Link>
                <Link to="/creator/songs">
                    <li>Songs</li>
                </Link>
                <hr/>
            </div>
        </aside>
    )
}

export default Sidebar;