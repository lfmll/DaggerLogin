package com.example.luisfernandomedinallorenti.daggerlogin.login;

public interface LoginActivityMVP {

    interface  View{
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();
        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Presenter{
        void setView(LoginActivityMVP.View view);
        void loginButttonCLicked();
        void getCurrentUser();
    }

    interface Model{
        void createUser(String firstName, String lastName);
        User getUser();
    }
}
