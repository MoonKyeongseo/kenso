package View;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import javax.sound.sampled.Clip;

import Model.Building;
import Model.BuildingDTO;
import Model.Dice;
import Controller.Controller;
import Controller.SoundController;
import Model.MemberDTO;
import Model.Player;

public class Main {
	

	public static void print(ArrayList<Player> players, ArrayList<Building> buildings) {

		ArrayList<String> s = new ArrayList<String>();
		String start = "START";
		String temp;
		int cnt = 0;
		for (int i = 0; i < buildings.size(); i++) {
			s.add(buildings.get(i).getName());
			for (int j = 0; j < players.size(); j++) {

				if (players.get(j).getPosition() - 1 == i) {
					s.set(i, players.get(j).getColor() + buildings.get(i).getName() + "\u001B[0m");
					cnt++;
				} else if (players.get(j).getPosition() == 0) {
					start = "\u001B[92m" + "START" + "\u001B[0m";
				}
			}
		}

		System.out.println("\n" + 
				"+---------------+---------------+---------------+---------------+---------------+---------------+----------------+\n"
						+ "|  [0] " + start + "    | [1] " + s.get(0) + "    |  [2] " + s.get(1) + "  | [3] " + s.get(2)
						+ "    |   [4] " + s.get(3) + "    | [5] " + s.get(4) + " | [6] " + s.get(5) + "      |\n"
						+ "+---------------+---------------+---------------+---------------+---------------+---------------+----------------+\n"
						+ "| [23] " + s.get(22)
						+ " |                                                                               | [7] "
						+ s.get(6) + "   |\n"
						+ "+---------------+                                                                               +----------------+\n"
						+ "| [22] " + s.get(21)
						+ "    |                                                                               | [8] "
						+ s.get(7) + "     |\n"
						+ "+---------------+                                                                               +----------------+\n"
						+ "| [21] " + s.get(20)
						+ "    |                                                                               | [9] "
						+ s.get(8) + " |\n"
						+ "+---------------+                                                                               +----------------+\n"
						+ "| [20] " + s.get(19)
						+ "   |                                                                               | [10] "
						+ s.get(9) + " |\n"
						+ "+---------------+                                                                               +----------------+\n"
						+ "| [19] " + s.get(18)
						+ "   |                                                                               | [11] "
						+ s.get(10) + "      |\n"
						+ "+---------------+---------------+---------------+---------------+---------------+---------------+----------------+\n"
						+ "| [18] " + s.get(17) + "   | [17] " + s.get(16) + "   | [16] " + s.get(15) + "|[15]"
						+ s.get(14) + "|  [14] " + s.get(13) + "  |  [13] " + s.get(12) + "  | [12] " + s.get(11)
						+ "    |\n"
						+ "+---------------+---------------+---------------+---------------+---------------+---------------+---------------+");

		System.out.println("이름\t잔액\t위치\t지역명");

		for (Player player : players) {

			if (player.getPosition() == 0) {

				temp = "\u001B[92m" + player.getName() + "\t" + player.getMoney() + "\t" + player.getPosition()
						+ "\t출발점" + "\u001B[0m";
				System.out.println(temp);
			} else {
				temp = player.getColor() + player.getName() + "\t" + player.getMoney() + "\t" + player.getPosition()
						+ "\t" + buildings.get(player.getPosition() - 1).getName() + "\u001B[0m";
				System.out.println(temp);
			}

		}

	}

	public static void game(ArrayList<Player> players) {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Controller control = new Controller();
		ArrayList<Building> buildings = control.Con_list();
		boolean isPlay = true;
		SoundController soundController = new SoundController();
		
		 Clip backgroundClip = soundController.playBackgroundMusic("C:/Users/a/OneDrive/바탕 화면/테마음악.wav");

		ArrayList<String> color = new ArrayList<String>();
		color.add("\u001B[31m");
		color.add("\u001B[32m");
		color.add("\u001B[33m");
		color.add("\u001B[34m");

		for (int i = 0; i < players.size(); i++) {
			players.get(i).setColor(color.get(i));
		}
		color = null;

		while (isPlay) {

			for (int i = 0; i < players.size(); i++) {

				int j = i;
				if (players.get(j).getIsland()) {
					System.out.println("\n===========================");
					System.out.println(players.get(j).getName() + "님은 무인도 입니다. 턴 넘어갑니다.");
					System.out.println("===========================");
					System.out.println("\n");
					players.get(j).setIsland(false);
					continue;
				}

//            print(); // 부루마블 판 보여주기
				print(players, buildings); // TODO : 플레이어 정보 출력 (이름, 잔액, 위치)

				// 주연진
//          TODO : 주사위 돌리고 결과 받아오기
				System.out.println("\n" + players.get(j).getName() + "님의 차례입니다. Enter를 눌러 주사위를 굴리세요.");
				soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/마이턴.wav");
				sc2.nextLine(); // Enter 키 대기

				int diceSum = 0; // 주사위의 총 합계
				int rollCount = 0; // 굴린 횟수

				// 주사위를 굴리는 동안 두 값이 동일하면 계속해서 추가로 굴리기 // 테스트 확인 아직 안해봄

				int[] diceResult = control.rollDice(); // 주사위 굴리기

				Dice dice = new Dice();

				dice.setResult1(diceResult[0]);
				dice.setResult2(diceResult[1]);

//			    dice.dicesample();

				dice.setVisible(true);
//
				dice.rollDice();

//				control.Con_dice();

				sc2.nextLine();

				diceSum += diceResult[0] + diceResult[1]; // 두개 합 계산
				System.out
						.println(players.get(j).getName() + "님이 " + diceResult[0] + "과 " + diceResult[1] + "이 나왔습니다.");

//               만약 같은숫자 두개면 한번더 고 

				// TODO : 이동하기
				int newPosition = (players.get(j).getPosition() + diceSum);
				players.get(j).setPosition(newPosition);
				System.out.println(players.get(j).getName() + "님이 총 " + diceSum + "만큼 이동했습니다!");

				if (diceResult[0] == diceResult[1]) {
					soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/주사위더블.wav");
					System.out.println(
							"\n           ************ 더블 주사위 등장 ! 한 판의 기회가 추가로 제공됩니다 ~! ****************         \r\n"
									+ "          ⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤       \r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣾⣿⣿⣿⣟⣿⣿⣿⣽⣷⣻⣷⣻⡾⠝⠓⠻⣯⣿⣞⣷⠟⠉⠉⠀⠀⠈⣹⡇⠀⠀⣿⡟⠀⠀⢠⡞⠀⠀⠉⠉⠙⠺⣿⣾⡿⠛⠋⠛⠛⢿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠛⠻⢯⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⣿⠀⠀⠀⠀⢿⣿⣿⠀⠀⠀⣠⣶⣦⣄⣿⡅⠀⠀⣟⠀⠀⣰⣿⡇⠀⠀⣠⣤⠀⠀⣼⡏⠀⢀⢠⣄⡀⠀⢘⣿⠀⠀⠀⠉⠉⠙⠛⣿⣿⣿⣷⠟⠛⢯⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⢸⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⠇⠀⣸⡆⠀⠘⣿⡟⠀⠀⢰⣿⣿⣿⣿⣿⡆⠀⠀⠏⢠⣶⣿⣿⡇⠀⠀⢿⡿⠀⠀⣿⠀⠀⣬⣿⣿⣷⠀⠈⣿⣷⣶⠀⠀⠀⣤⣄⣿⣿⣿⣧⠀⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣷⣶⣿⣿⣿⣿⣿⣿⡇⠀⠀⣿⡿⠀⠀⣻⣷⠀⠀⢿⡇⠀⠀⢸⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠹⣿⣿⡇⠀⠀⠀⠀⠀⢀⣽⠀⠀⣿⣿⣿⣿⠆⠀⠙⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣷⣶⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠟⠿⣿⣿⣿⣿⣿⣿⡇⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠈⢿⣿⣿⡿⣿⡇⠀⠀⣆⠀⠀⢻⣿⣗⠀⠀⢰⣴⣶⣿⣿⡄⠀⠀⢿⣿⡿⠀⠀⢰⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⠾⠻⢽⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀ ⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⡟⠀⢀⣰⣶⣶⣧⠀⠀⠘⣧⠀⠀⠀⠀⠀⠀⢹⡇⠀⠀⣿⣆⠀⠀⠹⡇⠀⠀⣸⣿⣿⣿⣿⣷⡀⡀⠀⠉⠁⠀⢠⣾⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣏⠀ ⠀⠀⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣶⣶⣿⣿⣿⣧⣤⣤⣶⣿⣧⣤⣭⣿⣿⣿⣿⣶⣦⣴⣿⣿⣶⣤⣤⣤⣾⣾⣷⣶⣤⣿⣿⣶⣶⣬⣼⣿⣿⣿⣿⣿⣿⣿⣾⣤⣤⣴⣿⣿⣿⣿⣦⣶⣶⣿⣿⣿⣿⣿⣿⣦⣶⣶⣿⣿⠀⠀⠀⠀⠀⠀⠀\r\n"

					);
					if (players.get(j).getPosition() == 12) {
						System.out.println("앗 당신은 무인도에 갇혔습니다 ! 잭팟의 기회가 날아갑니다 !!!");
						players.get(j).setIsland(true);
						continue;
					}
					i--;
				}

				// TODO : 위치계산하여 무인도이면 턴 종료하기
				if (players.get(j).getPosition() == 12) {
					System.out.println("무인도 도착해서 1턴 쉽니다 ");
					players.get(j).setIsland(true);
					continue;
				} else if (players.get(j).getPosition() == 0) {
					System.out.println("출발지역입니다. 할 수 있는 행동이 없습니다. ");
					continue;
				}

				/*
				 * TODO : buildings.get(위치)해서 불러온 다음 정보 출력하기 ex) ***님의 소유지입니다! 통행료 : 100 인수가격 :
				 * 200 ex2) 빈 땅입니다! 통행료 : 100 구매가 : 100
				 * 
				 */

				int pos = players.get(j).getPosition() - 1;
				String name = players.get(j).getName();
				String owner = buildings.get(pos).getOwner();
				int money = players.get(j).getMoney();
				int price = buildings.get(pos).getPrice();
				int fee = buildings.get(pos).getFee();
				int t_price = buildings.get(pos).getT_price();
				String build_name = buildings.get(pos).getName();

				System.out.println(name + "님의 현재 위치는 " + build_name + "입니다.");
				if (owner == null) { // 빈땅인 경우
					System.out.println("빈 땅입니다! ");
					System.out.println("구매가 : " + price + "\n통행료 : " + fee);

					System.out.print("구매하시겠습니까? [1]구매 [2]포기 : ");
					int choice = sc.nextInt();
					if (choice == 1) {
						if (players.get(j).getMoney() >= price) {
							soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/랜드마크.wav");

							// 소유자 정보 변경 // 구매완료 출력문 //돈 빼기
							players.get(j).setMoney(money - price);
							buildings.get(pos).setOwner(name);
							System.out.println(build_name + "의 소유자가 되셨습니다! 턴이 종료됩니다. ");
							System.out.println("\n" + name + "님의 보유머니는 " + (money - price) + "로 변동되었습니다.");

						} else {
							soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/경고.wav");
							System.out.println("돈이 부족합니다. 구매가 불가능합니다. 턴이 종료됩니다. ");
							continue;
						}

					} else if (choice == 2) {
						System.out.println("구매를 포기하셨습니다. 턴이 종료됩니다. ");
						continue;
					}

					// 빈땅인 경우 종료
				} else if (owner == name) { // 자신이 소유한 경우
					System.out.println(name + "님의 소유지입니다! 지나갑니다~");
					continue;
				} else { // 타인이 소유한 경우
					System.out.println(owner + "님의 소유지입니다! 통행료 (" + fee + "원) 을 지불합니다!");
					// 통행료 지불
					if (money >= fee) {
						players.get(j).setMoney(money - fee);
						for (Player p : players) {
							if (p.getName() == owner) {
								p.setMoney(p.getMoney() + fee);
							}
						}

						// 인수하기
						if (money >= t_price) {
							System.out.println("현재 금액 " + money);
							System.out.println("인수비용은" + t_price + "입니다. 인수하시겠습니까?");
							System.out.println("[1]인수 [2] 포기");
							int choice = sc.nextInt();
							if (choice == 1) {
								soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/랜드마크.wav");
								players.get(j).setMoney(money - t_price);
								for (Player p : players) {
									if (p.getName() == owner) {
										p.setMoney(p.getMoney() + t_price);
										buildings.get(pos).setOwner(name);
										System.out.println(build_name + "를 인수했습니다. 잔액은 " + (money - t_price));
										System.out.println("소유자 =" + name);
									}
								}

							} else if (choice == 2) {
								System.out.println("인수를 포기합니다. 턴을 종료합니다.");
								continue;
							}

						}

					} else { // 파산
						//게임 종료 시
		                backgroundClip.stop(); //배경음악종료 
		                soundController.playSoundEffect("C:/Users/a/OneDrive/바탕 화면/게임종료.wav");
						System.out.println("파산하셨습니다! 게임이 종료됩니다!");
						isPlay = false;
						players.get(j).setMoney(-9999);
						break;
					}

				}

			}

		} // while 문 종료

		for (Building b : buildings) //정산하기
	      {
	         for(Player p : players)
	         {   
	            if(p.getMoney()>0)
	            {
	               if(b.getOwner() == p.getName())
	               {
	                  p.setMoney(p.getMoney() + b.getPrice());
	               }
	            }
	         }
	      }
	      
	      players.sort(Comparator.comparingInt(Player::getMoney)); //잔액순으로 정렬하기
	      
	      for(int i=0; i<players.size(); i++)
	      {
	         if(i == 0)
	         {
	            players.get(i).setWinnum(0);
	         }else if(players.get(i).getMoney()>players.get(i-1).getMoney()){
	            players.get(i).setWinnum(i);
	         }else {
	            players.get(i).setWinnum(players.get(i-1).getWinnum());
	         }      
	         
	      }
	      int lank = 1;
	      
	      System.out.println("==============결과================");
	      System.out.println("등수\t이름\t금액");
	      for(int i=players.size()-1; i>=0; i--)
	      {    
	         control.conPlayUpdate(players.get(i));
	         if(i==0)
	         {
	            lank = players.size();
	            System.out.println(lank + "등\t" + players.get(i).getName() + "\t파산");
	            break;
	         }
	         else if(players.get(i).getWinnum()<players.get(i-1).getWinnum())
	         {
	               lank++;
	               
	         }
	         System.out.println(lank + "등\t" + players.get(i).getName() + "\t" 
	                          + players.get(i).getMoney());
	      }
	      
	      System.out.println("=============게임종료=============");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Controller control = new Controller();
		SoundController soundController = new SoundController();

		while (true) {
			System.out.print("\n메뉴 선택 [1]게임실행 [2]랭킹확인 [3]회원가입 [4]회원탈퇴 [5]종료 >> ");
			int input = sc.nextInt();

			if (input == 1) {

				System.out.print("플레이 인원 수 선택 : ");
				int input2 = sc.nextInt();
				boolean check = true;
				if (input2 > 1 && input2 <= 4) {
					ArrayList<Player> players = new ArrayList<Player>();
					for (int i = 0; i < input2; i++) {
						System.out.println("\n" + (i + 1) + "번째 플레이어에 로그인하세요 !");
						System.out.print("ID : ");
						String id = sc.next();
						System.out.print("PW : ");
						String pw = sc.next();

						players.add(control.Con_login(id, pw));

						if (players.get(i) != null) {
							System.out.println(players.get(i).getName() + "회원님 로그인 성공!");
						} else {
							System.out.println(id + "회원님 로그인 실패 ㅠㅠ");
							check = false;
							break;
						}
					}
					if (!check)
						continue;
					System.out.println("\n" + "     ⢀⣠⣤⣴⣶⣶⣤⣤⡀    ⠀⠀ ⣠⣤⣤⣤⣤⠀⠀ ⠀⠀⠀⣤⣤⣤⣤⣤⠀⠀ ⣠⣤⣤⣤⣤⣤⠀⠀⢠⣤⣤⣤⣤⣤⣤⣤⣤⣤⠀⠀⣾⣿⣿\r\n"
							+ "⠀ ⡀⢀⣴⣿⣿⣿⠿⠿⠿⢿⣿⡿⠃⠀ ⠀⠀⣰⣿⣿⣿⣿⣿⡇    ⢰⣿⣿⣿⣿⣿⡇⠀⢠⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⣿⣿⣿⣿⠿⠿⠿⠏⠀⠀⣾⣿⣿⡏⠀\r\n"
							+ "⡀⢠⣿⣿⣿⠏⠀⠀⠀⠀  ⠀⠀⠀   ⣼⣿⣿⡟⢹⣿⣿⣿⡏  ⡀⣼⣿⣿⣿⣿⣿⡇⢀⣿⣿⡏⣿⣿⣿⠁⠀⢠⣿⣿⣿⣇⣀⣀⣀⣀⠀⠀⠀⠀⣿⣿⣿⠁⠀⠀\r\n"
							+ "⢀⣿⣿⣿⡟⠀⠀⣼⣿⣿⣿⣿⣿⠀⠀ ⣼⣿⣿⡟⠀⢸⣿⣿⣿⡅  ⢀⣿⣿⡟⣿⣿⣿⡇⣼⣿⡿⢸⣿⣿⡏⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⢸⣿⣿⡏⠀⠀⠀\r\n"
							+ "⠘⣿⣿⣿⣧⠀⠀⠛⠛⢻⣿⣿⡏⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡗  ⢸⣿⣿⠇⢻⣿⣿⣿⣿⡿⠁⣾⣿⣿⠃⠀⠀⣿⣿⣿⡏⠉⠉⠉⠉⠁⠀⠀⠀⠸⠟⠛⠁⠀⠀⠀\r\n"
							+ "⠀⠹⣿⣿⣿⣷⣤⣤⣴⣾⣿⣿⠃⢀⣾⣿⣿⣏⠍⠭⠟⠋⢻⣿⣿⣷⠶⠂⣿⣿⣿⠀⢸⣿⣿⣿⣿⠃⢨⣿⣿⣿⠀⠀⢸⣿⣿⣿⣷⣶⣶⣶⣶⣶⡆⠀⢰⣶⣶⣶⠀⠀⠀\r\n"
							+ " ⠀⠙⠙⠛⠿⠿⠿⠿⠛⠋⠁⠀⠚⠛⠛⠋⠀      ⠘⠛⠛⠛⠀⠐⠛⠛⠋⠀⠘⠛⠛⠛⠃⠀⠘⠛⠛⠃⠀⠀⠚⠛⠛⠛⠛⠛⠛⠛⠛⠛⠀⠀⠚⠛⠛⠋⠀⠀⠀⠀\r\n");

					
					game(players);

				} else {
					System.out.println("인원수를 다시 선택해주세요. 2 ~ 4명이 플레이 할 수 있는 게임입니다.");
				}

			} else if (input == 2) { // 랭킹확인
				System.out.println("랭킹확인");

				System.out.println("ID\t이름\t승점\t레벨");

				ArrayList<MemberDTO> result = control.Con_lank();

				for (MemberDTO r : result) {
					System.out.println(r.getId() + "\t" + r.getName() + "\t" + r.getWinnum() + "\t" + r.getLv());
				}

			} else if (input == 3) {
				System.out.println("==회원가입==");

				System.out.print("ID : ");
				String id = sc.next();
				System.out.print("PW : ");
				String pw = sc.next();
				System.out.print("이름 : ");
				String name = sc.next();

				// controller에 필요한 정보를 담아서,
				// Model이 로직을 수행하게끔
				int result = control.Con_join(id, pw, name);

				// return된 result의 값으로 성공 여부를 확인
				if (result > 0) {
					System.out.println("회원가입 성공!");
				} else {
					System.out.println("회원가입 실패ㅠ");
				}
			} else if (input == 4) {
				// 회원탈퇴
	            System.out.print("ID : ");
	            String id = sc.next();
	            System.out.print("PW : ");
	            String pw = sc.next();
	            
	            Player p = control.Con_login(id, pw);
	            
	            if (p != null) {
	               
	               System.out.print("정말 회원탈퇴하시겠습니까? [Y] or [N] >> ");
	               String s = sc.next(); 
	               if(s.equals("Y"))
	               {
	                  int result = control.Con_delete(id, pw);

	                  if (result > 0) {
	                     System.out.println("회원탈퇴 성공!");
	                  } else {
	                     System.out.println("회원탈퇴 실패ㅠ");
	                  }
	               }else {
	                  System.out.println("회원탈퇴를 취소하셨습니다");
	                  continue;
	               }
	            } else {
	               System.out.println("로그인에 실패하였습니다.");
	            }
			} else if (input == 5) {
				break;
			}

		}

	}

}
