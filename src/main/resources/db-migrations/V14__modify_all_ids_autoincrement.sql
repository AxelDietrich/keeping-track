create sequence keepingtrack.balances_id_seq;

alter table keepingtrack.balances alter column id set default nextval('keepingtrack.balances_id_seq');

alter sequence keepingtrack.balances_id_seq owned by keepingtrack.balances.id;

create sequence keepingtrack.categories_id_seq;

alter table keepingtrack.categories alter column id set default nextval('keepingtrack.categories_id_seq');

alter sequence keepingtrack.categories_id_seq owned by keepingtrack.categories.id;

create sequence keepingtrack.records_id_seq;

alter table keepingtrack.records alter column id set default nextval('keepingtrack.records_id_seq');

alter sequence keepingtrack.records_id_seq owned by keepingtrack.records.id;

create sequence keepingtrack.subcategories_id_seq;

alter table keepingtrack.subcategories alter column id set default nextval('keepingtrack.subcategories_id_seq');

alter sequence keepingtrack.subcategories_id_seq owned by keepingtrack.subcategories.id;


