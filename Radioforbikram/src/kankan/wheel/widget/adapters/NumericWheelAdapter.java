/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package kankan.wheel.widget.adapters;

import android.content.Context;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter extends AbstractWheelTextAdapter {

	/** The default min value */
	public static final int DEFAULT_MAX_VALUE = 45;

	/** The default max value */
	private static final int DEFAULT_MIN_VALUE = 0;

	// Values
	private int minValue;
	private int maxValue;

	// format
	private String format;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the current context
	 */
	public NumericWheelAdapter(Context context) {
		this(context, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the current context
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 */
	public NumericWheelAdapter(Context context, int minValue, int maxValue) {
		this(context, minValue, maxValue, null);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the current context
	 * @param minValue
	 *            the wheel min value
	 * @param maxValue
	 *            the wheel max value
	 * @param format
	 *            the format string
	 */
	public NumericWheelAdapter(Context context, int minValue, int maxValue,
			String format) {
		super(context);

		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
	}

	@Override
	public CharSequence getItemText(int index) {
		if (index >= 0 && index < getItemsCount()) {
			int value = minValue + index;
			if (value == 6)
				value = 0;
			else if (value == 7)
				value = 1;
			else if (value == 8)
				value = 0;
			else if (value == 9)
				value = 3;
			else if (value == 10)
				value = 2;

			else if (value == 11)
				value = 0;
			else if (value == 12)
				value = 3;
			else if (value == 13)
				value = 0;
			else if (value == 14)
				value = 4;
			else if (value == 15)
				value = 1;
			else if (value == 16)
				value = 0;
			else if (value == 17)
				value = 2;
			else if (value == 18)
				value = 0;
			else if (value == 19)
				value = 4;
			else if (value == 20)
				value = 1;
			else if (value == 21)
				value = 0;
			else if (value == 22)
				value = 2;
			else if (value == 23)
				value = 0;
			else if (value == 24)
				value = 2;
			else if (value == 25)
				value = 1;
			else if (value == 26)
				value = 0;
			else if (value == 27)
				value = 2;
			else if (value == 28)
				value = 0;
			else if (value == 29)
				value = 4;
			else if (value == 20)
				value = 6;
			else if (value == 31)
				value = 0;
			else if (value == 32)
				value = 3;
			else if (value == 33)
				value = 0;
			else if (value == 34)
				value = 5;
			else if (value == 35)
				value = 7;
			else if (value == 36)
				value = 0;
			else if (value == 37)
				value = 2;
			else if (value == 38)
				value = 0;
			else if (value == 39)
				value = 1;
			else if (value == 40)
				value = 6;
			else if (value == 41)
				value = 0;
			else if (value == 42)
				value = 2;
			else if (value == 43)
				value = 0;
			else if (value == 44)
				value = 4;
			else if (value == 45)
				value = 1;

			return format != null ? String.format(format, value) : Integer
					.toString(value);
		}
		return null;
	}

	@Override
	public int getItemsCount() {
		return maxValue - minValue + 1;
	}
}
