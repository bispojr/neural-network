/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetworkja;

/**
 *
 * @author Rafael
 */
public class Neuron {
    
    public float w[];
    
    
    public Neuron(){        
        w = new float[3];
        this.w[0] = 0.75f;
        this.w[1] = 0.5f;
        this.w[2] = -0.6f;
    }  
    
    public Neuron(float[] charge)
    {
        w = new float[3];
        this.w[0] = charge[0];
        this.w[1] = charge[1];
        this.w[2] = charge[2];
    }
    

    
    @Override
    public String toString() {
        return "Node{" + "w=" + w + '}';
    }


}
