package com.anatawa12.entitydebugger.asm

import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.world.World

object EntitySelectorHook {
    @JvmStatic
    fun getWorlds(server: MinecraftServer?, sender: ICommandSender): Array<out World> {
        if (sender.entityWorld.isRemote)
            return arrayOf(sender.entityWorld)
        return server!!.worlds
    }
}
