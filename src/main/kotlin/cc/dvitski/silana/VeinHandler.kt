package cc.dvitski.silana

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.GameMasterBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

object VeinHandler {
    fun handleBlockBreak(level: Level, player: Player, pos: BlockPos, state: BlockState, blockEntity: BlockEntity?): Boolean {
        if (player !is ServerPlayer || level !is ServerLevel) {
            return true
        }

        // check veinable
        if (!state.`is`(SilanaBlockTags.VEINABLE)) {
            return true
        }

        // check correct tool
        val stack = player.mainHandItem
        if (/*!player.abilities.instabuild && */!stack.isCorrectToolForDrops(state)) {
            return true
        }

        // if silana enchantment has been filtered, ignore and treat like classic vein miner
        val enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
        val silanaEnchantment = enchantments.get(SilanaEnchantments.SILANA).orElse(null)
        if (silanaEnchantment != null) {
            if (EnchantmentHelper.getItemEnchantmentLevel(silanaEnchantment, stack) < 1) {
                return true
            }
        }

        // collect blocks
        val collector = Collector(level, player, state)
        val collected = collector.collectBlocks(pos)
        if (collected.isEmpty()) {
            return true
        }

        // remove standing position if supporting
        if (!player.abilities.flying && !player.isFallFlying) {
            collected.remove(player.onPos)
        }

        // add broken position back
        collected.add(pos)

        // process mined blocks
        val collectedBlocks = processAvailable(level, player, stack.copy(), collected)
        if (collectedBlocks.isEmpty()) {
            return true
        }

        // damage real item
        stack.hurtAndBreak(collectedBlocks.size, player, InteractionHand.MAIN_HAND)

        // batch rewards
        spawnAfterBreak(level, stack, collectedBlocks)

        val dropped = giveStacks(level, player, stack, collectedBlocks)
        if (dropped) {
            level.playSound(null, player, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 1f, 1f)
        }

        // batch destroy
        destroyBlocks(level, player, collectedBlocks)

        return false
    }

    private fun spawnAfterBreak(level: ServerLevel, stack: ItemStack, positions: Set<BlockPos>) {
        positions.forEach { pos ->
            level.getBlockState(pos).spawnAfterBreak(level, pos, stack, true)
        }
    }

    fun destroyBlocks(level: ServerLevel, player: ServerPlayer, positions: Set<BlockPos>) {
        positions.forEach { pos ->
            level.destroyBlock(pos, false, player)
        }

        player.causeFoodExhaustion(0.02f * positions.size)
    }

    fun giveStacks(level: ServerLevel, player: Player, stack: ItemStack, positions: Collection<BlockPos>): Boolean {
        if (player.preventsBlockDrops() || !level.gameRules.getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
            return false
        }

        var droppedAny = false
        positions.forEach { pos ->
            val state = level.getBlockState(pos)
            val blockEntity = level.getBlockEntity(pos)

            val drops = Block.getDrops(state, level, pos, blockEntity, player, stack)
            drops.forEach(player::handleExtraItemsCreatedOnUse)

            if (drops.isNotEmpty()) {
                droppedAny = true
            }
        }

        return droppedAny
    }

    private fun processAvailable(level: ServerLevel, player: ServerPlayer, stack: ItemStack, positions: Set<BlockPos>): Set<BlockPos> {
        val result = mutableSetOf<BlockPos>()
        for (pos in positions) {
            if (canDestroy(player, level, pos, stack)) {
                stack.hurtAndBreak(1, player, InteractionHand.MAIN_HAND)
                result.add(pos)
                if (stack.isBroken) {
                    break
                }
            }
        }

        return result
    }

    private fun canDestroy(player: ServerPlayer, level: ServerLevel, pos: BlockPos, stack: ItemStack): Boolean {
        val state = level.getBlockState(pos)

        if (!stack.canDestroyBlock(state, level, pos, player)) {
            return false
        }

        val block = state.block
        if (block is GameMasterBlock && !player.canUseGameMasterBlocks()) {
            return false
        }

        if (player.blockActionRestricted(level, pos, player.gameMode.gameModeForPlayer)) {
            return false
        }

        return true
    }

    class Collector(
        private val level: ServerLevel,
        private val player: ServerPlayer,
        private val rootState: BlockState
    ) {
        fun collectBlocks(start: BlockPos): MutableSet<BlockPos> {
            val visited = mutableSetOf<BlockPos>()
            val queue = ArrayDeque<BlockPos>()
            queue.add(start)
            visited.add(start)

            val maxBlocks = 128  // limit by count instead of range

            while (queue.isNotEmpty()) {
                if (visited.size >= maxBlocks) break

                val pos = queue.removeFirst()

                Direction.entries.forEach { dir ->
                    val neighbor = pos.relative(dir)
                    if (neighbor in visited) return@forEach
                    val neighborState = level.getBlockState(neighbor)
                    if (neighborState.`is`(rootState.block)) {
                        visited.add(neighbor)
                        queue.add(neighbor)
                        if (visited.size >= maxBlocks) return@forEach
                    }
                }
            }

            visited.remove(start)
            return visited
        }
    }

}
