alter table keepingtrack.balances
    alter column savings_amount TYPE numeric(16,2);

alter table keepingtrack.balances
    alter column available_amount TYPE numeric(16,2);

alter table keepingtrack.balances
    alter column debt TYPE numeric(16,2);

alter table keepingtrack.subcategories
    alter column amount type numeric(16,2);

alter table keepingtrack.records
    alter column amount type numeric(16,2);

