import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound; //setting of target number

        int attempts = 0;
        int maxAttempts = 10; 
        int points = 100; // Initial points

        System.out.println("Welcome to the Number Guessing Game!"); //start of game
        System.out.println("I've selected a random number between " + lowerBound + " and " + upperBound + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess the number.");

        while (attempts < maxAttempts) //to check for number of attempts
        {
            System.out.print("Guess #" + (attempts + 1) + ": Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < lowerBound || guess > upperBound) //to check if the number guessed is out of bounds
            {
                System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                continue;
            }

            if (guess == targetNumber) //to check if the guessed number is equal to the target number
            {
                System.out.println("Congratulations! You've guessed the correct number: " + targetNumber);
                System.out.println("You earned " + points + " points."); //displays points after correct guess
                break;
            }
            else if (guess < targetNumber) //to check if guessed number is smaller than target number
            {
                System.out.println("Try a higher number.");
            }
            else //if guessed number is larger than target number
            {
                System.out.println("Try a lower number.");
            }
            points -= 10; //10 points deducted after every wrong guess
        }

        if (attempts >= maxAttempts) //after 10 attempts game gets terminated automatically 
        {
            System.out.println("Game over! You've used all your attempts.");
            System.out.println("The correct number was: " + targetNumber);
        }

        scanner.close();
    }
}
