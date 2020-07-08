package io.github.boogiemonster1o1.essentials.mixin;

import io.github.boogiemonster1o1.essentials.command.ShorthandGiveCommand;
import net.minecraft.class_1581;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin extends class_1581 {
    @Inject(method="<init>",at=@At("HEAD"))
    public void registerCommands(CallbackInfo ci){
        this.registerCommand(new ShorthandGiveCommand());
    }
}
