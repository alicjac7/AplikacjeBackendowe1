package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

public class Main {


    public static void main(String[] args) throws IOException {
        int[] array = {4, 5, 7, 11, 12, 15, 15, 21, 40, 45};
        zadaniePierwsze();
        zadanieDrugie();
        System.out.println(zadanieTrzecie(array, 11));
        System.out.println(zadanieCzwarte("This is example text ..."));
        zadaniePiate();
        zadanieSzoste();
        System.out.println(zadanieSiodme());
        zadanieOsme();
    }

    public static String zadanieSiodme() throws JsonProcessingException {
        User user = new User("imieusera",21);
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsString(user);
    }
    public static User zadanieOsme() throws JsonProcessingException {
        User user = new User("imieusera",21);
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(user);
        return mapper.readValue(jsonObject,User.class);
    }

    public static void zadanieSzoste() throws IOException {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("D:\\Users\\aleksander.gorecki\\IdeaProjects\\aplikacje-backendowe\\lab1\\input"));
            String line = reader.readLine();
            int licznik = 1;
            while (line != null) {
                System.out.println(licznik + " " + line);
                line = reader.readLine();
                licznik +=1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zadaniePiate() {
        System.out.println(java.time.LocalTime.now());
        System.out.println(Instant.now());
    }

    public static int zadanieTrzecie(int[] array, int value) {
        int index = 0;
        int limit = array.length;
        while (index <= limit) {
            int point = (int) Math.ceil((index + limit) / 2.0);
            int entry = array[point];
            if (value > entry) {
                index = point + 1;
                continue;
            }
            if (value < entry) {
                limit = point - 1;
                continue;
            }
            return point;
        }
        return -1;
    }

    public static int zadanieCzwarte(String text) {
        int[] crcTable = new int[256];
        for (int i = 0; i < 256; ++i) {
            int code = i;
            for (int j = 0; j < 8; ++j) {
                code = (((code & 0x01) == 1) ? 0xEDB88320 ^ (code >>> 1) : (code >>> 1));
            }
            crcTable[i] = code;
        }

        int crc = -1;
        for (int i = 0; i < text.length(); ++i) {
            int code = Character.codePointAt(text, i);
            crc = crcTable[((code ^ crc) & 0xFF)] ^ (crc >>> 8);
        }
        return (~crc);
    }

    public static void zadaniePierwsze() throws IOException {

        try (FileInputStream in = new FileInputStream("output.txt")) {
            byte[] bytes = in.readAllBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);

            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            System.out.println(textBuilder);
        }
    }

    public static void zadanieDrugie() throws IOException {
        String fileName = "D:\\Users\\aleksander.gorecki\\IdeaProjects\\aplikacje-backendowe\\lab1\\output.txt";
        try (FileOutputStream fos = new FileOutputStream(fileName)) {

            String text = "Today is a beautiful day";
            byte[] mybytes = text.getBytes();

            fos.write(mybytes);
        }
    }


}
