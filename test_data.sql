-- Test verisi ekleme scripti
-- Admin kullanıcısı (role = 1)
INSERT INTO `user` (`user_name`, `password`, `email`, `phone_number`, `first_name`, `last_name`, `role`) 
VALUES ('admin', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Qz8K2O', 'admin@nereye.com', '5551234567', 'Admin', 'User', 1);

-- Normal kullanıcı (role = 0)
INSERT INTO `user` (`user_name`, `password`, `email`, `phone_number`, `first_name`, `last_name`, `role`) 
VALUES ('user', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Qz8K2O', 'user@nereye.com', '5557654321', 'Normal', 'User', 0);

-- Şifre: "password123" (BCrypt ile hashlenmiş)
