package main;

import java.util.Scanner;

/**
 * Generates the POJO class and set methods for the POJO instance
 */
public class PoJoGeneratorApplication {

	public static void main(String[] args) {

		final Scanner sc = new Scanner(System.in);

		String result = "";

		while(true) {		

			String text = sc.nextLine();

			if(!"EOF".equals(text)) {
				
				if(text.contains("protected")) {

					String value ;

					value = text.replaceFirst("protected", "private");
					
					if(value.contains("Gregorian")) {
						value = value.replaceFirst("Gregorian", "String");
					}

					int index = value.lastIndexOf(" ") + 1;
					String lwd = value.substring(index, (value.length()-1));

					String str=lwd;

					String first = lwd.substring(0, 1);
					lwd = lwd.replaceFirst(first, first.toUpperCase());
					result = result + "\n pojo.set" + lwd + "(response.get" + lwd + "());";

					// Camel Case Conversion
					String regex = "([a-z])([A-Z]+)"; 	  
					String replacement = "$1_$2"; 
					str = str.replaceAll(regex, replacement).toLowerCase();

					System.out.println("\n@JsonProperty(\"" + str + "\")");
					System.out.println(value);
				}

			} else {

				System.out.println(" ------   --------   ------");
				System.out.println(result);
				sc.close();
				break;
			}

		}
	}
}
