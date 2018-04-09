/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class kmeansclustering {  
    
    
    public static void ReadData( ArrayList<ArrayList<Integer>> data) throws FileNotFoundException, IOException
    {
        
        //banknote
          //      InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\banknotedataset\\banknote.xlsx");
        
        //cmc
          //      InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\cmc\\cmc.xlsx");       
        
            //glass        
              //   InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\glass\\glassforweka.xlsx");
                
        //iris
                 // InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\iris\\irisorigional.xlsx");
                  
                  //pima
               //   InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\pima\\pima.xlsx");
                  
                  //wheatseeds
               //   InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\wheatseeds\\seeddataforweka.xlsx");
                  
                   //wine
                 // InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\wine\\winedataforweka.xlsx");        
        
                  //ionosphere
              //    InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\ionosphere\\ionosphere.xlsx");
                  
                  //sonar
                  InputStream ExcelFileToRead = new FileInputStream("E:\\datsetforcluster\\sonar\\sonar.xlsx");
                
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();
                
                
                 
                 int i=0;

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
                         
                          data.add(new ArrayList<Integer>());
			while (cells.hasNext())
			{
                          
				cell=(XSSFCell) cells.next();
		
				
				 if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
                                    
					
                                          int val=  (int) cell.getNumericCellValue();
                                          data.get(i).add(val);
                                        
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
                        
                        i++;
             
		}
	
    }
    
  
 
 private static void CreateOutFiles(ArrayList<ArrayList<Integer>> clust,int num) throws IOException
  {
      int fn=num+1;
      
      //banknote
      // String filename="E:\\datsetforcluster\\banknotedataset\\kmeansclusterbanknote\\"+"KmeansCluster"+fn+".txt";
      //cmc
      // String filename="E:\\datsetforcluster\\cmc\\kmeansclustercmc\\"+"KmeansCluster"+fn+".txt";
        //glass
      // String filename="E:\\datsetforcluster\\glass\\kmeansglassclustering\\"+"KmeansCluster"+fn+".txt";
       //iris
     //  String filename="E:\\datsetforcluster\\iris\\kmeansirisclustering\\"+"KmeansCluster"+fn+".txt";
      //pima
    //  String filename="E:\\datsetforcluster\\pima\\kmeansclusterpima\\"+"KmeansCluster"+fn+".txt";
      //wheat 
     //  String filename="E:\\datsetforcluster\\wheatseeds\\kmeansclusterwheatseeds\\"+"KmeansCluster"+fn+".txt";
      //wheat 
     //  String filename="E:\\datsetforcluster\\wine\\kmeansclusterwine\\"+"KmeansCluster"+fn+".txt";
       //ionosphere 
      // String filename="E:\\datsetforcluster\\ionosphere\\kmeansclusterionos\\"+"KmeansCluster"+fn+".txt";
       //ionosphere 
       String filename="E:\\datsetforcluster\\sonar\\kmeanssonarcluster\\"+"KmeansCluster"+fn+".txt";
       
       
       System.out.println(filename);
      try {
	     File file = new File(filename);
	
             boolean fvar = file.createNewFile();
	     if (fvar){
	          System.out.println("File has been created successfully");
	     }
	     else{
	          System.out.println("File already present at the specified location");
	     }
    	} catch (IOException e) {
    		System.out.println("Exception Occurred:");
	        e.printStackTrace();
	  }
      
                   
      
                   FileWriter Fwrite = new FileWriter(filename,true);
                     
                    for(int i=0;i<clust.get(num).size();i++)
                    {
                     char c=(char) (clust.get(num).get(i)+'0');
                     
                      Fwrite.write(' ');
                      Fwrite.write(c);
                     
                      
                    }
                        
                  Fwrite.close();  
               
  }
  
    
    private static void print(ArrayList<ArrayList<Integer>> data){
        
        for(int i=0;i<data.size();i++)
        {
           // System.out.println("i"+data.get(i).size());
            for(int j=0;j<data.get(i).size();j++)
            {
                System.out.print(+data.get(i).get(j)+" ");
            }
            System.out.println();
        }
    }
    private static void UpdateCentroid(int index,float [][]centroid,int []a)
    {
        System.out.println("index-"+index);
       // System.out.println(+centroid.length);
        
        for(int i=0;i<centroid[index].length;i++)
        {
           
             float temp=centroid[index][i];
            centroid[index][i]=(a[i]+centroid[index][i])/2;
            
        }
         System.out.println("Updated Centroid:");
         for(int i=0;i<centroid.length;i++)
         {
             for(int j=0;j<centroid[index].length;j++)
             {
                 System.out.print(+centroid[i][j]+" ");
             }
             System.out.println();
         }
        
       
        System.out.println("\n");
       
    }
    private static int CalculateDis(int []a,float [][]centroid)
    {
        float min_length=Float.MAX_VALUE;
        int index=Integer.MAX_VALUE;
        
        
        for(int i=0;i<centroid[0].length;i++)
        System.out.print(+a[i]+" ");
        System.out.println();
        
        
        for(int i=0;i<centroid.length;i++)
        {
            float dis=0;
            for(int j=0;j<centroid[i].length;j++)
            {
                
               dis+=Math.pow(a[j]-centroid[i][j], 2);
               
            }
            System.out.println("Eu.Dist->"+Math.sqrt(dis));
            if(min_length>Math.sqrt(dis))
            {
                 
                min_length=(float) Math.sqrt(dis);
                index=i;
            }
            
        }
       
        UpdateCentroid(index,centroid,a);
      
        return index;
    }
    
    private static void  FindClusterUtil(ArrayList<ArrayList<Integer>> clust,float [][]centroid,
            ArrayList<ArrayList<Integer>> data){
        
        for(int i=0;i<centroid.length;i++)
        {
            clust.add(new ArrayList<Integer>());
        }
        
        
        
        for(int i=0;i<data.size();i++)
        {
            int a[]=new int[data.get(i).size()-1];
            for(int j=0;j<data.get(i).size()-1;j++)
            {
                a[j]=data.get(i).get(j);
            }
            
            System.out.println("FileNo->"+i);
            
            int index=CalculateDis(a,centroid);
              // System.out.println("index-"+index);
            clust.get(index).add(data.get(i).get(data.get(i).size()-1));   
        }
    }
    
    private static void Find_cluster(ArrayList<ArrayList<Integer>> data, ArrayList<ArrayList<Integer>> clust)
    {
        System.out.println("Enter Number of cluster");
        Scanner s= new Scanner(System.in);
        int cluster=s.nextInt();
       
        float [][] centroid=new float[cluster][data.get(0).size()-1];
       
        
      // System.out.println(centroid.length+" ,"+centroid[0].length);
        int k=0;
        int c=0;
        int size=data.size();
        int cluster_break_point=size/cluster;
        while(c<cluster)
        {
            
           for(int i=0;i<data.get(k).size()-1;i++)
           { 
               centroid[c][i]=data.get(k).get(i);
           }
           c++;
          
           k+=cluster_break_point;
        }
        
        System.out.println("Initial Centroid:");
        for(int i=0;i<cluster;i++)
        {
            for(int j=0;j<data.get(0).size()-1;j++)
            System.out.print(+centroid[i][j]+" ");
            System.out.println();
        }
        System.out.println("\n");
          FindClusterUtil(clust,centroid,data);
          int sum=0;
          for(int i=0;i<clust.size();i++)
          {
              for(int j=0;j<clust.get(i).size();j++)
              {
                  System.out.print(" "+clust.get(i).get(j));
              }
              sum+=clust.get(i).size();
              System.out.println();
          }
          System.out.println(+sum);
        
        
    }
    
    private static void Kmeans() throws IOException
    {
        ArrayList<ArrayList<Integer>> data=new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> clust=new ArrayList<ArrayList<Integer>>(); 
        ReadData(data);
        print(data);
        Find_cluster(data,clust);
        for(int i=0;i<clust.size();i++)
        CreateOutFiles(clust,i); 
    }
    
    public static void main(String [] args) throws IOException
    {
        Kmeans();
    }
    
}