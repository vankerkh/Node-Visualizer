/**
*Class:             Reader.java
*Project:           Node-Visualizer
*Author:            Jason Van Kerkhoven                                             
*Date of Update:    23/01/2017                                              
*Version:           1.0.0                                         
*                                                                                   
*Purpose:           Reads the raw Byte array from a file on disk.
*					Loads said Bytes into memory.
*					Modified from TFTPReader v1.0.0, written 26/09/2016 for TFTP Project
* 
* 
*Update Log			v1.0.0
*						- structured as completely static class
*/
package io;


//imports
import java.io.*;
import java.util.*;


public abstract class Reader extends BasicIO
{	
	//reads file passed to it, returns a string
	public static String readAsString(String msg) throws FileNotFoundException
	{
		File file = Reader.getFile(msg);
		
		if (file != null)
		{
			String content = new Scanner(file).useDelimiter("\\Z").next();
			System.out.println(content);
			return content;
		}
		else
		{
			return null;
		}
	}
}