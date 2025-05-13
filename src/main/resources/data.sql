-- 建立 currency_mapping 資料表
CREATE TABLE IF NOT EXISTS currency_mapping (
    code VARCHAR(255) PRIMARY KEY NOT NULL,
    english_name VARCHAR(255) NOT NULL,
    chinese_name VARCHAR(255)
);


-- 初始化測試資料
INSERT INTO currency_mapping (code, english_name, chinese_name) VALUES 
('USD', 'United States Dollar', '美元'),
('EUR', 'Euro', '歐元'),
('GBP', 'British Pound', '英鎊'),
('TWD', 'New Taiwan Dollar', '新台幣');
