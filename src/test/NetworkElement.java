/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author targuan
 */
public class NetworkElement implements NetworkFrameListener{
    String name;
    List<NetworkInterface> ifaces;

    public NetworkElement(String name) {
        ifaces = new ArrayList<>();
        this.name = name;
    }
    
    public NetworkElement(String name, int ifCount) {
        this(name);
        for(int i=0;i<ifCount;i++) {
            NetworkInterface iface = new NetworkInterface(this,String.valueOf(i));
            addIface(iface);
        }
    }
    
    public void addIface(NetworkInterface iface) {
        ifaces.add(iface);
        iface.addNetworkFrameListener(this);
    }
    
    public NetworkInterface getIface(int ifIndex) {
        return ifaces.get(ifIndex);
    }
    
    public boolean  hasIface(String name) {
        for(NetworkInterface iface : ifaces) {
            if(iface.getIfaceName().equals(name)) {
                return true;
            }
        }
        
        return false;
    }
    
    public NetworkInterface getIface (String name) {
        for(NetworkInterface iface : ifaces) {
            if(iface.getIfaceName().equals(name)) {
                return iface;
            }
        }
        
        return null;
    }

    @Override
    public void receiveFrame(NetworkFrameEvent event) {
        NetworkInterface iface = (NetworkInterface) event.getSource();
        System.out.println(name + " on iface " + iface.getIfaceName());
    }
    
    public void sendFrame(int ifIndex,NetworkFrame frame) {
        ifaces.get(ifIndex).sendFrame(frame);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
