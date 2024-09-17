package com.krmi.urcap.sample.simpleWaypoint.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.UserInterfaceAPI;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.userinteraction.RobotPositionCallback;
import com.ur.urcap.api.domain.userinteraction.RobotPositionCallback2;
import com.ur.urcap.api.domain.userinteraction.UserInteraction;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidationFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.userinteraction.robot.movement.RobotMovement;
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
        UserInteraction originalInteraction = uiapi.getUserInteraction();
        UserInteractionWrapper interactionWrapper = new UserInteractionWrapper(originalInteraction);
        interactionWrapper.getUserDefinedRobotPosition(new RobotPositionCallback2() {

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

// Wrapper class defined in the same file (not public)
class UserInteractionWrapper implements UserInteraction {

    private final UserInteraction original;

    public UserInteractionWrapper(UserInteraction original) {
        this.original = original;
    }

    @Override
    public void getUserDefinedRobotPosition(RobotPositionCallback2 callback) {
        // Introduce a delay before proceeding
        System.out.println("Injecting delay before getUserDefinedRobotPosition is called.");
        try {
            Thread.sleep(5000); // Sleep for 5 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        }

        // Proceed with the original method call
        original.getUserDefinedRobotPosition(callback);
    }

    @Override
    public void getUserDefinedRobotPosition(RobotPositionCallback callback) {
        original.getUserDefinedRobotPosition(callback);
    }

    @Override
    public RobotMovement getRobotMovement() {
        return original.getRobotMovement();
    }

    @Override
    public KeyboardInputFactory getKeyboardInputFactory() {
        return original.getKeyboardInputFactory();
    }

    @Override
    public InputValidationFactory getInputValidationFactory() {
        return original.getInputValidationFactory();
    }

}