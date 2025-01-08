module fr.esiee.easytrainfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens fr.esiee.easytrainfx to javafx.fxml;
    exports fr.esiee.easytrainfx;
}