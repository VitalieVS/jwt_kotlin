CREATE TABLE IF NOT EXISTS users_role (
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
    )