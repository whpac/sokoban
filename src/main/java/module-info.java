module pl.whpac.sokoban {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.whpac.sokoban to javafx.fxml;
    exports pl.whpac.sokoban;
}