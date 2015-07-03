package com.iqrasys.callblocker.activity;

import com.iqrasys.callblocker.model.PhoneNumberContainer;
import com.iqrasys.db.DBHelper;
import com.iqrasys.helper.GetNetworkStatus;
import com.iqrasys.resource.PhoneNumberModel;
import com.kd.phonecall.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DataInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_input);

		setTitle("Lägg till nummer");
		setTitleColor(Color.DKGRAY);

		GradientDrawable gd = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						Color.WHITE, Color.DKGRAY });
		View title = getWindow().findViewById(android.R.id.title);
		gd.setCornerRadius(0f);

		View titleBar = (View) title.getParent();
		titleBar.setBackgroundDrawable(gd);

		final Context con = this;
		Button b = (Button) findViewById(R.id.add);
		b.setTextColor(Color.DKGRAY);

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String Text = ((EditText) findViewById(R.id.name)).getText()
						.toString();
				String number = ((EditText) findViewById(R.id.number))
						.getText().toString();

				if (number.length() > 4) {
					PhoneNumberModel listItem = new PhoneNumberModel();
					listItem.setTitleText(Text);
					listItem.setNumberText(number);

					PhoneNumberContainer.localNumber.add(listItem);
					DBHelper myDbHelper = new DBHelper(con);

					try {
						myDbHelper.openDataBase();

					} catch (SQLException sqle) {
						throw sqle;
					}

					myDbHelper.insert(listItem);
					myDbHelper.close();

					if (GetNetworkStatus.isNetworkAvailable(con)) {
						String title = "Var god vänta...";
						ProgressDialog pd = ProgressDialog.show(con, title,
								"Kontaktar server", true, false);
						// Toast.makeText(con, "Contact added",
						// Toast.LENGTH_LONG).show();

						boolean bCheckFlag = ((CheckBox) findViewById(R.id.chkbxTellUs))
								.isChecked();
						if (bCheckFlag) {
							/*
							 * GMailSender sender = new
							 * GMailSender("support@communify.se",
							 * "support4088");
							 * 
							 * try { sender.sendMail("New number", "Company: "+
							 * Text + "\n number: " + number,
							 * "support@communify.se", "support@communify.se");
							 * //"support@communify.se",
							 * //"support@communify.se"); } catch
							 * (AddressException e) { // TODO Auto-generated
							 * catch block e.printStackTrace();
							 * Toast.makeText(con,R.string.msgNetworkError,
							 * Toast.LENGTH_LONG).show(); } catch
							 * (MessagingException e) { // TODO Auto-generated
							 * catch block e.printStackTrace();
							 * Toast.makeText(con, R.string.msgNetworkError,
							 * Toast.LENGTH_LONG).show(); }
							 */

							Intent emailIntent = new Intent(
									android.content.Intent.ACTION_SEND);
							String[] recipients = new String[] {
									"support@communify.se", "", };

							emailIntent.putExtra(
									android.content.Intent.EXTRA_EMAIL,
									recipients);
							emailIntent.putExtra(
									android.content.Intent.EXTRA_SUBJECT,
									"Nytt nummer");
							emailIntent
									.putExtra(
											android.content.Intent.EXTRA_TEXT,
											"Företag: " + Text + "\n Nummer: "
													+ number);
							emailIntent.setType("text/plain");

							startActivity(Intent.createChooser(emailIntent,
									"Sända Mail..."));

							Toast.makeText(con, R.string.msgMailSubmit,
									Toast.LENGTH_LONG).show();
						}

						pd.dismiss();
						// Toast.makeText(con, R.string.msgContactAdded,
						// Toast.LENGTH_LONG).show();
					}

					Toast.makeText(con, R.string.msgContactSubmit,
							Toast.LENGTH_LONG).show();
					finish();
				} else {
					Toast.makeText(con, R.string.msgInvalidPhoneNumber,
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}
}
