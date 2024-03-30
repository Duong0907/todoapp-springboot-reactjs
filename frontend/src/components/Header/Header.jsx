import * as React from "react";
import "./Header.css";

const Header = (props) => {
    return (
        <header className="app-header mb-5">
            <img src={props.logoSrc} className="app-logo" alt="logo" />
            <h1 className="app-title">{props.pageTitle}</h1>
        </header>
    );
};

export default Header;
