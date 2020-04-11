package com.yang.headsupblock;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.app.Notification;
import android.util.Log;

public class Blocker implements IXposedHookLoadPackage {
    private static final String TAG = "Blocker";
    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.tencent.tim")) {
            return;
        }
        XposedBridge.log("我哋係TIM喇！！");

        findAndHookMethod("android.app.Notification.Builder", lpparam.classLoader, "build", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Notification notification = (Notification)param.getResult();
                notification.priority = Notification.PRIORITY_DEFAULT;
            }
        });
    }
}
