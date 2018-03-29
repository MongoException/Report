package de.mats.report.command;

import de.mats.report.MainClass;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Erstellt von Boss
 */
public class ReportCommand extends Command {

    public ReportCommand() {
        super("report");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer))
            return;

        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;

        if (strings.length != 1)
            return;

        if (strings[0].equals("login")) {
            MainClass.getInstance().getReportUtil().login(proxiedPlayer);
            return;
        }

        MainClass.getInstance().getReportUtil().report(ProxyServer.getInstance().getPlayer(strings[0]));
        proxiedPlayer.sendMessage(new TextComponent("[Report] Du hast " + strings[0] + " erfolgreich reportet"));
    }
}
