/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CpuSimulation;

import GUI.MainFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Billy
 */
public class SchedulingSimulator {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static final double x = screenSize.getWidth();
    static final double y = screenSize.getHeight();

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        MainFrame Mf = new MainFrame();
//        Mf.startGen(0, 1000);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Mf.setTitle("Cpu Simulation");
                Mf.setResizable(false);
                Mf.setLocation((int) ((x / 2) - 794 / 2), (int) ((y / 2) - 974 / 2));
                Mf.setVisible(true);
            }//974, 794
        });

    }

}
