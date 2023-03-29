public class Main{
    public static void main(String[] args){
        //Main obj = new Main();
        Simulation s = new Simulation();
        s.setBats(2);
        s.setExit(1);
        s.setHoles(3);
        s.setWumpus(5);
        s.setTreasure(4);
        s.displayBoard();
    }
}