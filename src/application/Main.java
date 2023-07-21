package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
	
	private TextField tf=new TextField();
	private long num1=0;
	private long num2=0;
	private String op="";
	private boolean start=true;
	@Override
	public void start(Stage primaryStage) {
		
		this.tf.setFont(Font.font(20));
		this.tf.setPrefHeight(50);
		this.tf.setEditable(false);
		this.tf.setAlignment(Pos.CENTER_RIGHT);
		
		StackPane sp=new StackPane();
		sp.setPadding(new Insets(10));
		sp.getChildren().add(tf);
		
		
		TilePane tp=new TilePane();
		tp.setHgap(10);
		tp.setVgap(10);
		tp.setAlignment(Pos.TOP_CENTER);
		tp.getChildren().addAll(
				createButtonForNumber("7"),
				createButtonForNumber("8"),
				createButtonForNumber("9"),
				createButtonForOperator("/"),
				
				createButtonForNumber("4"),
				createButtonForNumber("5"),
				createButtonForNumber("6"),
				createButtonForOperator("X"),
				
				createButtonForNumber("1"),
				createButtonForNumber("2"),
				createButtonForNumber("3"),
				createButtonForOperator("-"),
				
				createButtonForNumber("0"),
				createButtonForClear("C"),
				createButtonForOperator("="),
				createButtonForOperator("+")
				
				);
		
		
		
		
		
		
		
		
		
		
		BorderPane root=new BorderPane();
		
		root.setTop(sp);
		root.setCenter(tp);
		Scene scene=new Scene(root,250,310);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Calculator");
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
		
		
		
		
		
	}
	private Button createButtonForNumber (String ch) {
		
		Button button=new Button(ch);
		button.setPrefSize(50, 50);
		button.setFont(Font.font(18));
		button.setOnAction(this::processNumber);
		
		
		return button;
	}
	
	private void processNumber(ActionEvent e) {
		if(start) {
			this.tf.setText("");
			this.start=false;
		}
		
		String value=((Button)e.getSource()).getText();
		this.tf.setText(this.tf.getText()+value);
		
		
		
	}
private void processOperator(ActionEvent e) {
	String value =((Button)e.getSource()).getText();
	if(!value.equals("=")) {
		if(!op.isEmpty()) {
			return;
		
		}
		this.num1=Long.parseLong(this.tf.getText());
		op=value;
		tf.setText("");
		
	}else {
		if(op.isEmpty()) {
			return;
		}
		
		this.num2=Long.parseLong(this.tf.getText());
		float calculate = calculate(num1,num2,op);
		this.tf.setText(String.valueOf(calculate));
		start=true;
		this.op="";
		
	}
		
	}
private float calculate(long num1,long num2, String operator) {
	switch(operator) {
	case "+":
		return num1+num2;
	case "-":
		return num1-num2;
	case "X":
		return num1*num2;
	case "/":
		if(num2==0) {
			return 0;
		}
	return num1/num2;
		
	default: return 0;
}
}
	
private Button createButtonForOperator (String ch) {
		
	Button button=new Button(ch);
	button.setPrefSize(50, 50);
	button.setFont(Font.font(18));
	button.setOnAction(this::processOperator);
	
		
		return button;
	}
private Button createButtonForClear (String ch) {
	
	Button button=new Button(ch);
	button.setPrefSize(50, 50);
	button.setFont(Font.font(18));
	button.setOnAction(e->{
		this.tf.setText("");
		this.op="";
		this.start=true;
	});
	
		
	
	
	return button;
}
	
	public static void main(String[] args) {
		launch(args);
	}
}
