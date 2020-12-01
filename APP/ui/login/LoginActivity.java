package space.projectKPO.yourtrener.ui.login;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import space.projectKPO.yourtrener.MainActivity;
import space.projectKPO.yourtrener.Menu1;
import space.projectKPO.yourtrener.R;
import space.projectKPO.yourtrener.ui.login.LoginViewModel;
import space.projectKPO.yourtrener.ui.login.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText nameEditText = findViewById(R.id.name);
        final EditText ageEditText = findViewById(R.id.age);
        final EditText weightEditText = findViewById(R.id.weight);
        final EditText rostEditText = findViewById(R.id.rost);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getNameError() != null) {
                    nameEditText.setError(getString(loginFormState.getNameError()));
                }
                if (loginFormState.getAgeError() != null) {
                    ageEditText.setError(getString(loginFormState.getAgeError()));
                }
                if (loginFormState.getWeightError() != null) {
                    weightEditText.setError(getString(loginFormState.getWeightError()));
                }

                if (loginFormState.getWeightError() != null) {
                    rostEditText.setError(getString(loginFormState.getWeightError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                updateUiWithUser(loginResult.getSuccess());
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(nameEditText.getText().toString(),
                        ageEditText.getText().toString(),
                        weightEditText.getText().toString(),
                        rostEditText.getText().toString() );
                try {
                    Intent intent = new Intent(LoginActivity.this, Menu1.class);
                    startActivity(intent); finish();     //команда перейти на другую страницу
                } catch (Exception e) {  //если переход не состоится, то игра не вылетит и не закроется

                }


            }
        });
    }


    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
