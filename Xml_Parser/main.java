// Java code that extracts all elements from the settings.xml file and puts them in separate XML files in a new folder.

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class main { 

    public static void main(String[] args) {
        String inputFilePath = "settings.xml";
        String outputDirectory = "Individual_Elements";

        try {
            // Create the output directory if it doesn't exist
            File outputDir = new File(outputDirectory);
            outputDir.mkdirs();

            // Create the document builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File(inputFilePath));

            // Get the root element of the document
            Element root = document.getDocumentElement();

            // Get a list of all child elements
            NodeList elements = root.getChildNodes();

            // Process each child element
            for (int i = 0; i < elements.getLength(); i++) {
                Node element = elements.item(i);

                // Ignore non-element nodes (such as text nodes)
                if (element.getNodeType() == Node.ELEMENT_NODE) {
                    // Create a new document for each element
                    Document newDocument = builder.newDocument();
                    // Import the element into the new document
                    Node importedNode = newDocument.importNode(element, true);
                    // Append the imported element to the new document
                    newDocument.appendChild(importedNode);

                    // Create the output file
                    String outputFileName = outputDirectory + "/" + "element" + i + ".xml";
                    File outputFile = new File(outputFileName);

                    // Transform the new document into an XML file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                    DOMSource source = new DOMSource(newDocument);
                    StreamResult result = new StreamResult(outputFile);
                    transformer.transform(source, result);

                    System.out.println("\nElement " + i + " saved to " + outputFileName + ".");
                }
            }

            System.out.println("\nParsing and splitting XML file complete!");
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}