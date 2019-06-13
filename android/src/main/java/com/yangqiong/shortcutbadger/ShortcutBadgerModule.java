package com.yangqiong.shortcutbadger;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.content.SharedPreferences;
import android.content.Context;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by yang on 16/8/17.
 */
public class ShortcutBadgerModule extends ReactContextBaseJavaModule {
    static ReactApplicationContext context;
    private SharedPreferences mPrefs;
    private static final String BADGE_FILE = "BadgeCountFile";
    private static final String BADGE_KEY = "BadgeCount";

    public ShortcutBadgerModule(ReactApplicationContext reactContext){
        super(reactContext);
        context = reactContext;
        mPrefs = reactContext.getSharedPreferences(BADGE_FILE, Context.MODE_PRIVATE);
    }

    @Override
    public String getName(){
        return "ShortcutBadger";
    }

    @ReactMethod
    public void applyCount(int badgeCount){
        if (context != null){
            mPrefs.edit().putInt(BADGE_KEY, badgeCount).apply();
            ShortcutBadger.applyCount(context, badgeCount);
        }
    }

    @ReactMethod
    public void removeCount(){
        if (context != null){
            mPrefs.edit().putInt(BADGE_KEY, 0).apply();
            ShortcutBadger.removeCount(context);
        }
    }

    @ReactMethod
    public void getCount(Callback callback){
      callback.invoke(mPrefs.getInt(BADGE_KEY, 0));
    }
}
