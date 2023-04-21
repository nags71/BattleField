package org.battlefield.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Ship {
    private final String id;
    private final int x;
    private final int y;
    private final int size;
    private boolean isDestroyed;
    private final String playerId;
    private final String battleFieldId;

    public void setIsDestroyed() {
        this.isDestroyed = true;
    }
}
