package net.disorganized.charming.registry;

import net.disorganized.charming.Charming;
import net.disorganized.charming.content.Charm;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.*;

public class CharmingRegistries {

    public static final RegistryKey<Registry<Charm>> KEY = RegistryKey.ofRegistry(Charming.of("charm"));


    public static void register() {
        DynamicRegistries.registerSynced(KEY, Charm.CODEC);
    }

}
