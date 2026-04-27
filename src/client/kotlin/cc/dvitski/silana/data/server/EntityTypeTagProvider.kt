package cc.dvitski.silana.data.server

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.EntityTypeTagsProvider(output, future) {
    override fun addTags(lookup: HolderLookup.Provider) {
    }
}
