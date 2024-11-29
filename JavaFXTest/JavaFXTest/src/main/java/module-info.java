module fr.esiee.javafxtest {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.esiee.javafxtest to javafx.fxml;
    exports fr.esiee.javafxtest;
}