/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaussjordan1;

/**
 *
 * @author marce
 */
public class GaussJordan1 {
  
    public static Boolean garantePivosNaoNullos(double matriz[][], int qntdLinha, int qntdColuna){
        int tamVet= qntdColuna;
        double auxVet[]= new double[tamVet];
        int troqueiLinha[] = new int[tamVet];
 
        for(int k=0; k<qntdLinha; k++){
            if(matriz[k][k]==0){
                for(int i=0; i<qntdLinha; i++){
                    if(i!=k &&matriz[i][k]!=0 && troqueiLinha[i]==0){
                        troqueiLinha[k]=1; //para não alterarem a linha já trocada
                        for(int j=0; j<qntdColuna; j++){
                            auxVet[j]=matriz[k][j]; //pegando a linha problemática
                            matriz[k][j]=matriz[i][j];//pegando a linha solucao
                            matriz[i][j]=auxVet[j]; //linha solucao recebendo a problemática  
                        }
                     i=qntdLinha;//se já trocou com essa linha, n precisa procurar outra
                    }//Matriz.imprimeMatriz(matriz, qntdLinha, qntdColuna);
                    
                }
            }
        }
        return true;
    }
    
    public static double[][] garantePivosComValorUm(double matriz[][],int qntdLinha, int qntdColuna){
        double dividendo=1;
        for(int k=0; k<qntdLinha; k++){
            if(matriz[k][k]!=1){
                //System.out.println("Matriz ["+k+"]["+k+"] = "+matriz[k][k]);
                dividendo = matriz[k][k];
                //System.out.println("Pivo = "+dividendo);
                for(int j=0; j<qntdColuna; j++){
                    if(matriz[k][j]/dividendo == -0) matriz[k][j]=0;
                    else matriz[k][j]=matriz[k][j]/dividendo; //linha solucao recebendo a problemática  
                } 
            }
        }
            return matriz;
    }
    
    public static void metodoGaussJordan(double matriz[][], double matrizResultado[][],int qntdLinha, int qntdColuna){ 
        for(int k=0; k<qntdLinha; k++){
                for(int i=0; i<qntdLinha; i++){
                    if(i!=k && matriz[i][k]!=0){  
                        for(int j=0; j<qntdColuna; j++){     
                            System.out.printf("Matriz conta ["+i+"]["+j+"] (%.4f) \n ",matriz[i][j]);
                            System.out.printf("= Matriz["+i+"]["+k+"]  (%.4f) ",(matriz[i][k]));
                            System.out.printf("* Matriz["+k+"]["+j+"]  (%.4f) ",matriz[k][j]);
                            System.out.printf("+ Matriz["+i+"]["+j+"]  (%.4f) ",matriz[i][j]);
                            double conta1=(double)(matriz[i][k]*matriz[k][j]);
                            double conta2=(conta1*(double)-1)+matriz[i][j];
                            matrizResultado[i][j]= conta2;//Li = - pivoColuna*Lpivo+Li
                            System.out.printf("= %.4f \n ",conta2);
                        }     
                    }   
                } 
            Matriz.imprimeMatriz(matrizResultado, qntdLinha, qntdColuna);
            matriz=Matriz.copiaMatriz(matrizResultado, qntdLinha, qntdColuna);
            matriz=garantePivosComValorUm(matriz, qntdLinha, qntdColuna); // se o pivô chegar a mudar o valor...
            
            Matriz.imprimeMatriz(matriz,qntdLinha, qntdColuna);
        }
    }
    
    public static void main(String[] args) {
        double matriz[][]=Arquivo.lerEGravarMatriz();
        int qndtColuna=matriz[0].length, qndtLinha=matriz.length;
        System.out.println("Matriz Inicial");
        Matriz.imprimeMatriz(matriz, qndtLinha, qndtColuna);
        
        System.out.println("\nMatriz Reorganizada");
        GaussJordan1.garantePivosNaoNullos(matriz, qndtLinha, qndtColuna);
        Matriz.imprimeMatriz(matriz, qndtLinha, qndtColuna);
        
        System.out.println("Matriz com pivôs valor 1");
        GaussJordan1.garantePivosComValorUm(matriz, qndtLinha, qndtColuna);
        Matriz.imprimeMatriz(matriz, qndtLinha, qndtColuna);
        //System.out.printf("Matriz ["+2+"]["+0+"] = %.2f\n", matriz[2][0]);
        System.out.println("");
        Matriz.imprimeMatriz(matriz, qndtLinha, qndtColuna);
        double matrizResultado[][] = Matriz.copiaMatriz(matriz, qndtLinha, qndtColuna);
        GaussJordan1.metodoGaussJordan(matriz,matrizResultado, qndtLinha, qndtColuna);
       // Matriz.imprimeMatriz(matriz, qndtLinha, qndtColuna);
        
        
    }
    
}
