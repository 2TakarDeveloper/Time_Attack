package Root.Settings;


public class Sound {
    private static boolean canCreateInstance = true;
    private double MaxSound;
    private Boolean SFX=false;
    private Boolean BGM=false;

    public Sound(){

    }

    public Sound(double maxSound,Boolean SFX,Boolean BGM){
        this.setMaxSound(maxSound);
        this.setSFX(SFX);
        this.setBGM(BGM);
    }

    public double getMaxSound() {
        return MaxSound;
    }

    public void setMaxSound(double maxSound) {
        MaxSound = maxSound;
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
