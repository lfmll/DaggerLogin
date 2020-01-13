package com.example.luisfernandomedinallorenti.daggerlogin.login;

public class LoginActivityModel implements LoginActivityMVP.Model {
    private LoginRepository repository;

    public LoginActivityModel(LoginRepository repository){
        this.repository=repository;
    }
    @Override
    public void createUser(String firstName, String lastName) {
        //Logica de Negocio
        repository.saveUser(new User(firstName,lastName));
    }

    @Override
    public User getUser() {
        //Logica de Nogocio
        return repository.getUser();
    }
}
