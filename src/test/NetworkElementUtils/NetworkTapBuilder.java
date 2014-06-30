/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.NetworkElementUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdom2.Element;
import test.NetworkInterface;
import test.NetworkTap;
import test.tap.FilterUtils;

/**
 *
 * @author targuan
 */
public class NetworkTapBuilder {

    public static NetworkTap createFromFile(File configurationFile) throws FileNotFoundException {
        NetworkTap tap = new NetworkTap(null);

        BufferedReader reader = new BufferedReader(new FileReader(configurationFile));
        String line;
        String rule = "";
        boolean inRule = false;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("filter rule")) {
                    rule = line;
                    inRule = true;
                } else if (inRule && line.startsWith("!")) {
                    inRule = false;
                    tap.addFilter(FilterUtils.createFilter(rule));
                } else if (inRule) {
                    rule += line + "\n";
                } else if (line.startsWith("port") && line.contains("name")) {
                    Pattern pat = Pattern.compile("port (\\d+) name *\"([^\"]+)\"");
                    Matcher mat = pat.matcher(line);
                    if (mat.matches()) {
                        NetworkInterface iface = new NetworkInterface(tap, mat.group(1));
                        iface.setIfaceDescription(mat.group(2));
                        tap.addIface(iface);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkTap.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tap;
    }

    public static NetworkTap build(String name, Element element) throws FileNotFoundException {
        String filename = element.getAttributeValue("configurationfile");

        NetworkTap tap = createFromFile(new File(filename));
        tap.setName(name);
        
        return tap;
    }
}
