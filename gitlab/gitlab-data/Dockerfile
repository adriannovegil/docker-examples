#
# Jenkins Data Dockerfile
#
FROM centos:7

# Maintainer details
MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Create the gitlab user
RUN useradd -d "/var/opt/gitlab" -u 1000 -m -s /bin/bash gitlab

# Create the folders and volume mount points
#RUN mkdir -p /var/log/gitlab
#RUN chown -R jenkins:jenkins /var/log/gitlab
VOLUME ["/etc/gitlab", "/var/opt/gitlab", "/var/log/gitlab"]

# Once finished, set the gitlab user.
USER gitlab

# Finally, show a text message via console.
CMD ["echo", "Data container for Gitlab"]

# If you're reading this and have any feedback on how this image could be
#  improved, please open an issue or a pull request so we can discuss it!
