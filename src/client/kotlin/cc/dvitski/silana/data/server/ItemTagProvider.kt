package cc.dvitski.silana.data.server

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.ItemTagsProvider(output, future) {
    override fun addTags(provider: HolderLookup.Provider) {
    }
}
