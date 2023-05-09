package org.proyect.Controller;

import org.proyect.Model.DAO.DAOInstrument;
import org.proyect.Model.Domain.Instrument;

import java.sql.SQLException;
import java.util.List;


public class InstrumentController {
    private DAOInstrument daoInstrument;

    public InstrumentController() {
        daoInstrument = new DAOInstrument();
    }

    public void insertInstrument(Instrument instrument) throws SQLException {
        daoInstrument.insert(instrument);
    }

    public void delete(int id) throws SQLException {
        daoInstrument.delete(id);
    }

    public Instrument searchInstrument(int id) throws SQLException {
        return daoInstrument.searchById(id);
    }

    public List<Instrument> findAllInstruments() throws SQLException {
        return daoInstrument.findAll();
    }

    public void updateInstrument(Instrument instrument) throws SQLException {
        daoInstrument.update(instrument);
    }
}