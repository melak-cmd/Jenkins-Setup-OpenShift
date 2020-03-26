import jenkins.model.Jenkins;

import hudson.tools.*
import hudson.tasks.Maven

import groovy.json.*

import org.apache.http.*
import org.apache.http.client.entity.*
import org.apache.http.client.methods.*
import org.apache.http.entity.*
import org.apache.http.impl.client.*
import org.apache.http.message.*

import java.util.logging.Logger

@Grapes(
@Grab(group='org.apache.httpcomponents', module='httpclient', version='4.5.11')
)

// Init HTTP Client
def httpClient = HttpClientBuilder.create().build();

def instance = Jenkins.getInstance()
def log = Logger.getLogger(this.class.name)

Thread.start {
    sleep 10000

    log.info "Setting up Maven tool..."
    def getMvnTags = new HttpGet("https://api.github.com/repos/apache/maven/tags");
    def getMvnTagsResponse = httpClient.execute(getMvnTags);
    def mvnTagsJson = new JsonSlurper().parseText(getMvnTagsResponse.entity.content.text)
    def mvnLatestTag = mvnTagsJson.find { it.name =~ /^maven-[0-9]\.[0-9]\.[0-9]$/ }
    def mvnLatestVersion = mvnLatestTag.name.replace('maven-', '')
    hudson.tasks.Maven.DESCRIPTOR.setInstallations([
        new hudson.tasks.Maven.MavenInstallation(
            /* name */ 'maven', 
            /* home */ null, 
            /* properties */ [
                new InstallSourceProperty([
                    new hudson.tasks.Maven.MavenInstaller(/* id */ mvnLatestVersion),
                ]),
            ]
        ),
    ] as hudson.tasks.Maven.MavenInstallation[])
    log.info "Maven tool setup done."

    instance.save()
}    