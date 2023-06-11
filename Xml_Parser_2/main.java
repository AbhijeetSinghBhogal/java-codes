// Java code that extracts specific child elements (with same names) under a parent element from the settings.xml file.

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

// ssl-profile [0]
// external-profile [1]
// red-hat-enterprise-maven-repository [2]
// nexus-profile [3]
// artifactory [4]
// former-network [5]

public class main {
    public static void main(String[] args) {
        try {
            // Load the XML file
            File xmlFile = new File("settings.xml");

            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file and create a Document object
            Document doc = builder.parse(xmlFile);

            // Get the parent element
            Element parentElement = (Element) doc.getElementsByTagName("profiles").item(0);

            // Choose the index of the child element to parse
            // Extracting external-profile element from settings.xml
            int chosenChildIndex1 = 1;

            // Get the child elements with the same name under the parent element
            NodeList childNodes1 = parentElement.getElementsByTagName("profile");

            // Check if the chosen index is within the valid range
            if (chosenChildIndex1 >= 0 && chosenChildIndex1 < childNodes1.getLength()) {
                Element chosenChildElement1 = (Element) childNodes1.item(chosenChildIndex1);

                // Create a new XML document
                Document outputDoc1 = builder.newDocument();
                
                // Import the selected child element from the original document to the output document                
                Element outputChildElement1 = (Element) outputDoc1.importNode(chosenChildElement1, true);
                
                // Append the child element to the root element in the output document
                outputDoc1.appendChild(outputChildElement1);

                // Save the output document to a file
                TransformerFactory transformerFactory1 = TransformerFactory.newInstance();
                Transformer transformer1 = transformerFactory1.newTransformer();
                DOMSource source1 = new DOMSource(outputDoc1);
                StreamResult result1 = new StreamResult(new File("external-profile.xml"));
                transformer1.transform(source1, result1);

                System.out.println("\nexternal-profile element extracted successfully.");
            } else {
                System.out.println("\nInvalid child element index.");
            }

            // Choose the index of the child element to parse
            // Extracting artifactory element from settings.xml
            int chosenChildIndex4 = 4;

            // Get the child elements with the same name under the parent element
            NodeList childNodes4 = parentElement.getElementsByTagName("profile");

            // Check if the chosen index is within the valid range
            if (chosenChildIndex4 >= 0 && chosenChildIndex4 < childNodes4.getLength()) {
                Element chosenChildElement4 = (Element) childNodes4.item(chosenChildIndex4);

                // Create a new XML document
                Document outputDoc4 = builder.newDocument();
                
                // Import the selected child element from the original document to the output document      
                Element outputChildElement4 = (Element) outputDoc4.importNode(chosenChildElement4, true);

                // Append the child element to the root element in the output document                
                outputDoc4.appendChild(outputChildElement4);

                // Save the output document to a file
                TransformerFactory transformerFactory4 = TransformerFactory.newInstance();
                Transformer transformer4 = transformerFactory4.newTransformer();
                DOMSource source4 = new DOMSource(outputDoc4);
                StreamResult result4 = new StreamResult(new File("artifactory.xml"));
                transformer4.transform(source4, result4);

                System.out.println("\nartifactory element extracted successfully.");
            } else {
                System.out.println("\nInvalid child element index.");
            }

            // Choose the index of the child element to parse
            // Extracting former-network element from settings.xml
            int chosenChildIndex5 = 5;

            // Get the child elements with the same name under the parent element
            NodeList childNodes5 = parentElement.getElementsByTagName("profile");

            // Check if the chosen index is within the valid range
            if (chosenChildIndex5 >= 0 && chosenChildIndex5 < childNodes5.getLength()) {
                Element chosenChildElement5 = (Element) childNodes5.item(chosenChildIndex5);

                // Create a new XML document
                Document outputDoc5 = builder.newDocument();
                
                // Import the selected child element from the original document to the output document           
                Element outputChildElement5 = (Element) outputDoc5.importNode(chosenChildElement5, true);

                // Append the child element to the root element in the output document                
                outputDoc5.appendChild(outputChildElement5);

                // Save the output document to a file
                TransformerFactory transformerFactory5 = TransformerFactory.newInstance();
                Transformer transformer5 = transformerFactory5.newTransformer();
                DOMSource source5 = new DOMSource(outputDoc5);
                StreamResult result5 = new StreamResult(new File("former-network.xml"));
                transformer5.transform(source5, result5);

                System.out.println("\nformer-network element extracted successfully.");
            } else {
                System.out.println("\nInvalid child element index.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}