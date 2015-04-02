/**
 * This is a simple Java program completed as a coding challenge 
 * from the Artificial Intelligence Computer Science Club at 
 * Bakersfield College Spring 2015.
 * 
 * https://github.com/AICSC/Coding-Challenges/issues/11
 * 
 * This program displays a calendar and initializes the current view
 * to the current month of the year on the computer's local time. 
 * It has a previous and next button to switch month views to see
 * the calendars of each month.
 * 
 * If I have time I will add functionality to specify a month and year
 * as opposed to just providing buttons to navigate the calendar.
 * 
 * Documentation and comments are added as much as possible for
 * the benefit of AICSC members.
 * 
 * @author Peter Nguyen
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {
	// Final calendar instance for the current time and date
	public static final Calendar calendarCurrent = Calendar.getInstance();
	// Values used to switch calendar view
	private static int month;
	private static int year;

	public static void main(String[] args) {
		/* ActionListener to perform actions for this calendar's Timer */
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// The current calendar will be updated to the local time every
				// tick
				calendarCurrent.setTime(new Date());
			}
		};

		/*
		 * Instantiate a Timer that ticks every 1 second and performs the
		 * specified action
		 */
		Timer timer = new Timer(1000, listener);

		// Calendar instance for the selected calendar view
		Calendar calendarViewed = Calendar.getInstance();

		// Make calenar's time equal to calendarToday
		calendarViewed.setTime(calendarCurrent.getTime());

		// Initialize month and year to the current Calendar's values
		month = calendarViewed.get(Calendar.MONTH);
		year = calendarViewed.get(Calendar.YEAR);

		// Set up JFrame to add all children components
		JFrame frame = new JFrame();
		frame.setTitle("Simple Calendar by Peter Nguyen");
		frame.setSize(450, 300);

		/*
		 * BorderLayout allows us to arrange components based on the relative
		 * proximity of components
		 */
		frame.setLayout(new BorderLayout());

		/*
		 * We don't want the JFrame to resize because the calendar would look
		 * ugly. It was not designed to be scaled and dynamically displayed.
		 */
		frame.setResizable(false);

		/*
		 * This method ensures that the X button would terminate the program and
		 * prevent it from running hidden processes in the background.
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * This method centers the frame on the screen
		 */
		frame.setLocationRelativeTo(null);

		// Create buttons to switch view of the month
		JButton jbNextMonth = new JButton("Next");
		JButton jbPrevMonth = new JButton("Prev");

		// Instantiate and add components to the frame
		JPanel upperPanel = new JPanel(new BorderLayout());
		final MonthTitle monthTitle = new MonthTitle(calendarViewed);
		upperPanel.add(jbPrevMonth, BorderLayout.WEST);
		upperPanel.add(monthTitle, BorderLayout.CENTER);
		upperPanel.add(jbNextMonth, BorderLayout.EAST);

		// Add the upper components to the northern region of the frame
		frame.add(upperPanel, BorderLayout.NORTH);

		// Instantiate MonthBody which stores the days of the month
		final MonthBody monthBody = new MonthBody(calendarViewed);

		// Add the MonthBody to the frame (positioned at the center)
		frame.add(monthBody, BorderLayout.CENTER);

		// Add functionality to buttons
		jbNextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * To the Calendar class, January is 0 and December is 11. The
				 * next button should increment month. If month is 11, do not
				 * increment it to 12. Instead, reset it to 0 to restart the
				 * year
				 */
				if (month == 11) {
					month = 0;
					year++;
				} else {
					month++;
				}
				monthBody.update(month, year);
				monthTitle.update(month, year);
			}
		});

		jbPrevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * Similar to jbNextMonth, except decrements the month
				 */
				if (month == 0) {
					month = 11;
					year--;
				} else {
					month--;
				}
				monthBody.update(month, year);
				monthTitle.update(month, year);
			}
		});

		// Start the timer so that the current calendarCurrent is updated every
		// second
		timer.start();

		// Make frame visible, this should always be last
		frame.setVisible(true);
	}
}
