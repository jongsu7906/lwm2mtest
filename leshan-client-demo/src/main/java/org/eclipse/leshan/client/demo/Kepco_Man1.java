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

public class Kepco_Man1 extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(Kepco_Man1.class);


    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 5, 6, 7, 8, 10, 11, 15, 16, 20, 21, 22, 25, 26, 27, 30, 31, 35, 36, 37, 40, 41, 42, 43, 44, 47);

    @Override
    public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
        switch (resourceId) {
        case 0:
            return ReadResponse.success(resourceId, getSystemTitle());
        case 1:
            return ReadResponse.success(resourceId, getAMIname());
        case 5:
            return ReadResponse.success(resourceId, getOSname());
        case 6:
            return ReadResponse.success(resourceId, getOSversion());
        case 7:
            return ReadResponse.success(resourceId, getCPUChip());
        case 8:
            return ReadResponse.success(resourceId, getCPUChipname());
        case 10:
            return ReadResponse.success(resourceId, getCPUUse());
        case 11:
            return ReadResponse.success(resourceId, getRAMUse());
        case 20:
            return ReadResponse.success(resourceId, getFWUpdateType());
        case 21:
            return ReadResponse.success(resourceId, getScheduleUpdateTime());
        case 25:
            return ReadResponse.success(resourceId, getFWDownloadTime());
        case 26:
            return ReadResponse.success(resourceId, getFWUpdateTime());
        case 27:
            return ReadResponse.success(resourceId, getFWVersion());
        case 30:
            return ReadResponse.success(resourceId, getPowerOutageTime());
        case 31:
            return ReadResponse.success(resourceId, getRestoreTime());
        case 36:
            return ReadResponse.success(resourceId, getSelfResetPeriod());
        case 37:
            return ReadResponse.success(resourceId, getSelfResetTime());
        case 40:
            return ReadResponse.success(resourceId, getDLBPeriod());
        case 43:
            return ReadResponse.success(resourceId, getLBTestInputData());
        case 44:
            return ReadResponse.success(resourceId, getLBTestOutputData());
        default:
            return super.read(identity, resourceId);
        }
    }

    private String getSystemTitle() {return "0";}  //Opaque
    private int getAMIname() {return 1;}
    private String getOSname() {return "OS Name";}
    private String getOSversion() {return "OS Version";}
    private String getCPUChip() {return "14";}
    private String getCPUChipname() {return "CPU Model Name";}
    private int getCPUUse() {return 10;}
    private int getRAMUse() {return 30;}
    private int getFWUpdateType() {return fwupdatetype;}
    private Date getScheduleUpdateTime() {return scheduleupdatetime;}
    private Date getFWDownloadTime() {return new Date();}
    private Date getFWUpdateTime() {return new Date();}
    private String getFWVersion() {return "F/W Version";}
    private Date getPowerOutageTime() {return new Date();}
    private Date getRestoreTime() {return new Date();}
    private int getSelfResetPeriod() {return selfresetperiod;}
    private int getSelfResetTime() {return selfresettime;}
    private int getDLBPeriod() {return dlbperiod;}
    private String getLBTestInputData() {return lbtestinputdate;}
    private String getLBTestOutputData() {return "AMI 485 DLB TEST";}

     @Override
    public WriteResponse write(ServerIdentity identity, int resourceid, LwM2mResource value) {
        LOG.info("Write on Kepco_Man1 Resource " + resourceid + " value " + value);
        switch (resourceid) {
        case 20:
            setFWUpdateType((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 21:
            setScheduleUpdateTime((Date) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 36:
            setSelfResetPeriod((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 37:
            setSelfResetTime((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 40:
            setDLBPeriod((int) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        case 43:
            setLBTestInputData((String) value.getValue());
            fireResourcesChange(resourceid);
            return WriteResponse.success();
        default:
            return super.write(identity, resourceid, value);
        }
    }

    private int fwupdatetype = 0;
    private Date scheduleupdatetime = new Date();
    private int selfresetperiod = 1;
    private int selfresettime = 1430;
    private int dlbperiod = 60;
    private String lbtestinputdate = "AMI 485 DLB TEST";

    private void setFWUpdateType(int fwut) {fwupdatetype = fwut;}
    private void setScheduleUpdateTime(Date sut) {scheduleupdatetime = sut;}
    private void setSelfResetPeriod(int srp) {selfresetperiod = srp;}
    private void setSelfResetTime(int srt) {selfresettime = srt;}
    private void setDLBPeriod(int dlbp) {dlbperiod = dlbp;}
    private void setLBTestInputData(String lbtid) {lbtestinputdate = lbtid;}

    @Override
    public synchronized ExecuteResponse execute(ServerIdentity identity, int resourceId, String params) {
        switch (resourceId) {
        case 10:
            CPUUse();
            return ExecuteResponse.success();
        case 11:
            RAMUse();
            return ExecuteResponse.success();
        case 15:
            FWFactoryreboot();
            return ExecuteResponse.success();
        case 16:
            FWLastreboot();
            return ExecuteResponse.success();
        case 22:
            RunCommandUpdate();
            return ExecuteResponse.success();
        case 35:
            HWWatchDogTest();
            return ExecuteResponse.success();
        case 41:
            DLBStart();
            return ExecuteResponse.success();
        case 42:
            DLBStop();
            return ExecuteResponse.success();
        case 47:
            LEDTest();
            return ExecuteResponse.success();
        default:
            return super.execute(identity, resourceId, params);
        }
    }

    private void CPUUse() {//
      }
    private void RAMUse() {//
      }
    private void FWFactoryreboot() {//
      }
    private void FWLastreboot() {//
      }
    private void RunCommandUpdate() {//
      }
    private void HWWatchDogTest() {//
      }
    private void DLBStart() {//
      }
    private void DLBStop() {//
      }
    private void LEDTest() {//
      }

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}

