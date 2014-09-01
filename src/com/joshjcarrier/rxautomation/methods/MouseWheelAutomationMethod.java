package com.joshjcarrier.rxautomation.methods;

import com.joshjcarrier.rxautomation.persistence.IAutomationReader;
import com.joshjcarrier.rxautomation.persistence.IAutomationWriter;

import java.awt.*;

public class MouseWheelAutomationMethod implements IAutomationMethod {
    private static final String METHOD_ID = "mouse-wheel";
    private static final String SCROLL_KEY = "scroll";
    private static Robot humanInterfaceDeviceService;

    static {

        try
        {
            humanInterfaceDeviceService = new Robot();

            // this prevents the OS from ignoring events generated too quickly in succession
            humanInterfaceDeviceService.setAutoDelay(5);
        }
        catch (AWTException e)
        {
            // TODO 2.0+ throw exception
            e.printStackTrace();
        }
    }

    private final int scrollAmount;

    public MouseWheelAutomationMethod(int scrollAmount) {

        this.scrollAmount = scrollAmount;
    }

    public static IAutomationMethod load(IAutomationReader automationReader) {
        String methodId = automationReader.readMethod();
        if(!methodId.equalsIgnoreCase(METHOD_ID)) {
            return null;
        }

        try {
            Integer scrollAmount = automationReader.readInt(SCROLL_KEY);

            return new MouseWheelAutomationMethod(scrollAmount);
        } catch (Exception e) {
            return null;
        }
    }

    public void automate(Float value) {
        if(value == 1f) {
            humanInterfaceDeviceService.mouseWheel(scrollAmount);
        }
        else if(value == -1f) {
            humanInterfaceDeviceService.mouseWheel(-scrollAmount);
        }
    }

    @Override
    public String getName() {
        return scrollAmount > 0 ? "Mouse scroll down" : "Mouse scroll up";
    }

    @Override
    public void save(IAutomationWriter automationWriter) {
        automationWriter.writeMethod(METHOD_ID);
        automationWriter.write(SCROLL_KEY, this.scrollAmount);
    }
}
