FROM openshift/jenkins-2-centos7
COPY /master/plugins.txt /opt/openshift/configuration/plugins.txt
rm -f /usr/lib/jenkins/jenkins.war 
RUN /usr/local/bin/install-plugins.sh /opt/openshift/configuration/plugins.txt