package net.disorganized.charming.content;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.disorganized.charming.registry.CharmComponents;
import net.disorganized.charming.registry.CharmRegistry;
import net.disorganized.charming.registry.CharmTags;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public record ItemCharmsComponent (
        List<RegistryEntry<Charm>> charms,
        boolean showInTooltip
) implements TooltipAppender {

    public static final ItemCharmsComponent DEFAULT = new ItemCharmsComponent(new ArrayList<>(), true);

    public static final Codec<ItemCharmsComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Charm.ENTRY_CODEC.listOf().optionalFieldOf("values", new ArrayList<>()).forGetter(ItemCharmsComponent::charms),
            Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(ItemCharmsComponent::showInTooltip)
    ).apply(instance, ItemCharmsComponent::new));

    public static ItemCharmsComponent get(ItemStack stack) {
        return stack.getOrDefault(CharmComponents.ITEM_CHARMS, DEFAULT);
    }


    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        if (!this.showInTooltip) return;

        RegistryWrapper.WrapperLookup lookup = context.getRegistryLookup();
        RegistryEntryList<Charm> charms = ItemEnchantmentsComponent.getTooltipOrderList(lookup, CharmRegistry.KEY, CharmTags.TOOLTIP_ORDER);

//        lookup.getWrapperOrThrow(CharmRegistry.KEY).streamEntries().forEach(charm -> {
//            tooltip.accept(Charm.getDescription(charm));
//        });

        for (RegistryEntry<Charm> charm : charms) {
//            tooltip.accept(Text.of("e"));
            tooltip.accept(Charm.getDescription(charm));
        }
    }

}
