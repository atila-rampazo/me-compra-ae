CREATE TABLE products (
	id INT auto_increment NOT NULL,
	category_id INT NOT NULL,
	name varchar(100) NOT NULL,
	price double(18,2) NULL,
	description LONGTEXT NULL,
	status BOOL DEFAULT 1 NULL,
	image varchar(255) NULL,
	created_at timestamp default now() null,
    updated_at timestamp default now() not null,
	CONSTRAINT products_PK PRIMARY KEY (id),
	CONSTRAINT products_FK FOREIGN KEY (category_id) REFERENCES mecompraae.products(id) ON DELETE CASCADE
)