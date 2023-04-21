package org.battlefield.controller;

import lombok.AllArgsConstructor;
import org.battlefield.model.Ship;
import org.battlefield.service.ShipService;

import java.util.List;

@AllArgsConstructor
public class ShipController {
    private final ShipService shipService;

    public boolean addShip(List<Ship> shipList) {
        return shipService.addShip(shipList);
    }
}
