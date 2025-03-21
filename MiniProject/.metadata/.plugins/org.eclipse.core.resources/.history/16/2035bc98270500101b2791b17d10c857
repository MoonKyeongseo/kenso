package Model;

public class MemberDTO {
	// [DTO]
	// -> Database Transfer Object

	private String id;
	private String pw;
	private String name;
	private int playnum;
	private int winnum;
	private int lv;

	// 회원가입을 위한 생성자 메소드
	public MemberDTO(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	// 랭킹조회를 위한 생성자 메소드
	public MemberDTO(String id, String name, int winnum, int lv) {
		this.id = id;
		this.name = name;
		this.winnum = winnum;
		this.lv = lv;
	}

	// 로그인
	public MemberDTO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public MemberDTO() {

	}

	public String getId() {
		return id;
	}

	public int getPlaynum() {
		return playnum;
	}

	public int getWinnum() {
		return winnum;
	}

	public int getLv() {
		return lv;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public void setPlaynum(int playnum) {
		this.playnum = playnum;
	}

	public void setWinnum(int winnum) {
		this.winnum = winnum;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
