@echo off
:loop
rem Genera un numero casuale da 1 a 200
set /a number=%random% %% 200 + 1
echo %number%
timeout /t 1 >nul
goto loop