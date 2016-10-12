#!/bin/bash

# Build the image
docker build -t devcircus-jenkins-master .

# Run a container with a Jenkins inside.
docker run --rm -p 8080:8080 -p 50000:50000 --name devcircus-jenkins-master-test --volumes-from=devcircus-jenkins-data-test -t -i devcircus-jenkins-master
