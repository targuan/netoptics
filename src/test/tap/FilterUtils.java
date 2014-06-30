/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.tap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author targuan
 */
public class FilterUtils {

    public static long IP2Long(String dottedIP) {
        String[] addrArray = dottedIP.split("\\.");
        long num = 0;
        for (int i = 0; i < addrArray.length; i++) {
            int power = 3 - i;
            num += ((Integer.parseInt(addrArray[i]) % 256) * Math.pow(256, power));
        }
        return num;
    }
    
    public static long Mac2Long(String mac) {
        mac = mac.replace(":", "");
        Long.parseLong(mac,16);
        
        return Long.parseLong(mac,16);
    }
    
    
    
    
    public static TapFilter createFilter(String filterString) {
        TapFilter filter = new TapFilter();
        String lines[] = filterString.split("\n");

        for (String line : lines) {
            line = line.trim();
            
            String parts[] = line.split("\t| ", 2);
            String name = parts[0];
            
            if(name.equals("filter")) {
                parts = line.split("\t| ");
                parts[0] = "id";
                parts[1] = parts[2];
                name = parts[0];
            }
            
            String nameParts[] = name.split("_");
            name="";
            for(int i = 0 ; i< nameParts.length;i++) {
                name += nameParts[i].substring(0,1).toUpperCase() + nameParts[i].substring(1);
            }
            
            

            try {
                Method setter = TapFilter.class.getMethod("set"+name, String.class);
                setter.invoke(filter, parts[1].trim());
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(FilterUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(FilterUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FilterUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(FilterUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(FilterUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return filter;
    }
}
