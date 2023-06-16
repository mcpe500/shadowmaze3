@echo off

rem Clean the output directory
rmdir /s /q output

rem Compile the source code
javac -d output -cp "lib/*" src/*.java src/util/*.java src/game/level/*.java src/game/*.java src/game/Tile/*.java src/game/Interface/*.java

rem Change to the output directory
cd output

rem Execute the program with JaCoCo agent
java -javaagent:../lib/jacoco-0.8.10/lib/jacocoagent.jar=destfile=../test/coverage.exec -cp ".;../lib/*" src.Main

rem Change back to the project directory
cd ..

rem Generate the HTML report
java -jar lib/jacoco-0.8.10/lib/jacococli.jar report test/coverage.exec --classfiles output --html report --sourcefiles src

rem Open the HTML report
start report/index.html
