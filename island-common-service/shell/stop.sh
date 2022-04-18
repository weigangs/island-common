#!/bin/bash
BASE_DIR=`cd $(dirname $0);pwd -P`
JAR_NAME=island-common-service-1.0.1.jar
APP_NAME=${BASE_DIR}/${JAR_NAME}
LOG_HOME=${BASE_DIR}/logs
source /etc/profile

if [ ! -d ${LOG_HOME} ] ;then
    mkdir -p ${LOG_HOME}
fi


is_Running(){
    pid=`ps -ef|grep -w $JAR_NAME|grep -v grep|awk '{print $2}' `
    if [ -z "${pid}" ]; then
    return 1
    else
    return 0
    fi
}


stop(){
    is_Running
    if [ $? -eq "0" ]; then
    kill $pid
    else
    echo "${APP_NAME} is not running"
    fi
}

stop
