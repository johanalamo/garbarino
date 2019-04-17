normal="echo -n -e \e[0m";
blink="echo -n -e \e[5m";
green="echo -n -e \e[42m";
red="echo -n -e \e[41m";
log_file="log.txt";
app="com.example.johan.garbarino";
activity="MainActivity"

if [ $1 = "com" ]; then
    first=1;
    while [ "$2" = "-r" ] || [ $first -eq 1 ]; do
        fecha=`date`;
        echo "">$log_file;
        echo "==${fecha}=======">>$log_file;
        #./gradlew assembleDebug --stacktrace --debug 2>>$log_file 1>>$log_file; 
        ./gradlew assembleDebug --stacktrace 2>>$log_file 1>>$log_file; 
        r=$?;
        if [ $r -eq 0 ]; then
            $green;
            echo  -n "$fecha: Compilation OK";
            $normal;
            echo;
        else
            $red;
            echo -n "$fecha: Compilation ERROR";
            $normal;
            echo 
            echo "ERROR: ";
            cat $log_file | head -20;
            echo $fecha;
        fi
        first=0;
        sleep 1s;
    done;
    exit;
fi;
if [ $1 = "viewcolors" ]; then
    for i in `seq 0 256`; do
        echo -e "\e[${i}mTEXTO con valor: ${i}\e[0m";
    done;
        echo -e "\e[31m\e[42mTEXTO con valor: ${i}";
    exit;
fi

if [ "$2" != ""  ]; then
	activity=$2;
fi
echo "activity: $activity";
#exit;
if [ $1 = "run" ]; then
    ./gradlew assembleDebug && 
    ./gradlew installDebug && 
    adb shell cmd package uninstall -k $app &&
    #el anterior siempre da $?=0
    adb -d install "`pwd`/app/build/outputs/apk/debug/app-debug.apk" &&
    #anterior manejarla con $? 0->exito   otro-> fall{o
    adb shell am start -n "${app}/${app}.${activity}" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER;
    exit;
fi;
if [ $1 = "install" ]; then
    adb shell cmd package uninstall -k $app
    #el anterior siempre da $?=0
    adb -d install "`pwd`/app/build/outputs/apk/debug/app-debug.apk" &&
    #anterior manejarla con $? 0->exito   otro-> fall{o
    adb shell am start -n "${app}/${app}.${activity}" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER;
    exit;
fi;
if [ $1 = "start" ]; then
#    adb -d install "`pwd`/app/build/outputs/apk/debug/app-debug.apk" &&
    adb shell am start -n "${app}/${app}.${activity}"  -a android.intent.action.MAIN -c android.intent.category.LAUNCHER;
#    sleep 1s;
    exit;
fi;

if [ $1 = "log" ]; then
    p=`adb shell ps -A | grep ${app} | awk '{ print $2 }'`
    echo "PID = $p"; 
    if [ "$p" != "" ]; then
      adb shell logcat --pid=$p | cut -c 32-   
    else
      echo "not opened";
    fi;
    exit;
fi;

echo "unrecognized option";

exit;
