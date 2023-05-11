package com.solvd.fileutils;
import java.io.*;
import java.nio.charset.Charset;
//import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Homework8 {
    public static void main( String[] args ) throws IOException
    {
        final Logger logger = LogManager.getLogger(Homework8.class);
        String fileRead = "src/main/resources/Data/input.txt";
        String fileWrite = "src/main/resources/Data/output.txt";
        int uniqueWordCount = 0;
        try{
        String fileContents = FileUtils.readFileToString( new File(fileRead),
                                Charset.forName("UTF-8"));
        logger.info("Reading input file.");
        String [] words = fileContents.split("\\b\\W*\\b");
        logger.info("Input file:");
        logger.info(fileContents);
        Set<String> uniqueWords = new LinkedHashSet<>();
        for(String word : words)
        {
            if (StringUtils.isNotBlank(word)) 
            {
                uniqueWords.add(word.toLowerCase());
            }
        }

        uniqueWordCount = uniqueWords.size();
        String result = "Number of unique words: " + uniqueWordCount + "\n";
        FileUtils.writeStringToFile(new File(fileWrite), result +  String.join(System.lineSeparator(), uniqueWords), "UTF-8");
        logger.info("Writting output file:");
        logger.info(result +  String.join(System.lineSeparator(), uniqueWords));
    } catch(IOException e){
        logger.error("There's an error.");
    }
    }
}
