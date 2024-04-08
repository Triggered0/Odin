package me.odinmain.ui

import me.odinmain.features.impl.render.ClickGUIModule
import me.odinmain.font.OdinFont
import me.odinmain.utils.render.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.renderer.GlStateManager

class OdinGuiButton(
    buttonId: Int, x: Int, y: Int, width: Int, height: Int, buttonText: String?, private val textSize: Float) : GuiButton(
    buttonId, x, y, width, height, buttonText
) {

    init {
        this.id = buttonId
        this.xPosition = x
        this.yPosition = y
        this.width = width
        this.height = height
        this.displayString = buttonText
    }

    override fun drawButton(mc: Minecraft?, mouseX: Int, mouseY: Int) {
        if (!this.visible) return
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)
        this.hovered = (mouseX >= this.xPosition) && (mouseY >= this.yPosition) && (mouseX < this.xPosition + this.width) && (mouseY < this.yPosition + this.height)
        val hoverState = this.getHoverState(this.hovered)
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.blendFunc(770, 771)
        roundedRectangle(this.xPosition, this.yPosition, this.width, this.height, ClickGUIModule.color, if (hoverState == 2) Color.WHITE else ClickGUIModule.color, Color.BLACK, 2f, 5f, 5f, 5f, 5f, 4f)
        text(this.displayString, this.xPosition + this.width / 2f, this.yPosition + height / 2f, Color.WHITE, textSize, OdinFont.REGULAR, TextAlign.Middle, TextPos.Middle, true)
    }

}