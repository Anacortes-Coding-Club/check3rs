import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.geom.Point2D;

import javax.imageio.ImageIO;

public class Main
{

    private static World habitat;
    private static Turtle mainTurtl;

    private static Long milliTimeDelta;
  
    private static int screenW = 900;
    private static int screenH = 600;

    private static int[][] map = null;
    private static Color[][] stoneTexture = null;
    private static Color[][] mossTexture = null;

    private static Point mouseDelta;
  
    public static void main(String[] args)
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

        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        cursorImg, new Point(0, 0), "blank cursor");

        try
        {
            BufferedImage image = ImageIO.read(new File("mapV3.png"));
            map = ImageTo2DArray.convertTo2DUsingGetRGB(image);

            image = ImageIO.read(new File("brickWall.png"));
            int[][] buff = ImageTo2DArray.convertTo2DUsingGetRGB(image);
            stoneTexture = new Color[buff.length][buff[0].length];

            for (int i = 0; i < buff.length; i++)
            {
                for (int j = 0; j < buff[i].length; j++)
                {
                    stoneTexture[i][j] = new Color(buff[i][j]);
                }
            }

            image = ImageIO.read(new File("darkWall.png"));
            buff = ImageTo2DArray.convertTo2DUsingGetRGB(image);
            mossTexture = new Color[buff.length][buff[0].length];

            for (int i = 0; i < buff.length; i++)
            {
                for (int j = 0; j < buff[i].length; j++)
                {
                    mossTexture[i][j] = new Color(buff[i][j]);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("image error");
        }

        //Player player1 = new Player(map.length / 2, map.length / 2, 90, habitat);

        //init done

        
        System.out.println("starting game loop");

        boolean toggleMouse = true;
        boolean previous = false;

        while(true)
        {
            habitat.erase();
            milliTimeDelta = System.currentTimeMillis();


            if(toggleMouse)
            {
                Point mouse = habitat.getMousePosition();

                if(mouse != null)
                    mouseDelta = new Point((int)mouse.getX() - screenW/ 2, (int)mouse.getY() - screenH/ 2);
                else
                    mouseDelta = new Point();

                moveMouse(new Point(worldPos.getX() + screenW/ 2, worldPos.getY() + screenH / 2));
                // Set the blank cursor to the JFrame.
                habitat.getContentPane().setCursor(blankCursor);
            }
            else
            {
                mouseDelta = new Point();
                // Set the blank cursor to the JFrame.
                 habitat.getContentPane().setCursor(Cursor.getDefaultCursor());
            }

            if(habitat.getKeyPressed(KeyEvent.VK_ESCAPE))
            {
                if(!previous)
                {
                    toggleMouse = !toggleMouse;
                    System.out.println("toggled");
                    previous = true;
                }
            }
            else
            {
                previous = false;
            }

            // try
            // {
            //     player1.Update();
            // } 
            // catch (InterruptedException e)
            // {
            //     System.out.println("player failed to raycast");
            //     e.printStackTrace();
            // }

            //System.out.println(isOnBlock(new Point2D.Float(0,0)));
            habitat.turtleMoved();

            milliTimeDelta = milliTimeDelta - System.currentTimeMillis();
            //System.out.println(Math.round(1 / (0.001 * (10 - milliTimeDelta))));

            try
            {
                Thread.sleep(Math.max(30 + milliTimeDelta, 0));
            } 
            catch(InterruptedException e) 
            { 
                System.out.println("sleep error");
            }
        }


    }

    public static Color getTextureAtCord(Point2D.Float p, int index)
    {
        if(new Color(index).getRed() == 100)
            return mossTexture[(int)(p.y)][(int)(p.x)];

        return stoneTexture[(int)(p.y)][(int)(p.x)];
    }

    public static Point getMouseDelta()
    {
        return mouseDelta;
    }
    
    public static int getBlockValue(Point2D.Float pos)
    {
        if(pos.x < map[0].length -1 && pos.y < map.length - 1 && pos.x > 0 && pos.y > 0)
            return map[Math.round(pos.x)][Math.round(pos.y)];
        return 0;
    }

    public static boolean isOnBlock(Point2D.Float pos)
    {
        if(pos.x < map[0].length -1 && pos.y < map.length - 1 && pos.x > 0 && pos.y > 0)
            return (map[Math.round(pos.x)][Math.round(pos.y)] != -1);
        return false;
    }

    public static Point getScreenSize()
    {
        return new Point(screenW, screenH);
    }

    private static void moveMouse(Point p)
    {
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
    
        // Search the devices for the one that draws the specified point.
        for (GraphicsDevice device: gs) { 
            GraphicsConfiguration[] configurations =
                device.getConfigurations();
            for (GraphicsConfiguration config: configurations) {
                Rectangle bounds = config.getBounds();
                if(bounds.contains(p)) {
                    // Set point to screen coordinates.
                    Point b = bounds.getLocation(); 
                    Point s = new Point(p.x - b.x, p.y - b.y);
    
                    try {
                        Robot r = new Robot(device);
                        r.mouseMove(s.x, s.y);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
    
                    return;
                }
            }
        }
        // Couldn't move to the point, it may be off screen.
        return;
    }
}
