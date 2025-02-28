package game.itemGenerator;

import java.util.ArrayList;
import java.util.Random;

import game.util.ItemTier;

public class ItemRandomizer {
	private static ArrayList<String> common, uncommon, rare, epic, legend;
	private static int begin=0;
	private static int end=1001;
	
	private static ItemTier getTier() {
		//TODO: Connect luck and floor system
		int luckNumber = new Random().nextInt(begin, end);
		
		if (luckNumber<=550) return ItemTier.COMMON;//55%
		if (luckNumber<=790) return ItemTier.UNCOMMON;//24%
		if (luckNumber<=910) return ItemTier.RARE;//12%
		if (luckNumber==911) return ItemTier.GOD;//0.1%
		if (luckNumber<=971) return ItemTier.EPIC;//6%
		return ItemTier.LEGENDARY;//2.9%
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
