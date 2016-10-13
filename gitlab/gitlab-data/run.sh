#!/bin/bash

# Build the image
docker build -t devcircus-gitlab-data .

# Run the container
docker run --name devcircus-gitlab-data-test -t -i devcircus-gitlab-data
