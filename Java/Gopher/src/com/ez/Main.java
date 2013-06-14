package com.ez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Main
{
	public static String city = "new york";
	public static DataModel dm;
	public static void main(String[] args)
	{
		dm = new DataModel();
		dm.buildDataBase(city);
	}
}
