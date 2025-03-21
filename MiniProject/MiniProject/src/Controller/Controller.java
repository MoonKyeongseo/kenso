package Controller;

import Model.MemberDAO;
import Model.MemberDTO;
import Model.Player;

import java.util.ArrayList;
import java.util.Random;

import Model.Building;
import Model.BuildingDAO;
import Model.BuildingDTO;
import Model.Dice;
//import Model.Dice;

public class Controller {
   // [Controller]

   // dao : db연결, 쿼리문실행 메소드
   MemberDAO dao = new MemberDAO();
   BuildingDAO dao_b = new BuildingDAO();
   Player p = new Player();
   
   // join()의 리턴데이터
   int result = 0;
   // list()의 리턴데이터
   ArrayList<MemberDTO> resultList = new ArrayList<MemberDTO>();
   // rolldice 해보자
   private int dice1;
   private int dice2;
   

   // 회원가입용
   public int Con_join(String id, String pw, String name) {
      // 전달해야 하는 데이터를 하나의 묶음으로 만들어서(객체 생성)
      MemberDTO dto = new MemberDTO(id, pw, name);
      result = dao.join(dto);
      return result;
   }

   // 회원탈퇴용
   public int Con_delete(String id, String pw) {

      MemberDTO dto = new MemberDTO();
      dto.setId(id);
      dto.setPw(pw);
      result = dao.delete(dto);
      return result;
   }

   // 로그인용
   public Player Con_login(String id, String pw) {
	      MemberDTO dto = new MemberDTO(id, pw);
	      p = dao.login(dto);
	      return p;
	   }

   // 랭킹확인용
   public ArrayList<MemberDTO> Con_lank() {
      resultList = dao.lank();
      return resultList;
   }
   
   // 빌딩 객체 생성용
   public ArrayList<Building> Con_list() {
        return dao_b.list();
    }
   

   // 주연진
   // 랜덤값으로 나온 주사위결과 확인용
   public int[] rollDice() {
       Random random = new Random();
       setDice1(random.nextInt(6) + 1);
       setDice2(random.nextInt(6) + 1);
//       System.out.println("🎲 주사위 결과: " + dice1 + " + " + dice2 + " = " + (dice1 + dice2));
       
       return new int[]{getDice1(), getDice2()};
   }
   
//   public void Con_dice() {
//	   
//       Dice.dicesample();
//    }

public int getDice1() {
	return dice1;
}

public void setDice1(int dice1) {
	this.dice1 = dice1;
}

public int getDice2() {
	return dice2;
}

public void setDice2(int dice2) {
	this.dice2 = dice2;
}

public void conPlayUpdate(MemberDTO dto) {
    dao.playUpdate(dto);
 }
   
   
   
   
   
   
   


}
