package com.example.springfx.navigation;

public enum SecondaryAppExternalNavigation {
    MAIN_APP_Main_SCREEN("mainScreenController");

    private final String controllerString;

    SecondaryAppExternalNavigation(String secondScreenController) {
        this.controllerString = secondScreenController;
    }

    public String get() {
        return controllerString;
    }
}
