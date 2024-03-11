/**
 *
 *  @author Przybylski Jakub S24512
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
            .when(  x -> x.startsWith("WAW")/*<-- lambda wyrażenie
             *  selekcja wylotów z Warszawy (zaczynających się od WAW)
             */
            )
            .mapEvery( x -> {
                String str = x.substring(x.lastIndexOf(" "), x.length());

                double price = Double.valueOf(str);
                price*=xrate;

                x.replace(str, " " + (int)(price));
                x.replace("WAW", "to ");

                String res = "to"+x.substring(x.indexOf(" ") , x.lastIndexOf(" "))+ " - price in PLN:\t"+(int)(price);
                return res;
                }
            );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
            "bleble bleble 2000",
            "WAW HAV 1200",
            "xxx yyy 789",
            "WAW DPS 2000",
            "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
