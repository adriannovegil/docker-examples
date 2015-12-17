#
# Java 1.7 & Jetty & Maven Dockerfile
#
FROM java:7

# Maintainer details
MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Update packages and install maven
RUN  \
  export DEBIAN_FRONTEND=noninteractive && \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10 && \
  echo 'deb http://downloads-distro.mongodb.org/repo/ubuntu-upstart dist 10gen' > /etc/apt/sources.list.d/mongodb.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y vim wget curl git maven mongodb-org-shell

# Attach volumes
VOLUME /volume/git

# Create working directory
RUN mkdir -p /local/git
WORKDIR /local/git/

# Install the test webapp
RUN git clone https://github.com/adriannovegil/docker-maven-tomcat
WORKDIR /local/git/docker-maven-tomcat

RUN chmod +x /local/git/docker-maven-tomcat/run.sh

# Run startup script
# CMD ["run.sh"]

EXPOSE 8080
