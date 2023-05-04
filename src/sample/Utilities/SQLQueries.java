package sample.Utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SQLQueries {
    // ~~~DONE~~~ \\

    public static int GetCount(String column, String table) {
        int value = 0;

        String sql = "Select count(" + column + ") as " + column + " from " + table;

        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET COUNT");
        }
        return value;
    }
    public static int GetCount(String column, String table, String where, int valueToCheck) {
        int value = 0;

        String sql = "Select count(" + column + ") as " + column + " from " + table + " where " + where + " = " + valueToCheck;

        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET COUNT");
        }
        return value;
    }
    /*public static int GetCount(String column, String table, Map<String, Integer> conditionMap) {
        int value = 0;

        StringBuilder sql = new StringBuilder("Select count(" + column + ") as " + column + " from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Integer> iterator : conditionMap.entrySet())
        {
            sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }
        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET COUNT");
        }
        return value;
    }*/
    public static int GetCount(String column, String table, Map<String, Object> conditionMap) {
        int value = 0;

        StringBuilder sql = new StringBuilder("Select count(" + column + ") as " + column + " from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Object> iterator : conditionMap.entrySet())
        {
            if (iterator.getValue() instanceof Integer)
            {
                sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
                i++;
                if (i < conditionMap.size())
                    sql.append(" and ");
            }
            else
            {
                sql.append(iterator.getKey()).append(" = ").append("\"").append(iterator.getValue()).append("\"");
                i++;
                if (i < conditionMap.size())
                    sql.append(" and ");
            }
        }
        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET COUNT");
        }
        return value;
    }

    public static boolean UpdateSql(String table, String column, String where, int valueToCheck, Object valueUpdateTo) {
        try {
            Connection conn = ConnectionUser.getConnection();

            String sql;
            if (valueUpdateTo instanceof String)
                sql = "update " + table + " set " + column + " = \"" + valueUpdateTo + "\" where " + where + " = " + valueToCheck;
            else
                sql = "update " + table + " set " + column + " = " + valueUpdateTo + " where " + where + " = " + valueToCheck;


            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL UPDATE");
            return false;
        }
    }
    public static void UpdateSql(String table, String column, Map<String, Integer> conditionMap, String valueUpdateTo) {
        try {
            Connection conn = ConnectionUser.getConnection();

            StringBuilder sql = new StringBuilder("update " + table + " set " + column + " = " + valueUpdateTo + " where ");

            int i = 0;
            for (Map.Entry<String, Integer> iterator : conditionMap.entrySet())
            {
                sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
                i++;
                if (i < conditionMap.size())
                    sql.append(" and ");
            }

            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL UPDATE");
        }
    }

    public static ResultSet GetResults(String table) {
        String sql = "";
        sql = "Select * from " + table;

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            //stmt.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
        }
        return null;
    }
    public static ResultSet GetResults(String table, String where, int valueToCheck) {
        String sql = "";
        sql = "Select * from " + table + " where " + where + " = " + valueToCheck;

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            //stmt.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
        }
        return null;
    }
    public static ResultSet GetResultsLike(String table, String where, String valueToCheck) {
        String sql = "";
        sql = "Select * from " + table + " where " + where + " LIKE \'" + valueToCheck + "\'";

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            //stmt.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
        }
        return null;
    }
    public static ResultSet GetResults(String table, Map<String, Object> conditionMap) {
        StringBuilder sql = new StringBuilder("Select * from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Object> iterator : conditionMap.entrySet())
        {
            if (iterator.getValue() instanceof Integer)
            {
                sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
            }
            else
            {
                sql.append(iterator.getKey()).append(" = ").append("\"").append(iterator.getValue()).append("\"");
            }
            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            //stmt.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
        }
        return null;
    }

    public static Date GetDate(String columnName, String table, String where, int valueToCheck) {
        Date value = Date.valueOf("2000-01-01");

        String sql = "";
        sql = "Select " + columnName + " from " + table + " where " + where + " = " + valueToCheck;

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value = rs.getDate(1);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
            return null;
        }
        return value;
    }
    public static Date GetDate(String columnName, String table, Map<String, Integer> conditionMap) {
        Date value = Date.valueOf("2000-01-01");

        StringBuilder sql = new StringBuilder("Select " + columnName + " from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Integer> iterator : conditionMap.entrySet())
        {
            sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }

        Connection conn = ConnectionUser.getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value = rs.getDate(1);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET DATE");
            return null;
        }
        return value;
    }

    public static int GetInt(String columnName, String table, String where, Object valueToCheck) {
        int value = 0;

        String sql = "Select " + columnName + " from " + table + " where " + where + " = "+ ((valueToCheck instanceof String) ? valueToCheck : Integer.parseInt(valueToCheck.toString()));
        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            if (rs != null && rs.next())
            {
                value = rs.getInt(columnName);
            }
            else
            {
                value = -1;
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET INT");
            return -1;
        }
        return value;
    }

    public static int GetInt(String columnName, String table, Map<String, Object> conditionMap) {
        int value = 0;

        StringBuilder sql = new StringBuilder("Select " + columnName + " from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Object> iterator : conditionMap.entrySet())
        {
            sql.append(iterator.getKey()).append(" = ");
            if (iterator.getValue() instanceof String)
                sql.append("\'").append(iterator.getValue()).append("\'");
            else
                sql.append(iterator.getValue());

            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }

        Connection conn = ConnectionUser.getConnection();

        System.out.println(sql);
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value = rs.getInt(columnName);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET INT");
            return -1;
        }
        return value;
    }

    public static String GetString(String columnName, String table, String where, int valueToCheck) {
        String string = "";

        String sql = "Select " + columnName + " from " + table + " where " + where + " = "+ valueToCheck;

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            string = rs.getString(columnName);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL GET STRING");
            return "";
        }

        return string;
    }
    public static String GetString(String columnName, String table, Map<String, Integer> conditionMap) {
        String string = "";

        StringBuilder sql = new StringBuilder("Select " + columnName + " from " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Integer> iterator : conditionMap.entrySet())
        {
            sql.append(iterator.getKey()).append(" = ").append(iterator.getValue());
            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql.toString());
            ResultSet rs = stmt.getResultSet();
            rs.next();
            string = rs.getString(columnName);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL GET STRING");
            return "";
        }

        return string;
    }

    public static Map<Object, Object> GetMapString(String keyColumn, String valueColumn, String table) {
        Map<Object, Object> myMap = new HashMap<>();

        String sql = "Select * from " + table;

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next())
            {
                int keyMap = rs.getInt(keyColumn);
                String valueMap = rs.getString(valueColumn);
                myMap.put(keyMap, valueMap);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL GET STRING");
            return null;
        }

        return myMap;
    }
    public static Map<Object, Object> GetMapInt(String keyColumn, String valueColumn, String table) {
        Map<Object, Object> myMap = new HashMap<>();

        String sql = "Select * from " + table;

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next())
            {
                int keyMap = rs.getInt(keyColumn);
                int valueMap = rs.getInt(valueColumn);
                myMap.put(keyMap, valueMap);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL GET STRING");
            return null;
        }

        return myMap;
    }
    public static Map<Object, Object> GetMapString(String keyColumn, String valueColumn, String table, String where, int valueToCheck) {
        Map<Object, Object> myMap = new HashMap<>();

        String sql = "Select * from " + table + " where " + where + " = "+ valueToCheck;

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next())
            {
                int keyMap = rs.getInt(keyColumn);
                String valueMap = rs.getString(valueColumn);
                myMap.put(keyMap, valueMap);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL GET STRING");
            return null;
        }

        return myMap;
    }
    public static Map<Object, Object> GetMapInt(String keyColumn, String valueColumn, String table, String where, int valueToCheck) {
        Map<Object, Object> myMap = new HashMap<>();

        String sql = "Select * from " + table + " where " + where + " = "+ valueToCheck;

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next())
            {
                int keyMap = rs.getInt(keyColumn);
                int valueMap = rs.getInt(valueColumn);
                myMap.put(keyMap, valueMap);
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL GET STRING");
            return null;
        }

        return myMap;
    }

    public static boolean Insert(String table, ArrayList<Object> arrayOfValues) {
        StringBuilder sql = new StringBuilder("insert into " + table + " values( ");

        int i = 0;
        for (Object object : arrayOfValues)
        {
            if (object instanceof String)
                sql.append("\"").append(object).append("\"");
            else
                sql.append(object);

            i++;
            if (i == arrayOfValues.size())
                sql.append(")");
            else
                sql.append(", ");
        }
        System.out.println(sql);

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql.toString());
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean Insert(String table, ArrayList<Object> arrayOfColumns, ArrayList<Object> arrayOfValues) {
        StringBuilder sql = new StringBuilder("insert into " + table + " (");

        int i = 0;
        for (Object object : arrayOfColumns)
        {
            sql.append(object);

            i++;
            if (i == arrayOfColumns.size())
                sql.append(")");
            else
                sql.append(", ");
        }
        sql.append(" values( ");

        i = 0;
        for (Object object : arrayOfValues)
        {
            if (object instanceof String)
                sql.append("\"").append(object).append("\"");
            else
                sql.append(object);

            i++;
            if (i == arrayOfValues.size())
                sql.append(")");
            else
                sql.append(", ");
        }
        System.out.println(sql);

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql.toString());
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean Delete(String table, Map<String, Object> conditionMap) {
        StringBuilder sql = new StringBuilder("Delete From " + table + " where ");

        int i = 0;
        for (Map.Entry<String, Object> iterator : conditionMap.entrySet())
        {
            sql.append(iterator.getKey()).append(" = ");

            if (iterator.getValue() instanceof String)
                sql.append("\"").append(iterator.getValue()).append("\"");
            else
                sql.append(iterator.getValue());

            i++;
            if (i < conditionMap.size())
                sql.append(" and ");
        }
        System.out.println(sql);

        Connection con = ConnectionUser.getConnection();

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql.toString());
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }

        return true;
    }

    public static int SumOfColumn(String column, String table){
        int value = 0;

        String sql = "Select sum(" + column + ") as " + column + " from " + table;

        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error: SQL Sum Of Column");
        }
        return value;

    }
    public static int SumOfColumn(String column, String table, String where, Object checkValue){
        int value = 0;

        String sql = "Select sum(" + column + ") as " + column + " from " + table + " where " + where + " = " + ((checkValue instanceof String) ? "\"" + checkValue + "\"" : checkValue);

        Connection conn = ConnectionUser.getConnection();

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();
            rs.next();
            value += rs.getInt(column);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(sql);
            System.out.println("Error: SQL Sum Of Column");
            return 0;
        }
        return value;

    }
}
