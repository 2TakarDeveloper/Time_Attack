package Root.Settings;


public class Sound {
    private static boolean canCreateInstance = true;
    private double volume;
    private Boolean SFX=false;
    private Boolean BGM=false;

    public Sound(){

    }

    public Sound(double volume, Boolean SFX, Boolean BGM){
        this.setVolume(volume);
        this.setSFX(SFX);
        this.setBGM(BGM);
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Boolean getSFX() {
        return SFX;
    }

    public void setSFX(Boolean SFX) {
        this.SFX = SFX;
    }

    public Boolean getBGM() {
        return BGM;
    }

    public void setBGM(Boolean BGM) {
        this.BGM = BGM;
    }
}
