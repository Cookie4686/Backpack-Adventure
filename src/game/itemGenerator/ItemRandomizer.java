package game.itemGenerator;

import java.util.Random;

import game.util.ItemTier;

public class ItemRandomizer {
	private static int begin = 0;
	private static int end = 1001;
	private static String[] common = { "Apple", "Beggar Hood", "Book", "Bread", "Cake", "Circle Wood Shield",
			"Cloth Armor", "Coconut", "Coins", "Damage Relic I", "Empty Bottle", "Empty Bronze Cup", "Empty Potion I",
			"Empty Potion II", "Empty Potion III", "Empty Wooden Cup", "Energy Drink I", "Fire Knife", "Fire Potion I",
			"Heal Potion I", "Healthy Drink I", "Ink", "Iron Dagger", "Iron Knife", "Iron Rapier", "Key",
			"Leather Boots", "Lucky Drink I", "Mana Stone I", "Moguro Leaf Drink I", "Poison Potion I", "Rock",
			"Shield Potion I", "Spicy Drink I", "Squre Wood Shield", "Staff of Seed I", "Wide Dagger", "Wood Pole" },

			uncommon = { "Azurite Sword", "Barbuta", "Beer I", "Bomb", "Bread Loaf", "Bronze Shield", "Circular Blade",
					"Croissant", "Damage Relic II", "Empty Long Bottle", "Empty Potion IV", "Empty Potion V",
					"Empty Silver Cup", "Energy Drink II", "Fire Ball", "Fire Potion II", "Fire Potion III",
					"Heal Potion II", "Heal Potion III", "Healthy Drink II", "Iron Saber", "Iron Shuriken",
					"Kettle Helm", "Leather Armor", "Luck Potion I", "Lucky Drink II", "Moguro Leaf Drink II",
					"One Edge Sword", "Orange Inscription I", "Poison Potion II", "Poison Potion III", "Rage Fang",
					"Red Inscription I", "Salad", "Shield Potion II", "Shield Potion III", "Shield Relic I",
					"Spicy Drink II", "Spike Sword", "Staff of Seed II", "Steel Boots", "War Axe" },

			rare = { "Armet", "Bee Hive Sword", "Beer II", "Blood  Drain Shield", "Blood Spike Sword",
					"Blue Inscription I", "Chainmail Armor", "Chocolate", "Cyan Inscription I", "Damage Relic III",
					"Dark Dagger", "Eclipse Sword", "Empty Gold Cup", "Energy Drink III", "Fire Potion IV",
					"Flow Spear", "Gold Sword", "Heal Potion IV", "Healthy Drink III", "Kunai", "Luck Potion II",
					"Lucky Drink III", "Mana Stone II", "Moguro Leaf Drink III", "Natural Shield",
					"Orange Inscription II", "Poison Potion IV", "Red Inscription II", "Shield Potion IV",
					"Shield Relic II", "Spicy Drink III", "Staff of Scarlet", "Staff of Seed III", "Staff of Tree",
					"Talon Boots", "Tech Scythe" },

			epic = { "Beer III", "Blue Inscription II", "Cyan Inscription II", "Damage Relic IV", "Energy Drink IV",
					"Fire Potion V", "Hamburger", "Heal Potion V", "Healthy Drink IV", "Iron Plate", "Long Long Sword",
					"Lucky Drink IV", "Mana Stone III", "Moguro Leaf Drink IV", "Orange Inscription III",
					"Poison Potion V", "Red Inscription III", "Shield Potion V", "Shield Relic III",
					"Siege Warp Hammer", "Soul Eater Sword", "Spicy Drink IV", "Staff of Curse", "Staff of Flame",
					"Staff of Scarlet Sands", "Well Made Shield" },

			legend = { "Blue Inscription III", "Buster Sword", "Curse Sword", "Cyan Inscription III", "Excalibur",
					"Hellfire Calibur", "Holy Calibur", "Shield Relic IV" };

	private static ItemTier getTier() {
		// TODO: Connect luck and floor system
		int luckNumber = new Random().nextInt(begin, end);

		if (luckNumber <= 666)
			return ItemTier.COMMON;// 66.6%
		if (luckNumber <= 790)
			return ItemTier.UNCOMMON;// 15.3%
		if (luckNumber <= 910)
			return ItemTier.RARE;// 9%
		if (luckNumber == 911)
			return ItemTier.GOD;// 0.1%
		if (luckNumber <= 971)
			return ItemTier.EPIC;// 6%
		return ItemTier.LEGENDARY;// 3%
	}

	public static String getNameFromTier(ItemTier itemTier) {
		return switch (itemTier) {
		case COMMON		-> common[new Random().nextInt(common.length)];
		case UNCOMMON	-> uncommon[new Random().nextInt(uncommon.length)];
		case RARE		-> rare[new Random().nextInt(rare.length)];
		case EPIC		-> epic[new Random().nextInt(epic.length)];
		case LEGENDARY	-> legend[new Random().nextInt(legend.length)];
		case GOD		-> "Almighty ProgMeth";
		default			-> {
			System.out.println("itemTier invalid : " + itemTier.toString());
			yield "";
		}
		};
	}

	public static String getRandomItemName() {
		String name = getNameFromTier(getTier());
		System.out.println(name);
		return name;
	}
}
