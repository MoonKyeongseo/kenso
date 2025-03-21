package Controller;

import Model.MemberDAO;
import Model.MemberDTO;
import java.util.ArrayList;

public class Controller {
	// [Controller]
	// 사용자의 요청에 따라서 그에 맞는 DB로직을 실행
	
	// dao : db연결, 쿼리문실행 메소드
	MemberDAO dao = new MemberDAO();
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
	
	// 랭킹확인용
	public ArrayList<MemberDTO> Con_lank(){ 
		resultList = dao.lank();	
		 return resultList;
	}

	
	
}
