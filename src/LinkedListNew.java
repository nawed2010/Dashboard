import sun.awt.image.ImageWatched;

/**
 * Created by nawed2010 on 26/06/19.
 */
public class LinkedListNew {
    Node head = null;
    Node tail = null;
    int length;

    public  LinkedListNew() {
        //Empty constructor for initializing an empty linked list
        head = null;
        tail = null;
        length = 0;
    }

    public LinkedListNew(String position, int...values) {
        for (int value : values) {
            if(position.equalsIgnoreCase("start")){
                addToStart(value);
            } else {
                //By default add to last using tail node
                addToEnd(value);
            }
            length ++;
        }
    }

    public int size(){
        if (head == null) {
            length = 0;
            return length;
        } else {
            int count = 0;
            Node current = head;
            while (current != null) {
                count ++;
                current = current.getNext();
            }
            length = count;
            return length;
        }
    }

    public boolean isEmpty() {
        if(head == null) {
            return true;
        } else {
            return false;
        }
    }

    public void printList() {
        if(head != null) {
            Node current = head;
            while (current != null) {
                System.out.print(" "+current.getData());
                current = current.getNext();
            }
        } else {
            System.out.println("Empty Linked list");
        }
    }

    public void addToStart(int value) {
        if (head == null) {
            //Make it first node
            Node node = new Node(null, value);
            head = node;
        } else {
            //Make head node second
            Node node = new Node(head, value);
            head = node;
            //No impact on tail node
        }

        length ++;
    }

    public void addToEnd(int value) {
        if(head == null) {
            //Make it first as well as last node
            Node node = new Node(null, value);
            head = node;
            tail = node;
        } else {
            //If tail node is being tracked
            if (tail != null) {
                Node node = new Node(null, value);
                tail.setNext(node);
                tail = node;
                //No impact on head node
            } else {
                //If tail node is not being tracked
                Node current = head;
                while (current.getNext() != null) {
                    current = current.getNext(); //Find tail node
                }
                Node node = new Node(null, value);
                current.setNext(node); //Currently current holds tail node
                tail = node; //Make new tail node
                //No impact on head node
            }
        }
        length ++ ;
    }

    /**
     * Insert value after the position
     * @param value
     * @param position
     */
    public void addToPosition(int value, int position) {
        if(head == null) {
            //Make it the first node
            Node node = new Node(null, value);
            head = tail = node;
        } else {
            if (position > size()) {
                System.out.println("Position exceeds length of Linked List");
            } else if ( position == size()){
                //Insertion request at tail node
                addToEnd(value);
            } else if (position < 1) {
                System.out.println("Invalid position as it can't be less then 1");
            } else if (position == 1) {
                //Insert at head position
                addToStart(value);
            } else {
                //Insertion request at middle
                Node node = new Node(null, value);
                Node current = head;
                int count = 1;
                while (count < position) {
                    count ++ ;
                    current = current.getNext();
                }

                Node temp = current.getNext(); //Get next of current node
                current.setNext(node);
                node.setNext(temp);
            }
        }
        length ++ ;
    }

    /***
     * Delete exactly the same position
     * @param position
     */
    public void deleteNode(int position) {
        if (head == null) {
            System.out.println("Linked list is empty");
        } else {
            if(position > size()) {
                System.out.println("Can't delete beyond the size of linked list");
            } else if (position == 1) {
                //Delete head position, make the next element the new head
                head = head.getNext();
            } else if (position == size()){
                //Delete tail node
                Node current = head;
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                //Now current holds the node prior to tail node
                current.setNext(null);
                tail = current;
            } else if (position < 1) {
                System.out.println("Invalid position as it can't be less than 1");
            } else {
                Node current = head;
                int count = 2;
                while(count < position) {
                    count ++;
                    current = current.getNext();
                }
                current.setNext(current.getNext().getNext());
            }
            length --;
        }
    }


}
