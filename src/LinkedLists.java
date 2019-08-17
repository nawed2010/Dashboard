/**
 * Created by nawed2010 on 07/02/17.
 */
public class LinkedLists {
    public int size;          //Gives size of the LinkedList
    protected  Node start;   //HEAD Node - Need to track it always
    protected  Node end;    //Tail Node - Not required to track - though useful while inserting in tail location

    //Constructor for creating empty linked list
    public LinkedLists(){
        start = null;
        end = null;
        size = 0;
    }

    //Constructor for creating linked list with pre-loaded data
    public LinkedLists(int...values){
        for(int value : values){
            insertAtStart(value);
        }
    }

    /**
     * This method returns whether the LL is empty or not
     * @return boolean:Returns boolean
     **/
    public boolean isEmpty(){
        return start == null;
    }

    /**
     * This method returns the size of LL
     * @return int:Returns int
     */
    public int getSize(){
        return size;
    }

    /**
     * This method is to insert a new node at Head and make it as the new Head
     **/
    public void insertAtStart(int val){
        Node node = new Node(null, val);
        size++;

        if(start == null){
            start = node;
            end = start;
        }else {
            node.setNext(start);
            start = node;
        }
    }

    /**
     * This method is to insert a new node at Tail and make it as the end node
     **/
    public void insertAtTail(int val){
        Node node = new Node(null, val);
        end.setNext(node);
        end = node;
        size++;
    }

    /**
     * This method is to insert a new node at Middle of the LinkedList
     **/
    public void insertAtMiddle(int val, int location){
        if(location>size){
            System.out.println("Invalid location as size of the list is: "+size);
        }else{
            Node traverse = start;
            for(int i=1; i<location; i++){
                traverse = traverse.getNext();
            }
            Node node = new Node(traverse.getNext(), val);
            traverse.setNext(node);
            size++;
        }
    }

    public void deleteAtPosition(int location){
        if(location > size){
            System.out.println("Invalid location as size of the list is: "+size);
        }else if(location == 1){
            start = start.getNext();
            size--;
        }else{
            Node traverse = start;
            for(int i = 1 ; i<location-1; i++){
                traverse = traverse.getNext();
            }
            traverse.setNext(traverse.getNext().getNext());
            size--;
        }
    }

    /**
     * This method is to print the whole LinkedList
     **/
    public void display(){
        if(size == 0){
            System.out.println("Singly Linked List is empty!");
            return;
        }else if(start.getNext() == null){
            System.out.println("Singly Linked List => Head->"+start.getData());
            return;
        }else{
            System.out.println("Singly Linked List = ");
            Node traverse = start;
            while(traverse != null){
                System.out.print(traverse.getData());
                if(traverse.getNext()!=null){
                    System.out.print("->");
                }
                traverse = traverse.getNext();
            }
        }
    }

}
