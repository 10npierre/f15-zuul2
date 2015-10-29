/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Inventory inventory = new Inventory();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room roadside, followRoad, woods, campsite, deepWoods, dodgeWalker, confrontCampers, sneakAway;
      
        // create the rooms
        roadside = new Room("Wandering around the road is not a very good idea, get moving. . .");
        followRoad = new Room("There's movement in the distance as you draw nearer to what looks like a campsite");
        woods = new Room("You decide the darkly lit and densely popuated woodline is your best option. . . What could go wrong?");
        campsite = new Room("(Ah, civilization. But is that a good thing?) Six campers quietly converse huddled around a fireplace");
        deepWoods = new Room("(Not easily intimidated I see), Suddeny there's a deep growl and a walker emerges from the darkness headed right at you.");
        dodgeWalker = new Room("The lifeless corpse is quick to lunge for your fleshy face but you're quick this time and shove it face first stumbling down a slight mound");
        confrontCampers = new Room("You're starving, desperate enough to try to trust these strangers and they greet you warmly with the hot end of a loaded pistol");
        sneakAway = new Room("Trust is a long forgotten commodity, you're only option is to sneak around their camp and hope for a little luck");
        
        
        // These are the 'exits' from the player's starting point "roadside"
        roadside.setExit("east", woods);
        roadside.setExit("north", followRoad);
      
        //These are the exits from "followRoad"
        followRoad.setExit("north", campsite);
        followRoad.setExit("east", woods);
       
        
        //These are the exits from "woods"
         woods.setExit("east", deepWoods);
         
         //These are the exits for "deepWoods"
         deepWoods.setExit("east", dodgeWalker);
         
         //These are the exits for "campsite"
         campsite.setExit("north", confrontCampers);
         campsite.setExit("east", sneakAway);
         campsite.conditionsMet(false);
        
         //These are the exits for "dodgeWalker"
        dodgeWalker.setItem(new Item("Gun", "Fully loaded Colt .45 revolver"));
        dodgeWalker.getItem().setUseDirection("down");
        dodgeWalker.setNeeds("GUN");
        dodgeWalker.setConditionalExit("north", campsite);
        
        


        currentRoom = roadside;  // start game at "roadside"
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the land of the Walkind Dead");
        System.out.println("Trust no one, don't hesitate to kill, and Just Survive Somehow");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case SEARCH:
                searchRoom();
                break;
                
            case TAKE:
                takeItem(command);
                break;
                
            case USE:
                useItem(command);
                break;
                
            case QUIT:
                wantToQuit = quit(command);
                break;
                
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("No one's going to show you any sympathy");
        System.out.println("It's kill or be killed. . .");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("That's a bad idea");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /** 
    * Searches the room for items.
    */
   private void searchRoom()
   {
       if (currentRoom.hasItem()) {
           System.out.println("The room contains a(n)" + currentRoom.getItem().getDescription());
       }
       else
       {
           System.out.println("There is nothing interesting in this room.");
        }       
   }
    
    /** 
    * Takes the item from the room.
    */
   private void takeItem(Command command)
   {
       if (currentRoom.hasItem()) {
           
           if (command.getSecondWord().equalsIgnoreCase(currentRoom.getItem().getName()))
           {
               System.out.println("Took the " + currentRoom.getItem().getDescription() + ".");
               inventory.addItem(currentRoom.takeItem());
           }   
           else 
           {
               System.out.println("What exactly are you trying to take?");
           }
       }
       else
       {
           System.out.println("There is nothing to take in this room.");
        }       
   }
   
   /** 
    * Uses the item specified.
    */
   private void useItem(Command command)
   {
       if (inventory.hasItem(command.getSecondWord())) {
           if (currentRoom.needs(command.getSecondWord()))
           {
               currentRoom.conditionalNeighbor.conditionsMet(true);
               System.out.println("Success! " + inventory.getItem(command.getSecondWord()).getUseText() );
           }
           else
           {
               System.out.println("That doesn't help us.");
           }
           
       }
       else
       {
           System.out.println("You don't have an item with that name");
        }       
   }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}