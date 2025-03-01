package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import game.util.Effect;
import game.util.EffectType;
import game.util.MobTier;
import javafx.scene.image.Image;

public class EntityList {
private static HashMap<String, Resource> entityMap;
	
	static {
		// MobTier.E - 5 entities
		entityMap = new HashMap<String, Resource>();
		entityMap.put("werewolf", new Resource(() -> new Entity(null, 50, 10, MobTier.E,
				new ArrayList<Effect>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
				new ArrayList<String>(Arrays.asList(
						"Frames/werewolf-idle1.png",
						"Frames/werewolf-idle2.png",
						"Frames/werewolf-idle3.png",
						"Frames/werewolf-idle4.png",
						"Frames/werewolf-idle5.png"
				))));
        entityMap.put("ghost", new Resource(() -> new Entity("ghost", 70, 10, MobTier.E,
                        new ArrayList<>(Arrays.asList(new Effect(15,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/ghost1.png",
                        "Frames/ghost2.png",
                        "Frames/ghost3.png",
                        "Frames/ghost4.png",
                        "Frames/ghost5.png",
                        "Frames/ghost6.png",
                        "Frames/ghost7.png"
                ))));

        entityMap.put("bird", new Resource(() -> new Entity("bird", 30, 10, MobTier.E,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/bird1.png",
                        "Frames/bird2.png",
                        "Frames/bird3.png",
                        "Frames/bird4.png",
                        "Frames/bird5.png",
                        "Frames/bird6.png"
                ))));

//        entityMap.put("crow", new Resource(() -> new Entity("crow", 50, 10, MobTier.E,
//                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/crow-fly1.png",
//                        "Frames/crow-fly2.png"
//                ))));

//        entityMap.put("snake", new Resource(() -> new Entity("snake", 30, 10, MobTier.E,
//                        new ArrayList<>(Arrays.asList(new Effect(10,EffectType.DAMAGE), new Effect(10,EffectType.POISON)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/snake1.png",
//                        "Frames/snake2.png",
//                        "Frames/snake3.png",
//                        "Frames/snake4.png"
//                ))));


        // MobTier.D - 5 entities
        entityMap.put("flyingAlien", new Resource(() -> new Entity("flyingAlien", 40, 10, MobTier.D, 
        		new ArrayList<Effect>(Arrays.asList(new Effect(30,EffectType.DAMAGE), new Effect(15,EffectType.POISON)))),
        		new ArrayList<String>(Arrays.asList(
        				"Frames/alien-enemy-flying1.png",
        				"Frames/alien-enemy-flying2.png",
        				"Frames/alien-enemy-flying3.png",
        				"Frames/alien-enemy-flying4.png",
        				"Frames/alien-enemy-flying5.png",
        				"Frames/alien-enemy-flying6.png",
        				"Frames/alien-enemy-flying7.png",
        				"Frames/alien-enemy-flying8.png"
        				))));
        entityMap.put("walkingAlien", new Resource(() -> new Entity("walkingAlien", 75, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/walkingAlien1.png",
                        "Frames/walkingAlien2.png",
                        "Frames/walkingAlien3.png",
                        "Frames/walkingAlien4.png"
                ))));

        entityMap.put("frog", new Resource(() -> new Entity("frog", 60, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(25,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/frog1.png",
                        "Frames/frog2.png",
                        "Frames/frog3.png",
                        "Frames/frog4.png"
                ))));

//        entityMap.put("meerman", new Resource(() -> new Entity("meerman", 75, 15, MobTier.D,
//                        new ArrayList<>(Arrays.asList(new Effect(10,EffectType.DAMAGE), new Effect(20,EffectType.ANGER)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/meerman1.png",
//                        "Frames/meerman2.png"
//                ))));

        entityMap.put("bunny", new Resource(() -> new Entity("bunny", 40, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(5,EffectType.DAMAGE), new Effect(40,EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/bunny1.png",
                        "Frames/bunny2.png",
                        "Frames/bunny3.png",
                        "Frames/bunny4.png"
                ))));

        // MobTier.C - 3 entities
        entityMap.put("lizzard", new Resource(() -> new Entity("", 80, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(30,EffectType.POISON), new Effect(80, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/lizzard1.png",
                        "Frames/lizzard2.png",
                        "Frames/lizzard3.png",
                        "Frames/lizzard4.png"
                ))));

        entityMap.put("mushroom", new Resource(() -> new Entity("mushroom", 70, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(35,EffectType.DAMAGE), new Effect(1, EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/mushroom1.png",
                        "Frames/mushroom2.png",
                        "Frames/mushroom3.png",
                        "Frames/mushroom4.png"
                ))));

        entityMap.put("toad", new Resource(() -> new Entity("toad", 100, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.POISON), new Effect(20, EffectType.THORN)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/mutant-toad-idle1.png",
                        "Frames/mutant-toad-idle2.png",
                        "Frames/mutant-toad-idle3.png",
                        "Frames/mutant-toad-idle4.png"
                ))));

//        entityMap.put("C4", new Resource(() -> new Entity("", 100, 30, MobTier.C,
//                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(5, EffectType.HEAL), new Effect(5, EffectType.SHIELD)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/1.png",
//                        "Frames/2.png",
//                        "Frames/3.png",
//                        "Frames/4.png",
//                        "Frames/5.png",
//                        "Frames/6.png",
//                        "Frames/7.png",
//                        "Frames/8.png"
//                ))));
//
//        entityMap.put("C5", new Resource(() -> new Entity("", 100, 30, MobTier.C,
//                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(5, EffectType.SUMMONER), new Effect(5, EffectType.THORN)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/1.png",
//                        "Frames/2.png",
//                        "Frames/3.png",
//                        "Frames/4.png",
//                        "Frames/5.png",
//                        "Frames/6.png",
//                        "Frames/7.png",
//                        "Frames/8.png"
//                ))));

        // MobTier.B - 4 entities
        entityMap.put("dragon", new Resource(() -> new Entity("dragon", 150, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(60,EffectType.DAMAGE), new Effect(120, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/dragon1.png",
                        "Frames/dragon2.png",
                        "Frames/dragon3.png",
                        "Frames/dragon4.png",
                        "Frames/dragon5.png",
                        "Frames/dragon6.png",
                        "Frames/dragon7.png",
                        "Frames/dragon8.png",
                        "Frames/dragon9.png"
                ))));

        entityMap.put("hellHound", new Resource(() -> new Entity("hellHound", 100, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(50, EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
//                        "Frames/hellHound1.png",
  //                      "Frames/hellHound2.png",
                        "Frames/hellHound3.png",
                        "Frames/hellHound4.png",
                        "Frames/hellHound5.png",
                        "Frames/hellHound6.png",
                        "Frames/hellHound7.png",
                        "Frames/hellHound8.png",
                        "Frames/hellHound9.png",
                        "Frames/hellHound10.png",
                        "Frames/hellHound11.png"
                ))));

        entityMap.put("ogre", new Resource(() -> new Entity("ogre", 200, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE), new Effect(30, EffectType.THORN), new Effect(40,EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/ogre-idle1.png",
                        "Frames/ogre-idle2.png",
                        "Frames/ogre-idle3.png",
                        "Frames/ogre-idle4.png"
                ))));

        entityMap.put("highGhost", new Resource(() -> new Entity("highGhost", 150, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(1, EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/highGhost1.png",
                        "Frames/highGhost2.png",
                        "Frames/highGhost3.png",
                        "Frames/highGhost4.png",
                        "Frames/highGhost5.png",
                        "Frames/highGhost6.png"
                ))));
//
//        entityMap.put("B5", new Resource(() -> new Entity("", 150, 45, MobTier.B,
//                        new ArrayList<>(Arrays.asList(new Effect(50,EffectType.POISON), new Effect(10, EffectType.HEAL), new Effect(5, EffectType.ANGER)))),
//                new ArrayList<>(Arrays.asList(
//                        "Frames/1.png",
//                        "Frames/2.png",
//                        "Frames/3.png",
//                        "Frames/4.png",
//                        "Frames/5.png",
//                        "Frames/6.png",
//                        "Frames/7.png",
//                        "Frames/8.png"
//                ))));

        // MobTier.A - 3 entities
        entityMap.put("fireSkull", new Resource(() -> new Entity("fireSkull", 200, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(80, EffectType.FIRE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/fireSkull1.png",
                        "Frames/fireSkull2.png",
                        "Frames/fireSkull3.png",
                        "Frames/fireSkull4.png",
                        "Frames/fireSkull5.png",
                        "Frames/fireSkull6.png",
                        "Frames/fireSkull7.png",
                        "Frames/fireSkull8.png"
                ))));

        entityMap.put("flyingEye", new Resource(() -> new Entity("flyingEye", 200, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.THORN), new Effect(60, EffectType.VAMPIRIC)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/flyingEye1.png",
                        "Frames/flyingEye2.png",
                        "Frames/flyingEye3.png",
                        "Frames/flyingEye4.png",
                        "Frames/flyingEye5.png",
                        "Frames/flyingEye6.png",
                        "Frames/flyingEye7.png",
                        "Frames/flyingEye8.png"
                ))));

        entityMap.put("hellBeast", new Resource(() -> new Entity("hellBeast", 250, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(50, EffectType.VAMPIRIC), new Effect(1,EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/hellBeast1.png",
                        "Frames/hellBeast2.png",
                        "Frames/hellBeast3.png",
                        "Frames/hellBeast4.png",
                        "Frames/hellBeast5.png",
                        "Frames/hellBeast6.png"
                ))));

        // MobTier.S - 2 entities
        entityMap.put("nightmare", new Resource(() -> new Entity("nightmare", 400, 180, MobTier.S,
                        new ArrayList<>(Arrays.asList(new Effect(120,EffectType.FIRE), new Effect(120, EffectType.VAMPIRIC)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/nightmare1.png",
                        "Frames/nightmare2.png",
                        "Frames/nightmare3.png",
                        "Frames/nightmare4.png"
                ))));

        entityMap.put("fireDragon", new Resource(() -> new Entity("fireDragon", 450, 180, MobTier.S,
                        new ArrayList<>(Arrays.asList(new Effect(60, EffectType.THORN), new Effect(140, EffectType.FIRE)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/fireDragon1.png",
                        "Frames/fireDragon2.png",
                        "Frames/fireDragon3.png",
                        "Frames/fireDragon4.png",
                        "Frames/fireDragon5.png",
                        "Frames/fireDragon6.png"
                ))));
        
        // Final Boss
        entityMap.put("demon", new Resource(() -> new Entity("demon", 1500, 130, MobTier.EX,
                        new ArrayList<>(Arrays.asList(new Effect(100,EffectType.DAMAGE), new Effect(150, EffectType.ANGER), new Effect(1000, EffectType.HEAL), new Effect(1, EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "Frames/demon1.png",
                        "Frames/demon2.png",
                        "Frames/demon3.png",
                        "Frames/demon4.png",
                        "Frames/demon5.png",
                        "Frames/demon6.png"
                ))));
		
	}

	public static HashMap<String, Resource> getEntityMap() {
		return entityMap;
	}
}
