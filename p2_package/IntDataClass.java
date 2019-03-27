/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2_package;

/**
 *
 * @author carterwrobel
 */
public class IntDataClass 
extends java.lang.Object
implements java.lang.Comparable<IntDataClass>
{
    private int dataVal;
    /**
     * Default constructor
     */
    public IntDataClass()
    {
        
    }
    /**
     * Initialization constructor
     * @param initialValue - integer value to be loaded into object
     */
    public IntDataClass(int initialValue)
    {
        dataVal = initialValue;
    }
    /**
     * Copy constructor
     * @param copied - IntDataClass object to be copied into THIS object
     */
    public IntDataClass(IntDataClass copied)
    {
        dataVal = copied.dataVal;
    }
    /**
     * Setter method to update data in object
     * @param newValue - integer value to be set in object
     */
    public void setValue(int newValue)
    {
        dataVal = newValue;
    }
    /**
     * Getter method to retrieve data from object
     * @return integer value retrieved from object
     */
    public int getValue()
    {
        return dataVal;
    }
    /**
     * Overrides toString method
     * @Overrides toString in class java.lang.Object
     * @return String output of integer data value
     */
    @Override
    public java.lang.String toString()
    {
        String returnStr = "";
        returnStr += dataVal;
        return returnStr;
    }
    /**
     * Method required of class extending Comparable;
     * used by generic classes for management
     * @param other
     * @return 
     */
    @Override
    public int compareTo(IntDataClass other)
    {
        return dataVal - other.dataVal;
    }
    
    
}
