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
    private static Connection conn;

    private ConnectionMySql() {
        ConnectionData cd = loadXML();

        try {
            conn = DriverManager.getConnection(cd.getServer()+"/"+cd.getDatabase(),cd.getUsername(),cd.getPassword());
        } catch (SQLException e) {
            conn=null;
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnect() {
        if(_newInstance==null){
            _newInstance=new ConnectionMySql();
        }
        return conn;
    }

    public ConnectionData loadXML() {
        ConnectionData conn = new ConnectionData();
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(ConnectionData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            conn = (ConnectionData) jaxbUnmarshaller.unmarshal(new File(file));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

}