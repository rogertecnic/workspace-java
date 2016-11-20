//package painting;

import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

public class SwingPaintDemo3 {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    } 
}

class MyPanel extends JPanel {
    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    private int realx, realy;
    
    public MyPanel() {
    	
        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
        	public void mouseMoved(MouseEvent e){
        		 moveMouse(e.getX(),e.getY());
        	}
		});
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
                moveMouse(e.getX(),e.getY());
            }
            
        });
        
    }
    
    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
        	// repaint old position of square
        	// executado ao final de todo codigo do frame
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
			repaint(x,y,squareW+OFFSET,squareH+OFFSET);// renderiza denovo onde o mouse esta
        }
    }
    
    private void moveMouse(int x, int y) {
    	realx = x;
    	realy = y;
        repaint(9,9,280,24); // renderiza denovo a area do texto
    }
    
    /**
     * usado pelo metodo pack() para setar as dimensoes da janela
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    /**
     * Metodo que pinta o componente inteiro. no nosso caso o JPanel e tambem escreve
     * a string na posicao 10,20,
     * desenha um quadrado vermelho com borda preta na posicao (squareX,squareY, comprimento, largura);
     */
    @Override
    protected void paintComponent(Graphics g) {
              
        g.drawString("This is my custom Panel! square x,y: ("+squareX+","+squareY+")",10,20);
        g.drawString("mouse position: ("+realx+","+realy+")",10,30);
        g.setColor(Color.RED);
        g.fillRect(squareX,squareY,squareW,squareH);
        g.setColor(Color.BLACK);
        g.drawRect(squareX,squareY,squareW,squareH);
        //g.drawOval(squareX-15, squareY-15, 30, 30);
        super.paintComponent(g); 
    }  
}