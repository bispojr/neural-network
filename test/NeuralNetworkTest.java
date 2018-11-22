/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Map;
import neuralnetworkja.NeuralNetwork;
import neuralnetworkja.Neuron;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordsfile.WordsFile;

/**
 *
 * @author Rafael
 */
public class NeuralNetworkTest {

    public NeuralNetworkTest() {
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
    // @Test
    // public void hello() {}
    @Test
    public void activationThresholdTeste() {
        NeuralNetwork neural = new NeuralNetwork();

        int real = neural.activationThreshold(-20);

        assertEquals("Metodo activationThreshold esta com problemas.", -1, real);
    }

    @Test
    public void errorMinimizationTest() {
        NeuralNetwork neural = new NeuralNetwork();

        float actualError = -4;
        float oldError = 1.4f;

        assertEquals("Metodo errorMinimizationTest esta com problemas.", 3.857, neural.errorMinimization(actualError, oldError), 0.001);
    }

    @Test
    public void meanErrorTest() {
        HashMap<Float[], Integer> trainingSet = new HashMap<Float[], Integer>();

        float[] x1 = new float[]{1f, 9.4f, 2.5f, 8f, 0.5f, 7.9f, 7f, 2.8f, 1.2f, 7.8f};
        float[] x2 = new float[]{1f, 6.4f, 2.1f, 7.7f, 2.2f, 8.4f, 7f, 0.8f, 3f, 6.1f};
        Integer[] saida = new Integer[]{1, -1, 1, -1, 1, -1, -1, 1, 1, -1};

        for (int i = 0; i < x1.length; i++) {
            Float[] pair = new Float[]{x1[i], x2[i]};
            trainingSet.put(pair, saida[i]);
        }

        NeuralNetwork neural = new NeuralNetwork(0.6f, trainingSet);

        assertEquals("Metodo meanError esta com problemas.", 24, neural.meanError(), 0.001);
    }
    
    @Test
    public void meanErrorTest01()
    {
        HashMap<Float[], Integer> andNeural = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {0f,0f,1f,1f};
        Float[] x2 = new Float[] {0f,1f,0f,1f};
        Integer[] expectedResult = new Integer[] {-1,-1,-1,1};
        float trainingTh = 0.03f;
        
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            andNeural.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, andNeural);
        
        
        assertNotEquals("Metodo AND esta com problemas.", 0f, neural.meanError(), 0.001);
    }

    @Test
    public void computeTest() {
        HashMap<Float[], Integer> trainingSet = new HashMap<Float[], Integer>();
        NeuralNetwork neural = new NeuralNetwork();

        float[] x1 = new float[]{2.5f}; //1, 9.4f, 2.5f, 8f, 0.5f, 7.9f, 7f, 2.8f, 1.2f, 7.8f};
        float[] x2 = new float[]{2.1f}; //1, 6.4f, 2.1f, 7.7f, 2.2f, 8.4f, 7f, 0.8f, 3f, 6.1f};
        Integer[] saida = new Integer[]{-1};//1, -1, 1, -1, 1, -1, -1, 1, 1, -1};

            Float[] pair = new Float[]{x1[0], x2[0]};
            trainingSet.put(pair, saida[0]);

        assertNotEquals("Metodo compute esta com problemas.", saida[0], neural.compute(pair), 0.001);
        
    }
    
    @Test
    public void adjustedWeightTest()
    {
        HashMap<Float[], Integer> perceptronLivro = new HashMap<Float[], Integer>();
        

        float[] x1 = new float[]{1f, 9.4f, 2.5f, 8f, 0.5f, 7.9f, 7f, 2.8f, 1.2f, 7.8f};
        float[] x2 = new float[]{1f, 6.4f, 2.1f, 7.7f, 2.2f, 8.4f, 7f, 0.8f, 3f, 6.1f};
        Integer[] saida = new Integer[]{1, -1, 1, -1, 1, -1, -1, 1, 1, -1};
        float trainingTh = 0.2f;
        
        for (int i = 0; i < x1.length; i++) {
            Float[] pair = new Float[]{x1[i], x2[i]};
            perceptronLivro.put(pair, saida[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, perceptronLivro);
        neural.train();
        
        
        assertNotEquals("Metodo compute esta com problemas.", -3.01f, neural.neuronLayer[0].w[0], 0.001);
        assertNotEquals("Metodo compute esta com problemas.", -2.06f, neural.neuronLayer[0].w[1], 0.001);
        assertNotEquals("Metodo compute esta com problemas.", -1.00f, neural.neuronLayer[0].w[2], 0.001);       
    }   
    
    
    @Test
    public void neuralAltoPequenoTest()
    {
        HashMap<Float[], Integer> altoPequeno = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {1.87f, 1.35f, 1.90f, 1.75f, 1.20f, 1.69f, 1.88f, 1.44f, 1.37f, 1.22f};
        Float[] x2 = new Float[] {0.25f, 0.9f, 1.0f, 0.87f, 0.5f, 0.6f, 0.67f, 0.33f, 0.77f, 0.40f};
        Integer[] expectedResult = new Integer[] {-1, 1, -1,-1, 1,-1,-1,1,1,1};
        float trainingTh = 0.004f;      

        for(int i = 0; i<x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            altoPequeno.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, altoPequeno);
        neural.train();
            
        assertNotEquals("Metodo compute esta com problemas.", 0f, neural.meanError(), 0.001);     
    }
    
    @Test
    public void neuralANDTest()
    {
        HashMap<Float[], Integer> andNeural = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {0f,0f,1f,1f};
        Float[] x2 = new Float[] {0f,1f,0f,1f};
        Integer[] expectedResult = new Integer[] {-1,-1,-1,1};
        float learningRate = 0.1f;
        
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            andNeural.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(learningRate, andNeural, 100);
        neural.train();
        
        assertEquals("Metodo AND esta com problemas.", 0f, neural.meanError(), 0.001);
             
    }
    
    @Test
    public void neuralANDTest01()
    {
        HashMap<Float[], Integer> andNeural = new HashMap<Float[], Integer>();
        HashMap<Float[], Integer> andNeural2 = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {0f,0f,1f,1f};
        Float[] x2 = new Float[] {0f,1f,0f,1f};
        Float[] x3 = new Float[] {0f,1f,1f,1f};
        Float[] x4 = new Float[] {1f,0f,0f,1f};
        Integer[] expectedResult = new Integer[] {-1,-1,-1,1};
        
        float learningRate = 0.03f;
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            Float[] pair2 = new Float[] {x3[i], x4[i]};
            andNeural.put(pair, expectedResult[i]);
            andNeural2.put(pair2, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(learningRate, andNeural, 500);
        NeuralNetwork neural2 = new NeuralNetwork(learningRate, andNeural2, 500);
        neural.train();
        neural2.train();
        
        assertEquals("O metodo AND01 esta com problemas. 0 ", 0f, neural.meanError(), 0.001);
        assertEquals("O metodo AND01 esta com problemas. 1 ", 0f, neural2.meanError(), 0.001); 
    }
    
            
    @Test
    public void neuralORTest()
    {
        HashMap<Float[], Integer> orNeural = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {0f,0f,1f,1f};
        Float[] x2 = new Float[] {0f,1f,0f,1f};
        Integer[] expectedResult = new Integer[] {-1,1,1,1};
        float trainingTh = 0.003f;
        
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            orNeural.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, orNeural, 500);
        neural.train();
        
        assertEquals("Metodo OR esta com problemas.", 0f, neural.meanError(), 0.001);
             
    }
    
    @Test
    public void neuralXORTest()
    {
        HashMap<Float[], Integer> xorNeural = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {1f,1f,0f,0f};
        Float[] x2 = new Float[] {1f,0f,1f,0f};
        Integer[] expectedResult = new Integer[] {-1,1,1,-1};
        float trainingTh = 0.03f;
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i], x2[i]};
            xorNeural.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, xorNeural, 500);  
        neural.train();
        
        assertNotEquals("Metodo AND esta com problemas.", 0f, neural.meanError(), 0.001);
    }
    
    @Test
    public void neuralNOTTest()
    {
        HashMap<Float[], Integer> notNeural = new HashMap<Float[], Integer>();
        
        Float[] x1 = new Float[] {0f, 1f};
        Integer[] expectedResult = new Integer[] {1, -1};
        float trainingTh = 0.3f;
        
        for(int i = 0; i < x1.length; i++)
        {
            Float[] pair = new Float[] {x1[i]};
            notNeural.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(trainingTh, notNeural, 500);
        neural.train();
        
        assertEquals("Metodo NOT esta com problemas.", 0f, neural.meanError(), 0.001);            
    }
    
    @Test
    public void stackoverflowTest()
    {
        HashMap<Float[], Integer> so = new HashMap<Float[], Integer>();
        WordsFile file = new WordsFile("teste.txt");
        file.openFile();
        Float[] x1 = new Float[36];
        
        float traininTh = 0.3f;
        //Remeber the 1 is for array conjuct and -1 for json
        Integer[] expectedResult = new Integer[] {1, 1, -1, 1, -1, -1, -1, 1, -1, 
                                                 1, -1, 1, -1, -1, -1, 1, 1, 1,
                                                  1, 1, 1, -1, -1, -1, 1, 1, 1,
                                                 -1, -1, -1, 1, 1, 1, -1, -1, -1};
        
        
        for(int i = 0; i < x1.length; i++)
        {
           Float[] pair = new Float[]{file.vWords[i]};
           so.put(pair, expectedResult[i]);
        }
        
        NeuralNetwork neural = new NeuralNetwork(traininTh, so);
        
        assertNotEquals("Metodo stackoverflowTest esta com problemas.", 0f, neural.meanError(), 0.001);
       
    }
}
