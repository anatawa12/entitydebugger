package com.anatawa12.entitydebugger

import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.network.NetworkCheckHandler
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side


@Mod(modid = EntityDebugger.MOD_ID)
object EntityDebugger {
    @Mod.EventHandler
    fun preinit(event: FMLPreInitializationEvent) {
        GameRegistry.registerEntitySelector(EntityIdSelector())
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
    }

    @Mod.EventHandler
    fun postinit(event: FMLPostInitializationEvent) {
        ClientCommandHandler.instance.registerCommand(CommandCEntity)
    }

    @Mod.EventHandler
    fun onServerStarting(event: FMLServerStartingEvent) {
        event.registerServerCommand(CommandSEntity)
    }

    @NetworkCheckHandler
    fun networkCheck(mods: Map<String, String>, side: Side): Boolean = true

    @JvmStatic @Mod.InstanceFactory private fun get() = this

    const val MOD_ID = "entity-debugger"
}
