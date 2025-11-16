package cc.dvitski.silana.data

import cc.dvitski.silana.data.client.LanguageProvider
import cc.dvitski.silana.data.server.BlockTagProvider
import cc.dvitski.silana.data.server.EnchantmentProvider
import cc.dvitski.silana.data.server.EnchantmentTagProvider
import cc.dvitski.silana.data.server.EntityTypeTagProvider
import cc.dvitski.silana.data.server.ItemTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object SilanaDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()

        pack.addProvider(::EnchantmentProvider)

        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::EnchantmentTagProvider)
        pack.addProvider(::EntityTypeTagProvider)

        pack.addProvider(::LanguageProvider)
	}
}
