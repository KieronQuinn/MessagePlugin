package com.quinny898.plugin.msg;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;

@Manifest(name = "Private Message by Quinny898")
public class Msg extends MBServerPlugin {
	static Msg context;

	public void onEnable() {
		context = this;
		this.getPluginManager().registerCommand("msg", new MsgCommand());

	}

}