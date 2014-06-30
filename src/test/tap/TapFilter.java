/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.tap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.NetworkFrame;

/**
 *
 * @author targuan
 */
public class TapFilter {

    public enum Action {

        redir, drop
    };
    int id;
    
    String description = "";
    String name = "";
    
    Action action = Action.redir;
    
    List<Integer> inPorts = new ArrayList<>();
    List<Integer> redirPorts = new ArrayList<>();

    long ip4Src;
    long ip4SrcMask;
    long ip4Dst;
    long ip4DstMask;

    /*String ip6Src;
     String ip6SrcMask;
     String ip6Dst;
     String ip6DstMask;*/
    int l4SrcPort;
    int l4SrcPortMask;
    int l4DstPort;
    int l4DstPortMask;

    long macDst;
    long macDstMask;
    long macSrc;
    long macSrcMask;

    int vlan;
    int vlanMask;

    int ethertype;
    int ethertypeMask;

    int ipProtocol;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setAction(String action) {
        if (action.equals("redir")) {
            this.action = Action.redir;
        } else if (action.equals("drop")) {
            this.action = Action.drop;
        }
    }

    public List<Integer> getInPorts() {
        return inPorts;
    }

    public void setInPorts(List<Integer> inPorts) {
        this.inPorts = inPorts;
    }

    public void setInPorts(String ports) {
        String parts[] = ports.split(",");

        for (String part : parts) {
            if (part.contains("-")) {
                String subparts[] = part.split("-");
                for(int i = Integer.valueOf(subparts[0]);i<=Integer.valueOf(subparts[1]);i++) {
                    inPorts.add(i);
                }
            } else {
                inPorts.add(Integer.valueOf(part));
            }
        }
    }

    public List<Integer> getRedirPorts() {
        return redirPorts;
    }

    public void setRedirPorts(List<Integer> redirPorts) {
        this.redirPorts = redirPorts;
    }
    
    public void setRedirPorts(String ports) {
        String parts[] = ports.split(",");

        for (String part : parts) {
            if (part.contains("-")) {
                String subparts[] = part.split("-");
                for(int i = Integer.valueOf(subparts[0]);i<=Integer.valueOf(subparts[1]);i++) {
                    redirPorts.add(i);
                }
            } else {
                redirPorts.add(Integer.valueOf(part));
            }
        }
    }

    public long getIp4Src() {
        return ip4Src;
    }

    public void setIp4Src(long ip4Src) {
        this.ip4Src = ip4Src;
        
        if(ip4SrcMask == 0) {
            setIp4SrcMask("255.255.255.255");
        }
    }
    public void setIp4Src(String ip4Src) {
        setIp4Src(FilterUtils.IP2Long(ip4Src));
    }
    

    public long getIp4SrcMask() {
        return ip4SrcMask;
    }

    public void setIp4SrcMask(long ip4SrcMask) {
        this.ip4SrcMask = ip4SrcMask;
    }
    public void setIp4SrcMask(String ip4SrcMask) {
        setIp4SrcMask(FilterUtils.IP2Long(ip4SrcMask));
    }

    public long getIp4Dst() {
        return ip4Dst;
    }

    public void setIp4Dst(long ip4Dst) {
        this.ip4Dst = ip4Dst;
        
        if(ip4DstMask == 0) {
            setIp4DstMask("255.255.255.255");
        }
    }
    public void setIp4Dst(String ip4Dst) {
        setIp4Dst(FilterUtils.IP2Long(ip4Dst));
    }

    public long getIp4DstMask() {
        return ip4DstMask;
    }

    public void setIp4DstMask(long ip4DstMask) {
        this.ip4DstMask = ip4DstMask;
    }
    public void setIp4DstMask(String ip4DstMask) {
        this.ip4DstMask = FilterUtils.IP2Long(ip4DstMask);;
    }

    public int getL4SrcPort() {
        return l4SrcPort;
    }

    public void setL4SrcPort(int l4SrcPort) {
        this.l4SrcPort = l4SrcPort;
        
        if(l4SrcPortMask == 0) {
            setL4SrcPortMask(0xffff);
        }
    }
    public void setL4SrcPort(String l4SrcPort) {
        setL4SrcPort(Integer.valueOf(l4SrcPort));
    }

    public int getL4SrcPortMask() {
        return l4SrcPortMask;
    }

    public void setL4SrcPortMask(int l4SrcPortMask) {
        this.l4SrcPortMask = l4SrcPortMask;
    }
    public void setL4SrcPortMask(String l4SrcPortMask) {
        this.l4SrcPortMask = Integer.valueOf(l4SrcPortMask);
    }

    public int getL4DstPort() {
        return l4DstPort;
    }

    public void setL4DstPort(int l4DstPort) {
        this.l4DstPort = l4DstPort;
        
        if(l4DstPortMask == 0) {
            setL4DstPortMask(0xffff);
        }
    }
    public void setL4DstPort(String l4DstPort) {
        setL4DstPort(Integer.valueOf(l4DstPort));
    }

    public int getL4DstPortMask() {
        return l4DstPortMask;
    }

    public void setL4DstPortMask(int l4DstPortMask) {
        this.l4DstPortMask = l4DstPortMask;
    }
    public void setL4DstPortMask(String l4DstPortMask) {
        this.l4DstPortMask = Integer.valueOf(l4DstPortMask);
    }

    public long getMacDst() {
        return macDst;
    }

    public void setMacDst(long macDst) {
        this.macDst = macDst;
        
        if(macDstMask == 0) {
            setMacDstMask(0xffffffffffffL);
        }
    }
    public void setMacDst(String macDst) {
        setMacDst(FilterUtils.Mac2Long(macDst));
    }

    public long getMacDstMask() {
        return macDstMask;
    }

    public void setMacDstMask(long macDstMask) {
        this.macDstMask = macDstMask;
    }
    public void setMacDstMask(String macDstMask) {
        this.macDstMask = FilterUtils.Mac2Long(macDstMask);
    }

    public long getMacSrc() {
        return macSrc;
    }

    public void setMacSrc(long macSrc) {
        this.macSrc = macSrc;
        
        if(macSrcMask == 0) {
            setMacSrcMask(0xffffffffffffL);
        }
    }
    public void setMacSrc(String macSrc) {
        setMacSrc(FilterUtils.Mac2Long(macSrc));
    }

    public long getMacSrcMask() {
        return macSrcMask;
    }

    public void setMacSrcMask(long macSrcMask) {
        this.macSrcMask = macSrcMask;
    }
    public void setMacSrcMask(String macSrcMask) {
        this.macSrcMask = FilterUtils.Mac2Long(macSrcMask);
    }

    public int getVlan() {
        return vlan;
    }

    public void setVlan(int vlan) {
        this.vlan = vlan;
        
        if(vlanMask == 0) {
            setVlanMask(0xffff);
        }
    }
    public void setVlan(String vlan) {
        setVlan(Integer.valueOf(vlan));
    }

    public int getVlanMask() {
        return vlanMask;
    }

    public void setVlanMask(int vlanMask) {
        this.vlanMask = vlanMask;
    }
    public void setVlanMask(String vlanMask) {
        this.vlanMask = Integer.valueOf(vlanMask);
    }

    public int getEthertype() {
        return ethertype;
    }

    public void setEthertype(int ethertype) {
        this.ethertype = ethertype;
        
        if(ethertypeMask == 0) {
            setEthertypeMask(0xffff);
        }
    }
    public void setEthertype(String ethertype) {
        setEthertype(Integer.valueOf(ethertype));
    }

    public int getEthertypeMask() {
        return ethertypeMask;
    }

    public void setEthertypeMask(int ethertypeMask) {
        this.ethertypeMask = ethertypeMask;
    }
    public void setEthertypeMask(String ethertypeMask) {
        this.ethertypeMask =  Integer.valueOf(ethertypeMask);
    }

    public int getIpProtocol() {
        return ipProtocol;
    }

    public void setIpProtocol(int ipProtocol) {
        this.ipProtocol = ipProtocol;
    }
    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol =  Integer.valueOf(ipProtocol);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        Field fields[] = this.getClass().getDeclaredFields();
        String value = "{";
        for(Field field : fields) {
            try {
                value += field.getName() + ":" + field.get(this) + ",\n";
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TapFilter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TapFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return value;
    }
    
    
    public boolean match(NetworkFrame frame) {
        /**
         * L2
         */
        if((frame.getVlan() & getVlanMask()) != getVlan()) {
            return false;
        }
        if((frame.getMacDst() & getMacDstMask()) != getMacDst()) {
            return false;
        }
        if((frame.getMacSrc()& getMacSrcMask()) != getMacSrc()) {
            return false;
        }
        if((frame.getEthertype() & getEthertypeMask()) != getEthertype()) {
            return false;
        }
        
        /**
         * L3
         */
        if(frame.getIpProtocol() != getIpProtocol()) {
            return false;
        }
        
        if((frame.getIp4Dst() & getIp4DstMask()) != getIp4Dst()) {
            return false;
        }
        
        if((frame.getIp4Src() & getIp4SrcMask()) != getIp4Src()) {
            return false;
        }
        
        /**
         * L4
         */
        if((frame.getL4DstPort() & getL4DstPortMask()) != getL4DstPort()) {
            return false;
        }
        if((frame.getL4SrcPort() & getL4SrcPortMask()) != getL4SrcPort()) {
            return false;
        }
        return true;
    }
    
    public NetworkFrame partialMatch(NetworkFrame originalFrame) {
        NetworkFrame frame = originalFrame.copy();
        /**
         * L2
         */
        if(frame.getVlan() == 0 ) {
            frame.setVlan(getVlan());
        }
        
        if(frame.getMacDst() == 0 ) {
            frame.setMacDst(getMacDst());
        }
        
        if(frame.getMacSrc() == 0 ) {
            frame.setMacSrc(getMacSrc());
        }
        
        if(frame.getEthertype() == 0 ) {
            frame.setEthertype(getEthertype());
        }
        
        
        /**
         * L3
         */
        if(frame.getIpProtocol() == 0 ) {
            frame.setIpProtocol(getIpProtocol());
        }
        
        if(frame.getIp4Dst() == 0 ) {
            frame.setIp4Dst(getIp4Dst());
        }
        
        if(frame.getIp4Src() == 0) {
            frame.setIp4Src(getIp4Src());
        }
        
        /**
         * L4
         */
        if(frame.getL4DstPort() == 0) {
            frame.setL4DstPort(getL4DstPort());
        }
        
        if(frame.getL4SrcPort() == 0 ) {
            frame.setL4SrcPort(getL4SrcPort());
        }
        
        if(match(frame)) {
            return frame;
        }
        
        return null;
        
    }
    
    

    
}
