package com.anatawa12.entitydebugger

import net.minecraft.command.*
import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString
import net.minecraftforge.client.IClientCommand

object CommandSEntity : CommandEntityBase() {
    override fun getName(): String = "s:entity"

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        main(sender, args)
    }
}
