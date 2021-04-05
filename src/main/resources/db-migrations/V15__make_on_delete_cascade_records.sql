alter table keepingtrack.records drop constraint fk_subcategory;

alter table keepingtrack.records
	add constraint fk_subcategory
			foreign key (subcategory_id) references keepingtrack.subcategories
						on delete cascade;
						
