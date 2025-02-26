package game.itemGenerator;

import java.util.ArrayList;
import java.util.Random;

import game.util.ItemTier;

public class ItemRandomizer {
	private static ArrayList<String> common, uncommon, rare, epic, legend;
	private static int begin=0;
	private static int end=100;
	
	private static ItemTier getTier() {
		//TODO: Connect luck and floor system
		int luckNumber = new Random().nextInt(begin, end);
		
		if (luckNumber<=55) return ItemTier.COMMON;		//55%
		if (luckNumber<=79) return ItemTier.UNCOMMON;	//24%
		if (luckNumber<=91) return ItemTier.RARE;		//12%
		if (luckNumber<=97) return ItemTier.EPIC;		//6%
		return ItemTier.LEGENDARY;						//3%
	}
	
	private static String getNameFromTier(ItemTier itemTier) {
		switch (itemTier) {
		case COMMON:
			return common.get(new Random().nextInt(common.size()));
		case UNCOMMON:
			return uncommon.get(new Random().nextInt(uncommon.size()));
		case RARE:
			return rare.get(new Random().nextInt(rare.size()));
		case EPIC:
			return epic.get(new Random().nextInt(epic.size()));
		case LEGENDARY:
			return legend.get(new Random().nextInt(legend.size()));
		default:
			return "";
		}
	}
}
