package cc.dvitski.silana.client

import cc.dvitski.silana.Silana.MOD_ID
import cc.dvitski.silana.Silana.MOD_NAME
import net.fabricmc.api.ClientModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SilanaClient : ClientModInitializer {
    val logger: Logger = LoggerFactory.getLogger("$MOD_ID-client")

    override fun onInitializeClient() {
        logger.info("Initializing $MOD_NAME client")
    }
}
