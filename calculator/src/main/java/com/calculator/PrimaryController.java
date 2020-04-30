/**
 * Sample Skeleton for 'primary.fxml' Controller Class
 */

package com.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

	public static String arithmetic = "";

	@FXML
	private TextField Text;

	@FXML // fx:id="Btn4"
	private Button Btn4; // Value injected by FXMLLoader

	@FXML // fx:id="Btn5"
	private Button Btn5; // Value injected by FXMLLoader

	@FXML // fx:id="Btn7"
	private Button Btn7; // Value injected by FXMLLoader

	@FXML // fx:id="Btn8"
	private Button Btn8; // Value injected by FXMLLoader

	@FXML // fx:id="Btn9"
	private Button Btn9; // Value injected by FXMLLoader

	@FXML // fx:id="Btn1"
	private Button Btn1; // Value injected by FXMLLoader

	@FXML // fx:id="Btn2"
	private Button Btn2; // Value injected by FXMLLoader

	@FXML // fx:id="Btn3"
	private Button Btn3; // Value injected by FXMLLoader

	@FXML // fx:id="Btn6"
	private Button Btn6; // Value injected by FXMLLoader

	@FXML // fx:id="BtnPlus"
	private Button BtnPlus; // Value injected by FXMLLoader

	@FXML // fx:id="BtnEqu"
	private Button BtnEqu; // Value injected by FXMLLoader

	@FXML // fx:id="BtnC"
	private Button BtnC; // Value injected by FXMLLoader

	@FXML // fx:id="BtnDel"
	private Button BtnDel; // Value injected by FXMLLoader

	@FXML // fx:id="BtnOpen"
	private Button BtnOpen; // Value injected by FXMLLoader

	@FXML // fx:id="BtnClose"
	private Button BtnClose; // Value injected by FXMLLoader

	@FXML // fx:id="BtnMul"
	private Button BtnMul; // Value injected by FXMLLoader

	@FXML // fx:id="BtnSub"
	private Button BtnSub; // Value injected by FXMLLoader

	@FXML // fx:id="BtnDiv"
	private Button BtnDiv; // Value injected by FXMLLoader

	@FXML // fx:id="Btn0"
	private Button Btn0; // Value injected by FXMLLoader

	@FXML // fx:id="BtnDot"
	private Button BtnDot; // Value injected by FXMLLoader

	@FXML
	void calculate(ActionEvent event) {
		if(event.getSource() == Btn0)
		{
			arithmetic+="0";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn1)
		{
			arithmetic+="1";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn2)
		{
			arithmetic+="2";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn3)
		{
			arithmetic+="3";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn4)
		{
			arithmetic+="4";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn5)
		{
			arithmetic+="5";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn6)
		{
			arithmetic+="6";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn7)
		{
			arithmetic+="7";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn8)
		{
			arithmetic+="8";
			Text.setText(arithmetic);
		}
		if(event.getSource() == Btn9)
		{
			arithmetic+="9";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnPlus)
		{
			arithmetic+="+";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnSub)
		{
			arithmetic+="-";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnMul)
		{
			arithmetic+="*";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnDiv)
		{
			arithmetic+="/";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnDot)
		{
			arithmetic+=".";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnOpen)
		{
			arithmetic+="(";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnClose)
		{
			arithmetic+=")";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnC)
		{
			arithmetic="";
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnDel)
		{
			if(arithmetic.length() > 0)
				arithmetic = arithmetic.substring(0, arithmetic.length()-1);
			Text.setText(arithmetic);
		}
		if(event.getSource() == BtnEqu)
		{
			arithmetic = FinalCalc.OnEquPress(arithmetic);
			Text.setText(arithmetic);
			arithmetic = "";
		}
	}

}
