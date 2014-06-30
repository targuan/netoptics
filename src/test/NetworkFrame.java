/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.tap.FilterUtils;
import test.tap.TapFilter;

/**
 *
 * @author targuan
 */
public class NetworkFrame {

    int vlan;
    int ethertype;

    long macDst;
    long macSrc;

    int ipProtocol;

    long ip4Src;
    long ip4Dst;

    int l4SrcPort;
    int l4DstPort;

    public int getVlan() {
        return vlan;
    }

    public void setVlan(int vlan) {
        this.vlan = vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = Integer.valueOf(vlan);
    }

    public int getEthertype() {
        return ethertype;
    }

    public void setEthertype(int ethertype) {
        this.ethertype = ethertype;
    }

    public void setEthertype(String ethertype) {
        this.ethertype = Integer.valueOf(ethertype);
    }

    public long getMacDst() {
        return macDst;
    }

    public void setMacDst(long macDst) {
        this.macDst = macDst;
    }

    public void setMacDst(String macDst) {
        this.macDst = FilterUtils.Mac2Long(macDst);
    }

    public long getMacSrc() {
        return macSrc;
    }

    public void setMacSrc(long macSrc) {
        this.macSrc = macSrc;
    }

    public void setMacSrc(String macSrc) {
        this.macSrc = FilterUtils.Mac2Long(macSrc);
    }

    public int getIpProtocol() {
        return ipProtocol;
    }

    public void setIpProtocol(int ipProtocol) {
        this.ipProtocol = ipProtocol;
    }

    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol = Integer.valueOf(ipProtocol);
    }

    public long getIp4Src() {
        return ip4Src;
    }

    public void setIp4Src(long ip4Src) {
        this.ip4Src = ip4Src;
    }

    public void setIp4Src(String ip4Src) {
        this.ip4Src = FilterUtils.IP2Long(ip4Src);
    }

    public long getIp4Dst() {
        return ip4Dst;
    }

    public void setIp4Dst(long ip4Dst) {
        this.ip4Dst = ip4Dst;
    }

    public void setIp4Dst(String ip4Dst) {
        this.ip4Dst = FilterUtils.IP2Long(ip4Dst);;

    }

    public int getL4SrcPort() {
        return l4SrcPort;
    }

    public void setL4SrcPort(int l4SrcPort) {
        this.l4SrcPort = l4SrcPort;
    }

    public void setL4SrcPort(String l4SrcPort) {
        this.l4SrcPort = Integer.valueOf(l4SrcPort);
    }

    public int getL4DstPort() {
        return l4DstPort;
    }

    public void setL4DstPort(int l4DstPort) {
        this.l4DstPort = l4DstPort;
    }

    public void setL4DstPort(String l4DstPort) {
        this.l4DstPort = Integer.valueOf(l4DstPort);
    }

    public NetworkFrame copy() {
        NetworkFrame frame = new NetworkFrame();
        frame.vlan = vlan;
        frame.ethertype = ethertype;

        frame.macDst = macDst;
        frame.macSrc = macSrc;

        frame.ipProtocol = ipProtocol;

        frame.ip4Src = ip4Src;
        frame.ip4Dst = ip4Dst;

        frame.l4SrcPort = l4SrcPort;
        frame.l4DstPort = l4DstPort;
        
        return frame;
    }
    
    @Override
    public String toString() {
        Field fields[] = this.getClass().getDeclaredFields();
        String value = "{";
        for(Field field : fields) {
            try {
                if(field.getLong(this) != 0) {
                    value += field.getName() + ":" + field.get(this);
                }
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TapFilter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TapFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        value += "}";
        
        return value;
    }

}
