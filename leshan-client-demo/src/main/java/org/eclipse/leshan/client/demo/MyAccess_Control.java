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

public class MyAccess_Control extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(MyAccess_Control.class);

    private static final Random RANDOM = new Random();
    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 2, 3);

    public MyAccess_Control() {
        // notify new date each 5 second
        Timer timer = new Timer("ACL-Current Time");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fireResourcesChange(13);
            }
        }, 5000, 5000);
    }

    @Override
    public ReadResponse read(ServerIdentity identity, int resourceid) {
        LOG.info("Read on ACL Resource " + resourceid);
        switch (resourceid) {
        case 0:
            return ReadResponse.success(resourceid, getObjectID());
        case 1:
            return ReadResponse.success(resourceid, getInstanceID());
        case 2:
            return ReadResponse.success(resourceid, getACL());
        case 3:
            return ReadResponse.success(resourceid, getAccessControlOwner());
        default:
            return super.read(identity, resourceid);
        }
    }

	private int acl = 1;
	private int aco = 1;

    @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on ACL Resource " + resourceid + " value " + value);
        switch (resourceid) {
        case 2:
            setACL((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 3:
            setAccessControlOwner((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        default:
            return super.write(identity, resourceid, value);
        }
    }

    private int getObjectID() {
        return 1;
    }

    private int getInstanceID() {
        return 0;
    }

    private int getACL() {
        return acl;
    }

    private int getAccessControlOwner() {
        return aco;
    }

	private void setACL(int ac) {
		acl = ac;
	}

	private void setAccessControlOwner(int ao) {
		aco = ao;
	}

    
    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}


