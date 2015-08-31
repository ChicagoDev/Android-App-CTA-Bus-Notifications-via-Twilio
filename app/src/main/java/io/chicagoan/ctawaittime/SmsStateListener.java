/* Created by Blake Gideon. <blake at chicagoan.io>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package io.chicagoan.ctawaittime;

import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;

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
