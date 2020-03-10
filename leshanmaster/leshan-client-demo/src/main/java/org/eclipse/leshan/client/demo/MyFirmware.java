package org.eclipse.leshan.client.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.leshan.client.request.ServerIdentity;
import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.model.ResourceModel.Type;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirmware extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(MyFirmware.class);

    private static final Random RANDOM = new Random();
    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8, 9);
    private String packageuri = "coap://[IPv6]/firmware";;

    public MyFirmware() {
        // notify new date each 5 second
        Timer timer = new Timer("Firmware Time");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fireResourcesChange(13);
            }
        }, 5000, 5000);
    }

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        LOG.info("Read on Firmware Resource " + resourceid);
        switch (resourceid) {
        case 1:
            return ReadResponse.success(resourceid, getPackageURI());
        case 3:
            return ReadResponse.success(resourceid, getState());
        case 5: 
            return ReadResponse.success(resourceid, getUpdateResult());
        case 6:
            return ReadResponse.success(resourceid, getPackageName());
        case 7:
            return ReadResponse.success(resourceid, getPackageVersion());
        case 8:
            return ReadResponse.success(resourceid, getFirmwareUpdateProtocolSupport());
        case 9:
            return ReadResponse.success(resourceid, getFirmwareUpdateDeliveryMethod());
        default:
            return super.read(identity, resourceid);
        }
    }
    private String getPackageURI() {
        return packageuri;
    }
    private int getState() {
        return 0; //0-3
    }
    private int getUpdateResult() {
        return 0; //0-9
    }
    private String getPackageName() {
        return "lwm2m_agent";
    }
    private String getPackageVersion() {
        return "1.0.2";
    }
    private int getFirmwareUpdateProtocolSupport() {
        return 0; //0-3
    }
    private int getFirmwareUpdateDeliveryMethod() {
        return 0; //0-2
    }



    @Override
    public ExecuteResponse execute(ServerIdentity identity, int resourceid, String params) {
        LOG.info("Execute on Firmware resource " + resourceid);
        if (params != null && params.length() != 0)
            System.out.println("\t params " + params);
        if (resourceid == 2) {
        }
        return ExecuteResponse.success();
    }

    @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on Firmware Resource " + resourceid + " value " + value);
        switch (resourceid) {
        case 0:
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 1:
            setPackageURI((String) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        default:
            return super.write(identity, resourceid, value);
        }
    }

    private void setPackageURI(String puri) {
        packageuri = puri;
    }


    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}
