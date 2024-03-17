package com.mapzen.helpers;

import com.mapzen.model.ValhallaLocation;
import com.mapzen.valhalla.Instruction;
import com.mapzen.valhalla.Route;
import java.util.ArrayList;

public class RouteEngine {
    public static final int ALERT_RADIUS = 100;
    public static final int APPROACH_RADIUS = 50;
    public static final int DESTINATION_RADIUS = 30;
    private Instruction currentInstruction;
    private ArrayList<Instruction> instructions;
    private Milestone lastMilestoneUpdate;
    private RouteListener listener;
    private ValhallaLocation location;
    private Route route;
    private RouteState routeState;
    private ValhallaLocation snapLocation;

    public enum Milestone {
        TWO_MILE,
        ONE_MILE,
        QUARTER_MILE
    }

    public enum RouteState {
        PRE_INSTRUCTION,
        INSTRUCTION,
        COMPLETE,
        LOST
    }

    public void onLocationChanged(ValhallaLocation valhallaLocation) {
        if (this.routeState != RouteState.COMPLETE) {
            this.location = valhallaLocation;
            snapLocation();
            if (this.routeState == RouteState.COMPLETE) {
                this.listener.onUpdateDistance(0, 0);
            } else {
                this.listener.onUpdateDistance(this.route.getDistanceToNextInstruction(), this.route.getRemainingDistanceToDestination());
            }
            if (this.routeState != RouteState.LOST) {
                checkApproachMilestone(Milestone.TWO_MILE, 3218.0d);
                checkApproachMilestone(Milestone.ONE_MILE, 1609.0d);
                checkApproachMilestone(Milestone.QUARTER_MILE, 402.25d);
                if (this.routeState == RouteState.PRE_INSTRUCTION && this.route.getDistanceToNextInstruction() < 100 && this.route.getNextInstructionIndex() != null) {
                    this.listener.onApproachInstruction(this.route.getNextInstructionIndex().intValue());
                    this.routeState = RouteState.INSTRUCTION;
                    this.lastMilestoneUpdate = null;
                }
                Instruction nextInstruction = this.route.getNextInstruction();
                if (this.instructions != null && !this.currentInstruction.equals(nextInstruction)) {
                    this.routeState = RouteState.PRE_INSTRUCTION;
                    this.listener.onInstructionComplete(this.instructions.indexOf(this.currentInstruction));
                }
                this.currentInstruction = this.route.getNextInstruction();
            }
        }
    }

    private void checkApproachMilestone(Milestone milestone, double d) {
        if (this.routeState == RouteState.PRE_INSTRUCTION && Math.abs(((double) this.route.getDistanceToNextInstruction()) - d) < 50.0d && this.lastMilestoneUpdate != milestone && this.route.getNextInstructionIndex() != null) {
            this.listener.onMilestoneReached(this.route.getNextInstructionIndex().intValue(), milestone);
            this.lastMilestoneUpdate = milestone;
        }
    }

    private void snapLocation() {
        ValhallaLocation snapToRoute = this.route.snapToRoute(this.location);
        this.snapLocation = snapToRoute;
        if (snapToRoute != null) {
            this.listener.onSnapLocation(this.location, snapToRoute);
        }
        if (youHaveArrived()) {
            this.routeState = RouteState.COMPLETE;
            this.listener.onRouteComplete();
        }
        if (this.route.isLost()) {
            this.routeState = RouteState.LOST;
            this.listener.onRecalculate(this.location);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.snapLocation;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean youHaveArrived() {
        /*
            r1 = this;
            com.mapzen.model.ValhallaLocation r0 = r1.getLocationForDestination()
            if (r0 == 0) goto L_0x001a
            com.mapzen.model.ValhallaLocation r0 = r1.snapLocation
            if (r0 == 0) goto L_0x001a
            com.mapzen.model.ValhallaLocation r1 = r1.getLocationForDestination()
            float r1 = r0.distanceTo(r1)
            r0 = 1106247680(0x41f00000, float:30.0)
            int r1 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r1 >= 0) goto L_0x001a
            r1 = 1
            goto L_0x001b
        L_0x001a:
            r1 = 0
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mapzen.helpers.RouteEngine.youHaveArrived():boolean");
    }

    private ValhallaLocation getLocationForDestination() {
        if (this.route.getRouteInstructions() == null) {
            return null;
        }
        return this.route.getRouteInstructions().get(this.route.getRouteInstructions().size() - 1).getLocation();
    }

    public void setRoute(Route route2) {
        if (this.listener != null) {
            this.route = route2;
            ArrayList<Instruction> routeInstructions = route2.getRouteInstructions();
            this.instructions = routeInstructions;
            if (routeInstructions != null) {
                this.currentInstruction = routeInstructions.get(0);
            }
            this.listener.onRouteStart();
            this.routeState = RouteState.PRE_INSTRUCTION;
            return;
        }
        throw new IllegalStateException("Route listener is null");
    }

    public Route getRoute() {
        return this.route;
    }

    public void setListener(RouteListener routeListener) {
        this.listener = routeListener;
    }
}
