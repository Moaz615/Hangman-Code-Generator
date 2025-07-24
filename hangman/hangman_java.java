import java.util.*;
public class hangman {
    static Map<String, List<String>> wordCategories = new LinkedHashMap<>() {{
        put("Animal", Arrays.asList("Lion", "Elephant", "Tiger", "Giraffe", "Zebra", "Bear", "Wolf", "Kangaroo", "Panda", "Camel", "Fox", "Leopard", "Cheetah", "Rhino", "Monkey", "Goat", "Sheep", "Cow", "Dog", "Cat", "Rabbit", "Mouse", "Squirrel", "Deer", "Owl", "Fish", "Bird", "Duck", "Frog", "Snake"));
        put("Country", Arrays.asList("Egypt", "Germany", "Brazil", "Japan", "Canada", "Italy", "Spain", "France", "China", "India", "Mexico", "Russia", "Australia", "Argentina", "Kenya", "Sweden", "Norway", "Denmark", "Thailand", "Vietnam", "Pakistan", "Indonesia", "Philippines", "Morocco", "South Africa", "Ukraine", "USA", "UK", "Korea", "Greece"));
        put("Fruit", Arrays.asList("Apple", "Banana", "Mango", "Orange", "Grape", "Watermelon", "Peach", "Pear", "Pineapple", "Cherry", "Plum", "Kiwi", "Lemon", "Lime", "Blueberry", "Strawberry", "Coconut", "Avocado", "Fig", "Date"));
        put("Color", Arrays.asList("Red", "Blue", "Green", "Yellow", "Purple", "Pink", "Orange", "Brown", "Black", "White", "Grey", "Gold", "Silver", "Navy", "Teal", "Olive", "Violet", "Turquoise", "Maroon", "Beige"));
        put("Sport", Arrays.asList("Football", "Tennis", "Cricket", "Basketball", "Swimming", "Baseball", "Rugby", "Volleyball", "Golf", "Boxing", "Cycling", "Skiing", "Wrestling", "Karate", "Judo", "Badminton", "Hockey", "Handball", "Surfing", "Diving"));
        put("Profession", Arrays.asList("Doctor", "Nurse", "Engineer", "Teacher", "Lawyer", "Architect", "Scientist", "Dentist", "Mechanic", "Pilot", "Chef", "Artist", "Musician", "Soldier", "Policeman", "Firefighter", "Journalist", "Accountant", "Actor", "Driver"));
        put("Vehicle", Arrays.asList("Car", "Truck", "Bus", "Motorcycle", "Bicycle", "Van", "Train", "Airplane", "Boat", "Ship", "Taxi", "Yacht", "Jeep", "Scooter", "Helicopter", "Submarine", "Rocket", "Spaceship", "Tram", "Ambulance"));
        put("Body Part", Arrays.asList("Head", "Eye", "Ear", "Nose", "Mouth", "Face", "Hair", "Neck", "Shoulder", "Arm", "Hand", "Finger", "Chest", "Back", "Leg", "Knee", "Foot", "Toe", "Brain", "Heart"));
        put("Clothing", Arrays.asList("Shirt", "Pants", "Jeans", "Jacket", "Coat", "Sweater", "T-shirt", "Shorts", "Skirt", "Dress", "Socks", "Shoes", "Boots", "Hat", "Cap", "Scarf", "Gloves", "Belt", "Tie", "Suit"));
        put("Tool", Arrays.asList("Hammer", "Screwdriver", "Wrench", "Pliers", "Saw", "Drill", "Axe", "Shovel", "Scissors", "Knife", "Ladder", "Rake", "Mallet", "Level", "Tape Measure", "Toolbox", "Clamp", "Chisel", "Paintbrush", "Crowbar"));
        put("City", Arrays.asList("London", "Paris", "Berlin", "Tokyo", "Madrid", "Rome", "Moscow", "Beijing", "New York", "Sydney", "Dubai", "Istanbul", "Mumbai", "Delhi", "Los Angeles", "Chicago", "Toronto", "Bangkok", "Seoul", "Singapore"));
        put("Insect", Arrays.asList("Ant", "Bee", "Wasp", "Butterfly", "Moth", "Mosquito", "Dragonfly", "Grasshopper", "Cricket", "Fly", "Beetle", "Ladybug", "Spider", "Flea", "Tick", "Worm", "Hornet", "Gnat", "Aphid", "Termite"));
        put("Planet", Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Moon", "Sun"));
        put("Element", Arrays.asList("Hydrogen", "Oxygen", "Carbon", "Gold", "Silver", "Iron", "Copper", "Zinc", "Helium", "Nitrogen", "Neon", "Sodium", "Chlorine", "Aluminum", "Lead", "Platinum", "Nickel", "Tin", "Calcium", "Argon"));
        put("Emotion", Arrays.asList("Happy", "Sad", "Angry", "Scared", "Excited", "Surprised", "Nervous", "Anxious", "Confused", "Bored", "Hopeful", "Jealous", "Lonely", "Guilty", "Proud", "Relaxed", "Grateful", "Joyful", "Tired", "Calm"));
    }};
    static String[] hangmanAscii = {
        "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        while (true) {
            List<String> categories = new ArrayList<>(wordCategories.keySet());
            String category = categories.get(rand.nextInt(categories.size()));
            List<String> words = wordCategories.get(category);
            String word = words.get(rand.nextInt(words.size()));
            StringBuilder display = new StringBuilder();
            for (int i = 0; i < word.length(); i++) display.append('_');
            Set<Character> wrongLetters = new LinkedHashSet<>();
            int tries = 6;
            clearScreen();
            System.out.println("The word is a/an: " + category);
            while (tries > 0 && display.indexOf("_") != -1) {
                clearScreen();
                System.out.println("The word is a/an: " + category);
                System.out.println(hangmanAscii[6 - tries]);
                System.out.println("\nWord: " + spaced(display));
                System.out.print("Wrong letters: ");
                for (char c : wrongLetters) System.out.print(c + " ");
                System.out.println("\nTries left: " + tries);
                System.out.print("Guess a letter: ");
                String input = sc.nextLine().trim();
                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    System.out.println("Please enter a single letter (a-z). Press Enter to continue...");
                    sc.nextLine();
                    continue;
                }
                char guess = Character.toLowerCase(input.charAt(0));
                boolean alreadyWrong = wrongLetters.contains(guess);
                boolean alreadyRevealed = false;
                for (int i = 0; i < word.length(); i++) {
                    if (Character.toLowerCase(word.charAt(i)) == guess && display.charAt(i) == word.charAt(i)) {
                        alreadyRevealed = true;
                        break;
                    }
                }
                if (alreadyWrong || alreadyRevealed) {
                    System.out.println("You already tried \"" + guess + "\"! Try another letter. Press Enter to continue...");
                    sc.nextLine();
                    continue;
                }
                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (Character.toLowerCase(word.charAt(i)) == guess) {
                        display.setCharAt(i, word.charAt(i));
                        found = true;
                    }
                }
                if (found) System.out.println("Good job!");
                else { tries--; wrongLetters.add(guess); System.out.println("Wrong!"); }
            }
            if (display.indexOf("_") == -1)
                System.out.println("\nCongratulations! The word was: " + word);
            else
                System.out.println("\nGame over! The word was: " + word);
            System.out.print("Play again? (y/n): ");
            String answer = sc.nextLine().trim();
            if (answer.isEmpty() || !(answer.charAt(0) == 'y' || answer.charAt(0) == 'Y')) break;
        }
        System.out.println("Thanks for playing!");
    }
    static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
        } catch (Exception e) {
            System.out.println();
        }
    }
    static String spaced(StringBuilder sb) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            out.append(sb.charAt(i)).append(' ');
        }
        return out.toString();
    }
} 