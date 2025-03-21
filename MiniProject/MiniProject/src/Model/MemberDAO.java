package Model;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO extends DAO{
   // [DAO]
   // -> Database Access Object

   int result = 0; // return되는 int 형태 결과를 저장
   
   public int join(MemberDTO dto) { // 회원가입 쿼리 메소드

      getConn();

      String sql = "INSERT INTO USER_INFO(ID,PW,NAME) VALUES(?,?,?)";

      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, dto.getId());
         psmt.setString(2, dto.getPw());
         psmt.setString(3, dto.getName());
         result = psmt.executeUpdate(); // 실행

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(); // 도구 반납
      }

      return result;
   }

   public Player login(MemberDTO dto) { // 로그인 쿼리 메소드
	      getConn();

	      String sql = "SELECT NAME FROM USER_INFO WHERE ID = ? AND PW = ?";
	      Player p = new Player();
	      
	      try {
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, dto.getId());
	         psmt.setString(2, dto.getPw());
	         rs = psmt.executeQuery();

	         if(rs.next()) 
	            {  
	               String name = rs.getString("NAME");
	               String id = dto.getId();
	               String pw = dto.getPw();
	               
	               p = new Player(id, pw, name);
	            }
	         else {
	            p = null;
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }

	      return p;
	   }

   public int delete(MemberDTO dto) { // 회원탈퇴 쿼리 메소드

      getConn();

      String sql = "DELETE FROM USER_INFO WHERE ID = ? AND PW = ?";

      try {
         psmt = conn.prepareStatement(sql);

         psmt.setString(1, dto.getId());
         psmt.setString(2, dto.getPw());

         result = psmt.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return result;
   }

   public ArrayList<MemberDTO> lank() {

      ArrayList<MemberDTO> resultList = new ArrayList<MemberDTO>();
      getConn();

      String sql = "SELECT ID,NAME,WINNUM,LV FROM USER_INFO WHERE ROWNUM <=5 AND"
              + " WINNUM IS NOT NULL ORDER BY WINNUM DESC";

      try {
         psmt = conn.prepareStatement(sql);
         rs = psmt.executeQuery();

         // ResultSet 형태의 결과물을
         // ArrayList<MemberDTO> 타입 데이터로 변환

         while (rs.next()) {
            String id = rs.getString("ID");
            String name = rs.getString("NAME");
            int winnum = rs.getInt("WINNUM");
            int lv = rs.getInt("LV");
            resultList.add(new MemberDTO(id, name, winnum, lv));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return resultList;
   }

   public void playUpdate(MemberDTO dto) {
	      
	      getConn();

	      String sql = "UPDATE USER_INFO SET WINNUM = WINNUM + ?,"
	            + "PLAYNUM = PLAYNUM +1 WHERE ID = ?";
	            
	      try {
	         psmt = conn.prepareStatement(sql);

	         psmt.setInt(1, dto.getWinnum());
	         psmt.setString(2, dto.getId());
	         

	         psmt.executeQuery();
	         sql = "UPDATE USER_INFO SET LV = FLOOR(playnum / 10) WHERE ID = ?";
	         
	         psmt = conn.prepareStatement(sql);
	         
	         psmt.setString(1, dto.getId());
	         psmt.executeQuery();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	   }
   
}
