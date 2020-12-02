/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.venyunoc.badgepic;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author nathan
 */
public class UI extends JFrame {
    
    private final ImageDataService imageDataService;
    private final ImageWriterService imageWriterService;
    private final BioDataService bioDataService;

    /**
     * Creates new form BadgePicUI
     */
    public UI(ImageDataService imageDataService, ImageWriterService imageWriterService, BioDataService bioDataService) {
        this.imageDataService = imageDataService;
        this.imageWriterService = imageWriterService;
        this.bioDataService = bioDataService;        
        
        initComponents();
        
        DocumentListener dl = new TextFieldDocumentListener();
        
        this.jTextField1.getDocument().putProperty("id", 1);
        this.jTextField2.getDocument().putProperty("id", 2);
        this.jTextField3.getDocument().putProperty("id", 3);
        
        this.jTextField1.getDocument().addDocumentListener(dl);
        this.jTextField2.getDocument().addDocumentListener(dl);
        this.jTextField3.getDocument().addDocumentListener(dl);
    }
    
    private void updateGutter() {
        String filename = String.format("%s - %s, %s.jpg", 
                                            this.bioDataService.badgeNumber(),
                                            this.bioDataService.lastName(),
                                            this.bioDataService.firstName());
        String fullPath = this.imageWriterService.folder.resolve(filename).toString();
        
        this.jLabel4.setText(String.format("Output file path: %s", fullPath));
    }
    
    public class TextFieldDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            this.onChange(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            this.onChange(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            
        }
        
        private void onChange(DocumentEvent e) {
            int id = (int)e.getDocument().getProperty("id");
            switch (id) {
                case 1:
                    bioDataService.submitFirstName(jTextField1.getText());
                    break;
                case 2:
                    bioDataService.submitLastName(jTextField2.getText());
                    break;
                case 3:
                    bioDataService.submitBadgeNumber(jTextField3.getText());
                    break;
                default:
                    break;
            }
            updateGutter();
        }
        
    }

    private void initComponents() {
        this.setTitle("Badger");
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        this.setPreferredSize(new Dimension(600, 400));

        jLabel1 = new JLabel("First Name");
        jTextField1 = new JTextField();
        jLabel2 = new JLabel("Last Name");
        jTextField2 = new JTextField();
        jLabel3 = new JLabel("Badge Number");
        jTextField3 = new JTextField();
        jLabel4 = new JLabel("Output file path: <none>");
        imageCapture = new ImageCaptureComponent(this.imageDataService, this.imageWriterService, this.bioDataService);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)))
                    .addComponent(imageCapture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageCapture, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private ImageCaptureComponent imageCapture;
}
