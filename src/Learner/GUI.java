package Learner;

import PostProcessing.Job;
import PostProcessing.ProcessJob;
import PostProcessing.ReadLetter;
import Utility.ArePointsConnected;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI extends javax.swing.JFrame {

    private File dir;
    Dimension SCREEN_SIZE=Toolkit.getDefaultToolkit().getScreenSize();
    Object[][] loaded;
//    Object[][] original_loaded;
    private int counter=0;
    private Point center;
    private Cursor pencil;
    private boolean mark_center=false;
    private Circle circle;
    private ReadLetter readLetter;
    private boolean start_tagging=false;
    private BufferedImage curr_image;
    private Job process;
    private ProcessJob processJob;
    private JFrame frame;
    private int table_can_be_changed=1;
    private List data;
    private File learner_data;
    private final double error_percent=0.4;

    public GUI() {
//        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(new File("C:/Users/ci/Desktop/pencil_cursor.png"));
//        } catch (IOException ex) {
//            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        pencil = defaultToolkit.createCustomCursor(image,new Point(8,8) , "Pencil");
//        frame=this;
        initComponents();
        data=new LinkedList();
        learner_data=new File("C:/Users/ci/Desktop/captcha reading/learner data.txt");
        this.readFromFile(learner_data);
        info.setVisible(false);
        readLetter=new ReadLetter();
        processJob = new ProcessJob();
        zoomer.setValue(1);
        zoomer.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(curr_image!=null)
                    table.setImage(scaleImage(curr_image,zoomer.getValue()));
                coordinates.setText("");
            }

        }
        );
        imagelist.setRowSelectionAllowed(true);
        imagelist.setColumnSelectionAllowed(false);
        ListSelectionListener listSelectionListener;
        imagelist.getSelectionModel().addListSelectionListener((listSelectionListener=new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if(table_can_be_changed==0||e.getValueIsAdjusting())
                    return;
                int old_row=e.getFirstIndex();
                int new_row = imagelist.getSelectedRow();
//                System.out.println(old_row+","+new_row);
                if(canCounterbeChanged(old_row,new_row)){
//                    System.out.println("here");
                    counterChanged(old_row,new_row);
                }
//                System.out.println("out");
            }


        }

        ));
        imagelist.getColumnModel().getSelectionModel().addListSelectionListener(listSelectionListener);
        circle=new Circle();
        tools.setOrientation(JToolBar.VERTICAL);
        this.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                try {
                    writeToFile(learner_data);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        }
        );
        this.setSize(SCREEN_SIZE);
    }


    private BufferedImage createCopyofImage(BufferedImage img){
        int w=img.getWidth();
        int h=img.getHeight();
        int[] pix=new int[w*h];
        pix=img.getRGB(0, 0, w, h, pix, 0, w);
        int[] newpix=new int[w*h];
        int i=0;
        while(i<pix.length){
            newpix[i]=pix[i];
            i++;
        }
        BufferedImage img1=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        img1.setRGB(0, 0, w, h, newpix, 0, w);
        return img1;
    }

    private boolean canCounterbeChanged(int old_counter,int new_counter){
        if(start_tagging&&tag.getText().equals("")&&old_counter!=new_counter){
            new Message("       Tag the Image.      ").setVisible(true);
//            System.out.println("here 1");
            table_can_be_changed=0;
            imagelist.setRowSelectionInterval(old_counter, old_counter);
            table_can_be_changed=1;
//            System.out.println("false");
            return false;
        }
//        System.out.println("true");
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        /*
        back = new javax.swing.JPanel();
        */back=new BackPanel();
        /*
        table = new javax.swing.JPanel();
        */table=new TablePanel();
        zoomer = new javax.swing.JSlider();
        /*
        slide = new javax.swing.JPanel();
        */slide=new SlidePanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        job = new javax.swing.JLabel();
        jobname = new javax.swing.JLabel();
        /*
        info = new javax.swing.JPanel();
        */info=new InfoPanel();
        name = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        imagelist = new javax.swing.JTable();
        ask_for_tag = new javax.swing.JButton();
        id = new javax.swing.JPanel();
        tag = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        hideinfopanel = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        bar = new javax.swing.JProgressBar();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        coordinates = new javax.swing.JLabel();
        tools = new javax.swing.JToolBar();
        showinfo = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setBackground(new java.awt.Color(204, 204, 204));
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                BackMouseWheelListener(evt);
            }
        });
        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tableMouseDragged(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableKeyTyped(evt);
            }
        });

        zoomer.setBackground(new java.awt.Color(204, 204, 204));
        zoomer.setFont(new java.awt.Font("Tahoma", 0, 10));
        zoomer.setForeground(new java.awt.Color(102, 0, 102));
        zoomer.setMajorTickSpacing(1);
        zoomer.setMaximum(20);
        zoomer.setMinimum(1);
        zoomer.setOrientation(javax.swing.JSlider.VERTICAL);
        zoomer.setPaintLabels(true);
        zoomer.setPaintTicks(true);
        zoomer.setSnapToTicks(true);
        zoomer.setDoubleBuffered(true);

        javax.swing.GroupLayout tableLayout = new javax.swing.GroupLayout(table);
        table.setLayout(tableLayout);
        tableLayout.setHorizontalGroup(
            tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tableLayout.createSequentialGroup()
                .addContainerGap(878, Short.MAX_VALUE)
                .addComponent(zoomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        tableLayout.setVerticalGroup(
            tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(zoomer, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );

        zoomer.getAccessibleContext().setAccessibleParent(this);

        slide.setBackground(new java.awt.Color(102, 102, 102));

        jButton1.setText("< Previous");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Next >");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        job.setFont(new java.awt.Font("Tahoma", 0, 20));
        job.setForeground(new java.awt.Color(255, 255, 255));
        job.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jobname.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jobname.setForeground(new java.awt.Color(255, 255, 255));
        jobname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout slideLayout = new javax.swing.GroupLayout(slide);
        slide.setLayout(slideLayout);
        slideLayout.setHorizontalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slideLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jobname, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                    .addComponent(job, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        slideLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        slideLayout.setVerticalGroup(
            slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slideLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(slideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(job)
                    .addComponent(jobname))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        info.setBackground(new java.awt.Color(102, 102, 102));

        name.setFont(new java.awt.Font("Tahoma", 0, 14));
        name.setForeground(new java.awt.Color(204, 204, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Image Name");

        imagelist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        imagelist.setColumnSelectionAllowed(true);
        imagelist.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(imagelist);
        imagelist.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        imagelist.getColumnModel().getColumn(0).setResizable(false);
        imagelist.getAccessibleContext().setAccessibleParent(this);

        ask_for_tag.setText("Start Tagging");
        ask_for_tag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ask_for_tagActionPerformed(evt);
            }
        });

        id.setBackground(new java.awt.Color(102, 102, 102));

        jButton4.setText("Mark It.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("Tag the image : ");

        javax.swing.GroupLayout idLayout = new javax.swing.GroupLayout(id);
        id.setLayout(idLayout);
        idLayout.setHorizontalGroup(
            idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(idLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tag, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                .addContainerGap())
        );
        idLayout.setVerticalGroup(
            idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(idLayout.createSequentialGroup()
                .addGroup(idLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tag.getAccessibleContext().setAccessibleParent(this);

        hideinfopanel.setText(">");
        hideinfopanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideinfopanelActionPerformed(evt);
            }
        });

        jButton5.setText("Clear the Lines.");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("Identify");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout infoLayout = new javax.swing.GroupLayout(info);
        info.setLayout(infoLayout);
        infoLayout.setHorizontalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hideinfopanel))
            .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
            .addGroup(infoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ask_for_tag, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        infoLayout.setVerticalGroup(
            infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoLayout.createSequentialGroup()
                .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hideinfopanel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))))
                .addGap(18, 18, 18)
                .addComponent(name)
                .addGap(18, 18, 18)
                .addComponent(ask_for_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/point.png"))); // NOI18N

        coordinates.setText("Point");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(coordinates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1155, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(coordinates)
        );

        bar.getAccessibleContext().setAccessibleParent(this);

        tools.setFloatable(false);
        tools.setOrientation(JToolBar.VERTICAL);
        tools.setRollover(true);
        tools.setDoubleBuffered(true);

        showinfo.setText("Info Panel");
        showinfo.setFocusable(false);
        showinfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showinfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showinfoActionPerformed(evt);
            }
        });
        tools.add(showinfo);

        javax.swing.GroupLayout backLayout = new javax.swing.GroupLayout(back);
        back.setLayout(backLayout);
        backLayout.setHorizontalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backLayout.createSequentialGroup()
                        .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(tools, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        backLayout.setVerticalGroup(
            backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backLayout.createSequentialGroup()
                .addGroup(backLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backLayout.createSequentialGroup()
                        .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tools, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tools.getAccessibleContext().setAccessibleParent(this);

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menu.add(jMenu1);

        jMenu2.setText("Edit");
        menu.add(jMenu2);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void counterChanged(int old_counter,int new_counter){
        bar.setIndeterminate(true);
        table_can_be_changed=0;
        start_tagging=false;
        BufferedImage im;
        id.setVisible(false);
        ask_for_tag.setVisible(true);
        tag.setText("");
        if(new_counter<loaded.length&&new_counter>=0){
            counter=new_counter;
            im=(BufferedImage) loaded[counter][0];
            curr_image=this.createCopyofImage(im);
            table.setImage(this.scaleImage(curr_image,zoomer.getValue()));
            name.setText((String) loaded[counter][1]);
            jobname.setText(((Job)loaded[counter][2]).getJob().toString());
            imagelist.setRowSelectionInterval(counter, counter);
        }
        else{
            name.setText("Image Name");
            curr_image=null;
        }
        table_can_be_changed=1;
        bar.setIndeterminate(false);
    }

    private void loadImages(File f){
        table_can_be_changed=0;
        bar.setIndeterminate(true);
        int count=0;
        File[] list=f.listFiles();
        int i=0;
        String s;
        while(i<list.length){
            if(!list[i].isDirectory()&&(s=list[i].getName()).substring(s.indexOf('.')).equals(".png"))
                    count++;
            i++;
        }
        loaded=new Object[count][3];
//        original_loaded=new Object[count][2];
        BufferedImage im;
        i=0;count=0;
        while(i<list.length){
            if(!list[i].isDirectory()&&(s=list[i].getName()).substring(s.indexOf('.')).equals(".png")){
                try {
                    System.out.println(list[i].getName());
                    loaded[count][2] = readLetter.readLetter((BufferedImage) (ImageIO.read(list[i])));
                    loaded[count][0] = this.createCopyofImage((im = ((Job)loaded[count][2]).getImg()));//m = ((Job)loaded[count][2]).getImg()).getSubimage(0, 0, im.getWidth(), im.getHeight());
                    ImageIO.write((RenderedImage) loaded[count][0], "png", new File("C:/Users/ci/Desktop/boundary forming 1/"+String.valueOf(count+1)+".png"));
                    loaded[count][1] = list[i].getPath();
                } catch (IOException ex) {
                    i--;
                    count--;
                }
                count++;
            }
            i++;
        }
        TableModel model= imagelist.getModel();
        i=model.getRowCount();
        while(i>0){
                ((DefaultTableModel)imagelist.getModel()).removeRow(0);
            i--;
        }
        i=0;
        while(i<loaded.length){
            ((DefaultTableModel)model).addRow(new Object[]{loaded[i][1]});
            i++;
        }
        if(loaded.length>0){
            info.setVisible(true);
            showinfo.setVisible(false);
        }
        else{
            info.setVisible(false);
            showinfo.setVisible(true);
        }
        this.counterChanged(-1,0);
        table_can_be_changed=1;
        bar.setIndeterminate(false);
    }

    private BufferedImage scaleImage(BufferedImage img,int scale_factor){
        if(img==null)
            return null;
        int w=img.getWidth();
        int h=img.getHeight();
        int pix[]=new int[w*h];
        PixelGrabber grab=new PixelGrabber(img,0,0,img.getWidth(),img.getHeight(),pix,0,w);
        try {
            grab.grabPixels();
        } catch (InterruptedException ex) {
            //Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage img1=new BufferedImage(w*scale_factor,scale_factor*h,BufferedImage.TYPE_INT_RGB);
        int[] newpix=new int[w*scale_factor*h*scale_factor];
        int i=0;
        int w1,h1,color,x,y;
        while(i<w*h){
            w1=i%w;
            h1=(int)i/w;
            color=pix[i];
            for(x=w1*scale_factor;x<(w1+1)*scale_factor;x++)
                            for(y=h1*scale_factor;y<(h1+1)*scale_factor;y++)
                                newpix[x+y*w*scale_factor]=color;
            i++;
        }
        img1.setRGB(0, 0, w*scale_factor, h*scale_factor, newpix, 0, w*scale_factor);
        return img1;
    }

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed

        Thread t=new Thread(){
            @Override
            public void run(){
                JFileChooser choose = new JFileChooser();
                choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                choose.showOpenDialog(null);
                File f=choose.getSelectedFile();
                if(f!=null){
                    dir=f;
                    loadImages(dir);
                }
                if(f==null)
                    dir=null;
            }
        };
            t.start();
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(loaded==null)
            return;
        counter++;
        if(counter<loaded.length){
            if(this.canCounterbeChanged(counter-1, counter))
                this.counterChanged(counter-1,counter);
            else
                counter--;
        }
        else
            counter=loaded.length;
//        System.out.println(counter);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(loaded==null)
            return;
        counter--;
        if(counter>=0){
            if(this.canCounterbeChanged(counter+1, counter))
                this.counterChanged(counter+1,counter);
            else counter++;
        }
        else
            counter=0;
//        System.out.println(counter);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BackMouseWheelListener(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_BackMouseWheelListener
       if(zoomer.getValue()-evt.getWheelRotation()<=zoomer.getMaximum()&&zoomer.getValue()-evt.getWheelRotation()>=zoomer.getMinimum())
           zoomer.setValue(zoomer.getValue()-evt.getWheelRotation());
    }//GEN-LAST:event_BackMouseWheelListener

    private void tableMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseDragged
    }//GEN-LAST:event_tableMouseDragged

    private void storeResult(JTable t){
        int i=0,k=0;
        String tagged_as=tag.getText();
        sign get = null;
        k=0;
        while(k<data.size()){
            get = (sign) data.get(k);
            if(get.getCharacter().equals(tagged_as))
                break;
            k++;
        }
        if(k==data.size()){
            data.add(get=new sign());
            get.setCharacter(tagged_as);
        }
        
        while(i<t.getRowCount()){
            if((Boolean)t.getValueAt(i, 6)&&!get.isPresent((String)t.getValueAt(i, 3), (String)t.getValueAt(i, 1), (String)t.getValueAt(i, 2), true,true,true,(Boolean)t.getValueAt(i, 5)))
                get.addInfo((String)t.getValueAt(i, 3), (String)t.getValueAt(i, 1), (String)t.getValueAt(i, 2), (Boolean)t.getValueAt(i, 4), (Boolean)t.getValueAt(i,5));
            i++;
        }
    }

    private void writeToFile(File f) throws IOException{
        FileWriter writer=null;
        writer = new FileWriter(f);
        int i=0;
        int k=0;
        while(i<data.size()){
            sign get = (sign) data.get(i);
            writer.write(get.getCharacter()+"\r\n");
            k=0;
            while(k<get.getInfo().size()){
                Object[] get1 = (Object[]) get.getInfo().get(k);
                writer.write("("+((String)get1[0])+","+((String)get1[1])+","+((String)get1[2])+")"+"\r\n");
                k++;
            }
            i++;
        }
        writer.close();
    }


    private void readFromFile(File f){
        Scanner scan = null;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String g;
        String g1,g2,g3;
        int t;
        sign sign = null;
        while(scan.hasNextLine()){
            g=scan.nextLine();
            if(g.indexOf('(')==-1){
                sign = new sign();
                sign.setCharacter(g);
                data.add(sign);
            }
            else
            {
                g1=g.substring(1, t=g.indexOf(','));
                g2=g.substring(t+1, t=g.indexOf(',', t+1));
                g3=g.substring(t+1, g.length()-1);
                sign.addInfo(g1, g2, g3, true,false);
            }
        }
        scan.close();
    }

    private sign getSign(String character){
        int i=0;
        while(i<data.size()){
            if(((sign)data.get(i)).getCharacter().equals(character))
                return ((sign)data.get(i));
            i++;
        }
        return null;
    }

    class sign{
        private String character;
        private List l=new LinkedList();

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }
        
        public boolean isPresent(String id,String extraction,String length,boolean consider_id,boolean consider_extraction,boolean consider_length,boolean consider_atStrech){
            int i=0;
            boolean b1,b2,b3;
            while(i<l.size()){
                Object[] get = (Object[]) l.get(i);
                b1=false;b2=false;b3=false;
                if(consider_id&&((String)get[0]).equals(id))
                    b1=true;
                if(consider_extraction&&((String)get[1]).equals(extraction))
                    b2=true;
                if(consider_length&&!consider_atStrech&&((String)get[2]).equals(length))
                    b3=true;
                else if(consider_length && consider_atStrech && ((String) get[2]).equals(length + "s"))
                    b3=true;
                if(b1&&b2&&b3)
                    return true;
                i++;
            }
            return false;
        }
        
        public void addInfo(String id,String extraction,String length,boolean consider_length,boolean consider_atStrech){
            String s=consider_length ? length : "";
            s+=(consider_atStrech&&consider_length)?"s":"";
            boolean add = l.add(new Object[]{id, extraction, s});
        }

        public List getInfo(){
            return l;
        }

        private List getIDs(){
            int i=0;
            List <String>list=new LinkedList();
            while(i<l.size()){
                Object[] get = (Object[]) l.get(i);
                if(!this.isIntheList(get[0],list)){
                    list.add((String) get[0]);
                }
                i++;
            }
            return list;
        }
        private List getListForID(String s){
            List list=new LinkedList();
            int i=0;
            while(i<l.size()){
                Object[] get = (Object[]) l.get(i);
                if(((String)get[0]).equals(s))
                    list.add(get);
                i++;
            }
            return list;
        }

            private boolean isIntheList(Object o,List l){
                int y=0;
                while(y<l.size()){
                    if(l.get(y).equals(o))
                        return true;
                    y++;
                }
                return false;

            }
            
        }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(start_tagging&&tag.getText().equals("")){
            new Message("       Tag the Image.      ").setVisible(true);
            return;
        }
        mark_center=true;
        start_tagging=true;
        table.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ask_for_tagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ask_for_tagActionPerformed
        start_tagging=true;
        ask_for_tag.setVisible(false);
        id.setVisible(true);
    }//GEN-LAST:event_ask_for_tagActionPerformed

    private void hideinfopanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideinfopanelActionPerformed
        showinfo.setVisible(true);
        info.setVisible(false);
    }//GEN-LAST:event_hideinfopanelActionPerformed

    private void showinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showinfoActionPerformed
        showinfo.setVisible(false);
        info.setVisible(true);
    }//GEN-LAST:event_showinfoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        curr_image=this.createCopyofImage((BufferedImage) loaded[counter][0]);
        table.setImage(this.scaleImage(curr_image, zoomer.getValue()));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
    }//GEN-LAST:event_tableKeyPressed

    private void tableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyTyped
//        if(evt.getKeyCode()==KeyEvent.VK_LEFT)
//            this.jButton1ActionPerformed(null);
//        else if(evt.getKeyCode()==KeyEvent.VK_RIGHT)
//            this.jButton2ActionPerformed(null);
    }//GEN-LAST:event_tableKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Job job = ((Job) loaded[counter][2]);
        List l=new LinkedList();
        int i=0;
        while(i<data.size()){
            if(this.scanFor((sign)data.get(i),job)){
                l.add(((sign)data.get(i)).getCharacter());
            }
            i++;
        }
        if(!l.isEmpty()){
            LetterIs letterIs = new LetterIs();
            letterIs.setLetters(l);
            letterIs.setVisible(true);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private double getSumLength(String s){
        double sum=0;
        if(s.equals(""))
            return sum;
        int start=-1,remstart;
        while(true){
            if((remstart=s.indexOf(',',start+1))!=-1)
                sum+=Double.parseDouble(s.substring(start+1, start=remstart));
            else{
                sum+=Double.parseDouble(s.substring(start+1,s.length()));
                break;
            }
//            System.out.println(sum);
        }
        return sum;
    }
    private boolean scanFor(sign s,Job job){
        double pass_percent=0;
        int i=0,k=0;
        List iDs = s.getIDs();
        int count=0,m=0,start=0,t=0;
        double remi;
        String extraction,length;
        double rem,remsum = 0,sum = 0;
        StringBuffer buff=job.getJob();
        boolean broken=false,isAtStrech,strech_result = false;
        while(i<iDs.size()){
            Object get = iDs.get(i);
//            System.out.println(get);
            List listForID = s.getListForID((String) get);
            k=0;
            while(k<listForID.size()){
                Object[] get1 = (Object[]) listForID.get(k);
                extraction=(String) get1[1];
                length=(String) get1[2];
                isAtStrech=length.indexOf('s')==-1?false:true;
                length=isAtStrech?length.replace("s", ""):length;

                if(extraction.indexOf(':')!=-1){
                    StringBuffer stringBuffer = new StringBuffer(extraction);
                    String g1=extraction.substring(0, extraction.indexOf(':')),g2=extraction.substring(extraction.indexOf(':')+1);
                    if(buff.substring(buff.length()-g1.length()).equals(g1)&&buff.substring(0, g2.length()).equals(g2)){
                        if(length.equals("")){
                            k++;
                            count++;
                            continue;
                        }
                        String y1=length.substring(0, length.indexOf(':')),y2=length.substring(length.indexOf(':')+1);
                                String extract=g1;
                                start=buff.length()-g1.length();
                                m=start;
                                t=-1;
                                if(isAtStrech){
                                    strech_result=false;
                                    sum=0;
                                    remsum=this.getSumLength(y1)+this.getSumLength(y2);

                                    while(m<start+extract.length()){
                                        sum+=Double.parseDouble(y1.substring(t+1,t=(y1.indexOf(',',t+1)==-1?y1.length():y1.indexOf(',',t+1))));
                                        m++;
                                    }
                                    extract=g2;
                                    t=-1;
                                    start=0;
                                    m=start;
                                     while(m<start+extract.length()){
                                         sum+=Double.parseDouble(y2.substring(t+1,t=(y2.indexOf(',',t+1)==-1?y2.length():y2.indexOf(',',t+1))));
                                         m++;
                                     }

                                     if(sum<=remsum*(1+error_percent)&&sum>=remsum*(1-error_percent)){
                                         strech_result=true;
                                         count++;
                                         k++;
                                         continue;
                                    }
                                     else{
                                         extraction=extraction.replace(":", ",");
                                         length=length.replace(":", ",");
                                     }
                                }
                                else{
                                    while(m<start+extract.length()){
                                        rem=Double.parseDouble(y1.substring(t+1,t=(y1.indexOf(',',t+1)==-1?y1.length():y1.indexOf(',',t+1))));
                                        if(!((remi=job.getLength(m))<=rem*(1+error_percent)&&remi>=(1-error_percent)*rem)){
                                                m=start;
                                                break;
                                        }
                                        m++;
                                    }

                                    if(m==start+extract.length()){
                                        extract=g2;
                                        start=0;
                                        m=start;
                                        t=-1;
                                        while(m<start+extract.length()){
                                                rem=Double.parseDouble(y2.substring(t+1,t=(y2.indexOf(',',t+1)==-1?y2.length():y2.indexOf(',',t+1))));
                                                if(!((remi=job.getLength(m))<=rem*(1+error_percent)&&remi>=(1-error_percent)*rem)){
                                                        m=start;
                                                        break;
                                                }
                                                m++;
                                        }
                                        if(m==start+extract.length()){
                                                count++;
                                                k++;
                                                continue;
                                        }
                                        else{
                                            extraction=extraction.replace(":", ",");
                                            length=length.replace(":", ",");
                                        }
                                    }
                                    else{
                                        extraction=extraction.replace(":", ",");
                                        length=length.replace(":", ",");
                                    }
                            }
                    }
                    else{
                        extraction=extraction.replace(":", ",");
                        length=length.replace(":", ",");
                    }
                }

//                System.out.println("length : "+length);
                remsum = this.getSumLength(length);
                
                start=buff.indexOf(extraction,0);
                broken=false;
                while(start!=-1){
                    if(length.equals("")){
                        count++;
                        broken=true;
                        break;
                    }
                    m=start;
                    t=-1;
                    if(isAtStrech){
                        sum=0;
                        while(m<start+extraction.length()){
                            sum+=Double.parseDouble(length.substring(t+1,t=(length.indexOf(',',t+1)==-1?length.length():length.indexOf(',',t+1))));
                            m++;
                        }
                        if(sum<=(1+error_percent)*remsum&&sum>=(1-error_percent)*remsum){
                            broken=true;
                            count++;
                            break;
                        }
                    }
                    else{
                        while(m < start + extraction.length()){
                            rem=Double.parseDouble(length.substring(t+1,t=(length.indexOf(',',t+1)==-1?length.length():length.indexOf(',',t+1))));
                            if(!((remi=job.getLength(m))<=rem*(1+error_percent)&&remi>=(1-error_percent)*rem)){
                                m=start;
                                break;
                            }
                            m++;
                        }
                        if(m==start+extraction.length()){
                            count++;
                            broken=true;
                            break;
                        }
                    }
                    start=buff.indexOf(extraction,start+1);
                }
                if(broken)
                    break;
                k++;
            }
            i++;
        }
        if(!iDs.isEmpty()){
            pass_percent=100*(count/iDs.size());
            if(pass_percent>80)
                return true;
        }
        return false;
    }

    class Circle extends ArePointsConnected{

        private List <Integer>pointList;
        private Point center;
        private BufferedImage img;

        @Override
            public void strechMax(Line l){
            int y=l.getLeftCoordinate().y;
            int i=l.getLeftCoordinate().x;
            boolean entered=false;
            while(i>=0&&pix[i+y*w]!=Color.BLUE.getRGB()){
                    entered=true;
                    i--;
            }
            if(entered)
                    l.setLeftCoordinate(i+1, y);
            entered=false;
            i=l.getRightCoordinate().x;
            while(i<w&&pix[i+y*w]!=Color.BLUE.getRGB()){
                    entered=true;
                    i++;
            }
            if(entered)
                    l.setRightCoordinate(i-1, y);
    }

            public List getPointsList(Point p){
                center=p;
                pointList=new LinkedList();
                img=curr_image;
                w=img.getWidth();
                h=img.getHeight();
                pix=new int[w*h];
                PixelGrabber grab=new PixelGrabber(img,0,0,w,h,pix,0,w);
                try {
                    grab.grabPixels();
                } catch (InterruptedException ex) {
                    new Message("Failure. Try Again.").setVisible(true);
                }
                trav=new VectorLinesTravelled();
                Line hLine;
                strechMax(hLine=new Line(center.x,center.y,center.x,center.y));
                if(!this.traverse(hLine, -1, new Point(center.x,center.y))){
                        this.strechMax(hLine=new Line(center.x,center.y,center.x,center.y));
                        boolean b=this.traverse(hLine, 1, new Point(center.x,center.y));
                }
                strechMax(hLine=new Line(center.x,center.y,center.x,center.y));
                int x=hLine.getLeftCoordinate().x,y=hLine.getLeftCoordinate().y;
                while(x<=hLine.getRightCoordinate().x){
                    if(pix[x+y*w]==Color.RED.getRGB()){
                       pointList.add(x+y*w);
                    }
                    x++;
                }
                return pointList;
            }


        @Override
            public List getValidLines(Line valline){
            int start=-1;
            List <Line>list=new LinkedList();
            list.clear();
            int y=valline.getLeftCoordinate().y;
            int i=valline.getLeftCoordinate().x;
            int remx=valline.getRightCoordinate().x;
            while(i<=remx){
                if(pix[i+y*w]==Color.RED.getRGB()&&!this.isPresent(i+y*w, pointList))
                    pointList.add(i+y*w);
                if(start==-1&&pix[i+y*w]!=Color.BLUE.getRGB())
                            start=i;
                    if(start!=-1&&(i==valline.getRightCoordinate().x||pix[i+y*w]==Color.BLUE.getRGB())){
                            if(i==valline.getRightCoordinate().x&&pix[i+y*w]!=Color.BLUE.getRGB())
                                    list.add(new Line(start,y,i,y));
                            else
                                    list.add(new Line(start,y,i-1,y));
                            start=-1;
                    }

                    i++;
            }
            return list;
    }

        private boolean isPresent(int o,List l){
            int i=0;
            while(i<l.size()){
                if(((Integer)l.get(i))==o)
                    return true;
                i++;
            }
            return false;
        }

        @Override
        public boolean traverse(Line line,int dir,Point from){
            //System.out.println("("+line.getLeftCoordinate().x+","+line.getLeftCoordinate().y+"),("+line.getRightCoordinate().x+","+line.getRightCoordinate().y);
            Line remLine=new Line(line.getLeftCoordinate().x,line.getLeftCoordinate().y,line.getRightCoordinate().x,line.getRightCoordinate().y);
            line.setLeftCoordinate(line.getLeftCoordinate().x, line.getRightCoordinate().y+dir);
            line.setRightCoordinate(line.getRightCoordinate().x, line.getRightCoordinate().y+dir);
            if(line.getLeftCoordinate().y<0||line.getLeftCoordinate().y>=curr_image.getHeight()*zoomer.getHeight()){
                    line.setLeftCoordinate(line.getLeftCoordinate().x, line.getRightCoordinate().y-dir);
                    line.setRightCoordinate(line.getRightCoordinate().x, line.getRightCoordinate().y-dir);
                    return this.traverse(line, -dir, from);
            }
            Line unstreched=new Line(line.getLeftCoordinate().x,line.getLeftCoordinate().y,line.getRightCoordinate().x,line.getRightCoordinate().y);
            List <Line>list=getValidLines(line);
            int i=0;
            boolean conn=false,traverse;
            VectorLine vline;
            if(list.size()>1&&!trav.isLinePresent(vline=new VectorLine(remLine,-dir))){
                    conn=conn||this.traverse(new Line(unstreched.getLeftCoordinate().x,unstreched.getLeftCoordinate().y,unstreched.getRightCoordinate().x,unstreched.getRightCoordinate().y), -dir, from);
                    if(conn)
                            return true;
            }
            while(i<list.size()){
                    this.strechMax(list.get(i));
                    if(list.get(i).isPointOnLine(center.x, center.y)){
                            i++;
                            continue;
                    }
                    if(!trav.isLinePresent(vline=new VectorLine(list.get(i),dir))){
                            trav.addLine(vline);
                            traverse = this.traverse(list.get(i), dir, list.size() > 1 ? list.get(i).getMidPoint() : from);
                            conn=conn||traverse;
                            if(conn)
                                    return true;
                    }
                    i++;
            }
            if(i==0)
                return this.traverse(unstreched, -dir, from);
            return conn;
    }


    }


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ask_for_tag;
    /*
    private javax.swing.JPanel back;
    */BackPanel back;
    private javax.swing.JProgressBar bar;
    private javax.swing.JLabel coordinates;
    private javax.swing.JButton hideinfopanel;
    private javax.swing.JPanel id;
    private javax.swing.JTable imagelist;
    /*
    private javax.swing.JPanel info;
    */InfoPanel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel job;
    private javax.swing.JLabel jobname;
    private javax.swing.JMenuBar menu;
    private javax.swing.JLabel name;
    private javax.swing.JButton showinfo;
    /*
    private javax.swing.JPanel slide;
    */SlidePanel slide;
    /*
    private javax.swing.JPanel table;
    */TablePanel table;
    private javax.swing.JTextField tag;
    private javax.swing.JToolBar tools;
    private javax.swing.JSlider zoomer;
    // End of variables declaration//GEN-END:variables

    class BackPanel extends JPanel{

        BufferedImage img;
        BackPanel(){
            try {
                img = ImageIO.read(new File("C:/Users/ci/Desktop/dark_flame_hd-wide.png"));
            } catch (IOException ex) {
                //Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.repaint();
        }
        @Override
        public void paintComponent(Graphics g){
            boolean drawImage = g.drawImage(img, 0, 0, null);
        }
    }

    class InfoPanel extends JPanel{

    }

    class SlidePanel extends JPanel{

    }

    class TablePanel extends JPanel{
        private BufferedImage img;
        Point p;
        boolean dragging=false;
        Graphics2D rem_graphics;

        TablePanel(){

            p=null;

            this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if(mark_center&&(e.getPoint().x>=curr_image.getWidth()*zoomer.getValue()||e.getPoint().y>=curr_image.getHeight()*zoomer.getValue())){
                    Message message = new Message("   Mark should be inside the image.    ");
                    message.setVisible(true);
                    message.setFocusableWindowState(true);
                }
                else if(mark_center)
                {
                    center=new Point((int)e.getPoint().x/zoomer.getValue(),(int)e.getPoint().y/zoomer.getValue());
                    if(center==null){
                    new Message("Firstly mark a point inside the enclosing area.").setVisible(true);
                    return;
                    }

                    List pointsList = null;
                    try{
                        pointsList = circle.getPointsList(center);
                    }catch(Exception exe){
                        new Message("       Check if the required area is completely enclosed       ").setVisible(true);
                        mark_center=false;
                        center=null;
                        table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        return;
                    }
                        List[] strings = ((Job)loaded[counter][2]).getStrings(pointsList);

                int i=0;
                while(i<strings[0].size()){
                    System.out.print(strings[0].get(i)+",");
                    i++;
                }
                System.out.print("\n");
                final TagID tagID = new TagID();
                tagID.setExtractions(strings);
                tagID.setVisible(true);
                tagID.addWindowListener(new WindowListener() {

                    public void windowOpened(WindowEvent e) {
                    }

                    public void windowClosing(WindowEvent e) {
                    }

                    public void windowClosed(WindowEvent e) {
                        if(tagID.BUTTON)
                            storeResult(tagID.getResult());
                    }

                    public void windowIconified(WindowEvent e) {
                    }

                    public void windowDeiconified(WindowEvent e) {
                    }

                    public void windowActivated(WindowEvent e) {
                    }

                    public void windowDeactivated(WindowEvent e) {
                    }
                });
//                BufferedImage im=(BufferedImage) loaded[counter][0];
//                curr_image=createCopyofImage(im);//im.getSubimage(0, 0, im.getWidth(), im.getHeight());
//                            new Message("Here").setVisible(true);
//                            table.setImage(scaleImage(im, zoomer.getValue()));
                table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                mark_center=false;
                }
            }

            public void mouseReleased(MouseEvent e) {
                dragging=false;
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
            this.addMouseMotionListener(new MouseMotionListener() {

                public void mouseDragged(MouseEvent e) {
                    dragging=true;
                    p=e.getPoint();
                    repaint();
                }

                public void mouseMoved(MouseEvent e) {
                    if(curr_image!=null&&e.getPoint().x<curr_image.getWidth()*zoomer.getValue()&&e.getPoint().y<curr_image.getHeight()*zoomer.getValue())
                        coordinates.setText(String.valueOf(e.getPoint().x/zoomer.getValue())+","+String.valueOf(e.getPoint().y/zoomer.getValue()));
                    else
                        coordinates.setText("");
                }
        }
            );
            
        }
        
        @Override
        public void paintComponent(Graphics g1){
            if(img!=null){
                g1.drawImage(img, 0, 0, null);
                img=null;
            }
            Graphics g=g1.create();
            super.paintComponent(g1);
            if(dragging){
                g1.setColor(Color.BLUE);
                if(p!=null&&curr_image!=null&&p.x<curr_image.getWidth()*zoomer.getValue()&&p.y<curr_image.getHeight()*zoomer.getValue()){
                    int zoom_value = zoomer.getValue(),x,y;
                    curr_image.setRGB((int)p.x/zoom_value, (int)p.y/zoom_value, Color.BLUE.getRGB());
                }
                }
            
            g1.drawImage(scaleImage(curr_image,zoomer.getValue()), 0, 0, null);
        }
        public void setImage(BufferedImage img1){
            img=img1;
            this.repaint();
        }
    }

}