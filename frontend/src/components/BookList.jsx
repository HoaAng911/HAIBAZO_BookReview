import React, { useEffect, useState } from "react";
import axios from 'axios'
import { Link, useNavigate } from "react-router-dom";
import '../App.css'

const BookList = () => {
  const [books, setBooks] = useState([])
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:8080/api/books")
      .then(res => setBooks(res.data))
      .catch(err => console.error("Lỗi khi tải danh sách", err))
  }, [])

  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  }

  return (
    <div className="page-wrapper">
      <header className="app-header">
        <Link to="/books" className="logo">BookReview</Link>
        <button className="logout-btn" onClick={handleLogout}>Đăng xuất</button>
      </header>

      <section className="hero-section">
        <h1>Thư viện của bạn</h1>
        <p>Khám phá và lưu giữ những cuốn sách yêu thích.</p>
      </section>
      
      <main className="book-list-container">
        <div className="book-grid">
          {books.length > 0 ? (
            books.map(book => (
              <div className="book-card" key={book.id}>
                <img src={book.coverImage || "https://images.unsplash.com/photo-1543005157-8656c7022322?q=80&w=380&h=500&auto=format&fit=crop"} alt={book.title} />
                <div className="book-card-content">
                  <h3>{book.title}</h3>
                  <p className="book-author">{book.author}</p>
                  <div className="book-rating">
                    <span>★ {book.avgRating || "0.0"}</span>
                  </div>
                  <Link to={`/books/${book.id}`} className="view-detail-btn">Chi tiết →</Link>
                </div>
              </div>
            ))
          ) : (
            <p className="loading-text">Đang tải danh sách...</p>
          )}
        </div>
      </main>

    </div>
  )
}

export default BookList