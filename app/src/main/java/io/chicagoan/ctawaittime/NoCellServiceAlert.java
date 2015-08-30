package io.chicagoan.ctawaittime;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by bjg on 8/30/15.
 */
public class NoCellServiceAlert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Sorry, please check your cell service. This app requires SMS ability. Try turning Airplane-Mode off.").
        setIcon(android.R.attr.alertDialogIcon).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });


        return builder.create();
    }
}
