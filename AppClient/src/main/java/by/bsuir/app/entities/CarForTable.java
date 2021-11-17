//package by.bsuir.app.entities;
//
//import by.bsuir.app.clientConnection.Phone;
//
//import java.util.ArrayList;
//
//public class CarForTable extends Car {
//
//    private String model;
//    private String series;
//    private String body_type;
//    private String fuel_type;
//    private String responsible;
//    private int seats;
//
//    public CarForTable(String VIN, int model_id, int body_type_id, int fuel_type_id, String issue_date,
//                       double price, String price_current, int responsible_id, String gearbox, String description,
//                       double rate, String model) {
//        super(VIN, model_id, body_type_id, fuel_type_id, issue_date, price, price_current,
//                responsible_id, gearbox, description, rate);
//        this.model = model;
//    }
//
//    public CarForTable(Car c, String model) {
//        super(c);
//        this.model = model;
//    }
//
//    public CarForTable(String VIN, int model_id, int body_type_id, int fuel_type_id, String issue_date, double price,
//                       String price_current, int responsible_id, String gearbox, String description, int rate,
//                       String model, String body_type, String fuel_type, String responsible) {
//
//        super(VIN, model_id, body_type_id, fuel_type_id, issue_date, price,
//                price_current, responsible_id, gearbox, description, rate);
//        this.model = model;
//        this.body_type = body_type;
//        this.fuel_type = fuel_type;
//        this.responsible = responsible;
//    }
//
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    public String getSeries() {
//        return series;
//    }
//
//    public CarForTable(Car c, String model, String body_type, String fuel_type,
//                       String responsible) {
//        super(c);
//        this.model = model;
//        this.body_type = body_type;
//        this.fuel_type = fuel_type;
//        this.responsible = responsible;
//    }
//
//    public CarForTable(Car c) {
//        super(c);
//        autofill();
//    }
//
//    public void autofill() {
//        Phone.writeLine("SELECT * FROM " + TableNames.MODELS + " WHERE ID = " + model_id);
//        ArrayList<Model> model = (ArrayList<Model>) Phone.readObject();
//        System.out.println(model);
//        if (!model.isEmpty()) {
//            this.model = model.get(0).getModel();
//            this.series = model.get(0).getSeries();
//        }
//
//        Phone.writeLine("SELECT * FROM " + TableNames.BODY_TYPE + " WHERE ID = " + body_type_id);
//        ArrayList<Body_type> body_type = (ArrayList<Body_type>) Phone.readObject();
//        System.out.println(body_type);
//        if (!body_type.isEmpty()) {
//            this.body_type = body_type.get(0).getBody_type();
//            this.seats = body_type.get(0).getSeats_num();
//        }
//        Phone.writeLine("SELECT * FROM " + TableNames.FUEL_TYPES + " WHERE ID = " + fuel_type_id);
//        ArrayList<Fuel_type> fuel = (ArrayList<Fuel_type>) Phone.readObject();
//        System.out.println(fuel);
//        if (!fuel.isEmpty()) {
//            this.fuel_type = fuel.get(0).getFuel_type();
//        }
//
//        Phone.writeLine("SELECT * FROM " + TableNames.EMPLOYEES + " WHERE ID = " + responsible_id);
//        ArrayList<Employees> empl = (ArrayList<Employees>) Phone.readObject();
//        System.out.println(empl);
//        if (!empl.isEmpty()) {
//            this.responsible = ((empl.get(0).getSurname() != null) ? empl.get(0).getSurname() : "")
//                    + " "
//                    + ((empl.get(0).getName() != null) ? empl.get(0).getName() : "");
//        }
//    }
//
//    public int getSeats() {
//        return seats;
//    }
//
//    public void setSeats(int seats) {
//        this.seats = seats;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public void setBody_type(String body_type) {
//        this.body_type = body_type;
//    }
//
//    public void setFuel_type(String fuel_type) {
//        this.fuel_type = fuel_type;
//    }
//
//    public void setResponsible(String responsible) {
//        this.responsible = responsible;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public String getBody_type() {
//        return body_type;
//    }
//
//    public String getFuel_type() {
//        return fuel_type;
//    }
//
//    public String getResponsible() {
//        return responsible;
//    }
//
//    @Override
//    public String toString() {
//        return "CarForTable{" +
//                "model='" + model + '\'' +
//                ", series='" + series + '\'' +
//                ", body_type='" + body_type + '\'' +
//                ", fuel_type='" + fuel_type + '\'' +
//                ", responsible='" + responsible + '\'' +
//                '}';
//    }
//}