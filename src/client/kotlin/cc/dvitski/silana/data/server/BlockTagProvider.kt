package cc.dvitski.silana.data.server

import cc.dvitski.silana.SilanaBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.BlockTagProvider(output, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        valueLookupBuilder(SilanaBlockTags.VEINABLE)
            .forceAddTag(ConventionalBlockTags.ORES)
            .forceAddTag(ConventionalBlockTags.NATURAL_LOGS)
            .forceAddTag(BlockTags.LEAVES)
    }
}
