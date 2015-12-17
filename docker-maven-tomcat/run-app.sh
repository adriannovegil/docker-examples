#!/bin/bash

echo `env`
git pull
mvn jetty:run
