docker run -itd --name db2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=123456 -e DBNAME=testdb ibmcom/db2:11.5.6.0

ifconfig | grep eth0 -n1 | grep inet | awk '{print $3}'

db2set DB2COMM=TCPIP

echo 'db2c_db2inst1 50000/tcp' >> /etc/service

cat /etc/services

db2 get dbm cfg |grep SVCENAME


su - db2inst1

db2start

db2 list db directory

db2 select current schema from sysibm.sysdummy1
