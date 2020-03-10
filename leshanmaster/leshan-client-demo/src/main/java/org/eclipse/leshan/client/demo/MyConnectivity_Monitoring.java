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

public class MyConnectivity_Monitoring extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(MyConnectivity_Monitoring.class);

    private static final Random RANDOM = new Random();
    private static final List<Integer> supportedResources = Arrays.asList(2, 3, 4, 8, 9, 10, 11);

    public MyConnectivity_Monitoring() {
        // notify new date each 5 second
        Timer timer = new Timer("Connectivity monitoring Time");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fireResourcesChange(13);
            }
        }, 5000, 5000);
    }

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        LOG.info("Read on Connectivity monitoring Resource " + resourceid);
        switch (resourceid) {
        case 2:
            return ReadResponse.success(resourceid, getRadioSignalStrength());
        case 3:
            return ReadResponse.success(resourceid, getLinkQuality());
		case 4:
            return ReadResponse.success(resourceid, getIPAddresses());
        case 8:
            return ReadResponse.success(resourceid, getCellID());
        case 9:
            return ReadResponse.success(resourceid, getSMNC());
        case 10:
            return ReadResponse.success(resourceid, getSMCC());
        case 11:
            return ReadResponse.success(resourceid, getSignalSNR());
        default:
            return super.read(identity, resourceid);
        }
    }

	private int getRadioSignalStrength() {
		return 86400;
	}

	private int getLinkQuality() {
		return 86400;
	}

	private String getIPAddresses() {
		return "192.168.0.00";
	}

	private int getCellID() {
		return 28;
	}

	private int getSMNC() {
		return 03;
	}

	private int getSMCC() {
		return 450;
	}

	private int getSignalSNR() {
		return 60;
	}
  

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}


