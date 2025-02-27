package component;

import entities.Being;
import interfaces.ReRenderable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HpBar extends StackPane implements ReRenderable {
	private static Image heartImage;
	private static ColorAdjust shieldAdjust;
	private static int SIZE = 12;
	private Being being;
	private ProgressBar hpBar;
	private double realProgress;
	private ImageView imageView;
	private Text hpBarText, shieldText;

	public HpBar(Being being) {
		super();
		this.being = being;
		if (heartImage == null) {
			heartImage = new Image(ClassLoader.getSystemResource("component/heart.png").toString());
		}
		if (shieldAdjust == null) {
			shieldAdjust = new ColorAdjust();
			shieldAdjust.setHue(222);
			shieldAdjust.setSaturation(1);
			shieldAdjust.setBrightness(0.60);
		}

		setPadding(new Insets(0, 4, 0, 4 + SIZE));

		hpBar = new ProgressBar((double) being.getHp() / being.getMaxHp());
		hpBar.setStyle("-fx-accent: #EF2929;");
		hpBar.setMaxWidth(Double.MAX_VALUE);
		realProgress = hpBar.getProgress();

		hpBarText = new Text();
		hpBarText.setFont(Font.font("Courier New", FontWeight.BOLD, SIZE));
		hpBarText.setFill(Color.WHITE);

		StackPane heartPane = new StackPane();
		heartPane.setMinSize(SIZE * 2.5, SIZE * 2.5);
		heartPane.setMaxSize(SIZE * 2.5, SIZE * 2.5);
		heartPane.setTranslateX(-SIZE);

		shieldText = new Text();
		shieldText.setFont(Font.font("Courier New", FontWeight.NORMAL, SIZE * 1.5));

		imageView = new ImageView(heartImage);
		imageView.setFitWidth(SIZE * 2.5);
		imageView.setFitHeight(SIZE * 2.5);

		heartPane.getChildren().setAll(imageView, shieldText);

		setAlignment(heartPane, Pos.CENTER_LEFT);
		getChildren().setAll(hpBar, hpBarText, heartPane);
	}

	@Override
	public void render() {
		hpBarText.setText(String.format("%s/%s", being.getHp(), being.getMaxHp()));
		setHpBar();
		setShield();
	}

	private void setHpBar() {
		// check if working: prevent bugs when multiple thread are modifying hpBar
		final double temp = realProgress;
		realProgress = (double) being.getHp() / being.getMaxHp();
		Thread thread = new Thread(() -> {
			final int SMOOTHNESS = 50;
			double interval = (temp - (double) being.getHp() / being.getMaxHp()) / SMOOTHNESS;
			for (int i = 0; i < SMOOTHNESS; i++) {
				Platform.runLater(() -> {
					hpBar.setProgress(hpBar.getProgress() - interval);
				});
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	private void setShield() {
		if (being.getShield() > 0) {
			imageView.setEffect(shieldAdjust);
			shieldText.setText(Integer.toString(being.getShield()));
			shieldText.setVisible(true);
		} else if (shieldText.isVisible()) {
			imageView.setEffect(null);
			shieldText.setVisible(false);
		}
	}

}
