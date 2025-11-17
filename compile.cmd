@echo off
setlocal
REM change this if your jar name differs

if not exist out mkdir out

javac -encoding UTF-8 -cp ".;lib/*"  -d out src\*.java

if errorlevel 1 exit /b 1
echo Build OK. Class files in .\out
endlocal
