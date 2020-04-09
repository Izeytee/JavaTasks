package ru.sibsutis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Manager extends User implements CSV {
    private String number;

    public Manager(String name, String number) {
        super(name);
        this.number = number;
    }

    public Manager(String name, String email, String number) {
        super(name, email);
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    @Override
    public String toCSV() {
        try {
            FileWriter file = new FileWriter("res/manager.csv", true);
            file.write(name + ';' + email + ';' + number + '\n');
            file.close();
        }
        catch (IOException e) {

        }
        return null;
    }

    @Override
    public void fromCSV(String str) {
        try {
            str = this.name + ';' + this.email + ';' + this.number;
            List<String> elemSearched = Arrays.asList(str.split(";"));

            File file = new File("res/manager.csv");
            FileWriter fileTemp = new FileWriter("res/managerT.csv");
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                String temp = in.nextLine();
                List<String> elem = Arrays.asList(temp.split(";"));
                if (elem.equals(elemSearched))
                    continue;
                fileTemp.write(temp + '\n');
            }
            fileTemp.close();
            file.delete();
            File finalFile = new File("res/managerT.csv");
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
        List<String> phoneList  = Arrays.asList("89135542332", "89135667432", "89604432312", "89137064428",
            "343405665887", "89137759812", "89137573198", "89605412298", "89237653892",
            "89133409966");

        try {
            FileWriter file = new FileWriter("res/manager.csv");
            List<String> userList = new ArrayList<>();
            for (String name : nameList)
                for (String email : emailList)
                    for (String phone : phoneList)
                        userList.add(name + ';' + email + ';' + phone + '\n');

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
