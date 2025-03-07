
package scene.popup;

import application.Main;
import component.GameButton;
import component.GameButtonType;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scene.MenuScene;
import sound.Sfx;
import sound.SfxPlayer;

public class GameOverPopup extends Popup {
    private static GameOverPopup instance;

    public GameOverPopup() {
        super("Game Over");

        VBox titleBox = new VBox();
        titleBox.setSpacing(15);
        titleBox.setAlignment(Pos.CENTER);

        Text titleText = new Text("You died.");
        titleText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 64));
        titleText.setFill(Color.WHITE);
        Text titleText2 = new Text("unlucky...");
        titleText2.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 40));
        titleText2.setFill(Color.WHITE);
        titleBox.getChildren().addAll(titleText, titleText2);

        VBox buttonBox = new VBox();
        buttonBox.setSpacing(15);
        buttonBox.setAlignment(Pos.CENTER);

        GameButton newButton = new GameButton(170, 70, GameButtonType.NEW_GAME);
        newButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                SfxPlayer.play(Sfx.SELECT);
                //hide();
                CharacterPopup.getInstance().show();
            }
        });

        GameButton menuButton = new GameButton(170, 70, GameButtonType.MENU);
        menuButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                SfxPlayer.play(Sfx.SELECT);
                hide();
                MenuScene.setGameStarted(false);
                if (!MenuScene.isInMenuScene()) {
                    Main.root.getChildren().clear();
                    MenuScene.use();
                }
            }
        });

        GameButton exitButton = new GameButton(170, 70, GameButtonType.EXIT);
        exitButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Platform.exit();
            }
        });

        buttonBox.getChildren().addAll(newButton, menuButton, exitButton);
        
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(40);
        contentBox.getChildren().addAll(titleBox, buttonBox);
        
        setCenter(contentBox);

        bottomBox.getChildren().clear();
        bottomBox.setPadding(new Insets(0, 0, 0, 0));
    }

    public static GameOverPopup getInstance() {
        if (instance == null) {
            instance = new GameOverPopup();
        }
        return instance;
    }
}