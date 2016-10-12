#
# Jenkins Master Dockerfile
#

FROM jenkinsci/jenkins:2.14

# Maintainer details
MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Set the root user.
USER root

# Set the jenkins update URL
ENV JENKINS_UC https://updates.jenkins-ci.org

# Install plugins.
COPY plugins.txt /tmp/files/
RUN /usr/local/bin/plugins.sh /tmp/files/plugins.txt

# Create a new user in the Jenkins
RUN touch /usr/share/jenkins/ref/plugins/credentials.jpi.pinned
COPY resources/*.groovy /usr/share/jenkins/ref/init.groovy.d

# Configure environment variables
ARG JAVA_OPTS
ENV JAVA_OPTS ${JAVA_OPTS:--Xmx8192m -Djenkins.install.runSetupWizard=false}
ENV JENKINS_OPTS="--handlerCountMax=300 --logfile=/var/log/jenkins/jenkins.log  --webroot=/var/cache/jenkins/war"

# Change permissions
RUN chown -R jenkins:jenkins /usr/share/jenkins/

# Dockerize
RUN wget https://github.com/jwilder/dockerize/releases/download/v0.2.0/dockerize-linux-amd64-v0.2.0.tar.gz
RUN tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.2.0.tar.gz

# Once finished, set the jenkins user again.
USER jenkins

# Expose the ports
EXPOSE 8080 50000

# If you're reading this and have any feedback on how this image could be
#   improved, please open an issue or a pull request so we can discuss it!
