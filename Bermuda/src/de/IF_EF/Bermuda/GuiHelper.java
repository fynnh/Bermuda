package de.IF_EF.Bermuda;

import java.util.ArrayList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GuiHelper {

	private ConfigDummy configHelper;

	/**
	 * @param args
	 */


	public GuiHelper() {

		configHelper = App.getConfigHelper();
		createXML();
	}

	public void createXML() {
		try {

			String[] cubes = configHelper.getCubeVariants();

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// generate Nifty
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("nifty");
			Attr xmlns = doc.createAttribute("xmlns");
			xmlns.setValue("http://nifty-gui.sourceforge.net/nifty.xsd");
			rootElement.setAttributeNode(xmlns);
			Attr xmlnsXsi = doc.createAttribute("xmlns:xsi");
			xmlnsXsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
			rootElement.setAttributeNode(xmlnsXsi);
			Attr xsiSchemaLocation = doc.createAttribute("xsi:schemaLocation");
			xsiSchemaLocation
					.setValue("http://nifty-gui.sourceforge.net/nifty.xsd http://nifty-gui.sourceforge.net/nifty.xsd");
			rootElement.setAttributeNode(xsiSchemaLocation);

			doc.appendChild(rootElement);

			// add controls

			Element useControls = doc.createElement("useControls");
			useControls.setAttribute("filename", "nifty-default-controls.xml");
			Element useStyles = doc.createElement("useStyles");
			useStyles.setAttribute("filename", "nifty-default-styles.xml");
			rootElement.appendChild(useStyles);
			rootElement.appendChild(useControls);

			// generate Screen
			Element screenStart = doc.createElement("screen");
			rootElement.appendChild(screenStart);

			Attr attr = doc.createAttribute("id");
			attr.setValue("start");
			screenStart.setAttributeNode(attr);
			screenStart.setAttribute("controller",
					"de.IF_EF.Bermuda.GuiController");

			// generate layers

			Element foreground = doc.createElement("layer");
			foreground.setAttribute("id", "foreground");
			foreground.setAttribute("childLayout", "center");
			screenStart.appendChild(foreground);

			// Generate Layout panels

			// foreground

			Element foregroundPanelMid = doc.createElement("panel");
			foregroundPanelMid.setAttribute("id", "panel_mid");
			foregroundPanelMid.setAttribute("height", "50%");
			foregroundPanelMid.setAttribute("align", "center");
			foregroundPanelMid.setAttribute("childLayout", "vertical");
			foreground.appendChild(foregroundPanelMid);

			// create one row
			Element row = doc.createElement("panel");
			int length = cubes.length;
			System.out.println(length);

			int rows = 0;
			if (length < 5) {
				rows = 1;
			} else {
				rows = length / 5;
			}
			int height = 100 / rows;
			System.out.println(height);
			for (int i = 0; i < length; i++) {

				if ((i + 1) % 5 == 1) {
					row = doc.createElement("panel");
					row.setAttribute("id", "row" + i);
					row.setAttribute("height", height + "%");
					row.setAttribute("width", "100%");
					row.setAttribute("childLayout", "horizontal");

					foregroundPanelMid.appendChild(row);

				}
				// Create Image

				Element elementImage = doc.createElement("image");
				elementImage.setAttribute("filename",configHelper.getCubeTextureUrl(cubes[i]));
				elementImage.setAttribute("height", "90%");
				elementImage.setAttribute("width", "100%");

				// Create Action

				Element interact = doc.createElement("interact");
				interact.setAttribute("onClick", "setCube(" + cubes[i] + ")");
				elementImage.appendChild(interact);

				Element elementText = doc.createElement("text");
				elementText.setAttribute("text", cubes[i]);
				elementText.setAttribute("font", "Interface/Fonts/Default.fnt");

				Element element = doc.createElement("panel");
				element.setAttribute("id", "" + cubes[i] + i);
				element.setAttribute("width", "20%");
				element.setAttribute("align", "center");
				element.setAttribute("childLayout", "vertical");
				element.appendChild(elementImage);
				element.appendChild(elementText);

				row.appendChild(element);

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(
					"assets/Interface/cubesChooser.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
