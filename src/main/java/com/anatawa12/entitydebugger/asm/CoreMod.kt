package com.anatawa12.entitydebugger.asm

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin

@IFMLLoadingPlugin.TransformerExclusions("kotlin.", "com.anatawa12.entitydebugger.asm.")
@IFMLLoadingPlugin.SortingIndex(1100)
class CoreMod : IFMLLoadingPlugin {
    override fun getModContainerClass(): String? = null
    
    override fun getASMTransformerClass(): Array<String> = arrayOf(
            "com.anatawa12.entitydebugger.asm.EntitySelectorTransformer"
    )

    override fun getSetupClass(): String? = null

    override fun injectData(p0: MutableMap<String, Any>?) {
    }

    override fun getAccessTransformerClass(): String? = null
}
