package org.example;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, SAXException {

        Main.validateFile(new File("src/main/resources/vdi3814.xsd.xml"), new File("src/main/resources/vdi3814.xsd"));
    }
    private static void validateFile(File xmlFile, File xsdFile) throws SAXException, IOException
    {
        System.out.printf(xsdFile.getAbsolutePath());

        // 1. Lookup a factory for the W3C XML Schema language
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/XML/XMLSchema/v1.1");
        factory.setFeature("http://apache.org/xml/features/validation/cta-full-xpath-checking",true);

        // 2. Compile the schema.
        File schemaLocation = xsdFile;
        Schema schema = factory.newSchema(schemaLocation);

        // 3. Get a validator from the schema.
        Validator validator = schema.newValidator();

        // 4. Parse the document you want to check.
        Source source = new StreamSource(xmlFile);

        // 5. Check the document
        try
        {
            validator.validate(source);
            System.out.println(xmlFile.getName() + " is valid.");
        }
        catch (SAXException ex)
        {
            System.out.println(xmlFile.getName() + " is not valid because ");
            System.out.println(ex.getMessage());
            // ex.printStackTrace();
        }
    }
}