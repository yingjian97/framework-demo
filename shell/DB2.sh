docker run -itd --name db2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=123456 -e DBNAME=testdb ibmcom/db2:11.5.6.0


su - db2inst1

db2start

db2 list db directory

db2 connect to testdb

db2 select schemaname from syscat.schemata

db2 select current schema from sysibm.sysdummy1
