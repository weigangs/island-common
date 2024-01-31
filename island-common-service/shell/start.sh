#!/bin/bash

BASE_DIR=$(
  cd $(dirname $0)
  pwd -P
)
LOG_HOME=${BASE_DIR}/logs
STDOUT_FILE=$LOG_HOME/trace.log
SERVER_PORT=443
JASYPT_SECRET_VM="-Djasypt.encryptor.password="

appName=$(ls | grep .jar$)
if [ -z $appName ]; then
  echo "Please check that this script and your jar-package is in the same directory!"
  exit 1
fi

killForceFlag=$2

function setVmParams() {
  #if start $2 instead of secret
  JASYPT_SECRET_VM=$JASYPT_SECRET_VM$killForceFlag
}

function start() {
  # set vm params
  setVmParams
  count=$(ps -ef | grep java | grep $appName | wc -l)
  if [ $count != 0 ]; then
    echo "Maybe $appName is running, please check it..."
  else
    echo "The $appName is starting..."
    nohup java -Xms125M -Xmx125M -jar "-Dspring.config.activate.on-profile=prd" "$JASYPT_SECRET_VM" $appName >$STDOUT_FILE 2>&1 &
  fi

  COUNT=0
  while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 3
    IS_LISTENED=$(netstat -an | grep -w LISTEN | grep -w $SERVER_PORT)
    if [ -n "$IS_LISTENED" ]; then
      COUNT=1
    fi
  done

  PIDS=$(ps -ef | grep -w $appName | grep -v grep | awk '{print $2}')
  echo "\n${appName} start success!"
  echo "PID: $PIDS"
  echo "STDOUT: $STDOUT_FILE"
}

function status() {
  appId=$(ps -ef | grep java | grep $appName | awk '{print $2}')
  if [ -z $appId ]; then
    echo -e "\033[31m Not running \033[0m"
  else
    echo -e "\033[32m Running [$appId] \033[0m"
  fi
}

function stop() {
  appId=$(ps -ef | grep java | grep $appName | awk '{print $2}')
  if [ -z $appId ]; then
    echo "Maybe $appName not running, please check it..."
  else
    echo -n "The $appName is stopping..."
    if [ "$killForceFlag" == "-f" ]; then
      echo "by force"
      kill -9 $appId
    else
      echo
      kill $appId
    fi
  fi
}

function restart() {
  stop
  for i in {3..1}; do
    echo -n "$i "
    sleep 1
  done
  echo 0
  start
}

function usage() {
  echo "Usage: $0 {start|stop|restart|status|stop -f}"
  echo "Example: $0 start"
  exit 1
}

case $1 in
start)
  start
  ;;

restart)
  restart
  ;;

status)
  status
  ;;

*)
  usage
  ;;
esac
