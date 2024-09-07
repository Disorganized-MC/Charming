package net.disorganized.charming.content;

import com.mojang.serialization.Codec;
import net.disorganized.charming.registry.CharmingComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public record ItemNotchesComponent (
        int notches
) implements TooltipAppender {

    public static final ItemNotchesComponent DEFAULT = new ItemNotchesComponent(0);

    public static final Codec<ItemNotchesComponent> CODEC = Codec.intRange(1, 1024).xmap(
            ItemNotchesComponent::new,
            ItemNotchesComponent::notches
    );

    public static ItemNotchesComponent get(ItemStack stack) {
        return stack.getOrDefault(CharmingComponents.ITEM_NOTCHES, DEFAULT);
    }


    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        tooltip.accept(Text.translatable("component.charming.notches", notches));
    }

}
