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
public class NetworkRouter extends NetworkElement{

    public NetworkRouter(String name) {
        super(name);
    }
    
    public void monitor(NetworkInterface source, NetworkInterface target) {
        source.addNetworkFrameListener(target);
    }
    
    @Override
    public NetworkInterface getIface (String name) {
        for(NetworkInterface iface : ifaces) {
            if(iface.getIfaceName().equals(name)) {
                return iface;
            }
        }
        
        NetworkInterface ni = new NetworkInterface(this, name);
        addIface(ni);
        
        return ni;
    }
}
