#
# Jenkins Nginx Dockerfile
#

FROM centos:7

# Maintainer details
MAINTAINER Adrian Novegil <adrian.novegil@gmail.com>

# Yum workaround to stalled mirror
RUN sed -i -e 's/enabled=1/enabled=0/g' /etc/yum/pluginconf.d/fastestmirror.conf

RUN rm -f /var/lib/rpm/__*
RUN rpm --rebuilddb -v -v
RUN yum clean all

# Install NGINX
RUN yum -y -v install http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm;
RUN yum -y -v install nginx-1.8.0

# Remove default files we don't need
RUN rm /etc/nginx/conf.d/default.conf
RUN rm /etc/nginx/conf.d/example_ssl.conf

# Add default configuration
COPY conf/jenkins.conf /etc/nginx/conf.d/jenkins.conf
COPY conf/nginx.conf /etc/nginx/nginx.conf

# Expose the ports
EXPOSE 80

# Define the exntry point
CMD ["nginx"]

# If you're reading this and have any feedback on how this image could be
#   improved, please open an issue or a pull request so we can discuss it!
