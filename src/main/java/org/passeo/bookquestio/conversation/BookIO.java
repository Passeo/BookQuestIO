package org.passeo.bookquestio.conversation;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import org.betonquest.betonquest.api.profiles.OnlineProfile;
import org.betonquest.betonquest.conversation.ChatConvIO;
import org.betonquest.betonquest.conversation.Conversation;
import org.betonquest.betonquest.conversation.ConversationIO;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.passeo.bookquestio.BookQuestIO;

import java.util.Map;

public class BookIO extends ChatConvIO implements ConversationIO, Listener {

    public BookIO(Conversation conv, OnlineProfile onlineProfile) {
        super(conv, onlineProfile);
        BookQuestIO plugin = BookQuestIO.getInstance();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void display() {
        final TextComponent.Builder component =
                Component.text()
                        .append(Component.text(this.npcName + ": \n"))
                        .append(Component.text(this.npcText + ": \n"));
        if(!this.options.isEmpty()){
            component.append(Component.text("§7-----------------\n"));
        }

        for(final Map.Entry<Integer, String> options : this.options.entrySet()){
            final int optionIndex = options.getKey();
            final String optionText = options.getValue();
            component.append(Component.text(optionIndex + ": "+ optionText + "\n\n").clickEvent(ClickEvent.callback((audience) -> {
                this.conv.passPlayerAnswer(optionIndex);
            })));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                BookQuestIO.getInstance().getBook().openBookIO(player, component.build());
            }
        }.runTask(BookQuestIO.getInstance());
    }

    /*
    Check if Player Yaw & Pitch Move to prevent escaping
     */
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent e){
        float movedFromYaw = e.getFrom().getYaw();
        float movedFromPitch = e.getFrom().getPitch();
        float movedToYaw = e.getTo().getYaw();
        float movedToPitch = e.getTo().getPitch();

        if((movedFromYaw != movedToYaw)
                && (movedFromPitch != movedToPitch)){
        display();
        }
    }
}
