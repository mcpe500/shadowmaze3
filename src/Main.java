package src;

import src.util.Button;
import src.game.level.Level;
import src.game.level.Level1;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage background;
    private Button playButton;
    private Button settingsButton;
    private Button backButton;
    private Button level1Button;
    private Button level2Button;
    private Button level3Button;
    private Button level4Button;
    private Button level5Button;

    private final int mainmenu = 0;
    private final int settings = 1;
    private final int playmenu = 2;
    private final int adventuremenu = 3;

    private final int jumlahLevel = 5;

    private int currentPage;
    private int previousPage;

    public static void main(String[] args) {
        Main app = new Main();
        String[] main = { "Main" };
        PApplet.runSketch(main, app);
    }

    public void settings() {
        size(1280, 720);
    }

    public void setup() {
        background = loadImage("../assets/sprites/SL_menu.png");
        background.resize(1280, 720);
        playButton = new Button(100, 100, 100, 100, "Start");
        playButton.setImage(loadImage("../assets/sprites/start_button.png"));
        settingsButton = new Button(200, 200, 200, 100, "Settings");
        settingsButton.setImage(loadImage("../assets/sprites/settings_button.png"));
        backButton = new Button(50, 50, 100, 50, "Back");
        level1Button = new Button(100, 100, 100, 50, "Level 1");
        level2Button = new Button(100, 200, 100, 50, "Level 2");
        level3Button = new Button(100, 300, 100, 50, "Level 3");
        level4Button = new Button(100, 400, 100, 50, "Level 4");
        level5Button = new Button(100, 500, 100, 50, "Level 5");

        currentPage = mainmenu;
        previousPage = mainmenu;
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
            displaySettings();
        } else if (currentPage == playmenu) {
            displayGameMenu();
        } else if (currentPage == adventuremenu) {
            displayAdventureMenu();
        }
    }

    public void displaySettings() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);

        if (backButton.isClicked()) {
            goToPreviousPage();
        }
    }

    public void displayGameMenu() {
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        Button adventureButton = new Button(300, 300, 100, 50, "Adventure");
        adventureButton.setImage(loadImage("../assets/sprites/adventure_button.png"));
        adventureButton.display(this);
        adventureButton.update(mouseX, mouseY, mousePressed);
        Button versusButton = new Button(300, 400, 100, 50, "Versus");
        versusButton.setImage(loadImage("../assets/sprites/versus_button.png"));
        versusButton.display(this);
        versusButton.update(mouseX, mouseY, mousePressed);

        if (backButton.isClicked()) {
            goToPreviousPage();
        } else if (adventureButton.isClicked()) {
            goToAdventureMenu();
        } else if (versusButton.isClicked()) {
        }
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
            System.out.println("Level 1");
            goToLevel1();
            level1Button.setEnabled(false);
        }
    }

    public void goToLevel1() {
        String[] levStrings = { "Level1" };
        PApplet.runSketch(levStrings, new Level1(this));
        surface.setVisible(false);
    }

}
