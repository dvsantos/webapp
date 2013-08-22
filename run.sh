#!/bin/bash

JAVA_HOME=/opt/jdk1.7.0_17/

export JAVA_HOME

echo 'Setting JAVA_HOME to' $JAVA_HOME

mvn jetty:run

