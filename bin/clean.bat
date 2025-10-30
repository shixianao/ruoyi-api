@echo off
echo.
echo [��Ϣ] ��������·����
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

pause