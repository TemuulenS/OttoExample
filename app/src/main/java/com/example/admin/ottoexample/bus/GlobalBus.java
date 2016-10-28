package com.example.admin.ottoexample.bus;

import com.squareup.otto.Bus;

/**
 * Created by admin on 10/27/2016.
 */

public class GlobalBus {
    private static Bus sBus;
    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }

}
