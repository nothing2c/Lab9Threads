//Ball without threads

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.*;

public class BounceFrame extends JFrame {

    private JPanel canvas;
    private JButton startButton;
    private JButton stopFirstButton;
    private JButton stopLastButton;
    private JButton stopAllButton;
    private JButton closeButton;
    
    public BounceFrame() {
        setSize(500, 200);
        setTitle("Bounce");
        canvas = new JPanel();
        add(canvas, "Center");

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start");
        closeButton = new JButton("Close");
        stopFirstButton = new JButton("Stop First");
        stopLastButton = new JButton("Stop Last");
        stopAllButton = new JButton("Stop All");

        buttonPanel.add(startButton);
        buttonPanel.add(stopFirstButton);
        buttonPanel.add(stopLastButton);
        buttonPanel.add(stopAllButton);
        buttonPanel.add(closeButton);

        ButtonHandler handler = new ButtonHandler();

        startButton.addActionListener(handler);
        stopFirstButton.addActionListener(handler);
        stopLastButton.addActionListener(handler);
        stopAllButton.addActionListener(handler);
        closeButton.addActionListener(handler);

        add(buttonPanel, "South");
    }

    private class ButtonHandler implements ActionListener {
        //private Ball ball;
        private LinkedList<Ball> ballList = new LinkedList<>();

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == startButton) {
                Ball ball = new Ball(canvas);
                ballList.add(ball);

                Thread t1 = new Thread(ball);
                t1.start();
            }

            else if (event.getSource() == stopFirstButton) {
                if(ballList.size() != 0)
                {
                    ballList.getFirst().setRunning(false);
                    ballList.removeFirst();
                }
            }

            else if(event.getSource() == stopLastButton)
            {
                if(ballList.size() != 0) {
                    ballList.getLast().setRunning(false);
                    ballList.removeLast();
                }
            }

            else if(event.getSource() == stopAllButton)
            {
                if(ballList.size() != 0) {
                    ListIterator<Ball> i = ballList.listIterator();

                    while (i.hasNext())
                    {
                        i.next().setRunning(false);
                        i.remove();
                    }
                }
            }

            else if (event.getSource() == closeButton) {
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) {
        BounceFrame frame = new BounceFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
