package de.twometer.amongus.model;

import org.joml.Vector3f;

import java.util.List;

public class Player implements PlayerBehavior {

    public int id;

    public String username;

    public Vector3f position;

    public float rotation;

    public PlayerRole role;

    public PlayerColor color;

    public List<PlayerTask> tasks;

    public boolean alive;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public PlayerRole getRole() {
        return role;
    }

    public PlayerColor getColor() {
        return color;
    }

    public boolean canDoTask(Location location, TaskType taskType) {
        return findTaskByStage(location, taskType) != null;
    }

    public PlayerTask findTaskByStage(Location location, TaskType taskType) {
        for (var task : tasks) {
            var stage = task.getNextStage();
            if (stage == null) continue;
            if (stage.getTaskType() == taskType && stage.getLocation() == location)
                return task;
        }
        return null;
    }

}
