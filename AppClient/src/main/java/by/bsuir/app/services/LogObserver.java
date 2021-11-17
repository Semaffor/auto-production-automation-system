//package by.bsuir.app.services;
//
//import by.bsuir.app.util.connection.Phone;
//
//public class LogObserver {
//    public static void authLogging(int id) {
//        Phone.writeLine("INSERT INTO Logging_history (employee_ID, entranceDate) " +
//                " VALUES(" + id + ", GETDATE())");
//        if (!Phone.readLine().equals("GOOD"))
//            System.out.println("Ошибка логгирования.");
//    }
//}
