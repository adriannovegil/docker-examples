#!/bin/bash

# Build the image
docker build -t devcircus-gitlab .

# Run the container
docker run --rm -p 80:80 -p 443:443 --name devcircus-gitlab-test --volumes-from=devcircus-gitlab-data-test -t -i devcircus-gitlab
