import java.util.*;

public class AssassinManager {

  AssassinNode killRing;// represents the list of the killring beginning by the head
  AssassinNode graveYard;//same thing for the graveYard...

  public AssassinManager(ArrayList<String> names) throws IllegalArgumentException {

   //Method 1: add the names to the killRing using a for loop. To do so, create temporary node (AssassinNode) variables such that the list doesn't cut off.
   //          if the killRing is empty then assign the name to the head (ONCE), else do so using the current variable. 
   if(names.size() < 1){throw new IllegalArgumentException();}//if list is empty then throws an exception error
   else{
      for(int index = 0; index < names.size(); index++){
      AssassinNode killer = new AssassinNode(names.get(index));
      
      if(killRing == null){killRing = killer;} 
      else {AssassinNode current = killRing;
            while(current.next != null){current = current.next;}//traverse the list of names
            current.next = killer;//assign the nodes without changing the killRing head to a new head otherwise the list will be gone.
      }
   }
}
    /* Method 2: Add the names to the killRing one by one.
    killRing = new AssassinNode(names.get(0), new AssassinNode(names.get(1),
        new AssassinNode(names.get(2), new AssassinNode(names.get(3),
            new AssassinNode(names.get(4), new AssassinNode(names.get(5),
                new AssassinNode(names.get(6), new AssassinNode(names.get(7)))))))));*/
  }

  public void printKillRing() {
    AssassinNode current = killRing;
    //Prints the killRing (kill list). If the current person reached the end of the list, then assign his victim to be the head of the list such that it is a circular list.
    while (current != null) {
      if(current.next == null) System.out.println(current.name + " is stalking " + killRing.name);
      else {System.out.println(current.name + " is stalking " + current.next.name);}
      current = current.next;}
  }

  public void printGraveyard() {
  //Prints the the victims inside the graveYard. The important thing here is to watch out for the last node (currentVictim.next == null). If so, print the last statement and exit the loop.
  // the killer attribute can be better understood in the kill method. The killer is the node before the victim, where the victim is the next node. Since he is being stalked by the killer.
      AssassinNode currentVictim = graveYard;
      while (currentVictim != null) {
        if(currentVictim.next == null)
        System.out.println(currentVictim.name + " was killed by " + currentVictim.killer);
        else
        System.out.println(currentVictim.name + " was killed by " + currentVictim.killer);
        currentVictim = currentVictim.next;}
  }
  //Returns true if the first node is the name, else, loop through until the name is found. else, return false (name not found).
  public boolean killRingContains(String name) {
    AssassinNode current = killRing;
    while(current != null){
       if(current.name.equalsIgnoreCase(name)){
          return true;
       }else current = current.next;
    }return false;//no name found since current == null at this point
  }
  //If the graveYard is empty then return false such that the person that is to be dead is not dead yet. Then, traverse the list until the currentVictim is found to be the target (having the required name).If he is found then return true such that he/she is already dead
  // else Return false if that name doesn't exist yet.
  public boolean graveyardContains(String name) {
    AssassinNode currentVictim = graveYard;
    if(currentVictim == null) return false;
    while (currentVictim != null) {
      if(currentVictim.name.equalsIgnoreCase(name)){ return true;}
      else currentVictim = currentVictim.next;}
    return false;
  }
  //If the head is the only person alive then the game is over. This is defined by the .next node being null and thus cutting off the list. Else return false;
  public boolean isGameOver() {
   if(killRing.next == null) return true;
   else return false;
  }
  //If the head is the only one alive then return his/her name. I'm saying the head here because, even if the head was initially killed, a new head would be assigned.
  //Moreover, the head will be reassigned depending on the killed victims. This is done in the kill method. Else return null such that there are still killers stalking victims.
  public String winner() {
   if(isGameOver() == true) return killRing.name;
   else return null;
  }

  public void kill(String name) throws IllegalStateException, IllegalArgumentException {

    if(isGameOver() == true) throw new IllegalStateException();//If the game is over and a new victim was to be assigned then throw this exception indicating that the game is over. (only one killer left while all other nodes and victims are dead)
    else if(!killRingContains(name)) throw new IllegalArgumentException("Invalid name");
    else {
      AssassinNode currentVictim = null;
      
      //if the victim is at the head of the list.
      if(killRing.name.equalsIgnoreCase(name)){     
         String killer = "";
         AssassinNode current = killRing;
         while(current != null){ //
            if(current.next == null){killer = current.name;}//assigns the last node as the killer since this is a circular list and will stalk the first node in the list.
                                     current = current.next;}//end the traversal

         currentVictim = killRing;//the victim is the head of the killRing
         currentVictim.killer = killer;//assign the killer to the current.prev.name. but this is a singly list so i assigned the killer as a string in a variable.
         killRing = killRing.next;//to make a new head with one less node (such that it shows that the victim was killed)
         
      } else {//if the victim is not at the head of the list.
         AssassinNode current = killRing;
         while(current.next != null){
            if(current.next.name.equalsIgnoreCase(name)){
               String killer = current.name;//store the killer name in this killer variable since this is a singly linked list so i cant go back using the prev attribute.
               currentVictim = current.next;  //moreover, i can't assign currentVictim.killer = current.name since currentVictim is null before this line (116).
               currentVictim.killer = killer;
               if(current.next.next != null){ current.next = current.next.next;break;}//in order to make sure that i only need to kill one person. break the loop. moreover, i used 2 curent nodes (currentVictim and current)
                                                  //such that the current loops over the names while the currentVictim stays as one node here. this is because i want to insert one new victim per time to the graveYard.
               else {current.next = null; break;}//this can be seen in the following code where i wrote currentVictim.next = graveYard and did not loop through the currentVictim as this loop on the left of my comment does so using the current node.   
            }  current = current.next;//traverse the list
         }
      }
      if(graveYard != null){currentVictim.next = graveYard;}//sets the new victim as the head of the graveYard. watch out here that currentVictim.next does not affect the previous victim. i.e. it adds to the victims list.
      else {currentVictim.next = null;}
      graveYard = currentVictim; } //sets the graveYard as the victims. i.e. graveYard.next = currentVictim.next and so on.... it contains all.
   
   }
  
}