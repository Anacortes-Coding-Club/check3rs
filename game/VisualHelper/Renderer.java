
import java.awt.Color;

import game.Stone;
import game.VisualInterface;

public class Renderer implements VisualInterface
{
    
    private static World habitat;
    private static Turtle mainTurtl;
  
    private static int screenW = 900;
    private static int screenH = 600;

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
        
        //Update Screen
        habitat.turtleMoved();
    }
}