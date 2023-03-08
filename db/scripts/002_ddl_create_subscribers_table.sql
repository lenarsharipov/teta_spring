create table subscribers (
	id serial primary key,
	name varchar not null,
	surname varchar not null,
	subscriber_number varchar unique not null,
	plate varchar not null,
	company_id int references companies (id)
);