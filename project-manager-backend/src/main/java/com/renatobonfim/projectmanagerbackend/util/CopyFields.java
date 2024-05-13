package com.renatobonfim.projectmanagerbackend.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class CopyFields {

    public static void copy(Object from, Object to) {
        try {
            for (Field field : from.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                if (Objects.nonNull(field.get(from))) {
                    field.set(to, field.get(from));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
