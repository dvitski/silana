package cc.dvitski.silana

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.core.Registry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Silana : ModInitializer {
    const val MOD_ID = "silana"
    const val MOD_NAME = "Silana"

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")

        PlayerBlockBreakEvents.BEFORE.register(VeinHandler::handleBlockBreak)
    }

    fun <T : Any?> Registry<T>.filterSilanaMod(): List<T> {
        return filter { obj ->
            val location = getKey(obj)
            location?.namespace == MOD_ID
        }
    }
}
