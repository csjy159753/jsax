#!/bin/bash
app=$1
port=$2
path=target
echo this is app : $app
echo port : $port
#若项目已启动，杀死旧进程
api_pid=`ps -ef | grep "$app.jar" | grep -v grep | awk '{print $2}'`
echo api_pid = $api_pid

if [ "$api_pid" != "" ]; then
        echo kill api
        kill -9 $api_pid

        echo sleep 3s
        sleep 1
        echo sleep 2s
        sleep 1
        echo sleep 1s
        sleep 1

        echo kill api
        kill -9 $api_pid
fi

cd $path

#防止进程被杀死
BUILD_ID=dontKillMe
#后台进程形式启动项目
nohup java -jar -Dserver.port=$port -Xmx256m -Xms128m  $app.jar &
echo $app start success
exit 0