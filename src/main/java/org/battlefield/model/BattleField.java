package org.battlefield.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class BattleField {
    private final String id;
    private final List<String> missiles;
    private final List<String> ships;
    private String[][] grid;
    private final List<String> players;
    private String loserId;

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public void setLoserId(String playerId) {
        this.loserId = playerId;
    }
}
