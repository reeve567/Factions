package pw.xwy.factions.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class DRM {
	
	private final boolean use = false;
	private boolean okay = true;
	private File file;
	
	public DRM(String name) {
		if (use) {
			String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			file = new File(path);
			String st = path.substring(path.lastIndexOf("/") + 1);
			if (!st.equalsIgnoreCase(name)) {
				okay = false;
			}
			
			try {
				Socket socket = new Socket("40.70.61.245", 5978);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				boolean b = Boolean.parseBoolean(in.readLine());
				if (!b) {
					okay = false;
				}
				socket.close();
			} catch (IOException ignored) {
				okay = false;
			}
		}
	}
	
	public boolean remove() {
		if (use) {
			if (!okay) {
				file.delete();
				return true;
			}
		}
		return false;
	}
	
}
