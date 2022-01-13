package com.nighthawk.csa.mvc.DataOps.genericDataModel;
import com.nighthawk.csa.utility.ConsoleMethods;
import java.util.Random;

/*
 * Biology class extends Generics and defines abstract methods
 */
public class Password extends Generics {
    // Class data
    public static KeyTypes key = KeyType.title;  // static initializer
    public static void setOrder(KeyTypes key) { Password.key = key; }
    public enum KeyType implements KeyTypes {title, str, len, type}

    // Instance data
    private final String str;
    private final int len;
    private final String type;
    private final String password; // for each data you want displayed on the page, you must have instances.
    //instance supports the data and ensures that the data is being added to the page.

    /* constructor
     *
     */
    public Password(String str, int len, String type)
    {
        super.setType("Password");
        this.str = str;
        this.len = len;
        this.type = type;

        String pass = "";


        for (int i = 0; i < len; i++) {
            pass = str;
            int num = (int)(Math.random()*10);
            num = Math.round(num);
            String number = String.valueOf(num);
            pass = str + "." + number;
        }

        System.out.print(pass);
        this.password = pass;
    }




    /* 'Generics' requires getKey to help enforce KeyTypes usage */
    @Override
    protected KeyTypes getKey() { return Password.key; }

    /* 'Generics' requires toString override
     * toString provides data based off of Static Key setting
     */
    @Override
    public String toString()
    {
        String output="";
        if (KeyType.str.equals(this.getKey())) {
            output += this.str;
        }
        else if (KeyType.len.equals(this.getKey())) {
            output += "00" + this.len;
            output = output.substring(output.length() - 2);
        }
        else if (KeyType.type.equals(this.getKey())) {
            output += this.type;
        }
        //else if (KeyType.password.equals(this.getKey())){
          //  output += this.password;
       // }
        else {
            output += super.getType() + ": " + this.str + ", " + this.type + ", " + this.len + ", " + this.password;
        }
        return output;

    }

    // Test data initializer
    public static Password[] PasswordData() {
        return new Password[]{
                new Password("Chlamydomonas reinhardtii", 1, "Algae"),
                new Password("Amanita muscaria", 1, "Fungi"),
                new Password("Robin", 7, "Bird"),
                new Password("Cat", 10, "Mammal"),
                new Password("Snake", 1, "Reptile"),
                new Password("Dog", 14, "Mammal")
        };
    }

    /* main to test Password class
     *
     */
    public static void main(String[] args)
    {
        // Inheritance Hierarchy
        Password[] objs = PasswordData();

        // print with title
        Password.setOrder(KeyType.title);
        Generics.print(objs);

        // print name only
        Password.setOrder(KeyType.str);
        Generics.print(objs);
    }

}
