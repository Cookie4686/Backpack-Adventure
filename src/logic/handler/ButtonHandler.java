package logic.handler;

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
		// TODO
	}
}
