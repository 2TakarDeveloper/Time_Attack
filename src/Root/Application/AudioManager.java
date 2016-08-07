package Root.Application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class AudioManager {
    public static MediaPlayer mediaPlayer;

    //audio settings
    public static boolean BGM=true;
    public static boolean SFX=true;
    public static double volume=1;

    public static void updateVolume(){
        mediaPlayer.setVolume(volume);
    }

    private static String GetFilepPath(){
        return "file:///" +
                System.getProperty("user.dir").replace("\\","/").replace(" ","%20")+
                "/Resources/";
    }

    public static  void buttonAudio(){
        if(SFX){
            try{
                Media audioClip = new Media(GetFilepPath()+"AudioClip/Button01.wav");

                MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    public static void LoadingScreenAudio(){
        try{
            Media audioClip = new Media(GetFilepPath()+"AudioClip/LoadingScreen.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void TitleScreenAudio(){
        try{
            Media audioClip = new Media(GetFilepPath()+"/AudioClip/TitleScreenAudio.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void TitleKeyPress(){
        try{
            Media audioClip = new Media(GetFilepPath()+"AudioClip/TitleClickSound.mp3");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void GameBGM(){
        if(BGM){
            try{
                Media audioClip = new Media(GetFilepPath()+"AudioClip/GameBGM.mp3");
                mediaPlayer= new MediaPlayer(audioClip);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(volume);
                mediaPlayer.play();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public static void MainMenuAudio(){
        if(BGM){
            try{
                Media audioClip = new Media(GetFilepPath()+"AudioClip/MainMenu.mp3");

                mediaPlayer= new MediaPlayer(audioClip);
                mediaPlayer.setVolume(volume);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

                mediaPlayer.play();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
    public static void CoinAudio(){
        if(SFX){
            try{
                Media audioClip = new Media(GetFilepPath()+"AudioClip/Coin.mp3");



                MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
                mediaPlayer.setVolume(volume*0.2);
                mediaPlayer.play();
            }catch (Exception e){e.printStackTrace();}
        }


    }

    public static void gameOverMusic(){
        try{
            Media audioClip = new Media(GetFilepPath()+"AudioClip/GameOver.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
