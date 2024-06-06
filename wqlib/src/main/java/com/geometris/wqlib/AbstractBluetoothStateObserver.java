package com.geometris.wqlib;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Override this Broadcast receiver to override onE
 */
public abstract class AbstractBluetoothStateObserver extends BroadcastReceiver {

    private String TAG = "BluetoothStateObserver";

    public AbstractBluetoothStateObserver() {

    }

    /**
     * Receives the intent and routes the data to the appropriate method call,
     * including onDisabled(), onEnabled() -- these must be overridden in a derived class
     *
     * @param context The context in which the receiver is running.
     * @param intent  The Intent being received.
     */
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.w(TAG, "onReceive: " + action);

        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
                Log.w(TAG, "--##-- BLuetooth disabled");
                this.onDisabled();
            } else if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_ON) {
                Log.w(TAG, "--##-- BLuetooth enabled **");
                this.onEnabled();
            }
        }
    }

    /**
     * Will be called when bluetooth is enabled
     */
    public abstract void onEnabled();

    /**
     * Will be called when bluetooth is disabled
     */
    public abstract void onDisabled();

}
