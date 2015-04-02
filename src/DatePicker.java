import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * DatePicker is a JPanel that allows the user to specify a month and year
 * between 1600 and 9999.
 * 
 * @author Peter Nguyen
 */
@SuppressWarnings("serial")
public class DatePicker extends JPanel {
	// String Array of months
	public static final String[] months = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	// The drop-down box that provides a list of months
	private JComboBox<String> jcbMonths;
	// JTextField to receive the user specified year
	private JTextField jtfYear;

	/**
	 * DatePicker() default constructor
	 */
	public DatePicker() {
		// Layout: 2 rows
		setLayout((new GridLayout(2, 0)));

		// This is how you instantiate your JComboBox
		jcbMonths = new JComboBox<String>(months);
		// Specify how many options to show at the same time
		jcbMonths.setMaximumRowCount(12);

		// The specifies the length of the JTextField
		jtfYear = new JTextField(5);

		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();

		// Add the components to row1
		row1.add(new JLabel("Month: ", SwingConstants.RIGHT));
		row1.add(jcbMonths);

		// Add the components to row2
		row2.add(new JLabel("Year: ", SwingConstants.RIGHT));
		row2.add(jtfYear);

		// Add the rows to the GridLayout JPanel
		add(row1);
		add(row2);

		// Attempt to keep the DatePicker panel small
		setSize(new Dimension(60, 60));
		setPreferredSize(new Dimension(60, 60));
	}

	/**
	 * @return integer value for the year
	 */
	public int getYear() {
		int year;
		try {
			// This try block attempts to convert the String to an integer
			year = Integer.valueOf(jtfYear.getText());
		} catch (NumberFormatException ex) {
			// If try block fails, catch the exception and specify the default
			// year.
			year = 0;
		}
		return year;
	}

	/**
	 * @return integer value for the month
	 */
	public int getMonth() {
		// getSelectedIndex() returns the index of the selected option
		return jcbMonths.getSelectedIndex();
	}
}
