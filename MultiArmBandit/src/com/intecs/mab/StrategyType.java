package com.intecs.mab;

import java.util.Objects;

public class StrategyType {

    private String type;

    public StrategyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StrategyType that = (StrategyType) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type);
    }
    
}
