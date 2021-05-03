package com.company;

import java.util.*;

public class PhoneBook {
    private static String name;
    private static String phone;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, String> aBan = new TreeMap<>();
        Map<String, String> bBan = new TreeMap<>();
        aBan.put("김예찬", "1010501");
        aBan.put("김예찬2", "1010501");
        aBan.put("김예찬3", "1010501");

        bBan.put("김예찬4", "1010501");
        bBan.put("김예찬5", "1010501");
        bBan.put("김예찬6", "1010501");
        Map<String, Map<String, String>> phonebook = new TreeMap<>();

        phonebook.put("A반", aBan);
        phonebook.put("B반", bBan);

        Set<Map.Entry<String, Map<String, String>>> entrySet = phonebook.entrySet();

        Iterator<Map.Entry<String, Map<String, String>>> iter1 = entrySet.iterator();

        while (iter1.hasNext()) {
            Map.Entry<String, Map<String, String>> entry = iter1.next();
            System.out.println(entry.getKey());
            Map<String, String> pb = entry.getValue();

            Set<Map.Entry<String, String>> phones = pb.entrySet();
            Iterator<Map.Entry<String, String>> iter2 = phones.iterator();
            while (iter2.hasNext()) {
                Map.Entry<String, String> phone = iter2.next();
                System.out.println(phone.getKey() + " : " + phone.getValue());

            }
            System.out.println("\n");
        }

        Set<String> keyset = phonebook.keySet();
        for (String s : keyset) {
            System.out.println(s);

            phonebook.get(s).forEach((k, v) -> {
                System.out.printf("%s : %s\n", k, v);
            });

            System.out.println();
        }

    }
}
