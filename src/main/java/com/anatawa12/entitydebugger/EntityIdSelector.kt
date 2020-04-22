package com.anatawa12.entitydebugger

import com.google.common.base.Predicate
import net.minecraft.command.ICommandSender
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d
import net.minecraftforge.fml.common.IEntitySelectorFactory

class EntityIdSelector : IEntitySelectorFactory {
    override fun createPredicates(arguments: MutableMap<String, String>, mainSelector: String?, sender: ICommandSender?, position: Vec3d?): List<Predicate<Entity>> {
        val eid = arguments["eid"]?.toIntOrNull() ?: return listOf()
        return listOf(Predicate {
            it?.entityId == eid
        })
    }
}
