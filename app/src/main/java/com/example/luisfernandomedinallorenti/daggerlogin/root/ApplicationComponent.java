package com.example.luisfernandomedinallorenti.daggerlogin.root;

import javax.inject.Singleton;

import dagger.Component;

import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginModule;
import com.example.luisfernandomedinallorenti.daggerlogin.login.MainActivity;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject(MainActivity target);
}
