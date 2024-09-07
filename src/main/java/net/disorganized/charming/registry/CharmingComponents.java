package net.disorganized.charming.registry;

import com.mojang.serialization.Codec;
import net.disorganized.charming.Charming;
import net.disorganized.charming.content.ItemCharmsComponent;
import net.disorganized.charming.content.ItemNotchesComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CharmingComponents {

    public static void init() {}


    public static final ComponentType<ItemCharmsComponent> ITEM_CHARMS = register("charms", ItemCharmsComponent.CODEC);
    public static final ComponentType<ItemNotchesComponent> ITEM_NOTCHES = register("notches", ItemNotchesComponent.CODEC);


    public static <T> ComponentType<T> register(String path, Codec<T> codec) {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Charming.of(path),
                ComponentType.<T>builder().codec(codec).packetCodec(PacketCodecs.codec(codec)).build()
        );
    }

}
