package Controller;

import Model.MemberDAO;
import Model.MemberDTO;
// <<<<<<< kenso
// import Model.dice;
// =======
// import Model.Player;
// >>>>>>> main

import java.util.ArrayList;

import Model.Building;
import Model.BuildingDAO;
import Model.BuildingDTO;

public class Controller {
	// [Controller]
	// 사용자의 요청에 따라서 그에 맞는 DB로직을 실행

	// dao : db연결, 쿼리문실행 메소드
	MemberDAO dao = new MemberDAO();
	BuildingDAO dao_b = new BuildingDAO();
	Player p = new Player();
	
	// join()의 리턴데이터
	int result = 0;
	// list()의 리턴데이터
	ArrayList<MemberDTO> resultList = new ArrayList<MemberDTO>();

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
		
		if(p.getName() != null)
		{
		   return p;
		}
		
		return null;
	}

	// 랭킹확인용
	public ArrayList<MemberDTO> Con_lank() {
		resultList = dao.lank();
		return resultList;
	}
	
// <<<<<<< kenso
// 	// 주사위 굴리기
// 	public void Con_dice () {
// 		dice.dicesample();
// 	}
// =======
// 	// 빌딩 객체 생성용
// 	public ArrayList<Building> Con_list() {
//         return dao_b.list();
//     }
// >>>>>>> main

}
