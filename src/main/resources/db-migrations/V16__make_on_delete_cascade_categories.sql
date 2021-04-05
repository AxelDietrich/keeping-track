alter table keepingtrack.categories drop constraint fk_account;

alter table keepingtrack.categories
	add constraint fk_account
			foreign key (account_id) references keepingtrack.accounts
						on delete cascade;

