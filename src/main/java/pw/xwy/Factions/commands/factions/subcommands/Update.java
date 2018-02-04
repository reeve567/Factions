package pw.xwy.Factions.commands.factions.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pw.xwy.Factions.commands.SubCommand;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class Update extends SubCommand {
	public Update() {
		super("update", "", "gets the version saved in the cloud", true);
	}
	
	private void downloadFromUrl(URL url, String localFilename, CommandSender sender) throws IOException {
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			//connect
			URLConnection urlConn = url.openConnection();
			//get connection inputstream
			is = urlConn.getInputStream();
			//open outputstream to local file
			fos = new FileOutputStream(localFilename);
			//declare 4KB buffer
			byte[] buffer = new byte[4096];
			int len;
			
			//while we have availble data, continue downloading and storing to local file
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			sender.sendMessage(ChatColor.GOLD + "Plugin has been downloaded.");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
		}
	}
	
	@Override
	public void run(final Player p, String[] args) {
		Thread download = new Thread() {
			public void run() {
				
				URL url = null;
				try {
					url = new URL("https://drive.google.com/uc?export=download&id=142E7FitZxBAJ0oVupUaXRVzwWiN0Lm_j");
				} catch (MalformedURLException e) {
					p.sendMessage(ChatColor.RED + "Could not get file");
				}
				String localFilename = System.getProperty("user.dir") + "/plugins/Factions-BETA.jar"; //needs to be replaced with local file path
				try {
					assert url != null;
					downloadFromUrl(url, localFilename, p);
				} catch (IOException e) {
					p.sendMessage(ChatColor.RED + "Could not get file");
				}
			}
		};
		download.start();
	}
}
