package com.gammadelta.dowsing

import net.minecraft.block.Blocks
import net.minecraft.item.Items

/**
 * All the items accepted as dowsing rods.
 */
object RodRegistry {
    // So I don't have to re-write the ores all the time
    private val oreSet = setOf(
        Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE,
        Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE, Blocks.EMERALD_ORE, Blocks.NETHER_QUARTZ_ORE
    )

    // The rods
    private val stick = DowsingRod(2, 16, setOf(Blocks.WATER)) // but you could be fire
    private val woodenHoe = DowsingRod(4, 8, oreSet)
    private val stoneHoe = DowsingRod(3, 16, oreSet)
    private val ironHoe = DowsingRod(2, 32, oreSet)
    private val goldHoe = DowsingRod(1, 8, oreSet)
    private val diamondHoe = DowsingRod(1, 64, oreSet)
    private val bone = DowsingRod(2, 80, setOf(Blocks.LAVA))
    private val blazeRod = DowsingRod(4, 80, setOf(Blocks.SPAWNER))

    // This gets checked to see if the player is holding the right stuff
    public val rods = mapOf(
        Items.STICK to stick,
        Items.WOODEN_HOE to woodenHoe,
        Items.STONE_HOE to stoneHoe,
        Items.IRON_HOE to ironHoe,
        Items.GOLDEN_HOE to goldHoe,
        Items.DIAMOND_HOE to diamondHoe,
        Items.BONE to bone,
        Items.BLAZE_ROD to blazeRod
    )

    /*
        Other stick-ish items for possible future updates:
        - End rods
        - Torches & redstone torches
        - Carrots on Sticks
        - Fishing Rods
        - Levers
        - Arrows
        - Bamboo
     */

}