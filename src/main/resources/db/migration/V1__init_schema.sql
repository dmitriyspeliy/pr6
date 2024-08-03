create table if not exists tiny_url
(
    id       bigint generated always as identity primary key,
    original varchar(1000) not null,
    shorter  varchar(100)  not null unique,
    ttl      timestamp     not null
);

create index if not exists tiny_url_index ON tiny_url (shorter);
