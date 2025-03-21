package Model;

public class SoundDTO {
    private String filePath;

    // 생성자 추가: filePath를 매개변수로 받는 생성자
    public SoundDTO(String filePath) {
        this.filePath = filePath;
    }

    // getter 메서드 추가: filePath 반환
    public String getFilePath() {
        return filePath;
    }

    // setter 메서드 추가: filePath 설정
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
