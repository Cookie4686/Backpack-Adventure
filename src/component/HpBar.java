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
	private static int SIZE = 12;
	private static Image heartImage;
	private static ColorAdjust shieldAdjust;
	private Being being;
	private ProgressBar hpBar;
	private double realProgress;
	private ImageView heartImageView;
	private Text hpBarText, shieldText;
	private Thread thread;

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

		heartImageView = new ImageView(heartImage);
		heartImageView.setFitWidth(SIZE * 2.5);
		heartImageView.setFitHeight(SIZE * 2.5);

		heartPane.getChildren().setAll(heartImageView, shieldText);

		setAlignment(heartPane, Pos.CENTER_LEFT);
		getChildren().setAll(hpBar, hpBarText, heartPane);
	}

	@Override
	public void render() {
		setHpBar();
		setShield();
	}

	public void setHpBar() {
		// to prevent bugs when multiple thread are modifying hpBar
		if (thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		final int hp = being.getHp() > being.getMaxHp() ? being.getMaxHp() : being.getHp(); // no overheal show
		hpBarText.setText(String.format("%s/%s", hp, being.getMaxHp()));
		final double temp = realProgress;
		realProgress = (double) hp / being.getMaxHp();
		thread = new Thread(() -> {
			final int SMOOTHNESS = 50;
			double interval = (temp - (double) hp / being.getMaxHp()) / SMOOTHNESS;
			for (int i = 0; i < SMOOTHNESS; i++) {
				Platform.runLater(() -> {
					hpBar.setProgress(hpBar.getProgress() - interval < 0 ? 0 : hpBar.getProgress() - interval);
				});
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		});
		thread.start();
	}

	public void setShield() {
		if (being.getShield() > 0) {
			heartImageView.setEffect(shieldAdjust);
			shieldText.setText(Integer.toString(being.getShield()));
			shieldText.setVisible(true);
		} else if (shieldText.isVisible()) {
			heartImageView.setEffect(null);
			shieldText.setVisible(false);
		}
	}
}
