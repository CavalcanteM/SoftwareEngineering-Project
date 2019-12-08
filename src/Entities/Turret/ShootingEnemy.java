/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Turret;

import Entities.Player;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Heisenberg
 */
public interface ShootingEnemy {

    public void Shoot(Player player);

    public void IncreaseDamage();

    public void IncreaseFireRate();
}
