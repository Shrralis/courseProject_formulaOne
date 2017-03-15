package server;

import models.*;

import java.sql.*;

/**
 * Created by shrralis on 3/12/17.
 */
@SuppressWarnings("unchecked")
public class DatabaseWorker {
    private static DatabaseWorker iam = null;
    private Connection connection = null;

    private DatabaseWorker() {}

    public static boolean openConnection() {
        if (iam == null) {
            iam = new DatabaseWorker();
        } else {
            closeConnection();
            return openConnection();
        }

        final String sDatabaseName = "formula_one";
        final String sServerUser = "ТУТ_ВВЕДИ_ІМ'Я_КОРИСТУВАЧА_MYSQL_SERVER";
        final String sServerPassword = "ТУТ_ВВЕДИ_ПАРОЛЬ_КОРИСТУВАЧА_MYSQL_SERVER";

        try {
            if (iam.connection == null || iam.connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                iam.connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + sDatabaseName + "?useUnicode=true&characterEncoding=UTF-8",
                        sServerUser,
                        sServerPassword
                );
            }
            return true;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void closeConnection() {
        try {
            if (iam != null) {
                if (iam.connection != null && !iam.connection.isClosed()) {
                    iam.connection.close();
                }
                iam = null;
            }
        } catch (SQLException ignored) {}
    }

    public static ServerResult processQuery(ServerQuery query) {
        if (query == null) {
            System.out.println("No query from the server!");
            return ServerResult.create(1, "No query");
        } else {
            String method = query.getMethodName();

            if (method.equalsIgnoreCase("disconnect")) {
                return ServerResult.create(0, "disconnect");
            }

            if (method.equalsIgnoreCase("get")) {
                return iam.get(query);
            }

            if (method.equalsIgnoreCase("add")) {
                return iam.add(query);
            }

            if (method.equalsIgnoreCase("delete")) {
                return iam.delete(query);
            }

            if (method.equalsIgnoreCase("edit")) {
                return iam.edit(query);
            }
        }
        return null;
    }

    private ServerResult get(ServerQuery query) {
        ServerResult result = null;

        try {
            String table = query.getTableName().toLowerCase();
            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                if (table.matches("^(results)|(teams)|(pilots)|(teams_has_sponsors)|(sponsors)|(cars)|(races)$")) {

                    if (table.equalsIgnoreCase("teams_has_sponsors")) {
                        String sql;

                        if (query.getMySQLCondition().matches("(\\D|\\d)+sponsor(\\D|\\d)+")) {
                            sql = "SELECT t.* FROM `teams_has_sponsors` ths, `teams` t" +
                                    query.getMySQLCondition() + " AND ths.`team` = t.`id`;";
                            result = ServerResult.create(new List(statement.executeQuery(sql), Team.class, connection));
                        } else {
                            sql = "SELECT s.* FROM `teams_has_sponsors` ths, `sponsors` s" +
                                    query.getMySQLCondition() + " AND ths.`sponsor` = s.`id`;";
                            result = ServerResult.create(new List(statement.executeQuery(sql), Sponsor.class));
                        }
                        return result;
                    }

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM `" + table + "`" +
                            query.getMySQLCondition() + ";");

                    if (table.equalsIgnoreCase("results")) {
                        result = ServerResult.create(new List(resultSet, Result.class, connection));
                    } else if (table.equalsIgnoreCase("teams")) {
                        result = ServerResult.create(new List(resultSet, Team.class, connection));
                    } else if (table.equalsIgnoreCase("pilots")) {
                        result = ServerResult.create(new List(resultSet, Pilot.class));
                    } else if (table.equalsIgnoreCase("sponsors")) {
                        result = ServerResult.create(new List(resultSet, Sponsor.class));
                    } else if (table.equalsIgnoreCase("cars")) {
                        result = ServerResult.create(new List(resultSet, Car.class));
                    } else if (table.equalsIgnoreCase("races")) {
                        result = ServerResult.create(new List(resultSet, Race.class));
                    }
                } else {
                    System.out.println("Unknown table (" + table + ") for get()");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private ServerResult add(ServerQuery query) {
        ServerResult result = null;

        try {
            String table = query.getTableName().toLowerCase();

            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                if (table.matches("^(results)|(teams)|(pilots)|(teams_has_sponsors)|(sponsors)|(cars)|(races)$")) {
                    System.out.println(query.getInsertMysqlQuery());

                    int iResult = statement.executeUpdate(query.getInsertMysqlQuery(), Statement.RETURN_GENERATED_KEYS);

                    if (iResult >= 0) {
                        ResultSet rs = statement.getGeneratedKeys();
                        int id = 0;

                        if (rs.next()) {
                            id = rs.getInt(1);
                        }

                        if (table.equalsIgnoreCase("teams_has_sponsors")) {
                            result = ServerResult.create(0, "successfully added");
                        } else {
                            result = ServerResult.create(
                                    new List(statement.executeQuery("SELECT * FROM `" + table + "` WHERE `id` = " + id + ";"),
                                            query.getObjectToProcess().getClass(), connection)
                            );
                        }
                    } else {
                        result = ServerResult.create(1, "not added");
                    }
                }
            }
        } catch (SQLException | IllegalAccessException ignored) {}
        return result;
    }

    private ServerResult delete(ServerQuery query) {
        ServerResult result = null;

        try {
            String table = query.getTableName().toLowerCase();

            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                if (table.matches("^(results)|(teams)|(pilots)|(teams_has_sponsors)|(sponsors)|(cars)|(races)$")) {
                    int iResult = statement.executeUpdate("DELETE FROM `" + table + "` WHERE `id` = "
                            + query.getObjectToProcess().getId() + ";");

                    if (iResult >= 0) {
                        result = ServerResult.create(0, "deleted");
                    } else {
                        result = ServerResult.create(1, "not deleted");
                    }
                }
            }
        } catch (SQLException ignored) {}
        return result;
    }

    private ServerResult edit(ServerQuery query) {
        ServerResult result = null;

        try {
            String table = query.getTableName();

            if (connection != null && !connection.isClosed()) {
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                if (table.matches("^(results)|(teams)|(pilots)|(teams_has_sponsors)|(sponsors)|(cars)|(races)$")) {
                    int iResult = statement.executeUpdate(query.getUpdateMysqlQuery(), Statement.RETURN_GENERATED_KEYS);

                    if (iResult >= 0) {
                        int id = query.getObjectToProcess().getId();

                        result = ServerResult.create(
                                new List(statement.executeQuery("SELECT * FROM `" + table + "` WHERE `id` = " + id + ";"),
                                        query.getObjectToProcess().getClass(), connection)
                        );
                        System.out.println("Size: " + result.getObjects().size());
                    } else {
                        result = ServerResult.create(1, "not updated");
                    }
                }
            }
        } catch (SQLException | IllegalAccessException ignored) {}
        return result;
    }
}
