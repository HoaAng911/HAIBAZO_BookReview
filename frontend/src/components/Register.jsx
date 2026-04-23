import api from "../api";
import { useState } from "react"
import './Login.css'
import { Link, useNavigate } from "react-router-dom";

const Register = () => {
  const [data, setData] = useState({ username: '', password: '', email: '', fullName: '' })
  const navigate = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/auth/register", data)
      alert("Đăng ký thành công! Hãy đăng nhập.")
      navigate('/')
    } catch (err) {
      alert("Lỗi khi đăng ký. Vui lòng kiểm tra lại thông tin.")
    }
  }
  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2>Đăng Ký</h2>
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
          <input 
            type="email" 
            placeholder="Email" 
            value={data.email}
            onChange={(e) => setData({ ...data, email: e.target.value })} 
          />
          <input 
            type="text" 
            placeholder="Full Name" 
            value={data.fullName}
            onChange={(e) => setData({ ...data, fullName: e.target.value })} 
          />
          <button type="submit">Đăng ký</button>
        </form>
        <div className="auth-footer">
          Đã có tài khoản? <Link to="/">Đăng nhập</Link>
        </div>
      </div>
    </div>
  )
}
export default Register