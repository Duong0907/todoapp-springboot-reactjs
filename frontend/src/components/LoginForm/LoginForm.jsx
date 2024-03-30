import { useEffect, useState } from "react";
import classNames from "classnames";

const LoginForm = (props) => {
    const [state, setState] = useState({
        active: "login",
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        onLogin: props.onLogin,
        onRegister: props.onRegister
    });

    const onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        let tempState = state;
        tempState[name] = value;

        setState(tempState);
    };

    const onSubmitLogin = (event) => {
        state.onLogin(event, state.email, state.password);
    };

    const onSubmitRegister = (event) => {
        state.onRegister(
            event,
            state.firstname,
            state.lastname,
            state.email,
            state.password
        );
    };

    return (
        <div className="row justify-content-center">
            <div className="col-4">
                <ul
                    className="nav nav-pills nav-justified mb-3"
                    id="ex-1"
                    role="tablist"
                >
                    <li className="nav-item" role="presentation">
                        <button
                            className={classNames(
                                "nav-link",
                                state.active === "login" ? "active" : ""
                            )}
                            id="tab-login"
                            onClick={() => {
                                setState({ active: "login" });
                            }}
                        >
                            Login
                        </button>
                    </li>

                    <li className="nav-item" role="presentation">
                        <button
                            className={classNames(
                                "nav-link",
                                state.active === "register" ? "active" : ""
                            )}
                            id="tab-register"
                            onClick={() => {
                                setState({ active: "register" });
                            }}
                        >
                            Register
                        </button>
                    </li>
                </ul>

                <div className="tab-content">
                    <div
                        className={classNames(
                            "tab-pane",
                            "fade",
                            state.active === "login" ? "show active" : ""
                        )}
                    >
                        <form action="" onSubmit={onSubmitLogin}>
                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="loginEmail"
                                >
                                    Email
                                </label>
                                <input
                                    type="email"
                                    id="loginEmail"
                                    name="email"
                                    className="form-control"
                                    onChange={onChangeHandler}
                                />
                            </div>

                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="loginPassword"
                                >
                                    Password
                                </label>
                                <input
                                    type="password"
                                    id="loginPassword"
                                    name="password"
                                    className="form-control"
                                    onChange={onChangeHandler}
                                />
                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary btn-block mb-4"
                            >
                                Signin
                            </button>
                        </form>
                    </div>

                    <div
                        className={classNames(
                            "tab-pane",
                            "fade",
                            state.active === "register" ? "show active" : ""
                        )}
                    >
                        <form action="">
                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="firstname"
                                >
                                    Firstname
                                    <input
                                        type="text"
                                        id="firstname"
                                        name="firstname"
                                        className="form-control"
                                    />
                                </label>
                            </div>

                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="lastname"
                                >
                                    Lastname
                                </label>
                                <input
                                    type="text"
                                    id="lastname"
                                    name="lastname"
                                    className="form-control"
                                />
                            </div>

                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="registerName"
                                >
                                    Username
                                </label>
                                <input
                                    type="text"
                                    id="registerName"
                                    name="registerName"
                                    className="form-control"
                                />
                            </div>

                            <div className="form-outline mb-4">
                                <label
                                    className="form-label"
                                    htmlFor="registerPassword"
                                >
                                    Password
                                </label>
                                <input
                                    type="password"
                                    id="registerPassword"
                                    name="registerPassword"
                                    className="form-control"
                                />
                            </div>

                            <button
                                type="submit"
                                className="btn btn-primary btn-block mb-4"
                            >
                                Signin
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginForm;
