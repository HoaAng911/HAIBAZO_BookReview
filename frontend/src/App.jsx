import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import BookList from './components/BookList'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import BookDetail from './components/BookDetail'
import Login from './components/Login'
import Register from './components/Register'
function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/books" element={<BookList />} />
        <Route path="/books/:id" element={<BookDetail />} />
      </Routes>
    </Router>
  )
}
export default App
