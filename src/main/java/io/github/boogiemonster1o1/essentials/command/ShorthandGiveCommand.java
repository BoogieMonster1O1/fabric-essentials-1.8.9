package io.github.boogiemonster1o1.essentials.command;

import net.minecraft.class_1371;
import net.minecraft.class_1421;
import net.minecraft.command.AbstractCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.CommandStats;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.server.command.GiveCommand;

public class ShorthandGiveCommand extends GiveCommand {
    public String getName() {
        return "i";
    }

    public int getPermissionLevel() {
        return 2;
    }

    public String getUsageTranslationKey(CommandSource commandSource) {
        return "/i <item>";
    }

    @Override
    public void method_5885(CommandSource commandSource, String[] strings) throws CommandException {
        if(!(commandSource instanceof PlayerEntity)){
            throw new class_1371("Can only be run by the player!");
        }

        PlayerEntity playerEntity = (PlayerEntity)commandSource;

        Item item = method_5694(commandSource, strings[0]);
        int i = strings.length >= 2 ? method_5665(strings[1], 1, 64) : 1;
        int j = strings.length >= 3 ? method_5661(strings[2]) : 0;
        ItemStack itemStack = new ItemStack(item, i, j);

        if (strings.length >= 5) {
            String string = method_5675(commandSource, strings, 4).getString();

            try {
                itemStack.setTag(StringNbtReader.parse(string));
            } catch (class_1421 var10) {
                throw new CommandException("commands.give.tagError", var10.getMessage());
            }
        }

        boolean bl = playerEntity.inventory.insertStack(itemStack);
        if (bl) {
             playerEntity.world.playSound(playerEntity, "random.pop", 0.2F, ((playerEntity.method_7125().nextFloat() - playerEntity.method_7125().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            playerEntity.playerContainer.sendContentUpdates();
        }

        ItemEntity itemEntity;
        if (bl && itemStack.count <= 0) {
            itemStack.count = 1;
            commandSource.feedback(CommandStats.Type.AFFECTED_ITEMS, i);
            itemEntity = playerEntity.dropItem(itemStack, false);
            if (itemEntity != null) {
                itemEntity.setDespawnImmediately();
            }
        } else {
            commandSource.feedback(CommandStats.Type.AFFECTED_ITEMS, i - itemStack.count);
            itemEntity = playerEntity.dropItem(itemStack, false);
            if (itemEntity != null) {
                itemEntity.resetPickupDelay();
                itemEntity.method_7762(playerEntity.getTranslationKey());
            }
        }

        method_5674(commandSource, this, "commands.give.success", itemStack.toHoverableText(), i, playerEntity.getTranslationKey());
    }
}
