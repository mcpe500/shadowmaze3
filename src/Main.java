package src;

import src.util.Button;
import src.game.Level;
import src.game.level.Level1;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    private PImage background;
    private Button playButton;
    private Button settingsButton;

    private final int mainmenu = 0;
    private final int settings = 1;
    private final int playmenu = 2;
    private final int adventuremenu = 3;

    private final int jumlahLevel = 5;

    private int currentPage;
    private int previousPage;

    private Level[] levels;

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
                System.out.println("playButton pressed");
                goToGame();
            }
            if (settingsButton.isClicked()) {
                System.out.println("settingsButton pressed");
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
        Button backButton = new Button(50, 50, 100, 50, "Back");
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);

        if (backButton.isClicked()) {
            goToPreviousPage();
        }
    }

    public void displayGameMenu() {
        Button backButton = new Button(50, 50, 100, 50, "Back");
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
        // adventureButton.setImage(loadImage("../assets/sprites/adventure_button.png"));

        if (backButton.isClicked()) {
            goToPreviousPage();
        } else if (adventureButton.isClicked()) {
            System.out.println("adventureButton pressed");
            goToAdventureMenu();
        } else if (versusButton.isClicked()) {
            System.out.println("versusButton pressed");
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
        levels = new Level[jumlahLevel];
        levels[0] = new Level1(this);
        Button backButton = new Button(50, 50, 100, 50, "Back");
        backButton.display(this);
        backButton.update(mouseX, mouseY, mousePressed);
        if (backButton.isClicked()) {
            goToPreviousPage();
        }
        for (int i = 0; i < jumlahLevel; i++) {
            Button levelButton = new Button(300, 300 + (100 * i), 100, 50, "Level " + (i + 1));
            levelButton.display(this);
            levelButton.update(mouseX, mouseY, mousePressed);

            if (levelButton.isClicked()) {
                if(i == 0){
                    levels[i].setup();
                }
                System.out.println("Level " + i + 1);
            }
        }
    }
}
