/**
*Class:             JsonTestBench.java
*Project:           Node-Visualizer
*Author:            Jason Van Kerkhoven                                             
*Date of Update:    19/01/2017                                              
*Version:           1.0.0                                                     
*                                                                                   
*Purpose:           Test saving and loading Network and objects aggregated by Network to
*					disk as .json files
* 
* 
*Update Log:		v1.0.0
*						- null
*/
package tests;


//import stuff
import java.util.ArrayList;

//imports from local packages
import io.*;
import io.json.JsonException;
import io.json.JsonFile;
import network.*;


public class JsonTestBench 
{

	
	public static void println(String printable)
	{
		System.out.println(printable);
	}
	
	
	public static void print(String printable)
	{
		System.out.print(printable);
	}
	
	public static void main(String[] args)
	{
		//working instance variables
		JsonFile jsonFileNetwork, jsonFileId;
		String expected;
		IdDispatcher dispatch = new IdDispatcher();
		Network network = new Network();
		final String DIV = "\n==================================================================================================================";
		
		
		//test to .json for IdDispatcher
		////////////////////////////////////////////////////////////////////////////////////////////
		println("Initializing idDispatch value..." + DIV);
		try
		{
			//checkout ids 0 through 9
			print("IDs checked out: ");
			for(int i=0; i<10; i++)
			{
				print(dispatch.getNextID() + ", ");
			}
			println("...");
			
			//check in ids 4 through 9
			print("IDs checked in:  ");
			for(int i=4; i<10; i++)
			{
				dispatch.retireID(i);
				print(i + ", ");
			}
			println("...");
		}
		catch (NetworkException e)
		{
			print("ERROR");
			System.exit(0);
		}
		
		//convert to .json and print
		jsonFileId = dispatch.toJSON(null);
		println("-------start-------\n" + jsonFileId.toString() + "\n-----------end----------");
		
		
		
		//test to .json for IdDispatcher
		////////////////////////////////////////////////////////////////////////////////////////////
		println("Initializing idDispatch value..." + DIV);
		dispatch.reset();
		try
		{
			//checkout ids 0 through 9
			print("IDs checked out: ");
			for(int i=0; i<10; i++)
			{
				print(dispatch.getNextID() + ", ");
			}
			println("...");
			
			//check in ids 4 through 9
			print("IDs checked in:  ");
			for(int i=4; i<10; i++)
			{
				dispatch.retireID(i);
				print(i + ", ");
			}
			println("...");
		}
		catch (NetworkException e)
		{
			print("ERROR");
			System.exit(0);
		}
		
		//convert to .json and print
		print("\n{\nvar : thing\nvar thing\nvar : ");
		println(dispatch.toJSON("\t").toString());
		println("}");
		
		
		
		//test to .json for IdDispatcher
		////////////////////////////////////////////////////////////////////////////////////////////
		println("\n\nInitializing Network values..." + DIV);
		try
		{
			//add nodes
			network.add("the first node");
			network.add("stage 1");
			network.add("stage 2");
			network.add("end node");
			for(int i=0; i<6; i++)
			{
				network.add("garbage");
			}
			
			//remove garbage nodes
			for(int i=4; i<10; i++)
			{
				network.remove(i);
			}
			
			//link
			network.link(0, 1);
			network.link(0, 2);
			network.link(1, 3);
			network.link(2, 3);
			network.link(3, 0);
		}
		catch (NetworkException e)
		{
			print("ERROR");
			System.exit(0);
		}
		
		jsonFileNetwork = network.toJSON();
		println("---------start---------" + jsonFileNetwork.toString() + "-----------end------------");
		
		
		
		//test from .json for IdDispatcher
		////////////////////////////////////////////////////////////////////////////////////////////
		println("\n\nTesting idDispatcher.java from .json..." + DIV);
		IdDispatcher builtDispatch = new IdDispatcher();
		
		try
		{
			//build from json
			println("Building object...");
			builtDispatch.fromJSON(jsonFileId.toString());
			println("Object built!");
			println("Printing diagnostics...");

			//print json used
			println("\n.json file used\n-----------------------");
			print(jsonFileId.toString());
			println("-------------------------");
			
			//print json of object built
			println("\n.json file of built object\n-----------------------");
			print(builtDispatch.toJSON(null).toString());
			println("-------------------------");

			//compare (they should be equal)
			println("Comparing .json files of built object and origianl .json file (files should match)...");
			if(jsonFileId.toString().equals(builtDispatch.toJSON(null).toString()))
			{
				println("PASS! -- Encoding correct!");
			}
			else
			{
				println("FAIL! -- Encoding failed!");
			}
		}
		catch (JsonException e)
		{
			print(e.toString());
			System.exit(0);
		}
		
		
		
		//test from .json for Network
		////////////////////////////////////////////////////////////////////////////////////////////
		println("\n\nTesting idDispatcher.java from .json..." + DIV);
		Network builtNetwork = new Network();
		
		try
		{
			//build from json
			println("Building object...");
			builtNetwork.fromJSON(jsonFileNetwork.toString());
			println("Object built!");
			println("Printing diagnostics...");
			
			//print json used
			println("\n.json file used\n-----------------------");
			print(jsonFileNetwork.toString());
			println("-------------------------");
			
			//print json of object built
			println("\n.json file of built object\n-----------------------");
			print(builtNetwork.toJSON().toString());
			println("-------------------------");

			//compare (they should be equal)
			println("Comparing .json files of built object and origianl .json file (files should match)...");
			if(jsonFileNetwork.toString().equals(builtNetwork.toJSON().toString()))
			{
				println("PASS! -- Encoding correct!");
			}
			else
			{
				println("FAIL! -- Encoding failed!");
			}
		}
		catch (JsonException e)
		{
			print(e.toString());
			System.exit(0);
		}
		
		
		
		
		print("\n\n\n-- end");
	}

}
