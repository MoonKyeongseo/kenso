package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

   Connection conn = null;
   PreparedStatement psmt = null; // sql구문셋팅/실행
   ResultSet rs = null; // return되는 테이블 형태 결과를 저장
   // 주연진
   Statement stmt = null;

   public void getConn() { // DB 접속 메소드
      // DB 드라이버 로드
      try {
         // getConn
         // - 드라이버 로딩, url/user/pw로 db접속
         Class.forName("oracle.jdbc.driver.OracleDriver");
         // DB연결에 필요한 설정값
         String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";
         String user = "hapjeong_24SW_DS_p1_3";
         String pw = "smhrd3";
         conn = DriverManager.getConnection(url, user, pw);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void close() { // 객체를 반납할 수 있는 메소드
      try {
         if (rs != null)
            rs.close();
         if (psmt != null)
            psmt.close();
         if (conn != null)
            conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // 주연진
   public List<String> getBuildingNames() {
      List<String> buildingNames = new ArrayList<>();

      String sql = "SELECT name FROM building_info ORDER BY id"; // id 기준으로 순서대로 읽기

      try {
         // 이미 getConn()에서 연결을 받아서 사용하므로, conn을 다시 연결할 필요 없음
         stmt = conn.createStatement(); // 기존 conn 객체로 Statement 생성
         rs = stmt.executeQuery(sql);

         // 결과셋을 List에 추가
         while (rs.next()) {
            buildingNames.add(rs.getString("name"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return buildingNames;
   }

   public String getBuildingNameByIndex(int index) {
       String buildingName = null;
       String sql = "SELECT name FROM building_info WHERE id = ?";

       try {
           psmt = conn.prepareStatement(sql);
           psmt.setInt(1, index);  // 인덱스 번호를 쿼리에 매핑
           rs = psmt.executeQuery();

           if (rs.next()) {
               buildingName = rs.getString("name");
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return buildingName;
   }


}
