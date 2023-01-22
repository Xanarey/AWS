package com.timur.AWS.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    ADMIN(Set.of(
            Permission.DEVELOPERS_REGISTER,
            Permission.DEVELOPERS_READ,
            Permission.DEVELOPERS_READ_ALL,
            Permission.DEVELOPERS_UPDATE,
            Permission.DEVELOPERS_DELETE,
            Permission.DEVELOPERS_DELETE_NOT_ACTIVE,

            Permission.EVENTS_READ_ALL,
            Permission.EVENTS_CREATE,
            Permission.EVENTS_UPDATE,
            Permission.EVENTS_DELETE,

            Permission.FILES_DOWNLOAD,
            Permission.FILES_UPLOAD,
            Permission.FILES_READ_ALL,
            Permission.FILES_UPDATE,
            Permission.FILES_DELETE)),

    MODERATOR(Set.of(
            Permission.DEVELOPERS_READ_ALL,
            Permission.DEVELOPERS_DELETE_NOT_ACTIVE,

            Permission.EVENTS_READ_ALL,
            Permission.EVENTS_UPDATE,
            Permission.EVENTS_DELETE,

            Permission.FILES_READ_ALL,
            Permission.FILES_UPDATE,
            Permission.FILES_DELETE)),

    USER(Set.of(
            Permission.EVENTS_READ_ALL,

            Permission.FILES_DOWNLOAD,
            Permission.FILES_UPLOAD,
            Permission.FILES_READ_ALL,
            Permission.FILES_UPDATE,
            Permission.FILES_DELETE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
