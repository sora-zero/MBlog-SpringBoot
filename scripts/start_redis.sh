#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

REDIS_CONFIG="$DIR/../config/redis.conf"

docker run -p 6379:6379 -d -v ${REDIS_CONFIG}:/usr/local/etc/redis/redis.conf --name myredis redis redis-server /usr/local/etc/redis/redis.conf
