import os
import random

word_categories = {
    "Animal": [
        "Lion", "Elephant", "Tiger", "Giraffe", "Zebra", "Bear", "Wolf", "Kangaroo", "Panda", "Camel",
        "Fox", "Leopard", "Cheetah", "Rhino", "Monkey", "Goat", "Sheep", "Cow", "Dog", "Cat",
        "Rabbit", "Mouse", "Squirrel", "Deer", "Owl", "Fish", "Bird", "Duck", "Frog", "Snake"
    ],
    "Country": [
        "Egypt", "Germany", "Brazil", "Japan", "Canada", "Italy", "Spain", "France", "China", "India",
        "Mexico", "Russia", "Australia", "Argentina", "Kenya", "Sweden", "Norway", "Denmark", "Thailand", "Vietnam",
        "Pakistan", "Indonesia", "Philippines", "Morocco", "South Africa", "Ukraine", "USA", "UK", "Korea", "Greece"
    ],
    "Fruit": [
        "Apple", "Banana", "Mango", "Orange", "Grape", "Watermelon", "Peach", "Pear", "Pineapple", "Cherry",
        "Plum", "Kiwi", "Lemon", "Lime", "Blueberry", "Strawberry", "Coconut", "Avocado", "Fig", "Date"
    ],
    "Color": [
        "Red", "Blue", "Green", "Yellow", "Purple", "Pink", "Orange", "Brown", "Black", "White",
        "Grey", "Gold", "Silver", "Navy", "Teal", "Olive", "Violet", "Turquoise", "Maroon", "Beige"
    ],
    "Sport": [
        "Football", "Tennis", "Cricket", "Basketball", "Swimming", "Baseball", "Rugby", "Volleyball", "Golf", "Boxing",
        "Cycling", "Skiing", "Wrestling", "Karate", "Judo", "Badminton", "Hockey", "Handball", "Surfing", "Diving"
    ],
    "Profession": [
        "Doctor", "Nurse", "Engineer", "Teacher", "Lawyer", "Architect", "Scientist", "Dentist", "Mechanic", "Pilot",
        "Chef", "Artist", "Musician", "Soldier", "Policeman", "Firefighter", "Journalist", "Accountant", "Actor", "Driver"
    ],
    "Vehicle": [
        "Car", "Truck", "Bus", "Motorcycle", "Bicycle", "Van", "Train", "Airplane", "Boat", "Ship",
        "Taxi", "Yacht", "Jeep", "Scooter", "Helicopter", "Submarine", "Rocket", "Spaceship", "Tram", "Ambulance"
    ],
    "Body Part": [
        "Head", "Eye", "Ear", "Nose", "Mouth", "Face", "Hair", "Neck", "Shoulder", "Arm",
        "Hand", "Finger", "Chest", "Back", "Leg", "Knee", "Foot", "Toe", "Brain", "Heart"
    ],
    "Clothing": [
        "Shirt", "Pants", "Jeans", "Jacket", "Coat", "Sweater", "T-shirt", "Shorts", "Skirt", "Dress",
        "Socks", "Shoes", "Boots", "Hat", "Cap", "Scarf", "Gloves", "Belt", "Tie", "Suit"
    ],
    "Tool": [
        "Hammer", "Screwdriver", "Wrench", "Pliers", "Saw", "Drill", "Axe", "Shovel", "Scissors", "Knife",
        "Ladder", "Rake", "Mallet", "Level", "Tape Measure", "Toolbox", "Clamp", "Chisel", "Paintbrush", "Crowbar"
    ],
    "City": [
        "London", "Paris", "Berlin", "Tokyo", "Madrid", "Rome", "Moscow", "Beijing", "New York", "Sydney",
        "Dubai", "Istanbul", "Mumbai", "Delhi", "Los Angeles", "Chicago", "Toronto", "Bangkok", "Seoul", "Singapore"
    ],
    "Insect": [
        "Ant", "Bee", "Wasp", "Butterfly", "Moth", "Mosquito", "Dragonfly", "Grasshopper", "Cricket", "Fly",
        "Beetle", "Ladybug", "Spider", "Flea", "Tick", "Worm", "Hornet", "Gnat", "Aphid", "Termite"
    ],
    "Planet": [
        "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Moon", "Sun"
    ],
    "Element": [
        "Hydrogen", "Oxygen", "Carbon", "Gold", "Silver", "Iron", "Copper", "Zinc", "Helium", "Nitrogen",
        "Neon", "Sodium", "Chlorine", "Aluminum", "Lead", "Platinum", "Nickel", "Tin", "Calcium", "Argon"
    ],
    "Emotion": [
        "Happy", "Sad", "Angry", "Scared", "Excited", "Surprised", "Nervous", "Anxious", "Confused", "Bored",
        "Hopeful", "Jealous", "Lonely", "Guilty", "Proud", "Relaxed", "Grateful", "Joyful", "Tired", "Calm"
    ]
}

hang_man_ascii = ['''
    +---+
    |   |
        |
        |
        |
        |
  =========''', '''
    +---+
    |   |
    O   |
        |
        |
        |
  =========''', '''
    +---+
    |   |
    O   |
    |   |
        |
        |
  =========''', '''
    +---+
    |   |
    O   |
   /|   |
        |
        |
  =========''', '''
    +---+
    |   |
    O   |
   /|\  |
        |
        |
  =========''', '''
    +---+
    |   |
    O   |
   /|\  |
   /    |
        |
  =========''', '''
    +---+
    |   |
    O   |
   /|\  |
   / \  |
        |
  =========''']

def play_game():
    os.system('cls')
    category = random.choice(list(word_categories.keys()))
    random_word = random.choice(word_categories[category])
    print("The word is a/an:", category)

    max_attempts = 6
    attempts = 0
    wrong_letters = []
    display = ['-'] * len(random_word)
    print(''.join(display))
    print(hang_man_ascii[0])

    while '-' in display and attempts < max_attempts:
        guessed = input('Please guess a letter: ')

        if guessed in wrong_letters:
            print(f'You already tried {guessed}! Try another letter')
            continue
        elif guessed.lower() in [letter.lower() for letter in random_word]:
            for i in range(len(random_word)):
                if guessed.lower() == random_word[i].lower():
                    if display[i] == '-':
                        display[i] = random_word[i]
                        print(f'Correct! You have {max_attempts - attempts} left attempts')
                    else:
                        print('You already tried ',guessed, 'before!')
        else:
            attempts += 1
            wrong_letters.append(guessed)
            print(f'Wrong! You have {max_attempts - attempts} left attempts')
            idx = attempts if attempts < len(hang_man_ascii) else len(hang_man_ascii) - 1
            print(hang_man_ascii[idx])
            print("Wrong letters:", ', '.join(wrong_letters))

        print(''.join(display))

    if '-' not in display:
        print("""
  **********
   YOU WIN!
  **********
            """)
    else:
        print("""
  ************
   GAME OVER!
  ************
            """)
    print(f'The {category} was {random_word}')

if __name__ == "__main__":
    while True:
        play_game()
        answer = input("Play again? (y/n): ").strip().lower()
        if not answer.startswith('y'):
            break
    print("Thanks for playing!")