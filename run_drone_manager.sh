#!/bin/bash
mvn clean install -DskipTests
cd target/ || exit
java -jar drone-manager-0.0.1.jar --spring.profiles.active=dev
#java -jar drone-manager-0.0.1.jar --spring.profiles.active=prod