package stevelevy;

import java.text.SimpleDateFormat;
import java.text.ParseException;


public class DateValidatorUsingDateFormat implements DateValidator {
	private String dateFormat;

	public DateValidatorUsingDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public boolean isValid(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}