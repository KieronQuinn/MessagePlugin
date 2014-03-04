package com.quinny898.plugin.msg;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class MsgCommand implements CommandExecutor {

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		Server server = Msg.context.getServer();
		Player player = server.getPlayer(args[0]);
		if (args.length != 2) {
			sender.sendMessage("Usage: /msg <name> <message>");
		} else {
			if (player != null) {
				player.sendMessage("Msg from " + sender.getName() + ": "
						+ args[1] + "\nUse /msg " + sender.getName()
						+ " to reply");
				sender.sendMessage(sender.getName() + " > " + player.getName()
						+ " "+ args[1]);
			} else {
				sender.sendMessage("Error: Player not found");
			}
		}
	}
}