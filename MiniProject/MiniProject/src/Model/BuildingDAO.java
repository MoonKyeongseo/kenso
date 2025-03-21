package Model;

import java.sql.*;
import java.util.ArrayList;

public class BuildingDAO extends DAO {
    
    public ArrayList<Building> list() {
        ArrayList<Building> resultList = new ArrayList<>();
        getConn();

        String sql = "SELECT * FROM BUILDING_INFO";

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int fee = rs.getInt("fee");
                int t_price = rs.getInt("t_price");
                resultList.add(new Building(name, price, fee, t_price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return resultList;
    }

     
}
