package ua.sampsa;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Maryna Tkachuk on 03.02.21.
 */
public class Collections {

  public static void main(String[] args) {

//    String[] langs = new String[4];
//    langs[0] = "Java";
//    langs[1] = "C#";
//    langs[2] = "Python";
//    langs[3] = "PHP";

    String[] langs = {"Java", "C#", "Python", "PHP"};


    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");


    //перебрать массив с переменной-счетчиком
//    for (int i = 0; i < langs.length; i++) {
//      System.out.println("I want to learn " + langs[i]);
//    }

    //переменная l как указатель на элементы массива
    for (String l : languages) {
      System.out.println("I want to learn " + l);
    }
  }
}
