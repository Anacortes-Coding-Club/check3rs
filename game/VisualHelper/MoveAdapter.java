import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class MoveAdapter extends ComponentAdapter
{
    private int x;
    private int y;

    public void componentMoved(ComponentEvent e)
    {
       x = e.getComponent().getX();
       y = e.getComponent().getY();
       //System.out.println("x: " + x);
       //System.out.println("y: " + y);
    }//from   w  ww. j  av a2 s .  c  o  m

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
 }
