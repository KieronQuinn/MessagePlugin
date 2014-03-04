package com.quinny898.plugin.msg;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class MsgCommand implements CommandExecutor {
	private static final String MSG_FORMAT = "%s > %s:%s";

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Error: PM is not available from the console");
			return;
		}
		Server server = Msg.context.getServer();
		Player player = server.getPlayer(args[0]);
		if (args.length < 2) {
			sender.sendMessage("Usage: /msg <name> <message>");
		} else {
			String message = "";
			for (String arg : Arrays.copyOfRange(args, 1, args.length))
				message += " " + arg;

			if (player != null) {
				player.sendMessage(String.format(MSG_FORMAT, sender.getName(),
						"Me", message));
				player.setMetaData("reply_user",
						new WeakReference<CommandSender>(sender));
				sender.sendMessage(String.format(MSG_FORMAT, "Me",
						player.getDisplayName(), message));
			} else {
				sender.sendMessage("Error: Player not found");
			}
		}
	}
}
