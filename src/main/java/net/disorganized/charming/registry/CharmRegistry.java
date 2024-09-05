package net.disorganized.charming.registry;

import net.disorganized.charming.content.Charm;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.List;

public class CharmRegistry {

    public static final RegistryKey<Registry<Charm>> KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("charm"));


    public static void register() {
        DynamicRegistries.registerSynced(KEY, Charm.CODEC);
    }


    public static List<RegistryLoader.Entry<?>> getCharms(DynamicRegistryManager registries) {
        return DynamicRegistries.getDynamicRegistries().stream()
                .filter(entry -> entry.key().isOf(KEY))
                .toList();
    }

}
