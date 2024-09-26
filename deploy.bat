@echo off

set "root=D:\Studies\ITU\S5\INF 301 - Architechture Logiciel\code\wildfly-ejb\EJBClient"
set "bin=%root%\bin"
set "lib=%root%\lib"
set "web=%root%\web"
set "temp=%root%\temp"
set "src=%root%\src"
set "target_dir=D:\Executable\wildfly-26.1.2.Final\standalone\deployments"
set "war_name=bean-ejb"

rem Copy all java files to a temporary folder
for /r "%src%" %%f in (*.java) do (
    xcopy "%%f" "%temp%"
)

javac -d "%bin%" -cp "%lib%\*" "%temp%\*.java"


@REM rem Set the path to your Wildfly deployments folder
xcopy /s /e /i "%lib%\*" "%web%\WEB-INF\lib\"
xcopy /s /e /i "%bin%\*" "%web%\WEB-INF\classes\"

rem Create the WAR file using an absolute path
jar -cvf "%war_name%.war" -C "%web%" .

copy "%war_name%.war" "%target_dir%"

del "%target_name%.war"
rd /s /q "%temp%"

echo Deployment complete.
pause