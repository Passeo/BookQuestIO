package org.passeo.bookquestio;

import lombok.Getter;
import org.betonquest.betonquest.BetonQuest;
import org.bukkit.plugin.java.JavaPlugin;
import org.passeo.bookquestio.conversation.BookIO;

public final class BookQuestIO extends JavaPlugin {

    @Getter
    private static BookQuestIO instance;
    @Getter
    private ClientBook book;

    @Override
    public void onEnable() {
        instance = this;
        book = new ClientBook();
        BetonQuest.getInstance().registerConversationIO("book", BookIO.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
