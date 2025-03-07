package logic.handler;

import application.Fader;
import entities.Npc;
import entities.Player;
import game.Game;
import game.GameBottom;
import game.GameTop;
import game.dialog.GameDialog;
import game.map.Map;
import game.map.MapMarker;
import game.map.MapSquare;
import game.util.Position;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.util.Duration;
import logic.GameLogic;
import scene.GameScene;
import sound.BackgroundSongPlayer;
import sound.Sfx;
import sound.SfxPlayer;

public class MapHandler {
	public static void handleMouseClicked(MapSquare square) {
		if (!Map.getInstance().isReachable(square)) {
			SfxPlayer.play(Sfx.DENY);
			return;
		}
		
		switch (square.getMarker()) {
		case FINAL		-> {
			GameLogic.getInstance().setDoctor(false);
			GameLogic.getInstance().setBoss(true);
			Player.getInstance().moveLeftAndBack();
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				square.getChildren().clear();
				square.setMarker(MapMarker.PLAYER);
				Map.getInstance().render();
				Map.getInstance().setNoHeal(false);
				GameLogic.getInstance().initializeFight();
			});
			pause.play();
			break;
		}
		case MONSTER	-> {
			GameLogic.getInstance().setDoctor(false);
			Player.getInstance().moveLeftAndBack();
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				Map.getInstance().removePlayerMark();
				square.getChildren().clear();
				square.setMarker(MapMarker.PLAYER);
				Map.getInstance().render();
				Map.getInstance().setNoHeal(false);
				GameLogic.getInstance().initializeFight();
			});
			pause.play();
			break;
		}
		case DOOR		-> {
			GameLogic.getInstance().setDoctor(false);
			Map.getInstance().setNoHeal(false);
			Player.getInstance().moveLeftAndBack();
			if (GameLogic.getInstance().getCurrentSubFloor() == 2) {
				GameLogic.getInstance().setCurrentFloor(GameLogic.getInstance().getCurrentFloor() + 1);
				GameLogic.getInstance().setCurrentSubFloor(0);
			} else {
				GameLogic.getInstance().setCurrentSubFloor(GameLogic.getInstance().getCurrentSubFloor() + 1);
			}
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				GameBottom.getInstance().getEnemyBox().getChildren().clear();
				Game.getInstance().initializeFight();
				generateNewMap();
				BackgroundSongPlayer.floor(GameLogic.getInstance().getCurrentFloor());
			});
			pause.play();
			break;
		}
		// Test dialog
		case PLAYER		-> {
			GameDialog dialog = new GameDialog("Test Dialog");
			dialog.setText(
					"""
							Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Sed laoreet dignissim urna, vel faucibus magna pellentesque sed.
							Ut varius, tortor nec varius cursus, neque neque facilisis metus, quis ultrices mi nulla ullamcorper risus.
							Curabitur vestibulum vel risus eu convallis. Vestibulum quis nunc magna. Curabitur vitae elit scelerisque, pharetra diam in, lacinia libero.
									""");
			dialog.addOption("What", _ -> {
				dialog.hide();
			});
			dialog.addOption("Leave me alonee", _ -> {
				dialog.hide();
			});
			dialog.show();
			break;
		}

		case DOCTOR		-> {
			if (GameLogic.getInstance().isDoctor())
				break;
			GameLogic.getInstance().setDoctor(true);
			Player.getInstance().moveLeftAndBack();
			Fader.fadeOutAndIn();
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			pause.setOnFinished(_ -> {
				Map.getInstance().removePlayerMark();
				square.setMarker(MapMarker.PLAYER);
				Map.getInstance().render();
				Game.getInstance().initializeFight();
				Npc doctor = Npc.getInstance();
				doctor.setAlignment(Pos.BOTTOM_LEFT);
				GameBottom.getInstance().getEnemyBox().getChildren().add(doctor);
				BackgroundSongPlayer.fight(4);

				GameDialog dialog = new GameDialog("Healer");
				dialog.setText("Greetingss traveller, would you like some healing <3");
				dialog.addOption("Sure thing (heal 20 health)", _ -> {
					Map.getInstance().setNoHeal(false);
					Player.getInstance().setHp(Player.getInstance().getHp() + 20);
					dialog.hide();
				});
				dialog.addOption("Yes. (add 5 maxHealth)", _ -> {
					Map.getInstance().setNoHeal(false);
					Player.getInstance().setMaxHp(Player.getInstance().getMaxHp() + 5);
					dialog.hide();
				});
				dialog.addOption("Nah I'm good", _ -> {
					Map.getInstance().setNoHeal(true);
					dialog.hide();
				});
				dialog.show();
			});
			pause.play();
			break;
		}
		default			-> {}
		}
	}

	public static void generateNewMap() {
		// Use a thread-safe operation

		Platform.runLater(() -> {
			Map.getInstance().createNewMap();
			Node oldMap = null;
			for (Node node : GameTop.getMap().getChildren()) {
				if (node instanceof Map) {
					oldMap = node;
					break;
				}
			}

			if (oldMap != null) {
				GameTop.getMap().getChildren().remove(oldMap);
			}

			GameTop.getMap().getChildren().add(Map.getInstance());

			GameScene.use();
		});
	}

}
