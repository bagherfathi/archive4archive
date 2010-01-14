/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package popmenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JWindow;

/**
 *
 * @author Administrator
 */
public class POPWindow extends JWindow implements Runnable {

    private static Dimension dim;
    private int x, y;
    private int width, height;
    private boolean isActive = false;

    {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = 270;
        height = 200;
        x = (int) (dim.getWidth() - width);
        y = (int) (dim.getHeight());
//        initwidth = 250;
//        initheight = 150;
//        initx = (int) (dim.getWidth() - initwidth);
//        inity = (int) (dim.getHeight());

    }

    public POPWindow() {
    }

    public boolean isRun() {
        return isActive;
    }

    public void startPop() {
        initComponents();
        if (isActive) {
            return;
        }
//        this.toFront();
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        new Thread(this).start();
        isActive = true;
    }

    public void stopPop() {
        if (!isActive) {
            return;
        }
        this.dispose();
        isActive = false;
    }

    private void showJPopupMenu(MouseEvent e) {
        stopPop();
    }

    public void run() {
        for (int i = 0; i <= height + 50; i += 10) {
            try {
//                this.toFront();
                this.setLocation(x, y - i);
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(POPWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initComponents() {
        this.setSize(width, height);
        this.setLocation(x, y);
        this.setLayout(new BorderLayout());
//        JPanel tipBar = createTipBar();
        // tipBar.addMouseListener(new MouseAdapter() {

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showJPopupMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showJPopupMenu(e);
            }
        });
//        this.add(tipBar, BorderLayout.NORTH);
        this.setVisible(true);
    }

    /**
     * 鍒涘缓涓婇潰鐨勫伐鍏锋潯
     */
//    private JPanel createTipBar() {
//
//        JPanel jpanel = new JPanel();
//        jpanel.setForeground(Color.red);
//        jpanel.setBackground(Color.red);
//        jpanel.setSize(width, height);
////        TipBar tipBar = new TipBar(createImage());
//        return jpanel;
//    }

    public static void main(String[] args) {
//        new POPWindow().toFront();
//        SplashScreen s = new SplashScreen();   // And sets it visible too.
//        TipWindow tipWindow=new TipWindow();
//        Timer timer = new Timer();
//        MyTask myTask=new MyTask();
//        myTask.tipWindow=tipWindow;
//        timer.schedule(new MyTask(), 1000, 5000);
    }
}
