package net.disorganized.charming.registry;

import net.disorganized.charming.Charming;
import net.disorganized.charming.content.Charm;
import net.minecraft.registry.tag.TagKey;

public class CharmingTags {

    public static void init() {}


    public static final TagKey<Charm> TOOLTIP_ORDER = of("tooltip_order");


    public static TagKey<Charm> of(String path) {
        return TagKey.of(CharmingRegistries.KEY, Charming.of(path));
    }

}
