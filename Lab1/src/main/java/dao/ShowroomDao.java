package dao;


import entities.Showroom;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowroomDao implements IShowroomDao{
    private final Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";

    private final Statement statement;


    public ShowroomDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Showroom getShowroom(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);

        return new Showroom(id,name);
    }

    @Override
    public List<Showroom> findAll() {
        String query = "SELECT * FROM showroom";
        List<Showroom> showroomList = new ArrayList<Showroom>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Showroom question = getShowroom(resultSet);
                showroomList.add(question);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return showroomList;
    }



    @Override
    public Showroom findById(long id){
        String query = "SELECT * FROM showroom WHERE showroom.id=" + id;

        Showroom showroom = new Showroom();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
//            System.out.println(resultSet.getLong(COLUMN_ID));
//            System.out.println(resultSet.getString(COLUMN_NAME));

            showroom = getShowroom(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return showroom;

    };

    @Override
    public long save(Showroom showroom) {
        String query = "INSERT INTO showroom (name) VALUES (";
        query += "'"+showroom.getName()+"'"
                +") RETURNING id" ;

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
    public void update(long id, Showroom showroom) {
        String query = "UPDATE showroom SET name = '"+showroom.getName()
                +"'"
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };




    @Override
    public void delete(long id) {
        String query = "DELETE FROM showroom WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


}
