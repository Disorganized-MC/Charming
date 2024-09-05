package net.disorganized.charming.wip;

import net.disorganized.charming.Charming;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class CharmLoader implements SimpleSynchronousResourceReloadListener {

    public static final Identifier ID = Charming.of("charm");

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
//        manager.findResources("")
    }

}
