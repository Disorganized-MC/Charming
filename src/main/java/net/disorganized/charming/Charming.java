package net.disorganized.charming;

import net.disorganized.charming.content.Charm;
import net.disorganized.charming.content.ItemNotchesComponent;
import net.disorganized.charming.registry.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Charming implements ModInitializer {

	public static final String MOD_ID = "charming";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		CharmingRegistries.register();
		CharmingComponents.init();
		CharmingAttributes.init();
		CharmingDataTrackers.init();
		CharmingItems.init();
		CharmingTags.init();

		DefaultItemComponentEvents.MODIFY.register(Charming::modifyItems);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(Charming::modifyCreativeTab);

		LOGGER.info("Initialized!");
	}


	public static void modifyItems(DefaultItemComponentEvents.ModifyContext context) {
		context.modify(Items.DIAMOND_CHESTPLATE, builder -> builder.add(CharmingComponents.ITEM_NOTCHES, new ItemNotchesComponent(1)));
	}

	public static void modifyCreativeTab(FabricItemGroupEntries content) {
		var optional = content.getContext().lookup().getOptional(CharmingRegistries.KEY);
		if (optional.isEmpty()) return;

		optional.get().streamEntries()
				.map(Charm::asItem)
				.forEach(stack -> content.add(stack, ItemGroup.StackVisibility.PARENT_TAB_ONLY));
	}

}