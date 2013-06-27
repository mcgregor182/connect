package com.mirth.connect.server.migration;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("version")
public enum Version {
    // @formatter:off

    /*
     * When a new version of Mirth Connect is released, do the following:
     * 1) Add the new version to the end of the list below (the list must be kept in historical order)
     * 2) Specify a Migrator class for the new version in the ServerMigrator class
     * 3) Add migration code/classes for any plugins that need to be migrated
     */

    V0("0"),
    V1("1"),
    V2("2"),
    V3("3"),
    V4("4"),
    V5("5"),
    V6("6"),
    V7("7"),
    V8("8"),
    V9("9"),
    V3_0_0("3.0.0");
    
    // @formatter:on

    private String versionString;

    private Version(String value) {
        this.versionString = value;
    }

    public boolean nextVersionExists() {
        return ordinal() < getLatest().ordinal();
    }

    public Version getNextVersion() {
        return values()[ordinal() + 1];
    }

    @Override
    public String toString() {
        return versionString;
    }

    public static Version getLatest() {
        Version[] allVersions = values();
        return allVersions[allVersions.length - 1];
    }

    public static Version fromString(String value) {
        for (Version version : values()) {
            if (version.toString().equals(value)) {
                return version;
            }
        }

        return null;
    }
}