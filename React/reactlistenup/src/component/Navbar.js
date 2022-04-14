import React from 'react';
import { Nav, NavLogo, NavLink, Bars, NavMenu, NavBtn, NavBtnLink,} from "./navElements";

function Navbar(){
    return (
        <>
            <Nav>
                <NavLogo to="/Home">
                    Listen Up
                </NavLogo>
                <Bars />

                <NavMenu>
                    <NavLink to="/home" activeStyle={{ color: 'black' }}>Home</NavLink>
                    <NavLink to="/" activeStyle={{ color: 'black' }}>Update Application</NavLink>
                    <NavLink to="/Creator/AddApp" activeStyle={{ color: 'black' }}>Add App</NavLink>
                    <NavLink to="/Creator/MyApps" activeStyle={{ color: 'black' }}>My Apps</NavLink>
                    <NavLink to="/Statistics" activeStyle={{ color: 'black' }}>Statistics</NavLink>
                    <NavLink to="/Download" activeStyle={{ color: 'black' }}>Downloads</NavLink>
                    <NavLink to="/contact" activeStyle={{ color: 'black' }}>Contact</NavLink>
                    <NavLink to="/about"activeStyle={{ color: 'black' }}> About</NavLink>
                    <NavLink to="/logout" activeStyle={{ color: 'black' }}>Logout</NavLink>

                    <NavBtn><NavBtnLink to="/Login">Log In</NavBtnLink></NavBtn>

                </NavMenu>
            </Nav>
        </>
    )
}

export default Navbar;