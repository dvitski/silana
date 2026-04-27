package cc.dvitski.silana.data.server

import cc.dvitski.silana.SilanaBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.BlockTagsProvider(output, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        valueLookupBuilder(SilanaBlockTags.VEINABLE)
            .addOptionalTag(ConventionalBlockTags.ORES)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(BlockTags.LEAVES)
    }
}
