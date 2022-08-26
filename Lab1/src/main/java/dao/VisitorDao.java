package dao;

import entities.Visitor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorDao implements IVisitorDao {
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private Statement statement;



    public VisitorDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Visitor getVisitor(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);

        return new Visitor(id, name);
    }

    @Override
    public List<Visitor> findAll() {
        String query = "SELECT * FROM visitor";
        List<Visitor> visitorList = new ArrayList();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Visitor visitor = getVisitor(resultSet);
                visitorList.add(visitor);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return visitorList;
    }



    @Override
    public Visitor findById(long id){
       String query = "SELECT * FROM visitor WHERE visitor.id=" + id;

        Visitor visitor = new Visitor();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            visitor = getVisitor(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return visitor;

    };

    @Override
    public long save(Visitor visitor) {
        String query = "INSERT INTO visitor (name) VALUES";
        query += "('"+visitor.getName()+"') returning id";

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
    public void update(long id, Visitor visitor) {
        String query = "UPDATE visitor SET name ='"+visitor.getName()
                +"' WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    public void delete(long id) {
        String query = "DELETE FROM visitor WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };



}
