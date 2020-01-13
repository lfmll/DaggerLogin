package com.example.luisfernandomedinallorenti.daggerlogin.root;

import android.app.Application;

import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginModule;

public class ApplicationDagger extends Application{
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component=DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
