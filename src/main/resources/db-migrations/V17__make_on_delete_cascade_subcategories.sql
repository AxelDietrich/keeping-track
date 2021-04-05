alter table keepingtrack.subcategories drop constraint fk_category;

alter table keepingtrack.subcategories
	add constraint fk_category
			foreign key (category_id) references keepingtrack.categories
						on delete cascade;

