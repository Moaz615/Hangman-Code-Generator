#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <ctime>
#include <map>
using namespace std;

void clear_screen() {
#ifdef _WIN32
    (void)system("cls");
#else
    (void)system("clear");
#endif
}

map<string, vector<string>> word_categories = {
    {"Animal", {"Lion", "Elephant", "Tiger", "Giraffe", "Zebra", "Bear", "Wolf", "Kangaroo", "Panda", "Camel",
        "Fox", "Leopard", "Cheetah", "Rhino", "Monkey", "Goat", "Sheep", "Cow", "Dog", "Cat",
        "Rabbit", "Mouse", "Squirrel", "Deer", "Owl", "Fish", "Bird", "Duck", "Frog", "Snake"}},
    {"Country", {"Egypt", "Germany", "Brazil", "Japan", "Canada", "Italy", "Spain", "France", "China", "India",
        "Mexico", "Russia", "Australia", "Argentina", "Kenya", "Sweden", "Norway", "Denmark", "Thailand", "Vietnam",
        "Pakistan", "Indonesia", "Philippines", "Morocco", "South Africa", "Ukraine", "USA", "UK", "Korea", "Greece"}},
    {"Fruit", {"Apple", "Banana", "Mango", "Orange", "Grape", "Watermelon", "Peach", "Pear", "Pineapple", "Cherry",
        "Plum", "Kiwi", "Lemon", "Lime", "Blueberry", "Strawberry", "Coconut", "Avocado", "Fig", "Date"}},
    {"Color", {"Red", "Blue", "Green", "Yellow", "Purple", "Pink", "Orange", "Brown", "Black", "White",
        "Grey", "Gold", "Silver", "Navy", "Teal", "Olive", "Violet", "Turquoise", "Maroon", "Beige"}},
    {"Sport", {"Football", "Tennis", "Cricket", "Basketball", "Swimming", "Baseball", "Rugby", "Volleyball", "Golf", "Boxing",
        "Cycling", "Skiing", "Wrestling", "Karate", "Judo", "Badminton", "Hockey", "Handball", "Surfing", "Diving"}},
    {"Profession", {"Doctor", "Nurse", "Engineer", "Teacher", "Lawyer", "Architect", "Scientist", "Dentist", "Mechanic", "Pilot",
        "Chef", "Artist", "Musician", "Soldier", "Policeman", "Firefighter", "Journalist", "Accountant", "Actor", "Driver"}},
    {"Vehicle", {"Car", "Truck", "Bus", "Motorcycle", "Bicycle", "Van", "Train", "Airplane", "Boat", "Ship",
        "Taxi", "Yacht", "Jeep", "Scooter", "Helicopter", "Submarine", "Rocket", "Spaceship", "Tram", "Ambulance"}},
    {"Body Part", {"Head", "Eye", "Ear", "Nose", "Mouth", "Face", "Hair", "Neck", "Shoulder", "Arm",
        "Hand", "Finger", "Chest", "Back", "Leg", "Knee", "Foot", "Toe", "Brain", "Heart"}},
    {"Clothing", {"Shirt", "Pants", "Jeans", "Jacket", "Coat", "Sweater", "T-shirt", "Shorts", "Skirt", "Dress",
        "Socks", "Shoes", "Boots", "Hat", "Cap", "Scarf", "Gloves", "Belt", "Tie", "Suit"}},
    {"Tool", {"Hammer", "Screwdriver", "Wrench", "Pliers", "Saw", "Drill", "Axe", "Shovel", "Scissors", "Knife",
        "Ladder", "Rake", "Mallet", "Level", "Tape Measure", "Toolbox", "Clamp", "Chisel", "Paintbrush", "Crowbar"}},
    {"City", {"London", "Paris", "Berlin", "Tokyo", "Madrid", "Rome", "Moscow", "Beijing", "New York", "Sydney",
        "Dubai", "Istanbul", "Mumbai", "Delhi", "Los Angeles", "Chicago", "Toronto", "Bangkok", "Seoul", "Singapore"}},
    {"Insect", {"Ant", "Bee", "Wasp", "Butterfly", "Moth", "Mosquito", "Dragonfly", "Grasshopper", "Cricket", "Fly",
        "Beetle", "Ladybug", "Spider", "Flea", "Tick", "Worm", "Hornet", "Gnat", "Aphid", "Termite"}},
    {"Planet", {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Moon", "Sun"}},
    {"Element", {"Hydrogen", "Oxygen", "Carbon", "Gold", "Silver", "Iron", "Copper", "Zinc", "Helium", "Nitrogen",
        "Neon", "Sodium", "Chlorine", "Aluminum", "Lead", "Platinum", "Nickel", "Tin", "Calcium", "Argon"}},
    {"Emotion", {"Happy", "Sad", "Angry", "Scared", "Excited", "Surprised", "Nervous", "Anxious", "Confused", "Bored",
        "Hopeful", "Jealous", "Lonely", "Guilty", "Proud", "Relaxed", "Grateful", "Joyful", "Tired", "Calm"}}
};

vector<string> hangman_ascii = {
    "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
    "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
};

int main() {
    srand(time(0));
    while (true) {
        vector<string> categories;
        for (auto& kv : word_categories) categories.push_back(kv.first);
        string category = categories[rand() % categories.size()];
        vector<string>& words = word_categories[category];
        string word = words[rand() % words.size()];
        string display(word.size(), '-');
        vector<char> wrong_letters;
        int tries = 6;
        clear_screen();
        cout << "The word is a/an: " << category << endl;
        while (tries > 0 && display.find('-') != string::npos) {
            clear_screen();
            cout << hangman_ascii[6 - tries] << endl;
            cout << "\nWord: " << display << endl;
            cout << "Wrong letters: ";
            for (char c : wrong_letters) cout << c << ' ';
            cout << "\nTries left: " << tries << endl;
            string input;
            cout << "Guess a letter: ";
            cin >> input;
            if (input.length() != 1 || !isalpha(input[0])) {
                cout << "Please enter a single letter (a-z)." << endl;
                continue;
            }
            char guess = tolower(input[0]);
            bool already_wrong = find(wrong_letters.begin(), wrong_letters.end(), guess) != wrong_letters.end();
            bool already_revealed = false;
            for (size_t i = 0; i < word.size(); ++i) {
                if (tolower(word[i]) == guess && display[i] == word[i]) {
                    already_revealed = true;
                    break;
                }
            }
            if (already_wrong || already_revealed) {
                cout << "You already tried \"" << guess << "\"! Try another letter" << endl;
                continue;
            }
            bool found = false;
            for (size_t i = 0; i < word.size(); ++i) {
                if (tolower(word[i]) == guess) {
                    display[i] = word[i];
                    found = true;
                }
            }
            if (found) cout << "Good job!" << endl;
            else { tries--; wrong_letters.push_back(guess); cout << "Wrong!" << endl; }
        }
        if (display.find('-') == string::npos)
            cout << "\nCongratulations! The word was: " << word << endl;
        else
            cout << "\nGame over! The word was: " << word << endl;
        cout << "Play again? (y/n): ";
        string answer;
        cin >> answer;
        if (answer.empty() || (answer[0] != 'y' && answer[0] != 'Y')) break;
    }
    cout << "Thanks for playing!" << endl;
    return 0;
} 