@echo off
cd /d "%~dp0"
if not exist bin mkdir bin
javac -d bin src\com\university\model\*.java src\com\university\service\*.java src\com\university\util\*.java src\com\university\exception\*.java src\com\university\Main.java
if %errorlevel% equ 0 (
    echo.
    echo ===== COMPILATION SUCCESS =====
    echo.
    java -cp bin com.university.Main
    echo.
    echo ===== PROGRAM FINISHED =====
) else (
    echo.
    echo ===== COMPILATION FAILED =====
)
echo.
echo Press any key to close...
pause > nul