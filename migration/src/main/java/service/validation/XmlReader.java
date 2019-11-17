package service.validation;

import entity.PizzeriaData;
import service.exception.ServiceException;

public interface XmlReader {
    PizzeriaData read(String xmlPath) throws ServiceException;
}
