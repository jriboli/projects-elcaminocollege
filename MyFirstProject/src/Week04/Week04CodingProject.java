package Week04;

public class Week04CodingProject {

    public static void main(String[] args) {
        //    1. Create an array of int called ages that contains the following values: 3, 9, 23, 64, 2, 8, 28, 93.
        //    a. Programmatically subtract the value of the first element in the array from the value in the last
        //          element of the array (i.e. do not use ages[7] in your code). Print the result to the console.
        //    b. Create a new array with 9 elements (one more than the ages array)  Repeat the step above to ensure
        //          it is dynamic (works for arrays of different lengths).
        //    c. Use a loop to iterate through the array and calculate the average age. Print the result to the console.

        int[] ages = new int[] {3, 9, 23, 64, 2, 8, 28, 93};
        firstMinusLast(ages);

        int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        firstMinusLast(numbers);
        System.out.println(calcAvgInteger(ages));

        //    2. Create an array of String called names that contains the following values: “Sam”, “Tommy”, “Tim”, “Sally”, “Buck”, “Bob”.
        //      a. Use a loop to iterate through the array and calculate the average number of letters per name.
        //          Print the result to the console.
        //      b. Use a loop to iterate through the array again and concatenate all the names together, separated
        //          by spaces, and print the result to the console.
        String[] names = new String[] {"Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"};
        System.out.println(calcAvgInteger(ageSum));
        System.out.println(concateStringArray(names));

        //    3. How do you access the last element of any array?
        /*
        * You access the last element of an array by taking the array length an minus 1, due to arrays being zero index
        */

        //    4. How do you access the first element of any array?
        /*
        * You access the first element of an array with zero, being zero indexed
        */

        //    5. Create a new array of int called nameLengths. Write a loop to iterate over the previously created
        //      names array and add the length of each name to the nameLengths array.
        int[] nameLengths = new int[names.length];
        for(int i = 0; i < names.length; i++) {
            nameLengths[i] = names[i].length();
        }

        //    6. Write a loop to iterate over the nameLengths array and calculate the sum of all the elements
        //      in the array. Print the result to the console.
        System.out.println(nameLengths);

        //    7. Write a method that takes a String, word, and an int, n, as arguments and returns the word
        //      concatenated to itself n number of times. (i.e. if I pass in “Hello” and 3, I expect the method to
        //      return “HelloHelloHello”).
        System.out.println(concateNTimes("Hello", 3));

        //    8. Write a method that takes two Strings, firstName and lastName, and returns a full name (the full name
        //      should be the first and the last name as a String separated by a space).
        System.out.println(joinFirstNLast("Rocket", "Raccoon"));

        //    9. Write a method that takes an array of int and returns true if the sum of all the ints in the array
        //      is greater than 100.
        System.out.println(isIntSumOver100(numbers));

        //    10. Write a method that takes an array of double and returns the average of all the elements in the array.
        double[] values = new double[] {98.07, 99.45, 87.40, 75};
        System.out.println(calcAvgDouble(values));

        //    11. Write a method that takes two arrays of double and returns true if the average of the elements in
        //      the first array is greater than the average of the elements in the second array.
        double[] secondValues = new double[] {101.01, 99.09, 87.08, 75.07};
        System.out.println(isArraySumLarger(values, secondValues));

        //    12. Write a method called willBuyDrink that takes a boolean isHotOutside, and a double moneyInPocket,
        //      and returns true if it is hot outside and if moneyInPocket is greater than 10.50.
        System.out.println(willBuyDrink(true, 9.00));
        System.out.println(willBuyDrink(false, 11.00));
        System.out.println(willBuyDrink(true, 22.50));

        //    13. Create a method of your own that solves a problem. In comments, write what the method does and why
        //      you created it.
        System.out.println(String.format("You dog is %s years old.", calcDogAge(7)));
    }
    public static int calcDogAge(int age) {
        int dogAge = 0;
        for(int i = 1; i <= age; i++) {
            if(i == 1)
                dogAge += 7;
            else
                dogAge += 6;
        }
        return dogAge;
    }

    public static boolean willBuyDrink(boolean isHotOutside, double moneyInPocket) {
        if(isHotOutside && moneyInPocket > 10.50)
            return true;
        return false;
    }

    public static boolean isArraySumLarger(double[] arr, double[]arr2) {
        double firstSum = calcAvgDouble(arr);
        double secondSum = calcAvgDouble(arr2);
        if(firstSum > secondSum)
            return true;
        return false;
    }

    public static boolean isIntSumOver100(int[] arr) {
        int intSum = 0;
        for (int num : arr) {
            intSum += num;
        }

        if(intSum > 100)
            return true;
        return false;
    }

    public static String joinFirstNLast(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    public static String concateNTimes(String word, int n) {
        String newWord = "";
        for(int i = 0; i < n; i++) {
            newWord += word;
        }
        return newWord;
    }

    public static void firstMinusLast(int[] arr) {
        System.out.println(ages[ages.length - 1] - ages[0]);
    }

    public static int calcAvgInteger(int[] arr){
        int intAvg = 0;
        for (int num : arr) {
            intAvg += num;
        }
        return intAvg /= arr.length;
    }

    public static double calcAvgDouble(double[] arr){
        double doubleAvg = 0;
        for (double num : arr) {
            doubleAvg += num;
        }
        return doubleAvg /= arr.length;
    }

    public static int calcAvgStringLength(String[] arr) {
        int avgStringLength = 0;
        for(String word : arr) {
            avgStringLength += word.length();
        }
        return avgStringLength /= arr.length;
    }

    public static String concateStringArray(String[] arr) {
        String newString = "";
        for(String word : arr) {
            newString += word + " ";
        }
        return newString;
    }
}