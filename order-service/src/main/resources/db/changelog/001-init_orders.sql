CREATE TABLE IF NOT EXISTS orders (
    id              BIGSERIAL       PRIMARY KEY,
    product         VARCHAR(255)     NOT NULL,
    description     VARCHAR(255)     NOT NULL,
    customer        VARCHAR(255)     NOT NULL,
    order_status    VARCHAR(50)      NOT NULL,
    created_at      TIMESTAMP        NOT NULL,
    updated_at      TIMESTAMP        NOT NULL
);

CREATE TABLE IF NOT EXISTS outbox_events (
     id              BIGSERIAL       PRIMARY KEY,
     payload         TEXT            NOT NULL,
     event_type      VARCHAR(30)     NOT NULL,
     created_at      TIMESTAMP       NOT NULL
);