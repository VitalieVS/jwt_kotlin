CREATE TABLE IF NOT EXISTS roles_permissions (
        FOREIGN KEY (role_id) REFERENCES roles(id),
        FOREIGN KEY (permission_id) REFERENCES permissions(id)
    )