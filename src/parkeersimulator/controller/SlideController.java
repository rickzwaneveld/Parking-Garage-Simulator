package parkeersimulator.controller;

import java.awt.GridLayout;


import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import parkeersimulator.Location;
import parkeersimulator.model.Model;

@SuppressWarnings("serial")
public class SlideController extends AbstractController{

	static final int minTick = 1;
	static final int maxTick = 999;
	static final int InitTick = 100;
	
	private JSlider tickRate;
	
    public SlideController(Model model) {
    	super(model);
    	

    	tickRate = new JSlider(JSlider.HORIZONTAL, minTick, maxTick,  InitTick);
    	tickRate.addChangeListener(e -> {
    		model.setTickPause(tickRate.getValue());
    	});
    	
        setLayout(new GridLayout(2, 1));
        add(new JLabel("TickPause", SwingConstants.CENTER));
        add(tickRate);
    }



public void clickedSpot(Location location) {
	// TODO Auto-generated method stub
	
}

}