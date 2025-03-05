package game.util;

import java.util.HashMap;

public class IconList {
	private static HashMap<EffectType, Resource> iconMap;

	static {
		iconMap = new HashMap<EffectType, Resource>();
		iconMap.put(EffectType.ANGER, new Resource("anger.png"));
		iconMap.put(EffectType.DAMAGE, new Resource("damage.png"));
		iconMap.put(EffectType.DODGE, new Resource("dodge.png"));
		iconMap.put(EffectType.ENERGY, new Resource("energy.png"));
		iconMap.put(EffectType.FIRE, new Resource("fire.png"));
		iconMap.put(EffectType.HEAL, new Resource("heal.png"));
		iconMap.put(EffectType.LUCK, new Resource("luck.png"));
		iconMap.put(EffectType.POISON, new Resource("poison.png"));
		iconMap.put(EffectType.REGEN, new Resource("regen.png"));
		iconMap.put(EffectType.SHIELD, new Resource("shield.png"));
		iconMap.put(EffectType.STUNNED, new Resource("stun.png"));
		iconMap.put(EffectType.SUMMONER, new Resource("summon.png"));
		iconMap.put(EffectType.THORN, new Resource("thorn.png"));
		iconMap.put(EffectType.VAMPIRIC, new Resource("vampiric.png"));
	}

	public static HashMap<EffectType, Resource> getIconMap() {
		return iconMap;
	}
}
