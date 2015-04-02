import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * MonthBody is a JPanel that contains the components to display the days of the
 * month as well as the heading to indicate the day of the week. Sunday is the
 * designated first day of the week.
 * 
 * Documentation and comments are added as much as possible for the benefit of
 * AICSC members.
 * 
 * @author Peter Nguyen
 */
@SuppressWarnings("serial")
class MonthBody extends JPanel {
	// Constants to help with construction of the JPanel
	public static final String[] DAYS_OF_WEEK = { "Sun", "Mon", "Tue", "Wed",
			"Thu", "Fri", "Sat" };

	// 6 rows because a month can can need 4 to 6 rows to display the days
	private final static int MAX_WEEK_ROWS = 6;

	public static final int DAYS_IN_WEEK = 7;

	// JLabel array to display the headings for days of the week
	private static final JLabel[] JL_DAYS_IN_WEEK = new JLabel[DAYS_IN_WEEK];

	// Modified JPanels stored on a 2D array for easy access to specific panel
	private static final DaySlot[][] DAY_SLOTS = new DaySlot[MAX_WEEK_ROWS][DAYS_IN_WEEK];

	// Date
	private int dayOfMonth;
	private int firstDayOfFirstWeek;
	private int lastDayOfMonth;

	// Calendar
	private Calendar calendar;

	// Padding to make empty cells (otherwise day 1 will always be on Sunday)
	private int paddingStart;

	// Border to indicate the current day of the current month
	private Border border;

	/**
	 * MonthBody(Calendar calendar) is the constructor used to instantiate and
	 * add the child components and initially fill the values of the labels
	 * 
	 * @param calendar
	 *            Calendar to display
	 */
	public MonthBody(Calendar calendar) {
		// Store a local reference to the calendar
		this.calendar = calendar;

		// Invoker helper methods
		buildMonth();
		fillMonth();
	}

	/**
	 * update(int month, int year) is a method that updates the calendar view
	 * according to the specified month and year.
	 * 
	 * @param month
	 *            integer value of the selected month
	 * @param year
	 *            integer value of the selected year
	 */
	public void update(int month, int year) {
		/*
		 * Set the date to the first day of the specified month and year.
		 */
		calendar.set(year, month, 1, 0, 0, 0);

		fillMonth();

		/*
		 * Print debugging. I apologize for the messy automatic formatting
		 */
		System.out.println(calendar.getTime() + " <--Selected Time");
		System.out
				.println(Main.calendarCurrent.getTime() + " <---Current Time");
		System.out.println("*********************************************");
	}

	/**
	 * buildMonth() is a method invoked only once to create the containers and
	 * visual components.
	 */
	private void buildMonth() {
		// Border used to indicate current day of the current month
		border = BorderFactory.createLineBorder(CalendarColors.COLOR_1);

		/*
		 * GridLayout lays out components on a 2D grid, 0 forces the layout to
		 * fill each column (7 total) before incrementing a row when adding
		 * components
		 */
		setLayout(new GridLayout(0, DAYS_IN_WEEK));

		setBackground(CalendarColors.COLOR_3);

		/*
		 * Instantiate and add JLabels that display the day of the week
		 */
		for (int i = 0; i < DAYS_IN_WEEK; i++) {
			JL_DAYS_IN_WEEK[i] = new JLabel(DAYS_OF_WEEK[i],
					SwingConstants.CENTER);
			add(JL_DAYS_IN_WEEK[i]);
		}

		/*
		 * Instantiate and add each DaySlot to the grid
		 */
		for (int i = 0; i < MAX_WEEK_ROWS; i++) {
			for (int j = 0; j < DAYS_IN_WEEK; j++) {
				DAY_SLOTS[i][j] = new DaySlot();
				DAY_SLOTS[i][j].setBackground(CalendarColors.COLOR_4);
				add(DAY_SLOTS[i][j]);
			}
		}
	}

	/**
	 * fillMonth() is a helper method used to display the days of the month by
	 * updating the JLabels inside each DaySlot.
	 */
	private void fillMonth() {
		// Day of month is set to 1 so I can add padding
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		/*
		 * By setting day of the month to 1, I can get the first day of the week
		 * (sunday is 1, and saturday is 7). Subtract by 1 to get the
		 * appropriate index value.
		 */
		firstDayOfFirstWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		/*
		 * Padding is required so that we can add them before days are added,
		 * otherwise day 1 will always be on a Sunday
		 */
		paddingStart = firstDayOfFirstWeek;

		dayOfMonth = Main.calendarCurrent.get(Calendar.DAY_OF_MONTH);
		lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		// Integers to traverse rows and collumns
		int row = 0;
		int col = 0;

		// Integer to increment day of the month
		int day = 1;

		// Begin by adding padding to the grid
		for (col = 0; col < paddingStart; col++) {
			// Remove all borders between this range
			DAY_SLOTS[row][col].setBorder(null);
			// Clear the JLabel contained in DAY_SLOTS[row][col]
			DAY_SLOTS[row][col].setDay("");
		}

		/*
		 * Values used to check if the calendar view is the current month and
		 * year
		 */
		int currentMonth = Main.calendarCurrent.get(Calendar.MONTH);
		int viewedMonth = calendar.get(Calendar.MONTH);
		int currentYear = Main.calendarCurrent.get(Calendar.YEAR);
		int viewedYear = calendar.get(Calendar.YEAR);

		boolean isCurrent = false;

		/*
		 * If these values are equal, then that calendar view is the current
		 * month and year
		 */
		if (currentMonth == viewedMonth && currentYear == viewedYear) {
			isCurrent = true;
		}

		col = paddingStart;
		while (day <= lastDayOfMonth) {
			/*
			 * Clear borders and add the day to the JLabel in
			 * DAY_SLOTS[row][col]
			 */
			DAY_SLOTS[row][col].setBorder(null);
			DAY_SLOTS[row][col].setDay("" + day);

			/*
			 * If it is the current month, and the incremented day is correct,
			 * // add border to indicate that the day is current
			 */
			if (isCurrent && day == dayOfMonth) {
				DAY_SLOTS[row][col].setBorder(border);
			}

			/*
			 * If column has reached the end, reset to 0 and move to next row.
			 * If not, move to the next column
			 */
			if (col == 6) {
				row++;
				col = 0;
			} else {
				col++;
			}
			day++;
		}

		/*
		 * Clear the remaining unused DaySlots by removing border and assigning
		 * an empty string
		 */
		for (; row < MAX_WEEK_ROWS; row++) {
			for (; col < DAYS_IN_WEEK; col++) {
				DAY_SLOTS[row][col].setBorder(null);
				DAY_SLOTS[row][col].setDay("");
			}
			col = 0;
		}
	}
}