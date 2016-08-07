package Root.CustomContol;


public class ScoreBoard {
    private String name;
    private String score;
    private String lvlReached;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLvlReached() {
        return lvlReached;
    }

    public void setLvlReached(String lvlReached) {
        this.lvlReached = lvlReached;
    }

    public ScoreBoard(String name, String score, String lvlReached) {
        this.name = name;
        this.score = score;
        this.lvlReached = lvlReached;
    }
}
