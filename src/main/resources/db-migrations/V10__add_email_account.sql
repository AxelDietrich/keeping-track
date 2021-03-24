alter table keepingtrack.accounts
	add email varchar not null;

	create unique index accounts_email_uindex
		on keepingtrack.accounts (email);
