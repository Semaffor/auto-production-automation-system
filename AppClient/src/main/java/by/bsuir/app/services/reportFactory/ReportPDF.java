package by.bsuir.app.services.reportFactory;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.PersonalData;
import by.bsuir.app.entity.Position;
import by.bsuir.app.exception.GettingDataException;
import by.bsuir.app.util.Commands;
import by.bsuir.app.util.connection.Phone;
import by.bsuir.app.util.constants.LocalStorage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static by.bsuir.app.util.constants.Constants.*;

@Log4j2
public class ReportPDF implements Report {
    @Override
    public void createReport() throws IOException, GettingDataException, ClassNotFoundException, DocumentException {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showSaveDialog(new Stage());

        List<Account> accounts = (List<Account>) Phone.sendOrGetData(Commands.GET_ALL_USERS, new Account());
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();
        String author = LocalStorage.getAccount().getLogin();
        document.addAuthor(AUTHOR_MSG + author);
        document.addTitle(REPORT_TITLE_MSG);
        document.add(new Paragraph(REPORT_ACCOUNT_DATA_MSG + "                                         " +
                "                             " +
                AUTHOR_MSG + author + "\n\n"));

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

            Paragraph p = new Paragraph(
                    "Name: " + name +
                            "\nSurname: " + surname +
                            "\nThird_Name: " + thirdName +
                            "\nPosition: " + positionName +
                            "\nSalary: " + salary +
//                            "\nLogin: " + login +
                            "\nMail: " + email +
//                            "\nRole: " + role +
                            "\n\n\n");
            document.add(p);
        }
        document.close();
    }
}
