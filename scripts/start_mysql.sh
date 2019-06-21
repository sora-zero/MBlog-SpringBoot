#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

MASTER_CONFIG="$DIR/../config/master-my.cnf"
SLAVE_CONFIG="$DIR/../config/slave-my.cnf"

MASTER_SCRIPT_TEMPLATE="$DIR/../scripts/master_mysql.sql.template"
SLAVE_SCRIPT_TEMPLATE="$DIR/../scripts/slave_mysql.sql.template"

MASTER_SCRIPT="$DIR/../scripts/master_mysql.sql"
SLAVE_SCRIPT="$DIR/../scripts/slave_mysql.sql"


docker run -p 3306:3306 --name master-mysql -v ${MASTER_CONFIG}:/etc/my.cnf -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7.25
docker run -p 3307:3306 --name slave-mysql -v ${SLAVE_CONFIG}:/etc/my.cnf -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7.25

# extract master mysql ip address
MASTER_IP=$(docker inspect master-mysql |grep -o '[0-9]\{1,3\}\.[0-9]\{1,3\}\.[0-9]\{1,3\}\.[0-9]\{1,3\}'|tail -1)
SLAVE_IP=$(docker inspect slave-mysql |grep -o '[0-9]\{1,3\}\.[0-9]\{1,3\}\.[0-9]\{1,3\}\.[0-9]\{1,3\}'|tail -1)

# check master bin file name
BIN_FILE=$(docker exec master-mysql sh -c 'mysql -u root -p123456 -e "show master status;"'|grep -ow 'mysql-bin.*[0-9]'|cut -d$'\t' -f1)

# fill parameters into sql scripts
sed "s/SED_SLAVE_IP/${SLAVE_IP}/g" ${MASTER_SCRIPT_TEMPLATE} > ${MASTER_SCRIPT}

sed "s/SED_MASTER_IP/${MASTER_IP}/g" ${SLAVE_SCRIPT_TEMPLATE} > ${SLAVE_SCRIPT}
# sed -i "s/SED_MASTER_LOG_FILE/${BIN_FILE}/g" ${SLAVE_SCRIPT}
# on macOS
sed -i '' "s/SED_MASTER_LOG_FILE/${BIN_FILE}/g" ${SLAVE_SCRIPT}

# copy config file to container
docker cp ${MASTER_SCRIPT} master-mysql:.
docker cp ${SLAVE_SCRIPT} slave-mysql:.

# run sql script
docker exec master-mysql sh -c 'mysql -u root -p123456 < ./master_mysql.sql'
docker exec slave-mysql sh -c 'mysql -u root -p123456 < ./slave_mysql.sql'

