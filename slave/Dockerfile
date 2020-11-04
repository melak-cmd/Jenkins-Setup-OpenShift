# The FROM will be replaced when building in OpenShift
FROM registry.access.redhat.com/ubi8/ubi:8.1

MAINTAINER Siamak Sadeghianfar <ssadeghi@redhat.com>

# Install headless Java
USER root
LABEL maintainer="Amine KAOUANI"
RUN yum update --disablerepo=* --enablerepo=ubi-8-appstream --enablerepo=ubi-8-baseos -y && rm -rf /var/cache/yum
RUN yum install iputils -y

# Copy the entrypoint
USER 1001

# Run the JNLP client by default
# To use swarm client, specify "/var/lib/jenkins/run-swarm-client" as Command
ENTRYPOINT ["/var/lib/jenkins/run-jnlp-client"]
