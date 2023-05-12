import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener{
    private static final int WIDTH=600;
    private static final int HEIGHT=600;
    private static final int UNIT_SIZE=20;
    private static final int GAME_UNITS=(WIDTH*HEIGHT)/UNIT_SIZE;
    private static final int DELAY=75;

    private final int[]x=new int[GAME_UNITS];
    private final int[]Y=new int[GAME_UNITS];
    private int bodyParts=6;
    private int appleX;
    private int appleY;
    private char direction='R';
    private boolean running=false;
    private Timer timer;

    public GamePanel(){
        setPreferredSize(new Dimention(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        addKeyListener(new GameKeyListener());
    }
    public void startGame(){
        newApple();
        running=true;
        timer=new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    private void draw(Graphics g){
        if(running){
            g.setColor(Color.RED);
            g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE));

            for(int i=0;i<bodyParts;i++){
                if(i==0){
                    g.setColor(Color.GREEN);
                }else{
                    g.setColor(new Color(45,180,0));
                }
                g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Int Free",Font.BOLD,40));
            FontMetrics metrics=getFontMetrics(g.getFont());
            g.drawString("Score: "+(bodyParts-6),(WIDTH-metrics.stringWidth("Score: "+(bodyParts-6)))/2,g.getFont().getSizer());
        }else{
            gameOver(g);
        }
    }
    private void newApple(){
        appleX=(int)(Math.random()*(WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY=(int)(Math.random()*(WIDTH/UNIT_SIZE))*UNIT_SIZE;
    }

    private void move(){
        for(int i=bodyParts;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        switch (direction){
            case'U':y[0]=y[0]-UNIT_SIZE;
            break;
            case'D':y[0]=y[0]+UNIT_SIZE;
            break;
            case'L':x[0]=x[0]-UNIT_SIZE;
            break;
            default:System.out.println("Wrong choice");
        }
    }
}
