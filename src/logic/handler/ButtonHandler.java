package logic.handler;

import entities.Player;
import game.GameTop;
import game.backpack.Slot;
import logic.FightLogic;

public class ButtonHandler {
	public static void handleBackpackButtonOnAction() {
		if (!FightLogic.getInstance().isInFight()) {
			if (GameTop.getInstance().isBackpack()) {
				GameTop.getInstance().useMap();
			} else {
				GameTop.getInstance().useBackpack();
			}
		}
	}

	public static void handleSlotOnClicked(Slot slot) {
		if (!slot.isUnlocked() && !FightLogic.getInstance().isInFight()) {
			slot.setUnlocked(true);
		}
	}

	public static void handleEndTurnButtonOnAction() {
		if (FightLogic.getInstance().isInFight() && FightLogic.getInstance().isPTurn() && Player.getInstance().getHp() != 0) {	
			FightLogic.getInstance().setPTurn(false);
			FightLogic.getInstance().entitiesTurn();
		}
	}
}
