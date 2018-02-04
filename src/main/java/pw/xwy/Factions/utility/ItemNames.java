package pw.xwy.Factions.utility;
// made by reeve
// on 12:17 AM

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

////////////////////////////////////////////////////////////////////////////////
// File copyright last updated on: 2/3/18 9:22 AM                              /
//                                                                             /
// Copyright (c) 2018.                                                         /
// All code here is made by Xwy (gitout#5670) unless otherwise noted.          /
//                                                                             /
//                                                                             /
////////////////////////////////////////////////////////////////////////////////

public class ItemNames {
	
	private static Map<String, String> Items = new HashMap<String, String>();
	
	static {
		URL url = null;
		try {
			url = new URL("http://minecraft-ids.grahamedgecombe.com/items.tsv");
		} catch (MalformedURLException ignored) {
		}
		
		
		if (url != null) {
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
				
				String row;
				while ((row = reader.readLine()) != null) {
					row = row.trim();
					if (row.isEmpty())
						continue;
					String[] cols = row.split("\t");
					String name = cols[2];
					String id = cols[0];
					String metadata = cols[1];
					String idAndMetadata = metadata.equals("0") ? id : (id + ":" + metadata);
					Items.put(idAndMetadata, name);
				}
			} catch (IOException ignored) {
			}
		}
	}
	
	public static String get(String ID) {
		
		return Items.get(ID);
	}
	
	public static String get(String ID, short dur) {
		if (dur == 99) {
			return "Spawn Iron Golem";
		}
		return Items.get(ID + ":" + dur);
	}
}
