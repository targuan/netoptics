/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import test.NetworkElementUtils.NetworkElementBuilder;

/**
 *
 * @author targuan
 */
public class NetworkTopology {
    List<NetworkElement> elements;
    List<NetworkBus> buses;

    public NetworkTopology() {
        elements = new ArrayList<>();
        buses = new ArrayList<>();
    }
    
    public void addNetworkElement(NetworkElement element) {
        elements.add(element);
    } 
    
    public NetworkElement getNetworkElement(String id) {
        for(NetworkElement e : elements) {
            if(e.getName().equals(id)) {
                return e;
            }
        }
        return null;
    }
    
    public List<NetworkElement> getNetworkElementList() {
        return elements;
    }
    
    public void addBus(NetworkBus bus) {
        buses.add(bus);
    }
    
    public List<NetworkBus> getNetworkBusList() {
        return buses;
    }
    
    public static NetworkTopology createNetworkTopology(File file) {
        NetworkTopology topology = new NetworkTopology();
        SAXBuilder sax = new SAXBuilder();
        
        try {
            Document document = sax.build(file);
            
            Element element = document.getRootElement();
            
            List<Element> elements = element.getChildren("element");
            
            for(Element e : elements) {
                NetworkElement ne = NetworkElementBuilder.buildElement(e);
                topology.addNetworkElement(ne);
            }
        } catch (JDOMException ex) {
            Logger.getLogger(NetworkTopology.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NetworkTopology.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NetworkTopology.class.getName()).log(Level.SEVERE, null, ex);
        }
        return topology;
    }
}
