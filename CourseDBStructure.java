import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure implements CourseDBStructureInterface{

    private Node hashTable[];
    private int hashTableSize;
    private double LOAD_FACTOR=1.5;
    //constructors
    public CourseDBStructure(int numOfCourses)
    {
        hashTableSize=primeGenerator(numOfCourses,LOAD_FACTOR);
        hashTable=new Node[hashTableSize];



    }

    //test constructor
    public CourseDBStructure(String test, int size){
        hashTableSize=size;
      hashTable=new  Node[hashTableSize];



    }

    /**
     * Adds a CourseDBElement object to the CourseDBStructure using the hashcode
     * of the CourseDatabaseElement object's crn value.
     * If the CourseDatabaseElement already exists, exit quietly
     *
     * @param element the CourseDBElement to be added to CourseDBStructure
     */
    public void add(CourseDBElement element){
        String value=String.valueOf(element.getCRN());
        int position=value.hashCode()%getTableSize();
        Node currentNode=hashTable[position];

//        boolean exists=false;
        Node newNode= new Node(element);
        if (currentNode==null) {
            hashTable[position]=newNode;
        } else {
            while (currentNode != null) {
                if (currentNode.getData().getCRN() == element.getCRN()) {
                    currentNode.getData().setId(element.getID());
                    currentNode.getData().setInstructorName(element.getInstructorName());
                    currentNode.getData().setNumOfCredits(element.getNumOfCredits());
                    currentNode.getData().setRoomNum(element.getRoomNum());
                    break;

                } else if (currentNode.next == null) {
                    currentNode.next = newNode;
                    break;
                } else
                    currentNode = currentNode.next;
            }
        }

    }

    /**
     * Find a courseDatabaseElement based on the key (crn) of the
     * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
     * throw an IOException
     *
     * @param crn crn (key) whose associated courseDatabaseElement is to be returned
     * @return a CourseDBElement whose crn is mapped to the key
     * @throws IOException if key is not found
     */

    public CourseDBElement get(int crn) throws IOException{
        String value=String.valueOf(crn);
        int position=value.hashCode()%getTableSize();
        Node currentNode=hashTable[position];


        while (currentNode!=null )
        {
            if(currentNode.getData().getCRN()==crn)
            {
                return currentNode.getData();
            }

                currentNode=currentNode.next;
        }
        throw new IOException();


    }

    /**
     * @return an array list of string representation of each course in
     * the data structure separated by a new line.
     * Refer to the following example:
     * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
     * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
     */

    public ArrayList<String> showAll(){
        ArrayList<String> courses=new ArrayList<String>();
        for(int i=0; i<hashTable.length; i++)
        {
            Node currentNode=hashTable[i];
            while (currentNode!=null){
                CourseDBElement currentCourse=currentNode.getData();
                courses.add("\n"+"Course:"+currentCourse.getID()+ " CRN:"+currentCourse.getCRN()+" Credits:"+currentCourse.getNumOfCredits()+
                        " Instructor:"+currentCourse.getInstructorName()+" Room:"+currentCourse.getRoomNum());
                currentNode=currentNode.next;
            }
        }
        return courses;

    }
    /**
     * Returns the size of the ConcordanceDataStructure (number of indexes in the array)
     */
    public int getTableSize(){
        return hashTableSize;
    }

    /**
     * Hash function which will take a crn and determine its position ia given array size
     */
    public int hashFunction( String value, int size)
    {

        int hash = 0;
        int G=2;
        int n = value.length();
        for (int i = 0; i < n; i++)
            hash = G * hash + value.charAt(i);

        return hash%size;

    }
    /**
     * A method that generates a prime number using load factor approach to be used a size of the hash table
     * @returns a prime number
     */
    public static int primeGenerator(int n, double loadFactor)
    {
        int prime;
        int highDivisor;
        int divisor;
        boolean fourKPlusThree=false;
        boolean isPrime=false;
        // a for the prime number is initialized
        prime= (int) (n/loadFactor);
        //if the resulted number out of the division is even, 1 is added to prime to make prime odd
        if(prime%2==0) {
            prime = prime + 1;//chang prime to be odd
        }

        while (fourKPlusThree==false){//main goal is to change this to make this true
            while (isPrime==false){//indicates that number is still not prime
                //start by initializing a high divisor
                highDivisor=(int)(Math.sqrt(prime)+0.5);
                for(divisor=highDivisor;divisor>1;--divisor) {
                    if(prime%divisor==0)//means that the prime number has divisor
                    {
                        break; //we exit the loop and update prime

                    }

                }
                if (divisor!=1)
                {
                    prime=prime+2; // we add 2 to prime to make sure it is still odd
                }

                else
                    isPrime=true;

            }
            //secondary check for the validity of the prime number
            if((prime - 3) % 4 == 0)
                fourKPlusThree = true;
            else
            {  prime = prime + 2;
                isPrime = false;
            }


        }

        return prime;
    }



//Node inner class
    protected class Node{
        protected Node next;
        protected CourseDBElement data;

    private Node(CourseDBElement dataEntry) {
        this(dataEntry, null);
    }

    /**
     * Constructor
     */
    private Node(CourseDBElement dataEntry, Node nextNode) {
        data = dataEntry;
        next = nextNode;


    }

    private CourseDBElement getData() {
        return data;
    }
    }

}
