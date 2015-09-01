package ru.apache_maven.SecondJavaTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Collections;

/** 
 * Class used for print a list of files/directorys in the directory 
 * that is specified in the run configuration.
 * If the directory is not specified, the class analyzes the current directory.
 * @version 1.3.
 * @author Vdovichenko A.A.
 */

public class PrintFileList{

/**
 * Class that defines the analyzed directory.
 * @param path - the path-name of directory.
 * @return name of a given path-name of the specified directory or otherwise
 *  of the current directory.
 */
	
  public static File specificDirectory(String path){
/** An object refers to the current directory. */
     String userDirectory = System.getProperty("user.dir");
			
/** Set the directory for analysis. */
     File directory;
			
/** Analyzed the current directory if the directory path is not specified. */
     if(path.length() == 0)
       directory = new File(userDirectory);
     else
       directory = new File(path);
				
     return directory;
  }
  
/**
 * Class create an array containing the names of directories and files.
 * @param directory - analyzed directory.
 * @return mainArray - array in which the names are located in the unsorted form.
 */
  public static ArrayList<File> createArray (File directory){
  		
/** Object to represent the path list of directories and files. */
     File[] fileSpisok = directory.listFiles();

/** 
 * Primary and secondary arrays for storage of path-names of files and directories.
 * Without secondary array algorithm does not include all path-names of the files and 
 * directories to the list.
 */
     ArrayList<File> mainArray = new ArrayList<File>(Arrays.asList(fileSpisok));
     ArrayList<File> secondaryArray = new ArrayList<File>();
     
/** Slider for iteration, which runs through the list items. */
     ListIterator<File> slider = mainArray.listIterator();

/** In this series path-names of files and directories are written in array.*/
     while (slider.hasNext()) {      /** While slider has not passed the entire list... */
       File file = slider.next();     /** Get the value of the slider (path-name of the selected element of the list). */
        
       if (file.isDirectory()) {        /** Check whether the value of the slider a directory (subdirectory). */ 
         int count = slider.nextIndex();                        /** Sent position value of sliders to the "count". */ 
         slider.remove();              /** Delete values of slider. */
         mainArray.addAll(Arrays.asList(file.listFiles()));      /** Content of subdirectory is added to the end of the list. */
         secondaryArray.add(file);				         /** Subdirectory name is recorded at the end of the secondary array. */
         slider = mainArray.listIterator(count-1);             /** Pass the value, which ended, of the runner, with a view of the completed list. */
   
       } 
     }

/** Secondary array is included in the main. */
     for (int i = 0; i < secondaryArray.size(); i++)       
       mainArray.add(secondaryArray.get(i));
 		
/** Return the main array. */
     return mainArray;		
 		
  }

 /**
  * In this class the main array is divided into two arrays. 
  * One contains directory path-names, the other - path-names of the files.
  * Then arrays are sorted and printed.
  * @param array - main array with the path-names of directories and files
  */
  public static void sortAndWrite (ArrayList<File> mainArray){
 		
/** Array for write path-names of directories. */
     ArrayList<File> arrayOfFiles = new ArrayList<File>();
     
/** Array for write path-names of files. */
     ArrayList<File> arrayOfDirectories = new ArrayList<File>();

/** Size of the main array. */
     int a = mainArray.size();
 		
/** Slider for iteration. */
     ListIterator<File> slider = mainArray.listIterator();

/** In this cycle, list items distributed on separate arrays. */
     for (int i = 0; i < a; i++) {
       File file1 = slider.next();
       
       if (file1.isDirectory())    /** The element of the array is analyzed. */
         arrayOfFiles.add(mainArray.get(i));  /** If the element is a file, then write it in array of files.*/
       else
         arrayOfDirectories.add(mainArray.get(i));  /** If not - into the array of directories. */
     }

/** 
 * Sorts the values of arrays with path-names of files and directories.
 * First places in the list of path-names of files and directories get 
 * those which closest to the original directory. 
 */
     Collections.sort(arrayOfFiles);
     Collections.sort(arrayOfDirectories);
 		
     int b = arrayOfFiles.size();
     int c = arrayOfDirectories.size();
 		
/** Display the sorted list of directories and files. */
     System.out.println("------------Directories------------");
     
     for (int i = 0; i < b; i++)
       System.out.println(arrayOfFiles.get(i));
 		
       System.out.println();
 		
       System.out.println("------------Files------------");
       
       for (int i = 0; i < c; i++)
         System.out.println(arrayOfDirectories.get(i));
  }
 	
  public static void main(String[] args){
     sortAndWrite(createArray(specificDirectory(args[0])));
  }

}
