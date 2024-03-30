const NavBar = (props) => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light p-3 justify-content-between">
            <a className="navbar-brand h1" href="#">
                Todo App
            </a>

            {props.loggedin && (
                <>
                    <div>
                        <button
                            className="btn btn btn-danger my-2 my-lg-0"
                            style={{ margin: "10px" }}
                            onClick={props.logout}
                        >
                            Logout
                        </button>
                        <button
                            className="btn btn-primary my-2 my-lg-0"
                            style={{ margin: "10px" }}
                        >
                            {props.email}
                        </button>
                    </div>
                </>
            )}
        </nav>
    );
};

export default NavBar;
