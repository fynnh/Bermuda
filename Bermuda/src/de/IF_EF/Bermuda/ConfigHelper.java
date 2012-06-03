/**
 * Klasse mit sämtlichen Konfigurationen 
 * für das aktuelle Spiel
 * @author 
 * @version 1.0
 */
package de.IF_EF.Bermuda;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class ConfigHelper {

	private static int groundX;
	private static int groundZ;
	private static String groundTextureUrl;
	private static HashMap<String, String> cubes = new HashMap<String, String>();
	private static Application application;
	private static GameHelper gameHelper;
	private static GraphicsHelper graphicsHelper;

	public static void createConfig(File file) {
		System.out.println(file);
		ZipEntry zipEntry = new ZipEntry("config.xml");
		BufferedInputStream bis = null;
		String xml = new String();
		try {
			ZipFile zipFile = new ZipFile(file);
			bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
			byte[] buffer = null;
			int avail = bis.available();
			if (avail > 0) {
				buffer = new byte[avail];
				bis.read(buffer, 0, avail);
			}
			readConfig(buffer);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readConfig(byte[] xml) {
		ArrayList<String> blocks = new ArrayList<String>();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream stream = new ByteArrayInputStream(xml);
			XMLStreamReader stax;
			stax = inputFactory.createXMLStreamReader(stream);
			while (stax.hasNext()) {
				stax.next();
				if (stax.hasName() && stax.isStartElement()) {
					String s = stax.getName().toString();
					if (stax.hasNext()) {
						stax.next();
						if (stax.hasText() && !stax.getText().trim().isEmpty()) {
							if (s.equals("sizeX")) {
								groundX = new Integer(stax.getText().trim());
							}
							if (s.equals("sizeZ")) {
								groundZ = new Integer(stax.getText().trim());
							}
							if (s.equals("blockName")) {
								blocks.add(stax.getText().trim());
							}
							if (s.equals("textureUrl")) {
								blocks.add(stax.getText().trim());
							}
						}
					}
				}
			}
			for (int i = 0; i < blocks.size(); i++) {

				String s1 = blocks.get(i);
				i++;
				String s2 = blocks.get(i);
				cubes.put(s1, s2);
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createApp();
		
	}

	public static void createApp() {
		graphicsHelper = new GraphicsHelper(groundX, groundZ, groundTextureUrl);
	}

	public static String getBlockTextureUrl(String block) {
		return cubes.get(block);
	}

	public static Application getApplication() {
		return application;
	}

	public static GameHelper getGameHelper() {
		return gameHelper;
	}

	public static GraphicsHelper getGraphicsHelper() {
		return graphicsHelper;
	}

}
