package Model;

import javax.sound.sampled.*;
import java.io.File;

public class SoundDAO {

    // 배경 음악 재생 (무한 반복) - Clip 객체 반환
    public Clip playBackgroundMusic(SoundDTO soundDTO) {
        Clip clip = null;
        try {
            // 오디오 파일 경로 가져오기
            File audioFile = new File(soundDTO.getFilePath());

            // 파일이 존재하는지 확인
            if (!audioFile.exists()) {
                System.out.println("파일이 존재하지 않습니다: " + soundDTO.getFilePath());
                return null;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);  // 배경음악은 무한 반복
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;  // Clip 객체 반환
    }

    // 효과음 재생 (한 번만 재생)
    public void playSoundEffect(SoundDTO soundDTO) {
        new Thread(() -> {
            try {
                // 오디오 파일 경로 가져오기
                File audioFile = new File(soundDTO.getFilePath());

                // 파일이 존재하는지 확인
                if (!audioFile.exists()) {
                    System.out.println("파일이 존재하지 않습니다: " + soundDTO.getFilePath());
                    return;
                }

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                clip.start();  // 효과음은 한 번만 재생

                // 효과음 재생이 끝날 때까지 대기
                Thread.sleep(clip.getMicrosecondLength() / 1000);  // 마이크로초를 밀리초로 변환
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
