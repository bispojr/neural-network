/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.Assert;
import neuralnetworkja.Neuron;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void constructorTest() {
        
        Neuron node = new Neuron();
        
        assertEquals(0.75, node.w[0], 0.001);
        assertEquals(0.5, node.w[1], 0.001);
        assertEquals(0.6, node.w[2], 0.001);
    }
    
    @Test
    public void setWTest() {
     
        Neuron node = new Neuron();
        
        node.w[2] = 5;
        
        assertEquals("Metodo setW com problemas!", 5, node.w[2], 0.001);
        
    }

}
