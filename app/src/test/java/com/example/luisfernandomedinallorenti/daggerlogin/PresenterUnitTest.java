package com.example.luisfernandomedinallorenti.daggerlogin;

import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginActivityMVP;
import com.example.luisfernandomedinallorenti.daggerlogin.login.LoginActivityPresenter;
import com.example.luisfernandomedinallorenti.daggerlogin.login.User;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
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

        //when(mockedModel.getUser()).thenReturn(user);

        when(mockedView.getFirstName()).thenReturn("Cuenta");
        when(mockedView.getLastName()).thenReturn("Falsa");

        presenter=new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }
    @Test
    public void noExistsInteractionWithView(){
        presenter.getCurrentUser();
        verifyZeroInteractions(mockedView);
    }
}
