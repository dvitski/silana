package cc.dvitski.silana

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block

object SilanaBlockTags {
    val VEINABLE = create("veinable")

    private fun create(id: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Silana.MOD_ID, id))
    }
}
