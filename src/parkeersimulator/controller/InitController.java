
package parkeersimulator.controller;


import javax.swing.*;

import parkeersimulator.model.Model;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class InitController extends AbstractController implements ActionListener {

	private JLabel multiplier;
	private JLabel maxRes, maxAbo;
	private JLabel perInv, aboTa;
	private JLabel norTa, resTa;

	private JLabel label;
	private JLabel labelFiller;

	private JFormattedTextField multiplierAmount;
	private JFormattedTextField aantalReserveringen;
    private JFormattedTextField aantalAbonnees;
    private JFormattedTextField percentageInvalidenplekken;
    private JFormattedTextField abonneeTarief;
    private JFormattedTextField normaalTarief;
    private JFormattedTextField reserveringsTarief;

    private JLabel lblWeek, lblDag, lblUur;
    private JFormattedTextField resWeek;
    private JComboBox<String> resDag;
    private JComboBox<Integer> resUur;

    private JButton initButton;
    private JButton resetValues;
    
    private double defaultMultiplier = model.getMultiplier() * 100;
    private int defaultReservering = model.getReservering();
    private int defaultAbonnees = model.getAbonnees();
    private int defaultAboneeTarief = model.getAbonneeTarief();
    private int defaultNormaalTarief = model.getNormaalTarief();
    private int defaultReserveringTarief = model.getReserveringTarief();
    private int defaultHandicapPercentage = model.getHandicapPercentage();
    
    

    private void initFocusListener(JFormattedTextField textField)
    {
    	textField.addFocusListener(new java.awt.event.FocusAdapter() {
    		public void focusGained(java.awt.event.FocusEvent evt) {
    			SwingUtilities.invokeLater(new Runnable() {
    				@Override
    				public void run() {
    					textField.selectAll();
    				}
    			});
    		}
    	});
    }

    private void setDefaultValues() {

        model.setMultiplier(defaultMultiplier);
        model.setReservering(defaultReservering);
        model.setAbonnees(defaultAbonnees);
        model.setAbonneeTarief(defaultAboneeTarief);
        model.setNormaalTarief(defaultNormaalTarief);
        model.setReserveringTarief(defaultReserveringTarief);
        model.setHandicapPercentage(defaultHandicapPercentage);

        updateTextFieldValues();
    }

    private void updateTextFieldValues() {
    	multiplierAmount.setValue(model.getMultiplier() * 100);
    	aantalReserveringen.setValue(model.getReservering());
    	aantalAbonnees.setValue(model.getAbonnees());
    	percentageInvalidenplekken.setValue(model.getHandicapPercentage());
    	abonneeTarief.setValue(model.getAbonneeTarief());
    	normaalTarief.setValue(model.getNormaalTarief());   
    	reserveringsTarief.setValue(model.getReserveringTarief());
    }

    public InitController(Model model) {

    	super(model);

    	multiplierAmount = new JFormattedTextField();
    	aantalReserveringen = new JFormattedTextField();
    	aantalAbonnees = new JFormattedTextField();
    	percentageInvalidenplekken = new JFormattedTextField();
    	abonneeTarief = new JFormattedTextField();
    	normaalTarief = new JFormattedTextField();   
    	reserveringsTarief = new JFormattedTextField();

        lblWeek = new JLabel("Reservering Week");
        lblDag = new JLabel("Reservering Dag");
        lblUur = new JLabel("Reservering Uur");

        resWeek = new JFormattedTextField("0");
        String[] dagStrings = { "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag" };
        resDag = new JComboBox<String>(dagStrings);
        Integer[] uurInts = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
        resUur = new JComboBox<Integer>(uurInts);

    	initFocusListener(multiplierAmount);
    	initFocusListener(aantalReserveringen);
    	initFocusListener(aantalAbonnees);
    	initFocusListener(percentageInvalidenplekken);
    	initFocusListener(abonneeTarief);
    	initFocusListener(normaalTarief);
    	initFocusListener(reserveringsTarief);



    	initButton = new JButton("Verstuur");
    	initButton.addActionListener(this);

    	resetValues = new JButton("Reset Waarden");
    	resetValues.addActionListener(this);

    	multiplier = new JLabel("Vermenigvuldiger (%)");
 		maxRes = new JLabel("Reserveringen");
 		maxAbo = new JLabel("Abonnees");
 		perInv = new JLabel("Invaliden (%)");
 		aboTa = new JLabel("Abonnee Tarief");
 		norTa = new JLabel("Normaal Tarief");
 		resTa = new JLabel("Reservering Tarief");

    	BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);

		label = new JLabel("Waarden");
		labelFiller = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(label);
		add(labelFiller);
		
		

		GridLayout gridLayout = new GridLayout(0,2);
		setLayout(gridLayout);

		add(multiplier); add(multiplierAmount);
		add(maxRes); add(aantalReserveringen);
		add(maxAbo); add(aantalAbonnees);
		add(perInv); add(percentageInvalidenplekken);
		add(aboTa); add(abonneeTarief);
		add(norTa); add(normaalTarief);
		add(resTa); add(reserveringsTarief);
		add(lblWeek); add(resWeek);
		add(lblDag); add(resDag);
		add(lblUur); add(resUur);
		add(initButton); add(resetValues);
    	setDefaultValues();

    	setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
		try {
			Object sourceObject = e.getSource();
			if (sourceObject == initButton) {
				model.playSound("button.wav");
				
				model.setMultiplier(getMultiplier());
				model.setReservering(getReservering());
				model.setAbonnees(getAbonee());
				model.setHandicapPercentage(getHandiPer());
				model.setAbonneeTarief(getAboneeTarief());
				model.setNormaalTarief(getNormaalTarief());
				model.setReserveringTarief(getReserveringTarief());
				
				int dag = resDag.getSelectedIndex();
				int uur = resUur.getSelectedIndex();
				model.setSpecialReservering(getResWeek(), dag, uur);
			} else if (sourceObject == resetValues) {
				model.playSound("button.wav");
				
				setDefaultValues();
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private double getMultiplier() throws NumberFormatException {
		return Double.parseDouble(multiplierAmount.getText());
	}

	private int getReservering() throws NumberFormatException {
		return Integer.parseInt(aantalReserveringen.getText());
	}

	private int getAbonee() throws NumberFormatException {
		return Integer.parseInt(aantalAbonnees.getText());
	}

	private int getHandiPer() throws NumberFormatException {
		return Integer.parseInt(percentageInvalidenplekken.getText());
	}

	private int getAboneeTarief() throws NumberFormatException {
		return Integer.parseInt(abonneeTarief.getText());
	}

	private int getNormaalTarief() throws NumberFormatException {
		return Integer.parseInt(normaalTarief.getText());
	}

	private int getReserveringTarief() throws NumberFormatException {
		return Integer.parseInt(reserveringsTarief.getText());
	}

	private int getResWeek() throws NumberFormatException {
		return Integer.parseInt(resWeek.getText());
	}

	/**
	private void enableOrDisable(boolean value) {
		multiplier.setEnabled(value);
		aantalReserveringen.setEnabled(value);
		aantalAbonnees.setEnabled(value);
		percentageInvalidenplekken.setEnabled(value);
		abonneeTarief.setEnabled(value);
		normaalTarief.setEnabled(value);
		reserveringsTarief.setEnabled(value);
	    //initButton.setEnabled(value);

	    resWeek.setEnabled(value);;
	    resDag.setEnabled(value);;
	    resUur.setEnabled(value);;
	}
	

	public void simStarted() {
		enableOrDisable(false);
	}

	public void simStopped() {
		enableOrDisable(true);
	}
	*/


}
