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
						"frames/werewolf-idle1.png",
						"frames/werewolf-idle2.png",
						"frames/werewolf-idle3.png",
						"frames/werewolf-idle4.png",
						"frames/werewolf-idle5.png"
				))));
        entityMap.put("ghost", new Resource(() -> new Entity("ghost", 70, 10, MobTier.E,
                        new ArrayList<>(Arrays.asList(new Effect(15,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/ghost1.png",
                        "frames/ghost2.png",
                        "frames/ghost3.png",
                        "frames/ghost4.png",
                        "frames/ghost5.png",
                        "frames/ghost6.png",
                        "frames/ghost7.png"
                ))));

        entityMap.put("bird", new Resource(() -> new Entity("bird", 30, 10, MobTier.E,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/bird1.png",
                        "frames/bird2.png",
                        "frames/bird3.png",
                        "frames/bird4.png",
                        "frames/bird5.png",
                        "frames/bird6.png"
                ))));

//        entityMap.put("crow", new Resource(() -> new Entity("crow", 50, 10, MobTier.E,
//                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/crow-fly1.png",
//                        "frames/crow-fly2.png"
//                ))));

//        entityMap.put("snake", new Resource(() -> new Entity("snake", 30, 10, MobTier.E,
//                        new ArrayList<>(Arrays.asList(new Effect(10,EffectType.DAMAGE), new Effect(10,EffectType.POISON)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/snake1.png",
//                        "frames/snake2.png",
//                        "frames/snake3.png",
//                        "frames/snake4.png"
//                ))));


        // MobTier.D - 5 entities
        entityMap.put("flyingAlien", new Resource(() -> new Entity("flyingAlien", 40, 10, MobTier.D, 
        		new ArrayList<Effect>(Arrays.asList(new Effect(30,EffectType.DAMAGE), new Effect(15,EffectType.POISON)))),
        		new ArrayList<String>(Arrays.asList(
        				"frames/alien-enemy-flying1.png",
        				"frames/alien-enemy-flying2.png",
        				"frames/alien-enemy-flying3.png",
        				"frames/alien-enemy-flying4.png",
        				"frames/alien-enemy-flying5.png",
        				"frames/alien-enemy-flying6.png",
        				"frames/alien-enemy-flying7.png",
        				"frames/alien-enemy-flying8.png"
        				))));
        entityMap.put("walkingAlien", new Resource(() -> new Entity("walkingAlien", 75, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/walkingAlien1.png",
                        "frames/walkingAlien2.png",
                        "frames/walkingAlien3.png",
                        "frames/walkingAlien4.png"
                ))));

        entityMap.put("frog", new Resource(() -> new Entity("frog", 60, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(25,EffectType.DAMAGE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/frog1.png",
                        "frames/frog2.png",
                        "frames/frog3.png",
                        "frames/frog4.png"
                ))));

//        entityMap.put("meerman", new Resource(() -> new Entity("meerman", 75, 15, MobTier.D,
//                        new ArrayList<>(Arrays.asList(new Effect(10,EffectType.DAMAGE), new Effect(20,EffectType.ANGER)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/meerman1.png",
//                        "frames/meerman2.png"
//                ))));

        entityMap.put("bunny", new Resource(() -> new Entity("bunny", 40, 15, MobTier.D,
                        new ArrayList<>(Arrays.asList(new Effect(5,EffectType.DAMAGE), new Effect(40,EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
                        "frames/bunny1.png",
                        "frames/bunny2.png",
                        "frames/bunny3.png",
                        "frames/bunny4.png"
                ))));

        // MobTier.C - 3 entities
        entityMap.put("lizzard", new Resource(() -> new Entity("", 80, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(30,EffectType.POISON), new Effect(80, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "frames/lizzard1.png",
                        "frames/lizzard2.png",
                        "frames/lizzard3.png",
                        "frames/lizzard4.png"
                ))));

        entityMap.put("mushroom", new Resource(() -> new Entity("mushroom", 70, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(35,EffectType.DAMAGE), new Effect(1, EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "frames/mushroom1.png",
                        "frames/mushroom2.png",
                        "frames/mushroom3.png",
                        "frames/mushroom4.png"
                ))));

        entityMap.put("toad", new Resource(() -> new Entity("toad", 100, 30, MobTier.C,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.POISON), new Effect(20, EffectType.THORN), new Effect(60, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "frames/mutant-toad-idle1.png",
                        "frames/mutant-toad-idle2.png",
                        "frames/mutant-toad-idle3.png",
                        "frames/mutant-toad-idle4.png"
                ))));

//        entityMap.put("C4", new Resource(() -> new Entity("", 100, 30, MobTier.C,
//                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(5, EffectType.HEAL), new Effect(5, EffectType.SHIELD)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/1.png",
//                        "frames/2.png",
//                        "frames/3.png",
//                        "frames/4.png",
//                        "frames/5.png",
//                        "frames/6.png",
//                        "frames/7.png",
//                        "frames/8.png"
//                ))));
//
//        entityMap.put("C5", new Resource(() -> new Entity("", 100, 30, MobTier.C,
//                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(5, EffectType.SUMMONER), new Effect(5, EffectType.THORN)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/1.png",
//                        "frames/2.png",
//                        "frames/3.png",
//                        "frames/4.png",
//                        "frames/5.png",
//                        "frames/6.png",
//                        "frames/7.png",
//                        "frames/8.png"
//                ))));

        // MobTier.B - 4 entities
        entityMap.put("dragon", new Resource(() -> new Entity("dragon", 150, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(60,EffectType.DAMAGE), new Effect(120, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "frames/dragon1.png",
                        "frames/dragon2.png",
                        "frames/dragon3.png",
                        "frames/dragon4.png",
                        "frames/dragon5.png",
                        "frames/dragon6.png",
                        "frames/dragon7.png",
                        "frames/dragon8.png",
                        "frames/dragon9.png"
                ))));

        entityMap.put("hellHound", new Resource(() -> new Entity("hellHound", 100, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(50, EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
//                        "frames/hellHound1.png",
  //                      "frames/hellHound2.png",
                        "frames/hellHound3.png",
                        "frames/hellHound4.png",
                        "frames/hellHound5.png",
                        "frames/hellHound6.png",
                        "frames/hellHound7.png",
                        "frames/hellHound8.png",
                        "frames/hellHound9.png",
                        "frames/hellHound10.png",
                        "frames/hellHound11.png"
                ))));

        entityMap.put("ogre", new Resource(() -> new Entity("ogre", 200, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(20,EffectType.DAMAGE), new Effect(30, EffectType.THORN), new Effect(40,EffectType.ANGER)))),
                new ArrayList<>(Arrays.asList(
                        "frames/ogre-idle1.png",
                        "frames/ogre-idle2.png",
                        "frames/ogre-idle3.png",
                        "frames/ogre-idle4.png"
                ))));

        entityMap.put("highGhost", new Resource(() -> new Entity("highGhost", 150, 45, MobTier.B,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.DAMAGE), new Effect(1, EffectType.SUMMONER), new Effect(100, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "frames/highGhost1.png",
                        "frames/highGhost2.png",
                        "frames/highGhost3.png",
                        "frames/highGhost4.png",
                        "frames/highGhost5.png",
                        "frames/highGhost6.png"
                ))));
//
//        entityMap.put("B5", new Resource(() -> new Entity("", 150, 45, MobTier.B,
//                        new ArrayList<>(Arrays.asList(new Effect(50,EffectType.POISON), new Effect(10, EffectType.HEAL), new Effect(5, EffectType.ANGER)))),
//                new ArrayList<>(Arrays.asList(
//                        "frames/1.png",
//                        "frames/2.png",
//                        "frames/3.png",
//                        "frames/4.png",
//                        "frames/5.png",
//                        "frames/6.png",
//                        "frames/7.png",
//                        "frames/8.png"
//                ))));

        // MobTier.A - 3 entities
        entityMap.put("fireSkull", new Resource(() -> new Entity("fireSkull", 200, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(80, EffectType.FIRE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/fireSkull1.png",
                        "frames/fireSkull2.png",
                        "frames/fireSkull3.png",
                        "frames/fireSkull4.png",
                        "frames/fireSkull5.png",
                        "frames/fireSkull6.png",
                        "frames/fireSkull7.png",
                        "frames/fireSkull8.png"
                ))));

        entityMap.put("flyingEye", new Resource(() -> new Entity("flyingEye", 200, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(40,EffectType.THORN), new Effect(60, EffectType.VAMPIRIC)))),
                new ArrayList<>(Arrays.asList(
                        "frames/flyingEye1.png",
                        "frames/flyingEye2.png",
                        "frames/flyingEye3.png",
                        "frames/flyingEye4.png",
                        "frames/flyingEye5.png",
                        "frames/flyingEye6.png",
                        "frames/flyingEye7.png",
                        "frames/flyingEye8.png"
                ))));

        entityMap.put("hellBeast", new Resource(() -> new Entity("hellBeast", 250, 90, MobTier.A,
                        new ArrayList<>(Arrays.asList(new Effect(50, EffectType.VAMPIRIC), new Effect(1,EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "frames/hellBeast1.png",
                        "frames/hellBeast2.png",
                        "frames/hellBeast3.png",
                        "frames/hellBeast4.png",
                        "frames/hellBeast5.png",
                        "frames/hellBeast6.png"
                ))));

        // MobTier.S - 2 entities
        entityMap.put("nightmare", new Resource(() -> new Entity("nightmare", 400, 180, MobTier.S,
                        new ArrayList<>(Arrays.asList(new Effect(120,EffectType.FIRE), new Effect(120, EffectType.VAMPIRIC), new Effect(400, EffectType.HEAL)))),
                new ArrayList<>(Arrays.asList(
                        "frames/nightmare1.png",
                        "frames/nightmare2.png",
                        "frames/nightmare3.png",
                        "frames/nightmare4.png"
                ))));

        entityMap.put("fireDragon", new Resource(() -> new Entity("fireDragon", 450, 180, MobTier.S,
                        new ArrayList<>(Arrays.asList(new Effect(60, EffectType.THORN), new Effect(140, EffectType.FIRE)))),
                new ArrayList<>(Arrays.asList(
                        "frames/fireDragon1.png",
                        "frames/fireDragon2.png",
                        "frames/fireDragon3.png",
                        "frames/fireDragon4.png",
                        "frames/fireDragon5.png",
                        "frames/fireDragon6.png"
                ))));
        
        // Final Boss
        entityMap.put("demon", new Resource(() -> new Entity("demon", 4000, 130, MobTier.EX,
                        new ArrayList<>(Arrays.asList(new Effect(100,EffectType.DAMAGE), new Effect(150, EffectType.ANGER), new Effect(1000, EffectType.HEAL), new Effect(1, EffectType.SUMMONER)))),
                new ArrayList<>(Arrays.asList(
                        "frames/demon1.png",
                        "frames/demon2.png",
                        "frames/demon3.png",
                        "frames/demon4.png",
                        "frames/demon5.png",
                        "frames/demon6.png"
                ))));
		
	}

	public static HashMap<String, Resource> getEntityMap() {
		return entityMap;
	}
}
