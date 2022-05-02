#!/bin/bash
BASE_DIR=`cd $(dirname $0);pwd -P`
JAR_NAME=island-common-service-1.0.1.jar
LOG_HOME=${BASE_DIR}/logs
CONF_DIR=${BASE_DIR}/conf
SERVER_NAME=`cat $CONF_DIR/application.yml | grep -w "name:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`
source /etc/profile

if [ ! -d ${LOG_HOME} ] ;then
    mkdir -p ${LOG_HOME}
fi


is_Running(){
    PIDS=`ps -ef|grep -w $JAR_NAME|grep -v grep|awk '{print $2}' `
    if [ -z "${pid}" ]; then
    return 1
    else
    return 0
    fi
}


stop(){
    is_Running
    if [ $? -eq "0" ]; then
    echo -e "Stopping the $SERVER_NAME ...\c"
    for PID in $PIDS ; do
        kill $PID > /dev/null 2>&1
    done

    echo -e "\n$SERVER_NAME server stopped! PID: $PIDS"
    else
    echo -e "\n${SERVER_NAME} is not running"
    fi
}

stop
