FROM quay.io/openshift/origin-jenkins

MAINTAINER MELAK <a18m02k86_pro@outlook.com>

# Install headless Java
USER root
RUN mkdir -p /var/lib/jenkins && \
    chown -R 1001:0 /var/lib/jenkins && \
    chmod -R g+w /var/lib/jenkins

# Copy the entrypoint
COPY configuration/* /var/lib/jenkins/
USER 1001

ENTRYPOINT ["/var/lib/jenkins/run-client"]
