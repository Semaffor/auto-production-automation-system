//package by.bsuir.app.constansts;
//
//import com.bsuir.entities.*;
//import com.bsuir.jdbc.TableNames;
//import sample.clientConnection.Phone;
//import sample.entities.CarForTable;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class Data {
//    private static Employees employee;
//    private static ArrayList<Feedback> feedbackList;
//    private static ArrayList<CarForTable> carList;
//    private static ArrayList<CarForTable> carListCopy;
//    private static ArrayList<Model> modelList;
//    private static Position position;
//    private static Contacts contacts;
//    private static ArrayList<?> generalList;
//
//    public static void setEmpl(Employees empl) {
//        Data.employee = empl;
//    }
//
//    public static ArrayList<Model> getModelList() {
//        return modelList;
//    }
//
//    public static void setModelList(ArrayList<Model> modelList) {
//        Data.modelList = modelList;
//    }
//
//    public static ArrayList<CarForTable> getCarListCopy() {
//        return carListCopy;
//    }
//
//    public static void setCarListCopy(ArrayList<CarForTable> carListCopy) {
//        Data.carListCopy = carListCopy;
//    }
//
//    public static ArrayList<CarForTable> getCarList() {
//        return carList;
//    }
//
//    public static void setCarList(ArrayList<CarForTable> carList) {
//        Data.carList = carList;
//    }
//
//    public static Employees getEmpl() {
//        return employee;
//    }
//
//    public static void setFeedbackList(ArrayList<Feedback> feedbackList) {
//        Data.feedbackList = feedbackList;
//    }
//
//    public static void setPosition(Position position) {
//        Data.position = position;
//    }
//
//    public static void setContacts(Contacts contacts) {
//        Data.contacts = contacts;
//    }
//
//    public static ArrayList<Feedback> getFeedbackList() {
//        return feedbackList;
//    }
//
//    public static Contacts getContacts() {
//        return contacts;
//    }
//
//    public static Position getPosition() {
//        return position;
//    }
//
//    public static void loadData() throws SQLException{
//
//        loadPosition();
//        loadContacts();
//    }
//
//    private static void loadPosition() throws SQLException{
//        String request = "SELECT * FROM " + TableNames.POSITIONS +
//                " WHERE id = " + employee.getPosition_id();
//        Phone.writeLine(request);
//        generalList = (ArrayList<?>) Phone.readObject();
//        System.out.println(generalList);
//
//        try {
//            position = (Position) generalList.get(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            position = new Position(0,"Ошибка",0,0,0);
//        }
//    }
//
//    private static void loadContacts() throws SQLException {
//        String request = "SELECT * FROM " + TableNames.CONTACTS +
//                " WHERE email = '" + employee.getEmail() + "' ";
//        System.out.println(request);
//        Phone.writeLine(request);
//        generalList = (ArrayList<?>) Phone.readObject();
//        System.out.println(generalList);
//
//        try {
//            contacts = (Contacts) generalList.get(0);
//        } catch (IndexOutOfBoundsException e){
//            e.printStackTrace();
//            contacts = new Contacts(null,null,0,null);
//        }
//    }
//}
