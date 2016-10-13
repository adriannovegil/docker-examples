#
# Gitlab CE Dockerfile
#
FROM ubuntu:16.04

MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Install required packages
RUN apt-get update -q \
    && DEBIAN_FRONTEND=noninteractive apt-get install -yq --no-install-recommends \
      ca-certificates \
      openssh-server \
      wget \
      apt-transport-https \
      vim \
      curl \
      nano

# Copu assets
COPY assets/ /assets/

# Download & Install GitLab
RUN curl -sS https://packages.gitlab.com/install/repositories/gitlab/gitlab-ce/script.deb.sh | bash
RUN wget -q -O - https://packages.gitlab.com/gpg.key | apt-key add -
RUN apt-get update && apt-get install -yq --no-install-recommends gitlab-ce  \
    bundler

# Remove current gitlab.rb file and load the custom configuration
RUN rm -f /etc/gitlab/gitlab.rb
RUN cp /assets/gitlab.rb /etc/gitlab/gitlab.rb

# Allow to access embedded tools
ENV PATH /opt/gitlab/embedded/bin:/opt/gitlab/bin:/assets:$PATH

# Expose web & ssh
EXPOSE 80 443

RUN chmod +x /assets/wrapper

# Wrapper to handle signal, trigger runit and reconfigure GitLab
CMD ["/assets/wrapper"]

# If you're reading this and have any feedback on how this image could be
#  improved, please open an issue or a pull request so we can discuss it!
