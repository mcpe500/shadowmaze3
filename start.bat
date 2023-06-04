@echo off

javac -d output -cp "lib/*" src/*.java src/interface/*.java

cd output

java -cp ".;../lib/*" src.Main

cd ..