package com.krmi.urcap.sample.simpleWaypoint.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.UserInterfaceAPI;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.RobotPositionCallback2;
import com.ur.urcap.api.domain.value.robotposition.PositionParameters;

public class WaypointProgramNodeContribution implements ProgramNodeContribution{
    private final ProgramAPIProvider apiProvider;

    public WaypointProgramNodeContribution(ProgramAPIProvider apiProvider, WaypointProgramNodeView view) {
        this.apiProvider = apiProvider;
    }

    @Override
    public void openView() {

    }

    @Override
    public void closeView() {

    }

    @Override
    public String getTitle() {
        return "Callback trigger";
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public void generateScript(ScriptWriter writer) {
        
    }

    public void triggerCallback() {
        UserInterfaceAPI uiapi = apiProvider.getUserInterfaceAPI();
        long startTime = System.currentTimeMillis();
        System.out.println("Starting CPU-burning delay at: " + startTime);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        }

        long afterDelayTime = System.currentTimeMillis();
        System.out.println("End CPU-burning delay at: " + afterDelayTime + " (Duration: "+ (afterDelayTime - startTime) + " ms)");

        uiapi.getUserInteraction().getUserDefinedRobotPosition(new RobotPositionCallback2() {

            @Override
            public void onOk(PositionParameters parameters) {
                System.out.println("onOk was triggered.");
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel was triggered.");
            }
        });        
    }
    
}
