@echo off

call mvn -B clean install

echo ====mvn finish===
docker build -t demo-app . -f Dockerfile

pause