create schema if not exists saving;

CREATE TABLE ema
(
    product_id   varchar(25) primary key,
    ema50        decimal(15, 7),
    ema100       decimal(15, 7),
    ema200       decimal(15, 7),
    created_date timestamp without time zone default (now()),
    updated_date timestamp without time zone
);


CREATE TABLE crypto_currencies_info
(
    product_id   varchar(25) primary key,
    price        decimal(15, 7),
    created_date timestamp without time zone default (now()),
    updated_date timestamp without time zone,
    FOREIGN KEY (product_id) REFERENCES ema (product_id)
);




create
    or replace function on_updated_trigger() returns trigger as
$updated_date$
begin
    new.updated_date
        := current_timestamp;
    return new;
end;

$updated_date$
    language plpgsql;

drop trigger if exists on_update on saving.crypto_currencies_info;
create trigger on_update
    before update
    on saving.crypto_currencies_info
    for each row
execute procedure on_updated_trigger();

drop trigger if exists on_update on saving.ema;
create trigger on_update
    before update
    on saving.ema
    for each row
execute procedure on_updated_trigger();