package Model;

public class Player extends MemberDTO {

	private int position = 0; // 현재 위치
	private int round = 0; // 몇 바퀴 돌았는지
	private int money = 1000; // 플레이어의 초기 지급 금액
	private final int income = 50; // 월급

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	private boolean island = false;

	public boolean getIsland() {
		return island;
	}

	public void setIsland(boolean island) {
		this.island = island;
	}

	public Player() {

	}

	public Player(String id, String pw, String name) {
		super(id, pw, name);
	}

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;

		if (this.position > 23) {
			this.position = this.position % 24;
			getIncome();
		}
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public void getIncome() {
		setMoney(getMoney() + income);
		System.out.println("===========================");
		System.out.println("월급이 " + income + "원 지급되었습니다");
		System.out.println("현재 보유 금액은 " + (getMoney() + income) + "원 입니다.");
		System.out.println("===========================");
	}

}
