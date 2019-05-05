
/**
 * Write a description of class FileComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FileComparator implements Comparable<FileComparator> 
{
    private String first; 
    private String second; 
    private int common;
    
    public FileComparator(){
        first = ""; 
        second = ""; 
        common = 0; 
    }
    
    public FileComparator(String f, String s, int c){
        first = f; 
        second = s; 
        common = c; 
    }
    
    public int getCommon(){
        return common;
    }
    
    public int compareTo(FileComparator file){
        return file.getCommon()-common; 
    }
    
    
    public String toString(){
        String str = ""; 
        str = "[" + first + ", " + second + "] -> " + common; 
        return str; 
    } 
}
