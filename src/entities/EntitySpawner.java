package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import game.util.MobTier;
import logic.GameLogic;

public class EntitySpawner {
	private static ArrayList<String> e, d, c, b, a, s;
	static {
		e = new ArrayList<String>(Arrays.asList(
				"werewolf",
				"ghost",
				"bird"
		));
		d = new ArrayList<String>(Arrays.asList(
				"flyingAlien",
				"walkingAlien",
				"frog",
				"bunny"
		));
		c = new ArrayList<String>(Arrays.asList(
				"lizzard",
				"mushroom",
				"toad"
		));
		b = new ArrayList<String>(Arrays.asList(
//				"dragon",
				"hellHound",
				"ogre",
				"highGhost"
		));
		a = new ArrayList<String>(Arrays.asList(
				"fireSkull",
				"flyingEye",
				"hellBeast"
		));
		s = new ArrayList<String>(Arrays.asList(
				"nightmare",
				"fireDragon"
		));
	}
	public static MobTier getTier() {
		//TODO: Connect luck and floor system
		int luckNumber = new Random().nextInt(100) + 1 + GameLogic.getInstance().getCurrentFloor() * 90;
		
		if (luckNumber<=40) return MobTier.E;
		if (luckNumber<=90) return MobTier.D;
		if (luckNumber<=130) return MobTier.C;
		if (luckNumber<=180) return MobTier.B;
		if (luckNumber<=255) return MobTier.A;
		return MobTier.S;
	}
	
	public static String getNameFromTier(MobTier mobTier) {
		switch (mobTier) {
		case E:
			return e.get(new Random().nextInt(e.size()));
		case D:
			return d.get(new Random().nextInt(d.size()));
		case C:
			return c.get(new Random().nextInt(c.size()));
		case B:
			return b.get(new Random().nextInt(b.size()));
		case A:
			return a.get(new Random().nextInt(a.size()));
		case S:
			return s.get(new Random().nextInt(s.size()));	
		default:
			return "";
		}
	}
}
