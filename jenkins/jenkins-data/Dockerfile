#
# Jenkins Data Dockerfile
#

FROM centos:7

# Maintainer details
MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Create the jenkins user
RUN useradd -d "/var/jenkins_home" -u 1000 -m -s /bin/bash jenkins

# Create the folders and volume mount points
RUN mkdir -p /var/log/jenkins
RUN chown -R jenkins:jenkins /var/log/jenkins
VOLUME ["/var/log/jenkins", "/var/jenkins_home"]

# Once finished, set the jenkins user.
USER jenkins

# Finally, show a text message via console.
CMD ["echo", "Data container for Jenkins"]

# If you're reading this and have any feedback on how this image could be
#   improved, please open an issue or a pull request so we can discuss it!
