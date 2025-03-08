package game;

import entities.Entity;
import entities.EntityLoader;
import entities.Player;
import interfaces.ReRenderable;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import logic.FightLogic;

public class GameBottom extends HBox implements ReRenderable {
	private static GameBottom instance;
	private HBox enemyBox;

	public GameBottom() {
		super();
		setPadding(new Insets(8));
		setSpacing(20);

		enemyBox = new HBox();
		enemyBox.setAlignment(Pos.BOTTOM_CENTER);
		enemyBox.setSpacing(16);
		setHgrow(enemyBox, Priority.ALWAYS);
		getChildren().setAll(Player.getInstance(), enemyBox);

		render();
	}

	@Override
	public void render() {
		System.out.println("render all");
		Platform.runLater(() -> {
			Player.getInstance().render();
			enemyBox.getChildren().setAll(FightLogic.getInstance().getEntities());
			for (Entity entity : FightLogic.getInstance().getEntities()) {
				entity.initialize();
				entity.getNextTurnMove().getFadeIn().play();
				FadeTransition ft = new FadeTransition(Duration.millis(500), entity);
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.play();
				animateLayoutChange();
			}
		});
	}

	public void renderTarget(Entity e) {
		System.out.println("render target");
		Platform.runLater(() -> {
			Player.getInstance().render();
			enemyBox.getChildren().setAll(FightLogic.getInstance().getEntities());
			for (Entity entity : FightLogic.getInstance().getEntities()) {
				if (entity == e) {
					FadeTransition ft = new FadeTransition(Duration.millis(800), entity);
					ft.setFromValue(0);
					ft.setToValue(1);
					ft.play();

				}
			}
		});
	}

	public HBox getEnemyBox() {
		return enemyBox;
	}

	public static GameBottom getInstance() {
		if (instance == null) {
			instance = new GameBottom();
		}
		return instance;
	}

	public void addEntity(String name) {
		Entity entity = EntityLoader.newEntity(name);
		FightLogic.getInstance().getEntities().add(entity);
		entity.setOpacity(0);
	}

	public void removeEntity() {
		if (enemyBox.getChildren().isEmpty())
			return;
		Platform.runLater(() -> {
			for (int i = 0; i < enemyBox.getChildren().size(); i++) {
				if (enemyBox.getChildren().get(i) instanceof Entity) {
					Entity e = (Entity) enemyBox.getChildren().get(i);

					if (e.getHp() == 0)
						enemyBox.getChildren().remove(i);
				}
			}
			animateLayoutChange();
		});
	}

	private void animateLayoutChange() {
		for (int i = 0; i < enemyBox.getChildren().size(); i++) {
			Node node = enemyBox.getChildren().get(i);
			if (node instanceof Entity) {
				Entity entity = (Entity) node;
				double newX = i * 10;
				double oldX = entity.getCurrentX();
				if (!entity.isMoving() && newX != oldX) {
					Platform.runLater(() -> {
						System.out.println("move");
						entity.moveTo(newX);
					});
				}
			}
		}
	}

	public static void setInstance(GameBottom instance) {
		GameBottom.instance = instance;
	}
}
