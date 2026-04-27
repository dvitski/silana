package cc.dvitski.silana.data.server

import cc.dvitski.silana.SilanaEnchantments
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.EnchantmentTags
import net.minecraft.world.item.enchantment.Enchantment
import java.util.concurrent.CompletableFuture

class EnchantmentTagProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricTagsProvider<Enchantment>(output, Registries.ENCHANTMENT, future) {
    override fun addTags(provider: HolderLookup.Provider) {
        builder(EnchantmentTags.IN_ENCHANTING_TABLE)
            .addOptional(SilanaEnchantments.SILANA)
    }
}
