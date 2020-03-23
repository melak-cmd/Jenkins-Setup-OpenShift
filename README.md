# Using Jenkins as a Source-To-Image builder

To customize the official OpenShift Container Platform Jenkins image, we use the image as a Source-To-Image builder.

You can use S2I to copy your custom Jenkins Jobs definitions, additional plug-ins or replace the provided config.xml file with your own, custom, configuration.

In order to include your modifications in the Jenkins image, you need to have a Git repository with the following directory structure:

### plugins
This directory contains those binary Jenkins plug-ins you want to copy into Jenkins.

### plugins.txt
This file lists the plug-ins you want to install:

```
pluginId:pluginVersion
```
### configuration/jobs
This directory contains the Jenkins job definitions.

### configuration/config.xml
This file contains your custom Jenkins configuration.

The contents of the configuration/ directory will be copied into the /var/lib/jenkins/ directory, so you can also include additional files, such as credentials.xml, there.
