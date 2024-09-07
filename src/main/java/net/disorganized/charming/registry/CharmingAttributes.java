package net.disorganized.charming.registry;

import net.disorganized.charming.Charming;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class CharmingAttributes {

    public static void init() {}


    public static RegistryEntry<EntityAttribute> PLAYER_AIR_JUMP = register("player.air_jump",
            new ClampedEntityAttribute(
                    "attribute.charming.player.air_jump",
                    0, 0, 1024
            ).setTracked(true)
    );


    public static RegistryEntry<EntityAttribute> register(String path, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Charming.of(path), attribute);
    }

}
