#!/bin/bash

# Build the image
docker build -t devcircus-jenkins-data .

# Run the container
docker run --name devcircus-jenkins-data-test -t -i devcircus-jenkins-data
