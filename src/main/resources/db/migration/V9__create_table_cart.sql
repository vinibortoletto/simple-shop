CREATE TABLE tb_cart (
    id VARCHAR(36) PRIMARY KEY,
    total DECIMAL NOT NULL,
    customer_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES tb_customer(id)
);

INSERT INTO tb_cart (id, total, customer_id)
VALUES
    ("cdd4ffcf-ef8e-4a24-9e3d-e333e159b7f2", 129.95, "5fef16b9-fc9b-4878-b09a-48efe4652933"),
    ("50e54531-2ee8-4554-ae76-231cceac3311", 129.95, "ca5cc4c9-0e04-4b8c-a3be-6e9f2e050148");
