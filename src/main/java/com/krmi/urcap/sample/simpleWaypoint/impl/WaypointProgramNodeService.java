package com.krmi.urcap.sample.simpleWaypoint.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;

public class WaypointProgramNodeService implements SwingProgramNodeService<WaypointProgramNodeContribution, WaypointProgramNodeView> {

    @Override
    public String getId() {
        return "WaypointBugSwingNode";
    }

    @Override
    public String getTitle(Locale locale) {
        return "Callback trigger";
    }


    @Override
    public void configureContribution(ContributionConfiguration configuration) {
        configuration.setChildrenAllowed(false);
    }

    @Override
    public WaypointProgramNodeView createView(ViewAPIProvider apiProvider) {
        return new WaypointProgramNodeView();
    }

    @Override
    public WaypointProgramNodeContribution createNode(ProgramAPIProvider apiProvider, WaypointProgramNodeView view,
            DataModel model, CreationContext context) {
        return new WaypointProgramNodeContribution(apiProvider, view);
    }
    
}
