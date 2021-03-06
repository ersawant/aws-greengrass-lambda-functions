package com.amazonaws.greengrass.cddkinesis;

import com.amazonaws.greengrass.cddkinesis.modules.AppModule;
import com.awslabs.aws.iot.greengrass.cdd.BaselineAppInterface;

import java.util.Arrays;

public class App implements BaselineAppInterface {
    static {
        // Specify any modules you need here
        BaselineAppInterface.initialize(Arrays.asList(new AppModule()));
    }

    // Greengrass requires a no-args constructor, do not remove!
    public App() {
    }
}
