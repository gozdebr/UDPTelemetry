@echo off
setlocal
set JAR=jeromq-0.5.3.jar
set CP=out;%JAR%

REM start each process in its own window:
cmd /c java -cp "out;lib/*" Receiver


echo Started. Close all windows to stop.
endlocal