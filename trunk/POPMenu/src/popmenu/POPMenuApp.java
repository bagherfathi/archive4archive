/*
 * POPMenuApp.java
 */
package popmenu;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import sun.awt.shell.ShellFolder;

/**
 * The main class of the application.
 */
public class POPMenuApp extends TrayIcon {

    private JPopupMenu menu;
    private static JDialog dialog;
    private static PopTimer PopTimer = new PopTimer();
    private static Timer timer = new Timer();
    public static POPWindow tipWindow = new POPWindow();
    public static String username = null;
    public static String password = null;
    private static LoginDialog loginDialog = new LoginDialog(new javax.swing.JFrame(), true);

    ;
//    private TipWindow tipWindow;

    static {
        dialog = new JDialog((Frame) null, "TrayDialog");
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);
        PopTimer popTimer = new PopTimer();
//        popTimer.setTipWindow(tipWindow);
        timer.schedule(popTimer, 1000, 5000);
//        final LoginDialog
//        loginDialog = new LoginDialog(new javax.swing.JFrame(), true);
        loginDialog.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent e) {
                loginDialog.setVisible(false);
            }
        });
        loginDialog.setVisible(false);
    }

    public static void finish() {
        tipWindow.stopPop();
        timer.cancel();
    }
    private static PopupMenuListener popupListener = new PopupMenuListener() {

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            dialog.setVisible(false);
        }

        public void popupMenuCanceled(PopupMenuEvent e) {
            dialog.setVisible(false);
        }
    };

    public POPMenuApp(Image image) {
        super(image);
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showJPopupMenu(e);
            }
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                showJPopupMenu(e);
//            }
        });
    }

    private void showJPopupMenu(MouseEvent e) {
//        if (e.isPopupTrigger() && menu != null) {
        if (menu != null) {
            if (e.getClickCount() == 1) {
                Dimension size = menu.getPreferredSize();
                dialog.setLocation(e.getX(), e.getY() - size.height);
                dialog.setVisible(true);
                menu.show(dialog.getContentPane(), 0, 0);
                // popup works only for focused windows
//                dialog.toFront();
            }
        }
    }

    public JPopupMenu getJPopupMenu() {
        return menu;
    }

    public void setJPopupMenu(JPopupMenu menu) {
        if (this.menu != null) {
            this.menu.removePopupMenuListener(popupListener);
        }
        this.menu = menu;
        menu.addPopupMenuListener(popupListener);
    }

    private static void createGui() {
        POPMenuApp tray = new POPMenuApp(createImage());
        tray.setJPopupMenu(createJPopupMenu());
        try {
            SystemTray.getSystemTray().add(tray);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static Image createImage() {
        File file = new File(".\\1.ico");
        Image icon;
        int width = 16;
        int height = 16;
        try {
            icon = ShellFolder.getShellFolder(file).getIcon(true);
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(POPMenuApp.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage b = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = b.getGraphics();
        graphics.drawImage(icon, 0, 0, width, height, null);
        return b;
    }

    static JPopupMenu createJPopupMenu() {
        final JPopupMenu m = new JPopupMenu();
        JMenuItem jMenuItemsub1 = new JMenuItem("item 2");
        jMenuItemsub1.setText("登录");
        m.add(new JMenuItem("Item 1"));
        m.add(new JMenuItem("Item 2"));
        JMenu submenu = new JMenu("系统设置");
        submenu.add(jMenuItemsub1);
        submenu.add(new JMenuItem("item 2"));
        jMenuItemsub1.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                loginDialog.setVisible(true);
            }
        });
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loginDialog.setAlwaysOnTop(true);
        loginDialog.setMyLocation(new Double(dim.getWidth() / 2 - 100).intValue(), new Double(dim.getHeight() / 2 - 200).intValue());
        loginDialog.setVisible(true);
        submenu.add(new JMenuItem("item 3"));
        m.add(submenu);
        JMenuItem exitItem = new JMenuItem("退出");
        MyActionListener myActionListener = new MyActionListener();
        exitItem.addActionListener(myActionListener);
        m.add(exitItem);
        return m;
    }

    static class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            POPMenuApp.finish();
            String reqString=ServerUrl.logoutUrl+"?jx_username=" + POPMenuApp.username + "&jx_password=" + POPMenuApp.password;
            String message = new HttpClientApp().httpRequest(reqString);
            System.out.print(message);
            if (!message.contains("false")) {
                POPMenuApp.tipWindow.setText(message);
                POPMenuApp.tipWindow.startPop();
            }
            System.exit(0);
        }
    }

    static class PopTimer extends java.util.TimerTask {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (POPMenuApp.tipWindow.isRun()) {
                POPMenuApp.tipWindow.stopPop();
            } else {
                if (POPMenuApp.username != null && POPMenuApp.password != null) {
                    try {
                        String messageSrc = new HttpClientApp().httpRequest(ServerUrl.checkListUrl+"?jx_username=" + POPMenuApp.username + "&jx_password=" + POPMenuApp.password);
                        byte[] bs = messageSrc.getBytes("UTF-8");
                        String message = new String(bs);

                        if (!message.contains("false")) {
                            POPMenuApp.tipWindow.setText(message);
                            POPMenuApp.tipWindow.startPop();
                        }
                    } catch (UnsupportedEncodingException ex) {
                        System.out.print(ex);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createGui();
            }
        });
    }
}

