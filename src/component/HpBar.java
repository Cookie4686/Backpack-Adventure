package component;

import entities.Being;
import interfaces.ReRenderable;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HpBar extends StackPane implements ReRenderable {
	private Being being;
	private ProgressBar hpBar;
	private Text text;

	public HpBar(Being being) {
		super();
		setPadding(new Insets(4));
		this.being = being;
		hpBar = new ProgressBar((double) being.getHp() / being.getMaxHp());
		hpBar.setStyle("-fx-accent: #EF2929;");
		text = new Text();
		getChildren().setAll(hpBar, text);
		render();
	}

	@Override
	public void render() {
		text.setText(String.format("%s/%s", being.getHp(), being.getMaxHp()));
		setHpBar();
	}

	public void setHpBar() {
		final int SMOOTHNESS = 50;
		Thread thread = new Thread(() -> {
			double interval = (hpBar.getProgress() - (double) being.getHp() / being.getMaxHp()) / SMOOTHNESS;
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

}
