# BÁO CÁO CUỐI KỲ DỰ ÁN DI ĐỘNG: ỨNG DỤNG HỌC TỪ VỰNG TIẾNG ANH MINLISH

---

## 1. Tóm tắt sản phẩm
**MinLish** là ứng dụng di động hỗ trợ học và ôn tập từ vựng tiếng Anh cá nhân hóa một cách thông minh và tối ưu. Ứng dụng giúp giải quyết vấn đề nhanh quên từ vựng của người học thông qua giao diện học tương tác trực quan và các thuật toán khoa học.

### Các chức năng chính đã hoàn thành:
*   **Quản lý bộ từ vựng (Decks):** Cho phép người dùng tạo, cập nhật, xóa và quản lý các bộ từ vựng theo chủ đề riêng biệt (ví dụ: IELTS, TOEIC, Giao tiếp).
*   **Quản lý từ vựng:** Thêm từ mới với các trường thông tin phong phú (phiên âm, định nghĩa tiếng Việt, giải thích tiếng Anh, ví dụ thực tế, collocations, từ đồng nghĩa/trái nghĩa và ghi chú).
*   **Học thông minh qua Flashcard:** Giao diện lật thẻ (flashcard) tương tác mượt mà tích hợp phát âm Text-to-Speech (TTS), cho phép người dùng tự đánh giá mức độ ghi nhớ (Again, Hard, Good, Easy).
*   **Thuật toán lặp lại ngắt quãng (Spaced Repetition):** Tích hợp thuật toán khoa học SuperMemo-2 (SM-2) tự động tính toán hệ số ghi nhớ (Ease Factor) và ngày ôn tập tối ưu tiếp theo cho từng từ vựng.
*   **Luyện tập & Kiểm tra (Quiz):** Tạo các bài kiểm tra trắc nghiệm ngẫu nhiên từ danh sách từ vựng của bộ để người dùng ôn luyện và tự đánh giá điểm số.
*   **Theo dõi tiến độ học tập (Dashboard):** Thống kê số từ đã thuộc, số từ cần ôn, lịch sử điểm số bài kiểm tra, nhật ký hoạt động chi tiết hằng ngày và tính toán chuỗi ngày học liên tục (Streak).
*   **Quản lý người dùng & Bảo mật:** Đăng ký, đăng nhập tài khoản bằng Email hoặc Google SSO giả lập, bảo mật mật khẩu bằng mã hóa BCrypt, lưu trữ phiên đăng nhập bằng Preferences DataStore và tùy chỉnh mục tiêu học tập hằng ngày.
*   **Nhập/Xuất dữ liệu (Import/Export CSV):** Nhập hàng loạt từ vựng từ tệp CSV nhanh chóng và xuất bộ từ vựng hiện tại ra tệp CSV để chia sẻ hoặc sao lưu.
*   **Thông báo nhắc nhở học tập:** Sử dụng Android WorkManager chạy ngầm định kỳ gửi thông báo đẩy nhắc nhở ôn tập từ đến hạn hoặc khuyến khích học từ mới hằng ngày.

---

## 2. Kết quả thực hiện

| Chức năng | Mức độ hoàn thành (0-100%) | Mô tả chi tiết kết quả |
| :--- | :---: | :--- |
| **Tạo bộ từ vựng** | 100% | Người dùng dễ dàng thêm mới bộ từ vựng với các thông tin: Tên bộ, Mô tả, Tags (thẻ phân loại) và thời gian khởi tạo. Có đầy đủ tính năng sửa/xóa bộ từ. |
| **Thêm từ vựng** | 100% | Form thêm từ trực quan hỗ trợ nhập: Từ vựng, Phiên âm, Định nghĩa tiếng Việt, Giải thích tiếng Anh, Ví dụ, Collocation, Từ liên quan, Ghi chú. Cho phép chỉnh sửa/xóa từ khỏi bộ. |
| **Học/ôn từ vựng** | 100% | Giao diện Flashcard lật thẻ 2 mặt với hiệu ứng mượt mà. Mặt trước hiện từ, phiên âm, từ loại và nút phát âm (TTS). Mặt sau hiện định nghĩa, ví dụ câu và giải thích. |
| **Quản lý người dùng** | 100% | Đăng ký, đăng nhập bảo mật bằng BCrypt. Hỗ trợ đăng nhập nhanh bằng Google SSO giả lập. Cấu hình mục tiêu học (IELTS/TOEIC/Giao tiếp), cấp độ (A1-C2), giới hạn số từ học mỗi ngày và tự động đếm streak ngày học liên tiếp. |
| **Import/Export** | 100% | Xuất danh sách từ ra file CSV tiêu chuẩn. Nhập từ vựng hàng loạt từ file CSV nhanh chóng với cơ chế kiểm tra định dạng và cảnh báo chi tiết nếu người dùng chọn nhầm tệp Excel thô (`.xlsx`/`.xls`) hoặc tệp nén. |
| **Ôn tập thông minh** | 100% | Tích hợp thuật toán lặp lại ngắt quãng SM-2 (SuperMemo-2). Tự động phân loại từ đến hạn ôn tập (due words) và điều chỉnh lịch học tiếp theo dựa trên đánh giá độ khó của người học. |
| **Theo dõi tiến độ** | 100% | Dashboard hiển thị thống kê tổng quan: Từ đã học, Từ cần ôn tập, Streak học tập. Có biểu đồ lịch sử hoạt động hàng ngày (nhật ký Activity Logs) và lịch sử làm Quiz kiểm tra. |
| **Thông báo nhắc học** | 100% | Sử dụng Android WorkManager thiết lập lịch chạy ngầm định kỳ 12 giờ một lần. Tự động kiểm tra số lượng từ cần ôn tập để gửi thông báo nhắc nhở cá nhân hóa hoặc gửi thông báo động viên học từ mới. |
| **Chức năng khác** | 100% | - Tích hợp Text-to-Speech (Phát âm giọng Anh-Mỹ chuẩn).<br>- Tự động gieo (pre-populate) dữ liệu mẫu phong phú gồm 8 bộ từ chuyên sâu khi người dùng đăng ký mới tài khoản.<br>- Hỗ trợ Dark Mode/Light Mode động đồng bộ theo hệ thống. |

---

## 3. Kiến trúc và kỹ thuật đã áp dụng

### Kiến trúc phần mềm:
Ứng dụng được xây dựng theo kiến trúc chuẩn **MVVM (Model - View - ViewModel)** kết hợp các thành phần Jetpack hiện đại nhằm tối ưu hiệu năng và khả năng bảo trì:
*   **Model (Data Layer):** Lớp dữ liệu gồm Room Database cục bộ (`AppDatabase`), DataStore để quản lý session và các Repository quản lý logic dữ liệu (`UserRepository`, `VocabularyRepository`, `LearningRepository`).
*   **ViewModel (Logic Layer):** Xử lý luồng dữ liệu bất đồng bộ (sử dụng Kotlin Coroutines và Flow), chuyển đổi trạng thái dữ liệu (UI State) để cung cấp cho giao diện.
*   **View (UI Layer):** Thiết kế hoàn toàn bằng Jetpack Compose khai báo (Declarative UI) với Material 3, tăng tính tùy biến giao diện và hiệu ứng hoạt ảnh mượt mà.

### Design Pattern áp dụng:
*   **Repository Pattern:** Che giấu nguồn dữ liệu Room cục bộ, giúp ViewModel chỉ cần tương tác qua interface sạch sẽ.
*   **Singleton Pattern:** Áp dụng cho đối tượng `AppDatabase` và DataStore để quản lý kết nối cơ sở dữ liệu duy nhất tránh tranh chấp tài nguyên.
*   **Factory Pattern:** Sử dụng `ViewModelFactory` để khởi tạo các ViewModel đi kèm các phụ thuộc cần thiết (Dependencies Injection).
*   **Observer Pattern:** Tận dụng `StateFlow` và `Flow` để UI tự động cập nhật ngay khi cơ sở dữ liệu thay đổi.

### Cơ sở dữ liệu (Database):
Sử dụng **Room Database** gồm 6 bảng thực thể quan hệ chặt chẽ:
1.  `users`: Lưu trữ thông tin cá nhân, mục tiêu học tập, cấp độ và streak.
2.  `decks`: Bộ từ vựng, liên kết với bảng `users` qua khóa ngoại `userId` (ON DELETE CASCADE).
3.  `words`: Từ vựng chi tiết, liên kết với bảng `decks` qua khóa ngoại `deckId`.
4.  `learning_progress`: Quản lý tiến trình học của từng từ vựng cho từng người dùng (Ease Factor, Repetitions, Interval, NextReviewTimeMs, Status).
5.  `activity_logs`: Ghi nhận số lượng từ học/ôn tập hằng ngày phục vụ biểu đồ thống kê.
6.  `quiz_history`: Lưu lịch sử kết quả làm bài trắc nghiệm của người dùng.

### Unit Test:
*   Cấu trúc code được thiết kế hướng tới khả năng kiểm thử cao. Bằng việc tách biệt hoàn toàn Business Logic vào các Repository độc lập và tách biệt UI thông qua ViewModel, ta có thể dễ dàng viết Unit Test bằng cách mock Dao hoặc sử dụng Room In-Memory Database để kiểm thử thuật toán SM-2 hoặc logic xác thực.

### Tối ưu hiệu năng (Performance Optimization):
*   **Kotlin Coroutines & Flow:** Thực hiện các tác vụ nặng (như đọc/ghi DB, phân tích file CSV) trên Dispatchers bất đồng bộ (IO) để không gây nghẽn luồng UI (ANR).
*   **Lazy Components:** Sử dụng `LazyColumn` cho danh sách bộ từ vựng và từ vựng giúp tái sử dụng view và giảm bộ nhớ tiêu thụ khi hiển thị danh sách lớn.
*   **Database Indexing:** Tạo chỉ mục (Index) trên các trường thường xuyên tìm kiếm và kết hợp khóa ngoại (như `email` trong `users`, `userId` trong `decks`, `deckId` trong `words`) để tối ưu tốc độ truy vấn SQL.

### Bảo mật (Security):
*   **Mã hóa mật khẩu:** Sử dụng thuật toán băm mật khẩu một chiều **BCrypt** (`BCryptHelper`) để bảo vệ thông tin mật khẩu người dùng trước khi ghi xuống SQLite.
*   **Bảo mật phiên làm việc:** Quản lý đăng nhập bằng mã thông tin bảo mật sinh ngẫu nhiên (Mock JWT Token) lưu trữ an toàn trong Preferences DataStore, tránh lưu trữ mật khẩu văn bản thô trên thiết bị.
*   **Phân quyền dữ liệu cục bộ:** Lọc dữ liệu nghiêm ngặt theo `userId` ngoại khóa, đảm bảo người dùng này không thể truy cập hoặc sửa đổi bộ từ vựng của người dùng khác trên cùng thiết bị.

### Thư viện và Framework sử dụng:
*   **Jetpack Compose & Material 3:** Thiết kế giao diện hiện đại và Responsive.
*   **Room DB (v2.6+):** Cơ sở dữ liệu ORM cục bộ mạnh mẽ.
*   **Jetpack Navigation Compose:** Điều hướng màn hình mượt mà và an toàn.
*   **Android WorkManager:** Lập lịch gửi thông báo nhắc học chạy nền bền bỉ.
*   **Preferences DataStore:** Giải pháp thay thế SharedPreferences hiện đại để lưu session an toàn.
*   **TextToSpeech (TTS):** Công cụ phát âm tiếng Anh có sẵn của Android.

---

## 4. Chức năng mở rộng và sáng tạo

Ứng dụng vượt trên các yêu cầu cơ bản bằng việc tích hợp nhiều cải tiến sáng tạo:
1.  **Thuật toán Spaced Repetition SM-2 thực tế:** Không chỉ đếm ngày thông thường, ứng dụng áp dụng chính xác thuật toán SM-2 điều chỉnh Ease Factor tự động, giúp tối ưu hóa thời gian ôn tập cho từng từ dựa trên trí nhớ của học viên.
2.  **Hệ thống dữ liệu mẫu khởi đầu thông minh (Sample Data Seeding):** Tạo ngay **8 bộ từ vựng mẫu phong phú** thuộc nhiều lĩnh vực quan trọng (Giáo dục, Kinh doanh, Môi trường, Sức khỏe, Công nghệ, Du lịch, Công sở, Xã hội) khi đăng ký tài khoản mới, giúp người dùng có thể trải nghiệm học tập ngay lập tức mà không cần tự nhập liệu từ đầu.
3.  **Tích hợp Phát âm bằng Giọng đọc (Text-to-Speech):** Hỗ trợ phát âm từ vựng chuẩn giọng Anh-Mỹ trực quan ngay trên màn hình flashcard.
4.  **Hệ thống đếm chuỗi Streak liên tục:** Khuyến khích người dùng hình thành thói quen học tập hằng ngày. Streak được cộng dồn khi hoạt động liên tục mỗi ngày và tự động reset nếu bị ngắt quãng.
5.  **Cơ chế bắt lỗi chọn nhầm định dạng file CSV thông minh:** Kiểm tra và chặn các file nhị phân (Excel `.xlsx`/`.xls`, file nén zip) từ bước đầu tiên khi người dùng nhập dữ liệu, đưa ra cảnh báo hướng dẫn chi tiết người dùng cách lưu dưới dạng CSV UTF-8 để hạn chế crash ứng dụng.

---

## 5. Tự đánh giá theo Rubric

| Tiêu chí | Điểm tối đa | Nhóm tự chấm | Giải thích vắn tắt |
| :--- | :---: | :---: | :--- |
| **Bài nộp** | 5 | **5** | Nộp bài đúng hạn, cấu trúc mã nguồn chuẩn MVVM sạch sẽ, không lỗi crash, chạy ổn định trên các thiết bị mô phỏng và thực tế. |
| **Chất lượng sản phẩm** | 30 | **30** | Giao diện Material 3 hiện đại, màu sắc phối hợp hài hòa, hỗ trợ chế độ tối (Dark mode) động, trải nghiệm người dùng (UX) trực quan và mượt mà. |
| **Chức năng cơ bản** | 30 | **30** | Hoàn thành xuất sắc và đầy đủ các chức năng quản lý từ vựng, bộ từ vựng, học flashcard, quản lý thông tin người dùng, đăng ký và đăng nhập. |
| **Chức năng nâng cao** | 25 | **25** | Tích hợp thuật toán SM-2, tính năng xuất/nhập tệp CSV tối ưu, quản lý session bằng DataStore và gửi thông báo nhắc nhở bằng WorkManager chạy nền. |
| **Sáng tạo & mở rộng** | 10 | **10** | Tích hợp Text-to-Speech phát âm, hệ thống streak hằng ngày, tự động gieo dữ liệu mẫu 8 bộ từ vựng ban đầu, và cơ chế phát hiện định dạng file lỗi khi import. |
| **TỔNG ĐIỂM** | **100** | **100/100** | **Dự án hoàn thành xuất sắc tất cả các mục tiêu đề ra.** |

---

## 6. Khó khăn và bài học kinh nghiệm

### Khó khăn gặp phải:
*   **Đồng bộ múi giờ thuật toán SM-2:** Việc tính toán thời điểm tiếp theo cần ôn tập (`nextReviewTimeMs`) theo ngày đòi hỏi xử lý chuẩn xác về mốc thời gian hệ thống và múi giờ địa phương nhằm tránh trường hợp từ vựng bị đưa ra ôn tập quá sớm hoặc quá muộn.
*   **Quyền gửi thông báo trên Android đời cao:** Kể từ Android 13 (API 33), hệ thống yêu cầu cấp quyền `POST_NOTIFICATIONS` động tại thời điểm runtime. Nhóm đã nghiên cứu và triển khai cơ chế kiểm tra quyền và yêu cầu cấp quyền trực quan ngay khi ứng dụng khởi chạy trong `MainActivity`.

### Bài học kinh nghiệm:
*   Hiểu rõ cách tổ chức mã nguồn ứng dụng di động Android theo cấu trúc MVVM sạch sẽ, giúp dự án dễ mở rộng, kiểm thử và bảo trì hơn.
*   Làm chủ các thành phần Jetpack Compose hiện đại như State management (`collectAsState`), hoạt ảnh và điều hướng màn hình.
*   Biết cách triển khai các tác vụ ngầm bền bỉ thông qua Android WorkManager chạy định kỳ và gửi thông báo cục bộ hiệu quả.

---

## 7. Hướng phát triển

*   **Đa dạng hóa hình thức học tập:** Bổ sung thêm các trò chơi nhỏ (mini-games) như ghép từ, điền từ vào chỗ trống, nghe chọn đáp án để việc ôn tập bớt nhàm chán.
*   **Đồng bộ đám mây (Cloud Synchronization):** Kết nối dữ liệu với Firebase hoặc RESTful API backend để người dùng đồng bộ tiến độ học và từ vựng trên nhiều thiết bị và tránh mất mát dữ liệu.
*   **Ứng dụng AI thông minh:** Tích hợp các mô hình ngôn ngữ lớn (như Gemini API) để tự động tạo câu ví dụ tự nhiên, giải thích ngữ nghĩa theo ngữ cảnh hoặc đề xuất lộ trình học cá nhân hóa cho học viên dựa trên lịch sử ghi nhớ.
