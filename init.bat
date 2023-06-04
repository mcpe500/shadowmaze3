@echo off

:: Membuat folder "src"
mkdir src

:: Membuat file "Main.java" dalam folder "src"
echo. > src\Main.java

:: Membuat folder "src\game"
mkdir src\game

:: Membuat file-file dalam folder "src\game"
echo. > src\game\Game.java
echo. > src\game\GameManager.java
echo. > src\game\Level.java
echo. > src\game\Player.java
echo. > src\game\Enemy.java
echo. > src\game\Obstacle.java
echo. > src\game\Item.java
echo. > src\game\Portal.java
echo. > src\game\HidingSpot.java
echo. > src\game\LightSource.java

:: Membuat folder "src\game\Interface"
mkdir src\game\Interface

:: Membuat file-file dalam folder "src\game\Interface"
echo. > src\game\Interface\Collidable.java
echo. > src\game\Interface\Movable.java
echo. > src\game\Interface\Interactable.java

:: Membuat folder "src\util"
mkdir src\util

:: Membuat file-file dalam folder "src\util"
echo. > src\util\InputManager.java
echo. > src\util\FileManager.java
echo. > src\util\AssetLoader.java
echo. > src\util\Constants.java

:: Membuat folder "assets"
mkdir assets

:: Membuat folder "assets\maps"
mkdir assets\maps

:: Membuat file-file dalam folder "assets\maps"
echo. > assets\maps\map1.txt
echo. > assets\maps\map2.txt

:: Membuat folder "assets\sprites"
mkdir assets\sprites

:: Membuat file-file dalam folder "assets\sprites"
echo. > assets\sprites\player.png
echo. > assets\sprites\enemy.png
echo. > assets\sprites\obstacle.png

:: Membuat folder "assets\sounds"
mkdir assets\sounds

:: Membuat file-file dalam folder "assets\sounds"
echo. > assets\sounds\bgm.mp3
echo. > assets\sounds\sfx_hit.wav

:: Membuat folder "lib"
mkdir lib

:: Membuat folder "data"
mkdir data

:: Membuat folder "output"
mkdir output

echo Penyelesaian! Struktur folder dan file telah dibuat.

pause
