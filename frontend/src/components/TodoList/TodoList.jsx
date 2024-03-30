import { useEffect, useState } from "react";
import { request } from "../../axios-helper";

const TodoList = (props) => {
    const [todos, setTodos] = useState([]);

    // Functions to update data in db
    const getAllTodos = () => {
        request("GET", "/api/v1/todos/user/" + props.userId)
            .then((res) => {
                if (!res.data.error) {
                    let newTodos = res.data.data.map((todo) => ({
                        id: todo.id,
                        task: todo.title,
                        completed: todo.status == "Done",
                    }));

                    setTodos(newTodos);
                    setState({ onLoading: false });
                } else {
                    alert(res.data.message);
                }
            })
            .catch((error) => {
                alert(error);
                setState({ onLoading: false });
            });
    };

    const createTodoInDb = (todo) => {
        return request("POST", "/api/v1/todos/", todo);
    };

    const changeTodoStatusInDb = (todo) => {
        let status = todo.status === "Done" ? "Todo" : "Done";

        return request("PUT", "/api/v1/todos/" + todo.id, {
            status: status,
        });
    };

    const deleteTodoInDb = (id) => {
        return request("DELETE", "/api/v1/todos/" + id);
    };

    // Get all todos when entering page
    const [state, setState] = useState({
        onLoading: true,
    });

    useEffect(() => {
        getAllTodos();
    }, []);

    if (state.onLoading) {
        return "Loading...";
    }

    // Event handers
    const handleSubmit = (event) => {
        event.preventDefault();

        let task = event.target.task.value;

        if (!task) {
            alert("Please provice a valid task");
            return;
        }

        createTodoInDb({
            title: task,
            status: "Todo",
        }).then((res) => {
            if (!res.data.error) {
                setTodos([
                    ...todos,
                    {
                        task: res.data.data.title,
                        completed: res.data.data.status === "Done",
                        id: res.data.data.id,
                    },
                ]);

                document.getElementById("task").value = "";
            } else {
                alert(res.data.message);
            }
        });
    };

    const changeTaskStatus = (id) => {
        let newTodos = [...todos];
        for (let i = 0; i < newTodos.length; i++) {
            if (newTodos[i].id === id) {
                changeTodoStatusInDb({
                    status: newTodos[i].completed ? "Done" : "Todo",
                    id: id,
                }).then((res) => {
                    console.log(res);
                    if (!res.data.error) {
                        newTodos[i].completed = !newTodos[i].completed;
                        setTodos(newTodos);
                    }
                });
                return;
            }
        }
    };

    const deleteTask = (id) => {
        let newTodos = [...todos];
        for (let i = 0; i < todos.length; i++) {
            if (todos[i].id === id) {
                deleteTodoInDb(todos[i].id).then((res) => {
                    if (!res.data.error) {
                        newTodos.splice(i, 1);
                        setTodos(newTodos);
                    }
                });
                return;
            }
        }
    };

    return (
        <div className="container" id="container">
            <div
                className="mx-auto rounded border p-4"
                style={{ width: "600px", backgroundColor: "#06818d" }}
            >
                <form action="" className="d-flex" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        className="form-control me-2"
                        placeholder="New Task"
                        id="task"
                    />
                    <button type="submit" className="btn btn-outline-light">
                        Add
                    </button>
                </form>
                <div data-spy="scroll" data-offset="0" data-target="#container">

                    {todos.map((todo, index) => {
                        return (
                            <div
                                key={index}
                                className="rounded mt-4 p-2 d-flex"
                                style={{
                                    backgroundColor: todo.completed
                                        ? "#87FC68"
                                        : "LightGray",
                                }}
                            >
                                <div className="me-auto">{todo.task}</div>
                                <div>
                                    <i
                                        className={
                                            "h5 me-2 " +
                                            (todo.completed
                                                ? "bi bi-check-square"
                                                : "bi bi-square")
                                        }
                                        style={{ cursor: "pointer" }}
                                        onClick={() => changeTaskStatus(todo.id)}
                                    ></i>
                                    <i
                                        className="bi bi-trash text-danger h5"
                                        style={{ cursor: "pointer" }}
                                        onClick={() => deleteTask(todo.id)}
                                    ></i>
                                </div>
                            </div>
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default TodoList;
