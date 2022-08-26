package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoFactory {

    public static IVisitorDao createVisitorDao (Connection connection) throws SQLException {
        return new VisitorDao(connection);
    }
    public static IShowroomDao createShowroomDao (Connection connection) throws SQLException {
        return new ShowroomDao(connection);
    }
    public static IExpositionDao creatExpositionDao (Connection connection) throws SQLException {
        return new ExpositionDao(connection);
    }

}
