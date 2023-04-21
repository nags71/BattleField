package org.battlefield.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Missile {
    private final String id;
    private final int x;
    private final int y;
    private final String playerId;
    private final String battleFieldId;
}
