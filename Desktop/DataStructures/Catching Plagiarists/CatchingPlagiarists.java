import java.util.*; 
import java.util.ArrayList;
import java.io.File; 
/**
 * Write a description of class CatchingPlagiarists here.
 *
 * @author (Aarthi Koripelly)
 * @version (April 2019)
 */
public class CatchingPlagiarists  
{
    public static void main(String[] args) throws Exception{
        System.out.print("Welcome to Catching Plagiarists!"); 
        System.out.println("The application has three sets of documents: small, medium, large. "); 
        System.out.println("The software will sort through and catch plagarism apparent in each set.");
        System.out.println("Below, answer which document you would like to sort.");
        System.out.println("After, input the number of word sequences that you would like to look for.");
        System.out.println("Then, enter the maximum number of hits."); 
        
        System.out.println("Enter 1 for the small document set.");
        System.out.println("Enter 2 for the medium document set.");
        System.out.println("Enter 3 for the large document set.");
        
        int doc = 0; 
        int n = 0; 
        int maxHit = 0; 
        System.out.println("\n" + "Which document would you like to sort?"); 
        Scanner scanner = new Scanner(System.in); 
        doc = scanner.nextInt();
        scanner.close(); 
        
        System.out.println("\n" + "What number of word sequences would you like?"); 
        Scanner scanner1 = new Scanner(System.in); 
        n = scanner1.nextInt(); 
        scanner1.close();
        
        System.out.println("\n" + "What number of maximum hits would you like?"); 
        Scanner scanner2 = new Scanner(System.in); 
        maxHit = scanner2.nextInt(); 
        
        File dir = new File(".");
        ArrayList<File> directories = new ArrayList<File>();
        for(File folder : dir.listFiles()){
            if(folder.isDirectory()){
                    directories.add(folder);
                }
        }

        String fileName = "five"; 
        if(doc == 1){
            fileName = "Small"; 
        }
        if(doc == 2){
            fileName = "Medium";
        }
        if(doc == 3){
            fileName = "Large";
        }
        
        File name = new File(fileName);
        String[] temp = name.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++){
            if(temp[i].endsWith(".txt")){
                    files.add(temp[i]);
                }
        }
        
        
        System.out.println( "\n" + "Here is the list of documents and the number of matching words between two respective document: ");
        ArrayList<ArrayList<String>> list =  new ArrayList<ArrayList<String>>(); 
        list = nWords(n, fileName, temp); 
        ArrayList<FileComparator> finalList = new ArrayList<FileComparator>(); 
        finalList = createList(n, temp, list);
        Collections.sort(finalList); 
        for(int i =0; i < maxHit; i++){
                System.out.println( "\n" + finalList.get(i).toString()); 
        }
        }
    
    public static ArrayList<ArrayList<String>> nWords(int numWords, String size, String[] temp) throws Exception{
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>(); 
        for(int i = 0; i < temp.length; i++){
           ArrayList<String> listWords = new ArrayList<String>(); 
           String name = size+"/"+temp[i]; 
           Scanner file = new Scanner(new File(name)); 
           int count = 0; 
            while(file.hasNext()){
                String phrase = ""; 
                //read numWords words from file 
                for(int j = 0; j < numWords; j++){
                    //string away punctuation and set to lowercase 
                    if(file.hasNext()){
                        phrase += file.next().replaceAll("[^A-z]","").toLowerCase(); 
                    }
                    else{
                        phrase = null; //not enough words at end  
                    }
                }
                count++; 
                file.close(); 
                file = new Scanner(new File(name));
                for(int p =0; p < count; p++){
                    if(file.hasNext()) file.next(); 
                }
                listWords.add(phrase); 
           }
           list.add(listWords);  
        }
        return list; 
     }
     
    public static ArrayList<FileComparator> createList(int n, String[] temp, ArrayList<ArrayList<String>> list){
        ArrayList<FileComparator> finalList = new ArrayList<FileComparator>();
        int common = 0; 
        for(int i =0; i < list.size()-1; i++){
            for(int p =i+1; p < list.size(); p++){
                common = compare(list.get(i), list.get(p)); 
                finalList.add(new FileComparator(temp[i], temp[i+1], common)); 
            }
        }
        return finalList; 
    }
    
    public static int compare(ArrayList<String> first, ArrayList<String> second){
        int count = 0; 
        if(first==null || second == null) return 0; 
        for(int i = 0; i < first.size(); i++){
            for(int p = 0; p < second.size(); p++){
                if(first.get(i) != null && second.get(p) != null && first.get(i).equals(second.get(p))){
                    count++; 
                }
            }
        }
        return count; 
    }

   }


