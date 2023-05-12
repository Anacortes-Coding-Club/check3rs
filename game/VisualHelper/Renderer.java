
import java.awt.Color;
import java.awt.Dimension;

import game.Stone;
import game.VisualInterface;

public class Renderer implements VisualInterface
{
    
    private World habitat;
    private Turtle mainTurtl;
  
    private int screenW = 900;
    private int screenH = 600;

    public Renderer()
    {
        habitat = new World(screenW, screenH, Color.BLACK);
        habitat.setResizable(false);
        habitat.setDefaultCloseOperation(World.EXIT_ON_CLOSE);

        MoveAdapter worldPos = new MoveAdapter();

        habitat.addComponentListener(worldPos);

        habitat.setVisible(true);

        mainTurtl = new Turtle(habitat);
        mainTurtl.setPenWidth(5);
        mainTurtl.turnLeft(90);
        mainTurtl.setColor(new Color(0,0,0));


    }

    @Override
    public void updateVisuals(Stone[][] gameBoard)
    {
        
        
        //draw board
        for(int row = 0; row < gameBoard.length; row++)
        {
            for(int col = 0; col < gameBoard[row].length; col++)
            {
                mainTurtl.goTo((row * 32) - gameBoard.length / 2, (col * 32) - gameBoard[row].length / 2);
                mainTurtl.drop("../Sprites/frame.png",32);
            }
        }

        //Update Screen
        habitat.turtleMoved();
    }
}