// Java code that accepts two inputs from the cmdline, checks if they exist in the settings.xml file, and if they do, adds them to a new XML file.

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

public class main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("\nProvide two inputs: username and password.");
            return;
        }

        String username = args[0];
        String password = args[1];

        try {
            // Load XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("settings.xml"));

            // Find the username and password elements
            NodeList nodeList = document.getElementsByTagName("server");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element userElement = (Element) nodeList.item(i);
                String storedUsername = userElement.getElementsByTagName("username").item(0).getTextContent();
                String storedPassword = userElement.getElementsByTagName("password").item(0).getTextContent();

                // Check if the input username and password match
                if (username.equals(storedUsername) && password.equals(storedPassword)) {

                    // Create a new XML document
                    Document newDocument = builder.newDocument();
                    Element serverElement = newDocument.createElement("server");
                    newDocument.appendChild(serverElement);

                    // Add id element with no text
                    Element idElement = newDocument.createElement("id");
                    serverElement.appendChild(idElement);

                    // Add username and password elements from the parsed file
                    Element newUsernameElement = newDocument.createElement("username");
                    newUsernameElement.setTextContent(storedUsername);
                    serverElement.appendChild(newUsernameElement);

                    Element newPasswordElement = newDocument.createElement("password");
                    newPasswordElement.setTextContent(storedPassword);
                    serverElement.appendChild(newPasswordElement);

                    // Write the new XML document to a file with proper formatting
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                    DOMSource source = new DOMSource(newDocument);
                    StreamResult result = new StreamResult(new File("credentials.xml"));
                    transformer.transform(source, result);

                    System.out.println("\nNew XML file created successfully.");
                    return;
                }
            }

            System.out.println("\nUsername or password not found in the XML file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}