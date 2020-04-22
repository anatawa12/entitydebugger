package com.anatawa12.entitydebugger

import net.minecraft.command.ICommandSender
import net.minecraft.command.WrongUsageException
import net.minecraft.server.MinecraftServer
import net.minecraftforge.client.IClientCommand

object CommandCEntity : CommandEntityBase(), IClientCommand {
    override fun getName(): String = "c:entity"

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        if (!sender.entityWorld.isRemote) throw WrongUsageException("have to use on client")
        main(sender, args)
    }
}
