CREATE TABLE products (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name VARCHAR(255),
  description TEXT,
  price NUMERIC(10, 2),
  quantity INTEGER,
  created_at TIMESTAMP,
  updated_at TIMESTAMP 
)