insert into ordering(user_name, updated_at)
values ('petr', '2022-01-22 12:31:42.448126');
insert into ordering(user_name, updated_at)
values ('ivan', '2022-01-22 12:31:42.448126');
insert into ordering(user_name, updated_at)
values ('jon', '2022-01-22 12:31:42.448126');

insert into ordering_items (ordering_id, item_name, item_count, item_price)
values ((select id from ordering where user_name = 'petr'), 'Кросовки', 2, 400.1);

insert into ordering_items (ordering_id, item_name, item_count, item_price)
values ((select id from ordering where user_name = 'petr'), 'Футблока', 1, 40);

insert into ordering_items (ordering_id, item_name, item_count, item_price)
values ((select id from ordering where user_name = 'ivan'), 'Мяч', 2, 10);

insert into ordering_items (ordering_id, item_name, item_count, item_price)
values ((select id from ordering where user_name = 'ivan'), 'Ракетка', 3, 5);

insert into ordering_items (ordering_id, item_name, item_count, item_price)
values ((select id from ordering where user_name = 'ivan'), 'Валан', 2, 3);
