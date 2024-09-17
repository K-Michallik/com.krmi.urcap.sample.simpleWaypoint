package com.krmi.urcap.sample.simpleWaypoint.impl;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;




public class WaypointProgramNodeView implements SwingProgramNodeView<WaypointProgramNodeContribution>{

    private static final int VERTICAL_SPACING = 10;
    private static final int HORIZONTAL_INDENT = 50;
    private static final Dimension BUTTON_SIZE = new Dimension(190, 40);
    private JButton callbackButton;

    @Override
    public void buildUI(JPanel panel, ContributionProvider<WaypointProgramNodeContribution> provider) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createVerticalSpacing());

        Box buttonSection = createSection(BoxLayout.LINE_AXIS);
        buttonSection.add(createHorizontalIndent());
        this.callbackButton = createButton("Trigger callback");
		this.callbackButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                provider.get().triggerCallback();
            }

		});
        this.callbackButton.setPreferredSize(BUTTON_SIZE);
        this.callbackButton.setMinimumSize(BUTTON_SIZE);
        this.callbackButton.setMaximumSize(BUTTON_SIZE);
        buttonSection.add(this.callbackButton, FlowLayout.LEFT);
        panel.add(buttonSection);
        panel.add(createVerticalSpacing());

    }

	JButton createButton(String text) {
		return new JButton(text);
	}

	Box createSection(int axis) {
		Box panel = new Box(axis);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		return panel;
	}

    Component createVerticalSpacing() {
		return Box.createRigidArea(new Dimension(0, VERTICAL_SPACING));
	}

    Component createHorizontalIndent() {
		return Box.createRigidArea(new Dimension(HORIZONTAL_INDENT, 0));
	}
}
