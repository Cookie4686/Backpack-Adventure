package game.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


import game.util.Effect;
import game.util.EffectType;
import game.util.MobTier;
import javafx.scene.image.Image;

public class IconList {
	private static HashMap<EffectType, Resource> iconMap;
	
	static {
		
		iconMap = new HashMap<EffectType, Resource>();
		iconMap.put(EffectType.ANGER, new Resource(() -> new EffectIcon(EffectType.ANGER), "icons/anger.png"));
		iconMap.put(EffectType.DAMAGE, new Resource(() -> new EffectIcon(EffectType.DAMAGE), "icons/damage.png"));
		iconMap.put(EffectType.DODGE, new Resource(() -> new EffectIcon(EffectType.DODGE), "icons/dodge.png"));
		iconMap.put(EffectType.ENERGY, new Resource(() -> new EffectIcon(EffectType.ENERGY), "icons/energy.png"));
		iconMap.put(EffectType.FIRE, new Resource(() -> new EffectIcon(EffectType.FIRE), "icons/fire.png"));
		iconMap.put(EffectType.HEAL, new Resource(() -> new EffectIcon(EffectType.HEAL), "icons/heal.png"));
		iconMap.put(EffectType.LUCK, new Resource(() -> new EffectIcon(EffectType.LUCK), "icons/luck.png"));
		iconMap.put(EffectType.POISON, new Resource(() -> new EffectIcon(EffectType.POISON), "icons/poison.png"));
		iconMap.put(EffectType.REGEN, new Resource(() -> new EffectIcon(EffectType.REGEN), "icons/regen.png"));
		iconMap.put(EffectType.SHIELD, new Resource(() -> new EffectIcon(EffectType.SHIELD), "icons/shield.png"));
		iconMap.put(EffectType.STUNNED, new Resource(() -> new EffectIcon(EffectType.STUNNED), "icons/stun.png"));
		iconMap.put(EffectType.SUMMONER, new Resource(() -> new EffectIcon(EffectType.SUMMONER), "icons/summon.png"));
		iconMap.put(EffectType.THORN, new Resource(() -> new EffectIcon(EffectType.THORN), "icons/thorn.png"));
		iconMap.put(EffectType.VAMPIRIC, new Resource(() -> new EffectIcon(EffectType.VAMPIRIC), "icons/vampiric.png"));
		
	}

	public static HashMap<EffectType, Resource> getIconMap() {
		return iconMap;
	}
}

