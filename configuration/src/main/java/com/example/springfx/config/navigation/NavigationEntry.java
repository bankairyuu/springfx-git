package com.example.springfx.config.navigation;

public class NavigationEntry {
    private String module;
    private String screen;
    private String beanName;

    public String getModule() {
        return module;
    }

    public String getScreen() {
        return screen;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
