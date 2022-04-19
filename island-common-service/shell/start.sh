#!/bin/bash
BASE_DIR=`cd $(dirname $0);pwd -P`
JAR_NAME=island-common-service-1.0.1.jar
APP_NAME=${BASE_DIR}/${JAR_NAME}
LOG_HOME=${BASE_DIR}/logs
CONF_DIR=${BASE_DIR}/conf
JVM_OPTS=`cat $CONF_DIR/application.yml | grep -w "jvm_opts:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`
SERVER_NAME=`cat $CONF_DIR/application.yml | grep -w "name:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`
SERVER_PORT=`cat $CONF_DIR/application.yml | grep -w "port:" | grep -v "#" | awk  'NR==1{print $2}' | tr -d '\r'`
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


start(){
    is_Running
    if [ $? -eq "0" ]; then
    echo "${APP_NAME} is already running. pid=${pid} ."
    else
    echo -e "Starting $SERVER_NAME ...\c"
    nohup java -jar -server $JVM_OPTS -Dlog.home=${LOG_HOME} ${APP_NAME} > ${LOG_HOME}/trace.log 2>&1 &
    COUNT=0
    while [ $COUNT -lt 1 ]; do
        echo -e ".\c"
        sleep 3
        IS_LISTENED=`netstat -an | grep -w LISTEN | grep -w $SERVER_PORT`
        if [ -n "$IS_LISTENED" ]; then
            COUNT=1
        fi
    done

    PIDS=`ps -ef | grep "$LIB_DIR" | awk 'NR==1{print $2}'`
    echo "${SERVER_NAME} start success!"
    echo "PID: $PIDS"
    echo "STDOUT: $STDOUT_FILE"
    fi
}

start
