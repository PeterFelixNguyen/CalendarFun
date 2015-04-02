import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * MonthTitle is a JPanel that is used to store the title of the selected month
 * view with the format: MMMMM YYYY.
 * 
 * Documentation and comments are added as much as possible for the benefit of
 * AICSC members.
 * 
 * @author Peter Nguyen
 */
@SuppressWarnings("serial")
public class MonthTitle extends JPanel {
	private JLabel jlMonth;
	private SimpleDateFormat format;
	private Calendar calendar;

	/**
	 * MonthTitle() is the constructor used to set the initial state and view of
	 * the JPanel.
	 * 
	 * @param calendar
	 *            Calendar to use when setting the label Month and Year
	 */
	public MonthTitle(Calendar calendar) {
		// Store a local reference to the calendar
		this.calendar = calendar;

		setBackground(CalendarColors.COLOR_1);

		// Specify the desired date format
		format = new SimpleDateFormat("MMMMM yyyy");

		// Acquire initial format for a String of the calendar
		String title = format.format(Main.calendarCurrent.getTime());

		// Set the JLabel to initially display the current month and year
		jlMonth = new JLabel("<html><b><font size='5' color='white'>" + title
				+ "</b></font></html>");

		// Add the JLabel to this JPanel
		add(jlMonth);
	}

	/**
	 * Updates the JLabel that displays the Month and Year.
	 * 
	 * @param month
	 *            integer value of month to set
	 * @param year
	 *            integer value of year to set
	 */
	public void update(int month, int year) {
		// Set the calendar to the beginning of the specified month and year
		calendar.set(year, month, 1, 0, 0, 0);

		// Acquire a formatted String of the calendar
		String title = format.format(calendar.getTime());

		// Update the JLabel to display the new month and year
		jlMonth.setText("<html><b><font size='5' color='white'>" + title
				+ "</b></font></html>");
	}
}