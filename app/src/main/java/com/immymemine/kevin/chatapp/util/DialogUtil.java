package com.immymemine.kevin.chatapp.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by quf93 on 2017-11-03.
 */

public class DialogUtil {
    public static void showDialog(String message, final Activity activity, final boolean activity_finish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Notice");
        builder.setMessage(message);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if(activity_finish)
                    activity.finish();
            }
        });
        // builder.setCancelable(); false 면 버튼을 달아줘야한다 버튼에 의해서만 사라짐
        builder.show();
    }
}
