import { useState, useEffect } from 'react';
import Header from "./components/Header";
import Tasks from "./components/Tasks";
import AddTask from './components/AddTask';

function App() {
  const [showAddTask, setShowAddTask] = useState(true)
  const [tasks, setTasks] = useState([])

  useEffect(() => {
    const getTasks = async () => {
      const tasksFromServer = await fetchTasks()
      setTasks(tasksFromServer)
    }

    getTasks()
  }, [])

  // fetch tasks
  const fetchTasks = async () => {
    const res = await fetch('http://localhost:8080')
    const data = await res.json()

    return data
  }

  // fetch task
  const fetchTask = async (id) => {
    const res = await fetch(`http://localhost:8080/${id}`)
    const data = await res.json()

    return data
  }

  // add task
  const addTask = async (task) => {
    const res = await fetch('http://localhost:8080/add', {
      method: 'POST',
      headers: {
        'Content-type': 'application/json'
      },
      body: JSON.stringify(task),
    })

    const data = await res.json()

    setTasks([...tasks, data])

    // const id = Math.floor(Math.random() * 10000) + 1
    // const newTask = { id, ...task}
    // setTasks([...tasks, newTask])
  }

  // delete task
  const deleteTask = async (id) => {
    await fetch(`http://localhost:8080/delete/${id}`, {
      mode: 'no-cors'
      // method: 'DELETE'
    })

    setTasks(tasks.filter((task) => task.id !== id))
  }

  // toggle reminder
  const toggleReminder = async (id) => {
    const taskToToggle = await fetchTask(id)
    const updTask = { ...taskToToggle,
    reminder: !taskToToggle.reminder }

    const res = await fetch(`http://localhost:8080/${id}`, {
      // mode: 'no-cors',
      // mode: 'cors',
      // credentials: 'include',
      method: 'PUT',
      headers: {
        'Content-type': 'application/json'
        // 'Accept': 'application/json',
        // 'Origin': 'http://localhost:3000'
      },
      body: JSON.stringify(updTask)
    })

    const data = await res.json()

    setTasks(
      tasks.map((task) =>
        task.id === id ? { ...task, reminder:
        data.reminder } : task
      )
    )
  }

  return (
    <div className="container">
      <Header 
        onAdd={() => setShowAddTask(!showAddTask)}
        showAdd={showAddTask}
      />
      {showAddTask && <AddTask onAdd={addTask} />}
      {tasks.length > 0 ? (
        <Tasks tasks={tasks} onDelete={deleteTask} onToggle={toggleReminder} />
      ) : (
        'Go add some tasks!'
      )}
    </div>
  );
}

export default App;
