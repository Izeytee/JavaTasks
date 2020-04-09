package ru.sibsutis;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Developer extends User implements CSV {
    private String language;

    public Developer(String name, String language) {
        super(name);
        this.language = language;
    }

    public Developer(String name, String email, String language) {
        super(name, email);
        this.language = language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return this.language;
    }

    @Override
    public String toCSV() {
        try {
            FileWriter file = new FileWriter("res/developer.csv", true);
            file.write(name + ';' + email + ';' + language + '\n');
            file.close();
        }
        catch (IOException e) {

        }
        return null;
    }

    @Override
    public void fromCSV(String str) {
        try {
            str = this.name + ';' + this.email + ';' + this.language;
            List<String> elemSearched = Arrays.asList(str.split(";"));

            FileReader fileRead = new FileReader("res/developer.csv");
            FileWriter fileWrite = new FileWriter("res/developerT.csv");
            Scanner in = new Scanner(fileRead);
            while(in.hasNextLine()) {
                String temp = in.nextLine();
                List<String> elem = Arrays.asList(temp.split(";"));
                if (elem.equals(elemSearched))
                    continue;
                fileWrite.write(temp + '\n');
            }
            fileRead.close();
            fileWrite.close();
            File file = new File("res/developer.csv");
            file.delete();
            File finalFile = new File("res/developerT.csv");
            finalFile.renameTo(file);
        }
        catch (IOException e) {

        }
    }

    public static void fillCSV() {
        List<String> nameList = Arrays.asList("Alex", "Igor", "Andrey", "Nikolai", "Dasha",
                "Dima", "Nikita", "Anastasia", "Boris", "Alexandra");
        List<String> emailList = Arrays.asList("Hollow@yandex.ru", "Rublick@mail.ru", "Lot@gmail.com",
                "JavaProf2020@yandex.ru", "Andrew54@gmail.com", "Lollypopx20@yandex.ru",
                "Redund2020@mail.ru", "WannaCyberpunk2077@yandex.ru", "goodMood@yandex.ru",
                "Sherlock_TOP@gmail.com");
        List<String> langugeList  = Arrays.asList("HTML", "Pascal", "C", "C++", "C#",
                "Objective C", "Java", "Kotlin", "Python", "Lisp");

        try {
            FileWriter file = new FileWriter("res/developer.csv");
            List<String> userList = new ArrayList<>();
            for (String name : nameList)
                for (String email : emailList)
                    for (String language : langugeList)
                        userList.add(name + ';' + email + ';' + language + '\n');

            Collections.shuffle(userList);
            for (String user : userList)
                file.write(user);
            file.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
