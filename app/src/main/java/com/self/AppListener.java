package com.self;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hello on 12-02-2018.
 */


public class AppListener extends BroadcastReceiver {

    String added = "Installed App is: ";
    String removed = "Uninstalled App is: ";
    String app_name, package_name, send;


    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("android.intent.action.PACKAGE_ADDED")){

        String[] a=intent.getData().toString().split(":");
        String packageName=a[a.length-1];

        List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packageInfoList.size(); i++) {
            PackageInfo packageInfo = packageInfoList.get(i);
            if(packageInfo.packageName.equals(packageName)){
                String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                send = added + appName;
                String appVersion = packageInfo.versionName;
                int appVerCode = packageInfo.versionCode;
                Drawable app_icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
                            try {
                MainActivity.getInstance().updateAppStatus(send);
            } catch (Exception e) {

            }

            }
        }
    }


//        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
//            package_name = intent.getData().toString();
//            app_name = getAppNameFromPackageName(context, package_name);
//            send = added + app_name;
//            Toast.makeText(MainActivity.context, "Added App is: ", Toast.LENGTH_LONG).show();
//            try {
//                MainActivity.getInstance().updateAppStatus(send);
//            } catch (Exception e) {
//
//            }


         else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            send = intent.getData().toString();
            String[] a = intent.getData().toString().split(":");
            String packageName = a[a.length - 1];

//            List<PackageInfo> packageInfoList = context.getPackageManager().getInstalledPackages(0);
//            for (int i = 0; i < packageInfoList.size(); i++) {
//                PackageInfo packageInfo = packageInfoList.get(i);
//                if(packageInfo.packageName.equals(packageName)){
//                    String appName = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
//                    send = removed + appName;
//                    String appVersion = packageInfo.versionName;
//                    int appVerCode = packageInfo.versionCode;
//                    Drawable app_icon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
            try {
                MainActivity.getInstance().updateAppStatus(send);
            } catch (Exception e) {

            }

//        }
//            }
            send = removed + app_name;
            Toast.makeText(MainActivity.context, "Uninstalled App is: ", Toast.LENGTH_LONG).show();
            try {
                MainActivity.getInstance().updateAppStatus(send);
            } catch (Exception e) {

            }

        }

    }

//    public static String getAppNameFromPackageName(Context context, String app_name) {
//        try {
//            PackageManager packageManager = context.getPackageManager();
//            ApplicationInfo info = packageManager.getApplicationInfo(app_name, PackageManager.GET_META_DATA);
//            String appName = (String) packageManager.getApplicationLabel(info);
//            return appName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
}
