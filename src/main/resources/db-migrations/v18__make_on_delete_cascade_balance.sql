alter table keepingtrack.balances drop constraint fk_account;

alter table keepingtrack.balances
	add constraint fk_account
			foreign key (account_id) references keepingtrack.accounts
						on delete cascade;
						
