package cc.dvitski.silana.data.server

import cc.dvitski.silana.SilanaBlockTags
import java.util.concurrent.CompletableFuture
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags

class BlockTagProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider.BlockTagsProvider(output, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        builder(SilanaBlockTags.VEINABLE)
            .addOptionalTag(ConventionalBlockTags.ORES)
            .forceAddTag(BlockTags.LOGS)
            .forceAddTag(BlockTags.LEAVES)
    }
}
