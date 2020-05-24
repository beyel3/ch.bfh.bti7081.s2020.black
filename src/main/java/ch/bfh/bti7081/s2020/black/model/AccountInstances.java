package ch.bfh.bti7081.s2020.black.model;

import java.util.ArrayList;

public class AccountInstances {

    private AccountInstances(){}

    @SuppressWarnings("Duplicates")
    public static ArrayList<Account> getAccounts(){
        ArrayList<Account> accountArrayList = new ArrayList<>();

        accountArrayList.add(new Patient("Max","Mustermann","max.mustermann@test.com"));
        accountArrayList.add(new Patient("Anna","Müller","anna.mueller@test.com"));
        accountArrayList.add(new Relative("Luca","Dietrich","luca.dietrich@test.com"));
        accountArrayList.add(new Relative("Sabrina","Mönch","sabrina.moench@test.com"));

        return accountArrayList;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<Account> getCoreuserAccounts(){
        ArrayList<Account> accountArrayList = new ArrayList<>();

        accountArrayList.add(new Patient("Max","Mustermann","max.mustermann@test.com"));
        accountArrayList.add(new Patient("Anna","Müller","anna.mueller@test.com"));
        accountArrayList.add(new Relative("Luca","Dietrich","luca.dietrich@test.com"));
        accountArrayList.add(new Relative("Sabrina","Mönch","sabrina.moench@test.com"));

        return accountArrayList;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<Admin> getAdminAccounts(){
        ArrayList<Admin> adminArrayList = new ArrayList<>();

        adminArrayList.add(new Admin("Max","Mustermann","max.mustermann@test.com"));
        adminArrayList.add(new Admin("Anna","Müller","anna.mueller@test.com"));
        adminArrayList.add(new Admin("Luca","Dietrich","luca.dietrich@test.com"));
        adminArrayList.add(new Admin("Sabrina","Mönch","sabrina.moench@test.com"));

        return adminArrayList;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<Patient> getPatientAccounts(){
        ArrayList<Patient> patientArrayList = new ArrayList<>();

        patientArrayList.add(new Patient("Sandra","Krüger","sandra.kruger@test.com"));
        patientArrayList.add(new Patient("Benjamin","Wexler","benjamin.wexler@test.com"));

        return patientArrayList;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<Relative> getRelativeAccounts(){
        ArrayList<Relative> relativeArrayList = new ArrayList<>();

        relativeArrayList.add(new Relative("Jörg","Abend","joerg.abend@test.com"));
        relativeArrayList.add(new Relative("Jennfier","Bar","jennifer.bar@test.com"));

        return relativeArrayList;
    }
}
