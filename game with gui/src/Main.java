import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args){

        //setting variables
        Simulation s = new Simulation();
        Player me = s.getMe();
        Wumpus wumpus = s.getWumpus();

        //frame setup
        JFrame frame = new JFrame("Hunt the Wumpus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500,500));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        //header setup
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        //title setup
        JLabel title = new JLabel("Hunt the Wumpus!");
        title.setFont(new Font(null, Font.BOLD, 40));
        headerPanel.add(title);

        //instructions setup
        JTextArea textArea = new JTextArea();
        try (BufferedReader br = new BufferedReader(new FileReader("Instructions.txt"))) {
            String line = br.readLine();
            while (line != null) {
                textArea.append(line);
                textArea.append("\n");
                line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        textArea.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(textArea);

        //cave system setup
        Dimension caveSize = new Dimension(20,20);
        GridLayout caveGrid = new GridLayout(20,20);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(caveGrid);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 20));


        //initialise cave grid display
        for (Cave[] caves : s.getCaves()) {
            for (Cave cave: caves) {
                cave.setPreferredSize(caveSize);
                gridPanel.add(cave);
            }
        }

        contentPanel.add(row);
        contentPanel.add(gridPanel);
        frame.add(headerPanel);
        frame.add(contentPanel);

        frame.setVisible(true);

        //display initial board and position
        s.displayBoard();
        System.out.println(me.getY() + " Y and X " + me.getX());
        System.out.println("You have "+me.getArrows()+" arrows left");

        //scanner to read user input
        Scanner reader = new Scanner(System.in);

        //game loop - runs as long as player is alive and the simulation is still running
        while(me.getStatus() && s.getRunning()){
            System.out.println("Press [m] to move or [s] to shoot: ");
            String command = reader.nextLine();

            if (command.toLowerCase().equals("m")) {
                String direction = reader.nextLine();
                //moving
                int yPosBeforeMove = me.getY();
                int xPosBeforeMove = me.getX();
                me.move(direction);

                //setting route
                s.removePlayer(yPosBeforeMove, xPosBeforeMove);
                s.setRoute(yPosBeforeMove, xPosBeforeMove);
                s.setPlayer(me.getY(), me.getX());
            } else if (command.toLowerCase().equals("s")) {
                String direction = reader.nextLine();
                me.shoot(s.getCaves(), wumpus, direction,s);
            } else {
                System.out.println("Please choose a valid action option!");
            }

            //checking for hazards
            me.checkPosition(s.getCaves(),s);

            //checking for percepts
            me.checkNeighbours(s.getCaves());

            //display board and position
            s.displayBoard();
            System.out.println(me.getY() + " Y and X " + me.getX());
            System.out.println("You have "+me.getArrows()+" arrows left");   
        }
        reader.close();

    }
}
