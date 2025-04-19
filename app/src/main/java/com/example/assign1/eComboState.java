package com.example.assign1;


import java.io.Serializable;

/**
 * These enums will track whether the current combo has been used/failed/success
 */
public enum eComboState implements Serializable
{
    SUCCESSFUL,
    FAILED,
    NOT_ATTEMPTED
}
