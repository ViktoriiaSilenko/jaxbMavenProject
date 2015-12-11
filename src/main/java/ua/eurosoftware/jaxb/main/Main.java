package ua.eurosoftware.jaxb.main;


import generated.Employee;
import generated.Name;
import generated.Salary;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main {

    public static StringWriter marshalling(JAXBContext jaxbContext) throws JAXBException {

        Employee employee = new Employee();
        employee.setId("1");
        employee.setAge(new BigInteger("25"));
        employee.setDepartment("Program development");
        employee.setPosition("Java developer");

        Name name = new Name();
        name.setFirstName("Alex");
        name.setLastName("Petrov");
        employee.setName(name);

        Salary salary = new Salary();
        salary.setValue(new BigDecimal(500));
        salary.setCurrency("dollars");
        employee.setSalary(salary);

        //marshalling the object to xml
        StringWriter writer = new StringWriter();
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(employee, writer);
        System.out.println(writer.toString());
        return writer;
    }

    public static Employee unmarshalling(JAXBContext jaxbContext, StringWriter writer) throws JAXBException {

        //unmarshalling the xml to object
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Employee employeeUnmarshalled = (Employee) jaxbUnmarshaller.unmarshal(new StringReader(writer.toString()));
        System.out.println(employeeUnmarshalled.getId());
        System.out.println(employeeUnmarshalled.getName().getFirstName());
        System.out.println(employeeUnmarshalled.getName().getLastName());
        System.out.println(employeeUnmarshalled.getAge());
        System.out.println(employeeUnmarshalled.getDepartment());
        System.out.println(employeeUnmarshalled.getPosition());
        System.out.println(employeeUnmarshalled.getSalary().getValue() + " "
                + employeeUnmarshalled.getSalary().getCurrency());
        return employeeUnmarshalled;
    }

    public static void main(String[] args) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(new Class[]{Employee.class});

            StringWriter writer = marshalling(jaxbContext);
            unmarshalling(jaxbContext, writer);

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

}
