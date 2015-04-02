import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * DaySlot is a special JPanel that stores a modifiable JLabel that is to be
 * stored in MonthBody.
 * 
 * Documentation and comments are added as much as possible for the benefit of
 * AICSC members.
 * 
 * @author Peter Nguyen
 */
@SuppressWarnings("serial")
public class DaySlot extends JPanel {
	private JLabel label;

	/**
	 * DaySlot() is the constructor to initialize and add the JLabel to the
	 * JPanel.
	 */
	public DaySlot() {
		label = new JLabel();
		add(label);
	}

	/**
	 * Set the day value as the label (should be 1 to 31).
	 * 
	 * @param day
	 *            String representation of day of the month
	 */
	public void setDay(String day) {
		label.setText(day);
	}
}
