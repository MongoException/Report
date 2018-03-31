package de.mats.report;

import de.mats.report.command.ReportCommand;
import de.mats.report.util.ReportUtil;
import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Erstellt von Boss
 */
@Getter
public class Report extends Plugin {

    @Getter
    private static Report instance;

    private ReportUtil reportUtil;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        intialize();
    }

    private void intialize() {

        reportUtil = new ReportUtil();

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReportCommand());
    }
}
