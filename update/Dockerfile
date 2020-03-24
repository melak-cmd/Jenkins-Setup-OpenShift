FROM quay.io/openshift/origin-jenkins
COPY /master/plugins.txt /opt/openshift/configuration/plugins.txt
RUN /usr/local/bin/install-plugins.sh /opt/openshift/configuration/plugins.txt