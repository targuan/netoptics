/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.tap.FilterUtils;
import test.tap.TapFilter;

/**
 *
 * @author targuan
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*NetworkTap tap = new NetworkTap("tap1");
         tap.addIface(new NetworkInterface(tap, "1"));
            
         NetworkRouter veg = new NetworkRouter("veg");
         veg.addIface(new NetworkInterface(veg, "po5"));
         veg.addIface(new NetworkInterface(veg, "eth7/14"));
            
         veg.monitor(veg.getIface("po5"), veg.getIface("eth7/14"));
            
         NetworkBus bus = new NetworkBus();
         bus.addIface(veg.getIface("eth7/14"));
         bus.addIface(tap.getIface("1"));
            
            
         veg.getIface("po5").sendFrame(new NetworkFrame());*/
        /*try {
         NetworkTap tap = NetworkTap.createFromFile(new File("sir14tap1_20140516"));
         NetworkProbe probe = new NetworkProbe("sirprobe1");
            
         NetworkBus.connect(tap.getIface("23"), probe.getIface("a"));
         NetworkBus.connect(tap.getIface("24"), probe.getIface("b"));
            
         NetworkFrame frame= new NetworkFrame();
         frame.setIp4Dst("10.194.9.255");
            
         tap.getIface("13").sendFrame(frame);
         } catch (FileNotFoundException ex) {
         Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        NetworkTopology topology = NetworkTopology.createNetworkTopology(new File("network.xml"));
        NetworkElement e = topology.getNetworkElement("sir14tap1");
        
         
        
    }

}
