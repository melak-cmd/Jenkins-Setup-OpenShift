# The FROM will be replaced when building in OpenShift
FROM registry.access.redhat.com/ubi8/ubi:8.1


# Install headless Java
USER root
LABEL maintainer="Amine KAOUANI"
RUN yum update disablerepo=* 1>/dev/null && \
    --enablerepo=ubi-8-appstream && \
    --enablerepo=ubi-8-baseos -y && \
    export INSTALL_PKGS="nss_wrapper java-1.8.0-openjdk-headless \
        java-1.8.0-openjdk-devel nss_wrapper gettext tar git" && \
    yum clean all && \
    yum install -y --setopt=tsflags=nodocs install $INSTALL_PKGS && \
    rpm -V $INSTALL_PKGS && \
    yum clean all && \
    mkdir -p /var/lib/jenkins && \
    chown -R 1001:0 /var/lib/jenkins && \
    chmod -R g+w /var/lib/jenkins

# Copy the entrypoint
COPY configuration/* /var/lib/jenkins/
USER 1001

# Run the JNLP client by default
# To use swarm client, specify "/var/lib/jenkins/run-swarm-client" as Command
ENTRYPOINT ["/var/lib/jenkins/run-jnlp-client"]
