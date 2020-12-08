package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

/**
 *
 */
public class ToolsQaPracticeFormTests {

    @BeforeAll
    static  void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void regForm() {
        String firstName = "Privet",
                lastName = "Kakdela",
                userEmail = "privetkakdela@yahoo.com",
                userGender = "Female",
                userNumber = "0987654321",
                dateOfBirthMonth = "February",
                dateOfBirthYear = "1950",
                dateOfBirthDay = "19",
                address = "City, street, building, floor, 999",
                subject1 = "Chemistry",
                subject2 = "English",
                hobbie1 = "Sports",
                hobbie2 = "Reading",
                state = "Uttar Pradesh",city = "Lucknow";
        File file = new File("src/test/resources/pic2.jpg");
        String filename = file.getName();
        open("https://demoqa.com/automation-practice-form");
// Filling out the form
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#userNumber").val(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(dateOfBirthMonth);
        $(".react-datepicker__year-select").selectOption(dateOfBirthYear);
        $(".react-datepicker__month").$(byText(dateOfBirthDay)).click();
        $("#subjectsInput").val("m");
        $(".subjects-auto-complete__menu-list").$(byText(subject1)).click();
        $("#subjectsInput").val("e");
        $(".subjects-auto-complete__menu-list").$(byText(subject2)).click();
        $("#hobbiesWrapper").$(byText(hobbie1)).click();
        $("#hobbiesWrapper").$(byText(hobbie2)).click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").val(address);
        $("#stateCity-wrapper").$("#state").scrollIntoView(true).click();
        $("#stateCity-wrapper").$("#state").$(byText(state)).click();
        $("#stateCity-wrapper").$("#city").click();
        $("#stateCity-wrapper").$("#city").$(byText(city)).click();
        $("#submit").click();
// Checking that the result form contains all values that were filled.
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[1]").shouldHave(text("Student Name"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[1]/td[2]").shouldHave(text(firstName + " " + lastName));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[1]").shouldHave(text("Student Email"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[2]/td[2]").shouldHave(text(userEmail));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[1]").shouldHave(text("Gender"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[3]/td[2]").shouldHave(text(userGender));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[1]").shouldHave(text("Mobile"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[4]/td[2]").shouldHave(text(userNumber));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[1]").shouldHave(text("Date of Birth"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[5]/td[2]").shouldHave(text(dateOfBirthDay + " " + dateOfBirthMonth +"," + dateOfBirthYear));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[1]").shouldHave(text("Subjects"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[6]/td[2]").shouldHave(text(subject1 + ", " + subject2));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[1]").shouldHave(text("Hobbies"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[7]/td[2]").shouldHave(text(hobbie1 + ", " + hobbie2));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[1]").shouldHave(text("Picture"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[8]/td[2]").shouldHave(text(filename));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[1]").shouldHave(text("Address"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[9]/td[2]").shouldHave(text(address));

        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[1]").shouldHave(text("State and City"));
        $x("/html/body/div[3]/div/div/div[2]/div/table/tbody/tr[10]/td[2]").shouldHave(text(state + " " + city));
    }
}
