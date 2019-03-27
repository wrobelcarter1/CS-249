package p4_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Class for finding the sum in an two dimensional array
 * 
 * @author CarterWrobel
 *
 */
public class MatrixAnalysisClass
   {
    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 80;
    
    /**
     * constant definition of EOF marker
     */
    private static final int EOF_MARKER = -1;

    /**
     * constant specification of number of spaces 
     * for each recursive level indent
     */
    private final int REC_LEVEL_INDENT = 3;
    
    /**
     * constant definition for COLON    
     */
    private static final char COLON = ':';
    
    /**
     * constant definition for SPACE    
     */
    private static final char SPACE = ' ';
    
    /**
     * constant definition for minus sign
     */
    private static final char MINUS_SIGN = '-';
    
    /**
     * constant control code for display method
     */
    private static final int VALID_ITEM = 101;
    
    /**
     * constant control code for display method
     */
    private static final int INVALID_ITEM = 102;
    
    /**
     * constant control code for display method
     */
    private static final int OVER_SUM = 103;
    
    /**
     * FileReader object used for data upload
     */
    private FileReader fileIn;
   
    /**
     * flag selects show operations
     */
    private boolean verboseFlag;
    
    /**
     * specification of member row height
     */
    private int arrayRowHeight;
    
    /**
     * specification of member row width
     */
    private int arrayRowWidth;
    
    /**
     * two dimensional array for data storage
     */
    private int[][] dataArray;

    /**
     * SimpleGenericSetClass holds locations found during search
     */
    private SimpleGenericSetClass<CellDataClass> locationSet;
    
    /**
     * Default constructor
     */
    /**
     * Default constructor
     */
    public MatrixAnalysisClass()
       {
        verboseFlag = true;
        locationSet = new SimpleGenericSetClass();
       }
    
    /**
     * Initialization constructor
     * 
     * @param verboseSetting boolean flag for verbose setting;
     * indicates whether or not to show search process
     */
    public MatrixAnalysisClass( boolean verboseSetting )
       {
        verboseFlag = verboseSetting;
        locationSet = new SimpleGenericSetClass();
       }
    
    /**
     * Method calls helper to find contiguous values
     * adding to a specified number in an array
     * <p>
     * Note: Displays success or failed results
     * <p>
     * Rules of the searching process:
     * <p> 
     * The search starts in the upper left corner, 
     * reporting each valid location found
     * <p>
     * The method must search to a current location's right,
     * then below it, and then to its left, exactly in that order
     * <p>
     * The method must report what it finds, 
     * either as a successful candidate value
     * or a failed candidate value (and why it failed)
     * <p>
     * The method must use recursive backtracking,
     * it must backtrack the recursion upon any failure,
     * but it must continue forward recursion upon any success
     * until the solution is found
     * <p>
     * The method must be able to handle the condition
     * that the value in the upper left corner does not support the solution 
     * 
     * @param sumRequest integer value indicating desired sum
     * 
     * @return boolean indication of success
     */
    public boolean findSum( int sumRequest )
       {
           System.out.println("Search Start: ");
           
           int runningTotal = 0;
           int xIndex = 0;
           int yIndex = 0;
           int recLevel = 0;
           if( findSumHelper(sumRequest, runningTotal, xIndex,
                             yIndex, recLevel))
           {
               System.out.println("Search End: Successful" +
                                  locationSet.toString());
               return true;
           }
           
           System.out.println("Search End: Failed");
           return false;
       }
    
    /**
     * Helper method to find requested sum in an array
     * <p>
     * Note: Uses displayStatus method for all output
     * (no other printing from this method);
     * displayStatus provides appropriately indented current test location
     * 
     * @param sumRequest integer value indicating desired sum
     * 
     * @param runningTotal integer current sum of search process
     * 
     * @param xIndex integer current x position of search process
     * 
     * @param yIndex integer current y position of search process
     * 
     * @param recLevel integer current level of recursion, 
     * used to set display indent
     * 
     * @return boolean indication of success
     */
    private boolean findSumHelper( int sumRequest, int runningTotal, 
                                       int xIndex, int yIndex, int recLevel )
       {
          // Initialize varibles 
           CellDataClass currentNumber;
           String found = "Found at";
           String invalid = "Location Failed";
           String valid = "Valid Location Found";
           
           // Checks if the index is in bounds
           if( !isInBounds( xIndex, yIndex) )
           {
               displayStatus(recLevel, invalid, null, 17);
               return false;
           }
           // Sets the current number
           currentNumber = new CellDataClass(
                                    dataArray[yIndex][xIndex], xIndex, yIndex);
           // Displays the status
           displayStatus(recLevel, valid, currentNumber, VALID_ITEM);
           
           // Checks if it is over the total
           if(runningTotal > sumRequest)
           {
               // Displays the status
               displayStatus(recLevel, invalid, currentNumber, OVER_SUM);
               return false;
           }
           
           // Checks if the set has the number
           if(locationSet.hasItem(currentNumber))
           {
               // Displays the status
               displayStatus(recLevel, invalid, currentNumber, 3);
               return false;
           }
          
           
           // Checks both to add it to the set after everything else has 
           // been checked
           if((runningTotal < sumRequest) && 
                   !locationSet.hasItem(currentNumber))
           {
               // adds the number to the set
               locationSet.addItem(currentNumber);
               // Updates the running total
               runningTotal += currentNumber.dataVal;
           }
           
           // Checks if its at the bottom
           if((runningTotal == sumRequest) &&
                   (yIndex == arrayRowHeight - 1))
           {
               return true;
           }
           
           // Checks to the right
           if(findSumHelper(sumRequest, runningTotal, xIndex + 1,
                            yIndex, recLevel + 1))
           {
               // Displays the status
               displayStatus(recLevel, found, currentNumber, VALID_ITEM);
               return true;
           }
           // Checks to the one below
           if(findSumHelper(sumRequest, runningTotal, xIndex,
                            yIndex + 1, recLevel + 1))
           {
               // Displays the status
               displayStatus(recLevel, found, currentNumber, VALID_ITEM);
               return true;
           }
           // Checks to the left
           if(findSumHelper(sumRequest, runningTotal, xIndex - 1,
                            yIndex, recLevel + 1))
           {
               // Displays the status
               displayStatus(recLevel, found, currentNumber, VALID_ITEM);
               return true;
           }
           // Removes the value if all of them fail
           runningTotal -= currentNumber.dataVal;
           locationSet.removeItem(currentNumber);  
           
           if((runningTotal == 0) && (yIndex < arrayRowHeight))
           {
               return findSumHelper(sumRequest, runningTotal, xIndex + 1,
                       yIndex, recLevel);
           }
           // Displays the status
           displayStatus(recLevel, invalid, currentNumber, INVALID_ITEM);
           return false;
       }

    /**
     * checks that requested x, y indices of array
     * are not out of bounds
     * 
     * @param xLocTest integer x index to be tested
     * 
     * @param yLocTest integer y index to be tested
     * 
     * @return boolean result of specified test
     */
    private boolean isInBounds( int xLocTest, int yLocTest )
       {   
           // checks if the locations are both greater than 0 and that it
           // is in bounds of the array
           return (  (yLocTest >= 0) && (xLocTest >= 0) &&
                   (xLocTest <  arrayRowWidth) && 
                   (yLocTest <  arrayRowHeight));
       }
    
    /**
     * displays status at indented level
     * <p>
     * Displays for 
     * <p>1) location not found, 
     * <p>2) item already in set,
     * <p>3) item causes over sum condition (opSuccess code OVER_SUM), 
     * <p>4) item has exhausted all child tests and 
     * does not support the solution
     * (opSuccess code INVALID_ITEM),
     * <p>5) item accepted (opSuccess code VALID_ITEM),
     * shows current location 
     * 
     * @param recLevel integer specification of current level
     * 
     * @param status String indication of success or failure
     * 
     * @param opSuccess integer code indicating success or different failures
     * 
     * @param current CellDataClass data at current level
     */
    private void displayStatus( int recLevel, 
                     String status, CellDataClass current, int opSuccess )
       {
           if(verboseFlag)
           {
           String output = "";
           int spaces = 0;
           while ( spaces < (recLevel * REC_LEVEL_INDENT))
           {
               output += SPACE;
               spaces++;
           }
           output += status + COLON + SPACE;
           if( opSuccess == VALID_ITEM)
           {
               output += current.toString();
           }
           else if(opSuccess == INVALID_ITEM)
           {
               output += "Doesn't Support Solution";
           }
           else if(opSuccess == OVER_SUM)
           {
               output += "Over Requested Sum";
           }
           else if(current == null)
           {
               output += "Location Not Found";
           }
           else if(locationSet.hasItem(current))
           {
               output += "Already in Set";
           }
           System.out.println(output);
           }
           
       }

    /**
     * uploads data from requested file
     * 
     * @param fileName name of file to access
     * 
     * @return Boolean result of data upload
     */
    public boolean uploadData( String fileName )
       {
        int rowIndex, colIndex;
        
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
           
            // get leader line ahead of array height
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row height
            arrayRowHeight = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line ahead of array width
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row width
            arrayRowWidth = getAnInt( MAX_INPUT_CHARS );
            
            dataArray = new int[ arrayRowHeight ][ arrayRowWidth ];
            
            for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
               {                
                for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
                   {
                    dataArray[ rowIndex ][ colIndex ] 
                                               = getAnInt( MAX_INPUT_CHARS );
                   }
               }
           }
      
        catch( FileNotFoundException fnfe )
           {
            System.out.println( "DATA ACCESS ERROR:"
                    + " Failure to open input file" );
          
            return false;
           }

        return true;
       }
   
   /**
    * gets a string up to a maximum length or to specified delimiter
    * 
    * @param maxLength maximum length of input line
    * 
    * @param delimiterChar delimiter character to stop input
    * 
    * @return String captured from file
    */
   private String getALine( int maxLength, char delimiterChar )
      {
       int inCharInt;
       int index = 0;
       String strBuffer = "";

       try
          {
           inCharInt = fileIn.read();

           // consume leading spaces
           while( index < maxLength && inCharInt <= (int)( SPACE )  )
              {
               if( inCharInt == EOF_MARKER )
                  {
                   return "EndOfFile";
                  }
               
               index++; 
               
               inCharInt = fileIn.read();
              }
           
           while( index < maxLength && inCharInt != (int)( delimiterChar ) )
              {   
               if( inCharInt >= (int)( SPACE ) )
                  {
                   strBuffer += (char)( inCharInt );

                   index++;
                 }
               
               inCharInt = fileIn.read();             
              }
           
           //inCharInt = fileIn.read();
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       return strBuffer;
      }

    /**
     * gets an integer from the input string
     * 
     * @param maxLength maximum length of characters
     * input to capture the integer
     * 
     * @return integer captured from file
     */
    private int getAnInt( int maxLength )
       {
       int inCharInt;
       int index = 0;
       String strBuffer = "";
       int intValue;
       boolean negativeFlag = false;

       try
          {
           inCharInt = fileIn.read();

           // clear space up to number
           while( index < maxLength && !charInString( 
                   (char)inCharInt, "0123456789+-" ) )
              {
               inCharInt = fileIn.read();
               
               index++;
              }
           
           if( inCharInt == MINUS_SIGN )
              {
               negativeFlag = true;
               
               inCharInt = fileIn.read();
              }

           while( charInString( (char)inCharInt, "0123456789" ) )
              {   
               strBuffer += (char)( inCharInt );

               index++;
               
               inCharInt = fileIn.read();
              }            
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       intValue = Integer.parseInt( strBuffer );
       
       if( negativeFlag )
          {
           intValue *= -1;
          }
       
       return intValue;
       }

    public void dumpArray()
       {
        int rowIndex, colIndex;
        
        System.out.println( "\nMatrixAnalysisClass Diagnostic Array Dump:" );
        
        for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
               {
                if( colIndex > 0 )
                   {
                    System.out.print( ", " );
                   }
                
                System.out.print( dataArray[ rowIndex ][ colIndex ] );
               }
            
            System.out.println();
           }
        
        System.out.println();
       }
    
    /**
     * tests and reports if a character is found in a given string
     * 
     * @param testChar character to be tested against the string
     * 
     * @param testString string within which the character is tested
     * 
     * @return Boolean result of test
     */
    private boolean charInString( char testChar, String testString )
       {
        int index;
       
        for( index = 0; index < testString.length(); index++ )
           {
            if( testChar == testString.charAt( index ) )
               {
                return true;
               }
           }
       
        return false;
       }
   

   }