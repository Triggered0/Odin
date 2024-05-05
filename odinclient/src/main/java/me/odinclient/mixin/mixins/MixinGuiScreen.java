package me.odinclient.mixin.mixins;

import me.odinmain.events.impl.GuiEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {

    @Unique
    private final GuiScreen odin$gui = (GuiScreen) (Object) this;

    @Inject(method = "handleMouseInput", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiScreen.mouseClicked(III)V"), cancellable = true)
    private void onMouseInput(CallbackInfo ci){
        int k = Mouse.getEventButton();
        if (Mouse.getEventButtonState()) {
            if (MinecraftForge.EVENT_BUS.post(new GuiEvent.GuiMouseClickEvent(odin$gui, k, Mouse.getEventX(), Mouse.getEventY()))) {
                ci.cancel();
            }
        }
    }
}

