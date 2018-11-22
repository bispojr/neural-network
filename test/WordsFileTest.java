/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class WordsFileTest {
    
    public WordsFileTest() {
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
    public void fileOpenTest()
    {
        WordsFile file = new WordsFile("TextTest.txt");
        file.openFile(); 
        
        for(int j = 0; j < 36; j++)
        {
            System.out.println(file.vWords[j]);
        }
    }
    
//    @Test
//    public void breakingTest()
//    {
//        
//        WordsFile file = new WordsFile();
//        String line = "batata;doce";
//        file.breaking(line);
//    }

    
//    @Test
//    public void test()
//    {
//        char[] letras = null;
//        String s = "batata";
//        int result = 0;
//        
//        letras = s.toCharArray();
//        
//        for(int i = 0; i< letras.length; i++)
//        {
//            System.out.println(letras[i] + "\n");
//            System.out.println((int)letras[i]);
//            
//            result += (int)letras[i];           
//        }
//        System.out.println(result);
//    }
}
