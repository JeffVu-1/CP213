package cp213;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

/**
 * The GUI for the Order class.
 *
 * @author Jeff Vu
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-10-15
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {

        PrinterJob printerJob = PrinterJob.getPrinterJob();
        boolean userAcceptedPrint = printerJob.printDialog();
        
        if (userAcceptedPrint) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Printing canceled.");
        }
    }
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.z
     */
    private class QuantityListener implements FocusListener {
        private MenuItem item;
    
        QuantityListener(final MenuItem item) {
            this.item = item;
        }
    
        @Override
        public void focusGained(FocusEvent e) {
            return;
        }
    
        @Override
        public void focusLost(FocusEvent e) {

            JTextField source = (JTextField) e.getSource();
            String input = source.getText();

            try {
                int quantity = Integer.parseInt(input);

                if (quantity < 0) {
                    quantity = 0; 
                } 
            
                order.update(item, quantity);

                BigDecimal subtotal = order.getSubTotal();
                subtotalLabel.setText(subtotal.toString());

                BigDecimal tax = order.getTaxes();
                BigDecimal roundedTax = tax.setScale(2, RoundingMode.HALF_UP);
                taxLabel.setText(roundedTax.toString());
        
                BigDecimal total = order.getTotal();
                BigDecimal totalRonded = total.setScale(2, RoundingMode.HALF_UP);
                totalLabel.setText(totalRonded.toString());

            } catch (NumberFormatException ex) {
                order.update(item, 0);
            }
        }
    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {

        setLayout(new GridLayout(this.menu.size() + 1, 3));

        for (int i = 0; i < this.menu.size(); ++i) {
            MenuItem SelectedItem = menu.getItem(i);
            nameLabels[i] = new JLabel(SelectedItem.getListing());  
            priceLabels[i] = new JLabel(priceFormat.format(SelectedItem.getPrice())); 
            quantityFields[i] = new JTextField("0");
            add(nameLabels[i]);  
            add(priceLabels[i]);  
            add(quantityFields[i]); 
        }

        JPanel totalsPanel = new JPanel();
        totalsPanel.setLayout(new GridLayout(3, 3)); 
        totalsPanel.add(new JLabel("Subtotal:"));
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(new JLabel("Tax:"));
        totalsPanel.add(taxLabel);
        totalsPanel.add(new JLabel("Total:"));
        totalsPanel.add(totalLabel);

        add(totalsPanel);
        add(printButton);
    }

    /**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
        this.printButton.addActionListener(new PrintListener());

        for (int i = 0; i < menu.size(); i++) {
            quantityFields[i].addFocusListener(new QuantityListener(menu.getItem(i)));
        }
    }
}