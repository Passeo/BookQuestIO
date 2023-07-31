package org.passeo.bookquestio;

import lombok.NoArgsConstructor;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

@NoArgsConstructor
public class ClientBook {

    public ItemStack createBookIO(final String title, final String author, final Component... page) {
        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
        Component components = page[0];


        bookMeta.setTitle(title);
        bookMeta.setAuthor(author);
        bookMeta.addPages(page);
        itemStack.setItemMeta(bookMeta);



        return itemStack;
    }

    public void openBookIO(Player player, Component... textComponent) {
        ItemStack book = this.createBookIO("", "", textComponent);
        player.openBook(book);
    }

}
