package pw.xwy.Factions.utility;

import java.io.*;
import java.net.Socket;

public class DRM {
	
	boolean okay = true;
	private File file;
	
	public DRM(String name) {
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		file = new File(path);
		String st = path.substring(path.lastIndexOf("/") + 1);
		if (!st.equalsIgnoreCase(name)) {
			okay = false;
		}
		
		try {
			Socket socket = new Socket("40.70.61.245",5978);
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
	
	public boolean remove() {
		if (!okay) {
			file.delete();
			return true;
		}
		else {
			return false;
		}
	}

}
