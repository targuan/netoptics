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
public class NetworkInterface  implements NetworkFrameListener{
    List<NetworkFrameListener> listeners;
    
    NetworkElement parent;
    
    String ifaceName;
    
    String ifaceDescription = "";

    public NetworkInterface(NetworkElement parent, String ifaceName) {
        listeners = new ArrayList<>();
        
        this.parent = parent;
        this.ifaceName = ifaceName;
    }
    
    public String getIfaceName() {
        return ifaceName;
    }
    
    public void addNetworkFrameListener(NetworkFrameListener listener) {
        listeners.add(listener);
    }
    
    public void connectTo(NetworkBus bus) {
        addNetworkFrameListener(bus);
    }
    
    public void sendFrame(NetworkFrame frame) {
        for(NetworkFrameListener listener: listeners) {
            listener.receiveFrame(new NetworkFrameEvent(this, frame));
        }
    }
    
    @Override
    public void receiveFrame(NetworkFrameEvent event) {
        for(NetworkFrameListener listener: listeners) {
            if(listener != event.getSource()) {
                listener.receiveFrame(new NetworkFrameEvent(this, event.getFrame()));
            }
        }
    }

    public void setIfaceDescription(String ifaceDescription) {
        this.ifaceDescription = ifaceDescription;
    }

    public String getIfaceDescription() {
        return ifaceDescription;
    }
    
    
}
