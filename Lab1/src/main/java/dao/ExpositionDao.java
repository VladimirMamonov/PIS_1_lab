package dao;

import entities.Exposition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDao implements IExpositionDao{
    private final String SELECT_QUESTIONS = "SELECT exposition.exposition";
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_SHOWROOM_ID = "showroom_id";
    private final static String COLUMN_EXPOSITION_TOPIC_ID = "exposition_topic_id";
    private final Statement statement;
    private final Connection connection;






    public ExpositionDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Exposition getExposition(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String exposition_name = resultSet.getString(COLUMN_NAME);
        long showroom_id = resultSet.getLong(COLUMN_SHOWROOM_ID);
        long exposition_topic_id = resultSet.getLong(COLUMN_EXPOSITION_TOPIC_ID);

        return new Exposition(id, exposition_name, showroom_id, exposition_topic_id);
    }

    @Override
    public List<Exposition> findAll() {
        String query = "SELECT * FROM exposition";
        List<Exposition> expositionList = new ArrayList<Exposition>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Exposition exposition = getExposition(resultSet);
                expositionList.add(exposition);
            }

            resultSet.close();
        } catch (
        SQLException e) {
            e.printStackTrace();
        }

        return expositionList;
    }



    @Override
    public Exposition findById(long id){
        String query = "SELECT * FROM exposition WHERE exposition.id=" + id;

        Exposition exposition = new Exposition();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            exposition = getExposition(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return exposition;

    };

    @Override
    public long save(Exposition exposition) {
        String query = "INSERT INTO exposition (name, showroom_id, exposition_topic_id) VALUES (";
        query += "'"+exposition.getName()+"',"
                +exposition.getShowroom_id()+","+exposition.getExposition_topic_id()+") RETURNING id" ;

        long id = 0;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return id;
    };

    @Override
    public void update(long id, Exposition exposition) {
        String query = "UPDATE exposition SET name = '"+exposition.getName()
                +"', showroom_id = "+exposition.getShowroom_id()+", exposition_topic_id = "+exposition.getExposition_topic_id()
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    public void delete(long id) {
        String query = "DELETE FROM exposition WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };

}








