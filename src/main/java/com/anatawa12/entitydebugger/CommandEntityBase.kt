package com.anatawa12.entitydebugger

import net.minecraft.command.*
import net.minecraft.entity.Entity
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString

abstract class CommandEntityBase : CommandBase() {
    override fun getUsage(sender: ICommandSender): String = "/$name <selector> [data|nbt|default]"

    protected fun main(sender: ICommandSender, args: Array<String>) {
        if (args.isEmpty()) throw WrongUsageException(getUsage(sender))
        val entities = EntitySelector.matchEntities(sender, args[0], Entity::class.java)
        when (args.getOrNull(1)) {
            null, "default" -> {
                if (entities.size > 10) throw CommandException("too many entities matched!")
                for (entity in entities) {
                    defaultMessage(entity, sender)
                }
            }
            "data" -> {
                if (entities.size > 3) throw CommandException("too many entities matched!")
                for (entity in entities) {
                    dataMessage(entity, sender)
                }
            }
            "nbt" -> {
                if (entities.size > 3) throw CommandException("too many entities matched!")
                for (entity in entities) {
                    nbtMessage(entity, sender)
                }
            }
            else -> throw WrongUsageException(getUsage(sender))
        }
    }

    private fun defaultMessage(entity: Entity, sender: ICommandSender) {
        val exitsInChunk = entity.world.getChunk(entity.chunkCoordX, entity.chunkCoordZ)
                .entityLists[entity.chunkCoordY].contains(entity)
        sender.sendMessage(TextComponentString(
                "$entity: dead: ${entity.isDead}, exitsInChunk: $exitsInChunk"
        ))
    }

    private fun dataMessage(entity: Entity, sender: ICommandSender) {
        sender.sendMessage(TextComponentString("for entity: $entity"))
        for (dataEntry in entity.dataManager.all.orEmpty()) {
            sender.sendMessage(TextComponentString("${dataEntry.key.id}: ${dataEntry.value}"))
        }
    }

    private fun nbtMessage(entity: Entity, sender: ICommandSender) {
        sender.sendMessage(TextComponentString("$entity: ${entity.writeToNBT(NBTTagCompound())}"))
    }

    override fun checkPermission(server: MinecraftServer?, sender: ICommandSender?): Boolean = true
}
