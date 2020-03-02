package com.gammadelta.dowsing

import net.fabricmc.api.ModInitializer

public class DowsingMod : ModInitializer {
    public val MOD_ID = "dowsing";

    override fun onInitialize() {
        println("Dowsing is initializing...")
        DowseEveryTick.init()
        println("Registered the ServerTickCallback. Dowsing is initialized!")
    }
}