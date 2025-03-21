package View;

import Controller.SoundController;
import javax.sound.sampled.Clip;
import java.util.Scanner;

public class SoundMain {
    public static void main(String[] args) throws InterruptedException {
        // SoundController 객체 생성
        SoundController soundController = new SoundController();

        // 배경음악 실행 (무한 반복)
        Clip backgroundClip = soundController.playBackgroundMusic("C:/Users/smhrd/OneDrive/바탕 화면/테마음악.wav");


                soundController.playSoundEffect("C:/Users/smhrd/OneDrive/바탕 화면/마이턴.wav");

                soundController.playSoundEffect("C:/Users/smhrd/OneDrive/바탕 화면/랜드마크.wav");

                soundController.playSoundEffect("C:/Users/smhrd/OneDrive/바탕 화면/주사위더블.wav");
                
                soundController.playSoundEffect("C:/Users/smhrd/OneDrive/바탕 화면/경고.wav");
                
                //게임 종료 시
                backgroundClip.stop(); //배경음악종료 
                soundController.playSoundEffect("C:/Users/smhrd/OneDrive/바탕 화면/게임종료.wav");
            
        }


  
}
