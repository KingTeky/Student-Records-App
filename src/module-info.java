module Student {
	requires javafx.controls;
	
	opens application to javafx.base, javafx.graphics, javafx.fxml;
}
