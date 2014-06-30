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
public class NetworkBus implements NetworkFrameListener {

    List<NetworkFrameListener> listeners;
    List<NetworkInterface> ifaces;

    public NetworkBus() {
        listeners = new ArrayList<>();
        ifaces = new ArrayList<>();
    }

    public void addIface(NetworkInterface iface) {
        if (!ifaces.contains(iface)) {
            ifaces.add(iface);
            listeners.add(iface);
            iface.connectTo(this);
        }
    }

    public void addNetworkFrameListener(NetworkFrameListener listener) {
        listeners.add(listener);
    }

    @Override
    public void receiveFrame(NetworkFrameEvent event) {
        NetworkFrameEvent sendEvent = new NetworkFrameEvent(this, event.getFrame());
        for (NetworkFrameListener listener : listeners) {
            if (listener != event.getSource()) {
                listener.receiveFrame(sendEvent);
            }
        }
    }
    
    public static NetworkBus connect(NetworkInterface a, NetworkInterface b) {
        NetworkBus bus = new NetworkBus();
        bus.addIface(a);
        bus.addIface(b);
        return bus;
    }
}
