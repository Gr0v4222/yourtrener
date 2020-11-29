package com.example.regform.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.regform.data.LoginRepository;
import com.example.regform.data.Result;
import com.example.regform.data.model.LoggedInUser;
import com.example.regform.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String name, String age, String weight, String rost) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(name, age, weight, rost);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String name, String age, String weight, String rost) {
        if (!isUserNameValid(name)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_name, null, null, null));
        } else if (!isAgeValid(age)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password, null, null));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String name) {
        if (name == null) {
            return false;
        }
        else return true;
    }

    // A placeholder password validation check
    private boolean isAgeValid(String age) {
        return age != null && age.trim().length() > 0 && age.trim().length() < 3;
    }
}