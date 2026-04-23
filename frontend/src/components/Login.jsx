import api from "../api";
import { useState } from "react"
import './Login.css'
import { Link, useNavigate } from "react-router-dom";

const Login = () => {
  const [data, setData] = useState({ username: '', password: '' })
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/auth/login", data)
      localStorage.setItem('user', JSON.stringify(res.data))
      alert("Đăng nhập thành công")
      navigate('/books')
    } catch (err) {
      alert("Sai thông tin đăng nhập")
    }
  }
  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>Đăng Nhập</h2>
        <form onSubmit={submit}>
          <input 
            placeholder="Username" 
            value={data.username}
            onChange={(e) => setData({ ...data, username: e.target.value })} 
          />
          <input 
            type="password" 
            placeholder="Password" 
            value={data.password}
            onChange={(e) => setData({ ...data, password: e.target.value })} 
          />
          <button type="submit">Đăng nhập</button>
        </form>
        <div className="auth-footer">
          Chưa có tài khoản? <Link to="/register">Đăng ký ngay</Link>
        </div>
      </div>
    </div>
  )
}
export default Login