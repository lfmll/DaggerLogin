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
}
