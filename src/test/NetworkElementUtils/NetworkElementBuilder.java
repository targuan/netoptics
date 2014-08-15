/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.NetworkElementUtils;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import test.NetworkElement;
import test.NetworkProbe;
import test.NetworkRouter;
import test.NetworkTap;

/**
 *
 * @author targuan
 */
public class NetworkElementBuilder {

    public static NetworkElement buildElement(Element element) throws NetworkElementBuilderException {
        String type = "router";
        if (element.getAttribute("type") != null) {
            type = element.getAttributeValue("type");
        }
        if (element.getAttribute("id") == null) {
            throw new NetworkElementBuilderException("no id found for element");
        }
        String id = element.getAttributeValue("id");
        NetworkElement ne;
        switch (type) {
            case "tap":
                try {
                    ne = NetworkTapBuilder.build(id, element);
                } catch (FileNotFoundException ex) {
                    throw new NetworkElementBuilderException("configuration file not found "+id);
                }
                break;
            case "probe":
                ne = new NetworkProbe(id);
                break;
            case "router":
            default:
                ne = new NetworkRouter(id);
        }
        return ne;
    }
}
