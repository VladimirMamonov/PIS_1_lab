package runner;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.IExpositionDao;
import dao.IShowroomDao;
import dao.IVisitorDao;
import entities.Exposition;
import entities.Showroom;
import entities.Visitor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestConnection {
    public static void main(String args[]) {

        System.out.println("\n");
//        testVisitorDao();

        System.out.println("\n\n");
//        testShowroomDao();
//        testConnectionPool();
        testExpositionDao();

    }


    private static void testExpositionDao(){
        try {
            System.out.println("______Test of ExpositionDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IExpositionDao expositionDao = DaoFactory.creatExpositionDao(connection1);

            System.out.println("\t*Finding exposition by id 2*");
            System.out.println(expositionDao.findById(4).toString());

            System.out.println("\n\t*Inserting new exposition*");
            Exposition exposition1 = new Exposition("super_exp", 1, 1);
            long new_id = expositionDao.save(exposition1);
            printAll(expositionDao.findAll());

            System.out.println("\n\t*Updating exposition*");
            exposition1.setExposition_topic_id(2);
            expositionDao.update(new_id , exposition1);
            printAll(expositionDao.findAll());

            System.out.println("\n\t*Deleting exposition*");
            expositionDao.delete(4);
            printAll(expositionDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("__________________________");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testShowroomDao(){
        try {
            System.out.println("______Test of ShowroomDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IShowroomDao showroomDao = DaoFactory.createShowroomDao(connection1);

            System.out.println("\t*Finding Showroom by id 1*");
            System.out.println(showroomDao.findById(2).toString());

            System.out.println("\n\t*Inserting new Showroom*");
            Showroom showroom = new Showroom("name");
            long new_id = showroomDao.save(showroom);
            printAll(showroomDao.findAll());

            System.out.println("\n\t*Updating Showroom*");
            showroom.setName("new_name");
            showroomDao.update(new_id , showroom);
            printAll(showroomDao.findAll());

            System.out.println("\n\t*Deleting Showroom*");
            showroomDao.delete(2);
            printAll(showroomDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("____________________________");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testVisitorDao(){
        try {
            System.out.println("______Test of VisitorDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IVisitorDao visitorDao = DaoFactory.createVisitorDao(connection1);



            System.out.println("\n\t*Inserting new visitor*");
            Visitor visitor1 = new Visitor("Inserted_name");
            long new_id = visitorDao.save(visitor1);


            printAll(visitorDao.findAll());


            System.out.println("\t*Finding visitor by id 1*");
            System.out.println(visitorDao.findById(1).toString());


            System.out.println("\n\t*Updating pack*");
            visitor1.setName("updated name");
            visitorDao.update(new_id , visitor1);
            printAll(visitorDao.findAll());

            System.out.println("\n\t*Deleting visitor*");
            visitorDao.delete(new_id);
            printAll(visitorDao.findAll());

            connectionPool.releaseConnection(connection1);
            System.out.println("____________________________");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testConnectionPool(){
        System.out.println("______Test of VisitorDao______");
        try {
            List<Connection> connectionList = new ArrayList<>();
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            System.out.print("Initial amount of coonections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  *Getting 100 connections from the pool*");

            for (int i = 0; i < 10; i++)
                connectionList.add(connectionPool.getConnection());

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  *Free all 100 connections from the pool*");

            for (int i = 9; i > -1; i--) {
                connectionPool.releaseConnection(connectionList.get(i));
                connectionList.remove(i);
            }

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("____________________________");
    }

    private static void printAll(List list){
        list.forEach((elem) -> {
            System.out.println(elem);
        });
        return;
    }


}
