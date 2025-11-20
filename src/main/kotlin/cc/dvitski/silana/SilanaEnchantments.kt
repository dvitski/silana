package cc.dvitski.silana

import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.enchantment.Enchantment

object SilanaEnchantments {
    val SILANA = register("silana")

    private fun register(id: String): ResourceKey<Enchantment> {
        return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Silana.MOD_ID, id))
    }
}
