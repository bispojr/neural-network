package neuralnetworkja;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class NeuralNetwork {
    
    public Neuron[] neuronLayer;
    public float learningRate = 0.03f;
    public HashMap<Float[], Integer> trainingSet;
    public int cont = 0;
    public float bias; /*Variavel utilizada para o calculo da primeira entrada*/
    public String activationKind;
    
    public int trainingNumber = 500;
    public boolean varAuxforMeanError = true;
    
    /*variavel utilizada para o calculo do limiar*/
    float trainingThreshold = 0.1f; 
    
    /*
    ****
    CONSTRUTORES
    ****
    */
    
    public NeuralNetwork(){       
        this.neuronLayer = new Neuron[1];
        this.neuronLayer[0] = new Neuron();
        
        this.trainingThreshold = 0.6f;
        this.trainingSet = new HashMap<Float[], Integer>();
        
        float[] x1 = new float[] {1f, 9.4f, 2.5f, 8f, 0.5f, 7.9f, 7f, 2.8f, 1.2f, 7.8f};
        float[] x2 = new float[] {1f, 6.4f, 2.1f, 7.7f, 2.2f, 8.4f, 7f, 0.8f, 3f, 6.1f};
        Integer[] saida = new Integer[] {1, -1, 1, -1, 1, -1, -1, 1, 1, -1};
        
        for(int i=0; i<x1.length; i++){
            Float[] pair = new Float[] {x1[i], x2[i]};
            this.trainingSet.put(pair, saida[i]);
        }  
        
        this.bias = 1;
        this.activationKind = "threshold";
    }
    
    public NeuralNetwork(float learningRate, HashMap<Float[], Integer> trainingSet){
        this.neuronLayer = new Neuron[1];
        this.neuronLayer[0] = new Neuron();
       
        this.trainingSet = trainingSet;
        
        this.learningRate = learningRate;
        this.bias = 1;
        this.activationKind = "threshold";
    }
    
    public NeuralNetwork(float learningRate, HashMap<Float[], Integer> trainingSet, int trainingNumber){
        this.neuronLayer = new Neuron[1];
        this.neuronLayer[0] = new Neuron();
        this.trainingSet = trainingSet;     
        this.trainingNumber = trainingNumber;        
        this.learningRate = learningRate;
        this.bias = 1;
        this.activationKind = "threshold";
        this.varAuxforMeanError = false;
    }
    
    public NeuralNetwork(Neuron[] neuron, float learningRate, HashMap<Float[], Integer> trainingSet, int trainingNumber)
    {     
        this.neuronLayer = neuron;       
        this.learningRate = learningRate;
        this.trainingNumber = trainingNumber;       
        this.trainingSet = trainingSet;       
        this.bias = 1;        
        this.activationKind = "threshold";
        this.varAuxforMeanError = false;
    }
    
    public NeuralNetwork(Neuron[] neuron, float learningRate, HashMap<Float[], Integer> trainingSet)
    {     
        this.neuronLayer = neuron;       
        this.learningRate = learningRate;
        this.trainingSet = trainingSet;       
        this.bias = 1;
        this.activationKind = "threshold";
    }
    
    /**************************FIM DOS CONSTRUTORES*****************************/
    
    /*
    Função responsável por fazer o treino da rede
    */
    public void train(){
        
        float oldError = Float.MAX_VALUE;
        float actualError = 0;
                               
        for(int j=0;j<this.trainingNumber;j++)
        {
            for (Map.Entry p : this.trainingSet.entrySet())
            {
                Float[] entry = (Float[])p.getKey();
                Integer expectedOutput = (Integer)p.getValue();
                
                float realOutput = compute(entry);                
                
                if(expectedOutput != 1 && realOutput > 0)
                    adjustWeight(expectedOutput, -1, entry);
                else if(expectedOutput != -1 && realOutput < 0)
                    adjustWeight(expectedOutput, 1, entry);
            }       
            
            if(varAuxforMeanError)
            {
                actualError = meanError();                    
            
                if(errorMinimization(actualError, oldError) < this.trainingThreshold)
                   break;

                oldError = actualError;
            } 
            
           
        } 
      
         System.out.println(this.neuronLayer[0].w[0] + " - " + this.neuronLayer[0].w[1] + " - " + this.neuronLayer[0].w[2]);
         System.out.println(meanError());
    }
    
    
    /*
        FUNCAO DE CALCULO DO ERRO MEDIO
    */
    public float meanError(){
        
        float error = 0;
        
        for (Map.Entry p : this.trainingSet.entrySet())
        {
            Float[] entry = (Float[])p.getKey();
            Integer expectedOutput = (Integer)p.getValue();

            float realOutput = compute(entry); 
            
            error += Math.pow(expectedOutput-realOutput, 2);
        }
        
        return error;
    }
    
    
    /*
        FUNCAO DE ATIVACAO E UTILIZADA PARA FAZER O REAJUSTE DOS PESOS DA 
        REDE NEURAL
    */
    private void adjustWeight(int expectedOutput, int realOutput, Float[] entry)
    {         
        for(Neuron neuron: this.neuronLayer){
            
            neuron.w[0] += (this.learningRate*(expectedOutput + realOutput)) * this.bias;
                       
            for(int i=0; i<entry.length; i++)
                neuron.w[i+1] += (this.learningRate*(expectedOutput + realOutput)) * entry[i];          
       }
    }
      
    /*
        FUNCAO LIMIAR E RESPONSAVEL POR CALCULAR O LIMIAR, SENDO ESSE UMA FUNCAO
        QUE DETERMINA SE A FUNCAO VAI CONTINUAR, DE MODO QUE SE O LIMIAR NAO HOUVER
        MUITA ALTERACOES IRA PARAR, POIS A REDE JA FOI TREINADA O SUFICIENTE
    */
    public float errorMinimization(float actualError, float oldError)
    {   
        return (oldError - actualError)/oldError;   
    }
    
    
    /*
        FUNCAO DE ATIVAÇÃO DE MODO QUE SE O RESULTADO TIVER MAIOR QUE 0, RECEBE
        TENSÃO POSITIVA, SE NÃO NEGATIVA
    */
    public Integer activationThreshold(float result)
    {
        if (result > 0)
            return 1;
        else 
            return -1;
    }
    
    /*
        FUNCAO DE ATIVACAO DE MODO A RETORNAR O TIPO DE REDE QUE E, SENDO NO CASO
        A ATUAL A PERCEPTRON COM SENDO APENAS 1 OU -1
    */
    public Integer activation(float result){
        
        switch(this.activationKind){
            case "threshold":
                return activationThreshold(result);
        }
        
        return null;
    }
    
    /*
        FUNCAO COMPUTE E RESPONSAVEL POR REALIZAR O TREINO DA REDE NEURAL
    */
    public Integer compute(Float[] entry) 
    {    
        float result = 0;
        
        for(Neuron neuron: this.neuronLayer){
            
            //calculo do neuron 0
            result = this.bias*(neuron.w[0]);
            
            //calculo dos demais neuron
            for(int i=0; i<entry.length; i++)
                result += entry[i]*neuron.w[i+1];
        }    
        
        return activation(result);        
    }
    
}
