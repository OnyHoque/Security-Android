@echo off
git add .
set /p comment= Enter commit comment here:
git commit -m "%comment%"
cmd /k
