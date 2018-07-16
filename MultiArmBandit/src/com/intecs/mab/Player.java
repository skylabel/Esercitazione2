package com.intecs.mab;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    private Username username;
    private String name;
    private String born;
    private String strategyType;

    public Player(Username username, String playerName, String birthdate, String strategyType) {
        if (playerName.equals(null) || birthdate.equals(null) || strategyType.equals(null))
            throw new NullPointerException();
        this.username=username;
        this.born = birthdate;
        this.name = playerName;
        this.strategyType = strategyType;
    }

    public abstract int[] playgame(MultiArm multiArm);

    public String getUserName() {
        return username.getValue();
    }

    public String getName() {
        return name;
    }

    public String getStrategyType() {
        return strategyType;
    }

    public String getBorn() {
        return born;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return  Objects.equals(username, player.username) &&
                Objects.equals(name, player.name) &&
                Objects.equals(born, player.born) &&
                Objects.equals(strategyType, player.strategyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, born, strategyType);
    }

}
