import java.util.Scanner;
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
        Player me = new Player();
        s.setPlayer(me.getX(), me.getY());
        while(me.getStatus()){
            Scanner reader = new Scanner(System.in);
            String command = reader.nextLine();
            me.move(command);
            s.setPlayer(me.getY(), me.getX());
            s.displayBoard();
            
        }
        

    }
}