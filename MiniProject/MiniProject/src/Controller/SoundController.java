package Controller;

import Model.SoundDAO;
import Model.SoundDTO;
import javax.sound.sampled.Clip;

public class SoundController {

    private final SoundDAO soundDAO;

    public SoundController() {
        this.soundDAO = new SoundDAO();
    }

    // 배경 음악 재생 (무한 반복) - Clip 객체 반환
    public Clip playBackgroundMusic(String filePath) {
        // DTO 객체 생성
        SoundDTO soundDTO = new SoundDTO(filePath);

        // DAO를 통해 배경음악 재생 (Clip 반환)
        return soundDAO.playBackgroundMusic(soundDTO);
    }

    // 효과음 재생 (한 번만 재생)
    public void playSoundEffect(String filePath) {
        // DTO 객체 생성
        SoundDTO soundDTO = new SoundDTO(filePath);

        // DAO를 통해 효과음 재생
        soundDAO.playSoundEffect(soundDTO);
    }
}
