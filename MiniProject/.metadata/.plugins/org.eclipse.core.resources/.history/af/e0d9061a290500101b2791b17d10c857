package Model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class dice extends JFrame {
	private JLabel dice1Label, dice2Label;
	private Timer timer;
	private int rollCount = 0;
	private int dice1Result, dice2Result;

	public dice() {
        setTitle("Dice Roller");
        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 주사위 1, 2를 표시할 라벨
        dice1Label = new JLabel(new ImageIcon("dice1.png"));
        dice2Label = new JLabel(new ImageIcon("dice1.png"));

        JPanel dicePanel = new JPanel();
        dicePanel.add(dice1Label);
        dicePanel.add(dice2Label);
        add(dicePanel, BorderLayout.CENTER);

        // 시작 버튼을 눌러서 주사위를 굴림
        JButton rollButton = new JButton("Roll Dice");
        rollButton.addActionListener(e -> rollDice());
        add(rollButton, BorderLayout.SOUTH);
    }

	public void rollDice() {
		// 주사위 애니메이션을 위한 타이머 설정
		rollCount = 0; // 애니메이션을 반복할 횟수 초기화

		// 타이머를 50ms 간격으로 설정해서 주사위가 굴러가는 애니메이션 효과를 줌
		timer = new Timer(50, e -> {
			// 매 타이머마다 주사위 두 개를 랜덤하게 변화시킴
			Random ran = new Random();
			int dice1 = ran.nextInt(6) + 1;
			int dice2 = ran.nextInt(6) + 1;

			// 주사위 이미지 변경
			dice1Label.setIcon(new ImageIcon("dice" + dice1 + ".png"));
			dice2Label.setIcon(new ImageIcon("dice" + dice2 + ".png"));

			rollCount++;

			// 20번 반복 후, 주사위 결과 출력
			if (rollCount >= 20) {
				timer.stop(); // 타이머 멈춤
				dice1Result = ran.nextInt(6) + 1; // 실제 결과
				dice2Result = ran.nextInt(6) + 1;

				// 애니메이션이 끝난 후 결과 출력
				JOptionPane.showMessageDialog(this,
						"Final Result:\nDice 1: " + dice1Result + "\nDice 2: " + dice2Result);
				
				dice1Label.setIcon(new ImageIcon("dice" + dice1Result + ".png"));
				dice2Label.setIcon(new ImageIcon("dice" + dice2Result + ".png"));
				
			}
		});

		timer.start(); // 타이머 시작
	}

	public static void dicesample() {
		System.out.println("주사위를 굴려주세요 !");
		SwingUtilities.invokeLater(() -> {
			dice frame = new dice();
			frame.setVisible(true);
		});
	}

}
