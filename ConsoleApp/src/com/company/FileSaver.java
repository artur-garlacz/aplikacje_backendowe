package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileSaver {
    public static void main(String[] args) throws IOException {

        System.out.println("Podaj wartosc do zapisania:");
        Scanner userData = new Scanner(System.in);
        String line = userData.nextLine();

        try (FileOutputStream fos = new FileOutputStream("newTest.txt")) {

            byte[] mybytes = line.getBytes();

            fos.write(mybytes);
        }
    }
}
