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

public class MyConnectivity_Statistics extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(MyConnectivity_Statistics.class);
    private int collectionperiod = 10;

    private static final Random RANDOM = new Random();
    private static final List<Integer> supportedResources = Arrays.asList(2, 3, 4, 5, 6, 7, 8);


    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        LOG.info("Read on Connectivity_Statistics Resource " + resourceid);
        switch (resourceid) {
        case 2:
            return ReadResponse.success(resourceid, getTxData());
        case 3:
            return ReadResponse.success(resourceid, getRxData());
        case 4:
            return ReadResponse.success(resourceid, getMaxMessageSize());
        case 5:
            return ReadResponse.success(resourceid, getAverageMessageSize());
        case 8:
            return ReadResponse.success(resourceid, getCollectionPeriod());
        default:
            return super.read(identity, resourceid);
        }
    }

    private int getTxData() {
        return 10;
    }

    private int getRxData() {
        return 10;
    }

    private int getMaxMessageSize() {
        return 10;
    }

    private int getAverageMessageSize() {
        return 10;
    }

    private int getCollectionPeriod() {
        return collectionperiod;
    }

    @Override
    public ExecuteResponse execute(ServerIdentity identity, int resourceid, String params) {
        LOG.info("Execute on Connectivity_Statistics resource " + resourceid);
        if (params != null && params.length() != 0)
            System.out.println("\t params " + params);
        switch (resourceid) {
		case 6:
			return ExecuteResponse.success();
	    case 7:
		    return ExecuteResponse.success();
	    default:
	            return super.execute(identity, resourceid, params);
	}		
    }

    @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on Connectivity_Statistics Resource " + resourceid + " value " + value);
        if (resourceid == 8) {
            setCollectionPeriod((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        }
        else return super.write(identity, resourceid, value);
    }

    private void setCollectionPeriod(int cp) {
        collectionperiod = cp;
    }

   
    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}


