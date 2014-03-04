package com.quinny898.plugin.msg;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.Server;
import com.mbserver.api.game.Player;

public class ReplyCommand implements CommandExecutor {
	private static final String MSG_FORMAT = "%s > %s: %s";

	@Override
	public void execute(String command, CommandSender sender, String[] args,
			String label) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Error: PM is not available from the console");
			return;
		}
		Server server = Msg.context.getServer();
		Player player = server.getPlayer(args[0]);

		String message = " ";
		for (String arg : Arrays.copyOfRange(args, 1, args.length))
			message += " " + arg;

		if (player != null) {
			WeakReference<CommandSender> oldSender = ((Player) sender)
					.getMetaData("reply_user", null);
			if (oldSender != null && oldSender.get() != null) {
				oldSender.get().sendMessage(
						String.format(MSG_FORMAT, sender.getName(), "Me",
								message));
				sender.sendMessage(String.format(MSG_FORMAT, "Me", oldSender
						.get().getName(), message));

			} else {
				sender.sendMessage("Error: You have not had a message sent to you this session");
			}
		}
	}
}
