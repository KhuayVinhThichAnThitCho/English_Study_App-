package com.group16.study_english_app.data.util

import com.group16.study_english_app.data.local.entity.WordEntity

object SampleData {

    fun getEducationWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Analyze",
                pronunciation = "/ˈæn.əl.aɪz/",
                meaning = "Phân tích",
                descriptionEn = "To examine something in detail in order to understand it better or draw conclusions from it.",
                example = "The scientist analyzed the data from the experiment to find patterns.",
                collocation = "analyze data, analyze results, critically analyze",
                relatedWords = "examine, assess, evaluate, scrutinize",
                note = "Động từ thường dùng trong học thuật và nghiên cứu. Danh từ là 'analysis' (số nhiều: analyses)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Hypothesis",
                pronunciation = "/haɪˈpɒθ.ə.sɪs/",
                meaning = "Giả thuyết",
                descriptionEn = "A proposed explanation made on the basis of limited evidence, used as a starting point for further investigation.",
                example = "The researchers tested their hypothesis by conducting a series of experiments.",
                collocation = "test a hypothesis, form a hypothesis, support a hypothesis",
                relatedWords = "theory, assumption, proposition, conjecture",
                note = "Số nhiều là 'hypotheses'. Giả thuyết cần được kiểm chứng bằng thực nghiệm để trở thành lý thuyết."
            ),
            WordEntity(
                deckId = deckId,
                word = "Empirical",
                pronunciation = "/ɪmˈpɪr.ɪ.kəl/",
                meaning = "Thực nghiệm",
                descriptionEn = "Based on observation or experience rather than theory or pure logic.",
                example = "The study provides empirical evidence that regular exercise improves mental health.",
                collocation = "empirical evidence, empirical research, empirical data",
                relatedWords = "experimental, observational, practical, factual",
                note = "Từ này nhấn mạnh việc dựa trên thực tế, quan sát được, không phải suy luận lý thuyết suông."
            ),
            WordEntity(
                deckId = deckId,
                word = "Coherent",
                pronunciation = "/kəʊˈhɪə.rənt/",
                meaning = "Mạch lạc, chặt chẽ",
                descriptionEn = "Logical and consistent; forming a unified whole where all parts fit together well.",
                example = "She presented a coherent argument that convinced the entire committee.",
                collocation = "coherent argument, coherent plan, coherent structure",
                relatedWords = "logical, consistent, organized, unified",
                note = "Trái nghĩa là 'incoherent' (không mạch lạc). Rất quan trọng khi viết essay học thuật."
            ),
            WordEntity(
                deckId = deckId,
                word = "Concise",
                pronunciation = "/kənˈsaɪs/",
                meaning = "Ngắn gọn, súc tích",
                descriptionEn = "Giving a lot of information clearly and in a few words; brief but comprehensive.",
                example = "The professor asked students to write a concise summary of the article.",
                collocation = "concise summary, concise description, concise manner",
                relatedWords = "brief, succinct, terse, compact",
                note = "Khác với 'short' (ngắn đơn thuần), 'concise' nhấn mạnh việc vẫn đầy đủ thông tin dù ngắn gọn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Elaborate",
                pronunciation = "/iˈlæb.ər.ət/",
                meaning = "Chi tiết, kỹ lưỡng",
                descriptionEn = "Involving many carefully arranged parts or details; to develop or present in further detail.",
                example = "Could you elaborate on your point about climate change?",
                collocation = "elaborate plan, elaborate on something, elaborate design",
                relatedWords = "detailed, complex, intricate, expand",
                note = "Có thể dùng làm tính từ (chi tiết) hoặc động từ (giải thích thêm). Phát âm khác nhau tùy vai trò."
            ),
            WordEntity(
                deckId = deckId,
                word = "Illustrate",
                pronunciation = "/ˈɪl.ə.streɪt/",
                meaning = "Minh họa, làm rõ",
                descriptionEn = "To explain or make something clear by using examples, charts, or pictures.",
                example = "The diagram illustrates how the water cycle works in nature.",
                collocation = "illustrate a point, clearly illustrate, illustrate the concept",
                relatedWords = "demonstrate, depict, show, exemplify",
                note = "Trong học thuật, thường dùng để giới thiệu ví dụ minh họa cho luận điểm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Evaluate",
                pronunciation = "/ɪˈvæl.ju.eɪt/",
                meaning = "Đánh giá, nhận định",
                descriptionEn = "To judge or assess the value, quality, or importance of something carefully.",
                example = "Teachers evaluate students' performance through exams and assignments.",
                collocation = "evaluate performance, evaluate effectiveness, carefully evaluate",
                relatedWords = "assess, appraise, judge, review",
                note = "Danh từ là 'evaluation'. Thường xuất hiện trong đề thi IELTS yêu cầu đánh giá quan điểm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Investigate",
                pronunciation = "/ɪnˈves.tɪ.ɡeɪt/",
                meaning = "Điều tra, nghiên cứu",
                descriptionEn = "To carry out a systematic inquiry to discover and examine facts or information.",
                example = "The police are investigating the cause of the fire.",
                collocation = "investigate a case, investigate the matter, thoroughly investigate",
                relatedWords = "examine, probe, explore, research",
                note = "Danh từ là 'investigation'. Dùng cả trong ngữ cảnh pháp luật lẫn nghiên cứu khoa học."
            ),
            WordEntity(
                deckId = deckId,
                word = "Interpret",
                pronunciation = "/ɪnˈtɜː.prɪt/",
                meaning = "Giải thích, phiên dịch",
                descriptionEn = "To explain the meaning of something, or to translate spoken words from one language to another.",
                example = "Different scholars interpret the ancient text in different ways.",
                collocation = "interpret data, interpret results, interpret the meaning",
                relatedWords = "explain, translate, construe, decipher",
                note = "Có hai nghĩa chính: giải thích (ý nghĩa) và phiên dịch (ngôn ngữ nói). Danh từ: 'interpretation'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Clarify",
                pronunciation = "/ˈklær.ɪ.faɪ/",
                meaning = "Làm rõ",
                descriptionEn = "To make a statement or situation less confused and more clearly understandable.",
                example = "The manager clarified the new policy during the meeting.",
                collocation = "clarify a point, clarify the situation, clarify the meaning",
                relatedWords = "explain, elucidate, illuminate, simplify",
                note = "Danh từ là 'clarification'. Hay dùng trong email: 'I would like to clarify that...'"
            ),
            WordEntity(
                deckId = deckId,
                word = "Compile",
                pronunciation = "/kəmˈpaɪl/",
                meaning = "Biên soạn, gom nhặt",
                descriptionEn = "To collect information from different sources and arrange it in a book, report, or list.",
                example = "She compiled a list of references for her research paper.",
                collocation = "compile a list, compile data, compile a report",
                relatedWords = "assemble, collect, gather, accumulate",
                note = "Trong CNTT, 'compile' cũng có nghĩa là biên dịch mã nguồn. Danh từ: 'compilation'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Derive",
                pronunciation = "/dɪˈraɪv/",
                meaning = "Bắt nguồn từ, rút ra từ",
                descriptionEn = "To obtain something from a specified source; to come from or originate in.",
                example = "Many English words are derived from Latin and Greek roots.",
                collocation = "derive from, derive pleasure, derive benefit",
                relatedWords = "originate, stem, obtain, extract",
                note = "Thường dùng ở thể bị động: 'be derived from'. Danh từ: 'derivation'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Infer",
                pronunciation = "/ɪnˈfɜːr/",
                meaning = "Suy luận",
                descriptionEn = "To form an opinion or reach a conclusion based on evidence and reasoning rather than explicit statements.",
                example = "From the evidence presented, we can infer that the suspect was at the scene.",
                collocation = "infer from, reasonably infer, infer meaning",
                relatedWords = "deduce, conclude, gather, surmise",
                note = "Khác với 'imply' (ngụ ý - người nói), 'infer' là hành động suy luận của người nghe/đọc."
            ),
            WordEntity(
                deckId = deckId,
                word = "Formulate",
                pronunciation = "/ˈfɔː.mjə.leɪt/",
                meaning = "Xây dựng, phát triển",
                descriptionEn = "To create or prepare something carefully, giving particular attention to the details.",
                example = "The team formulated a new strategy to increase sales.",
                collocation = "formulate a plan, formulate a policy, formulate a strategy",
                relatedWords = "devise, develop, create, design",
                note = "Danh từ là 'formulation'. Thường dùng với các kế hoạch, chính sách, chiến lược."
            ),
            WordEntity(
                deckId = deckId,
                word = "Indicate",
                pronunciation = "/ˈɪn.dɪ.keɪt/",
                meaning = "Chỉ ra, biểu thị",
                descriptionEn = "To point out or show something; to be a sign of something.",
                example = "Research indicates that a balanced diet reduces the risk of disease.",
                collocation = "clearly indicate, indicate that, indicate a trend",
                relatedWords = "show, suggest, signal, demonstrate",
                note = "Danh từ là 'indication'. Rất hay dùng trong viết học thuật để trình bày kết quả nghiên cứu."
            ),
            WordEntity(
                deckId = deckId,
                word = "Explicit",
                pronunciation = "/ɪkˈsplɪs.ɪt/",
                meaning = "Rõ ràng, dứt khoát",
                descriptionEn = "Stated clearly and in detail, leaving no room for confusion or doubt.",
                example = "The teacher gave explicit instructions on how to complete the assignment.",
                collocation = "explicit instructions, explicit content, make explicit",
                relatedWords = "clear, direct, unambiguous, specific",
                note = "Trái nghĩa là 'implicit' (ngầm ẩn). Cặp từ này rất hay xuất hiện trong bài thi IELTS Reading."
            ),
            WordEntity(
                deckId = deckId,
                word = "Implicit",
                pronunciation = "/ɪmˈplɪs.ɪt/",
                meaning = "Ngầm, ẩn ý",
                descriptionEn = "Implied or suggested but not directly expressed; understood without being stated.",
                example = "There was an implicit agreement between them not to discuss the matter.",
                collocation = "implicit meaning, implicit trust, implicit assumption",
                relatedWords = "implied, unspoken, tacit, indirect",
                note = "Trái nghĩa là 'explicit'. Trong đọc hiểu, 'implicit information' là thông tin không nói trực tiếp mà phải suy ra."
            ),
            WordEntity(
                deckId = deckId,
                word = "Precise",
                pronunciation = "/prɪˈsaɪs/",
                meaning = "Chính xác, tỉ mỉ",
                descriptionEn = "Exact, accurate, and careful about details.",
                example = "The scientist took precise measurements of the chemical reaction.",
                collocation = "precise measurement, precise definition, to be precise",
                relatedWords = "exact, accurate, specific, meticulous",
                note = "Danh từ là 'precision'. Trạng từ 'precisely' hay dùng để nhấn mạnh: 'Precisely!' (Chính xác!)"
            ),
            WordEntity(
                deckId = deckId,
                word = "Significant",
                pronunciation = "/sɪɡˈnɪf.ɪ.kənt/",
                meaning = "Quan trọng, đáng kể",
                descriptionEn = "Sufficiently great or important to be worthy of attention; having a particular meaning.",
                example = "There has been a significant increase in online learning over the past few years.",
                collocation = "significant impact, significant difference, statistically significant",
                relatedWords = "important, notable, considerable, meaningful",
                note = "Danh từ là 'significance'. Trong thống kê, 'statistically significant' nghĩa là có ý nghĩa thống kê."
            ),
            WordEntity(
                deckId = deckId,
                word = "Evident",
                pronunciation = "/ˈev.ɪ.dənt/",
                meaning = "Hiển nhiên, rõ ràng",
                descriptionEn = "Plain or obvious; clearly seen or understood.",
                example = "It was evident from his expression that he was not happy with the decision.",
                collocation = "clearly evident, self-evident, become evident",
                relatedWords = "obvious, apparent, clear, manifest",
                note = "Danh từ là 'evidence' (bằng chứng). 'Self-evident' nghĩa là hiển nhiên, không cần chứng minh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Contradict",
                pronunciation = "/ˌkɒn.trəˈdɪkt/",
                meaning = "Mâu thuẫn, phản bác",
                descriptionEn = "To say the opposite of what someone else has said; to be so different from something else that one of them must be wrong.",
                example = "The witness's testimony contradicted the evidence found at the scene.",
                collocation = "contradict a statement, contradict each other, directly contradict",
                relatedWords = "deny, oppose, dispute, counter",
                note = "Danh từ là 'contradiction'. Tính từ 'contradictory' nghĩa là mâu thuẫn nhau."
            ),
            WordEntity(
                deckId = deckId,
                word = "Comprise",
                pronunciation = "/kəmˈpraɪz/",
                meaning = "Bao gồm",
                descriptionEn = "To consist of; to be made up of specified parts or elements.",
                example = "The committee comprises ten members from different departments.",
                collocation = "comprise of, mainly comprise, comprise a total of",
                relatedWords = "include, consist of, contain, encompass",
                note = "Lưu ý: 'comprise' không cần 'of'. 'The team comprises 5 people' (đúng), không phải 'comprises of'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Depict",
                pronunciation = "/dɪˈpɪkt/",
                meaning = "Mô tả, khắc họa",
                descriptionEn = "To represent or show something in a picture, story, or description.",
                example = "The novel depicts life in rural Vietnam during the 1940s.",
                collocation = "depict a scene, accurately depict, depict as",
                relatedWords = "portray, illustrate, describe, represent",
                note = "Danh từ là 'depiction'. Thường dùng khi nói về nghệ thuật, văn học, phim ảnh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Conduct",
                pronunciation = "/kənˈdʌkt/",
                meaning = "Tiến hành, thực hiện",
                descriptionEn = "To organize and carry out an activity or process; to direct the performance of.",
                example = "The university conducted a survey on student satisfaction.",
                collocation = "conduct research, conduct a survey, conduct an experiment",
                relatedWords = "carry out, perform, execute, undertake",
                note = "Danh từ 'conduct' /ˈkɒn.dʌkt/ (nhấn âm đầu) nghĩa là hành vi, cách ứng xử."
            )
        )
    }

    fun getBusinessWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Negotiate",
                pronunciation = "/nəˈɡəʊ.ʃi.eɪt/",
                meaning = "Đàm phán, thương lượng",
                descriptionEn = "To discuss something in order to reach an agreement, especially in business or politics.",
                example = "The two companies negotiated a deal worth millions of dollars.",
                collocation = "negotiate a deal, negotiate terms, negotiate a contract",
                relatedWords = "bargain, discuss, mediate, arbitrate",
                note = "Danh từ là 'negotiation'. Người đàm phán là 'negotiator'. Kỹ năng đàm phán rất quan trọng trong kinh doanh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Budget",
                pronunciation = "/ˈbʌdʒ.ɪt/",
                meaning = "Ngân sách",
                descriptionEn = "A plan showing how much money a person or organization will earn and spend over a period of time.",
                example = "The government announced the annual budget for education and healthcare.",
                collocation = "budget plan, within budget, budget allocation",
                relatedWords = "funds, allocation, expenditure, finance",
                note = "Có thể dùng làm danh từ (ngân sách), động từ (lập ngân sách), hoặc tính từ (giá rẻ: budget airline)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Invoice",
                pronunciation = "/ˈɪn.vɔɪs/",
                meaning = "Hóa đơn thanh toán",
                descriptionEn = "A list of goods sent or services provided, with a statement of the sum due for these.",
                example = "Please send the invoice to our accounting department for payment.",
                collocation = "issue an invoice, pay an invoice, invoice number",
                relatedWords = "bill, receipt, statement, account",
                note = "Khác với 'receipt' (biên lai - đã thanh toán), 'invoice' là yêu cầu thanh toán (chưa trả tiền)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Asset",
                pronunciation = "/ˈæs.et/",
                meaning = "Tài sản, lợi thế",
                descriptionEn = "A useful or valuable thing, person, or quality; property owned by a person or company.",
                example = "The company's total assets exceeded ten billion dollars last year.",
                collocation = "valuable asset, asset management, fixed assets",
                relatedWords = "property, resource, possession, capital",
                note = "Trái nghĩa là 'liability' (nợ phải trả). Trong kế toán: assets = liabilities + equity."
            ),
            WordEntity(
                deckId = deckId,
                word = "Audit",
                pronunciation = "/ˈɔː.dɪt/",
                meaning = "Kiểm toán",
                descriptionEn = "An official inspection of an organization's accounts, typically by an independent body.",
                example = "The firm hired an external company to audit their financial records.",
                collocation = "financial audit, audit report, conduct an audit",
                relatedWords = "inspection, review, examination, assessment",
                note = "Người kiểm toán là 'auditor'. Kiểm toán giúp đảm bảo tính minh bạch tài chính của doanh nghiệp."
            ),
            WordEntity(
                deckId = deckId,
                word = "Dividend",
                pronunciation = "/ˈdɪv.ɪ.dend/",
                meaning = "Cổ tức",
                descriptionEn = "A sum of money paid regularly by a company to its shareholders from its profits.",
                example = "The company declared a dividend of two dollars per share.",
                collocation = "pay a dividend, dividend yield, declare a dividend",
                relatedWords = "profit, return, earnings, payout",
                note = "Cổ tức là phần lợi nhuận chia cho cổ đông. 'Dividend yield' là tỷ suất cổ tức."
            ),
            WordEntity(
                deckId = deckId,
                word = "Franchise",
                pronunciation = "/ˈfræn.tʃaɪz/",
                meaning = "Nhượng quyền thương hiệu",
                descriptionEn = "An authorization granted by a company to an individual or group to market its products or services in a specific territory.",
                example = "He bought a fast-food franchise and opened three stores in the city.",
                collocation = "franchise agreement, franchise owner, franchise model",
                relatedWords = "license, chain, brand, dealership",
                note = "Mô hình nhượng quyền phổ biến ở các chuỗi thức ăn nhanh như McDonald's, KFC."
            ),
            WordEntity(
                deckId = deckId,
                word = "Income",
                pronunciation = "/ˈɪn.kʌm/",
                meaning = "Thu nhập",
                descriptionEn = "Money received, especially on a regular basis, from work or through investments.",
                example = "Her monthly income is sufficient to cover all living expenses.",
                collocation = "annual income, income tax, source of income",
                relatedWords = "earnings, salary, revenue, wages",
                note = "Phân biệt: 'income' (thu nhập chung), 'salary' (lương tháng), 'wage' (lương theo giờ/ngày)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inflation",
                pronunciation = "/ɪnˈfleɪ.ʃən/",
                meaning = "Lạm phát",
                descriptionEn = "A general increase in prices and fall in the purchasing value of money over time.",
                example = "The country experienced high inflation, causing prices to rise dramatically.",
                collocation = "inflation rate, control inflation, rising inflation",
                relatedWords = "deflation, price increase, devaluation, hyperinflation",
                note = "Trái nghĩa là 'deflation' (giảm phát). Lạm phát cao làm giảm sức mua của đồng tiền."
            ),
            WordEntity(
                deckId = deckId,
                word = "Deposit",
                pronunciation = "/dɪˈpɒz.ɪt/",
                meaning = "Tiền đặt cọc, gửi tiền",
                descriptionEn = "A sum of money placed in a bank account or paid as a first installment or as a pledge.",
                example = "You need to pay a deposit of 30 percent before we can reserve the room.",
                collocation = "make a deposit, security deposit, deposit account",
                relatedWords = "down payment, installment, pledge, advance",
                note = "Có nhiều nghĩa: gửi tiền ngân hàng, đặt cọc thuê nhà, hoặc trầm tích (địa chất)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Discount",
                pronunciation = "/ˈdɪs.kaʊnt/",
                meaning = "Giảm giá",
                descriptionEn = "A deduction from the usual cost of something, typically given for prompt or advance payment.",
                example = "The store is offering a 20 percent discount on all electronics this week.",
                collocation = "get a discount, discount price, offer a discount",
                relatedWords = "reduction, markdown, rebate, sale",
                note = "Có thể dùng làm danh từ hoặc động từ. 'At a discount' nghĩa là với giá giảm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Expense",
                pronunciation = "/ɪkˈspens/",
                meaning = "Chi phí",
                descriptionEn = "The cost required for something; money spent on something.",
                example = "Travel expenses will be reimbursed by the company after the business trip.",
                collocation = "at the expense of, business expenses, expense report",
                relatedWords = "cost, expenditure, outlay, charge",
                note = "'At the expense of' nghĩa là 'với cái giá phải trả là'. Tính từ: 'expensive' (đắt đỏ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Financial",
                pronunciation = "/faɪˈnæn.ʃəl/",
                meaning = "Thuộc về tài chính",
                descriptionEn = "Relating to money or the management of money, especially for a company or government.",
                example = "The financial report showed a significant increase in quarterly revenue.",
                collocation = "financial statement, financial crisis, financial support",
                relatedWords = "monetary, fiscal, economic, pecuniary",
                note = "Danh từ là 'finance'. Trạng từ 'financially' dùng phổ biến: 'financially stable' (ổn định tài chính)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Forecast",
                pronunciation = "/ˈfɔː.kɑːst/",
                meaning = "Dự báo",
                descriptionEn = "A prediction or estimate of future events, especially relating to weather, finance, or trends.",
                example = "The economic forecast suggests growth will slow down next quarter.",
                collocation = "weather forecast, sales forecast, forecast growth",
                relatedWords = "predict, project, estimate, anticipate",
                note = "Dùng cả làm danh từ và động từ. Quá khứ có thể là 'forecast' hoặc 'forecasted'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Insurance",
                pronunciation = "/ɪnˈʃɔː.rəns/",
                meaning = "Bảo hiểm",
                descriptionEn = "An arrangement by which a company provides a guarantee of compensation for specified loss or damage in return for payment of a premium.",
                example = "Health insurance is essential for covering unexpected medical expenses.",
                collocation = "insurance policy, insurance premium, insurance claim",
                relatedWords = "coverage, protection, assurance, indemnity",
                note = "Các loại phổ biến: health insurance (bảo hiểm y tế), life insurance (bảo hiểm nhân thọ), car insurance (bảo hiểm xe)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inventory",
                pronunciation = "/ˈɪn.vən.tər.i/",
                meaning = "Hàng tồn kho",
                descriptionEn = "A complete list of items such as goods in stock or the contents of a building; the quantity of goods held in stock.",
                example = "The manager checks the inventory every month to avoid shortages.",
                collocation = "inventory management, take inventory, inventory control",
                relatedWords = "stock, supply, merchandise, goods",
                note = "Quản lý tồn kho hiệu quả giúp giảm chi phí lưu trữ và tránh thiếu hàng."
            ),
            WordEntity(
                deckId = deckId,
                word = "Guarantee",
                pronunciation = "/ˌɡær.ənˈtiː/",
                meaning = "Cam kết, bảo đảm",
                descriptionEn = "A formal promise or assurance that certain conditions will be fulfilled; a warranty.",
                example = "The product comes with a two-year money-back guarantee.",
                collocation = "money-back guarantee, guarantee quality, provide a guarantee",
                relatedWords = "warranty, assurance, pledge, promise",
                note = "Có thể dùng làm danh từ và động từ. Tương tự 'warranty' nhưng 'guarantee' rộng nghĩa hơn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Allocate",
                pronunciation = "/ˈæl.ə.keɪt/",
                meaning = "Phân bổ, cấp phát",
                descriptionEn = "To distribute resources or duties for a particular purpose.",
                example = "The government allocated more funds to education this year.",
                collocation = "allocate resources, allocate funds, allocate budget",
                relatedWords = "assign, distribute, apportion, designate",
                note = "Danh từ là 'allocation'. Thường dùng với ngân sách, tài nguyên, nhân lực."
            ),
            WordEntity(
                deckId = deckId,
                word = "Compensate",
                pronunciation = "/ˈkɒm.pən.seɪt/",
                meaning = "Đền bù, bồi thường",
                descriptionEn = "To give something, typically money, in recognition of loss, suffering, or injury; to make up for something.",
                example = "The airline compensated passengers for the delayed flight with vouchers.",
                collocation = "compensate for, compensate losses, fairly compensate",
                relatedWords = "reimburse, repay, indemnify, offset",
                note = "Danh từ là 'compensation'. 'Compensate for' nghĩa là bù đắp cho điều gì đó."
            ),
            WordEntity(
                deckId = deckId,
                word = "Estimate",
                pronunciation = "/ˈes.tɪ.meɪt/",
                meaning = "Ước lượng, báo giá",
                descriptionEn = "To roughly calculate or judge the value, number, quantity, or extent of something.",
                example = "The contractor estimated the cost of renovation at around fifty thousand dollars.",
                collocation = "rough estimate, estimate the cost, conservative estimate",
                relatedWords = "approximate, assess, calculate, gauge",
                note = "Danh từ phát âm /ˈes.tɪ.mət/, động từ phát âm /ˈes.tɪ.meɪt/. Danh từ: 'estimation'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Benefit",
                pronunciation = "/ˈben.ɪ.fɪt/",
                meaning = "Quyền lợi, lợi ích",
                descriptionEn = "An advantage or profit gained from something; a payment made by the state or an insurance scheme.",
                example = "Regular exercise has many benefits for both physical and mental health.",
                collocation = "mutual benefit, benefit from, health benefits",
                relatedWords = "advantage, gain, profit, perk",
                note = "Có thể dùng làm danh từ và động từ. Tính từ: 'beneficial' (có lợi)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Contract",
                pronunciation = "/ˈkɒn.trækt/",
                meaning = "Hợp đồng",
                descriptionEn = "A written or spoken agreement that is intended to be enforceable by law.",
                example = "Both parties signed the contract before the project officially started.",
                collocation = "sign a contract, breach of contract, contract terms",
                relatedWords = "agreement, deal, pact, arrangement",
                note = "Danh từ nhấn âm đầu /ˈkɒn.trækt/, động từ nhấn âm sau /kənˈtrækt/ (co lại, ký hợp đồng)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Consumer",
                pronunciation = "/kənˈsjuː.mər/",
                meaning = "Người tiêu dùng",
                descriptionEn = "A person who purchases goods and services for personal use.",
                example = "Consumer demand for organic food has increased significantly in recent years.",
                collocation = "consumer behavior, consumer goods, consumer rights",
                relatedWords = "buyer, customer, purchaser, client",
                note = "Động từ là 'consume' (tiêu thụ). Phân biệt: 'consumer' (người tiêu dùng cuối) và 'customer' (khách hàng)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Cost-effective",
                pronunciation = "/ˌkɒst.ɪˈfek.tɪv/",
                meaning = "Hiệu quả chi phí",
                descriptionEn = "Providing good value or results in relation to the money or time spent.",
                example = "Solar energy is becoming a more cost-effective alternative to fossil fuels.",
                collocation = "cost-effective solution, cost-effective method, cost-effective way",
                relatedWords = "economical, efficient, affordable, budget-friendly",
                note = "Từ ghép dùng dấu gạch nối. Nhấn mạnh việc đạt kết quả tốt mà không tốn kém quá mức."
            ),
            WordEntity(
                deckId = deckId,
                word = "Incentive",
                pronunciation = "/ɪnˈsen.tɪv/",
                meaning = "Động lực, khuyến khích",
                descriptionEn = "A thing that motivates or encourages someone to do something, often a reward or benefit.",
                example = "The company offers financial incentives to employees who exceed their targets.",
                collocation = "financial incentive, provide incentive, incentive scheme",
                relatedWords = "motivation, stimulus, encouragement, inducement",
                note = "Thường dùng trong kinh doanh và chính sách. 'Tax incentive' là ưu đãi thuế."
            )
        )
    }

    fun getEnvironmentWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Diminish",
                pronunciation = "/dɪˈmɪn.ɪʃ/",
                meaning = "Giảm bớt, thu nhỏ",
                descriptionEn = "To make or become less; to reduce in size, extent, or importance.",
                example = "The country's natural resources have been diminishing at an alarming rate.",
                collocation = "diminish in value, greatly diminish, diminish the impact",
                relatedWords = "decrease, reduce, lessen, decline",
                note = "Tính từ 'diminished' nghĩa là đã suy giảm. 'Law of diminishing returns' là quy luật lợi tức giảm dần."
            ),
            WordEntity(
                deckId = deckId,
                word = "Fluctuate",
                pronunciation = "/ˈflʌk.tʃu.eɪt/",
                meaning = "Biến động, dao động",
                descriptionEn = "To rise and fall irregularly in number or amount; to vary unpredictably.",
                example = "Oil prices fluctuate depending on global supply and demand.",
                collocation = "fluctuate widely, fluctuate between, prices fluctuate",
                relatedWords = "vary, change, oscillate, shift",
                note = "Danh từ là 'fluctuation'. Thường dùng khi nói về giá cả, thị trường, nhiệt độ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Accumulate",
                pronunciation = "/əˈkjuː.mjə.leɪt/",
                meaning = "Tích lũy, tích tụ",
                descriptionEn = "To gather together or acquire an increasing number or quantity of something over time.",
                example = "Plastic waste has accumulated in the ocean, causing severe environmental damage.",
                collocation = "accumulate wealth, accumulate over time, gradually accumulate",
                relatedWords = "amass, collect, gather, stockpile",
                note = "Danh từ là 'accumulation'. Dùng cả nghĩa tích cực (tích lũy kiến thức) và tiêu cực (tích tụ rác)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Eliminate",
                pronunciation = "/iˈlɪm.ɪ.neɪt/",
                meaning = "Loại bỏ, loại trừ",
                descriptionEn = "To completely remove or get rid of something.",
                example = "The new policy aims to eliminate single-use plastics by next year.",
                collocation = "eliminate waste, eliminate risk, completely eliminate",
                relatedWords = "remove, eradicate, abolish, extinguish",
                note = "Danh từ là 'elimination'. Mạnh hơn 'reduce' (giảm) vì nghĩa là loại bỏ hoàn toàn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Disperse",
                pronunciation = "/dɪˈspɜːs/",
                meaning = "Phân tán, giải tán",
                descriptionEn = "To distribute or spread over a wide area; to go in different directions.",
                example = "The wind dispersed the seeds across a vast area of the forest.",
                collocation = "disperse a crowd, widely dispersed, disperse into",
                relatedWords = "scatter, spread, disseminate, distribute",
                note = "Danh từ là 'dispersion' hoặc 'dispersal'. Dùng cả nghĩa vật lý và xã hội."
            ),
            WordEntity(
                deckId = deckId,
                word = "Emerge",
                pronunciation = "/ɪˈmɜːdʒ/",
                meaning = "Nổi lên, xuất hiện",
                descriptionEn = "To move out of or away from something and become visible; to come into existence.",
                example = "New technologies are emerging that could help reduce carbon emissions.",
                collocation = "emerge from, newly emerging, emerge as",
                relatedWords = "appear, arise, surface, develop",
                note = "Danh từ là 'emergence'. Tính từ 'emerging' hay dùng: 'emerging markets' (thị trường mới nổi)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Exploit",
                pronunciation = "/ɪkˈsplɔɪt/",
                meaning = "Khai thác, lợi dụng",
                descriptionEn = "To use something or someone unfairly for one's own advantage; to make full use of a resource.",
                example = "Companies that exploit natural resources without regard for the environment face heavy fines.",
                collocation = "exploit resources, exploit workers, exploit an opportunity",
                relatedWords = "utilize, abuse, take advantage of, manipulate",
                note = "Danh từ /ˈek.splɔɪt/ nghĩa là chiến công, kỳ tích. Danh từ 'exploitation' mang nghĩa tiêu cực hơn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inherent",
                pronunciation = "/ɪnˈhɪə.rənt/",
                meaning = "Vốn có, cố hữu",
                descriptionEn = "Existing as a natural or basic part of something; permanent and inseparable.",
                example = "There are inherent risks in any outdoor adventure activity.",
                collocation = "inherent risk, inherent quality, inherent weakness",
                relatedWords = "innate, intrinsic, built-in, natural",
                note = "Trạng từ 'inherently' rất hay dùng: 'inherently dangerous' (bản chất là nguy hiểm)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Yield",
                pronunciation = "/jiːld/",
                meaning = "Mang lại, sản sinh",
                descriptionEn = "To produce or provide a result, gain, or financial return; to give way under pressure.",
                example = "The farmland yields enough crops to feed the entire community.",
                collocation = "yield results, high yield, yield a profit",
                relatedWords = "produce, generate, provide, bear",
                note = "Có nhiều nghĩa: sản sinh (nông nghiệp), nhường (giao thông), lợi suất (tài chính)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inevitable",
                pronunciation = "/ɪnˈev.ɪ.tə.bəl/",
                meaning = "Không thể tránh khỏi",
                descriptionEn = "Certain to happen; unavoidable and impossible to prevent.",
                example = "Climate change will have inevitable consequences if we do not act now.",
                collocation = "inevitable consequence, inevitable result, seem inevitable",
                relatedWords = "unavoidable, inescapable, certain, bound to happen",
                note = "Trạng từ 'inevitably' rất phổ biến trong viết luận. Danh từ: 'inevitability'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Enhance",
                pronunciation = "/ɪnˈhɑːns/",
                meaning = "Nâng cao, cải thiện",
                descriptionEn = "To intensify, increase, or further improve the quality, value, or extent of something.",
                example = "Planting trees can enhance the beauty and air quality of urban areas.",
                collocation = "enhance quality, enhance performance, greatly enhance",
                relatedWords = "improve, boost, augment, elevate",
                note = "Danh từ là 'enhancement'. Thường dùng trong ngữ cảnh tích cực, cải thiện điều gì đó."
            ),
            WordEntity(
                deckId = deckId,
                word = "Diverse",
                pronunciation = "/daɪˈvɜːs/",
                meaning = "Đa dạng",
                descriptionEn = "Showing a great deal of variety; very different from each other.",
                example = "Tropical rainforests are home to the most diverse ecosystems on the planet.",
                collocation = "diverse range, culturally diverse, diverse population",
                relatedWords = "varied, assorted, mixed, heterogeneous",
                note = "Danh từ là 'diversity'. Rất quan trọng trong chủ đề môi trường: 'biodiversity' (đa dạng sinh học)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Drastic",
                pronunciation = "/ˈdræs.tɪk/",
                meaning = "Quyết liệt, mạnh mẽ",
                descriptionEn = "Likely to have a strong or far-reaching effect; radical and extreme.",
                example = "Drastic measures are needed to reduce greenhouse gas emissions.",
                collocation = "drastic measures, drastic change, drastic reduction",
                relatedWords = "extreme, severe, radical, sweeping",
                note = "Trạng từ 'drastically' rất hay dùng: 'drastically reduce' (giảm mạnh), 'drastically change' (thay đổi mạnh mẽ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Enormous",
                pronunciation = "/ɪˈnɔː.məs/",
                meaning = "To lớn, khổng lồ",
                descriptionEn = "Very large in size, quantity, or extent; immense.",
                example = "The oil spill caused enormous damage to marine life in the region.",
                collocation = "enormous amount, enormous impact, enormous pressure",
                relatedWords = "huge, vast, immense, colossal",
                note = "Trạng từ 'enormously' nghĩa là cực kỳ. Mạnh hơn 'big' hoặc 'large' rất nhiều."
            ),
            WordEntity(
                deckId = deckId,
                word = "Adapt",
                pronunciation = "/əˈdæpt/",
                meaning = "Thích nghi",
                descriptionEn = "To change or adjust in order to suit new conditions or a different environment.",
                example = "Many species cannot adapt quickly enough to survive rapid climate change.",
                collocation = "adapt to, easily adapt, adapt to changes",
                relatedWords = "adjust, modify, acclimate, evolve",
                note = "Danh từ 'adaptation' dùng nhiều trong sinh học. Tính từ: 'adaptable' (có khả năng thích nghi)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Infrastructure",
                pronunciation = "/ˈɪn.frəˌstrʌk.tʃər/",
                meaning = "Cơ sở hạ tầng",
                descriptionEn = "The basic physical and organizational structures and facilities needed for the operation of a society, such as roads, bridges, and power supplies.",
                example = "Investing in green infrastructure can help cities become more sustainable.",
                collocation = "build infrastructure, infrastructure development, critical infrastructure",
                relatedWords = "facilities, framework, foundation, system",
                note = "Bao gồm đường xá, cầu cống, hệ thống điện nước, viễn thông. Không đếm được (uncountable)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Prevalent",
                pronunciation = "/ˈprev.əl.ənt/",
                meaning = "Phổ biến, thịnh hành",
                descriptionEn = "Widespread in a particular area at a particular time; commonly occurring.",
                example = "Air pollution is prevalent in many major cities around the world.",
                collocation = "most prevalent, increasingly prevalent, prevalent in",
                relatedWords = "common, widespread, frequent, pervasive",
                note = "Danh từ là 'prevalence'. Mang tính trang trọng hơn 'common'. Thường dùng trong văn học thuật."
            ),
            WordEntity(
                deckId = deckId,
                word = "Consequence",
                pronunciation = "/ˈkɒn.sɪ.kwəns/",
                meaning = "Hệ quả, hậu quả",
                descriptionEn = "A result or effect of an action or situation, typically one that is unwelcome or unpleasant.",
                example = "Deforestation has serious consequences for biodiversity and climate stability.",
                collocation = "face consequences, as a consequence, serious consequences",
                relatedWords = "result, outcome, repercussion, aftermath",
                note = "'As a consequence of' = 'do hậu quả của'. Tính từ: 'consequent' (theo sau đó)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Sufficient",
                pronunciation = "/səˈfɪʃ.ənt/",
                meaning = "Đầy đủ",
                descriptionEn = "Enough to meet the needs of a situation or a proposed end; adequate.",
                example = "There is not sufficient evidence to prove that the chemical is safe for the environment.",
                collocation = "sufficient evidence, sufficient resources, self-sufficient",
                relatedWords = "enough, adequate, ample, satisfactory",
                note = "Trái nghĩa là 'insufficient'. 'Self-sufficient' nghĩa là tự cung tự cấp."
            ),
            WordEntity(
                deckId = deckId,
                word = "Duration",
                pronunciation = "/dʒʊəˈreɪ.ʃən/",
                meaning = "Khoảng thời gian kéo dài",
                descriptionEn = "The time during which something continues or exists.",
                example = "The duration of the dry season has been increasing due to climate change.",
                collocation = "for the duration of, duration of time, short duration",
                relatedWords = "length, period, span, extent",
                note = "Thường dùng trong ngữ cảnh trang trọng. 'For the duration' nghĩa là trong suốt thời gian."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inhibit",
                pronunciation = "/ɪnˈhɪb.ɪt/",
                meaning = "Ngăn chặn, hạn chế",
                descriptionEn = "To hinder, restrain, or prevent an action or process from occurring.",
                example = "Pollution can inhibit the growth of plants and aquatic organisms.",
                collocation = "inhibit growth, inhibit development, inhibit the process",
                relatedWords = "hinder, restrain, suppress, impede",
                note = "Danh từ là 'inhibition'. Trong sinh học, 'inhibitor' là chất ức chế."
            ),
            WordEntity(
                deckId = deckId,
                word = "Intense",
                pronunciation = "/ɪnˈtens/",
                meaning = "Mãnh liệt, dữ dội",
                descriptionEn = "Of extreme force, degree, or strength; very strong or severe.",
                example = "The region experienced intense heat waves as a result of global warming.",
                collocation = "intense heat, intense pressure, intense competition",
                relatedWords = "extreme, severe, fierce, powerful",
                note = "Danh từ là 'intensity'. Động từ 'intensify' nghĩa là làm tăng cường độ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Discard",
                pronunciation = "/dɪˈskɑːd/",
                meaning = "Vứt bỏ, loại bỏ",
                descriptionEn = "To get rid of something as no longer useful or desirable; to throw away.",
                example = "People should properly discard electronic waste to prevent soil contamination.",
                collocation = "discard waste, discard items, simply discard",
                relatedWords = "dispose of, throw away, dump, abandon",
                note = "Mang tính trang trọng hơn 'throw away'. Thường dùng trong ngữ cảnh môi trường và tái chế."
            ),
            WordEntity(
                deckId = deckId,
                word = "Dominate",
                pronunciation = "/ˈdɒm.ɪ.neɪt/",
                meaning = "Thống trị, áp đảo",
                descriptionEn = "To have a commanding influence on; to be the most important or conspicuous element.",
                example = "Fossil fuels still dominate the global energy supply despite the rise of renewables.",
                collocation = "dominate the market, dominate the landscape, dominate the industry",
                relatedWords = "control, rule, prevail, overshadow",
                note = "Danh từ là 'domination' hoặc 'dominance'. Tính từ: 'dominant' (chiếm ưu thế)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Intrinsic",
                pronunciation = "/ɪnˈtrɪn.zɪk/",
                meaning = "Bản chất, thực chất",
                descriptionEn = "Belonging naturally; essential and inherent rather than acquired from outside.",
                example = "Biodiversity has intrinsic value beyond its economic benefits to humans.",
                collocation = "intrinsic value, intrinsic motivation, intrinsic quality",
                relatedWords = "inherent, innate, essential, fundamental",
                note = "Trái nghĩa là 'extrinsic' (bên ngoài). 'Intrinsic motivation' là động lực nội tại (làm vì thích, không vì phần thưởng)."
            )
        )
    }

    fun getHealthWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Alleviate",
                pronunciation = "/əˈliː.vi.eɪt/",
                meaning = "Làm dịu, giảm bớt",
                descriptionEn = "To make suffering, pain, or a problem less severe.",
                example = "This medication can help alleviate the symptoms of chronic back pain.",
                collocation = "alleviate pain, alleviate suffering, alleviate poverty",
                relatedWords = "relieve, ease, reduce, mitigate",
                note = "Danh từ là 'alleviation'. Mang tính trang trọng hơn 'reduce'. Thường dùng với pain, suffering, stress."
            ),
            WordEntity(
                deckId = deckId,
                word = "Induce",
                pronunciation = "/ɪnˈdjuːs/",
                meaning = "Gây ra, xui khiến",
                descriptionEn = "To bring about or give rise to something; to persuade someone to do something.",
                example = "Lack of sleep can induce a range of health problems including anxiety.",
                collocation = "induce labor, induce change, induce sleep",
                relatedWords = "cause, provoke, trigger, stimulate",
                note = "Danh từ là 'inducement' (sự xui khiến). Trong y khoa, 'induce labor' nghĩa là gây chuyển dạ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Encounter",
                pronunciation = "/ɪnˈkaʊn.tər/",
                meaning = "Chạm trán, gặp phải",
                descriptionEn = "To unexpectedly meet or experience something, especially something difficult or hostile.",
                example = "Patients may encounter various side effects during the course of treatment.",
                collocation = "encounter problems, encounter difficulties, close encounter",
                relatedWords = "meet, face, confront, come across",
                note = "Có thể dùng làm danh từ và động từ. Mang tính bất ngờ, không lường trước được."
            ),
            WordEntity(
                deckId = deckId,
                word = "Crucial",
                pronunciation = "/ˈkruː.ʃəl/",
                meaning = "Cực kỳ quan trọng",
                descriptionEn = "Of great importance; decisive or critical, especially in the success or failure of something.",
                example = "Early diagnosis is crucial for the successful treatment of many diseases.",
                collocation = "crucial role, crucial factor, crucial decision",
                relatedWords = "vital, essential, critical, pivotal",
                note = "Mạnh hơn 'important'. Nhấn mạnh tính quyết định, sống còn của vấn đề."
            ),
            WordEntity(
                deckId = deckId,
                word = "Anticipate",
                pronunciation = "/ænˈtɪs.ɪ.peɪt/",
                meaning = "Dự đoán, trông đợi",
                descriptionEn = "To regard as probable; to expect or predict something and prepare for it.",
                example = "Doctors anticipate a rise in flu cases during the winter months.",
                collocation = "anticipate problems, widely anticipated, anticipate demand",
                relatedWords = "expect, predict, foresee, forecast",
                note = "Danh từ là 'anticipation'. 'In anticipation of' nghĩa là 'để chuẩn bị cho' hoặc 'trong sự mong đợi'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Durable",
                pronunciation = "/ˈdʒʊə.rə.bəl/",
                meaning = "Bền bỉ",
                descriptionEn = "Able to withstand wear, pressure, or damage; lasting for a long time.",
                example = "Choosing durable materials for medical equipment ensures long-term reliability.",
                collocation = "durable goods, durable material, highly durable",
                relatedWords = "lasting, sturdy, resilient, robust",
                note = "Danh từ là 'durability'. 'Durable goods' là hàng hóa lâu bền (tivi, tủ lạnh...)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Eligible",
                pronunciation = "/ˈel.ɪ.dʒə.bəl/",
                meaning = "Đủ điều kiện",
                descriptionEn = "Having the right to do or obtain something; satisfying the appropriate conditions.",
                example = "All citizens over 18 are eligible for free health screenings.",
                collocation = "eligible for, eligible to vote, eligible candidate",
                relatedWords = "qualified, entitled, suitable, worthy",
                note = "Danh từ là 'eligibility'. Trái nghĩa: 'ineligible'. Thường dùng với 'for' hoặc 'to'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Emphasize",
                pronunciation = "/ˈem.fə.saɪz/",
                meaning = "Nhấn mạnh",
                descriptionEn = "To give special importance or prominence to something in speaking or writing.",
                example = "Health experts emphasize the importance of regular exercise and a balanced diet.",
                collocation = "strongly emphasize, emphasize the importance, emphasize the need",
                relatedWords = "stress, highlight, underline, accentuate",
                note = "Danh từ là 'emphasis' (số nhiều: emphases). Tính từ: 'emphatic' (dứt khoát, nhấn mạnh)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Enable",
                pronunciation = "/ɪˈneɪ.bəl/",
                meaning = "Cho phép, giúp cho",
                descriptionEn = "To give someone the ability, means, or opportunity to do something; to make possible.",
                example = "New medical technology enables doctors to diagnose diseases more accurately.",
                collocation = "enable access, enable people to, enable growth",
                relatedWords = "allow, permit, empower, facilitate",
                note = "Cấu trúc: 'enable somebody to do something'. Trái nghĩa: 'disable' (vô hiệu hóa)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Equate",
                pronunciation = "/ɪˈkweɪt/",
                meaning = "Đánh đồng",
                descriptionEn = "To consider one thing to be the same as or equivalent to another.",
                example = "Many people equate wealth with happiness, but research suggests otherwise.",
                collocation = "equate with, equate to, cannot equate",
                relatedWords = "compare, liken, associate, identify",
                note = "Danh từ là 'equation' (phương trình). 'Equate A with B' nghĩa là đánh đồng A với B."
            ),
            WordEntity(
                deckId = deckId,
                word = "Exclude",
                pronunciation = "/ɪkˈskluːd/",
                meaning = "Loại trừ",
                descriptionEn = "To deny someone access to or bar someone from a place, group, or privilege; to leave out.",
                example = "The study excluded participants who had pre-existing medical conditions.",
                collocation = "exclude from, deliberately exclude, exclude the possibility",
                relatedWords = "omit, bar, eliminate, reject",
                note = "Danh từ là 'exclusion'. Trái nghĩa: 'include'. Tính từ: 'exclusive' (độc quyền)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Exhibit",
                pronunciation = "/ɪɡˈzɪb.ɪt/",
                meaning = "Trưng bày, thể hiện",
                descriptionEn = "To publicly display something in a gallery or museum; to show or demonstrate a quality or behavior.",
                example = "Patients who exhibit symptoms of depression should seek professional help.",
                collocation = "exhibit symptoms, exhibit behavior, exhibit signs",
                relatedWords = "display, show, demonstrate, manifest",
                note = "Danh từ 'exhibition' (triển lãm). Trong y khoa, 'exhibit symptoms' là biểu hiện triệu chứng."
            ),
            WordEntity(
                deckId = deckId,
                word = "Expand",
                pronunciation = "/ɪkˈspænd/",
                meaning = "Mở rộng",
                descriptionEn = "To become or make larger or more extensive; to increase in size, scope, or range.",
                example = "The hospital plans to expand its emergency department to serve more patients.",
                collocation = "expand rapidly, expand the scope, expand operations",
                relatedWords = "grow, extend, enlarge, broaden",
                note = "Danh từ là 'expansion'. Trái nghĩa: 'contract' (co lại) hoặc 'shrink' (thu nhỏ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Identify",
                pronunciation = "/aɪˈden.tɪ.faɪ/",
                meaning = "Nhận dạng, xác định",
                descriptionEn = "To establish or indicate who or what someone or something is; to recognize or distinguish.",
                example = "Researchers identified several risk factors for heart disease.",
                collocation = "identify a problem, identify risks, clearly identify",
                relatedWords = "recognize, determine, detect, pinpoint",
                note = "Danh từ là 'identification' (viết tắt: ID). Rất hay dùng trong nghiên cứu y khoa."
            ),
            WordEntity(
                deckId = deckId,
                word = "Ignore",
                pronunciation = "/ɪɡˈnɔːr/",
                meaning = "Phớt lờ, bỏ qua",
                descriptionEn = "To refuse to take notice of or acknowledge; to intentionally disregard.",
                example = "Ignoring early warning signs of a health problem can lead to serious complications.",
                collocation = "ignore advice, ignore the problem, simply ignore",
                relatedWords = "disregard, overlook, neglect, dismiss",
                note = "Danh từ là 'ignorance' (sự thiếu hiểu biết). Tính từ: 'ignorant' (thiếu hiểu biết, không biết)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Impact",
                pronunciation = "/ˈɪm.pækt/",
                meaning = "Ảnh hưởng, tác động",
                descriptionEn = "The effect or influence that an event, situation, or person has on something else.",
                example = "Air pollution has a significant impact on public health and quality of life.",
                collocation = "have an impact, major impact, impact on",
                relatedWords = "effect, influence, consequence, impression",
                note = "Có thể dùng làm danh từ và động từ. 'Make an impact' nghĩa là tạo ảnh hưởng."
            ),
            WordEntity(
                deckId = deckId,
                word = "Imply",
                pronunciation = "/ɪmˈplaɪ/",
                meaning = "Ngụ ý, ám chỉ",
                descriptionEn = "To strongly suggest the truth or existence of something not expressly stated.",
                example = "The doctor implied that the patient needed to make serious lifestyle changes.",
                collocation = "strongly imply, imply that, seem to imply",
                relatedWords = "suggest, hint, indicate, insinuate",
                note = "Danh từ là 'implication'. Phân biệt: 'imply' (người nói ngụ ý) vs 'infer' (người nghe suy luận)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Incorporate",
                pronunciation = "/ɪnˈkɔː.pər.eɪt/",
                meaning = "Tích hợp",
                descriptionEn = "To include something as part of a larger whole; to combine or unite.",
                example = "It is important to incorporate physical activity into your daily routine.",
                collocation = "incorporate into, incorporate elements, fully incorporate",
                relatedWords = "integrate, include, combine, merge",
                note = "Danh từ là 'incorporation'. Trong kinh doanh, 'Inc.' là viết tắt của 'Incorporated' (công ty cổ phần)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Initial",
                pronunciation = "/ɪˈnɪʃ.əl/",
                meaning = "Ban đầu",
                descriptionEn = "Existing or occurring at the beginning; first.",
                example = "The initial symptoms of the illness are often mild and easy to overlook.",
                collocation = "initial stage, initial reaction, initial assessment",
                relatedWords = "first, opening, preliminary, original",
                note = "Trạng từ 'initially' rất hay dùng: 'Initially, the treatment showed no effect.' Danh từ: 'initials' (chữ cái đầu tên)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Interact",
                pronunciation = "/ˌɪn.təˈrækt/",
                meaning = "Tương tác",
                descriptionEn = "To communicate or act in a way that has an effect on others; to influence each other.",
                example = "Social interaction is essential for mental health and emotional well-being.",
                collocation = "interact with, social interaction, interact directly",
                relatedWords = "communicate, engage, relate, connect",
                note = "Danh từ là 'interaction'. Tính từ: 'interactive' (tương tác). Rất phổ biến trong CNTT và tâm lý học."
            ),
            WordEntity(
                deckId = deckId,
                word = "Plausible",
                pronunciation = "/ˈplɔː.zə.bəl/",
                meaning = "Hợp lý, đáng tin",
                descriptionEn = "Seeming reasonable or probable; appearing worthy of belief.",
                example = "The researcher presented a plausible explanation for the unexpected results.",
                collocation = "plausible explanation, plausible argument, sound plausible",
                relatedWords = "reasonable, credible, believable, convincing",
                note = "Trái nghĩa là 'implausible'. Chú ý: 'plausible' không có nghĩa là 'đúng', chỉ là 'nghe có lý'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Paradox",
                pronunciation = "/ˈpær.ə.dɒks/",
                meaning = "Nghịch lý",
                descriptionEn = "A seemingly absurd or contradictory statement or situation that may actually be true.",
                example = "It is a paradox that some people feel lonelier in crowded cities than in rural areas.",
                collocation = "apparent paradox, paradox of, resolve a paradox",
                relatedWords = "contradiction, anomaly, puzzle, irony",
                note = "Tính từ: 'paradoxical'. Ví dụ nổi tiếng: 'The more you know, the more you realize you don't know.'"
            ),
            WordEntity(
                deckId = deckId,
                word = "Obtain",
                pronunciation = "/əbˈteɪn/",
                meaning = "Đạt được, giành được",
                descriptionEn = "To get, acquire, or secure something through effort or a formal process.",
                example = "You must obtain a doctor's approval before starting any new medication.",
                collocation = "obtain permission, obtain information, obtain a degree",
                relatedWords = "acquire, gain, secure, attain",
                note = "Mang tính trang trọng hơn 'get'. Tính từ: 'obtainable' (có thể đạt được)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Vague",
                pronunciation = "/veɪɡ/",
                meaning = "Mơ hồ, không rõ ràng",
                descriptionEn = "Of uncertain, indefinite, or unclear character or meaning; not clearly expressed.",
                example = "The patient gave a vague description of the symptoms, making diagnosis difficult.",
                collocation = "vague idea, vague description, rather vague",
                relatedWords = "unclear, ambiguous, imprecise, indefinite",
                note = "Trạng từ: 'vaguely'. Trái nghĩa: 'precise', 'specific', 'clear'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Biased",
                pronunciation = "/ˈbaɪ.əst/",
                meaning = "Phiến diện, thiên vị",
                descriptionEn = "Unfairly prejudiced for or against someone or something; showing partiality.",
                example = "A biased study cannot be considered reliable evidence for medical decisions.",
                collocation = "biased opinion, biased towards, culturally biased",
                relatedWords = "prejudiced, partial, one-sided, skewed",
                note = "Danh từ là 'bias'. Trái nghĩa: 'unbiased' hoặc 'impartial'. Quan trọng trong nghiên cứu khoa học."
            )
        )
    }

    fun getTechnologyWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Innovate",
                pronunciation = "/ˈɪn.ə.veɪt/",
                meaning = "Đổi mới, cải tiến",
                descriptionEn = "To introduce new methods, ideas, or products; to make changes in something established.",
                example = "Companies that fail to innovate risk falling behind their competitors.",
                collocation = "innovate constantly, innovate in technology, ability to innovate",
                relatedWords = "create, pioneer, revolutionize, modernize",
                note = "Danh từ là 'innovation'. Tính từ: 'innovative' (sáng tạo, đổi mới). Người đổi mới: 'innovator'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Simulate",
                pronunciation = "/ˈsɪm.jə.leɪt/",
                meaning = "Mô phỏng, giả lập",
                descriptionEn = "To imitate the appearance or character of something; to create a computer model of a process.",
                example = "Engineers use software to simulate real-world conditions before building prototypes.",
                collocation = "simulate conditions, simulate a scenario, flight simulator",
                relatedWords = "imitate, replicate, model, emulate",
                note = "Danh từ là 'simulation'. 'Simulator' là thiết bị mô phỏng (ví dụ: flight simulator)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Implement",
                pronunciation = "/ˈɪm.plɪ.ment/",
                meaning = "Thi hành, triển khai",
                descriptionEn = "To put a plan, decision, or agreement into effect; to carry out or execute.",
                example = "The company implemented a new security system to protect user data.",
                collocation = "implement a plan, implement changes, implement a policy",
                relatedWords = "execute, carry out, enforce, apply",
                note = "Danh từ là 'implementation'. Rất phổ biến trong CNTT và quản lý dự án."
            ),
            WordEntity(
                deckId = deckId,
                word = "Domain",
                pronunciation = "/dəˈmeɪn/",
                meaning = "Lĩnh vực, phạm vi",
                descriptionEn = "An area of territory or knowledge; a specified sphere of activity or knowledge.",
                example = "Artificial intelligence is rapidly expanding into every domain of modern life.",
                collocation = "domain name, public domain, domain knowledge",
                relatedWords = "field, area, realm, sphere",
                note = "Trong CNTT, 'domain name' là tên miền website. Trong học thuật, chỉ lĩnh vực chuyên môn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Deviate",
                pronunciation = "/ˈdiː.vi.eɪt/",
                meaning = "Chệch hướng",
                descriptionEn = "To depart from an established course, norm, or standard.",
                example = "The software detected any attempt to deviate from the standard operating procedure.",
                collocation = "deviate from, deviate significantly, deviate from the norm",
                relatedWords = "diverge, stray, depart, differ",
                note = "Danh từ là 'deviation'. 'Standard deviation' là độ lệch chuẩn trong thống kê."
            ),
            WordEntity(
                deckId = deckId,
                word = "Attribute",
                pronunciation = "/əˈtrɪb.juːt/",
                meaning = "Quy cho, thuộc tính",
                descriptionEn = "To regard something as being caused by someone or something; a quality or feature regarded as characteristic.",
                example = "Many experts attribute the success of the product to its user-friendly design.",
                collocation = "attribute to, key attribute, attribute success to",
                relatedWords = "ascribe, credit, assign, characteristic",
                note = "Động từ nhấn âm sau /əˈtrɪb.juːt/, danh từ nhấn âm đầu /ˈæt.rɪ.bjuːt/. Trong lập trình, 'attribute' là thuộc tính."
            ),
            WordEntity(
                deckId = deckId,
                word = "Decisive",
                pronunciation = "/dɪˈsaɪ.sɪv/",
                meaning = "Quyết định, dứt khoát",
                descriptionEn = "Settling an issue or producing a definite result; having the ability to make decisions quickly.",
                example = "The CEO made a decisive move to invest heavily in artificial intelligence.",
                collocation = "decisive action, decisive factor, decisive moment",
                relatedWords = "determined, resolute, conclusive, critical",
                note = "Danh từ là 'decision'. Trái nghĩa: 'indecisive' (do dự, thiếu quyết đoán)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Initiate",
                pronunciation = "/ɪˈnɪʃ.i.eɪt/",
                meaning = "Khởi xướng, bắt đầu",
                descriptionEn = "To cause a process or action to begin; to introduce someone to a new activity.",
                example = "The team initiated a new project to develop a mobile application.",
                collocation = "initiate a project, initiate contact, initiate the process",
                relatedWords = "start, begin, launch, commence",
                note = "Danh từ là 'initiation'. Trang trọng hơn 'start' hoặc 'begin'. Hay dùng trong môi trường công việc."
            ),
            WordEntity(
                deckId = deckId,
                word = "Efficient",
                pronunciation = "/ɪˈfɪʃ.ənt/",
                meaning = "Hiệu quả",
                descriptionEn = "Achieving maximum productivity with minimum wasted effort or expense.",
                example = "The new algorithm is more efficient, reducing processing time by fifty percent.",
                collocation = "energy efficient, highly efficient, efficient use of",
                relatedWords = "effective, productive, economical, streamlined",
                note = "Danh từ là 'efficiency'. Phân biệt: 'efficient' (hiệu quả về cách làm) vs 'effective' (đạt kết quả mong muốn)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Dispatch",
                pronunciation = "/dɪˈspætʃ/",
                meaning = "Gửi đi, vận chuyển",
                descriptionEn = "To send off to a destination or for a purpose; to deal with a task quickly and efficiently.",
                example = "The warehouse dispatched the order within two hours of receiving payment.",
                collocation = "dispatch a message, dispatch goods, dispatch immediately",
                relatedWords = "send, ship, forward, transmit",
                note = "Cũng viết là 'despatch' (Anh-Anh). Danh từ 'dispatch' cũng có nghĩa là bản tin nhanh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Distribute",
                pronunciation = "/dɪˈstrɪb.juːt/",
                meaning = "Phân phối",
                descriptionEn = "To give shares of something to a number of recipients; to spread or supply over an area.",
                example = "The software update was distributed to all users through the cloud platform.",
                collocation = "distribute evenly, distribute resources, distribute information",
                relatedWords = "allocate, share, deliver, dispense",
                note = "Danh từ là 'distribution'. Trong kinh doanh, 'distributor' là nhà phân phối."
            ),
            WordEntity(
                deckId = deckId,
                word = "Install",
                pronunciation = "/ɪnˈstɔːl/",
                meaning = "Lắp đặt, cài đặt",
                descriptionEn = "To place or fix equipment or software in position ready for use.",
                example = "You need to install the latest update to fix the security vulnerability.",
                collocation = "install software, install an app, install equipment",
                relatedWords = "set up, configure, deploy, mount",
                note = "Danh từ là 'installation'. Trái nghĩa: 'uninstall' (gỡ cài đặt). Rất phổ biến trong CNTT."
            ),
            WordEntity(
                deckId = deckId,
                word = "Enterprise",
                pronunciation = "/ˈen.tə.praɪz/",
                meaning = "Doanh nghiệp",
                descriptionEn = "A business or company, especially one that involves risk; a project or undertaking that requires boldness.",
                example = "Many tech enterprises are investing in artificial intelligence research.",
                collocation = "private enterprise, enterprise solution, enterprise software",
                relatedWords = "company, corporation, business, venture",
                note = "Trong CNTT, 'enterprise software' là phần mềm doanh nghiệp. 'Enterprising' (adj) nghĩa là có tinh thần kinh doanh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Feasible",
                pronunciation = "/ˈfiː.zə.bəl/",
                meaning = "Khả thi",
                descriptionEn = "Possible and practical to do easily or conveniently; likely to succeed.",
                example = "The engineers determined that building a bridge at that location was technically feasible.",
                collocation = "feasible solution, feasible option, economically feasible",
                relatedWords = "possible, viable, achievable, practical",
                note = "Danh từ là 'feasibility'. 'Feasibility study' là nghiên cứu khả thi, bước quan trọng trước khi triển khai dự án."
            ),
            WordEntity(
                deckId = deckId,
                word = "Expert",
                pronunciation = "/ˈek.spɜːt/",
                meaning = "Chuyên gia",
                descriptionEn = "A person who has a comprehensive and authoritative knowledge of or skill in a particular area.",
                example = "The company hired a cybersecurity expert to protect their systems from attacks.",
                collocation = "expert advice, expert opinion, subject matter expert",
                relatedWords = "specialist, professional, authority, master",
                note = "Có thể dùng làm danh từ (chuyên gia) và tính từ (thành thạo). Danh từ: 'expertise' (chuyên môn)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Expiry",
                pronunciation = "/ɪkˈspaɪə.ri/",
                meaning = "Hết hạn",
                descriptionEn = "The end of the period for which something is valid; the termination of a fixed period of time.",
                example = "Check the expiry date on your software license before renewing it.",
                collocation = "expiry date, upon expiry, before expiry",
                relatedWords = "expiration, termination, lapse, end",
                note = "'Expiry' dùng phổ biến ở Anh-Anh, 'expiration' ở Anh-Mỹ. Động từ: 'expire' (hết hạn)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Comply",
                pronunciation = "/kəmˈplaɪ/",
                meaning = "Tuân thủ",
                descriptionEn = "To act in accordance with a wish, command, rule, or regulation.",
                example = "All software products must comply with data protection regulations.",
                collocation = "comply with, fail to comply, comply with regulations",
                relatedWords = "obey, conform, adhere, follow",
                note = "Danh từ là 'compliance'. 'Non-compliance' nghĩa là không tuân thủ, có thể dẫn đến bị phạt."
            ),
            WordEntity(
                deckId = deckId,
                word = "Conform",
                pronunciation = "/kənˈfɔːm/",
                meaning = "Tuân theo",
                descriptionEn = "To comply with rules, standards, or laws; to behave according to socially acceptable conventions.",
                example = "The new device conforms to international safety standards.",
                collocation = "conform to standards, conform to rules, conform to expectations",
                relatedWords = "comply, follow, observe, adhere",
                note = "Danh từ là 'conformity'. 'Non-conformist' là người không tuân theo quy ước xã hội."
            ),
            WordEntity(
                deckId = deckId,
                word = "Concede",
                pronunciation = "/kənˈsiːd/",
                meaning = "Thừa nhận",
                descriptionEn = "To admit that something is true or valid after first denying or resisting it.",
                example = "The developer conceded that the software had some critical bugs that needed fixing.",
                collocation = "concede a point, concede defeat, reluctantly concede",
                relatedWords = "admit, acknowledge, accept, grant",
                note = "Danh từ là 'concession'. 'Concede defeat' nghĩa là thừa nhận thất bại."
            ),
            WordEntity(
                deckId = deckId,
                word = "Reject",
                pronunciation = "/rɪˈdʒekt/",
                meaning = "Bác bỏ, từ chối",
                descriptionEn = "To dismiss as inadequate, unacceptable, or faulty; to refuse to accept.",
                example = "The system automatically rejects any input that does not meet the required format.",
                collocation = "reject a proposal, reject an offer, outright reject",
                relatedWords = "decline, refuse, dismiss, deny",
                note = "Danh từ là 'rejection'. Động từ nhấn âm sau /rɪˈdʒekt/, danh từ nhấn âm đầu /ˈriː.dʒekt/."
            ),
            WordEntity(
                deckId = deckId,
                word = "Collaborate",
                pronunciation = "/kəˈlæb.ə.reɪt/",
                meaning = "Hợp tác",
                descriptionEn = "To work jointly with others, especially in an intellectual endeavor or creative project.",
                example = "Developers from different countries collaborate on open-source projects online.",
                collocation = "collaborate with, collaborate on, collaborate closely",
                relatedWords = "cooperate, partner, team up, work together",
                note = "Danh từ là 'collaboration'. Tính từ: 'collaborative'. Công cụ cộng tác: 'collaboration tools'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Accommodate",
                pronunciation = "/əˈkɒm.ə.deɪt/",
                meaning = "Đáp ứng",
                descriptionEn = "To provide lodging or room for; to fit in with the wishes or needs of someone.",
                example = "The new server infrastructure can accommodate up to ten thousand concurrent users.",
                collocation = "accommodate needs, accommodate requests, accommodate changes",
                relatedWords = "cater for, adapt, adjust, house",
                note = "Danh từ là 'accommodation'. Lưu ý chính tả: hai chữ 'c' và hai chữ 'm'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Appraise",
                pronunciation = "/əˈpreɪz/",
                meaning = "Đánh giá, định giá",
                descriptionEn = "To assess the value, quality, or nature of something; to evaluate formally.",
                example = "The team appraised the new technology before deciding to adopt it.",
                collocation = "appraise the value, appraise performance, critically appraise",
                relatedWords = "evaluate, assess, judge, review",
                note = "Danh từ là 'appraisal'. 'Performance appraisal' là đánh giá hiệu suất công việc."
            ),
            WordEntity(
                deckId = deckId,
                word = "Consensus",
                pronunciation = "/kənˈsen.səs/",
                meaning = "Đồng thuận",
                descriptionEn = "A general agreement among a group of people; the collective opinion.",
                example = "The team reached a consensus on the best approach to develop the new feature.",
                collocation = "reach a consensus, general consensus, consensus building",
                relatedWords = "agreement, accord, harmony, unanimity",
                note = "Không có dạng số nhiều. 'General consensus' là sự đồng thuận chung, dù có người cho là thừa (tautology)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Consult",
                pronunciation = "/kənˈsʌlt/",
                meaning = "Tham khảo, tư vấn",
                descriptionEn = "To seek information or advice from someone with expertise; to refer to a source for information.",
                example = "You should consult the technical documentation before making any system changes.",
                collocation = "consult a doctor, consult with, consult an expert",
                relatedWords = "advise, confer, discuss, refer to",
                note = "Danh từ: 'consultation' (buổi tư vấn), 'consultant' (nhà tư vấn). Rất phổ biến trong kinh doanh."
            )
        )
    }

    fun getTravelWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Itinerary",
                pronunciation = "/aɪˈtɪn.ər.ər.i/",
                meaning = "Lịch trình chuyến đi",
                descriptionEn = "A planned route or journey; a detailed plan for a trip including places to visit and times.",
                example = "Our travel agent prepared a detailed itinerary for our two-week trip to Europe.",
                collocation = "travel itinerary, detailed itinerary, plan an itinerary",
                relatedWords = "schedule, route, plan, agenda",
                note = "Phát âm khá khó. Thường dùng khi đi du lịch có kế hoạch. Hay xuất hiện trong IELTS Listening."
            ),
            WordEntity(
                deckId = deckId,
                word = "Destination",
                pronunciation = "/ˌdes.tɪˈneɪ.ʃən/",
                meaning = "Điểm đến",
                descriptionEn = "The place to which someone or something is going or being sent.",
                example = "Bali has become one of the most popular tourist destinations in Southeast Asia.",
                collocation = "tourist destination, popular destination, final destination",
                relatedWords = "location, place, target, endpoint",
                note = "Động từ gốc là 'destine' (định sẵn). 'Destiny' nghĩa là số phận, khác với 'destination'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Hospitality",
                pronunciation = "/ˌhɒs.pɪˈtæl.ə.ti/",
                meaning = "Lòng hiếu khách",
                descriptionEn = "The friendly and generous reception and entertainment of guests, visitors, or strangers.",
                example = "The local people are well known for their warm hospitality toward visitors.",
                collocation = "hospitality industry, show hospitality, hospitality services",
                relatedWords = "welcome, generosity, friendliness, warmth",
                note = "'Hospitality industry' là ngành dịch vụ khách sạn và du lịch. Tính từ: 'hospitable' (hiếu khách)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Souvenir",
                pronunciation = "/ˌsuː.vənˈɪər/",
                meaning = "Quà lưu niệm",
                descriptionEn = "A thing that is kept as a reminder of a person, place, or event; a memento.",
                example = "She bought some beautiful souvenirs from the local market for her family.",
                collocation = "souvenir shop, buy souvenirs, souvenir collection",
                relatedWords = "memento, keepsake, token, reminder",
                note = "Từ gốc tiếng Pháp. Phát âm nhấn âm cuối. Thường bán ở các điểm du lịch."
            ),
            WordEntity(
                deckId = deckId,
                word = "Baggage",
                pronunciation = "/ˈbæɡ.ɪdʒ/",
                meaning = "Hành lý",
                descriptionEn = "Personal belongings packed in suitcases for traveling; luggage.",
                example = "Passengers are allowed to carry one piece of baggage into the cabin.",
                collocation = "baggage claim, excess baggage, baggage allowance",
                relatedWords = "luggage, suitcase, bags, belongings",
                note = "Danh từ không đếm được. 'Baggage claim' là khu nhận hành lý tại sân bay. Nghĩa bóng: gánh nặng tâm lý."
            ),
            WordEntity(
                deckId = deckId,
                word = "Cuisine",
                pronunciation = "/kwɪˈziːn/",
                meaning = "Ẩm thực",
                descriptionEn = "A style or method of cooking, especially as characteristic of a particular country or region.",
                example = "Vietnamese cuisine is famous for its fresh ingredients and balanced flavors.",
                collocation = "local cuisine, traditional cuisine, French cuisine",
                relatedWords = "food, cooking, gastronomy, dishes",
                note = "Từ gốc tiếng Pháp. Dùng để chỉ phong cách ẩm thực đặc trưng của một vùng miền hoặc quốc gia."
            ),
            WordEntity(
                deckId = deckId,
                word = "Customs",
                pronunciation = "/ˈkʌs.təmz/",
                meaning = "Hải quan",
                descriptionEn = "The place at a port, airport, or border where officials check incoming goods, travelers, or luggage.",
                example = "All travelers must pass through customs when arriving from an international flight.",
                collocation = "customs officer, customs declaration, go through customs",
                relatedWords = "border control, immigration, checkpoint, inspection",
                note = "Luôn dùng dạng số nhiều khi chỉ hải quan. 'Custom' (số ít) nghĩa là phong tục, tập quán."
            ),
            WordEntity(
                deckId = deckId,
                word = "Delay",
                pronunciation = "/dɪˈleɪ/",
                meaning = "Trì hoãn",
                descriptionEn = "To make something or someone late or slow; a period of time by which something is late.",
                example = "The flight was delayed by three hours due to severe weather conditions.",
                collocation = "flight delay, without delay, delay the process",
                relatedWords = "postpone, defer, hold up, stall",
                note = "Có thể dùng làm danh từ và động từ. 'Without delay' nghĩa là ngay lập tức, không chậm trễ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Embassy",
                pronunciation = "/ˈem.bə.si/",
                meaning = "Đại sứ quán",
                descriptionEn = "The official residence or offices of an ambassador representing their country in a foreign nation.",
                example = "You should contact the embassy if you lose your passport while traveling abroad.",
                collocation = "contact the embassy, embassy staff, foreign embassy",
                relatedWords = "consulate, diplomatic mission, legation, mission",
                note = "Phân biệt: 'embassy' (đại sứ quán - cấp cao nhất) vs 'consulate' (lãnh sự quán - cấp thấp hơn)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Excursion",
                pronunciation = "/ɪkˈskɜː.ʃən/",
                meaning = "Chuyến đi chơi ngắn",
                descriptionEn = "A short journey or trip, especially one taken for leisure or as part of a longer journey.",
                example = "The hotel offers daily excursions to nearby islands and historical sites.",
                collocation = "day excursion, boat excursion, go on an excursion",
                relatedWords = "trip, outing, tour, expedition",
                note = "Thường là chuyến đi ngắn trong ngày hoặc vài giờ, khác với 'trip' hay 'journey' (dài hơn)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Fare",
                pronunciation = "/feər/",
                meaning = "Giá vé",
                descriptionEn = "The money a passenger on public transport has to pay for the journey.",
                example = "Bus fares have increased significantly over the past year.",
                collocation = "bus fare, airfare, fare increase",
                relatedWords = "ticket price, charge, cost, fee",
                note = "Chỉ giá vé phương tiện giao thông. 'Airfare' là giá vé máy bay. Đồng âm với 'fair' (công bằng)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Heritage",
                pronunciation = "/ˈher.ɪ.tɪdʒ/",
                meaning = "Di sản",
                descriptionEn = "Valued objects and qualities such as historic buildings and cultural traditions passed down from previous generations.",
                example = "The old town was designated as a UNESCO World Heritage Site.",
                collocation = "cultural heritage, world heritage, heritage site",
                relatedWords = "legacy, tradition, inheritance, patrimony",
                note = "'World Heritage Site' là Di sản Thế giới (UNESCO). Việt Nam có nhiều di sản thế giới như Vịnh Hạ Long."
            ),
            WordEntity(
                deckId = deckId,
                word = "Passenger",
                pronunciation = "/ˈpæs.ən.dʒər/",
                meaning = "Hành khách",
                descriptionEn = "A traveler on a public or private conveyance other than the driver, pilot, or crew.",
                example = "The airline can accommodate up to three hundred passengers per flight.",
                collocation = "passenger seat, passenger train, airline passenger",
                relatedWords = "traveler, commuter, rider, voyager",
                note = "Phân biệt: 'passenger' (hành khách trên phương tiện) vs 'pedestrian' (người đi bộ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Passport",
                pronunciation = "/ˈpɑːs.pɔːt/",
                meaning = "Hộ chiếu",
                descriptionEn = "An official document issued by a government, certifying the holder's identity and citizenship for international travel.",
                example = "Make sure your passport is valid for at least six months before your trip.",
                collocation = "passport control, passport holder, valid passport",
                relatedWords = "visa, identification, travel document, ID",
                note = "Cần kiểm tra hạn passport trước khi đi nước ngoài. 'Passport control' là cửa kiểm tra hộ chiếu."
            ),
            WordEntity(
                deckId = deckId,
                word = "Resort",
                pronunciation = "/rɪˈzɔːt/",
                meaning = "Khu nghỉ dưỡng",
                descriptionEn = "A place that is frequented for holidays or recreation; a popular vacation destination with amenities.",
                example = "We spent a relaxing week at a beach resort on the coast of Thailand.",
                collocation = "beach resort, luxury resort, resort hotel",
                relatedWords = "hotel, retreat, spa, lodge",
                note = "Động từ 'resort to' nghĩa là viện đến, dùng đến (biện pháp cuối cùng). Nghĩa hoàn toàn khác danh từ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Sightseeing",
                pronunciation = "/ˈsaɪtˌsiː.ɪŋ/",
                meaning = "Tham quan",
                descriptionEn = "The activity of visiting places of interest in a particular location as a tourist.",
                example = "We spent the whole day sightseeing in the historic center of the city.",
                collocation = "go sightseeing, sightseeing tour, sightseeing bus",
                relatedWords = "touring, exploring, visiting, excursion",
                note = "Là danh từ không đếm được. 'Go sightseeing' là cụm từ cố định, không nói 'do sightseeing'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Transit",
                pronunciation = "/ˈtræn.zɪt/",
                meaning = "Quá cảnh",
                descriptionEn = "The carrying of people or goods from one place to another; the act of passing through a place on the way to a destination.",
                example = "The airport has a comfortable lounge for passengers in transit.",
                collocation = "in transit, public transit, transit visa",
                relatedWords = "transfer, transport, passage, journey",
                note = "'In transit' nghĩa là đang trên đường vận chuyển. 'Transit visa' là visa quá cảnh."
            ),
            WordEntity(
                deckId = deckId,
                word = "Visa",
                pronunciation = "/ˈviː.zə/",
                meaning = "Thị thực",
                descriptionEn = "An endorsement on a passport indicating that the holder is allowed to enter, leave, or stay in a country.",
                example = "You need to apply for a tourist visa at least one month before your departure date.",
                collocation = "apply for a visa, visa application, tourist visa",
                relatedWords = "permit, authorization, entry clearance, travel document",
                note = "Các loại visa phổ biến: tourist visa, student visa, work visa, transit visa."
            ),
            WordEntity(
                deckId = deckId,
                word = "Adventure",
                pronunciation = "/ədˈven.tʃər/",
                meaning = "Cuộc phiêu lưu",
                descriptionEn = "An unusual and exciting experience or activity, often involving risk or the unknown.",
                example = "Backpacking through Southeast Asia was the greatest adventure of her life.",
                collocation = "adventure travel, adventure sports, sense of adventure",
                relatedWords = "expedition, journey, quest, exploration",
                note = "Tính từ: 'adventurous' (thích phiêu lưu). 'Adventure tourism' là du lịch mạo hiểm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Commute",
                pronunciation = "/kəˈmjuːt/",
                meaning = "Đi lại hàng ngày",
                descriptionEn = "To travel some distance between one's home and place of work on a regular basis.",
                example = "She commutes by train from the suburbs to the city center every day.",
                collocation = "daily commute, commute to work, long commute",
                relatedWords = "travel, journey, ride, shuttle",
                note = "Danh từ 'commuter' là người đi làm hàng ngày bằng phương tiện công cộng."
            ),
            WordEntity(
                deckId = deckId,
                word = "Enclose",
                pronunciation = "/ɪnˈkləʊz/",
                meaning = "Đính kèm",
                descriptionEn = "To place something in an envelope together with a letter; to surround or close off on all sides.",
                example = "Please find enclosed a copy of our travel brochure for your reference.",
                collocation = "enclosed area, please find enclosed, enclose a document",
                relatedWords = "attach, include, insert, surround",
                note = "Trong thư từ trang trọng: 'Please find enclosed...' (Xin gửi kèm...). 'Enclosure' là tài liệu đính kèm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inquire",
                pronunciation = "/ɪnˈkwaɪər/",
                meaning = "Hỏi thăm",
                descriptionEn = "To ask for information from someone; to investigate or look into something.",
                example = "Guests may inquire at the front desk about local tour packages.",
                collocation = "inquire about, inquire into, inquire within",
                relatedWords = "ask, question, query, investigate",
                note = "Cũng viết là 'enquire' (Anh-Anh). Danh từ: 'inquiry' hoặc 'enquiry'. 'Inquire about' = hỏi về."
            ),
            WordEntity(
                deckId = deckId,
                word = "Inspect",
                pronunciation = "/ɪnˈspekt/",
                meaning = "Thanh tra, kiểm tra",
                descriptionEn = "To look at something closely and carefully in order to assess its condition or to discover faults.",
                example = "Airport security officers inspect all luggage before boarding.",
                collocation = "inspect carefully, inspect the premises, inspect goods",
                relatedWords = "examine, check, review, scrutinize",
                note = "Danh từ: 'inspection' (cuộc kiểm tra), 'inspector' (thanh tra viên). Mang tính chính thức, kỹ lưỡng."
            ),
            WordEntity(
                deckId = deckId,
                word = "Customer",
                pronunciation = "/ˈkʌs.tə.mər/",
                meaning = "Khách hàng",
                descriptionEn = "A person or organization that buys goods or services from a store or business.",
                example = "The hotel prides itself on providing excellent customer service to all guests.",
                collocation = "customer service, customer satisfaction, loyal customer",
                relatedWords = "client, patron, buyer, consumer",
                note = "Phân biệt: 'customer' (mua hàng/dịch vụ), 'client' (dùng dịch vụ chuyên nghiệp), 'consumer' (người tiêu dùng cuối)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Adjourn",
                pronunciation = "/əˈdʒɜːn/",
                meaning = "Hoãn lại, tạm dừng",
                descriptionEn = "To break off a meeting or legal proceeding with the intention of resuming it later.",
                example = "The conference was adjourned until the following morning due to the late hour.",
                collocation = "adjourn a meeting, adjourn until, adjourn the session",
                relatedWords = "postpone, suspend, defer, recess",
                note = "Danh từ là 'adjournment'. Thường dùng trong ngữ cảnh họp hành, tòa án, hội nghị."
            )
        )
    }

    fun getWorkWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Candidate",
                pronunciation = "/ˈkæn.dɪ.dət/",
                meaning = "Ứng viên",
                descriptionEn = "A person who applies for a job or is nominated for an award or position.",
                example = "The company received over two hundred applications from qualified candidates.",
                collocation = "ideal candidate, candidate for, shortlist candidates",
                relatedWords = "applicant, contender, nominee, prospect",
                note = "Danh từ 'candidacy' là việc ứng cử. Thường dùng trong tuyển dụng và bầu cử."
            ),
            WordEntity(
                deckId = deckId,
                word = "Deadline",
                pronunciation = "/ˈded.laɪn/",
                meaning = "Hạn chót",
                descriptionEn = "The latest time or date by which something should be completed.",
                example = "The deadline for submitting the project report is next Friday.",
                collocation = "meet a deadline, miss a deadline, tight deadline",
                relatedWords = "due date, time limit, cutoff, target date",
                note = "'Meet a deadline' (hoàn thành đúng hạn) vs 'miss a deadline' (trễ hạn). Rất phổ biến trong công việc."
            ),
            WordEntity(
                deckId = deckId,
                word = "Delegate",
                pronunciation = "/ˈdel.ɪ.ɡət/",
                meaning = "Đại biểu, ủy quyền",
                descriptionEn = "A person sent to represent others; to entrust a task or responsibility to another person.",
                example = "A good manager knows how to delegate tasks effectively to team members.",
                collocation = "delegate responsibility, delegate tasks, delegate authority",
                relatedWords = "representative, assign, entrust, authorize",
                note = "Danh từ nhấn âm đầu /ˈdel.ɪ.ɡət/, động từ nhấn âm cuối /ˈdel.ɪ.ɡeɪt/. Danh từ: 'delegation'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Demand",
                pronunciation = "/dɪˈmɑːnd/",
                meaning = "Nhu cầu, yêu cầu",
                descriptionEn = "An insistent and peremptory request; the desire of consumers for a particular commodity or service.",
                example = "There is a growing demand for skilled workers in the technology sector.",
                collocation = "high demand, meet demand, demand for",
                relatedWords = "request, requirement, need, claim",
                note = "Trong kinh tế: 'supply and demand' (cung và cầu). Tính từ: 'demanding' (đòi hỏi cao)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Employ",
                pronunciation = "/ɪmˈplɔɪ/",
                meaning = "Thuê, tuyển dụng",
                descriptionEn = "To give work to someone and pay them for it; to make use of something.",
                example = "The factory employs over five hundred workers from the local community.",
                collocation = "employ staff, employ a strategy, employ workers",
                relatedWords = "hire, recruit, engage, utilize",
                note = "Danh từ: 'employment' (việc làm), 'employer' (người sử dụng lao động), 'employee' (nhân viên)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Executive",
                pronunciation = "/ɪɡˈzek.jə.tɪv/",
                meaning = "Giám đốc điều hành",
                descriptionEn = "A person with senior managerial responsibility in a business organization; relating to executing plans or laws.",
                example = "The chief executive officer announced a major restructuring of the company.",
                collocation = "executive director, senior executive, executive decision",
                relatedWords = "manager, director, administrator, officer",
                note = "CEO = Chief Executive Officer (Giám đốc điều hành). Có thể dùng làm danh từ và tính từ."
            ),
            WordEntity(
                deckId = deckId,
                word = "Agenda",
                pronunciation = "/əˈdʒen.də/",
                meaning = "Chương trình nghị sự",
                descriptionEn = "A list of items to be discussed or acted upon at a meeting; a plan of things to be done.",
                example = "The first item on the agenda is the quarterly sales report.",
                collocation = "set the agenda, on the agenda, hidden agenda",
                relatedWords = "schedule, program, plan, itinerary",
                note = "'Hidden agenda' nghĩa là mục đích ẩn giấu. Thường dùng trong bối cảnh họp hành, hội nghị."
            ),
            WordEntity(
                deckId = deckId,
                word = "Initiative",
                pronunciation = "/ɪˈnɪʃ.ə.tɪv/",
                meaning = "Sáng kiến",
                descriptionEn = "The ability to assess and initiate things independently; a new plan or process to achieve something.",
                example = "The company launched a new initiative to promote employee wellness.",
                collocation = "take the initiative, government initiative, new initiative",
                relatedWords = "enterprise, drive, proposal, scheme",
                note = "'Take the initiative' nghĩa là chủ động làm gì đó. Thể hiện sự tích cực trong công việc."
            ),
            WordEntity(
                deckId = deckId,
                word = "Mentor",
                pronunciation = "/ˈmen.tɔːr/",
                meaning = "Người hướng dẫn",
                descriptionEn = "An experienced and trusted adviser who guides and supports someone less experienced.",
                example = "She credited her mentor for helping her develop the skills needed for the promotion.",
                collocation = "mentor and mentee, act as a mentor, mentor program",
                relatedWords = "adviser, guide, coach, tutor",
                note = "Người được hướng dẫn là 'mentee'. 'Mentorship' là quá trình cố vấn, hướng dẫn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Supervise",
                pronunciation = "/ˈsuː.pə.vaɪz/",
                meaning = "Giám sát",
                descriptionEn = "To observe and direct the execution of a task or activity; to oversee someone's work.",
                example = "The project manager supervises a team of twenty engineers.",
                collocation = "supervise staff, supervise the work, closely supervise",
                relatedWords = "oversee, manage, direct, monitor",
                note = "Danh từ: 'supervision' (sự giám sát), 'supervisor' (người giám sát). Rất phổ biến trong quản lý."
            ),
            WordEntity(
                deckId = deckId,
                word = "Resign",
                pronunciation = "/rɪˈzaɪn/",
                meaning = "Từ chức",
                descriptionEn = "To voluntarily leave a job or position; to accept that something undesirable cannot be avoided.",
                example = "The director resigned from her position after ten years with the company.",
                collocation = "resign from, resign as, forced to resign",
                relatedWords = "quit, step down, leave, retire",
                note = "Danh từ là 'resignation'. 'Resign oneself to' nghĩa là cam chịu, chấp nhận điều không mong muốn."
            ),
            WordEntity(
                deckId = deckId,
                word = "Promote",
                pronunciation = "/prəˈməʊt/",
                meaning = "Thăng chức, quảng bá",
                descriptionEn = "To advance someone to a higher position or rank; to support or actively encourage something.",
                example = "She was promoted to senior manager after demonstrating outstanding leadership.",
                collocation = "promote to, promote growth, promote awareness",
                relatedWords = "advance, elevate, advertise, boost",
                note = "Danh từ: 'promotion' (thăng chức/khuyến mãi). Hai nghĩa chính: thăng chức và quảng bá sản phẩm."
            ),
            WordEntity(
                deckId = deckId,
                word = "Recruit",
                pronunciation = "/rɪˈkruːt/",
                meaning = "Tuyển dụng",
                descriptionEn = "To enroll someone as a member or worker in an organization; to find new people to join.",
                example = "The company plans to recruit fifty new graduates for the engineering department.",
                collocation = "recruit staff, recruit new members, recruit talent",
                relatedWords = "hire, enlist, employ, engage",
                note = "Danh từ: 'recruitment' (tuyển dụng), 'recruiter' (nhà tuyển dụng). Rất phổ biến trong HR."
            ),
            WordEntity(
                deckId = deckId,
                word = "Qualify",
                pronunciation = "/ˈkwɒl.ɪ.faɪ/",
                meaning = "Đủ điều kiện",
                descriptionEn = "To be entitled to a particular benefit or privilege by fulfilling a necessary condition.",
                example = "You need at least three years of experience to qualify for this senior position.",
                collocation = "qualify for, fully qualified, qualify as",
                relatedWords = "certify, entitle, meet requirements, be eligible",
                note = "Danh từ: 'qualification' (bằng cấp, tiêu chuẩn). Tính từ: 'qualified' (đủ trình độ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Punctual",
                pronunciation = "/ˈpʌŋk.tʃu.əl/",
                meaning = "Đúng giờ",
                descriptionEn = "Happening or doing something at the agreed or proper time; on time.",
                example = "Employees are expected to be punctual and arrive at work by nine o'clock.",
                collocation = "be punctual, punctual arrival, always punctual",
                relatedWords = "prompt, on time, timely, reliable",
                note = "Danh từ là 'punctuality'. Tính đúng giờ được đánh giá cao trong môi trường công sở."
            ),
            WordEntity(
                deckId = deckId,
                word = "Proficient",
                pronunciation = "/prəˈfɪʃ.ənt/",
                meaning = "Thành thạo",
                descriptionEn = "Competent or skilled in doing or using something; having a high degree of ability.",
                example = "Applicants must be proficient in at least two programming languages.",
                collocation = "proficient in, highly proficient, proficient at",
                relatedWords = "skilled, competent, adept, capable",
                note = "Danh từ là 'proficiency'. 'Language proficiency' là trình độ ngôn ngữ. Dùng với 'in' hoặc 'at'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Productivity",
                pronunciation = "/ˌprɒd.ʌkˈtɪv.ə.ti/",
                meaning = "Năng suất",
                descriptionEn = "The effectiveness of productive effort, especially in terms of the rate of output per unit of input.",
                example = "The new workflow system increased team productivity by thirty percent.",
                collocation = "increase productivity, boost productivity, productivity level",
                relatedWords = "efficiency, output, performance, yield",
                note = "Tính từ: 'productive' (năng suất cao). Trái nghĩa: 'unproductive'. Chủ đề thường gặp trong IELTS."
            ),
            WordEntity(
                deckId = deckId,
                word = "Authorize",
                pronunciation = "/ˈɔː.θər.aɪz/",
                meaning = "Ủy quyền, cho phép",
                descriptionEn = "To give official permission for or approval to an action or plan.",
                example = "Only the department head is authorized to approve budget requests over one thousand dollars.",
                collocation = "authorize access, authorize payment, duly authorized",
                relatedWords = "approve, permit, sanction, empower",
                note = "Danh từ: 'authorization' (sự ủy quyền). Tính từ: 'authorized' (được ủy quyền). Rất dùng nhiều trong CNTT."
            ),
            WordEntity(
                deckId = deckId,
                word = "Coordinate",
                pronunciation = "/kəʊˈɔː.dɪ.neɪt/",
                meaning = "Phối hợp",
                descriptionEn = "To organize the different elements of a complex activity so as to enable them to work together effectively.",
                example = "The manager coordinated the efforts of multiple teams to deliver the project on time.",
                collocation = "coordinate efforts, coordinate with, coordinate activities",
                relatedWords = "organize, arrange, synchronize, harmonize",
                note = "Danh từ là 'coordination'. Người phối hợp: 'coordinator'. Rất quan trọng trong quản lý dự án."
            ),
            WordEntity(
                deckId = deckId,
                word = "Competent",
                pronunciation = "/ˈkɒm.pɪ.tənt/",
                meaning = "Có năng lực",
                descriptionEn = "Having the necessary ability, knowledge, or skill to do something successfully.",
                example = "We need a competent leader who can guide the team through this challenging period.",
                collocation = "competent professional, highly competent, competent in",
                relatedWords = "capable, skilled, proficient, qualified",
                note = "Danh từ là 'competence' hoặc 'competency'. Trái nghĩa: 'incompetent' (bất tài)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Mandatory",
                pronunciation = "/ˈmæn.də.tər.i/",
                meaning = "Bắt buộc",
                descriptionEn = "Required by law or rules; compulsory and not optional.",
                example = "Attendance at the safety training session is mandatory for all new employees.",
                collocation = "mandatory requirement, mandatory training, mandatory reporting",
                relatedWords = "compulsory, obligatory, required, essential",
                note = "Danh từ gốc là 'mandate' (ủy nhiệm, lệnh). Trái nghĩa: 'optional' (tùy chọn), 'voluntary' (tự nguyện)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Terminate",
                pronunciation = "/ˈtɜː.mɪ.neɪt/",
                meaning = "Chấm dứt",
                descriptionEn = "To bring to an end; to end the employment of someone.",
                example = "The company decided to terminate the contract due to repeated violations.",
                collocation = "terminate a contract, terminate employment, terminate the agreement",
                relatedWords = "end, conclude, cancel, discontinue",
                note = "Danh từ là 'termination'. Mang tính trang trọng và chính thức hơn 'end' hoặc 'stop'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Overtime",
                pronunciation = "/ˈəʊ.və.taɪm/",
                meaning = "Làm thêm giờ",
                descriptionEn = "Time worked in addition to one's normal working hours; extra time beyond the standard schedule.",
                example = "Many employees had to work overtime to meet the project deadline.",
                collocation = "work overtime, overtime pay, overtime hours",
                relatedWords = "extra hours, additional time, extended hours, supplementary work",
                note = "'Overtime pay' là tiền làm thêm giờ, thường cao hơn lương bình thường (1.5x hoặc 2x)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Probation",
                pronunciation = "/prəˈbeɪ.ʃən/",
                meaning = "Thử việc",
                descriptionEn = "A period of time during which a new employee is tested to see if they are suitable for the job.",
                example = "New employees must complete a three-month probation period before receiving a permanent contract.",
                collocation = "probation period, on probation, probationary period",
                relatedWords = "trial period, test period, evaluation period, apprenticeship",
                note = "Tính từ: 'probationary'. Trong pháp luật, 'probation' còn nghĩa là quản chế (tội nhẹ)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Welfare",
                pronunciation = "/ˈwel.feər/",
                meaning = "Phúc lợi",
                descriptionEn = "The health, happiness, and fortunes of a person or group; government assistance for those in need.",
                example = "The company provides excellent welfare benefits including health insurance and paid leave.",
                collocation = "employee welfare, social welfare, welfare program",
                relatedWords = "well-being, benefit, aid, assistance",
                note = "'Social welfare' là phúc lợi xã hội. 'Welfare state' là nhà nước phúc lợi (cung cấp dịch vụ công cho dân)."
            )
        )
    }

    fun getSocietyWords(deckId: Long): List<WordEntity> {
        return listOf(
            WordEntity(
                deckId = deckId,
                word = "Advocate",
                pronunciation = "/ˈæd.və.keɪt/",
                meaning = "Ủng hộ, người biện hộ",
                descriptionEn = "To publicly recommend or support a cause or policy; a person who supports or speaks in favor of something.",
                example = "She has been a strong advocate for equal rights in the workplace.",
                collocation = "advocate for, strong advocate, advocate change",
                relatedWords = "supporter, champion, proponent, promoter",
                note = "Danh từ nhấn âm đầu /ˈæd.və.kət/, động từ nhấn âm cuối /ˈæd.və.keɪt/. Danh từ: 'advocacy'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Intervene",
                pronunciation = "/ˌɪn.təˈviːn/",
                meaning = "Can thiệp",
                descriptionEn = "To come between so as to prevent or alter a result or course of events; to take part in something to change the outcome.",
                example = "The government decided to intervene in the economic crisis to prevent further damage.",
                collocation = "intervene in, military intervention, intervene on behalf of",
                relatedWords = "interfere, mediate, step in, intercede",
                note = "Danh từ là 'intervention'. 'Military intervention' là can thiệp quân sự. Hay dùng trong chính trị quốc tế."
            ),
            WordEntity(
                deckId = deckId,
                word = "Predominant",
                pronunciation = "/prɪˈdɒm.ɪ.nənt/",
                meaning = "Chiếm ưu thế",
                descriptionEn = "Present as the strongest or main element; having greater influence or authority than others.",
                example = "English is the predominant language used in international business and diplomacy.",
                collocation = "predominant role, predominant factor, predominant culture",
                relatedWords = "dominant, prevailing, chief, principal",
                note = "Trạng từ 'predominantly' rất hay dùng. Động từ: 'predominate' (chiếm ưu thế)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Controversial",
                pronunciation = "/ˌkɒn.trəˈvɜː.ʃəl/",
                meaning = "Gây tranh cãi",
                descriptionEn = "Giving rise to public disagreement or heated debate; likely to cause argument.",
                example = "The new immigration policy has been highly controversial among both politicians and citizens.",
                collocation = "controversial issue, controversial topic, highly controversial",
                relatedWords = "debatable, contentious, disputed, divisive",
                note = "Danh từ là 'controversy'. Chủ đề gây tranh cãi là dạng bài thường gặp trong IELTS Writing Task 2."
            ),
            WordEntity(
                deckId = deckId,
                word = "Legitimate",
                pronunciation = "/lɪˈdʒɪt.ɪ.mət/",
                meaning = "Hợp pháp",
                descriptionEn = "Conforming to the law or to rules; able to be defended with logic or justification.",
                example = "Citizens have the legitimate right to protest peacefully against unjust laws.",
                collocation = "legitimate concern, legitimate reason, legitimate government",
                relatedWords = "lawful, legal, valid, justified",
                note = "Danh từ: 'legitimacy'. Động từ: 'legitimize' (hợp pháp hóa). Trái nghĩa: 'illegitimate'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Prohibit",
                pronunciation = "/prəˈhɪb.ɪt/",
                meaning = "Cấm",
                descriptionEn = "To formally forbid something by law, rule, or authority; to prevent from doing something.",
                example = "The law prohibits discrimination based on race, gender, or religion.",
                collocation = "strictly prohibited, prohibit the use of, prohibit from",
                relatedWords = "ban, forbid, bar, outlaw",
                note = "Danh từ là 'prohibition'. Lịch sử: 'Prohibition era' ở Mỹ (1920-1933) cấm sản xuất và bán rượu."
            ),
            WordEntity(
                deckId = deckId,
                word = "Violate",
                pronunciation = "/ˈvaɪ.ə.leɪt/",
                meaning = "Vi phạm",
                descriptionEn = "To break or fail to comply with a rule, law, or formal agreement.",
                example = "Any company that violates environmental regulations will face severe penalties.",
                collocation = "violate the law, violate human rights, violate an agreement",
                relatedWords = "breach, break, infringe, transgress",
                note = "Danh từ là 'violation'. 'Human rights violation' là vi phạm nhân quyền - chủ đề nóng toàn cầu."
            ),
            WordEntity(
                deckId = deckId,
                word = "Democracy",
                pronunciation = "/dɪˈmɒk.rə.si/",
                meaning = "Dân chủ",
                descriptionEn = "A system of government in which power is vested in the people, who rule either directly or through elected representatives.",
                example = "Many countries around the world are striving to strengthen their democracy.",
                collocation = "democratic society, promote democracy, democratic process",
                relatedWords = "republic, freedom, self-governance, liberty",
                note = "Tính từ: 'democratic'. Trái nghĩa: 'dictatorship' (chế độ độc tài), 'autocracy' (chế độ chuyên quyền)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Discriminate",
                pronunciation = "/dɪˈskrɪm.ɪ.neɪt/",
                meaning = "Phân biệt đối xử",
                descriptionEn = "To make an unjust distinction in the treatment of different categories of people, especially on grounds of race, age, or sex.",
                example = "It is illegal to discriminate against employees based on their age or gender.",
                collocation = "discriminate against, racial discrimination, discriminate between",
                relatedWords = "prejudice, bias, segregate, differentiate",
                note = "Danh từ là 'discrimination'. Cũng có nghĩa tích cực: phân biệt, nhận ra sự khác nhau."
            ),
            WordEntity(
                deckId = deckId,
                word = "Emigrate",
                pronunciation = "/ˈem.ɪ.ɡreɪt/",
                meaning = "Di cư ra nước ngoài",
                descriptionEn = "To leave one's own country in order to settle permanently in another.",
                example = "Thousands of people emigrate from developing countries in search of better opportunities.",
                collocation = "emigrate from, emigrate to, choose to emigrate",
                relatedWords = "migrate, relocate, move abroad, resettle",
                note = "Phân biệt: 'emigrate' (rời khỏi nước mình), 'immigrate' (đến nước mới), 'migrate' (di cư nói chung)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Abolish",
                pronunciation = "/əˈbɒl.ɪʃ/",
                meaning = "Bãi bỏ",
                descriptionEn = "To formally put an end to a system, practice, or institution.",
                example = "The movement fought to abolish slavery and establish equal rights for all citizens.",
                collocation = "abolish slavery, abolish the death penalty, abolish a law",
                relatedWords = "eliminate, eradicate, annul, revoke",
                note = "Danh từ là 'abolition'. 'Abolitionist' là người đấu tranh bãi bỏ (thường chỉ bãi bỏ nô lệ trong lịch sử)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Constitute",
                pronunciation = "/ˈkɒn.stɪ.tjuːt/",
                meaning = "Cấu thành",
                descriptionEn = "To be a part of a whole; to form or compose something.",
                example = "Young people under thirty constitute nearly half of the country's population.",
                collocation = "constitute a threat, constitute a majority, constitute a violation",
                relatedWords = "comprise, form, make up, compose",
                note = "Danh từ: 'constitution' (hiến pháp). Tính từ: 'constitutional' (hợp hiến). Rất quan trọng trong luật pháp."
            ),
            WordEntity(
                deckId = deckId,
                word = "Legislation",
                pronunciation = "/ˌledʒ.ɪˈsleɪ.ʃən/",
                meaning = "Pháp luật, luật pháp",
                descriptionEn = "Laws considered collectively; the process of making or enacting laws.",
                example = "New legislation was introduced to protect citizens' personal data online.",
                collocation = "introduce legislation, pass legislation, draft legislation",
                relatedWords = "law, regulation, statute, act",
                note = "Không đếm được. Động từ: 'legislate'. Người lập pháp: 'legislator'. Cơ quan: 'legislature'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Authority",
                pronunciation = "/ɔːˈθɒr.ə.ti/",
                meaning = "Chính quyền, thẩm quyền",
                descriptionEn = "The power or right to give orders, make decisions, and enforce obedience; a person or organization having political or administrative power.",
                example = "Local authorities are responsible for maintaining public infrastructure and services.",
                collocation = "local authority, authority figure, exercise authority",
                relatedWords = "power, control, jurisdiction, governance",
                note = "Số nhiều 'authorities' thường chỉ cơ quan chức năng. Tính từ: 'authoritative' (có thẩm quyền, đáng tin)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Reform",
                pronunciation = "/rɪˈfɔːm/",
                meaning = "Cải cách",
                descriptionEn = "To make changes in something, typically a social, political, or economic institution, in order to improve it.",
                example = "The government implemented significant reforms to the healthcare system.",
                collocation = "political reform, economic reform, reform the system",
                relatedWords = "change, improve, restructure, overhaul",
                note = "Có thể dùng làm danh từ và động từ. 'Reformer' là nhà cải cách. 'Reformation' là cuộc cải cách (lịch sử)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Protest",
                pronunciation = "/ˈprəʊ.test/",
                meaning = "Biểu tình, phản đối",
                descriptionEn = "A statement or action expressing disapproval of or objection to something; a public demonstration of opposition.",
                example = "Thousands of citizens took to the streets to protest against the new tax policy.",
                collocation = "peaceful protest, protest against, stage a protest",
                relatedWords = "demonstration, rally, march, objection",
                note = "Danh từ nhấn âm đầu /ˈprəʊ.test/, động từ nhấn âm sau /prəˈtest/. 'Protester' là người biểu tình."
            ),
            WordEntity(
                deckId = deckId,
                word = "Oppress",
                pronunciation = "/əˈpres/",
                meaning = "Áp bức",
                descriptionEn = "To keep someone in subservience and hardship, especially by the unjust exercise of authority.",
                example = "Throughout history, many groups have been oppressed based on their ethnicity or beliefs.",
                collocation = "oppress the people, feel oppressed, oppressive regime",
                relatedWords = "suppress, subjugate, persecute, tyrannize",
                note = "Danh từ: 'oppression' (sự áp bức). Tính từ: 'oppressive' (áp bức, ngột ngạt). 'Oppressor' là kẻ áp bức."
            ),
            WordEntity(
                deckId = deckId,
                word = "Propaganda",
                pronunciation = "/ˌprɒp.əˈɡæn.də/",
                meaning = "Tuyên truyền",
                descriptionEn = "Information, especially of a biased or misleading nature, used to promote a political cause or point of view.",
                example = "The regime used propaganda to control public opinion and suppress dissent.",
                collocation = "political propaganda, spread propaganda, propaganda campaign",
                relatedWords = "misinformation, indoctrination, brainwashing, publicity",
                note = "Thường mang nghĩa tiêu cực trong tiếng Anh, chỉ thông tin sai lệch có chủ đích."
            ),
            WordEntity(
                deckId = deckId,
                word = "Sanction",
                pronunciation = "/ˈsæŋk.ʃən/",
                meaning = "Lệnh trừng phạt",
                descriptionEn = "A penalty for disobeying a law or rule; official permission or approval. Measures taken by nations to coerce another to conform.",
                example = "The United Nations imposed economic sanctions on the country for violating international law.",
                collocation = "impose sanctions, economic sanctions, lift sanctions",
                relatedWords = "penalty, punishment, embargo, restriction",
                note = "Từ đa nghĩa thú vị: vừa có nghĩa trừng phạt, vừa có nghĩa cho phép (chính thức phê duyệt)."
            ),
            WordEntity(
                deckId = deckId,
                word = "Sovereignty",
                pronunciation = "/ˈsɒv.rɪn.ti/",
                meaning = "Chủ quyền",
                descriptionEn = "Supreme power or authority; the authority of a state to govern itself or another state.",
                example = "Every nation has the right to protect its sovereignty and territorial integrity.",
                collocation = "national sovereignty, sovereignty over, respect sovereignty",
                relatedWords = "independence, autonomy, self-rule, supremacy",
                note = "Tính từ: 'sovereign' (có chủ quyền). 'Sovereign state' là quốc gia có chủ quyền."
            ),
            WordEntity(
                deckId = deckId,
                word = "Immigrant",
                pronunciation = "/ˈɪm.ɪ.ɡrənt/",
                meaning = "Người nhập cư",
                descriptionEn = "A person who comes to live permanently in a foreign country.",
                example = "The city has a large immigrant population that contributes to its cultural diversity.",
                collocation = "illegal immigrant, immigrant community, immigrant workers",
                relatedWords = "migrant, settler, newcomer, expatriate",
                note = "Phân biệt: 'immigrant' (người nhập cư vào) vs 'emigrant' (người di cư ra). Động từ: 'immigrate'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Petition",
                pronunciation = "/pəˈtɪʃ.ən/",
                meaning = "Kiến nghị, thỉnh nguyện",
                descriptionEn = "A formal written request, typically signed by many people, appealing to authority for a specific cause.",
                example = "Over ten thousand citizens signed a petition demanding cleaner air in the city.",
                collocation = "sign a petition, file a petition, online petition",
                relatedWords = "appeal, request, plea, application",
                note = "Có thể dùng làm danh từ và động từ. 'Petitioner' là người kiến nghị."
            ),
            WordEntity(
                deckId = deckId,
                word = "Prejudice",
                pronunciation = "/ˈpredʒ.ə.dɪs/",
                meaning = "Định kiến",
                descriptionEn = "Preconceived opinion that is not based on reason or actual experience; unfair bias against a group.",
                example = "Education plays a vital role in reducing prejudice and promoting tolerance in society.",
                collocation = "racial prejudice, without prejudice, overcome prejudice",
                relatedWords = "bias, discrimination, bigotry, intolerance",
                note = "Tính từ: 'prejudiced' (có định kiến). 'Without prejudice' là thuật ngữ pháp lý nghĩa là 'không ảnh hưởng quyền lợi'."
            ),
            WordEntity(
                deckId = deckId,
                word = "Solidarity",
                pronunciation = "/ˌsɒl.ɪˈdær.ə.ti/",
                meaning = "Đoàn kết",
                descriptionEn = "Unity or agreement of feeling or action among individuals with a common interest; mutual support within a group.",
                example = "The community showed great solidarity by coming together to help flood victims.",
                collocation = "show solidarity, in solidarity with, sense of solidarity",
                relatedWords = "unity, togetherness, cooperation, camaraderie",
                note = "Thường dùng trong ngữ cảnh chính trị và xã hội. 'In solidarity with' nghĩa là đoàn kết cùng ai đó."
            ),
            WordEntity(
                deckId = deckId,
                word = "Transparent",
                pronunciation = "/trænsˈpær.ənt/",
                meaning = "Minh bạch",
                descriptionEn = "Open to public scrutiny; easy to perceive or detect. Also means allowing light to pass through.",
                example = "The government promised to be more transparent about how public funds are spent.",
                collocation = "transparent process, transparent government, fully transparent",
                relatedWords = "open, clear, accountable, candid",
                note = "Danh từ: 'transparency'. Nghĩa đen: trong suốt (vật liệu). Nghĩa bóng: minh bạch (chính quyền, tổ chức)."
            )
        )
    }
}
