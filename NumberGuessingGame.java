import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        int attempts = 0;
        int maxAttempts = 10;
        int points = 100; // Initial points

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I've selected a random number between " + lowerBound + " and " + upperBound + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess the number.");

        while (attempts < maxAttempts) {
            System.out.print("Guess #" + (attempts + 1) + ": Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < lowerBound || guess > upperBound) {
                System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                continue;
            }

            if (guess == targetNumber) {
                System.out.println("Congratulations! You've guessed the correct number: " + targetNumber);
                System.out.println("You earned " + points + " points.");
                break;
            } else if (guess < targetNumber) {
                System.out.println("Try a higher number.");
            } else {
                System.out.println("Try a lower number.");
            }

            // Deduct points for each attempt
            points -= 10;
        }

        if (attempts >= maxAttempts) {
            System.out.println("Game over! You've used all your attempts.");
            System.out.println("The correct number was: " + targetNumber);
        }

        scanner.close();
    }
}
