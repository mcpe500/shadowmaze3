@echo off

javac -d output -cp "lib/*" src/*.java src/util/*.java src/game/level/*.java src/game/*.java

cd output

java -cp ".;../lib/*" src.Main

cd ..