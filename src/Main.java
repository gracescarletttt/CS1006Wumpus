import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        //creating variables
        Simulation s = new Simulation();
        Player me = new Player();
        Wumpus wumpus = new Wumpus();

        //adding to simulation (could make this more efficient within simulation class?)
        s.setPlayer(me.getY(), me.getX());
        wumpus.moveToRandom(me.getY(), me.getX());
        s.setWumpus(wumpus.getY(), wumpus.getX());
        s.setBats(2, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());//for each of these sets, pass in coordinates of wumpus and player to make sure these aren't placed on their co-ordinates
        s.setExit(1, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());
        s.setHoles(4, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());
        s.setTreasure(1, me.getY(), me.getX(), wumpus.getY(), wumpus.getX());

        //display title and initial instructions
        System.out.println("""
            __                __       __   __                                                        
            |  |--.--.--.-----|  |_    |  |_|  |--.-----.   .--.--.--.--.--.--------.-----.--.--.-----.
            |     |  |  |     |   _|   |   _|     |  -__|   |  |  |  |  |  |        |  _  |  |  |__ --|
            |__|__|_____|__|__|____|   |____|__|__|_____|   |________|_____|__|__|__|   __|_____|_____|
                                                                                    |__|                                                                                                                                                                                                   
                """);

        System.out.println("*********************************************************************************************");
        System.out.println("Try to find your way through the cave system to collect the treasure and make it to the exit.");
        System.out.println("Within the cave system there are: a number of bottomless pits, a number of Wumpuses, a number of Superbats, one treasure chest and one exit.");
        System.out.println("- A Wumpus is very smelly. If you are in an adjacent cave to a Wumpus, you will smell it.");
        System.out.println("- If you are in an adjacent cave to a pit, you will feel a breeze.");
        System.out.println("- If you are in an adjacent cave to the treasure, you will see the treasure glittering.");
        System.out.println("- If you are picked up by a Superbat, you will be moved to a random position in the cave system.");
        System.out.println("On each move you can choose to move (m) or shoot (s). Use the wsad keys to choose the direction of your movement or shot.");


        //display initial board and position
        s.displayBoard();
        System.out.println(me.getY() + " Y and X " + me.getX());

        //scanner to read user input
        Scanner reader = new Scanner(System.in);

        //game loop - runs as long as player is alive and the simulation is still running
        while(me.getStatus() && s.getRunning()){
            String command = reader.nextLine();

            //moving
            int yPosBeforeMove = me.getY();
            int xPosBeforeMove = me.getX();
            me.move(command);

            //checking for hazards
            me.checkPosition(s.getCaves(),s);

            //checking for percepts
            me.checkNeighbours(s.getCaves());

            //setting route
            s.removePlayer(yPosBeforeMove, xPosBeforeMove);
            s.setRoute(yPosBeforeMove, xPosBeforeMove);
            s.setPlayer(me.getY(), me.getX());

            //display board and position
            s.displayBoard();
            System.out.println(me.getY() + " Y and X " + me.getX());
            
        }
        reader.close();

    }
}
