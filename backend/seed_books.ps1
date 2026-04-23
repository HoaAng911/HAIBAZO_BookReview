
$books = @(
    @{
        title = "Đắc Nhân Tâm"
        author = "Dale Carnegie"
        description = "Cuốn sách hay nhất mọi thời đại về nghệ thuật giao tiếp và thu phục lòng người."
        coverImage = "https://example.com/dac-nhan-tam.jpg"
        category = "Kỹ năng sống"
    },
    @{
        title = "Nhà Giả Kim"
        author = "Paulo Coelho"
        description = "Hành trình tìm kiếm vận mệnh của chàng chăn cừu Santiago."
        coverImage = "https://example.com/nha-gia-kim.jpg"
        category = "Tiểu thuyết"
    },
    @{
        title = "Tôi Thấy Hoa Vàng Trên Cỏ Xanh"
        author = "Nguyễn Nhật Ánh"
        description = "Câu chuyện về tuổi thơ hồn nhiên và đầy cảm xúc ở một làng quê nghèo."
        coverImage = "https://example.com/hoa-vang-co-xanh.jpg"
        category = "Văn học Việt Nam"
    },
    @{
        title = "Mắt Biếc"
        author = "Nguyễn Nhật Ánh"
        description = "Một câu chuyện tình buồn và đẹp về một chàng trai dành cả thanh xuân để yêu một người."
        coverImage = "https://example.com/mat-biec.jpg"
        category = "Văn học Việt Nam"
    },
    @{
        title = "Số Đỏ"
        author = "Vũ Trọng Phụng"
        description = "Tác phẩm trào phúng đỉnh cao của văn học hiện thực Việt Nam."
        coverImage = "https://example.com/so-do.jpg"
        category = "Văn học Việt Nam"
    },
    @{
        title = "Tắt Đèn"
        author = "Ngô Tất Tố"
        description = "Bức tranh chân thực về cuộc sống khốn cùng của người nông dân dưới chế độ cũ."
        coverImage = "https://example.com/tat-den.jpg"
        category = "Văn học Việt Nam"
    },
    @{
        title = "Chí Phèo"
        author = "Nam Cao"
        description = "Bi kịch của một người nông dân bị tha hóa và khát vọng làm người lương thiện."
        coverImage = "https://example.com/chi-pheo.jpg"
        category = "Văn học Việt Nam"
    },
    @{
        title = "Dế Mèn Phiêu Lưu Ký"
        author = "Tô Hoài"
        description = "Cuộc phiêu lưu đầy thú vị và ý nghĩa của chú Dế Mèn dũng cảm."
        coverImage = "https://example.com/de-men.jpg"
        category = "Văn học thiếu nhi"
    },
    @{
        title = "Sông Đông Êm Đềm"
        author = "Mikhail Sholokhov"
        description = "Bản anh hùng ca về cuộc sống và số phận của người Cossack vùng sông Đông."
        coverImage = "https://example.com/song-dong.jpg"
        category = "Văn học kinh điển"
    },
    @{
        title = "Chiến Tranh Và Hòa Bình"
        author = "Leo Tolstoy"
        description = "Một trong những tác phẩm vĩ đại nhất của văn học Nga và thế giới."
        coverImage = "https://example.com/chien-tranh-hoa-binh.jpg"
        category = "Văn học kinh điển"
    }
)

foreach ($book in $books) {
    $json = $book | ConvertTo-Json -Compress
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/books" -Method Post -Body ([System.Text.Encoding]::UTF8.GetBytes($json)) -ContentType "application/json; charset=utf-8"
    Write-Host "Added: $($response.title)"
}
