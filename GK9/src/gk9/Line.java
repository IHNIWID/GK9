/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk9;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jakub Tomczuk
 */
public class Line extends JPanel {

    static int xone = 100, xtwo = 900, yone = 200, ytwo = 400;

    public Line() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000, 550));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawLine(xone, yone, xtwo, ytwo);

    }

    public void init() {

        JFrame frame = new JFrame("GK9");
        JPanel panel = new JPanel();
        JPanel canvas = new JPanel();

        JButton move = new JButton("Move");
        JButton rotate = new JButton("Rotate");
        JButton scale = new JButton("Scale");

        JTextField h = new JTextField("0");
        h.setPreferredSize(new Dimension(40, 20));
        JLabel ht = new JLabel("H: ");
        JTextField v = new JTextField("0");
        v.setPreferredSize(new Dimension(40, 20));
        JLabel vt = new JLabel("V: ");
        JTextField x1 = new JTextField("0");
        x1.setPreferredSize(new Dimension(40, 20));
        JLabel x1t = new JLabel("X1: ");
        JTextField y1 = new JTextField("0");
        y1.setPreferredSize(new Dimension(40, 20));
        JLabel y1t = new JLabel("Y1: ");
        JTextField arck = new JTextField("0");
        arck.setPreferredSize(new Dimension(40, 20));
        JLabel arct = new JLabel("Arc: ");
        JTextField k = new JTextField("0");
        k.setPreferredSize(new Dimension(40, 20));
        JLabel kt = new JLabel("K: ");

        canvas.setPreferredSize(new Dimension(1000, 600));
        canvas.setBackground(Color.GRAY);

        panel.setPreferredSize(new Dimension(1000, 50));
        panel.setBackground(Color.WHITE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        canvas.add(panel);
        canvas.add(new Line());

        panel.add(move);
        panel.add(rotate);
        panel.add(scale);
        panel.add(ht);
        panel.add(h);
        panel.add(vt);
        panel.add(v);
        panel.add(x1t);
        panel.add(x1);
        panel.add(y1t);
        panel.add(y1);
        panel.add(arct);
        panel.add(arck);
        panel.add(kt);
        panel.add(k);

        frame.add(canvas);

        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int sendh = Integer.parseInt(h.getText());
                int sendv = Integer.parseInt(v.getText());
                moveit(sendh, sendv);
                canvas.setVisible(false);
                revalidate();
                repaint();
                canvas.setVisible(true);
            }
        });
        
        rotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pointx = Integer.parseInt(x1.getText());
                int pointy = Integer.parseInt(y1.getText());
                double arc = Double.parseDouble(arck.getText());
                rotateit(pointx, pointy, arc);
                canvas.setVisible(false);
                revalidate();
                repaint();
                canvas.setVisible(true);
            }
        });

        scale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pointx1 = Integer.parseInt(x1.getText());
                int pointy1 = Integer.parseInt(y1.getText());
                double k1 = Double.parseDouble(k.getText());
                scaleit(pointx1, pointy1, k1);
                canvas.setVisible(false);
                revalidate();
                repaint();
                canvas.setVisible(true);
            }
        });

        frame.setVisible(true);
        frame.pack();

    }

    static public void moveit(int h, int v) {

        xone += h;
        yone += v;
        xtwo += h;
        ytwo += v;

    }

    static public void rotateit(int x, int y, double arc) {
        int txone = (int) ((x + (xone - x) * Math.cos(arc)) - (yone - y) * Math.sin(arc));
        int tyone = (int) ((x + (xone - x) * Math.sin(arc)) + (yone - y) * Math.cos(arc));
        int txtwo = (int) ((x + (xtwo - x) * Math.cos(arc)) - (ytwo - y) * Math.sin(arc));
        int tytwo = (int) ((x + (xtwo - x) * Math.sin(arc)) + (ytwo - y) * Math.cos(arc));
        xone = txone;
        yone = tyone;
        xtwo = txtwo;
        ytwo = tytwo;
    }

    static public void scaleit(int x, int y, double k) {

        xone = (int) (xone * k + (1 - k) * x);
        yone = (int) (yone * k + (1 - k) * y);
        xtwo = (int) (xtwo * k + (1 - k) * x);
        ytwo = (int) (ytwo * k + (1 - k) * y);

    }
}
