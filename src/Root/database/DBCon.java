package Root.database;

import java.sql.*;

class DBCon {
    private  String sqlQuery;
    private Connection con;
    private  Statement stmt;
    private  ResultSet result;
    private boolean connectionCheck=true;
    DBCon() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(ClassNotFoundException ignored) {
        }
        try{

            //step2 create  the connection object
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

            //step3 create the statement object
            stmt = con.createStatement();

        } catch (Exception ex) {
            connectionCheck = false;
        }

    }

    void createTable(){
        if(connectionCheck()){
            try {
                inUpdateDelete("CREATE TABLE scoreBoard (name varchar(255), score number(10), lvlReached varchar (255))");
            } catch (SQLException ignored) {
            }
        }
    }

    boolean connectionCheck(){
        return connectionCheck;
    }

    int inUpdateDelete(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }

    ResultSet selectQuery(String sql) throws SQLException {
        return   result = stmt.executeQuery(sql);
    }


}
