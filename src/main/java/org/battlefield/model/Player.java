package org.battlefield.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Player {
    private final String id;
    private final String name;
    private final List<String> gamesPlayed;
    private final List<String> ships;
}
