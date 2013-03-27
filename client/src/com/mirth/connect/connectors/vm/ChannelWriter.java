/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL
 * license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */

package com.mirth.connect.connectors.vm;

import com.mirth.connect.client.ui.Frame;
import com.mirth.connect.client.ui.PlatformUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mirth.connect.client.ui.panels.connectors.ConnectorSettingsPanel;
import com.mirth.connect.donkey.model.channel.ConnectorProperties;
import com.mirth.connect.model.Channel;

public class ChannelWriter extends ConnectorSettingsPanel {

    private Frame parent;
    private Map<String, String> channelList;

    public ChannelWriter() {
        parent = PlatformUI.MIRTH_FRAME;
        initComponents();
    }

    @Override
    public String getConnectorName() {
        return new VmDispatcherProperties().getName();
    }

    @Override
    public ConnectorProperties getProperties() {
        if (channelList == null) {
            return null;
        }
        
        VmDispatcherProperties properties = new VmDispatcherProperties();

        properties.setChannelId(channelList.get((String) channelNames.getSelectedItem()));
        properties.setChannelTemplate(template.getText());

        properties.setWaitForDestinations(channelResponseYesButton.isSelected());

        return properties;
    }

    @Override
    public void setProperties(ConnectorProperties properties) {
        VmDispatcherProperties props = (VmDispatcherProperties) properties;

        ArrayList<String> channelNameArray = new ArrayList<String>();
        channelList = new HashMap<String, String>();
        channelList.put("None", "none");

        String selectedChannelName = "None";

        for (Channel channel : parent.channels.values()) {
            if (props.getChannelId().equalsIgnoreCase(channel.getId())) {
                selectedChannelName = channel.getName();
            }

            channelList.put(channel.getName(), channel.getId());
            channelNameArray.add(channel.getName());
        }

        // sort the channels in alpha-numeric order.
        Collections.sort(channelNameArray);

        // add "None" to the very top of the list.
        channelNameArray.add(0, "None");

        channelNames.setModel(new javax.swing.DefaultComboBoxModel(channelNameArray.toArray()));

        boolean enabled = parent.isSaveEnabled();

        channelNames.setSelectedItem(selectedChannelName);

        if (props.isWaitForDestinations()) {
            channelResponseYesButton.setSelected(true);
        } else {
            channelResponseNoButton.setSelected(true);
        }

        template.setText(props.getChannelTemplate());

        parent.setSaveEnabled(enabled);
    }

    @Override
    public ConnectorProperties getDefaults() {
        return new VmDispatcherProperties();
    }

    @Override
    public boolean checkProperties(ConnectorProperties props, boolean highlight) {
        return true;
    }

    @Override
    public void resetInvalidProperties() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        URL = new javax.swing.JLabel();
        channelNames = new com.mirth.connect.client.ui.components.MirthComboBox();
        channelResponseLabel = new javax.swing.JLabel();
        channelResponseYesButton = new com.mirth.connect.client.ui.components.MirthRadioButton();
        channelResponseNoButton = new com.mirth.connect.client.ui.components.MirthRadioButton();
        jLabel7 = new javax.swing.JLabel();
        template = new com.mirth.connect.client.ui.components.MirthSyntaxTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        URL.setText("Channel Name:");

        channelNames.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        channelNames.setToolTipText("<html>Select the channel to which messages accepted by this destination's filter should be written,<br> or none to not write the message at all.</html>");
        channelNames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                channelNamesActionPerformed(evt);
            }
        });

        channelResponseLabel.setText("Wait for Channel Response:");

        channelResponseYesButton.setBackground(new java.awt.Color(255, 255, 255));
        channelResponseYesButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(channelResponseYesButton);
        channelResponseYesButton.setText("Yes");
        channelResponseYesButton.setToolTipText("<html>If Yes, then the destination will wait until it gets a response from the destination channel<br> (after it has fully executed all of its destinations) before further destinations are processed on the current channel.<br>If No, then the current channel's workflow will continue regardless of what the destination channel is doing.</html>");
        channelResponseYesButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        channelResponseNoButton.setBackground(new java.awt.Color(255, 255, 255));
        channelResponseNoButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonGroup1.add(channelResponseNoButton);
        channelResponseNoButton.setText("No");
        channelResponseNoButton.setToolTipText("<html>If Yes, then the destination will wait until it gets a response from the destination channel<br> (after it has fully executed all of its destinations) before further destinations are processed on the current channel.<br>If No, then the current channel's workflow will continue regardless of what the destination channel is doing.</html>");
        channelResponseNoButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

        jLabel7.setText("Template:");

        template.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        template.setToolTipText("<html>A Velocity enabled template for the actual message to be written to the channel.<br>In many cases, the default value of \"${message.encodedData}\" is sufficient.</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(channelResponseLabel)
                    .addComponent(URL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(channelNames, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(channelResponseYesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(channelResponseNoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(template, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(URL)
                    .addComponent(channelNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(channelResponseLabel)
                    .addComponent(channelResponseYesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(channelResponseNoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(template, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void channelNamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_channelNamesActionPerformed
        if (((String) channelNames.getSelectedItem()).equalsIgnoreCase("None")) {
            channelResponseNoButton.setSelected(true);
            channelResponseLabel.setEnabled(false);
            channelResponseYesButton.setEnabled(false);
            channelResponseNoButton.setEnabled(false);
        } else {
            channelResponseLabel.setEnabled(true);
            channelResponseYesButton.setEnabled(true);
            channelResponseNoButton.setEnabled(true);
        }
    }//GEN-LAST:event_channelNamesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel URL;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.mirth.connect.client.ui.components.MirthComboBox channelNames;
    private javax.swing.JLabel channelResponseLabel;
    private com.mirth.connect.client.ui.components.MirthRadioButton channelResponseNoButton;
    private com.mirth.connect.client.ui.components.MirthRadioButton channelResponseYesButton;
    private javax.swing.JLabel jLabel7;
    private com.mirth.connect.client.ui.components.MirthSyntaxTextArea template;
    // End of variables declaration//GEN-END:variables
}
