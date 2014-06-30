/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author targuan
 */
public class NetworkProbe extends NetworkElement{

    public NetworkProbe(String name) {
        super(name);
        
        addIface(new NetworkInterface(this, "a"));
        addIface(new NetworkInterface(this, "b"));
        addIface(new NetworkInterface(this, "c"));
        addIface(new NetworkInterface(this, "d"));
    }
    
    @Override
    public void receiveFrame(NetworkFrameEvent event) {
        NetworkInterface iface = (NetworkInterface) event.getSource();
        System.out.println(event.getFrame() + " on iface " + iface.getIfaceName());
    }
    
}
