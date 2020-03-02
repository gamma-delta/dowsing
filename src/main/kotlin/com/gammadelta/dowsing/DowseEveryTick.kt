package com.gammadelta.dowsing

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.server.ServerTickCallback
import net.minecraft.item.Items
import net.minecraft.server.MinecraftServer
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import kotlin.random.Random

/**
 * Controls checking for dowsing every few ticks.
 */
object DowseEveryTick {
    fun init() {
        // Register something to happen every tick
        ServerTickCallback.EVENT.register(ServerTickCallback { server ->
            // Every 1/2 second there's a 50% chance to actually check for pings
            if (server.ticks % 10 != 0 || Random.nextBoolean()) return@ServerTickCallback
            // For each player playing right now...
            for (player in server.playerManager.playerList) {
                if (player.mainHandStack.item == player.offHandStack.item && RodRegistry.rods.containsKey(player.mainHandStack.item)) {
                    // Ooh, they're holding two of the same item *and* it's a dowsing rod!
                    val playerPos = player.blockPos
                    val rodInfo = RodRegistry.rods[player.mainHandStack.item]!! // this non-null assertion is safe because i just checked to see if i know about this rod

                    // Search every block in the area
                    // Start with searching the highest blocks
                    var success = false
                    var blocksSearched = 0
                    areaSearch@ for (y in playerPos.y downTo playerPos.y - rodInfo.depth)
                        for (x in playerPos.x - rodInfo.radius..playerPos.x + rodInfo.radius)
                            for (z in playerPos.z - rodInfo.radius..playerPos.z + rodInfo.radius) {
                                blocksSearched++
                                val blockHere = player.world.getBlockState(BlockPos(x, y, z)).block
                                if (rodInfo.dowsesFor.contains(blockHere)) {
                                    // yay we found the block we're looking for
                                    success = true
                                    break@areaSearch // stop the area search cause we found it
                                }
                            }
                    if (success) {
                        // ping the player yay
                        val volumeSearchable = rodInfo.depth * (1 + 2*rodInfo.radius) * (1 + 2*rodInfo.radius)
                        val percentageSearched = blocksSearched.toFloat() / volumeSearchable.toFloat() // 0 to 1
                        val pitch = 1 + (percentageSearched / 2) // 2 at farthest, 1 at nearest
                        val volume = 1 + (-percentageSearched / 1.5) // 1 at nearest, 0.25 at farthest
                        player.world.playSound(null, playerPos, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
                            SoundCategory.PLAYERS, volume.toFloat(), pitch) // for some reason pitch.toFloat() is redundant?
                    }
                }
            }
        })
    }
}