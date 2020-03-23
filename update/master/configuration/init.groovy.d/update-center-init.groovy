import jenkins.model.Jenkins
import jenkins.security.NotReallyRoleSensitiveCallable
import jenkins.model.Jenkins

import hudson.security.ACL
import hudson.model.DownloadService
import hudson.model.RestartListener
import hudson.model.UpdateCenter
import hudson.model.UpdateSite
import hudson.PluginManager
import hudson.util.HudsonIsRestarting

import java.time.Duration
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import java.util.Timer

import java.util.logging.Logger

ACL.impersonate(ACL.SYSTEM, new NotReallyRoleSensitiveCallable<Void, Exception>() {
    public Void call() throws Exception {
        Thread thread = new Thread(){
            public void run(){
                ACL.impersonate(ACL.SYSTEM, new NotReallyRoleSensitiveCallable<Void, Exception>() {
                    public Void call() throws Exception {
                            try {
                                Jenkins.getInstance().getPluginManager().doCheckUpdatesServer();
                            	Jenkins.getInstance().getUpdateCenter().getCoreSource().getData();                                                       
                            } catch (Throwable e) {
                                System.out.println("The OpenShift Jenkins image's attempt to accelerate Jenkins Update Center data retrieval has encounted a hiccup:  " + e.getMessage() + " ......  Moving on")
                            }
                        return null;
                    }
                });
            }
        };
        
        thread.start();
        return null;
    }
});

