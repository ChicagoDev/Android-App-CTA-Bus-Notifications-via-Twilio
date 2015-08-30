package io.chicagoan.ctawaittime;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;

/**
 * Created by bjg on 8/30/15.
 */
public class SmsStateListener extends PhoneStateListener {

    ServiceState serviceState;

    public SmsStateListener () {
        serviceState = new ServiceState();
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        this.serviceState.setState(serviceState.getState());

    }

    public int getState() {
        return serviceState.getState();
    }
}
