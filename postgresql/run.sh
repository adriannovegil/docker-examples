#!/bin/bash

docker build -t eg_postgresql .
#docker run --rm -P --name pg_test eg_postgresql
docker run --rm -p 127.0.0.1:32770:5432 --name pg_test eg_postgresql
