package src;

import src.util.Button;
import src.util.FileManager;
import src.util.Node;
import src.util.ScoreManager;
import src.game.level.Level1;
import src.game.level.Level2;
import src.game.level.Level3;
import src.game.level.Level4;
import src.game.level.Level5;
import src.game.level.LevelVersus;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import processing.sound.*;

public class Main extends PApplet {

    private PImage background;
    private Button playButton, settingsButton, backButton, level1Button, level2Button, level3Button, level4Button,
            level5Button, playVersusButton, nextButton, prevButton;
    private SoundFile sound;
    private Amplitude amp;

    private float scrollPosition = 0, scrollSpeed = 5, contentHeight = 1000, viewHeight = 400;

    private final int mainmenu = 0, settings = 1, playmenu = 2, adventuremenu = 3, versusmenu = 4, highscoremenu = 5;
    private float currentVolume = 1.0f;

    private int currentPage, previousPage;

    public static void main(String[] args) {
        Main app = new Main();
        String[] main = { "Main" };
        PApplet.runSketch(main, app);
    }

    public Main() {
        this.currentPage = mainmenu;
    }

    public Main(int currentPage) {
        this.currentPage = currentPage;
        this.previousPage = mainmenu;
    }

    public void settings() {
        size(1280, 720);
    }

    public void setup() {
        sound = new SoundFile(this, "../assets/sounds/bgm.mp3");
        sound.loop();
        amp = new Amplitude(this);
        amp.input(sound);

        int[] file = FileManager.openFile();

        background = loadImage("../assets/sprites/SL_menu.png");
        background.resize(1280, 720);
        playButton = new Button(280, 300, 250, 100, "Start");
        playButton.setImage(loadImage("../assets/buttons/start_button.png"));
        settingsButton = new Button(280, 460, 250, 110, "Settings");
        settingsButton.setImage(loadImage("../assets/buttons/settings_button.png"));
        backButton = new Button(50, 50, 100, 50, "Back");
        backButton.setImage(loadImage("assets/buttons/back_button.png"));
        level1Button = new Button(100, 400, 150, 80, "Level 1");
        level1Button.setImage(loadImage("assets/buttons/level1.png"));
        level2Button = new Button(300, 400, 150, 80, "Level 2");
        level3Button = new Button(500, 400, 150, 80, "Level 3");
        level4Button = new Button(700, 400, 150, 80, "Level 4");
        level5Button = new Button(900, 400, 150, 80, "Level 5");
        nextButton = new Button(900, 280, 250, 100, "Next");
        prevButton = new Button(100, 280, 250, 100, "Previous");
        nextButton.setImage(loadImage("../assets/buttons/next_button.png"));
        prevButton.setImage(loadImage("../assets/buttons/prev_button.png"));
        if (file[1] == 99999) {
            level2Button.setImage(loadImage("assets/buttons/level2_locked.png"));
            level2Button.setEnabled(false);
        } else {
            level2Button.setImage(loadImage("assets/buttons/level2.png"));
        }
        if (file[2] == 99999) {
            level3Button.setImage(loadImage("assets/buttons/level3_locked.png"));
            level3Button.setEnabled(false);
        } else {
            level3Button.setImage(loadImage("assets/buttons/level3.png"));
        }
        if (file[3] == 99999) {
            level4Button.setImage(loadImage("assets/buttons/level4_locked.png"));
            level4Button.setEnabled(false);
        } else {
            level4Button.setImage(loadImage("assets/buttons/level4.png"));
        }
        if (file[4] == 99999) {
            level5Button.setImage(loadImage("assets/buttons/level5_locked.png"));
            level5Button.setEnabled(false);
        } else {
            level5Button.setImage(loadImage("assets/buttons/level5.png"));
        }

        playVersusButton = new Button(200, 300, 250, 100, "Play");

    }

    public void mouseWheel(MouseEvent event) {
        float delta = event.getCount();
        scrollPosition += delta * scrollSpeed;
        scrollPosition = constrain(scrollPosition, 0, contentHeight - viewHeight);
    }

    public void draw() {
        background(background);
        if (currentPage == mainmenu) {
            playButton.update(mouseX, mouseY, mousePressed);
            playButton.display(this);
            settingsButton.update(mouseX, mouseY, mousePressed);
            settingsButton.display(this);
            if (playButton.isClicked()) {
                goToGame();
            } else if (settingsButton.isClicked()) {
                goToSettings();
            }
        } else if (currentPage == settings) {
            previousPage = mainmenu;
            displaySettings();
        } else if (currentPage == playmenu) {
            previousPage = mainmenu;
            displayGameMenu();
        } else if (currentPage == adventuremenu) {
            previousPage = playmenu;
            displayAdventureMenu();
        } else if (currentPage == versusmenu) {
            previousPage = playmenu;
            displayVersusMenu();
        } else if (currentPage == highscoremenu) {
            previousPage = versusmenu;
            displayHighScoreMenu();
        }
    }

    public void stopSound() {
        if (sound != null) {
            sound.stop();
        }
    }

    public void displaySettings() {
        // ...
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);

        // Display volume slider
        float volumeSliderX = width / 2 - 200;
        float volumeSliderY = height / 2 - 10;
        float volumeSliderWidth = 400;
        float volumeSliderHeight = 20;
        float volume = map(currentVolume, 0, 1, 0, volumeSliderWidth);
        fill(200);
        rect(volumeSliderX, volumeSliderY, volumeSliderWidth, volumeSliderHeight);
        fill(0);
        rect(volumeSliderX, volumeSliderY, volume, volumeSliderHeight);
        float volumePercentage = currentVolume * 100;
        String volumeText = "Volume menu: " + nf(volumePercentage, 0, 0) + "%";
        textSize(20);
        textAlign(CENTER, CENTER);
        fill(255);
        text(volumeText, width / 2, volumeSliderY - 30);
        if (mousePressed && mouseX >= volumeSliderX && mouseX <= volumeSliderX + volumeSliderWidth
                && mouseY >= volumeSliderY && mouseY <= volumeSliderY + volumeSliderHeight) {
            float normalizedVolume = constrain(map(mouseX, volumeSliderX, volumeSliderX + volumeSliderWidth, 0, 1), 0,
                    1);
            currentVolume = normalizedVolume;
            sound.amp(currentVolume);
        }

        if (backButton.isClicked()) {
            goToPreviousPage();
        }
    }

    public void displayGameMenu() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        Button adventureButton = new Button(515, 280, 250, 100, "Adventure");
        adventureButton.setImage(loadImage("../assets/buttons/adventure_button.png"));
        adventureButton.display(this);
        adventureButton.update(mouseX, mouseY, mousePressed);
        Button versusButton = new Button(515, 430, 250, 100, "Versus");
        versusButton.setImage(loadImage("../assets/buttons/versus_button.png"));
        versusButton.display(this);
        versusButton.update(mouseX, mouseY, mousePressed);

        if (backButton.isClicked()) {
            goToPreviousPage();
        } else if (adventureButton.isClicked()) {
            goToAdventureMenu();
        } else if (versusButton.isClicked()) {
            goToVersus();
        }
    }

    public void displayVersusMenu() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        playVersusButton.setImage(loadImage("../assets/buttons/start_button.png"));
        playVersusButton.display(this);
        playVersusButton.update(mouseX, mouseY, mousePressed);
        Button highscoreButton = new Button(800, 300, 250, 100, "Highscore");
        highscoreButton.setImage(loadImage("../assets/buttons/highscore_button.png"));
        highscoreButton.display(this);
        highscoreButton.update(mouseX, mouseY, mousePressed);
        if (backButton.isClicked()) {
            goToPreviousPage();
        } else if (playVersusButton.isClicked()) {
            stopSound();
            goToVersusGame();
            playVersusButton.setEnabled(false);
        } else if (highscoreButton.isClicked()) {
            goToHighscore();
        }
    }

    int currentHistoryPage = 1; 
    int itemsPerPage = 10;

    int prevButtonClickTime = 0;
    int nextButtonClickTime = 0;

    public void displayHighScoreMenu() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        ArrayList<Node> hs = ScoreManager.openFile();
        int totalPages = (int) Math.ceil((double) hs.size() / itemsPerPage);
        int startIndex = (currentHistoryPage - 1) * itemsPerPage;
        int endIndex = min(startIndex + itemsPerPage, hs.size());

        for (int i = startIndex; i < endIndex; i++) {
            Node n = hs.get(i);
            long score = n.getScore();
            String text = n.getIndentifier() + " - " + String.valueOf(score);
            textSize(20);
            textAlign(CENTER, CENTER);
            fill(255);
            text(text, width / 2, 100 + (i - startIndex) * 50);
        }

        // Initialize the buttons

        // Draw the buttons
        nextButton.display(this);
        prevButton.display(this);

        // Update the buttons
        nextButton.update(mouseX, mouseY, mousePressed);
        prevButton.update(mouseX, mouseY, mousePressed);
        if (nextButton.isClicked() && (millis() - nextButtonClickTime) > 500) {
            nextButtonClickTime = millis();
            nextPage(totalPages);
        }

        if (prevButton.isClicked() && (millis() - prevButtonClickTime) > 500) {
            prevButtonClickTime = millis();
            goToPreviousHistoryPage();
        }

        if (backButton.isClicked()) {
            goToPreviousPage();
        }
    }

    public void nextPage(int totalPages) {
        if (currentHistoryPage < totalPages) {
            currentHistoryPage++;
        }
    }

    public void goToPreviousHistoryPage() {
        if (currentHistoryPage > 1) {
            currentHistoryPage--;
        }
    }

    public void goToHighscore() {
        currentPage = highscoremenu;
    }

    public void goToSettings() {
        previousPage = currentPage;
        currentPage = settings;
    }

    public void goToPreviousPage() {
        currentPage = previousPage;
    }

    public void goToGame() {
        currentPage = playmenu;
    }

    public void goToAdventureMenu() {
        currentPage = adventuremenu;
    }

    public void goToVersus() {
        currentPage = versusmenu;
    }

    public void displayAdventureMenu() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        level1Button.display(this);
        level1Button.update(mouseX, mouseY, mousePressed);
        level2Button.display(this);
        level2Button.update(mouseX, mouseY, mousePressed);
        level3Button.display(this);
        level3Button.update(mouseX, mouseY, mousePressed);
        level4Button.display(this);
        level4Button.update(mouseX, mouseY, mousePressed);
        level5Button.display(this);
        level5Button.update(mouseX, mouseY, mousePressed);
        if (backButton.isClicked()) {
            goToPreviousPage();
        } else if (level1Button.isClicked()) {
            stopSound();
            System.out.println("Level 1");
            goToLevel1();
            level1Button.setEnabled(false);
        } else if (level2Button.isClicked()) {
            stopSound();
            System.out.println("Level 2");
            goToLevel2();
            level2Button.setEnabled(false);
        } else if (level3Button.isClicked()) {
            stopSound();
            System.out.println("Level 3");
            goToLevel3();
            level3Button.setEnabled(false);
        } else if (level4Button.isClicked()) {
            stopSound();
            System.out.println("Level 4");
            goToLevel4();
            level4Button.setEnabled(false);
        } else if (level5Button.isClicked()) {
            stopSound();
            System.out.println("Level 5");
            goToLevel5();
            level5Button.setEnabled(false);
        }
    }

    public void goToLevel1() {
        String[] levStrings = { "Level1" };
        PApplet.runSketch(levStrings, new Level1(this));
        surface.setVisible(false);
    }

    public void goToLevel2() {
        String[] levStrings = { "Level2" };
        PApplet.runSketch(levStrings, new Level2(this));
        surface.setVisible(false);
    }

    public void goToLevel3() {
        String[] levStrings = { "Level3" };
        PApplet.runSketch(levStrings, new Level3(this));
        surface.setVisible(false);
    }

    public void goToLevel4() {
        String[] levStrings = { "Level4" };
        PApplet.runSketch(levStrings, new Level4(this));
        surface.setVisible(false);
    }

    public void goToLevel5() {
        String[] levStrings = { "Level5" };
        PApplet.runSketch(levStrings, new Level5(this));
        surface.setVisible(false);
    }

    public void goToVersusGame() {
        String[] levStrings = { "Level Versus" };
        PApplet.runSketch(levStrings, new LevelVersus(this));
        surface.setVisible(false);
    }

}
