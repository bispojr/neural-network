package wordsfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordsFile {
  
    public String path = ""; 
    public String line;
    public Float[] vWords = new Float[36];
    int index = 0;
    
    public WordsFile()
    {
        String path = "teste.txt";
    }
    
    public WordsFile(String path)
    {
        this.path = path;
    }
    
    
    public void breaking(String line)
    {
        String array[] = new String[25];        
        array = line.split(" ");
        
        
        for(int i = 0; i < array.length; i++)         
            wordsConverter(array[i], index);             
     
        index++;
        
        for(int j = 0; j < vWords.length; j++)
        {
            if(vWords[j] == null) vWords[j] = 0f;
        }
    }
    
    public void openFile() {
        try {          
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                line = br.readLine();
                breaking(line);
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void wordsConverter(String word, int index)
    {
        char[] letras = null;
        String w = word;
              
        Float result = 0f;
        
        letras = w.toCharArray();
        
        for(int i = 0; i<letras.length; i++)
            result += (int)letras[i]; 
        
        vWords[index] = result;      
    }
}
