/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.EventObject;

/**
 *
 * @author targuan
 */
public class NetworkFrameEvent extends EventObject{

    
    NetworkFrame frame;

    public NetworkFrameEvent(Object source) {
        super(source);
    }
    
    public NetworkFrameEvent(Object source,NetworkFrame frame) {
        super(source);
        this.frame = frame;

    }
    
    public NetworkFrame getFrame() {
        return frame;
    }

    
}
