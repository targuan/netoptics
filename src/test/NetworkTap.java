/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.ArrayList;
import java.util.List;
import test.tap.TapFilter;

/**
 *
 * @author targuan
 */
public class NetworkTap extends NetworkElement{
    List<TapFilter> filters;
    public NetworkTap(String name) {
        super(name);
        
        filters = new ArrayList<>();
    }
    
    public void addFilter(TapFilter filter) {
        filters.add(filter);
    }
    
    public TapFilter getFilter(int i) {
        return filters.get(i);
    }
    
    public int getFilterCount() {
        return filters.size();
    }
    
    
    
    
    @Override
    public void receiveFrame(NetworkFrameEvent event) {
        NetworkInterface iface = (NetworkInterface) event.getSource();
        System.out.println("rcv:" +name + " on iface " + iface.getIfaceName());
        
        
        for(int i = 0;i<getFilterCount();i++) {
                if(getFilter(i).getInPorts().contains(Integer.valueOf(iface.getIfaceName())) && getFilter(i).match(event.getFrame())) {
                    
                    for(int fi = 0 ; fi<getFilter(i).getRedirPorts().size();fi++) {
                        NetworkInterface outIface = getIface(String.valueOf(getFilter(i).getRedirPorts().get(fi)));
                        System.out.println("send:" +name + " on iface " + outIface.getIfaceName());
                        outIface.sendFrame(event.getFrame());
                    }
                    getFilter(i);
                    break;
                }
            }
    }
    
}
