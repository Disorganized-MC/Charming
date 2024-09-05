package net.disorganized.charming.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.disorganized.charming.registry.CharmRegistry;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.text.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record Charm (
        Text description,
        List<AttributeModifierSlot> slots,
        ComponentMap effects
) {

    public static final Codec<Charm> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TextCodecs.CODEC.optionalFieldOf("description", Text.empty()).forGetter(Charm::description),
            AttributeModifierSlot.CODEC.listOf().optionalFieldOf("slots", List.of()).forGetter(Charm::slots),
            EnchantmentEffectComponentTypes.COMPONENT_MAP_CODEC.optionalFieldOf("effects", ComponentMap.EMPTY).forGetter(Charm::effects)
    ).apply(instance, Charm::new));

    public static final Codec<RegistryEntry<Charm>> ENTRY_CODEC = RegistryFixedCodec.of(CharmRegistry.KEY);


    public static Text getDescription(RegistryEntry<Charm> charm) {
        Text description = charm.value().description;
        if (description.equals(Text.empty())) return defaultName(charm);

        return description;
    }

    public static Text defaultName(RegistryEntry<Charm> charm) {
        return charm.getKey().map(key -> Text.translatable(key.getValue().toTranslationKey("charm")))
                .orElseGet(Text::empty);
    }

}
