package com.solvd.project.System;

@FunctionalInterface
interface Filter<T> {
    boolean filter(T student);
    
}
