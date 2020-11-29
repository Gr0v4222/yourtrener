package com.example.regform.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
class LoginFormState {
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer ageError;
    @Nullable
    private Integer weightError;
    private boolean isDataValid;

    LoginFormState(@Nullable Integer nameError, @Nullable Integer ageError, @Nullable Integer weightError) {
        this.nameError = nameError;
        this.ageError = ageError;
        this.weightError = weightError;
        this.isDataValid = false;
    }

    LoginFormState(boolean isDataValid) {
        this.nameError = nameError;
        this.ageError = ageError;
        this.weightError = weightError;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    @Nullable
    Integer getAgeError() {
        return ageError;
    }

    @Nullable
    Integer getWeightError() {
        return weightError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}