import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBook, faSearch, faHome, faAdd, faHeart, faHeadphones} from '@fortawesome/free-solid-svg-icons'
import {Link} from "react-router-dom";

function Sidebar(){
    return (
        <aside>
            <div className="logo">
                <FontAwesomeIcon icon={faHeadphones}/>
            </div>
            <div>
                <Link to="/home"><li><FontAwesomeIcon icon={faHome}/> Home</li></Link>
                <li><FontAwesomeIcon icon={faSearch}/> Search</li>
                <li><FontAwesomeIcon icon={faBook}/> Your Library</li>
                <br/>
                <li><FontAwesomeIcon icon={faAdd}/> Create Playlist</li>
                <li><FontAwesomeIcon icon={faHeart}/> Liked Songs</li>
                <hr/>
            </div>
        </aside>
    )
}

export default Sidebar;