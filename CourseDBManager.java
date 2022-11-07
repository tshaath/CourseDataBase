import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.*;

public class CourseDBManager implements CourseDBManagerInterface{

    CourseDBStructure structure=new CourseDBStructure(500);

    /**
     * Adds a course (CourseDBElement) with the given information
     * to CourseDBStructure.
     * @param id course id
     * @param crn course crn
     * @param credits number of credits
     * @param roomNum course room number
     * @param instructor name of the instructor
     */
    public void add(String id, int crn, int credits, String roomNum, String instructor){
        CourseDBElement newCourse= new CourseDBElement(id, crn,credits,roomNum,instructor);
        structure.add(newCourse);

    }

    /**
     * finds  CourseDBElement based on the crn key
     * @param crn course crn (key)
     * @return a CourseDBElement object
     *
     */
    public CourseDBElement get(int crn){
        try {
            return structure.get(crn);
        }
        catch(IOException e) {

        }
        return null;
    }

    /**
     * Reads the information of courses from a test file and adds them
     * to the CourseDBStructure data structure
     * @param input input file
     * @throws FileNotFoundException if file does not exists
     */
    public void readFile(File input) throws FileNotFoundException{

        try{
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String words[] = line.split(" ");
            String profName = "";

            for (int i = 4; i< words.length;i++){
                profName += words[i];
            }

            CourseDBElement c = new CourseDBElement<>(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3], profName);
            structure.add(c);

        }}
        catch (FileNotFoundException e){}


    }
    /**
     * @return an array list of string representation of each course in
     * the data structure separated by a new line.
     * Refer to the following example:
     * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
     * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
     */
    public ArrayList<String> showAll(){

        return structure.showAll();
    }
}
