package ru.apache_maven.SecondJavaTask;

import java.io.File;

import ru.apache_maven.SecondJavaTask.PrintFileListTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class PrintFileListTest extends TestCase{

  public PrintFileListTest( String testName ){
        super( testName );
  }
 
  public void testOfPrintList(){

/** Setting user directory. */
    String userDirectory = System.getProperty("user.dir");
    File directory = new File(userDirectory);
    
    int vsego = 109;
    
    int result = PrintFileList.createArray(directory).size();

/** Assessed number of files and directories in the user directory. */
    assertTrue( vsego != result);
    
  }
  
  public static Test suite(){
    return new TestSuite( PrintFileListTest.class );
  }

  public void testApp(){
      
    TestRunner runner = new TestRunner();
    TestSuite suite = new TestSuite();
  
    suite.addTest(new PrintFileListTest("testOfPrintList"));
    runner.doRun(suite);
  }
}
