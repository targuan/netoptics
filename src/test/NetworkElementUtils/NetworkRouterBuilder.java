/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.NetworkElementUtils;

import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Element;
import test.NetworkInterface;
import test.NetworkRouter;

/**
 *
 * @author agrapartth
 */
public class NetworkRouterBuilder {

    public static NetworkRouter build(Element e) throws Exception {

        Attribute nameAttribute = e.getAttribute("id");
        if (nameAttribute == null) {
            throw new Exception("id not set on element");
        }

        String name = nameAttribute.getValue();
        NetworkRouter nr = new NetworkRouter(name);

        List<Element> ports = e.getChildren("port");

        for (Element p : ports) {
            nr.addIface(new NetworkInterface(nr, p.getChild("name").getValue()));
        }

        return nr;
    }
}
