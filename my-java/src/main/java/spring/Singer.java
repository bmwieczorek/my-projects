package spring;

public class Singer implements Performer {

    private String song = "(no)";

    public void setSong(String song) {
        this.song = song;
    }

    /*
     * public Singer(String song) { this.song = song; }
     */

    @Override
    public void perform() {
        System.out.println("Singer singing " + song + " song!");
    }

}
