package org.eclipse.leshan.client.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.eclipse.leshan.client.request.ServerIdentity;
import org.eclipse.leshan.client.resource.BaseInstanceEnabler;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.response.ExecuteResponse;
import org.eclipse.leshan.core.response.ReadResponse;
import org.eclipse.leshan.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kepco_Man3 extends BaseInstanceEnabler {

    private static final Logger LOG = LoggerFactory.getLogger(Kepco_Man3.class);

    private static final List<Integer> supportedResources = Arrays.asList(0, 1, 2, 3, 4, 9, 10, 15, 17, 20, 21);

    @Override
    public synchronized ReadResponse read(ServerIdentity identity, int resourceId) {
        switch (resourceId) {
        case 0:
            return ReadResponse.success(resourceId, getWanCode());
        case 1:
            return ReadResponse.success(resourceId, getCommunicationCode());
        case 2:
            return ReadResponse.success(resourceId, getTelecommunicationsCompany());
        case 3:
            return ReadResponse.success(resourceId, getPhoneNumber());
        case 4:
            return ReadResponse.success(resourceId, getIpAddress());
        case 9:
            return ReadResponse.success(resourceId, getModuleCompany());
        case 10:
            return ReadResponse.success(resourceId, getTxPower());
        case 17:
            return ReadResponse.success(resourceId, getCoapPingResult());
        case 21:
            return ReadResponse.success(resourceId, getThroughPutTestResult());
        default:
            return super.read(identity, resourceId);
        }
    }

    private int wancode = 0;
    private int communicationcode = 0;
    private int telecommunicationscompany = 0;
    private String phonenumber = "01055559999";
    private String ipaddress = "IPv6";
    private String modulecompany = "14";
    private int txpower = 0;
    private String coappingresult = "1230ms";
    private String throughputtestresult = "12/10";

    private int getWanCode() {return wancode;}
    private int getCommunicationCode() {return communicationcode;}
    private int getTelecommunicationsCompany() {return telecommunicationscompany;}
    private String getPhoneNumber() {return phonenumber;}
    private String getIpAddress() {return ipaddress;}
    private String getModuleCompany() {return modulecompany;}
    private int getTxPower() {return txpower;}
    private String getCoapPingResult() {return coappingresult;}
    private String getThroughPutTestResult() {return throughputtestresult;}

    @Override
    public synchronized ExecuteResponse execute(ServerIdentity identity, int resourceId, String params) {
        switch (resourceId) {
        case 15:
            CoAPPingStart();
            return ExecuteResponse.success();
        case 20:
            ThroughputTestUPStart();
            return ExecuteResponse.success();
        default:
            return super.execute(identity, resourceId, params);
        }
    }

    private void CoAPPingStart() {//
      }
    private void ThroughputTestUPStart() {//
      }

    @Override
    public List<Integer> getAvailableResourceIds(ObjectModel model) {
        return supportedResources;
    }
}
