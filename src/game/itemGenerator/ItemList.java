package game.itemGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import game.item.Coins;
import game.item.DamageItem;
import game.item.ManaOrb;
import game.item.ShieldItem;
import game.item.consumable.Consumable;
import game.item.consumable.Container;
import game.item.consumable.FoodWithContainer;
import game.item.relic.ActiveRelic;
import game.item.relic.PassiveRelic;
import game.item.weapon.IncreaseCostWeapon;
import game.item.weapon.ManaWeapon;
import game.item.weapon.SpecialWeapon;
import game.item.weapon.Weapon;
import game.item.wearable.Armor;
import game.item.wearable.Helmet;
import game.util.Effect;
import game.util.EffectType;
import game.util.ItemTier;

public class ItemList {
	private static HashMap<String, Resource> itemMap;
	
	static {
		itemMap = new HashMap<String, Resource>();
		
//		Weapon(String name, String detail, int initdamage, int costActivate, int width, int height, ItemTier tier)  Not diagonal 
		itemMap.put("Almighty ProgMeth", new Resource(() -> new Weapon("Almighty ProgMeth", "Radiates a mystical blue glow.", 40, 1, 1, 2, ItemTier.GOD), "Items/Progmeth.png"));
		itemMap.put("Azurite Sword", new Resource(() -> new Weapon("Azurite Sword", "Radiates a mystical blue glow.", 40, 1, 1, 2, ItemTier.UNCOMMON), "Items/AzuriteSword.png"));
		itemMap.put("Book", new Resource(() -> new Weapon("Book", "It built for read not fight.", 20, 1, 1, 1, ItemTier.COMMON), "Items/Book.png"));
		itemMap.put("Buster Sword", new Resource(() -> new Weapon("Buster Sword", "Heavy blade designed for raw power over finesse.", 500, 1, 1, 3, ItemTier.LEGENDARY), "Items/BusterSword.png"));
		itemMap.put("Gold Sword", new Resource(() -> new Weapon("Gold Sword", "Gold Gold Gold.", 150, 1, 1, 2, ItemTier.RARE), "Items/GoldSword.png"));
		itemMap.put("Iron Knife", new Resource(() -> new Weapon("Iron Knife", "Do you love cooking?", 20, 1, 1, 1, ItemTier.COMMON), "Items/IronKnife.png"));
		itemMap.put("One Edge Sword", new Resource(() -> new Weapon("One Edge Sword", "Time to strike.", 60, 1, 1, 2, ItemTier.UNCOMMON), "Items/OneEdgeSowrd.png"));
		itemMap.put("Wood Pole", new Resource(() -> new Weapon("Wood Pole", "Use for training javelin.", 20, 1, 1, 3, ItemTier.COMMON), "Items/WoodPole.png"));
		
		
//		Weapon(String name, String detail, int initDamage, int costActivate, int width, ItemTier tier) Diagonal 
		itemMap.put("Iron Saber", new Resource(() -> new Weapon("Iron Knife", "Like a pirate.", 55, 1, 2, ItemTier.UNCOMMON), "Items/IronSaber.png"));
		
		
//		SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, int height, ItemTier tier) Not diagonal 
		itemMap.put("Bee Hive Sword", new Resource(() -> new SpecialWeapon("Bee Hive Sword", "Radiates a mystical blue glow.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.SHIELD), new Effect(10, EffectType.POISON))), 110, 1, 1, 3, ItemTier.RARE), "Items/BeeHive.png"));
		itemMap.put("Blood Spike Sword", new Resource(() -> new SpecialWeapon("Blood Spike Sword", "A sinister blade thirsts for blood.", new ArrayList<Effect>(Arrays.asList(new Effect(30, EffectType.POISON), new Effect(5, EffectType.VAMPIRIC))), 200, 2, 1, 2, ItemTier.RARE), "Items/BloodSpikeSword.png"));
		itemMap.put("Eclipse Sword", new Resource(() -> new SpecialWeapon("Eclipse Sword", "A sword from outer space.", new ArrayList<Effect>(Arrays.asList(new Effect(1, EffectType.LUCK))), 110, 1, 1, 2, ItemTier.RARE), "Items/EclipseSword.png"));
		itemMap.put("Excalibur", new Resource(() -> new SpecialWeapon("Excalibur", "The chosen one.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.HEAL), new Effect(20, EffectType.SHIELD), new Effect(120, EffectType.POISON))), 500, 2, 1, 2, ItemTier.LEGENDARY), "Items/Excalibur.png"));
		itemMap.put("Hellfire Calibur", new Resource(() -> new SpecialWeapon("Hellfire Calibur", "It burns with unholy fire.", new ArrayList<Effect>(Arrays.asList(new Effect(120, EffectType.FIRE))), 430, 2, 1, 3, ItemTier.LEGENDARY), "Items/HellfireCalibur.png"));
		itemMap.put("Holy Calibur", new Resource(() -> new SpecialWeapon("Holy Calibur", "A radiant sword forged in divine light.", new ArrayList<Effect>(Arrays.asList(new Effect(120, EffectType.POISON), new Effect(10, EffectType.SHIELD))), 400, 2, 1, 3, ItemTier.LEGENDARY), "Items/HolyCalibur.png"));
		itemMap.put("Long Long Sword", new Resource(() -> new SpecialWeapon("Long Long Sword", "L-long sword a bit too long.", new ArrayList<Effect>(Arrays.asList(new Effect(2, EffectType.LUCK))), 300, 2, 1, 5, ItemTier.EPIC), "Items/LongLongSword.png"));
		itemMap.put("Rage Fang", new Resource(() -> new SpecialWeapon("Rage Fang", "This sword make me rage.", new ArrayList<Effect>(Arrays.asList(new Effect(3, EffectType.ANGER))), 85, 2, 1, 2, ItemTier.UNCOMMON), "Items/RageSword.png"));
		itemMap.put("Siege Warp Hammer", new Resource(() -> new SpecialWeapon("Siege Warp Hammer", "Its powerful strikes create shockwaves.", new ArrayList<Effect>(Arrays.asList(new Effect(1, EffectType.STUNTED))), 500, 4, 2, 3, ItemTier.EPIC), "Items/SiegeWarp.png"));
		itemMap.put("Soul Eater Sword", new Resource(() -> new SpecialWeapon("Soul Eater Sword", "Each strike drains the life force.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.VAMPIRIC))), 250, 1, 1, 2, ItemTier.EPIC), "Items/SoulEater.png"));
		itemMap.put("Spike Sword", new Resource(() -> new SpecialWeapon("Spike Sword", "A sword with sharp spike around.", new ArrayList<Effect>(Arrays.asList(new Effect(5, EffectType.POISON))), 40, 1, 1, 2, ItemTier.UNCOMMON), "Items/SpikeSword.png"));
		itemMap.put("Fire Knife", new Resource(() -> new SpecialWeapon("Fire Knife", "Injection hot liquid.", new ArrayList<Effect>(Arrays.asList(new Effect(2, EffectType.FIRE))), 15, 1, 1, 1, ItemTier.COMMON), "Items/FireKnife.png"));
		                  												
//		SpecialWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, ItemTier tier) diagonal
		itemMap.put("Curse Sword", new Resource(() -> new SpecialWeapon("Curse Sword", "A blade infused with dark magic.", new ArrayList<Effect>(Arrays.asList(new Effect(120, EffectType.POISON))), 450, 2, 2, ItemTier.LEGENDARY), "Items/CurseSword.png"));
		itemMap.put("Dark Dagger", new Resource(() -> new SpecialWeapon("Dark Dagger", "A small but deadly dagger forged in the shadows.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.VAMPIRIC))), 80, 1, 1, ItemTier.RARE), "Items/DarkDagger.png"));
		
//		ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, int height, ItemTier tier) not diagonal
		itemMap.put("Staff of Curse", new Resource(() -> new ManaWeapon("Staff of Curse", "An ominous staff imbued with dark magic.", new ArrayList<Effect>(Arrays.asList(new Effect(100, EffectType.POISON))), 100, 2, 1, 3, ItemTier.EPIC), "Items/CurseStaff.png"));
		itemMap.put("Staff of Flame", new Resource(() -> new ManaWeapon("Staff of Flame", "A powerful staff with the essence of fire.", new ArrayList<Effect>(Arrays.asList(new Effect(200, EffectType.FIRE))), 10, 1, 1, 2, ItemTier.EPIC), "Items/FlameStaff.png"));
		itemMap.put("Staff of Scarlet", new Resource(() -> new ManaWeapon("Staff of Scarlet", "A staff infused with 3 element.", new ArrayList<Effect>(Arrays.asList(new Effect(50, EffectType.FIRE), new Effect(20, EffectType.REGEN), new Effect(1, EffectType.DODGE))), 10, 2, 1, 2, ItemTier.RARE), "Items/ScarletStaff.png"));
		itemMap.put("Staff of Scarlet Sands", new Resource(() -> new ManaWeapon("Staff of Scarlet Sands", "Used by protector or pyramid.", new ArrayList<Effect>(Arrays.asList(new Effect(40, EffectType.FIRE), new Effect(10, EffectType.SHIELD))), 200, 1, 1, 3, ItemTier.EPIC), "Items/ScarletSands.png"));
		itemMap.put("Staff of Seed I", new Resource(() -> new ManaWeapon("Staff of Seed I", "It look like a seed.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.REGEN))), 0, 1, 1, 2, ItemTier.COMMON), "Items/Seed1.png"));
		itemMap.put("Staff of Seed II", new Resource(() -> new ManaWeapon("Staff of Seed II", "It look like a seed.", new ArrayList<Effect>(Arrays.asList(new Effect(20, EffectType.REGEN))), 0, 1, 1, 2, ItemTier.UNCOMMON), "Items/Seed2.png"));
		itemMap.put("Staff of Seed III", new Resource(() -> new ManaWeapon("Staff of Seed III", "It look like a seed.", new ArrayList<Effect>(Arrays.asList(new Effect(40, EffectType.REGEN))), 0, 1, 1, 2, ItemTier.RARE), "Items/Seed3.png"));
		itemMap.put("Staff of Tree", new Resource(() -> new ManaWeapon("Staff of Tree", "The heartwood of an ancient tree.", new ArrayList<Effect>(Arrays.asList(new Effect(3, EffectType.LUCK), new Effect(2, EffectType.HEAL))), 100, 1, 1, 2, ItemTier.RARE), "Items/TreeStaff.png"));
		
//		ManaWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int width, ItemTier tier) diagonal
		
//		IncreaseCostWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int increaseCost, int width, ItemTier tier) diagonal
		itemMap.put("Iron Rapier", new Resource(() -> new IncreaseCostWeapon("Iron Rapier", "A slender, precision and speed.", new ArrayList<Effect>(), 20, 0, 1, 2, ItemTier.COMMON), "Items/IronRapier.png"));
		
//		IncreaseCostWeapon(String name, String detail, ArrayList<Effect> effects, int damage, int costActivate, int increaseCost, int width, int height, ItemTier tier) not diagonal
		itemMap.put("Flow Spear", new Resource(() -> new IncreaseCostWeapon("Flow Spear", "Its fluid strikes adapt to any battle.", new ArrayList<Effect>(), 90, 0, 1, 1, 3, ItemTier.RARE), "Items/FlowSpear.png"));
		itemMap.put("Iron Dagger", new Resource(() -> new IncreaseCostWeapon("Iron Dagger", "Quick strikes and silent takedowns.", new ArrayList<Effect>(), 10, 0, 1, 1, 1, ItemTier.COMMON), "Items/Dagger.png"));
		itemMap.put("War Axe", new Resource(() -> new IncreaseCostWeapon("War Axe", "A real axe.", new ArrayList<Effect>(), 30, 1, 1, 1, 2, ItemTier.UNCOMMON), "Items/WarAxe.png"));
		itemMap.put("Tech Scythe", new Resource(() -> new IncreaseCostWeapon("Tech Scythe", "A futuristic scythe with components.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.POISON), new Effect(10, EffectType.SHIELD))), 100, 1, 1, 1, 2, ItemTier.RARE), "Items/TechScythe.png"));
		itemMap.put("Wide Dagger", new Resource(() -> new IncreaseCostWeapon("Wide Dagger", "Wide blade designed for powerful slashes.", new ArrayList<Effect>(), 12, 0, 1, 1, 1, ItemTier.COMMON), "Items/WideDagger.png"));
		
		
//		DamageItem(String name, String detail, ArrayList<Effect> effects, int costActivate, boolean isAoE, int width, int height, ItemTier tier)
		itemMap.put("Bomb", new Resource(() -> new DamageItem("Bomb", "Explosion BOOM!!", new ArrayList<Effect>(Arrays.asList(new Effect(30, EffectType.DAMAGE))), 1, true, 1, 1, ItemTier.UNCOMMON), "Items/Bomb.png"));
		itemMap.put("Circular Blade", new Resource(() -> new DamageItem("Circular Blade", "A razor-sharp rotating disc.", new ArrayList<Effect>(Arrays.asList(new Effect(40, EffectType.DAMAGE))), 1, false, 1, 1, ItemTier.UNCOMMON), "Items/CircularBlade.png"));
		itemMap.put("Fire Ball", new Resource(() -> new DamageItem("Fire Ball", "Scorching enemy with intense flames.", new ArrayList<Effect>(Arrays.asList(new Effect(30, EffectType.DAMAGE), new Effect(10, EffectType.FIRE))), 0, false, 1, 1, ItemTier.UNCOMMON), "Items/FireBall.png"));
		itemMap.put("Fire Potion I", new Resource(() -> new DamageItem("Fire Potion I", "Molly.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.FIRE))), 1, true, 1, 1, ItemTier.COMMON), "Items/FirePotion1.png"));
		itemMap.put("Fire Potion II", new Resource(() -> new DamageItem("Fire Potion II", "Molly.", new ArrayList<Effect>(Arrays.asList(new Effect(20, EffectType.FIRE))), 1, true, 1, 1, ItemTier.UNCOMMON), "Items/FirePotion2.png"));
		itemMap.put("Fire Potion III", new Resource(() -> new DamageItem("Fire Potion III", "Molly.", new ArrayList<Effect>(Arrays.asList(new Effect(40, EffectType.FIRE))), 1, true, 1, 1, ItemTier.UNCOMMON), "Items/FirePotion3.png"));
		itemMap.put("Fire Potion IV", new Resource(() -> new DamageItem("Fire Potion IV", "Molly.", new ArrayList<Effect>(Arrays.asList(new Effect(80, EffectType.FIRE))), 1, false, 1, 1, ItemTier.RARE), "Items/FirePotion4.png"));
		itemMap.put("Fire Potion V", new Resource(() -> new DamageItem("Fire Potion V", "Molly.", new ArrayList<Effect>(Arrays.asList(new Effect(160, EffectType.FIRE))), 1, false, 1, 1, ItemTier.EPIC), "Items/FirePotion5.png"));
		itemMap.put("Ink", new Resource(() -> new DamageItem("Ink", "No need for pen.", new ArrayList<Effect>(Arrays.asList(new Effect(5, EffectType.POISON))), 0, false, 1, 1, ItemTier.COMMON), "Items/ink.png"));
		itemMap.put("Iron Shuriken", new Resource(() -> new DamageItem("Iron Shuriken", "Lightweight and deadly.", new ArrayList<Effect>(Arrays.asList(new Effect(30, EffectType.DAMAGE))), 0, false, 1, 1, ItemTier.UNCOMMON), "Items/IronShuriken.png"));
		itemMap.put("Key", new Resource(() -> new DamageItem("Key", "Its useless for now. Just throw it.", new ArrayList<Effect>(Arrays.asList(new Effect(5, EffectType.DAMAGE))), 0, false, 1, 1, ItemTier.COMMON), "Items/Key.png"));
		itemMap.put("Kunai", new Resource(() -> new DamageItem("Kunai", "Dagger-like throwing weapon favored by ninjas.", new ArrayList<Effect>(Arrays.asList(new Effect(50, EffectType.DAMAGE))), 0, false, 1, 1, ItemTier.RARE), "Items/Kunai.png"));
		itemMap.put("Poison Potion I", new Resource(() -> new DamageItem("Poison Potion I", "A vile concoction brewed with deadly toxins.", new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.POISON))), 1, true, 1, 1, ItemTier.COMMON), "Items/PoisonPotion1.png"));
		itemMap.put("Poison Potion II", new Resource(() -> new DamageItem("Poison Potion II", "A vile concoction brewed with deadly toxins.", new ArrayList<Effect>(Arrays.asList(new Effect(20, EffectType.POISON))), 1, true, 1, 1, ItemTier.UNCOMMON), "Items/PoisonPotion2.png"));
		itemMap.put("Poison Potion III", new Resource(() -> new DamageItem("Poison Potion III", "A vile concoction brewed with deadly toxins.", new ArrayList<Effect>(Arrays.asList(new Effect(40, EffectType.POISON))), 1, true, 1, 1, ItemTier.UNCOMMON), "Items/PoisonPotion3.png"));
		itemMap.put("Poison Potion IV", new Resource(() -> new DamageItem("Poison Potion IV", "A vile concoction brewed with deadly toxins.", new ArrayList<Effect>(Arrays.asList(new Effect(80, EffectType.POISON))), 1, false, 1, 1, ItemTier.RARE), "Items/PoisonPotion4.png"));
		itemMap.put("Poison Potion V", new Resource(() -> new DamageItem("Poison Potion V", "A vile concoction brewed with deadly toxins.", new ArrayList<Effect>(Arrays.asList(new Effect(160, EffectType.POISON))), 1, false, 1, 1, ItemTier.EPIC), "Items/PoisonPotion5.png"));
		itemMap.put("Rock", new Resource(() -> new DamageItem("Rock", "A rock from around here.", new ArrayList<Effect>(), 0, false, 1, 1, ItemTier.COMMON), "Items/Rock.png"));
		
		
		
//		ShieldItem(String name, String detail, int shield, ArrayList<Effect> effects, int costActivate, int width, int height, ItemTier tier)
		itemMap.put("Blood  Drain Shield", new Resource(() -> new ShieldItem("Blood Drain Shield", "It absorbs damage in exchange for the vitality but who.", 20, new ArrayList<Effect>(Arrays.asList(new Effect(20, EffectType.THORN))), 2, 1, 1, ItemTier.RARE), "Items/BloodDrainShield.png"));
		itemMap.put("Bronze Shield", new Resource(() -> new ShieldItem("Bronze Shield", "Though heavier than wooden counterparts.", 30, new ArrayList<Effect>(), 1, 2, 2, ItemTier.UNCOMMON), "Items/BronzeShield.png"));
		itemMap.put("Circle Wood Shield", new Resource(() -> new ShieldItem("Circle Wood Shield", "A reinforced wooden shield with a circular design.", 15, new ArrayList<Effect>(), 1, 2, 2, ItemTier.COMMON), "Items/CircleWoodShield.png"));
		itemMap.put("Natural Shield", new Resource(() -> new ShieldItem("Natural Shield", "Touch some nature.", 25, new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.REGEN))), 2, 2, 2, ItemTier.RARE), "Items/NaturalShield.png"));
		itemMap.put("Squre Wood Shield", new Resource(() -> new ShieldItem("Squre Wood Shield", "Design for maximum coverage.", 20, new ArrayList<Effect>(), 1, 2, 2, ItemTier.COMMON), "Items/SqureWoodShield.png"));
		itemMap.put("Well Made Shield", new Resource(() -> new ShieldItem("Well Made Shield", "A finely crafted shield.", 40, new ArrayList<Effect>(), 1, 2, 2, ItemTier.EPIC), "Items/WellMadeShield.png"));
		
		
		
//		Shoes(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier) 
		itemMap.put("Leather Boots", new Resource(() -> new Armor("Leather Boots", "Sturdy boots crafted from durable leather.", 5, 1, new ArrayList<Effect>(Arrays.asList(new Effect(5, EffectType.HEAL))), 2, 1, ItemTier.COMMON), "Items/LeatherBoots.png"));
		itemMap.put("Steel Boots", new Resource(() -> new Armor("Steel Boots", "Leather boots decorate with steel.", 10, 2, new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.HEAL))), 2, 1, ItemTier.UNCOMMON), "Items/SteelBoots.png"));
		itemMap.put("Talon Boots", new Resource(() -> new Armor("Talon Boots", "Steel boots with spike on a tip.", 15, 1, new ArrayList<Effect>(Arrays.asList(new Effect(5, EffectType.THORN), new Effect(15, EffectType.HEAL))), 2, 1, ItemTier.RARE), "Items/TalonBoots.png"));
		
//		Armor(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier)
		itemMap.put("Chainmail Armor", new Resource(() -> new Armor("Chainmail Armor", "Favored by both mobility and defense.", 20, 1, new ArrayList<Effect>(Arrays.asList(new Effect(20, EffectType.HEAL))), 2, 2, ItemTier.RARE), "Items/Chainmail.png"));
		itemMap.put("Cloth Armor", new Resource(() -> new Armor("Cloth Armor", "Simple cloth.", 5, 1, new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.HEAL))), 2, 2, ItemTier.COMMON), "Items/ClothArmor.png"));
		itemMap.put("Iron Plate", new Resource(() -> new Armor("Iron Plate", "No One able to hit you now.", 30, 2, new ArrayList<Effect>(Arrays.asList(new Effect(30, EffectType.HEAL))), 2, 2, ItemTier.EPIC), "Items/IronPlate.png"));
		itemMap.put("Leather Armor", new Resource(() -> new Armor("Leather Armor", "Cool looking design.", 5, 2, new ArrayList<Effect>(Arrays.asList(new Effect(15, EffectType.HEAL), new Effect(1, EffectType.ENERGY))), 2, 2, ItemTier.UNCOMMON), "Items/LeatherArmor.png"));

//		Helmet(String name, String detail, int initialShield, int increaseShield, ArrayList<Effect> effects, int width, int height, ItemTier tier)
		itemMap.put("Armet", new Resource(() -> new Helmet("Armet", "This is what I call a Knight.", 10, 2, new ArrayList<Effect>(Arrays.asList(new Effect(1, EffectType.THORN), new Effect(15, EffectType.HEAL))), 1, 1, ItemTier.RARE), "Items/Armet.png"));
		itemMap.put("Barbuta", new Resource(() -> new Helmet("Barbuta", "Leather armor reinforced with iron.", 5, 1, new ArrayList<Effect>(), 1, 1, ItemTier.UNCOMMON), "Items/Barbuta.png"));
		itemMap.put("Beggar Hood", new Resource(() -> new Helmet("Beggar Hood", "A hood like a thief", 1, 1, new ArrayList<Effect>(Arrays.asList(new Effect(1, EffectType.ANGER), new Effect(5, EffectType.HEAL), new Effect(1, EffectType.ENERGY))), 1, 1, ItemTier.COMMON), "Items/BeggarHood.png"));
		itemMap.put("Kettle Helm", new Resource(() -> new Helmet("Kettle Helm", "First step for a warrior", 5, 1, new ArrayList<Effect>(Arrays.asList(new Effect(10, EffectType.HEAL))), 1, 1, ItemTier.UNCOMMON), "Items/KettleHelm.png"));
		

//		Consumable(String name, String detail, int durability, Effect effect, int costActivate, int width, int height, ItemTier tier)
		itemMap.put("Apple", new Resource(() -> new Consumable("Apple", "No poison inside, I guess.", 1, new Effect(5, EffectType.HEAL), 1, 1, 1, ItemTier.COMMON), "Items/apple.png"));
		itemMap.put("Bread", new Resource(() -> new Consumable("Bread", "Just a dry bread", 2, new Effect(1, EffectType.ENERGY), 0, 1, 1, ItemTier.COMMON), "Items/Bread.png"));
		itemMap.put("Bread Loaf", new Resource(() -> new Consumable("Bread Loaf", "Baked to golden perfection", 4, new Effect(1, EffectType.ENERGY), 0, 1, 1, ItemTier.UNCOMMON), "Items/BreadLoaf.png"));
		itemMap.put("Cake", new Resource(() -> new Consumable("Cake", "Sweet and delightful", 1, new Effect(2, EffectType.ENERGY), 0, 1, 1, ItemTier.COMMON), "Items/Cake.png"));
		itemMap.put("Chocolate", new Resource(() -> new Consumable("Chocolate", "A rich and sweet delicacy made from cacao beans.", 2, new Effect(2, EffectType.ENERGY), 0, 1, 1, ItemTier.RARE), "Items/Chocolate.png"));
		itemMap.put("Coconut", new Resource(() -> new Consumable("Coconut", "Coconut water from a mature coconut.", 1, new Effect(1, EffectType.ENERGY), 0, 1, 1, ItemTier.COMMON), "Items/Coconut.png"));
		itemMap.put("Croissant", new Resource(() -> new Consumable("Croissant", "This could mean something else.", 2, new Effect(2, EffectType.ENERGY), 0, 1, 1, ItemTier.UNCOMMON), "Items/Croissant.png"));
		itemMap.put("Hamburger", new Resource(() -> new Consumable("Hamburger", "Juicy meat patty.", 4, new Effect(2, EffectType.ENERGY), 0, 1, 1, ItemTier.EPIC), "Items/Hamburger.png"));
		itemMap.put("Salad", new Resource(() -> new Consumable("Salad", "A fresh vegetables not other.", 4, new Effect(1, EffectType.ENERGY), 0, 1, 1, ItemTier.UNCOMMON), "Items/Salad.png"));
		
//		Potion(String name, String detail, String container, int costActivate, int durability, Effect effect, int width, int height, ItemTier tier)
		
//		Container(String name, String detail, int damage, int width, int height, ItemTier tier)
		itemMap.put("Empty Bottle", new Resource(() -> new Container("Empty Bottle", "A clear empty bottle.", 10, 1, 1, ItemTier.COMMON), "Items/EmptyBottle.png"));
		itemMap.put("Empty Bronze Cup", new Resource(() -> new Container("Empty Bronze Cup", "A empty strong cup.", 16, 1, 1, ItemTier.COMMON), "Items/EmptyBronzeCup.png"));
		itemMap.put("Empty Gold Cup", new Resource(() -> new Container("Empty Gold Cup", "Shiny, you really want to throw it?", 64, 1, 1, ItemTier.RARE), "Items/EmptyGoldCup.png"));
		itemMap.put("Empty Long Bottle", new Resource(() -> new Container("Empty Long Bottle", "BTW, Its not a sugar.", 20, 1, 2, ItemTier.UNCOMMON), "Items/EmptyLongBottle.png"));
		itemMap.put("Empty Silver Cup", new Resource(() -> new Container("Empty Silver Cup", "Luxury.", 32, 1, 1, ItemTier.UNCOMMON), "Items/EmptySilverCup.png"));
		itemMap.put("Empty Wooden Cup", new Resource(() -> new Container("Empty Wooden Cup", "Classic.", 8, 1, 1, ItemTier.COMMON), "Items/EmptyWoodenCup.png"));
		itemMap.put("Empty Potion I", new Resource(() -> new Container("Empty Potion I", "Too small to store water.", 10, 1, 1, ItemTier.COMMON), "Items/EmptyPotion1.png"));
		itemMap.put("Empty Potion II", new Resource(() -> new Container("Empty Potion II", "Normal bottle.", 20, 1, 1, ItemTier.COMMON), "Items/EmptyPotion2.png"));
		itemMap.put("Empty Potion III", new Resource(() -> new Container("Empty Potion III", "BTW, Its not a sugar.", 30, 1, 1, ItemTier.COMMON), "Items/EmptyPotion3.png"));
		itemMap.put("Empty Potion IV", new Resource(() -> new Container("Empty Potion IV", "Its a jar.", 40, 1, 1, ItemTier.UNCOMMON), "Items/EmptyPotion4.png"));
		itemMap.put("Empty Potion V", new Resource(() -> new Container("Empty Potion V", "Large bottle", 50, 1, 1, ItemTier.UNCOMMON), "Items/EmptyPotion5.png"));
		
		
//		FoodWithContainer(String name, String detail, String container, int costActivate, int durability, Effect effect, int width,int height, ItemTier tier)
		itemMap.put("Beer I", new Resource(() -> new FoodWithContainer("Beer I", "A frothy mug of golden ale.", "Empty Wooden Cup", 1, 1, new Effect(1, EffectType.DODGE), 1, 1, ItemTier.UNCOMMON), "Items/Beer1.png"));
		itemMap.put("Beer II", new Resource(() -> new FoodWithContainer("Beer II", "Golden ale served in a sturdy bronze cup.", "Empty Bronze Cup", 1, 1, new Effect(2, EffectType.DODGE), 1, 1, ItemTier.RARE), "Items/Beer2.png"));
		itemMap.put("Beer III", new Resource(() -> new FoodWithContainer("Beer III", "The metallic chill enhances its crisp taste.", "Empty Silver Cup", 1, 1, new Effect(3, EffectType.DODGE), 1, 1, ItemTier.EPIC), "Items/Beer3.png"));
		itemMap.put("Energy Drink I", new Resource(() -> new FoodWithContainer("Energy Drink I", "Energetic!", "Empty Wooden Cup", 0, 1, new Effect(1, EffectType.ENERGY), 1, 1, ItemTier.COMMON), "Items/EnergyDrink1.png"));
		itemMap.put("Energy Drink II", new Resource(() -> new FoodWithContainer("Energy Drink II", "Energetic!", "Empty Bronze Cup", 0, 1, new Effect(2, EffectType.ENERGY), 1, 1, ItemTier.UNCOMMON), "Items/EnergyDrink2.png"));
		itemMap.put("Energy Drink III", new Resource(() -> new FoodWithContainer("Energy Drink III", "Energetic!", "Empty Silver Cup", 0, 1, new Effect(3, EffectType.ENERGY), 1, 1, ItemTier.RARE), "Items/EnergyDrink3.png"));
		itemMap.put("Energy Drink IV", new Resource(() -> new FoodWithContainer("Energy Drink IV", "Energetic!", "Empty Gold Cup", 0, 1, new Effect(4, EffectType.ENERGY), 1, 1, ItemTier.EPIC), "Items/EnergyDrink4.png"));
		itemMap.put("Healthy Drink I", new Resource(() -> new FoodWithContainer("Healthy Drink I", "Fresh and feel good.", "Empty Wooden Cup", 1, 1, new Effect(3, EffectType.HEAL), 1, 1, ItemTier.COMMON), "Items/HealDrink1.png"));
		itemMap.put("Healthy Drink II", new Resource(() -> new FoodWithContainer("Healthy Drink II", "Fresh and feel good", "Empty Bronze Cup", 1, 1, new Effect(6, EffectType.HEAL), 1, 1, ItemTier.UNCOMMON), "Items/HealDrink2.png"));
		itemMap.put("Healthy Drink III", new Resource(() -> new FoodWithContainer("Healthy Drink III", "Fresh and feel good", "Empty Silver Cup", 1, 1, new Effect(12, EffectType.HEAL), 1, 1, ItemTier.RARE), "Items/HealDrink3.png"));
		itemMap.put("Healthy Drink IV", new Resource(() -> new FoodWithContainer("Healthy Drink IV", "Fresh and feel good", "Empty Gold Cup", 1, 1, new Effect(24, EffectType.HEAL), 1, 1, ItemTier.EPIC), "Items/HealDrink4.png"));
		itemMap.put("Heal Potion I", new Resource(() -> new FoodWithContainer("Heal Potion I", "Restore health!", "Empty Potion I", 1, 1, new Effect(5, EffectType.HEAL), 1, 1, ItemTier.COMMON), "Items/HealPotion1.png"));
		itemMap.put("Heal Potion II", new Resource(() -> new FoodWithContainer("Heal Potion II", "Restore health!", "Empty Potion II", 1, 1, new Effect(10, EffectType.HEAL), 1, 1, ItemTier.UNCOMMON), "Items/HealPotion2.png"));
		itemMap.put("Heal Potion III", new Resource(() -> new FoodWithContainer("Heal Potion III", "Restore health!", "Empty Potion III", 1, 1, new Effect(20, EffectType.HEAL), 1, 1, ItemTier.UNCOMMON), "Items/HealPotion3.png"));
		itemMap.put("Heal Potion IV", new Resource(() -> new FoodWithContainer("Heal Potion IV", "Restore health!", "Empty Potion IV", 1, 1, new Effect(40, EffectType.HEAL), 1, 1, ItemTier.RARE), "Items/HealPotion4.png"));
		itemMap.put("Heal Potion V", new Resource(() -> new FoodWithContainer("Heal Potion V", "Restore health!", "Empty Potion V", 1, 1, new Effect(80, EffectType.HEAL), 1, 1, ItemTier.EPIC), "Items/HealPotion5.png"));
		itemMap.put("Luck Potion I", new Resource(() -> new FoodWithContainer("Luck Potion I", "God will be on our side", "Empty Potion I", 1, 1, new Effect(3, EffectType.LUCK), 1, 1, ItemTier.UNCOMMON), "Items/LuckPotion1.png"));
		itemMap.put("Luck Potion II", new Resource(() -> new FoodWithContainer("Luck Potion II", "God will be on our side", "Empty Potion II", 1, 1, new Effect(6, EffectType.LUCK), 1, 1, ItemTier.RARE), "Items/LuckPotion2.png"));
		itemMap.put("Lucky Drink I", new Resource(() -> new FoodWithContainer("Lucky Drink I", "Made by Four-leaf clover", "Empty Wooden Cup", 1, 1, new Effect(1, EffectType.LUCK), 1, 1, ItemTier.COMMON), "Items/LuckyDrink1.png"));
		itemMap.put("Lucky Drink II", new Resource(() -> new FoodWithContainer("Lucky Drink II", "Made by Four-leaf clover", "Empty Bronze Cup", 1, 1, new Effect(2, EffectType.LUCK), 1, 1, ItemTier.UNCOMMON), "Items/LuckyDrink2.png"));
		itemMap.put("Lucky Drink III", new Resource(() -> new FoodWithContainer("Lucky Drink III", "Made by Four-leaf clover", "Empty Silver Cup", 1, 1, new Effect(3, EffectType.LUCK), 1, 1, ItemTier.RARE), "Items/LuckyDrink3.png"));
		itemMap.put("Lucky Drink IV", new Resource(() -> new FoodWithContainer("Lucky Drink IV", "Made by Four-leaf clover", "Empty Gold Cup", 1, 1, new Effect(4, EffectType.LUCK), 1, 1, ItemTier.EPIC), "Items/LuckyDrink4.png"));
		itemMap.put("Moguro Leaf Drink I", new Resource(() -> new FoodWithContainer("Moguro Leaf Drink I", "Now to down some Moguro leaf juice!", "Empty Wooden Cup", 1, 1, new Effect(5, EffectType.THORN), 1, 1, ItemTier.COMMON), "Items/MoguroLeafDrink1.png"));
		itemMap.put("Moguro Leaf Drink II", new Resource(() -> new FoodWithContainer("Moguro Leaf Drink II", "Now to down some Moguro leaf juice!", "Empty Bronze Cup", 1, 1, new Effect(10, EffectType.THORN), 1, 1, ItemTier.UNCOMMON), "Items/MoguroLeafDrink2.png"));
		itemMap.put("Moguro Leaf Drink III", new Resource(() -> new FoodWithContainer("Moguro Leaf Drink III", "Now to down some Moguro leaf juice!", "Empty Silver Cup", 1, 1, new Effect(20, EffectType.THORN), 1, 1, ItemTier.RARE), "Items/MoguroLeafDrink3.png"));
		itemMap.put("Moguro Leaf Drink IV", new Resource(() -> new FoodWithContainer("Moguro Leaf Drink IV", "Now to down some Moguro leaf juice!", "Empty Gold Cup", 1, 1, new Effect(40, EffectType.THORN), 1, 1, ItemTier.EPIC), "Items/MoguroLeafDrink4.png"));
		itemMap.put("Shield Potion I", new Resource(() -> new FoodWithContainer("Shield Potion I", "A magical protective shield", "Empty Potion I", 1, 1, new Effect(5, EffectType.SHIELD), 1, 1, ItemTier.COMMON), "Items/ShieldPotion1.png"));
		itemMap.put("Shield Potion II", new Resource(() -> new FoodWithContainer("Shield Potion II", "A magical protective shield", "Empty Potion II", 1, 1, new Effect(10, EffectType.SHIELD), 1, 1, ItemTier.UNCOMMON), "Items/ShieldPotion2.png"));
		itemMap.put("Shield Potion III", new Resource(() -> new FoodWithContainer("Shield Potion III", "A magical protective shield", "Empty Potion III", 1, 1, new Effect(20, EffectType.SHIELD), 1, 1, ItemTier.UNCOMMON), "Items/ShieldPotion3.png"));
		itemMap.put("Shield Potion IV", new Resource(() -> new FoodWithContainer("Shield Potion IV", "A magical protective shield", "Empty Potion IV", 1, 1, new Effect(40, EffectType.SHIELD), 1, 1, ItemTier.RARE), "Items/ShieldPotion4.png"));
		itemMap.put("Shield Potion V", new Resource(() -> new FoodWithContainer("Shield Potion V", "A magical protective shield", "Empty Potion V", 1, 1, new Effect(80, EffectType.SHIELD), 1, 1, ItemTier.EPIC), "Items/ShieldPotion5.png"));
		itemMap.put("Spicy Drink I", new Resource(() -> new FoodWithContainer("Spicy Drink I", "Hot and Spicy", "Empty Wooden Cup", 1, 1, new Effect(8, EffectType.ANGER), 1, 1, ItemTier.COMMON), "Items/SpicyDrink1.png"));
		itemMap.put("Spicy Drink II", new Resource(() -> new FoodWithContainer("Spicy Drink II", "Hot and Spicy", "Empty Bronze Cup", 1, 1, new Effect(16, EffectType.ANGER), 1, 1, ItemTier.UNCOMMON), "Items/SpicyDrink2.png"));
		itemMap.put("Spicy Drink III", new Resource(() -> new FoodWithContainer("Spicy Drink III", "Hot and Spicy", "Empty Silver Cup", 1, 1, new Effect(32, EffectType.ANGER), 1, 1, ItemTier.RARE), "Items/SpicyDrink3.png"));
		itemMap.put("Spicy Drink IV", new Resource(() -> new FoodWithContainer("Spicy Drink IV", "Hot and Spicy", "Empty Gold Cup", 1, 1, new Effect(64, EffectType.ANGER), 1, 1, ItemTier.EPIC), "Items/SpicyDrink4.png"));
		
		
//		PassiveRelic(String name, String detail, Effect effect, int range, int width, int height, ItemTier tier) Not diagonal 
		itemMap.put("Damage Relic II", new Resource(() -> new PassiveRelic("Damage Relic II", "Gold cross with red gem", new Effect(10, EffectType.DAMAGE), 1, 1, 1, ItemTier.UNCOMMON), "Items/DamageRelic2.png"));
		itemMap.put("Damage Relic IV", new Resource(() -> new PassiveRelic("Damage Relic IV", "Gold grail with red gem", new Effect(40, EffectType.DAMAGE), 2, 1, 1, ItemTier.EPIC), "Items/DamageRelic4.png"));
		itemMap.put("Shield Relic II", new Resource(() -> new PassiveRelic("Shield Relic II", "Gold cross with blue gem", new Effect(10, EffectType.SHIELD), 1, 1, 1, ItemTier.RARE), "Items/ShieldRelic2.png"));
		itemMap.put("Shield Relic IV", new Resource(() -> new PassiveRelic("Shield Relic IV", "Gold grail with blue gem", new Effect(40, EffectType.SHIELD), 2, 1, 1, ItemTier.LEGENDARY), "Items/ShieldRelic4.png"));
		
		
//		PassiveRelic(String name, String detail, Effect effect,int range, int height, ItemTier tier) Diagonal 
		itemMap.put("Damage Relic I", new Resource(() -> new PassiveRelic("Damage Relic I", "Gold plate with red gem", new Effect(5, EffectType.DAMAGE), 1, 1, ItemTier.COMMON), "Items/DamageRelic1.png"));
		itemMap.put("Damage Relic III", new Resource(() -> new PassiveRelic("Damage Relic III", "Gold ring with red gem", new Effect(20, EffectType.DAMAGE), 2, 1, ItemTier.RARE), "Items/DamageRelic3.png"));
		itemMap.put("Shield Relic I", new Resource(() -> new PassiveRelic("Shield Relic I", "Gold plate with blue gem", new Effect(5, EffectType.SHIELD), 1, 1, ItemTier.UNCOMMON), "Items/ShieldRelic1.png"));
		itemMap.put("Shield Relic III", new Resource(() -> new PassiveRelic("Shield Relic III", "Gold ring with blue gem", new Effect(20, EffectType.SHIELD), 2, 1, ItemTier.EPIC), "Items/ShieldRelic3.png"));
		
		
//		ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int width, int height, ItemTier tier) Not diagonal 
		itemMap.put("Blue Inscription I", new Resource(() -> new ActiveRelic("Blue Inscription I", "Ancient book with blue gem", new Effect(15, EffectType.SHIELD), 1, 1, 1, 1, ItemTier.RARE), "Items/ShieldBook1_a.png"));
		itemMap.put("Blue Inscription II", new Resource(() -> new ActiveRelic("Blue Inscription II", "Ancient book with blue gem", new Effect(30, EffectType.SHIELD), 1, 1, 1, 1, ItemTier.EPIC), "Items/ShieldBook2_a.png"));
		itemMap.put("Blue Inscription III", new Resource(() -> new ActiveRelic("Blue Inscription III", "Ancient book with blue gem", new Effect(60, EffectType.SHIELD), 1, 2, 1, 1, ItemTier.LEGENDARY), "Items/ShieldBook3_a.png"));
		itemMap.put("Red Inscription I", new Resource(() -> new ActiveRelic("Red Inscription I", "Ancient book with Red gem", new Effect(15, EffectType.DAMAGE), 1, 1, 1, 1, ItemTier.UNCOMMON), "Items/DamageBook1_a.png"));
		itemMap.put("Red Inscription II", new Resource(() -> new ActiveRelic("Red Inscription II", "Ancient book with Red gem", new Effect(30, EffectType.DAMAGE), 1, 1, 1, 1, ItemTier.RARE), "Items/DamageBook2_a.png"));
		itemMap.put("Red Inscription III", new Resource(() -> new ActiveRelic("Red Inscription III", "Ancient book with Red gem", new Effect(60, EffectType.DAMAGE), 1, 2, 1, 1, ItemTier.EPIC), "Items/DamageBook3_a.png"));
		
		
//		ActiveRelic(String name, String detail, Effect effect, int costActivate, int range, int height, ItemTier tier) Diagonal
		itemMap.put("Cyan Inscription I", new Resource(() -> new ActiveRelic("Cyan Inscription I", "Ancient book with blue gem", new Effect(15, EffectType.SHIELD), 1, 1, 1, ItemTier.RARE), "Items/ShieldBook1_b.png"));
		itemMap.put("Cyan Inscription II", new Resource(() -> new ActiveRelic("Cyan Inscription II", "Ancient book with blue gem", new Effect(30, EffectType.SHIELD), 1, 1, 1, ItemTier.EPIC), "Items/ShieldBook2_b.png"));
		itemMap.put("Cyan Inscription III", new Resource(() -> new ActiveRelic("Cyan Inscription III", "Ancient book with blue gem", new Effect(60, EffectType.SHIELD), 1, 2, 1, ItemTier.LEGENDARY), "Items/ShieldBook3_b.png"));
		itemMap.put("Orange Inscription I", new Resource(() -> new ActiveRelic("Orange Inscription I", "Ancient book with orange gem", new Effect(15, EffectType.DAMAGE), 1, 1, 1, ItemTier.UNCOMMON), "Items/DamageBook1_b.png"));
		itemMap.put("Orange Inscription II", new Resource(() -> new ActiveRelic("Orange Inscription II", "Ancient book with orange gem", new Effect(30, EffectType.DAMAGE), 1, 1, 1, ItemTier.RARE), "Items/DamageBook2_b.png"));
		itemMap.put("Orange Inscription III", new Resource(() -> new ActiveRelic("Orange Inscription III", "Ancient book with orange gem", new Effect(60, EffectType.DAMAGE), 1, 2, 1, ItemTier.EPIC), "Items/DamageBook3_b.png"));
		
		
//		Coins(String name, String detail, int amount, ItemTier tier)
		itemMap.put("Coins", new Resource(() -> new Coins("Coins", "Wealth brings power but fortune attracts danger.", 1, ItemTier.COMMON), "Items/Coin.png"));
		
//		ManaOrb(String name, String detail, int amount, int width, int height, ItemTier tier)
		itemMap.put("Mana Stone I", new Resource(() -> new ManaOrb("Mana Stone I", "Faintly glowing stone infused with magical energy.", 1, 1, 1, ItemTier.COMMON), "Items/Manastones1.png"));
		itemMap.put("Mana Stone II", new Resource(() -> new ManaOrb("Mana Stone II", "Faintly glowing stone infused with magical energy.", 2, 1, 1, ItemTier.RARE), "Items/Manastones2.png"));
		itemMap.put("Mana Stone III", new Resource(() -> new ManaOrb("Mana Stone III", "Faintly glowing stone infused with magical energy.", 3, 1, 1, ItemTier.EPIC), "Items/Manastones3.png"));
		
		
	}

	public static HashMap<String, Resource> getItemMap() {
		return itemMap;
	}
}
