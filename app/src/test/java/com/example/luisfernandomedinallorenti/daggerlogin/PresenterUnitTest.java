package com.example.luisfernandomedinallorenti.daggerlogin;

import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginActivityMVP;
import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginActivityPresenter;
import com.example.luisfernandomedinallorenti.daggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    User user;

    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before
    public void initialization(){
        mockedModel=mock(LoginActivityMVP.Model.class);
        mockedView=mock(LoginActivityMVP.View.class);

        user=new User("Cuenta","Falsa");

//        when(mockedModel.getUser()).thenReturn(user);
//
//        when(mockedView.getFirstName()).thenReturn("Cuenta");
//        when(mockedView.getLastName()).thenReturn("Falsa");

        presenter=new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }
    @Test
    public void noExistsInteractionWithView(){
        presenter.getCurrentUser();
//        verifyZeroInteractions(mockedView);

        verify(mockedView, times(1)).showUserNotAvailable();
    }
    @Test
    public void loadUserFromTheRepoWhenValidUserIsPresent(){
        when(mockedModel.getUser()).thenReturn(user);
        presenter.getCurrentUser();
        //COmprobamos la interactuacion con el modelo de datos
        verify(mockedModel,times(1)).getUser();

        //Comprobamos la interactuacion con la vista
        verify(mockedView,times(1)).setFirstName("Cuenta");
        verify(mockedView,times(1)).setLastName("Falsa");
        verify(mockedView,never()).showUserNotAvailable();
    }

    @Test
    public void showErrorMessageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        verify(mockedModel, times(1)).getUser();

        verify(mockedView, never()).setFirstName("Cuenta");
        verify(mockedView,never()).setLastName("Falsa");
        verify(mockedView,times(1)).showUserNotAvailable();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){
        //1ro poniendo el campo firstname vacio
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButttonCLicked();

        verify(mockedView,times(1)).getFirstName();
        verify(mockedView,never()).getLastName();
        verify(mockedView,times(1)).showInputError();

        //2do poniendo un valor en el campo firstnae y dejando el lastname vacio
        when(mockedView.getFirstName()).thenReturn("Cuenta");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButttonCLicked();

        verify(mockedView, times(2)).getFirstName();//El metodo se llama 2 veces, una en la prueba anterior y una en la actual
        verify(mockedView,times(1)).getLastName();//Este metodo se llama por 1ra vez, ya que antes no paso el test booleano
        verify(mockedView, times(2)).showInputError();//El metodo se llamo antes y de nuevo ahora, en total 2 veces
    }

    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("Cuenta");
        when(mockedView.getLastName()).thenReturn("Prueba");

        presenter.loginButttonCLicked();

        //Las llamadas deben ser dobles dentro del condicional y cuando se crea el usuario
        verify(mockedView,times(2)).getFirstName();
        verify(mockedView,times(2)).getLastName();

        //Miramos que el modelo persista en el repositorio
        verify(mockedModel,times(1)).createUser("Cuenta","Prueba");

        //Miramos que se muestre el mensaje de exito l usuario
        verify(mockedView,times(1)).showUserSaved();
    }
}
