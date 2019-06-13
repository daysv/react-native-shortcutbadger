package com.yangqiong.shortcutbadger;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by yang on 16/8/17.
 */
public class ShortcutBadgerModule extends ReactContextBaseJavaModule {
    static ReactApplicationContext context;
    static int count;

    public ShortcutBadgerModule(ReactApplicationContext reactContext){
        super(reactContext);
        context = reactContext;
        count = 0;
    }

    @Override
    public String getName(){
        return "ShortcutBadger";
    }

    @ReactMethod
    public void applyCount(int badgeCount){
        if (context != null){
            count = badgeCount;
            ShortcutBadger.applyCount(context, badgeCount);
        }
    }

    @ReactMethod
    public void removeCount(){
        if (context != null){
            count = 0;
            ShortcutBadger.removeCount(context);
        }
    }

    @ReactMethod
    public void getCount(Callback callback){
      callback.invoke(count);
    }
}
