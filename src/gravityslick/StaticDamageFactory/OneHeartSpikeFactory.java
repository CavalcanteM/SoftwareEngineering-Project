/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityslick.StaticDamageFactory;

import Entities.Spikes.OneHeartSpike;
import Entities.Spikes.StaticDamage;
import java.util.ArrayList;

/**
 *
 * @author Adria
 */
public class OneHeartSpikeFactory implements InterfaceStaticDamageFactory {

    
    
    
    public OneHeartSpikeFactory()
    {
        
    }
    
    
    public StaticDamage create(int x,int y) {
      return  new OneHeartSpike(x,y);
        
    }
    
}
