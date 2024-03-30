import { useEffect, useState } from "react";
import { request, setAccessToken, clearAccessToken } from "../../axios-helper";

import LoginForm from "../../components/LoginForm/LoginForm";
import TodoList from "../../components/TodoList/TodoList";
import logo from "../../assets/react.svg";
import Header from "../../components/Header/Header";
import NavBar from "../../components/NavBar/NavBar";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

const MainPage = () => {
    const [state, setState] = useState({
        componentToShow: "login",
        isLoggedin: false,
        isLoading: true,
        user: {},
    });

    // Check authentication before entering the web page
    useEffect(() => {
        request("GET", "/api/v1/users/me")
            .then((res) => {
                if (!res.data.error) {
                    setState({
                        componentToShow: "todolist",
                        isLoggedin: true,
                        isLoading: true,
                        user: res.data.data,
                    });
                } else {
                    setState({
                        componentToShow: "login",
                        isLoggedin: false,
                        isLoading: false,
                        user: {},
                    });
                }
            })
            .catch((err) => {
                setState({
                    componentToShow: "login",
                    isLoggedin: false,
                    isLoading: false,
                    user: {},
                });
            });
    }, []);

    if (!state.isLoggedin && state.isLoading) {
        return "Loading...";
    }

    // Functions to handling login
    const login = () => {
        setState({ componentToShow: "login" });
    };

    const logout = () => {
        clearAccessToken();
        login();
    };

    const onLogin = (event, email, password) => {
        event.preventDefault();
        request("POST", "/api/v1/auth/login", {
            email: email,
            password: password,
        }).then((res) => {
            if (!res.data.error) {
                setState({ componentToShow: "todolist" });
                setAccessToken(res.data.data);
            } else {
                // Need to show error message
                alert("Incorrect email or password");
            }
        });
    };

    return (
        <div>
            <NavBar logout={logout} loggedin={state.isLoggedin} email={state.user.email}></NavBar>
            {/* <Header pageTitle="Todo App" logoSrc={logo}></Header> */}
            <div className="container-fluid">
                <div className="row">
                    <div className="col">
                        {state.componentToShow === "todolist" && (
                            <TodoList userId={state.user.id}></TodoList>
                        )}
                        {state.componentToShow === "login" && (
                            <LoginForm onLogin={onLogin}></LoginForm>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default MainPage;
