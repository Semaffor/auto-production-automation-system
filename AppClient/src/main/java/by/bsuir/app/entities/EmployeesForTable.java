//package by.bsuir.app.entities;
//
//import com.bsuir.entities.Contacts;
//import com.bsuir.entities.Employees;
//import com.bsuir.entities.Position;
//import com.bsuir.jdbc.TableNames;
//import sample.clientConnection.Phone;
//
//import java.util.ArrayList;
//
//public class EmployeesForTable extends Employees {
//    private String position;
//    private String address;
//    private int phone;
//    private String social;
//
//    public EmployeesForTable(int id, String login, String email, String gender, int age, String startDate,
//                             String endDate, int position_id, String name, String surname,
//                             String thirdname, String position) {
//        super(id, login, email, gender, age, startDate, endDate, position_id, name, surname, thirdname);
//        this.position = position;
//    }
//
//    public EmployeesForTable(Employees e) {
//        super(e);
//        autofill();
//    }
//    public String getPosition() {
//        return position;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public int getPhone() {
//        return phone;
//    }
//
//    public String getSocial() {
//        return social;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    private void autofill() {
//        Phone.writeLine("SELECT * FROM " + TableNames.POSITIONS + " WHERE id = " + position_id);
//        ArrayList<Position> pos = (ArrayList<Position>) Phone.readObject();
//        if (!pos.isEmpty())
//            this.position = pos.get(0).getPositionName();
//
//        Phone.writeLine("SELECT * FROM " + TableNames.CONTACTS + " WHERE email = '" + email + "'");
//        ArrayList<Contacts> contacts = (ArrayList<Contacts>) Phone.readObject();
//        if (!contacts.isEmpty()) {
//            this.address = contacts.get(0).getAddress();
//            this.phone = contacts.get(0).getPhone();
//            this.social = contacts.get(0).getSocial();
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "EmployeesForTable{" +
//                "position='" + position + '\'' +
//                ", address='" + address + '\'' +
//                ", phone=" + phone +
//                ", social='" + social + '\'' +
//                '}';
//    }
//}
