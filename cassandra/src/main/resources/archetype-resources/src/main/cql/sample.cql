drop keyspace if exists scaffold_cassandra;
create keyspace scaffold_cassandra
with replication = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use scaffold_cassandra;
drop table if exists sampleuuidentities;
create table sampleuuidentities (
	id uuid,
	createdat timestamp,
	updatedat timestamp,
	primary key (id)
);

drop table if exists samplecompoundidentities;
create table samplecompoundidentities (
	key1 text,
	key2 text,
	key3 text,
	createdat timestamp,
	updatedat timestamp,
	primary key (key1, key2, key3)
);
