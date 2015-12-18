#!/bin/bash

docker build -t eg_python .
docker run -t -i -e VMHOST=127.0.0.1 --name mt_python -p 8000:8000 --link mt_tomcat eg_python /local/git/docker-python/run.sh
