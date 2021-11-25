package by.bsuir.app.services.reportFactory;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.entity.Position;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static by.bsuir.app.util.constants.Constants.*;

public class ReportTXT implements Report{
    @Override
    public void createReport() throws IOException, GettingDataException, ClassNotFoundException {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());


        PrintWriter out = new PrintWriter(file);
        out.print("\t\t\t\t\t"+ REPORT_ACCOUNT_DATA_MSG);
        out.print("\n\n");
        out.print("\t\t\t\t\t\t\t\t" + AUTHOR_MSG + LocalStorage.getAccount().getLogin() +"\n\n\n");

        List<Account> accounts = (List<Account>) Phone.sendOrGetData(Commands.GET_ALL_USERS, new Account());

        for (int j = 0; j < accounts.size(); j++) {

            String login = accounts.get(j).getLogin();
            String email = accounts.get(j).getEmail();
            if (email == null)
                email = DATA_MISSED_MSG;
            String role = accounts.get(j).getRole();
            PersonalData personalData = accounts.get(j).getData();

            String name = null;
            String surname = null;
            String thirdName = null;
            String positionName = null;
            String salary = null;
            if (personalData != null) {
                name = personalData.getName();
                if (name == null)
                    name = DATA_MISSED_MSG;
                surname = personalData.getSurname();
                if (surname == null)
                    surname = DATA_MISSED_MSG;
                thirdName = personalData.getThirdName();
                if (thirdName == null)
                    thirdName = DATA_MISSED_MSG;
                Position position = personalData.getPosition();
                if (position != null) {
                    positionName = position.getName();
                    salary = String.valueOf(position.getSalary());
                }
            }

           out.println(
                    "Name: " + name +
                            "\nSurname: " + surname +
                            "\nThird_Name: " + thirdName +
                            "\nPosition: " + positionName +
                            "\nSalary: " + salary +
//                            "\nLogin: " + login +
                            "\nMail: " + email +
//                            "\nRole: " + role +
                            "\n\n\n");
        }
        out.close();
    }
}
