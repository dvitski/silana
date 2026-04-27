package cc.dvitski.silana.data.client

import cc.dvitski.silana.SilanaBlockTags
import cc.dvitski.silana.SilanaEnchantments
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageProvider(output: FabricPackOutput, future: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output, future) {
    override fun generateTranslations(provider: HolderLookup.Provider, builder: TranslationBuilder) {
        builder.add(SilanaBlockTags.VEINABLE, "Veinable")
        builder.addEnchantment(SilanaEnchantments.SILANA, "Silana")
    }
}
