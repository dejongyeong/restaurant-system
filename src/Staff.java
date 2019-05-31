import java.io.Serializable;

/**
 * Created by De Jong Yeong on 16/11/2016.
 * @author De Jong Yeong
 * @version 1.0
 */

public class Staff implements Serializable {

    //attributes
    private String name;
    private String position;
    private String address;
    private String pps;
    private String dob;
    private String phone;
    private char gender;

    /**
     * Constructor method
     */
    public Staff() {
        this("Unknown","Unknown",'U',"Unknown","Unknown","Unknown","Unknown");
    }

    /**
     * 5-arguments constructor
     * @param name The full name of the staff
     * @param position The position of the staff (Manager, Waiter, Cleaner)
     * @param address The address of the staff
     * @param gender The gender of the staff
     * @param pps The pps of the staff
     * @param dob The date of birth of the staff
     * @param phone The contact number of the staff
     */
    public Staff(String pps, String name, char gender, String dob, String address, String phone, String position) {
        this.name = name;
        this.position = position;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.pps = pps;
        this.phone = phone;
    }

    /**
     * Mutator method to set the staff name
      * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator method to set the staff date of birth
     * @param dob
     */
    public void setDOB(String dob) {
        this.dob = dob;
    }

    /**
     * Mutator method to set the staff's PPS
     * @param pps
     */
    public void setPps(String pps) {
        this.pps = pps;
    }

    /**
     * Mutator method to set the gender of the staff
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Mutator method to set the address of the staff
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Mutator method to set the position of the staff
     * @param position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Mutator method to set the phone of the staff
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Accessor method to return the name of the staff.
     * @return The name of the staff.
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor method to return the pps number of the staff.
     * @return The PPS number of the staff.
     */
    public String getPps() {
        return pps;
    }

    /**
     * Accessor method to return the gender of the staff.
     * @return The gender of the staff.
     */
    public char getGender() {
        return gender;
    }

    /**
     * Accessor method to return the age of the staff.
     * @return The date of birth of the staff.
     */
    public String getDOB() {
        return dob;
    }

    /**
     * Accessor method to return the address of the staff.
     * @return The address of the staff.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Accessor method to return the position of the staff..
     * @return The position of the staff.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Accessor method to return the staff's phone number.
     * @return The phone number of the staff.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * toString() method to return the staff details
     * @return The details of the staff
     */
    @Override
    public String toString() {
        return String.format("PPS No.: %s\nName: %s\nGender: %s\nD.O.B: %s\nAddress: %s\nPhone: %s\nPosition: %s\n",
                getPps(), getName(), getGender(), getDOB(), getAddress(), getPhone(), getPosition());
    }

}
