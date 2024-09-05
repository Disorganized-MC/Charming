package net.disorganized.charming.wip;

import net.disorganized.charming.content.Charm;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.TagProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class CharmTagProvider extends TagProvider<Charm> {

    protected CharmTagProvider(DataOutput output, RegistryKey<? extends Registry<Charm>> registryRef, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryRef, registryLookupFuture);
    }


//    protected void createTooltipOrderTag(RegistryWrapper.WrapperLookup registries, RegistryKey<Charm>... charms) {
//        this.getOrCreateTagBuilder(CharmTags.TOOLTIP_ORDER).add(charms);
//        Set<RegistryKey<Charm>> set = Set.of(charms);
//        List<String> list = registries.getWrapperOrThrow(CharmRegistry.KEY)
//                .streamEntries()
//                .filter(entry -> !set.contains(entry.getKey().get()))
//                .map(RegistryEntry::getIdAsString)
//                .collect(Collectors.toList());
//        if (!list.isEmpty()) {
//            throw new IllegalStateException("Not all charms were registered for tooltip ordering. Missing: " + String.join(", ", list));
//        }
//    }

}
