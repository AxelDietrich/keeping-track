create sequence keepingtrack.accounts_id_seq;

alter table keepingtrack.accounts alter column id type bigint using id::bigint;

alter table keepingtrack.accounts alter column id set default nextval('keepingtrack.accounts_id_seq');

alter sequence keepingtrack.accounts_id_seq owned by keepingtrack.accounts.id;

