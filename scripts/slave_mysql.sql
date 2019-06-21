change master to master_host='172.17.0.2',
master_user='repl',
master_password='password',
master_log_file='mysql-bin.000006',
master_log_pos=0;

start slave;
