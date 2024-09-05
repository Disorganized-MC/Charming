package net.disorganized.charming.registry;

import net.disorganized.charming.Charming;
import net.disorganized.charming.content.Charm;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.registry.*;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.util.List;

public class CharmRegistry {

//    public static final RegistryKey<Registry<Charm>> KEY = RegistryKey.ofRegistry(Charming.of("charm"));
    public static final RegistryKey<Registry<Charm>> KEY = RegistryKey.ofRegistry(Identifier.ofVanilla("charm"));


    public static void register() {
//        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener();
        DynamicRegistries.registerSynced(KEY, Charm.CODEC);
//        FabricRegistryBuilder.createSimple(KEY).buildAndRegister();
    }


    public static List<RegistryLoader.Entry<?>> getCharms(DynamicRegistryManager registries) {
        List<RegistryKey<? extends Registry<?>>> test = registries.streamAllRegistryKeys()
                .filter(key -> key.isOf(KEY))
                .toList();

        return DynamicRegistries.getDynamicRegistries().stream()
                .filter(entry -> entry.key().isOf(KEY))
                .toList();
    }

}
