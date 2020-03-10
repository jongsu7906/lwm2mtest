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
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.core.response.WriteResponse;
import org.eclipse.leshan.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kepco_Man5 extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(Kepco_Man5.class);

    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 2, 5, 6, 10, 11, 12, 13, 20, 21, 22, 30, 31);

    @Override
    public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
        switch (resourceId) {
        case 0:
            return ReadResponse.success(resourceId, getModuleManufacturer());
        case 1:
            return ReadResponse.success(resourceId, getModuleModelNumber());
        case 2:
            return ReadResponse.success(resourceId, getModuleSerialNumber());
        case 5:
            return ReadResponse.success(resourceId, getModuleVersion());
        case 6:
            return ReadResponse.success(resourceId, getSecurityProgramVersion());
        case 10:
            return ReadResponse.success(resourceId, getModuleTestIdentification());
        case 13:
            return ReadResponse.success(resourceId, getModuleCheckResult());
        case 20:
            return ReadResponse.success(resourceId, getHealthPeriod());
        case 22:
            return ReadResponse.success(resourceId, getHealthResponse());
        case 30:
            return ReadResponse.success(resourceId, getSecureBootSuccess());
        case 31:
            return ReadResponse.success(resourceId, getSecureBootTime());
        default:
            return super.read(identity, resourceId);
        }
    }

    private String getModuleManufacturer() {return "14";}
    private String getModuleModelNumber() {return "MODEL NUMBER";}
    private String getModuleSerialNumber() {return "SERIAL NUMBER";}
    private String getModuleVersion() {return "1.0.1";}
    private String getSecurityProgramVersion() {return "1.0.1";}
    private int getModuleTestIdentification() {return 1;}
    private int getModuleCheckResult() {return 4;}
    private int getHealthPeriod() {return 0;}
    private String getHealthResponse() {return "";}  //opaque
    private boolean getSecureBootSuccess() {return true;}
    private Date getSecureBootTime() {return new Date();}  //Time

     @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on Kepco_Man5 Resource " + resourceid + " value " + value);
        switch (resourceid) {
        case 10:
            setModuleTestIdentification((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 20:
            setHealthPeriod((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        default:
            return super.write(identity, resourceid, value);
        }
    }

    private int moduletestidentification;
    private int healthperiod;

    private void setModuleTestIdentification(int mti) {moduletestidentification = mti;}
    private void setHealthPeriod(int hp) {healthperiod = hp;}

    @Override
    public synchronized ExecuteResponse execute(ServerIdentity identity, int resourceId, String params) {
        switch (resourceId) {
        case 11:
            ModuleTestStart();
            return ExecuteResponse.success();
        case 12:
            ModuleTestStop();
            return ExecuteResponse.success();
        case 21:
            HealthRequest();
            return ExecuteResponse.success();
        default:
            return super.execute(identity, resourceId, params);
        }
    }



    private void ModuleTestStart() {//
      }
    private void ModuleTestStop() {//
      }
    private void HealthRequest() {//
      }

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}

