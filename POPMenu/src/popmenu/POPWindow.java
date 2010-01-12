/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapplication1;

//import com.lgh.pic.Pic;
//import com.lgh.util.MouseMotionUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JWindow;
import sun.awt.shell.ShellFolder;

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
        width = 200;
        height = 150;
        x = (int) (dim.getWidth() - width);
        y = (int) (dim.getHeight());
    }

    public POPWindow() {
    }

    public boolean isRun(){
        return isActive;
    }
    public void startPop() {
        initComponents();
        if(isActive){
            return;
        }
        new Thread(this).start();
        isActive = true;
    }

    public void stopPop() {
        if(!isActive){
            return;
        }
        this.dispose();
        isActive = false;
    }

    private void showJPopupMenu(MouseEvent e) {
        stopPop();
    }

    public void run() {
        for (int i = 0; i <= height; i += 10) {
            try {
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
        JPanel tipBar = createTipBar();
        tipBar.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                showJPopupMenu(e);
            }

            public void mouseReleased(MouseEvent e) {
                showJPopupMenu(e);
            }
        });
        this.add(tipBar, BorderLayout.NORTH);
        this.setVisible(true);
    }

    /**
     * 创建上面的工具条
     */
    private JPanel createTipBar() {

//        TipBar tipBar = new TipBar(createImage());
        return new POPPanel();
    }

    public static void main(String[] args) {
//        TipWindow tipWindow=new TipWindow();
//        Timer timer = new Timer();
//        MyTask myTask=new MyTask();
//        myTask.tipWindow=tipWindow;
//        timer.schedule(new MyTask(), 1000, 5000);
    }
}

