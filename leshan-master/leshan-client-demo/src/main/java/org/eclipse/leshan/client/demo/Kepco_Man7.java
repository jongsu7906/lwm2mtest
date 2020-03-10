package org.eclipse.leshan.client.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.leshan.client.request.ServerIdentity;
import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.model.ResourceModel;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;
import org.eclipse.leshan.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kepco_Man7 extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(Kepco_Man7.class);

    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 5, 6, 7, 10, 20, 21, 25, 26, 30, 40, 41);

    @Override
    public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
        switch (resourceId) {
        case 0:
            return ReadResponse.success(resourceId, getDLMSMasterIP());
        case 1:
            return ReadResponse.success(resourceId, getDLMSMasterPortNumber());
        case 5:
            return ReadResponse.success(resourceId, getDLMSManagerIP());
        case 6:
            return ReadResponse.success(resourceId, getDLMSManagerPortNumber());
        case 7:
            return ReadResponse.success(resourceId, getDLMSManagerversion());
        case 10:
            return ReadResponse.success(resourceId, getDLMSAgentversion());
        case 20:
            return ReadResponse.success(resourceId, getLwM2MBootstrapServerIPAddress());
        case 21:
            return ReadResponse.success(resourceId, getLwM2MBootstrapServerPortNumber());
        case 25:
            return ReadResponse.success(resourceId, getLwM2MServerIPAddress());
        case 26:
            return ReadResponse.success(resourceId, getLwM2MServerPortNumber());
        case 30:
            return ReadResponse.success(resourceId, getLwM2MAgentVersion());
        case 40:
            return ReadResponse.success(resourceId, getSecurityServerIPAddress());
        case 41:
            return ReadResponse.success(resourceId, getSecurityServerPortNumber());
        default:
            return super.read(identity, resourceId);
        }
    }

    private String getDLMSMasterIP() {return "DLMS Master IP Address";}
    private int getDLMSMasterPortNumber() {return 0000;}
    private String getDLMSManagerIP() {return "DLMS Manager IP Address";}
    private int getDLMSManagerPortNumber() {return 1111;}
    private String getDLMSManagerversion() {return "1.0.1";}
    private String getDLMSAgentversion() {return "1.0.1";}
    private String getLwM2MBootstrapServerIPAddress() {return "Bootstrap IP Address";}
    private int getLwM2MBootstrapServerPortNumber() {return 0000;}
    private String getLwM2MServerIPAddress() {return "Server IP Address";}  
    private int getLwM2MServerPortNumber() {return 1111;}
    private String getLwM2MAgentVersion() {return "1.0.1";} 
    private String getSecurityServerIPAddress() {return "Security Server IP Address";}
    private int getSecurityServerPortNumber() {return 0000;}

    
     @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on Kepco_Man7 Resource " + resourceid + " value " + value);
        switch (resourceid) {
        case 0:
            setDLMSMasterIP((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 1:
            setDLMSMasterPortNumber((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 5:
            setDLMSManagerIP((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 6:
            setDLMSManagerPortNumber((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 20:
            setLwM2MBootstrapServerIPAddress((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 21:
            setLwM2MBootstrapServerPortNumber((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 25:
            setLwM2MServerIPAddress((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 26:
            setLwM2MServerPortNumber((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 40:
            setSecurityServerIPAddress((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 41:
            setSecurityServerPortNumber((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        default:
            return super.write(identity, resourceid, value);
        }
    }


    private int dlmsmasterip;
    private int dlmsmasterportnumber;
    private int dlmsmanagerip;
    private int dlmsmanagerportnumber;
    private int lwm2mbootstrapipaddress;
    private int lwm2mbootstrapportnumber;
    private int lwm2mserveripaddress;
    private int lwm2mserverportnumber;
    private int securityserveripaddress;
    private int securityserverportnumber;

    private void setDLMSMasterIP(int dmi) {dlmsmasterip = dmi;}
    private void setDLMSMasterPortNumber(int dmpn) {dlmsmasterportnumber = dmpn;}
    private void setDLMSManagerIP(int dmni) {dlmsmanagerip = dmni;}
    private void setDLMSManagerPortNumber(int dmnnpn) {dlmsmanagerportnumber = dmnnpn;}
    private void setLwM2MBootstrapServerIPAddress(int lbsia) {lwm2mbootstrapipaddress = lbsia;}
    private void setLwM2MBootstrapServerPortNumber(int lbspn) {lwm2mbootstrapportnumber = lbspn;}
    private void setLwM2MServerIPAddress(int lsia) {lwm2mserveripaddress = lsia;}
    private void setLwM2MServerPortNumber(int lspn) {lwm2mserverportnumber = lspn;}
    private void setSecurityServerIPAddress(int ssia) {securityserveripaddress = ssia;}
    private void setSecurityServerPortNumber(int sspn) {securityserverportnumber = sspn;}

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}
