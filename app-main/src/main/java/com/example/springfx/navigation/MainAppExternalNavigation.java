package com.example.springfx.navigation;

public enum MainAppExternalNavigation {
    SECONDARY_APP_SECOND_SCREEN("secondScreenController");

    private final String controllerString;

    MainAppExternalNavigation(String secondScreenController) {
        this.controllerString = secondScreenController;
    }

    public String get() {
        return controllerString;
    }
}
