package com.example.luisfernandomedinallorenti.daggerlogin.login;

public class MemoryRepository implements LoginRepository {
    private User user;
    @Override
    public void saveUser(User user) {
        if (user==null){
            user=getUser();
        }
        this.user=user;
    }

    @Override
    public User getUser() {
        if (user==null){
            user=new User("Usuario","Falso");
            user.setId(0);
            return user;
        }else {
            return user;
        }
    }
}
