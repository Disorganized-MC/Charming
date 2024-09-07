package net.disorganized.charming.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.disorganized.charming.registry.CharmingComponents;
import net.disorganized.charming.registry.CharmingRegistries;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record Charm (
        Text description,
        Definition definition,
        RegistryEntryList<Charm> exclusiveSet,
        ComponentMap effects
) {

    public static final Codec<Charm> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TextCodecs.CODEC.optionalFieldOf("description", Text.empty()).forGetter(Charm::description),
            Definition.CODEC.forGetter(Charm::definition),
            RegistryCodecs.entryList(CharmingRegistries.KEY).optionalFieldOf("exclusive_set", RegistryEntryList.empty()).forGetter(Charm::exclusiveSet),
            EnchantmentEffectComponentTypes.COMPONENT_MAP_CODEC.optionalFieldOf("effects", ComponentMap.EMPTY).forGetter(Charm::effects)
    ).apply(instance, Charm::new));

    public static final Codec<RegistryEntry<Charm>> ENTRY_CODEC = RegistryFixedCodec.of(CharmingRegistries.KEY);


    public static Text getDescription(RegistryEntry<Charm> charm) {
        Text description = charm.value().description;
        if (description.equals(Text.empty())) return defaultName(charm);

        return description;
    }

    public static Text defaultName(RegistryEntry<Charm> charm) {
        return charm.getKey().map(key -> Text.translatable(key.getValue().toTranslationKey("charm")))
                .orElseGet(Text::empty);
    }

    public static ItemStack asItem(RegistryEntry<Charm> charm) {
        Identifier id = charm.getKey().orElseGet(null).getValue();
//        ItemStack stack = CharmingItems.CHARM.getDefaultStack();
        ItemStack stack = Items.STICK.getDefaultStack();
        String nameKey = String.format("item.%s.charm.%s", id.getNamespace(), id.getPath());
        stack.set(DataComponentTypes.ITEM_NAME, Text.translatable(nameKey));
        System.out.println(id.withPrefixedPath("charm"));
        stack.set(DataComponentTypes.ITEM_MODEL, id.withPrefixedPath("charm/"));
        stack.set(CharmingComponents.ITEM_CHARMS, new ItemCharmsComponent(List.of(charm), true));
        return stack;
    }

    public record Definition (
            RegistryEntryList<Item> supportedItems,
            int notchCost
//            List<AttributeModifierSlot> slots
    ) {

        public static final MapCodec<Definition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                RegistryCodecs.entryList(RegistryKeys.ITEM).fieldOf("supported_items").forGetter(Definition::supportedItems),
                Codecs.rangedInt(1, 1024).fieldOf("notch_cost").forGetter(Definition::notchCost)
//                AttributeModifierSlot.CODEC.listOf().fieldOf("slots").forGetter(Definition::slots)
        ).apply(instance, Definition::new));

    }

}
