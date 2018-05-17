import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
public class hanshu
{
    public static void main(String[] args)
    {
        DrawFrame frame=new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class DrawFrame extends JFrame
{
    public DrawFrame()
    {
        setTitle("DrawTest");
        setSize(400,400);
        // add panel to frame
        DrawPanel panel = new DrawPanel();
        add(panel);
    }
}
class DrawPanel extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        int centerx=0;
        int centery=0;
        int minx=-150;
        int maxx=150;
        int miny=-150;
        int maxy=150;

        g2.translate(200,200);

        g2.draw(new Line2D.Double(minx,centery,maxx,centery));
        g2.draw(new Line2D.Double(maxx,centery,maxx-1,centery-1));
        g2.draw(new Line2D.Double(maxx,centery,maxx-1,centery+1));
        g2.drawString("X",151,0);

        g2.draw(new Line2D.Double(centerx,miny,centerx,maxy));
        g2.draw(new Line2D.Double(centerx,miny,centerx-1,miny+1));
        g2.draw(new Line2D.Double(centerx,miny,centerx+1,miny+1));
        g2.drawString("Y",-7,-145);

        int[] arrayy=new int[200];
        int[] arrayx=new int[200];
        arrayx[0]=-100;
        for (int i=0;i<199;i++)
        {
            arrayx[i+1]=arrayx[0]+i;
        }
        for (int i=0;i<200;i++)
        {
            arrayy[i]=-Function(arrayx[i])/10;
        }

        g2.drawPolyline(arrayx,arrayy,200);

    }
    public static int Function(int x)
    {
        return x*x;
    }
}