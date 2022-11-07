public class CourseDBElement<T> implements Comparable<T>{
    private String id;
    private int crn;
    private int numOfCredits;
    private String roomNum;
    private String instructorName;

    public CourseDBElement(String id, int crn, int numOfCredits, String roomNum, String instructorName)
    {
        this.id=id;
        this.crn=crn;
        this.numOfCredits=numOfCredits;
        this.roomNum=roomNum;
        this.instructorName=instructorName;

    }

    public int getCRN() {
        return crn;
    }

    public String getID()
    {
        return id;
    }
    public String getRoomNum()
    {
        return roomNum;
    }
    public int getNumOfCredits()
    {
        return numOfCredits;
    }
    public String getInstructorName()
    {
        return instructorName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCrn(int crn) {
        this.crn = crn;
    }

    public void setNumOfCredits(int numOfCredits) {
        this.numOfCredits = numOfCredits;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getId() {
        return id;
    }

    public int getCrn() {
        return crn;
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }
}
