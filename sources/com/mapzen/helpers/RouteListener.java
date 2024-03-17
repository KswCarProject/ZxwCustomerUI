package com.mapzen.helpers;

import com.mapzen.helpers.RouteEngine;
import com.mapzen.model.ValhallaLocation;

public interface RouteListener {
    void onApproachInstruction(int i);

    void onInstructionComplete(int i);

    void onMilestoneReached(int i, RouteEngine.Milestone milestone);

    void onRecalculate(ValhallaLocation valhallaLocation);

    void onRouteComplete();

    void onRouteStart();

    void onSnapLocation(ValhallaLocation valhallaLocation, ValhallaLocation valhallaLocation2);

    void onUpdateDistance(int i, int i2);
}
