#!/bin/bash

docker build -t eg_tomcat .
docker run -d --name mt_tomcat -p 8080:8080 --link mt_mongodb eg_tomcat /local/git/docker-maven-tomcat/run.sh

