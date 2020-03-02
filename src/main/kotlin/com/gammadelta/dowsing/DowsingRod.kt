package com.gammadelta.dowsing

import net.minecraft.block.Block

/**
 * The information about a rod.
 * Each item that works as a dowsing rod is associated with a certain instance of this class.
 * @see RodRegistry
 */
class DowsingRod {
    public val radius: Int // Radius of 1 = 1x1; radius of 4 = 7x7
    public val depth: Int // How many blocks below the player the dowsing rod will check
    public val dowsesFor: Set<Block> // All blocks the rod dowses for will be in this set

    constructor(radius: Int, depth: Int, dowsesFor: Set<Block>) {
        this.radius = radius
        this.depth = depth
        this.dowsesFor = dowsesFor
    }
}