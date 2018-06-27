cd /d %~dp0
cd ../../
call mvn clean package -Ptest -Psit
pause