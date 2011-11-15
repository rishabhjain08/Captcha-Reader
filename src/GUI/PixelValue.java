
package GUI;

import Utility.ArePointsConnected;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class PixelValue extends javax.swing.JFrame {

    /** Creates new form PixelValue */
    public PixelValue() {
//        this.setUndecorated(true);
        initComponents();
        click.setVisible(false);
        clicked=new ClickLabel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusBar = new javax.swing.JPanel();
        bar = new javax.swing.JProgressBar();
        point = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        pixel = new javax.swing.JLabel();
        connected = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        click = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        /*
        imgpanel = new javax.swing.JPanel();
        */imgpanel=new ImagePanel();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        statusBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        point.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        point.setText("Location");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pixel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pixel.setText("Pixel Value");

        connected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connected.setText("Connected");
        connected.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        click.setForeground(new java.awt.Color(255, 0, 0));
        click.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        click.setText("Clicked");

        javax.swing.GroupLayout statusBarLayout = new javax.swing.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusBarLayout.createSequentialGroup()
                .addComponent(point, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pixel, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connected, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(click, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(point, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addGroup(statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(click, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(statusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pixel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        imgpanel.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        imgpanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgpanelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                imgpanelMouseExited(evt);
            }
        });
        imgpanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                imgpanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout imgpanelLayout = new javax.swing.GroupLayout(imgpanel);
        imgpanel.setLayout(imgpanelLayout);
        imgpanelLayout.setHorizontalGroup(
            imgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );
        imgpanelLayout.setVerticalGroup(
            imgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        scroll.setViewportView(imgpanel);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menu.add(jMenu1);

        jMenu2.setText("Tools");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Show Scan Lines");
        jCheckBoxMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBoxMenuItem1MouseReleased(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);

        menu.add(jMenu2);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-564)/2, (screenSize.height-347)/2, 564, 347);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
    }//GEN-LAST:event_jMenuItem1MouseClicked
BufferedImage img;
    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        try {
            JFileChooser choose = new JFileChooser();
            choose.setVisible(true);
            choose.setDialogTitle("Choose an image file.");
            choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
            choose.showOpenDialog(null);
            File f = choose.getSelectedFile();
            if(f==null)
                return;
            imgpanel.setImage(f);
            img = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(PixelValue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
            System.exit(0);
    }//GEN-LAST:event_jMenuItem2MousePressed
boolean firstclick=false;
boolean secondclick=false;
ClickLabel clicked;
ConnectionThread connect;
    private void imgpanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgpanelMouseClicked
        if(connect!=null&&connect.isAlive()){
            evt.consume();
            return;
        }
        if(!connected.getText().equals("Connected"))
            connected.setText("Connected");
        new ClickLabel().start();
        int x=evt.getPoint().x;
        int y=evt.getPoint().y;
        if(firstclick)
            secondclick=true;
        if(!firstclick&&x>=0&&x<img.getWidth()&&y>=0&&y<img.getHeight()){
            firstclick=true;
            imgpanel.setStart(evt.getPoint());
        }
        if(firstclick&&secondclick){
            if(x>=0&&x<img.getWidth()&&y>=0&&y<img.getHeight()){
            firstclick=false;
            imgpanel.setEnd(evt.getPoint());
       /////////
                connect=new ConnectionThread();
                connected.setText("Computing....");
                connect.setPoints(imgpanel.start, imgpanel.end);
                connect.start();
                imgpanel.setStart(null);
                imgpanel.setEnd(null);
        }
            else{
                firstclick=false;
                imgpanel.setStart(null);
                imgpanel.setEnd(null);
                if(!connected.getText().equals("Computing...."))
                    connected.setText("Connected");
            }
                secondclick=false;
                imgpanel.repaint();
        }
    }//GEN-LAST:event_imgpanelMouseClicked

    class ConnectionThread extends Thread{
        private Point start;
        private Point end;
        @Override
            public void run(){
            try {
                boolean b=new ArePointsConnected().initialize(img, imgpanel, start.x, start.y, end.x, end.y,true,false);
                connected.setText(String.valueOf(b));
            } catch (Exception ex) {                
                connected.setText("false");
            }
            }
        public void setPoints(Point start1,Point end1){
            start=start1;
            end=end1;
        }
    }
    class ClickLabel extends Thread{
        @Override
        public void run(){
            if(click.isVisible())
                click.setVisible(false);
            else
                click.setVisible(true);
        }
    }
    private void imgpanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgpanelMouseMoved
        if(!connected.getText().equals("Computing...."))
                connected.setText("Connected");
        int x=evt.getPoint().x;
        int y=evt.getPoint().y;
        if(firstclick&&!secondclick&&x>=0&&x<img.getWidth()&&y>=0&&y<img.getHeight()){
            imgpanel.setEnd(evt.getPoint());
            imgpanel.repaint();
        }
            point.setText(x+","+y);
        if(img!=null&&x>=0&&x<img.getWidth()&&y>=0&&y<img.getHeight()){
            int pix=imgpanel.getPixels(evt.getPoint());
            int alpha=(pix>>24)&0xff;
            int r=(pix>>16)&0xff;
            int g=(pix>>8)&0xff;
            int b=(pix)&0xff;
            pixel.setText("Alpha : "+String.valueOf(alpha)+"  R : "+String.valueOf(r)+"  G : "+String.valueOf(g)+"  B : "+String.valueOf(b));
        }
        else
            pixel.setText("Pixel Value");
    }//GEN-LAST:event_imgpanelMouseMoved

    private void imgpanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgpanelMouseExited
/*        firstclick=false;
        secondclick=false;
        imgpanel.setStart(null);
        imgpanel.setEnd(null);
 * 
 */
        pixel.setText("Pixel Value");
        point.setText("Location");
        if(!connected.getText().equals("Computing...."))
                connected.setText("Connected");
    }//GEN-LAST:event_imgpanelMouseExited

    private void jCheckBoxMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1MouseClicked
    }//GEN-LAST:event_jCheckBoxMenuItem1MouseClicked

    private void jCheckBoxMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1MousePressed
    }//GEN-LAST:event_jCheckBoxMenuItem1MousePressed

    private void jCheckBoxMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1MouseReleased
        if(jCheckBoxMenuItem1.isSelected())
            imgpanel.setScanLinesVisible(true);
        else
            imgpanel.setScanLinesVisible(false);
    }//GEN-LAST:event_jCheckBoxMenuItem1MouseReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PixelValue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JLabel click;
    private javax.swing.JLabel connected;
    /*
    private javax.swing.JPanel imgpanel;
    */ImagePanel imgpanel;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JMenuBar menu;
    private javax.swing.JLabel pixel;
    private javax.swing.JLabel point;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JPanel statusBar;
    // End of variables declaration//GEN-END:variables

}
