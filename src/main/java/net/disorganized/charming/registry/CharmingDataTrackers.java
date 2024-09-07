package net.disorganized.charming.registry;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class CharmingDataTrackers {

    public static void init() {}


    public static TrackedData<Integer> AIR_JUMP = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);

}
