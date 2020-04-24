package com.anatawa12.entitydebugger.asm

import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.launchwrapper.Launch
import net.minecraft.launchwrapper.LaunchClassLoader
import org.objectweb.asm.*

class EntitySelectorTransformer : IClassTransformer {
    override fun transform(name: String?, transformedName: String, basicClass: ByteArray?): ByteArray? {
        if (basicClass == null) return null
        if (transformedName != "net.minecraft.command.EntitySelector") return basicClass
        val r = ClassReader(basicClass)
        val w = ClassWriter(0)
        r.accept(Transformer(w), 0)
        return w.toByteArray()
    }

    class Transformer(w: ClassVisitor): ClassVisitor(Opcodes.ASM5, w) {
        override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
            if ((name == "getWorlds" || name == "func_179654_a") && desc == "(L${"net/minecraft/command/ICommandSender"};L${"java/util/Map"};)L${"java/util/List"};")  {
                return Method(super.visitMethod(access, name, desc, signature, exceptions))
            }
            return super.visitMethod(access, name, desc, signature, exceptions)
        }
        
        class Method(mv: MethodVisitor) : MethodVisitor(Opcodes.ASM5, mv) {
            override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, desc: String?) {
                if (opcode == Opcodes.GETFIELD &&
                        owner == "net/minecraft/server/MinecraftServer" &&
                        (name == "worlds" || name == "field_71305_c") &&
                        desc == "[L${"net/minecraft/world/WorldServer"};"){
                    super.visitVarInsn(Opcodes.ALOAD, 0)
                    super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(EntitySelectorHook::class.java),
                            EntitySelectorHook::getWorlds.name, 
                            "(L${"net/minecraft/server/MinecraftServer"};L${"net/minecraft/command/ICommandSender"};)[L${"net/minecraft/world/World"};", 
                            false)
                } else {
                    super.visitFieldInsn(opcode, owner, name, desc)
                }
            }

            override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                super.visitMaxs(maxStack + 1, maxLocals)
            }
        }
    }
}
