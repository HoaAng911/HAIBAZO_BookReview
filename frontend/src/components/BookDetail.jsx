import React, { useEffect, useState } from "react";
import api from "../api";
import { useParams, Link } from "react-router-dom";
import '../App.css'

const BookDetail = () => {
  const { id } = useParams()
  const [book, setBook] = useState(null)
  const [reviews, setReviews] = useState([])
  const [newReview, setNewReview] = useState({ rating: 5, comment: '' })
  
  // Lấy thông tin user từ localStorage
  const user = JSON.parse(localStorage.getItem('user'))

  useEffect(() => {
    // Fetch thông tin sách
    api.get(`/books/${id}`)
      .then(res => setBook(res.data))
      .catch(err => console.error("Lỗi khi tải chi tiết sách", err))

    // Fetch danh sách review
    fetchReviews()
  }, [id])

  const fetchReviews = () => {
    api.get(`/reviews/book/${id}`)
      .then(res => setReviews(res.data))
      .catch(err => console.error("Lỗi khi tải review", err))
  }

  const handleSubmitReview = async (e) => {
    e.preventDefault()
    if (!user) {
      alert("Bạn cần đăng nhập để đánh giá!")
      return
    }

    try {
      await api.post("/reviews", {
        userId: user.id,
        bookId: id,
        rating: newReview.rating,
        comment: newReview.comment
      })
      alert("Đã gửi đánh giá của bạn!")
      setNewReview({ rating: 5, comment: '' })
      fetchReviews() // Tải lại danh sách review
    } catch (err) {
      alert("Lỗi khi gửi đánh giá.")
    }
  }

  if (!book) return (
    <div className="loading-container">
      <p>Đang tải dữ liệu...</p>
    </div>
  )

  return (
    <div className="book-detail-container">
      <Link to="/books" className="back-link">← Quay lại danh sách</Link>
      
      <div className="book-info-header">
        <img src={book.coverImage || "https://images.unsplash.com/photo-1543005157-8656c7022322?q=80&w=380&h=500&auto=format&fit=crop"} alt={book.title} />
        <div className="book-info-body">
          <h1>{book.title}</h1>
          <p className="book-author">Tác giả: <strong>{book.author}</strong></p>
          <p className="book-rating">Đánh giá chung: ★ {book.avgRating || "0.0"}</p>
          <div className="book-description">
            <p>{book.description || "Không có mô tả cho cuốn sách này."}</p>
          </div>
        </div>
      </div>

      <section className="review-section">
        <h2>Đánh giá từ độc giả ({reviews.length})</h2>

        {user ? (
          <form className="review-form" onSubmit={handleSubmitReview}>
            <h3>Viết đánh giá của bạn</h3>
            <select 
              value={newReview.rating} 
              onChange={(e) => setNewReview({...newReview, rating: parseInt(e.target.value)})}
            >
              <option value="5">5 Sao - Rất hay</option>
              <option value="4">4 Sao - Tốt</option>
              <option value="3">3 Sao - Bình thường</option>
              <option value="2">2 Sao - Không hay</option>
              <option value="1">1 Sao - Rất tệ</option>
            </select>
            <textarea 
              placeholder="Cảm nhận của bạn về cuốn sách..." 
              value={newReview.comment}
              onChange={(e) => setNewReview({...newReview, comment: e.target.value})}
              required
            />
            <button type="submit">Gửi đánh giá</button>
          </form>
        ) : (
          <div className="review-form">
            <p>Hãy <Link to="/">Đăng nhập</Link> để viết đánh giá.</p>
          </div>
        )}

        <div className="review-list">
          {reviews.length > 0 ? (
            reviews.map(review => (
              <div className="review-card" key={review.id}>
                <div className="review-card-header">
                  <span className="review-user">{review.user?.fullName || "Người dùng"}</span>
                  <span className="review-rating">{"★".repeat(review.rating)}{"☆".repeat(5-review.rating)}</span>
                </div>
                <p className="review-comment">{review.comment}</p>
              </div>
            ))
          ) : (
            <p className="loading-text">Chưa có đánh giá nào. Hãy là người đầu tiên!</p>
          )}
        </div>
      </section>
    </div>
  )
}

export default BookDetail