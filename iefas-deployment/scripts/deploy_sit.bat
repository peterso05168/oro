cd /d %~dp0
cd ../../
call mvn clean package -Psit -Psit-1
call mvn clean package -Psit -Psit-2
pause