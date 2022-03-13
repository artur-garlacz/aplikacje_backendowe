package com.company;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputFileReader {

    public static void main(String[] args) throws IOException {

        String fileName = "test.txt";
        File file = new File(fileName);
        int size = (int)file.length();
        FileInputStream test = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(test);

        char[] data = new char[size];
        reader.read(data, 0, size);
        test.close();

        System.out.println(data);
    }
}
