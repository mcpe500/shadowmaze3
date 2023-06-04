@echo off

javac -d output -cp "lib/*" src/*.java src/util/*.java

cd output

java -cp ".;../lib/*" src.Main

cd ..