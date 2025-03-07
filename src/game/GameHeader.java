package game;

import entities.Player;
import game.backpack.Backpack;
import interfaces.ReRenderable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import logic.handler.ButtonHandler;
import scene.popup.SettingPopup;
import sound.Sfx;
import sound.SfxPlayer;

public class GameHeader extends HBox implements ReRenderable {
	private static GameHeader instance;
	private Text floorText, experienceText, coinsText;
	private ImageView mapButton;
	private ImageView settingButton;
	private StackPane moneySlot;
	private StackPane expSlot;
	private VBox stat;

	public GameHeader() {
		super();
		setAlignment(Pos.CENTER);
		setPadding(new Insets(5, 10, 5, 10));
		setSpacing(10);
		setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);

		stat = new VBox();
		setAlignment(Pos.TOP_LEFT);
		moneySlot = new StackPane();
		moneySlot.setAlignment(Pos.TOP_LEFT);
		
		expSlot = new StackPane();
		expSlot.setAlignment(Pos.TOP_LEFT);
		
		floorText = new Text();
		floorText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 20));
		floorText.setFill(Color.WHITE);
		
		experienceText = new Text();
		StackPane.setAlignment(experienceText, Pos.CENTER);
		experienceText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 14));
		experienceText.setFill(Color.WHITE);
		
		coinsText = new Text();
		coinsText.setFont(Font.loadFont(ClassLoader.getSystemResource("ModernDOS8x16.ttf").toString(), 14));
		coinsText.setFill(Color.WHITE);
		StackPane.setAlignment(coinsText, Pos.CENTER);
		
		Region region = new Region();
		setHgrow(region, Priority.ALWAYS);
		
		ImageView expBg = new ImageView(new Image(ClassLoader.getSystemResource("picture/expbar.png").toString()));
		expBg.setFitHeight(25);
//		expBg.setFitWidth(25);
		
		ImageView moneyBg = new ImageView(new Image(ClassLoader.getSystemResource("picture/moneybar.png").toString()));
		moneyBg.setFitHeight(25);
//		moneyBg.setFitWidth(25);
		
		mapButton = new ImageView(new Image(ClassLoader.getSystemResource("icons/map.png").toString()));
		mapButton.setPreserveRatio(true);
		mapButton.setFitHeight(50);
		mapButton.setOnMouseClicked(_ -> {
			if (Backpack.getInstance().isLevelup()) {
				SfxPlayer.play(Sfx.DENY);
			}
			else {
				SfxPlayer.play(Sfx.MAP);
				ButtonHandler.handleBackpackButtonOnAction();
			}
		});

		
		settingButton = new ImageView(new Image(ClassLoader.getSystemResource("icons/setting.png").toString()));
		settingButton.setPreserveRatio(true);
		settingButton.setFitHeight(50);
		settingButton.setOnMouseClicked(_ -> {
			SfxPlayer.play(Sfx.SELECT);
			SettingPopup.getInstance().show();
		});
		
		expSlot.getChildren().addAll(expBg, experienceText);
		moneySlot.getChildren().addAll(moneyBg, coinsText);
		
		stat.getChildren().addAll(expSlot, moneySlot, floorText);

		getChildren().setAll(stat, region, settingButton, mapButton);
		render();
	}

	@Override
	public void render() {
		floorText.setText(String.format("Floor: %s", GameLogic.getInstance().getCurrentFloor()));
		experienceText
					.setText(String.format("Exp: %s/%s", Player.getInstance().getXp(), Player.getInstance().getMaxXp()));
		coinsText.setText(String.format("%s", Player.getInstance().getCoins()));
	}

	public static GameHeader getInstance() {
		if (instance == null) {
			instance = new GameHeader();
		}
		return instance;
	}
	
	

	public ImageView getMapButton() {
		return mapButton;
	}

	public void setMapButton(ImageView mapButton) {
		this.mapButton = mapButton;
	}

	public ImageView getSettingButton() {
		return settingButton;
	}

	public void setSettingButton(ImageView settingButton) {
		this.settingButton = settingButton;
	}

	public static void setInstance(GameHeader instance) {
		GameHeader.instance = instance;
	}
}
