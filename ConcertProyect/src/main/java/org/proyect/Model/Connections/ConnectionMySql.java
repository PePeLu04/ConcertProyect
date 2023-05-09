package org.proyect.Model.Connections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySql {
    private String file = "connection.xml";
    private static ConnectionMySql _newInstance;
    private static Connection con;

    private ConnectionMySql() {
        ConnectionData cd = loadXML();

        try {
            con = DriverManager.getConnection(cd.getServer()+"/"+cd.getDatabase(),cd.getUsername(),cd.getPassword());
        } catch (SQLException e) {
            con=null;
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnect() {
        if(_newInstance==null){
            _newInstance=new ConnectionMySql();
        }
        return con;
    }

    public ConnectionData loadXML() {
        ConnectionData con = new ConnectionData();
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(ConnectionData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            con = (ConnectionData) jaxbUnmarshaller.unmarshal(new File(file));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

}