package de.mats.report.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

/**
 * Erstellt von Boss
 */
public class ReportUtil {

    private ArrayList<ProxiedPlayer> reportedPlayer = new ArrayList<>();
    private ArrayList<ProxiedPlayer> loginPlayer = new ArrayList<>();

    public void login(ProxiedPlayer proxiedPlayer) {
        if (reportedPlayer.contains(proxiedPlayer)) {
            loginPlayer.remove(proxiedPlayer);
            proxiedPlayer.sendMessage(new TextComponent("[Report] Du siehts nun keine Reports mehr"));
            return;
        }

        loginPlayer.add(proxiedPlayer);
        proxiedPlayer.sendMessage(new TextComponent("[Report] Du siehts nun Reports"));
    }

    public void report(ProxiedPlayer proxiedPlayer) {
        if (reportedPlayer.contains(proxiedPlayer)) {
            for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                if (loginPlayer.contains(all))
                    continue;
                TextComponent textComponent = new TextComponent("Klicke hier um den Report von " + proxiedPlayer.getName() + " zu bearbeiten");

                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Verbinden...").create()));
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server" +  proxiedPlayer.getServer().getInfo().getName()));

                all.sendMessage(textComponent);
            }
            return;
        }

        reportedPlayer.add(proxiedPlayer);

        for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
            if (loginPlayer.contains(all))
                continue;
            TextComponent textComponent = new TextComponent("Klicke hier um den Report von " + proxiedPlayer.getName() + " zu bearbeiten");

            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Verbinden...").create()));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server" +  proxiedPlayer.getServer().getInfo().getName()));

            all.sendMessage(textComponent);
        }
    }
}
