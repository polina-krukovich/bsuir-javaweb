package service.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import entity.PizzeriaData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import service.exception.ServiceException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * This is a Singleton class that provides a basic service for performing operations with XML files,
 * such as deserialization of a XML file into application defined complex <code>PizzeriaData</code>
 * object and validation of a XML file according to the XSD scheme.
 *
 * @see PizzeriaData
 * @see XmlMapper
 * @see Validator
 * @see SchemaFactory
 * @see Schema
 *
 * @author Polina Krukovich
 */
public class XmlService implements XmlValidator, XmlReader {
    public static final XmlService INSTANCE = new XmlService();

    private final Logger LOGGER = LogManager.getLogger(getClass());

    private XmlService() {}

    /**
     * This method performs validation of the specified XML file according to the XSD scheme
     * @param xmlPath path to the XML file
     * @param xsdPath path to the XSD scheme
     * @throws ServiceException if validation of the XML file fails
     *
     * @see Validator
     * @see SchemaFactory
     * @see Schema
     */
    public void validate(String xmlPath, String xsdPath) throws ServiceException {
        try {
            File xsdFile = new File(xsdPath);
            File xmlFile = new File(xmlPath);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            LOGGER.log(Level.INFO, "XML file validated successfully.");
        } catch (IOException | SAXException e) {
            ServiceException serviceException = new ServiceException(e.getMessage(), e);
            LOGGER.log(Level.ERROR, serviceException.getMessage());
            throw serviceException;
        }
    }

    /**
     * This method performs deserialization of a XML file into application defined complex
     * <code>PizzeriaData</code> object
     * @param xmlPath path to the XML file
     * @return <code>PizzeriaData</code> object
     * @throws ServiceException if the deserialization fails
     *
     * @see PizzeriaData
     * @see XmlMapper
     */
    public PizzeriaData read(String xmlPath) throws ServiceException {
        PizzeriaData pizzeriaData;
        try {
            File xmlFile = new File(xmlPath);
            ObjectMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            pizzeriaData = xmlMapper.readValue(xmlFile, PizzeriaData.class);
            LOGGER.log(Level.INFO, "XML file deserialized successfully.");
        } catch (IOException e) {
            ServiceException serviceException = new ServiceException(e.getMessage(), e);
            LOGGER.log(Level.ERROR, serviceException.getMessage());
            throw serviceException;
        }
        return pizzeriaData;
    }
}
