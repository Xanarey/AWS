package com.timur.AWS.model;

public enum Permission {

    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_READ_ALL("developers:readAll"),
    DEVELOPERS_REGISTER("developers:register"),
    DEVELOPERS_UPDATE("developers:update"),
    DEVELOPERS_DELETE("developers:delete"),
    DEVELOPERS_DELETE_NOT_ACTIVE("developers:deleteNotActive"),

    EVENTS_READ_ALL("events:readAll"),
    EVENTS_CREATE("events:create"),
    EVENTS_UPDATE("events:update"),
    EVENTS_DELETE("events:delete"),

    FILES_DOWNLOAD("files:download"),
    FILES_UPLOAD("files:upload"),
    FILES_READ_ALL("files:readAll"),
    FILES_UPDATE("files:update"),
    FILES_DELETE("files:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
