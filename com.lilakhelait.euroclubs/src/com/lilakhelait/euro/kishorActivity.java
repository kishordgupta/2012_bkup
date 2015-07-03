package com.lilakhelait.euro;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.Toast;

public class kishorActivity extends Activity {

	private ImageView selectedImageView;

	private ImageView leftArrowImageView;

	private ImageView rightArrowImageView;

	private Gallery gallery;

	private int selectedImagePosition = 0;

	private List<Drawable> drawables;
	
	private GalleryImageAdapter galImageAdapter;
	
Boolean flag=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		 Button b =(Button)findViewById(R.id.df);
	     b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		getDrawablesList();
		setupUI();
		final String[] carray = this.getResources().getStringArray(
				R.array.country_arrays);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, carray);
		
		final AutoCompleteTextView ed = (AutoCompleteTextView) findViewById(R.id.edi2);
		ed.setAdapter(adapter);
	final	Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		
		ed.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : carray) {
					selection++;
					
					string= string.toUpperCase();
					String g = ed.getText().toString().toUpperCase();
					if (string.contains(g)) {
				
						spinner1.setSelection(selection - 1);
					
						adapter.notifyDataSetChanged();
						
					
						break;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int selection = 0;
				for (String string : carray) {
					selection++;
					string= string.toUpperCase();
					String g = ed.getText().toString().toUpperCase();
					if (string.contains(g)) {
					
						spinner1.setSelection(selection - 1);
					
						adapter.notifyDataSetChanged();
						
						selection = 0;
						break;
					}
				}
			}
		});
		
		// Get a reference to the AutoCompleteTextView in the layout
		
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//if(flag)
				{
					selectedImagePosition=arg2;
					setSelectedImage(selectedImagePosition);
		
					}
					//flag=true;
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		// Get the string array

	}

	private void setupUI() {
	final Context context=this;
		selectedImageView = (ImageView) findViewById(R.id.selected_imageview);
		leftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview);
		rightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview);
		gallery = (Gallery) findViewById(R.id.gallery);

	selectedImageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			
	          
			final String filename=spinner1.getSelectedItem().toString();;//(selectedImagePosition-1)+"";
			
			alertDialogBuilder.setTitle(filename.toUpperCase());
			alertDialogBuilder.setIcon(drawables.get(selectedImagePosition));
			// set dialog message
			alertDialogBuilder
				.setMessage(filename.toUpperCase() +  "'s logo save in sdcard ?")
				.setCancelable(true)
				.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
					  
						File file = new File(extStorageDirectory,filename+ "logo.png");
						FileOutputStream outStream;
						try {
							outStream = new FileOutputStream(file);
						 
						BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
						Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight() * 0.9), (int) (bd.getIntrinsicWidth() * 0.7), false);
						
						    b.compress(Bitmap.CompressFormat.PNG, 100, outStream);
						    
						    try {
								outStream.flush();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    try {
								outStream.close();
								Toast.makeText(context,"Saved in sd card ", Toast.LENGTH_LONG).show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}}
						    catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				  })
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
					;
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();

			
			
		}
	});
		
		leftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition > 0) {
					--selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);
			}
		});

		rightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (selectedImagePosition < drawables.size() - 1) {
					++selectedImagePosition;

				}

				gallery.setSelection(selectedImagePosition, false);

			}
		});

		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

				selectedImagePosition = pos;

				if (selectedImagePosition > 0 && selectedImagePosition < drawables.size() - 1) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_enabled));
					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_enabled));

				} else if (selectedImagePosition == 0) {

					leftArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_left_disabled));

				} else if (selectedImagePosition == drawables.size() - 1) {

					rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
				}

				changeBorderForSelectedImage(selectedImagePosition);
				setSelectedImage(selectedImagePosition);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

		galImageAdapter = new GalleryImageAdapter(this, drawables);

		gallery.setAdapter(galImageAdapter);

		if (drawables.size() > 0) {

			gallery.setSelection(selectedImagePosition, false);

		}

		if (drawables.size() == 1) {

			rightArrowImageView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_right_disabled));
		}

	}

	private void changeBorderForSelectedImage(int selectedItemPos) {

		int count = gallery.getChildCount();

		for (int i = 0; i < count; i++) {

			ImageView imageView = (ImageView) gallery.getChildAt(i);
			imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.image_border));
			imageView.setPadding(3, 3, 3, 3);

		}

		ImageView imageView = (ImageView) gallery.getSelectedView();
		imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_image_border));
		imageView.setPadding(3, 3, 3, 3);
	}
	
	private void getDrawablesList() {

		drawables = new ArrayList<Drawable>();
		drawables.add(getResources().getDrawable(R.drawable.aab_aalborg));
		drawables.add(getResources().getDrawable(R.drawable.aalesunds_fk));
		drawables.add(getResources().getDrawable(R.drawable.aa_gent));
		drawables.add(getResources().getDrawable(R.drawable.aberdeen));
		drawables.add(getResources().getDrawable(R.drawable.ab_kphybenhavn));
		drawables.add(getResources().getDrawable(R.drawable.academica_coimbra));
		drawables.add(getResources().getDrawable(R.drawable.ac_bellinzona));
		drawables.add(getResources().getDrawable(R.drawable.ac_cesena));
		drawables.add(getResources().getDrawable(R.drawable.ac_horsens));
		drawables.add(getResources().getDrawable(R.drawable.ac_milan));
		drawables.add(getResources().getDrawable(R.drawable.ac_parma));
		drawables.add(getResources().getDrawable(R.drawable.adanaspor));
		drawables.add(getResources().getDrawable(R.drawable.admira_wacker));
		drawables.add(getResources().getDrawable(R.drawable.admira_wien));
		drawables.add(getResources().getDrawable(R.drawable.ado_den_haag));
		drawables.add(getResources().getDrawable(R.drawable.aek_athens));
		drawables.add(getResources().getDrawable(R.drawable.aek_larnaca));
		drawables.add(getResources().getDrawable(R.drawable.ael_limassol));
		drawables.add(getResources().getDrawable(R.drawable.ae_larissa));
		drawables.add(getResources().getDrawable(R.drawable.afan_lido));
		drawables.add(getResources().getDrawable(R.drawable.agf_aarhus));
		drawables.add(getResources().getDrawable(R.drawable.aifk_turku));
		drawables.add(getResources().getDrawable(R.drawable.aik_stockholm));
		drawables.add(getResources().getDrawable(R.drawable.airdrieonians));
		drawables.add(getResources().getDrawable(R.drawable.ajax));
		drawables.add(getResources().getDrawable(R.drawable.aj_auxerre));
		drawables.add(getResources().getDrawable(R.drawable.akademik_sofia));
		drawables.add(getResources().getDrawable(R.drawable.alania_vladikavkaz));
		drawables.add(getResources().getDrawable(R.drawable.albania));
		drawables.add(getResources().getDrawable(R.drawable.albpetrol_patosi));
		drawables.add(getResources().getDrawable(R.drawable.alemannia_aachen));
		drawables.add(getResources().getDrawable(R.drawable.alki_larnaca));
		drawables.add(getResources().getDrawable(R.drawable.alliance_dudelange));
		drawables.add(getResources().getDrawable(R.drawable.allianssi_vantaa));
		drawables.add(getResources().getDrawable(R.drawable.altay_izmir));
		drawables.add(getResources().getDrawable(R.drawable.ameri_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.amica_wronki));
		drawables.add(getResources().getDrawable(R.drawable.amkar_perm));
		drawables.add(getResources().getDrawable(R.drawable.anderlecht));
		drawables.add(getResources().getDrawable(R.drawable.andorra));
		drawables.add(getResources().getDrawable(R.drawable.ankaragucu));
		drawables.add(getResources().getDrawable(R.drawable.anorthosis_famagusta));
		drawables.add(getResources().getDrawable(R.drawable.antalyaspor_as));
		drawables.add(getResources().getDrawable(R.drawable.anzhi_makhachkala));
		drawables.add(getResources().getDrawable(R.drawable.apoel_nicosia));
		drawables.add(getResources().getDrawable(R.drawable.apollon_athens));
		drawables.add(getResources().getDrawable(R.drawable.apollon_limassol));
		drawables.add(getResources().getDrawable(R.drawable.apolonia_fier));
		drawables.add(getResources().getDrawable(R.drawable.apop_kinyras_peyias));
		drawables.add(getResources().getDrawable(R.drawable.araks_ararat));
		drawables.add(getResources().getDrawable(R.drawable.ararat_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.ards_fc));
		drawables.add(getResources().getDrawable(R.drawable.arges_pitesti));
		drawables.add(getResources().getDrawable(R.drawable.aris_bonnevoie));
		drawables.add(getResources().getDrawable(R.drawable.aris_thessaloniki));
		drawables.add(getResources().getDrawable(R.drawable.arka_gdynia));
		drawables.add(getResources().getDrawable(R.drawable.armenia));
		
		drawables.add(getResources().getDrawable(R.drawable.arsenal));
		drawables.add(getResources().getDrawable(R.drawable.arsenal_kiev));
		drawables.add(getResources().getDrawable(R.drawable.asa_tirgu_mures));
		drawables.add(getResources().getDrawable(R.drawable.asteras_tripolis));
		drawables.add(getResources().getDrawable(R.drawable.aston_villa));
		drawables.add(getResources().getDrawable(R.drawable.as_angouleme));
		drawables.add(getResources().getDrawable(R.drawable.as_cannes));
		drawables.add(getResources().getDrawable(R.drawable.as_monaco));
		drawables.add(getResources().getDrawable(R.drawable.as_nancy_lorraine));
		drawables.add(getResources().getDrawable(R.drawable.as_roma));
		drawables.add(getResources().getDrawable(R.drawable.as_saint_etienne));
		drawables.add(getResources().getDrawable(R.drawable.atalanta));
		drawables.add(getResources().getDrawable(R.drawable.athinaikos));
		drawables.add(getResources().getDrawable(R.drawable.athletic_bilbao));
		drawables.add(getResources().getDrawable(R.drawable.athlone_town));
		drawables.add(getResources().getDrawable(R.drawable.atlantas_klaipeda));
		drawables.add(getResources().getDrawable(R.drawable.atletico_madrid));
		drawables.add(getResources().getDrawable(R.drawable.atromitos_fc));
		drawables.add(getResources().getDrawable(R.drawable.aufbau_magdeburg));
		drawables.add(getResources().getDrawable(R.drawable.austria));
		drawables.add(getResources().getDrawable(R.drawable.austria_salzburg));
		drawables.add(getResources().getDrawable(R.drawable.austria_wien));
		drawables.add(getResources().getDrawable(R.drawable.avenir_beggen));
		drawables.add(getResources().getDrawable(R.drawable.azerbaijan));
		drawables.add(getResources().getDrawable(R.drawable.az_alkmaar));
		drawables.add(getResources().getDrawable(R.drawable.b1901_nykphybing));
		drawables.add(getResources().getDrawable(R.drawable.b1903_kphybenhavn));
		drawables.add(getResources().getDrawable(R.drawable.b1909_odense));
		drawables.add(getResources().getDrawable(R.drawable.b1913_odense));
		drawables.add(getResources().getDrawable(R.drawable.b36_torshavn));
		drawables.add(getResources().getDrawable(R.drawable.b68_toftir));
		drawables.add(getResources().getDrawable(R.drawable.b71_sandur));
		drawables.add(getResources().getDrawable(R.drawable.b93_kphybenhavn));
		drawables.add(getResources().getDrawable(R.drawable.ballymena_united));
		drawables.add(getResources().getDrawable(R.drawable.banants_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.banga_gargzdai));
		drawables.add(getResources().getDrawable(R.drawable.bangor_city));
		drawables.add(getResources().getDrawable(R.drawable.bangor_fc));
		drawables.add(getResources().getDrawable(R.drawable.banik_ostrava));
		drawables.add(getResources().getDrawable(R.drawable.barry_town));
		drawables.add(getResources().getDrawable(R.drawable.basel_xi));
		drawables.add(getResources().getDrawable(R.drawable.baskimi_kumanovo));
		drawables.add(getResources().getDrawable(R.drawable.bate_borisov));
		drawables.add(getResources().getDrawable(R.drawable.bayern_munchen));
		drawables.add(getResources().getDrawable(R.drawable.bayer_leverkusen));
		drawables.add(getResources().getDrawable(R.drawable.beerschot_vav));
		drawables.add(getResources().getDrawable(R.drawable.beira_mar));
		drawables.add(getResources().getDrawable(R.drawable.beitar_jerusalem));
		drawables.add(getResources().getDrawable(R.drawable.bekescsabai_elore));
		drawables.add(getResources().getDrawable(R.drawable.belarus));
		drawables.add(getResources().getDrawable(R.drawable.belasica_strumica));
		drawables.add(getResources().getDrawable(R.drawable.belgium));
		drawables.add(getResources().getDrawable(R.drawable.belgrade_xi));
		drawables.add(getResources().getDrawable(R.drawable.belshina_bobruisk));
		drawables.add(getResources().getDrawable(R.drawable.belvedur_izola));
		drawables.add(getResources().getDrawable(R.drawable.benfica));
		drawables.add(getResources().getDrawable(R.drawable.berrichonne_chateauroux));
		drawables.add(getResources().getDrawable(R.drawable.besa_kavaje));
		drawables.add(getResources().getDrawable(R.drawable.besiktas));
		drawables.add(getResources().getDrawable(R.drawable.bfc_dynamo_berlin));
		drawables.add(getResources().getDrawable(R.drawable.birkirkara));
		drawables.add(getResources().getDrawable(R.drawable.birmingham_city));
		drawables.add(getResources().getDrawable(R.drawable.bk_hacken));
		drawables.add(getResources().getDrawable(R.drawable.blackburn_rovers));
		drawables.add(getResources().getDrawable(R.drawable.bnei_sakhnin));
		drawables.add(getResources().getDrawable(R.drawable.bnei_yehuda));
		drawables.add(getResources().getDrawable(R.drawable.boavista));
		drawables.add(getResources().getDrawable(R.drawable.bodphy_glimt));
		drawables.add(getResources().getDrawable(R.drawable.bohemians_dublin));
		drawables.add(getResources().getDrawable(R.drawable.bohemians_praha));
		drawables.add(getResources().getDrawable(R.drawable.bologna));
		drawables.add(getResources().getDrawable(R.drawable.bolton_wanderers));
		drawables.add(getResources().getDrawable(R.drawable.boluspor));
		drawables.add(getResources().getDrawable(R.drawable.borac_cacak));
		drawables.add(getResources().getDrawable(R.drawable.borough_united));
		drawables.add(getResources().getDrawable(R.drawable.borussia_dortmund));
		drawables.add(getResources().getDrawable(R.drawable.borussia_monchengladbach));
		drawables.add(getResources().getDrawable(R.drawable.bosnia_herzegovina));
		drawables.add(getResources().getDrawable(R.drawable.bosnia_herzegovina_2));
		drawables.add(getResources().getDrawable(R.drawable.botev_plovdiv));
		drawables.add(getResources().getDrawable(R.drawable.botev_vratsa));
		drawables.add(getResources().getDrawable(R.drawable.bray_wanderers));
		drawables.add(getResources().getDrawable(R.drawable.breidablik));
		drawables.add(getResources().getDrawable(R.drawable.brotnjo_citluk));
		drawables.add(getResources().getDrawable(R.drawable.brphyndby_if));
		drawables.add(getResources().getDrawable(R.drawable.bryne_fk));
		drawables.add(getResources().getDrawable(R.drawable.buducnost_banatski_dvor));
		drawables.add(getResources().getDrawable(R.drawable.buducnost_banovici));
		drawables.add(getResources().getDrawable(R.drawable.buducnost_podgorica));
		drawables.add(getResources().getDrawable(R.drawable.bulgaria));
		drawables.add(getResources().getDrawable(R.drawable.burnley_fc));
		drawables.add(getResources().getDrawable(R.drawable.bursaspor));
		drawables.add(getResources().getDrawable(R.drawable.bvsc_budapest));
		drawables.add(getResources().getDrawable(R.drawable.bylis_ballshi));
		drawables.add(getResources().getDrawable(R.drawable.cagliari));
		drawables.add(getResources().getDrawable(R.drawable.cardiff_city));
		drawables.add(getResources().getDrawable(R.drawable.carl_zeiss_jena));
		drawables.add(getResources().getDrawable(R.drawable.carmarthen_town));
		drawables.add(getResources().getDrawable(R.drawable.carrick_rangers));
		drawables.add(getResources().getDrawable(R.drawable.castilla));
		drawables.add(getResources().getDrawable(R.drawable.ca_spora_luxembourg));
		drawables.add(getResources().getDrawable(R.drawable.cca_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.cdna_sofia));
		drawables.add(getResources().getDrawable(R.drawable.cd_alaves));
		drawables.add(getResources().getDrawable(R.drawable.cefn_druids));
		drawables.add(getResources().getDrawable(R.drawable.celik_niksic));
		drawables.add(getResources().getDrawable(R.drawable.celta_de_vigo));
		drawables.add(getResources().getDrawable(R.drawable.celtic));
		drawables.add(getResources().getDrawable(R.drawable.cementarnica_skopje));
		drawables.add(getResources().getDrawable(R.drawable.cercle_brugge));
		drawables.add(getResources().getDrawable(R.drawable.ce_principat));
		drawables.add(getResources().getDrawable(R.drawable.ce_sabadell));
		drawables.add(getResources().getDrawable(R.drawable.cfr_cluj));
		drawables.add(getResources().getDrawable(R.drawable.cf_os_belenenses));
		drawables.add(getResources().getDrawable(R.drawable.charleroi));
		drawables.add(getResources().getDrawable(R.drawable.chelsea));
		drawables.add(getResources().getDrawable(R.drawable.chemie_halle));
		drawables.add(getResources().getDrawable(R.drawable.chemie_leipzig));
		drawables.add(getResources().getDrawable(R.drawable.chemlon_humenne));
		drawables.add(getResources().getDrawable(R.drawable.chernomorets_burgas));
		drawables.add(getResources().getDrawable(R.drawable.chernomorets_novoross));
		drawables.add(getResources().getDrawable(R.drawable.chernomorets_odesa));
		drawables.add(getResources().getDrawable(R.drawable.chernomorets_odessa));
		drawables.add(getResources().getDrawable(R.drawable.cherno_more_varna));
		drawables.add(getResources().getDrawable(R.drawable.chievo_verona));
		drawables.add(getResources().getDrawable(R.drawable.chimia_rimnicu_vilcea));
		drawables.add(getResources().getDrawable(R.drawable.cibalia_vinkovci));
		drawables.add(getResources().getDrawable(R.drawable.cliftonville));
		drawables.add(getResources().getDrawable(R.drawable.club_brugge));
		drawables.add(getResources().getDrawable(R.drawable.coleraine));
		drawables.add(getResources().getDrawable(R.drawable.constelacio_sportiva));
		drawables.add(getResources().getDrawable(R.drawable.constructorul_chisinau));
		drawables.add(getResources().getDrawable(R.drawable.cork_celtic));
		drawables.add(getResources().getDrawable(R.drawable.cork_city));
		drawables.add(getResources().getDrawable(R.drawable.cork_hibernians));
		drawables.add(getResources().getDrawable(R.drawable.corvinul_hunedoara));
		drawables.add(getResources().getDrawable(R.drawable.cosmos));
		drawables.add(getResources().getDrawable(R.drawable.coventry_city));
		drawables.add(getResources().getDrawable(R.drawable.croatia));
		drawables.add(getResources().getDrawable(R.drawable.crusaders_belfast));
		drawables.add(getResources().getDrawable(R.drawable.csepel_budapest));
		drawables.add(getResources().getDrawable(R.drawable.cska_kiev));
		drawables.add(getResources().getDrawable(R.drawable.cska_moscow));
		drawables.add(getResources().getDrawable(R.drawable.cska_sofia));
		drawables.add(getResources().getDrawable(R.drawable.csu_galati));
		drawables.add(getResources().getDrawable(R.drawable.cs_fola_esch));
		drawables.add(getResources().getDrawable(R.drawable.cs_grevenmacher));
		drawables.add(getResources().getDrawable(R.drawable.cs_petange));
		drawables.add(getResources().getDrawable(R.drawable.cs_sedan));
		drawables.add(getResources().getDrawable(R.drawable.cwks_warsaw));
		drawables.add(getResources().getDrawable(R.drawable.cwmbran_town));
		drawables.add(getResources().getDrawable(R.drawable.cyprus));
		drawables.add(getResources().getDrawable(R.drawable.czechoslovakia));
		drawables.add(getResources().getDrawable(R.drawable.czech_republic));
		drawables.add(getResources().getDrawable(R.drawable.dacia_chisinau));
		drawables.add(getResources().getDrawable(R.drawable.dac_dunajska_streda));
		drawables.add(getResources().getDrawable(R.drawable.dag_liepaja));
		drawables.add(getResources().getDrawable(R.drawable.daring_cb));
		drawables.add(getResources().getDrawable(R.drawable.daugava_daugavpils));
		drawables.add(getResources().getDrawable(R.drawable.daugava_riga));
		drawables.add(getResources().getDrawable(R.drawable.degerfors_if));
		drawables.add(getResources().getDrawable(R.drawable.denizlispor));
		drawables.add(getResources().getDrawable(R.drawable.denmark));
		drawables.add(getResources().getDrawable(R.drawable.deportivo_la_coruna));
		drawables.add(getResources().getDrawable(R.drawable.derby_county));
		drawables.add(getResources().getDrawable(R.drawable.derry_city));
		drawables.add(getResources().getDrawable(R.drawable.differdange_03));
		drawables.add(getResources().getDrawable(R.drawable.digenis_morphou));
		drawables.add(getResources().getDrawable(R.drawable.dila_gori));
		drawables.add(getResources().getDrawable(R.drawable.dinaburg_daugavpils));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_93_minsk));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_bacau));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_baku));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_batumi));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_brest));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_kiev));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_minsk));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_moscow));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_pitesti));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_tirana));
		drawables.add(getResources().getDrawable(R.drawable.dinamo_zagreb));
		drawables.add(getResources().getDrawable(R.drawable.diosgyori_miskolc));
		drawables.add(getResources().getDrawable(R.drawable.distillery_belfast));
		drawables.add(getResources().getDrawable(R.drawable.djurgardens_if));
		drawables.add(getResources().getDrawable(R.drawable.dnepr_dnepropetrovsk));
		drawables.add(getResources().getDrawable(R.drawable.dnepr_mogilev));
		drawables.add(getResources().getDrawable(R.drawable.dnipro_dnipropetrovsk));
		drawables.add(getResources().getDrawable(R.drawable.dos_utrecht));
		drawables.add(getResources().getDrawable(R.drawable.drogheda_united));
		drawables.add(getResources().getDrawable(R.drawable.drumcondra_fc));
		drawables.add(getResources().getDrawable(R.drawable.dukla_banska_bystrica));
		drawables.add(getResources().getDrawable(R.drawable.dukla_praha));
		drawables.add(getResources().getDrawable(R.drawable.dunaferr_dunaujvaros));
		drawables.add(getResources().getDrawable(R.drawable.dunav_ruse));
		drawables.add(getResources().getDrawable(R.drawable.dundalk));
		drawables.add(getResources().getDrawable(R.drawable.dundee_fc));
		drawables.add(getResources().getDrawable(R.drawable.dundee_united));
		drawables.add(getResources().getDrawable(R.drawable.dunfermline_athletic));
		drawables.add(getResources().getDrawable(R.drawable.dungannon_swifts));
		drawables.add(getResources().getDrawable(R.drawable.dvsc_debrecen));
		drawables.add(getResources().getDrawable(R.drawable.dws_amsterdam));
		drawables.add(getResources().getDrawable(R.drawable.dynamo_dresden));
		drawables.add(getResources().getDrawable(R.drawable.dynamo_zilina));
		drawables.add(getResources().getDrawable(R.drawable.east_germany));
		drawables.add(getResources().getDrawable(R.drawable.eb_streymur));
		drawables.add(getResources().getDrawable(R.drawable.eendracht_aalst));
		drawables.add(getResources().getDrawable(R.drawable.egaleo_athens));
		drawables.add(getResources().getDrawable(R.drawable.eintracht_braunschweig));
		drawables.add(getResources().getDrawable(R.drawable.eintracht_frankfurt));
		drawables.add(getResources().getDrawable(R.drawable.eisenhuttenstadt));
		drawables.add(getResources().getDrawable(R.drawable.ekranas_panevezys));
		drawables.add(getResources().getDrawable(R.drawable.electroputere_craiova));
		drawables.add(getResources().getDrawable(R.drawable.empoli));
		drawables.add(getResources().getDrawable(R.drawable.england));
		drawables.add(getResources().getDrawable(R.drawable.england_3_premier_league));
		drawables.add(getResources().getDrawable(R.drawable.enosis_paralimni));
		drawables.add(getResources().getDrawable(R.drawable.en_avant_guingamp));
		drawables.add(getResources().getDrawable(R.drawable.epa_larnaca));
		drawables.add(getResources().getDrawable(R.drawable.erciyesspor));
		drawables.add(getResources().getDrawable(R.drawable.esbjerg_fb));
		drawables.add(getResources().getDrawable(R.drawable.eskisehirspor));
		drawables.add(getResources().getDrawable(R.drawable.espanyol));
		drawables.add(getResources().getDrawable(R.drawable.estonia));
		drawables.add(getResources().getDrawable(R.drawable.estrela_amadora));
		drawables.add(getResources().getDrawable(R.drawable.etar_veliko_tarnovo));
		drawables.add(getResources().getDrawable(R.drawable.ethnikos_achna));
		drawables.add(getResources().getDrawable(R.drawable.eto_gyor));
		drawables.add(getResources().getDrawable(R.drawable.etzella_ettelbruck));
		drawables.add(getResources().getDrawable(R.drawable.everton));
		drawables.add(getResources().getDrawable(R.drawable.excelsior_mouscron));
		drawables.add(getResources().getDrawable(R.drawable.f91_dudelange));
		drawables.add(getResources().getDrawable(R.drawable.faetano));
		drawables.add(getResources().getDrawable(R.drawable.falkirk_fc));
		drawables.add(getResources().getDrawable(R.drawable.fandok_bobruisk));
		drawables.add(getResources().getDrawable(R.drawable.farense));
		drawables.add(getResources().getDrawable(R.drawable.faroe_islands));
		drawables.add(getResources().getDrawable(R.drawable.fbk_kaunas));
		drawables.add(getResources().getDrawable(R.drawable.fc_aarau));
		drawables.add(getResources().getDrawable(R.drawable.fc_amsterdam));
		drawables.add(getResources().getDrawable(R.drawable.fc_bacau));
		drawables.add(getResources().getDrawable(R.drawable.fc_baia_mare));
		drawables.add(getResources().getDrawable(R.drawable.fc_balzers));
		drawables.add(getResources().getDrawable(R.drawable.fc_barcelona));
		drawables.add(getResources().getDrawable(R.drawable.fc_barcelona_4_100_years));
		drawables.add(getResources().getDrawable(R.drawable.fc_barreirense));
		drawables.add(getResources().getDrawable(R.drawable.fc_basel));
		drawables.add(getResources().getDrawable(R.drawable.fc_beroe_stara_zagora));
		drawables.add(getResources().getDrawable(R.drawable.fc_brasov));
		drawables.add(getResources().getDrawable(R.drawable.fc_brno));
		drawables.add(getResources().getDrawable(R.drawable.fc_chemnitz));
		drawables.add(getResources().getDrawable(R.drawable.fc_den_haag));
		drawables.add(getResources().getDrawable(R.drawable.fc_encamp));
		drawables.add(getResources().getDrawable(R.drawable.fc_fehervar));
		drawables.add(getResources().getDrawable(R.drawable.fc_gomel));
		drawables.add(getResources().getDrawable(R.drawable.fc_groningen));
		drawables.add(getResources().getDrawable(R.drawable.fc_gueugnon));
		drawables.add(getResources().getDrawable(R.drawable.fc_haarlem));
		drawables.add(getResources().getDrawable(R.drawable.fc_halle));
		drawables.add(getResources().getDrawable(R.drawable.fc_izzo_vac));
		drawables.add(getResources().getDrawable(R.drawable.fc_kaiserslautern));
		drawables.add(getResources().getDrawable(R.drawable.fc_karl_marx_stadt));
		drawables.add(getResources().getDrawable(R.drawable.fc_karnten));
		drawables.add(getResources().getDrawable(R.drawable.fc_koln));
		drawables.add(getResources().getDrawable(R.drawable.fc_kosice));
		drawables.add(getResources().getDrawable(R.drawable.fc_kphybenhavn));
		drawables.add(getResources().getDrawable(R.drawable.fc_lahti));
		drawables.add(getResources().getDrawable(R.drawable.fc_liege));
		drawables.add(getResources().getDrawable(R.drawable.fc_lorient));
		drawables.add(getResources().getDrawable(R.drawable.fc_lugano));
		drawables.add(getResources().getDrawable(R.drawable.fc_lusitanos));
		drawables.add(getResources().getDrawable(R.drawable.fc_luzern));
		drawables.add(getResources().getDrawable(R.drawable.fc_magdeburg));
		drawables.add(getResources().getDrawable(R.drawable.fc_metz));
		drawables.add(getResources().getDrawable(R.drawable.fc_midtjylland));
		drawables.add(getResources().getDrawable(R.drawable.fc_mondercange));
		drawables.add(getResources().getDrawable(R.drawable.fc_nantes));
		drawables.add(getResources().getDrawable(R.drawable.fc_nitra));
		drawables.add(getResources().getDrawable(R.drawable.fc_nordsjaelland));
		drawables.add(getResources().getDrawable(R.drawable.fc_nurnberg));
		drawables.add(getResources().getDrawable(R.drawable.fc_porto));
		drawables.add(getResources().getDrawable(R.drawable.fc_rouen));
		drawables.add(getResources().getDrawable(R.drawable.fc_saarbrucken));
		drawables.add(getResources().getDrawable(R.drawable.fc_salzburg));
		drawables.add(getResources().getDrawable(R.drawable.fc_sankt_gallen));
		drawables.add(getResources().getDrawable(R.drawable.fc_santa_coloma));
		drawables.add(getResources().getDrawable(R.drawable.fc_schaan));
		drawables.add(getResources().getDrawable(R.drawable.fc_senec));
		drawables.add(getResources().getDrawable(R.drawable.fc_shumen));
		drawables.add(getResources().getDrawable(R.drawable.fc_sion));
		drawables.add(getResources().getDrawable(R.drawable.fc_sliven));
		drawables.add(getResources().getDrawable(R.drawable.fc_sochaux_montbeliard));
		drawables.add(getResources().getDrawable(R.drawable.fc_sopron));
		drawables.add(getResources().getDrawable(R.drawable.fc_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.fc_thun));
		drawables.add(getResources().getDrawable(R.drawable.fc_timisoara));
		drawables.add(getResources().getDrawable(R.drawable.fc_tiraspol));
		drawables.add(getResources().getDrawable(R.drawable.fc_tirol_innsbruck));
		drawables.add(getResources().getDrawable(R.drawable.fc_twente_enschede));
		drawables.add(getResources().getDrawable(R.drawable.fc_utrecht));
		drawables.add(getResources().getDrawable(R.drawable.fc_vac));
		drawables.add(getResources().getDrawable(R.drawable.fc_vaduz));
		drawables.add(getResources().getDrawable(R.drawable.fc_vaslui));
		drawables.add(getResources().getDrawable(R.drawable.fc_vorwarts_frankfurt));
		drawables.add(getResources().getDrawable(R.drawable.fc_wettingen));
		drawables.add(getResources().getDrawable(R.drawable.fc_wil));
		drawables.add(getResources().getDrawable(R.drawable.fc_zestafoni));
		drawables.add(getResources().getDrawable(R.drawable.fc_zlate_moravce));
		drawables.add(getResources().getDrawable(R.drawable.fc_zurich));
		drawables.add(getResources().getDrawable(R.drawable.fenerbahce));
		drawables.add(getResources().getDrawable(R.drawable.ferencvaros));
		drawables.add(getResources().getDrawable(R.drawable.feyenoord));
		drawables.add(getResources().getDrawable(R.drawable.fh_hafnarfjardar));
		drawables.add(getResources().getDrawable(R.drawable.finland));
		drawables.add(getResources().getDrawable(R.drawable.finnpa_helsinki));
		drawables.add(getResources().getDrawable(R.drawable.finn_harps));
		drawables.add(getResources().getDrawable(R.drawable.fiorentina));
		drawables.add(getResources().getDrawable(R.drawable.first_vienna_fc));
		drawables.add(getResources().getDrawable(R.drawable.fk_aktobe));
		drawables.add(getResources().getDrawable(R.drawable.fk_almaty));
		drawables.add(getResources().getDrawable(R.drawable.fk_astana));
		drawables.add(getResources().getDrawable(R.drawable.fk_atyrau));
		drawables.add(getResources().getDrawable(R.drawable.fk_baku));
		drawables.add(getResources().getDrawable(R.drawable.fk_bezanija));
		drawables.add(getResources().getDrawable(R.drawable.fk_bor));
		drawables.add(getResources().getDrawable(R.drawable.fk_borac_banja_luka));
		drawables.add(getResources().getDrawable(R.drawable.fk_jablonec));
		drawables.add(getResources().getDrawable(R.drawable.fk_jagodina));
		drawables.add(getResources().getDrawable(R.drawable.fk_jelgava));
		drawables.add(getResources().getDrawable(R.drawable.fk_minsk));
		drawables.add(getResources().getDrawable(R.drawable.fk_modrica));
		drawables.add(getResources().getDrawable(R.drawable.fk_moscow));
		drawables.add(getResources().getDrawable(R.drawable.fk_obilic));
		drawables.add(getResources().getDrawable(R.drawable.fk_pribram));
		drawables.add(getResources().getDrawable(R.drawable.fk_rad_belgrade));
		drawables.add(getResources().getDrawable(R.drawable.fk_riga));
		drawables.add(getResources().getDrawable(R.drawable.fk_sarajevo));
		drawables.add(getResources().getDrawable(R.drawable.fk_senica));
		drawables.add(getResources().getDrawable(R.drawable.fk_sevojno));
		drawables.add(getResources().getDrawable(R.drawable.fk_shamkir));
		drawables.add(getResources().getDrawable(R.drawable.fk_siauliai));
		drawables.add(getResources().getDrawable(R.drawable.fk_tauras));
		drawables.add(getResources().getDrawable(R.drawable.fk_teplice));
		drawables.add(getResources().getDrawable(R.drawable.fk_ventspils));
		drawables.add(getResources().getDrawable(R.drawable.fk_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.flacara_moreni));
		drawables.add(getResources().getDrawable(R.drawable.flora_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.floriana));
		drawables.add(getResources().getDrawable(R.drawable.folgore));
		drawables.add(getResources().getDrawable(R.drawable.fortuna_54_geleen));
		drawables.add(getResources().getDrawable(R.drawable.fortuna_dusseldorf));
		drawables.add(getResources().getDrawable(R.drawable.fortuna_sittard));
		drawables.add(getResources().getDrawable(R.drawable.fram_reykjavik));
		drawables.add(getResources().getDrawable(R.drawable.france));
		drawables.add(getResources().getDrawable(R.drawable.frankfurt_xi));
		drawables.add(getResources().getDrawable(R.drawable.fredrikstad_fk));
		drawables.add(getResources().getDrawable(R.drawable.fremad_amager));
		drawables.add(getResources().getDrawable(R.drawable.frem_kobenhavn));
		drawables.add(getResources().getDrawable(R.drawable.frigg_oslo));
		drawables.add(getResources().getDrawable(R.drawable.fsv_mainz_05));
		drawables.add(getResources().getDrawable(R.drawable.fulham_fc));
		drawables.add(getResources().getDrawable(R.drawable.fylkir_reykjavik));
		drawables.add(getResources().getDrawable(R.drawable.fyllingen_il));
		drawables.add(getResources().getDrawable(R.drawable.gagra_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.gais_goteborg));
		drawables.add(getResources().getDrawable(R.drawable.galatasaray));
		drawables.add(getResources().getDrawable(R.drawable.galway_united));
		drawables.add(getResources().getDrawable(R.drawable.gandzasar_kapan));
		drawables.add(getResources().getDrawable(R.drawable.garabag_agdam));
		drawables.add(getResources().getDrawable(R.drawable.gaziantepspor));
		drawables.add(getResources().getDrawable(R.drawable.gaz_metan_medias));
		drawables.add(getResources().getDrawable(R.drawable.gd_chaves));
		drawables.add(getResources().getDrawable(R.drawable.gd_cuf_barreiro));
		drawables.add(getResources().getDrawable(R.drawable.gefle_if));
		drawables.add(getResources().getDrawable(R.drawable.genclerbirligi));
		drawables.add(getResources().getDrawable(R.drawable.genoa));
		drawables.add(getResources().getDrawable(R.drawable.georgia));
		drawables.add(getResources().getDrawable(R.drawable.georgia_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.germany));
		drawables.add(getResources().getDrawable(R.drawable.germinal_beerschot));
		drawables.add(getResources().getDrawable(R.drawable.germinal_ekeren));
		drawables.add(getResources().getDrawable(R.drawable.getafe));
		drawables.add(getResources().getDrawable(R.drawable.girondins_bordeaux));
		drawables.add(getResources().getDrawable(R.drawable.gi_gotu));
		drawables.add(getResources().getDrawable(R.drawable.gjphyvik_lyn));
		drawables.add(getResources().getDrawable(R.drawable.gks_belchatow));
		drawables.add(getResources().getDrawable(R.drawable.gks_katowice));
		drawables.add(getResources().getDrawable(R.drawable.gks_tychy));
		drawables.add(getResources().getDrawable(R.drawable.glasgow_rangers));
		drawables.add(getResources().getDrawable(R.drawable.glenavon));
		drawables.add(getResources().getDrawable(R.drawable.glentoran));
		drawables.add(getResources().getDrawable(R.drawable.gloria_bistrita));
		drawables.add(getResources().getDrawable(R.drawable.gornik_zabrze));
		drawables.add(getResources().getDrawable(R.drawable.goztepe));
		drawables.add(getResources().getDrawable(R.drawable.go_ahead_deventer));
		drawables.add(getResources().getDrawable(R.drawable.grasshoppers_zurich));
		drawables.add(getResources().getDrawable(R.drawable.grazer_ak));
		drawables.add(getResources().getDrawable(R.drawable.greece));
		drawables.add(getResources().getDrawable(R.drawable.greenock_morton));
		drawables.add(getResources().getDrawable(R.drawable.gretna_fc));
		drawables.add(getResources().getDrawable(R.drawable.grindavik));
		drawables.add(getResources().getDrawable(R.drawable.groclin_grodzisk));
		drawables.add(getResources().getDrawable(R.drawable.gwardia_warsaw));
		drawables.add(getResources().getDrawable(R.drawable.gzira_united));
		drawables.add(getResources().getDrawable(R.drawable.hajduk_kula));
		drawables.add(getResources().getDrawable(R.drawable.hajduk_split));
		drawables.add(getResources().getDrawable(R.drawable.haka_valkeakoski));
		drawables.add(getResources().getDrawable(R.drawable.haladas_szombathely));
		drawables.add(getResources().getDrawable(R.drawable.halmstads_bk));
		drawables.add(getResources().getDrawable(R.drawable.hamburger_sv));
		drawables.add(getResources().getDrawable(R.drawable.hammarby_if));
		drawables.add(getResources().getDrawable(R.drawable.hamrun_spartans));
		drawables.add(getResources().getDrawable(R.drawable.hannover_96));
		drawables.add(getResources().getDrawable(R.drawable.hansa_rostock));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_beer_sheva));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_haifa));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_ironi_rishon));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_kiryat_shmona));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_petah_tikva));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_ramat_gan));
		drawables.add(getResources().getDrawable(R.drawable.hapoel_tel_aviv));
		drawables.add(getResources().getDrawable(R.drawable.hask_gradjanski));
		drawables.add(getResources().getDrawable(R.drawable.haugur_haugesund));
		drawables.add(getResources().getDrawable(R.drawable.haverfordwest_county));
		drawables.add(getResources().getDrawable(R.drawable.hb_torshavn));
		drawables.add(getResources().getDrawable(R.drawable.hearts_fc));
		drawables.add(getResources().getDrawable(R.drawable.heerenveen));
		drawables.add(getResources().getDrawable(R.drawable.hellas_verona));
		drawables.add(getResources().getDrawable(R.drawable.helsingborg_if));
		drawables.add(getResources().getDrawable(R.drawable.herfphylge_bk));
		drawables.add(getResources().getDrawable(R.drawable.hertha_bsc));
		drawables.add(getResources().getDrawable(R.drawable.hibernian));
		drawables.add(getResources().getDrawable(R.drawable.hibernians_fc));
		drawables.add(getResources().getDrawable(R.drawable.hifk_helsinki));
		drawables.add(getResources().getDrawable(R.drawable.hjk_helsinki));
		drawables.add(getResources().getDrawable(R.drawable.holbaek_bandif));
		drawables.add(getResources().getDrawable(R.drawable.home_farm_fc));
		drawables.add(getResources().getDrawable(R.drawable.honka_espoo));
		drawables.add(getResources().getDrawable(R.drawable.honved_budapest));
		drawables.add(getResources().getDrawable(R.drawable.hps_helsinki));
		drawables.add(getResources().getDrawable(R.drawable.hradec_kralove));
		drawables.add(getResources().getDrawable(R.drawable.hungary));
		drawables.add(getResources().getDrawable(R.drawable.hutnik_krakow));
		drawables.add(getResources().getDrawable(R.drawable.hvidovre_if));
		drawables.add(getResources().getDrawable(R.drawable.ia_akranes));
		drawables.add(getResources().getDrawable(R.drawable.iba_akureyri));
		drawables.add(getResources().getDrawable(R.drawable.ibv_vestmannaeyjar));
		drawables.add(getResources().getDrawable(R.drawable.ib_ljubljana));
		drawables.add(getResources().getDrawable(R.drawable.iceland));
		drawables.add(getResources().getDrawable(R.drawable.ifk_goteborg));
		drawables.add(getResources().getDrawable(R.drawable.ifk_malmo));
		drawables.add(getResources().getDrawable(R.drawable.ifk_norrkoping));
		drawables.add(getResources().getDrawable(R.drawable.if_elfsborg));
		drawables.add(getResources().getDrawable(R.drawable.if_fuglafjphyrdur));
		drawables.add(getResources().getDrawable(R.drawable.ikast_fs));
		drawables.add(getResources().getDrawable(R.drawable.ik_brage));
		drawables.add(getResources().getDrawable(R.drawable.illichivets_mariupol));
		drawables.add(getResources().getDrawable(R.drawable.ilves_tampere));
		drawables.add(getResources().getDrawable(R.drawable.inkaras_kaunas));
		drawables.add(getResources().getDrawable(R.drawable.internazionale));
		drawables.add(getResources().getDrawable(R.drawable.inter_baku));
		drawables.add(getResources().getDrawable(R.drawable.inter_bratislava));
		drawables.add(getResources().getDrawable(R.drawable.inter_cardiff));
		drawables.add(getResources().getDrawable(R.drawable.inter_turku));
		drawables.add(getResources().getDrawable(R.drawable.inter_zapresic));
		drawables.add(getResources().getDrawable(R.drawable.ionikos_nikea));
		drawables.add(getResources().getDrawable(R.drawable.ipswich_town));
		drawables.add(getResources().getDrawable(R.drawable.iraklis_thessaloniki));
		drawables.add(getResources().getDrawable(R.drawable.ireland));
		drawables.add(getResources().getDrawable(R.drawable.irtysh_pavlodar));
		drawables.add(getResources().getDrawable(R.drawable.iskra_stal_ribnita));
		drawables.add(getResources().getDrawable(R.drawable.israel));
		drawables.add(getResources().getDrawable(R.drawable.istanbulspor));
		drawables.add(getResources().getDrawable(R.drawable.italy));
		drawables.add(getResources().getDrawable(R.drawable.jagiellonia_bialystok));
		drawables.add(getResources().getDrawable(R.drawable.jazz_pori));
		drawables.add(getResources().getDrawable(R.drawable.jeunesse_d_esch));
		drawables.add(getResources().getDrawable(R.drawable.jeunesse_hautcharage));
		drawables.add(getResources().getDrawable(R.drawable.jfk_olimps_riga));
		drawables.add(getResources().getDrawable(R.drawable.jiul_petrosani));
		drawables.add(getResources().getDrawable(R.drawable.jjk_jyvaskyla));
		drawables.add(getResources().getDrawable(R.drawable.jokerit_helsinki));
		drawables.add(getResources().getDrawable(R.drawable.juvenes_dogana));
		drawables.add(getResources().getDrawable(R.drawable.juventus));
		drawables.add(getResources().getDrawable(R.drawable.kairat_almaty));
		drawables.add(getResources().getDrawable(R.drawable.kalev_sillamae));
		drawables.add(getResources().getDrawable(R.drawable.kalju_nomme));
		drawables.add(getResources().getDrawable(R.drawable.kalmar_ff));
		drawables.add(getResources().getDrawable(R.drawable.kamen_ingrad));
		drawables.add(getResources().getDrawable(R.drawable.kapaz_ganja));
		drawables.add(getResources().getDrawable(R.drawable.kareda_siauliai));
		drawables.add(getResources().getDrawable(R.drawable.karlsruher_sc));
		drawables.add(getResources().getDrawable(R.drawable.karpaty_lviv));
		drawables.add(getResources().getDrawable(R.drawable.karpaty_lvov));
		drawables.add(getResources().getDrawable(R.drawable.karvan_evlakh));
		drawables.add(getResources().getDrawable(R.drawable.kastoria_fc));
		drawables.add(getResources().getDrawable(R.drawable.kayserispor));
		drawables.add(getResources().getDrawable(R.drawable.kazakhstan));
		drawables.add(getResources().getDrawable(R.drawable.ka_akureyri));
		drawables.add(getResources().getDrawable(R.drawable.kb_kphybenhavn));
		drawables.add(getResources().getDrawable(R.drawable.kecskemeti_te));
		drawables.add(getResources().getDrawable(R.drawable.keflavik));
		drawables.add(getResources().getDrawable(R.drawable.kfc_uerdingen));
		drawables.add(getResources().getDrawable(R.drawable.kfc_winterslag));
		drawables.add(getResources().getDrawable(R.drawable.kf_laci));
		drawables.add(getResources().getDrawable(R.drawable.khazar_lenkoran));
		drawables.add(getResources().getDrawable(R.drawable.khazri_buzovna));
		drawables.add(getResources().getDrawable(R.drawable.kickers_offenbach));
		drawables.add(getResources().getDrawable(R.drawable.kilikia_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.kilmarnock));
		drawables.add(getResources().getDrawable(R.drawable.ki_klaksvik));
		drawables.add(getResources().getDrawable(R.drawable.koba_senec));
		drawables.add(getResources().getDrawable(R.drawable.kocaelispor));
		drawables.add(getResources().getDrawable(R.drawable.kolkheti_1913_poti));
		drawables.add(getResources().getDrawable(R.drawable.koln_xi));
		drawables.add(getResources().getDrawable(R.drawable.komloi_banyasz));
		drawables.add(getResources().getDrawable(R.drawable.kongsvinger_il));
		drawables.add(getResources().getDrawable(R.drawable.koparit_kuopio));
		drawables.add(getResources().getDrawable(R.drawable.kotaik_abovyan));
		drawables.add(getResources().getDrawable(R.drawable.kphybenhavn_xi));
		drawables.add(getResources().getDrawable(R.drawable.kphyge_bk));
		drawables.add(getResources().getDrawable(R.drawable.kpv_kokkola));
		drawables.add(getResources().getDrawable(R.drawable.krylia_sovetov_samara));
		drawables.add(getResources().getDrawable(R.drawable.kryvbas_kryviy_rih));
		drawables.add(getResources().getDrawable(R.drawable.kr_reykjavik));
		drawables.add(getResources().getDrawable(R.drawable.ksc_lokeren));
		drawables.add(getResources().getDrawable(R.drawable.ksv_roeselare));
		drawables.add(getResources().getDrawable(R.drawable.ks_elbasani));
		drawables.add(getResources().getDrawable(R.drawable.ks_flamurtari_vlore));
		drawables.add(getResources().getDrawable(R.drawable.ktp_kotka));
		drawables.add(getResources().getDrawable(R.drawable.kups_kuopio));
		drawables.add(getResources().getDrawable(R.drawable.kuusysi_lahti));
		drawables.add(getResources().getDrawable(R.drawable.kvc_westerlo));
		drawables.add(getResources().getDrawable(R.drawable.kv_mechelen));
		drawables.add(getResources().getDrawable(R.drawable.labinoti_elbasan));
		drawables.add(getResources().getDrawable(R.drawable.landskrona_bois));
		drawables.add(getResources().getDrawable(R.drawable.lantana_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.lask_linz));
		drawables.add(getResources().getDrawable(R.drawable.latvia));
		drawables.add(getResources().getDrawable(R.drawable.lausanne_sports));
		drawables.add(getResources().getDrawable(R.drawable.lazio_roma));
		drawables.add(getResources().getDrawable(R.drawable.la_fiorita));
		drawables.add(getResources().getDrawable(R.drawable.la_louviere));
		drawables.add(getResources().getDrawable(R.drawable.lechia_gdansk));
		drawables.add(getResources().getDrawable(R.drawable.lech_poznan));
		drawables.add(getResources().getDrawable(R.drawable.leeds_united));
		drawables.add(getResources().getDrawable(R.drawable.legia_warsaw));
		drawables.add(getResources().getDrawable(R.drawable.leicester_city));
		drawables.add(getResources().getDrawable(R.drawable.leiftur));
		drawables.add(getResources().getDrawable(R.drawable.leipzig_xi));
		drawables.add(getResources().getDrawable(R.drawable.leixoes_sc));
		drawables.add(getResources().getDrawable(R.drawable.leotar_trebinje));
		drawables.add(getResources().getDrawable(R.drawable.levadia_maardu));
		drawables.add(getResources().getDrawable(R.drawable.levadia_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.levante_ud));
		drawables.add(getResources().getDrawable(R.drawable.levski_sofia));
		drawables.add(getResources().getDrawable(R.drawable.levski_spartak_sofia));
		drawables.add(getResources().getDrawable(R.drawable.libertas));
		drawables.add(getResources().getDrawable(R.drawable.liechtenstein));
		drawables.add(getResources().getDrawable(R.drawable.lierse_sk));
		drawables.add(getResources().getDrawable(R.drawable.lillestrphym_sk));
		drawables.add(getResources().getDrawable(R.drawable.lille_osc));
		drawables.add(getResources().getDrawable(R.drawable.limerick));
		drawables.add(getResources().getDrawable(R.drawable.linfield_belfast));
		drawables.add(getResources().getDrawable(R.drawable.lisburn_distillery));
		drawables.add(getResources().getDrawable(R.drawable.litex_lovech));
		drawables.add(getResources().getDrawable(R.drawable.lithuania));
		drawables.add(getResources().getDrawable(R.drawable.liverpool));
		drawables.add(getResources().getDrawable(R.drawable.livingston));
		drawables.add(getResources().getDrawable(R.drawable.livorno));
		drawables.add(getResources().getDrawable(R.drawable.lks_lodz));
		drawables.add(getResources().getDrawable(R.drawable.llanelli_fc));
		drawables.add(getResources().getDrawable(R.drawable.llansantffraid));
		drawables.add(getResources().getDrawable(R.drawable.lokomotiva_kosice));
		drawables.add(getResources().getDrawable(R.drawable.lokomotive_leipzig));
		drawables.add(getResources().getDrawable(R.drawable.lokomotivi_tbilisi));
		drawables.add(getResources().getDrawable(R.drawable.lokomotiv_96_vitebsk));
		drawables.add(getResources().getDrawable(R.drawable.lokomotiv_moscow));
		drawables.add(getResources().getDrawable(R.drawable.lokomotiv_plovdiv));
		drawables.add(getResources().getDrawable(R.drawable.lokomotiv_sofia));
		drawables.add(getResources().getDrawable(R.drawable.london_xi));
		drawables.add(getResources().getDrawable(R.drawable.longford_town));
		drawables.add(getResources().getDrawable(R.drawable.ludogorets_razgrad));
		drawables.add(getResources().getDrawable(R.drawable.luxembourg));
		drawables.add(getResources().getDrawable(R.drawable.lyngby_bk));
		drawables.add(getResources().getDrawable(R.drawable.lyn_oslo));
		drawables.add(getResources().getDrawable(R.drawable.maccabi_haifa));
		drawables.add(getResources().getDrawable(R.drawable.maccabi_netanya));
		drawables.add(getResources().getDrawable(R.drawable.maccabi_petah_tikva));
		drawables.add(getResources().getDrawable(R.drawable.maccabi_tel_aviv));
		drawables.add(getResources().getDrawable(R.drawable.macedonia));
		drawables.add(getResources().getDrawable(R.drawable.makedonija_skopje));
		drawables.add(getResources().getDrawable(R.drawable.malaga_cf));
		drawables.add(getResources().getDrawable(R.drawable.malatyaspor));
		drawables.add(getResources().getDrawable(R.drawable.malmo_ff));
		drawables.add(getResources().getDrawable(R.drawable.malta));
		drawables.add(getResources().getDrawable(R.drawable.manchester_city));
		drawables.add(getResources().getDrawable(R.drawable.manchester_city_4_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.manchester_united));
		drawables.add(getResources().getDrawable(R.drawable.marek_stanke_dimitrov));
		drawables.add(getResources().getDrawable(R.drawable.margveti_zestafoni));
		drawables.add(getResources().getDrawable(R.drawable.maritimo_funchal));
		drawables.add(getResources().getDrawable(R.drawable.marsaxlokk));
		drawables.add(getResources().getDrawable(R.drawable.marsa_fc));
		drawables.add(getResources().getDrawable(R.drawable.matador_puchov));
		drawables.add(getResources().getDrawable(R.drawable.mersin_idmanyurdu));
		drawables.add(getResources().getDrawable(R.drawable.merthyr_tydfil_fc));
		drawables.add(getResources().getDrawable(R.drawable.metalist_kharkiv));
		drawables.add(getResources().getDrawable(R.drawable.metalist_kharkov));
		drawables.add(getResources().getDrawable(R.drawable.metalurgs_liepaja));
		drawables.add(getResources().getDrawable(R.drawable.metalurg_donetsk));
		drawables.add(getResources().getDrawable(R.drawable.metalurg_skopje));
		drawables.add(getResources().getDrawable(R.drawable.metalurg_zaporizhia));
		drawables.add(getResources().getDrawable(R.drawable.mfk_kosice));
		drawables.add(getResources().getDrawable(R.drawable.middlesbrough));
		drawables.add(getResources().getDrawable(R.drawable.miedz_legnica));
		drawables.add(getResources().getDrawable(R.drawable.mika_ashtarak));
		drawables.add(getResources().getDrawable(R.drawable.milano_kumanovo));
		drawables.add(getResources().getDrawable(R.drawable.millwall_fc));
		drawables.add(getResources().getDrawable(R.drawable.milsami_orhei));
		drawables.add(getResources().getDrawable(R.drawable.mjphyndalen_if));
		drawables.add(getResources().getDrawable(R.drawable.mkt_araz));
		drawables.add(getResources().getDrawable(R.drawable.mlada_boleslav));
		drawables.add(getResources().getDrawable(R.drawable.mogren_budva));
		drawables.add(getResources().getDrawable(R.drawable.montenegro));
		drawables.add(getResources().getDrawable(R.drawable.montpellier));
		drawables.add(getResources().getDrawable(R.drawable.moss_fk));
		drawables.add(getResources().getDrawable(R.drawable.motor_jena));
		drawables.add(getResources().getDrawable(R.drawable.motor_zwickau));
		drawables.add(getResources().getDrawable(R.drawable.mp_mikkeli));
		drawables.add(getResources().getDrawable(R.drawable.msc_pecs));
		drawables.add(getResources().getDrawable(R.drawable.msk_zilina));
		drawables.add(getResources().getDrawable(R.drawable.msv_duisburg));
		drawables.add(getResources().getDrawable(R.drawable.ms_ashdod));
		drawables.add(getResources().getDrawable(R.drawable.mtk_budapest));
		drawables.add(getResources().getDrawable(R.drawable.mtz_ripo_minsk));
		drawables.add(getResources().getDrawable(R.drawable.murata));
		drawables.add(getResources().getDrawable(R.drawable.mura_murska_sobota));
		drawables.add(getResources().getDrawable(R.drawable.mypa_47));
		drawables.add(getResources().getDrawable(R.drawable.nacional_funchal));
		drawables.add(getResources().getDrawable(R.drawable.nac_breda));
		drawables.add(getResources().getDrawable(R.drawable.naestved_if));
		drawables.add(getResources().getDrawable(R.drawable.naftan_novopolotsk));
		drawables.add(getResources().getDrawable(R.drawable.napoli));
		drawables.add(getResources().getDrawable(R.drawable.napredak_krusevac));
		drawables.add(getResources().getDrawable(R.drawable.national_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.neath_fc));
		drawables.add(getResources().getDrawable(R.drawable.nec_nijmegen));
		drawables.add(getResources().getDrawable(R.drawable.neftchi_baku));
		drawables.add(getResources().getDrawable(R.drawable.neftochimik_burgas));
		drawables.add(getResources().getDrawable(R.drawable.neman_grodno));
		drawables.add(getResources().getDrawable(R.drawable.nentori_tirana));
		drawables.add(getResources().getDrawable(R.drawable.netherlands));
		drawables.add(getResources().getDrawable(R.drawable.newcastle_united));
		drawables.add(getResources().getDrawable(R.drawable.newcastle_united_5_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.newport_county));
		drawables.add(getResources().getDrawable(R.drawable.newtown));
		drawables.add(getResources().getDrawable(R.drawable.nikol_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.nistru_otaci));
		drawables.add(getResources().getDrawable(R.drawable.niva_vynnitsa));
		drawables.add(getResources().getDrawable(R.drawable.nk_becej));
		drawables.add(getResources().getDrawable(R.drawable.nk_celje));
		drawables.add(getResources().getDrawable(R.drawable.nk_domzale));
		drawables.add(getResources().getDrawable(R.drawable.nk_koper));
		drawables.add(getResources().getDrawable(R.drawable.nk_maribor));
		drawables.add(getResources().getDrawable(R.drawable.nk_orasje));
		drawables.add(getResources().getDrawable(R.drawable.nk_osijek));
		drawables.add(getResources().getDrawable(R.drawable.nk_primorje));
		drawables.add(getResources().getDrawable(R.drawable.nk_rijeka));
		drawables.add(getResources().getDrawable(R.drawable.nk_sibenik));
		drawables.add(getResources().getDrawable(R.drawable.nk_varazdin));
		drawables.add(getResources().getDrawable(R.drawable.nk_zagreb));
		drawables.add(getResources().getDrawable(R.drawable.nk_zepce));
		drawables.add(getResources().getDrawable(R.drawable.norma_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.northern_ireland));
		drawables.add(getResources().getDrawable(R.drawable.norway));
		drawables.add(getResources().getDrawable(R.drawable.norwich_city));
		drawables.add(getResources().getDrawable(R.drawable.nottingham_forest));
		drawables.add(getResources().getDrawable(R.drawable.nottingham_forest_4_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.nova_gorica));
		drawables.add(getResources().getDrawable(R.drawable.novi_sad_xi));
		drawables.add(getResources().getDrawable(R.drawable.nsi_runavik));
		drawables.add(getResources().getDrawable(R.drawable.ob_odense));
		drawables.add(getResources().getDrawable(R.drawable.odd_grenland));
		drawables.add(getResources().getDrawable(R.drawable.odense_xi));
		drawables.add(getResources().getDrawable(R.drawable.odra_opole));
		drawables.add(getResources().getDrawable(R.drawable.odra_wodzislaw));
		drawables.add(getResources().getDrawable(R.drawable.ofi_heraklion));
		drawables.add(getResources().getDrawable(R.drawable.ofk_belgrade));
		drawables.add(getResources().getDrawable(R.drawable.ofk_petrovac));
		drawables.add(getResources().getDrawable(R.drawable.ogc_nice));
		drawables.add(getResources().getDrawable(R.drawable.okzhetpes_kokshetau));
		drawables.add(getResources().getDrawable(R.drawable.olimpia_balti));
		drawables.add(getResources().getDrawable(R.drawable.olimpija_ljubljana));
		drawables.add(getResources().getDrawable(R.drawable.olimpija_riga));
		drawables.add(getResources().getDrawable(R.drawable.olimpik_baku));
		drawables.add(getResources().getDrawable(R.drawable.olimpi_rustavi));
		drawables.add(getResources().getDrawable(R.drawable.olympiakos_nicosia));
		drawables.add(getResources().getDrawable(R.drawable.olympiakos_piraeus));
		drawables.add(getResources().getDrawable(R.drawable.olympiakos_volou));
		drawables.add(getResources().getDrawable(R.drawable.olympique_lyon));
		drawables.add(getResources().getDrawable(R.drawable.olympique_marseille));
		drawables.add(getResources().getDrawable(R.drawable.olympique_nimes));
		drawables.add(getResources().getDrawable(R.drawable.omonia_nicosia));
		drawables.add(getResources().getDrawable(R.drawable.ops_oulu));
		drawables.add(getResources().getDrawable(R.drawable.ordabasy_shymkent));
		drawables.add(getResources().getDrawable(R.drawable.orduspor));
		drawables.add(getResources().getDrawable(R.drawable.osasuna));
		drawables.add(getResources().getDrawable(R.drawable.otelul_galati));
		drawables.add(getResources().getDrawable(R.drawable.pacos_de_ferreira));
		drawables.add(getResources().getDrawable(R.drawable.paksi_fc));
		drawables.add(getResources().getDrawable(R.drawable.palermo));
		drawables.add(getResources().getDrawable(R.drawable.panachaiki_patras));
		drawables.add(getResources().getDrawable(R.drawable.panathinaikos));
		drawables.add(getResources().getDrawable(R.drawable.panionios));
		drawables.add(getResources().getDrawable(R.drawable.paok_thessaloniki));
		drawables.add(getResources().getDrawable(R.drawable.paris_saint_germain));
		drawables.add(getResources().getDrawable(R.drawable.partick_thistle));
		drawables.add(getResources().getDrawable(R.drawable.partizani_tirana));
		drawables.add(getResources().getDrawable(R.drawable.partizan_belgrade));
		drawables.add(getResources().getDrawable(R.drawable.pecsi_dozsa));
		drawables.add(getResources().getDrawable(R.drawable.pelister_bitola));
		drawables.add(getResources().getDrawable(R.drawable.pennarossa));
		drawables.add(getResources().getDrawable(R.drawable.perugia));
		drawables.add(getResources().getDrawable(R.drawable.petra_drnovice));
		drawables.add(getResources().getDrawable(R.drawable.petrolul_ploiesti));
		drawables.add(getResources().getDrawable(R.drawable.petrzalka_bratislava));
		drawables.add(getResources().getDrawable(R.drawable.pezoporikos_larnaca));
		drawables.add(getResources().getDrawable(R.drawable.pirin_blagoevgrad));
		drawables.add(getResources().getDrawable(R.drawable.pobeda_prilep));
		drawables.add(getResources().getDrawable(R.drawable.pogon_szczecin));
		drawables.add(getResources().getDrawable(R.drawable.poland));
		drawables.add(getResources().getDrawable(R.drawable.politehnica_timisoara));
		drawables.add(getResources().getDrawable(R.drawable.polonia_bytom));
		drawables.add(getResources().getDrawable(R.drawable.polonia_warsaw));
		drawables.add(getResources().getDrawable(R.drawable.portadown));
		drawables.add(getResources().getDrawable(R.drawable.portimonense_sc));
		drawables.add(getResources().getDrawable(R.drawable.portsmouth_fc));
		drawables.add(getResources().getDrawable(R.drawable.portsmouth_fc_4_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.portugal));
		drawables.add(getResources().getDrawable(R.drawable.port_talbot_town));
		drawables.add(getResources().getDrawable(R.drawable.progresul_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.progres_niederkorn));
		drawables.add(getResources().getDrawable(R.drawable.psv_eindhoven));
		drawables.add(getResources().getDrawable(R.drawable.psv_schwerin));
		drawables.add(getResources().getDrawable(R.drawable.publikum_celje));
		drawables.add(getResources().getDrawable(R.drawable.pyunik_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.queens_park_rangers));
		drawables.add(getResources().getDrawable(R.drawable.queen_of_the_south));
		drawables.add(getResources().getDrawable(R.drawable.rabat_ajax));
		drawables.add(getResources().getDrawable(R.drawable.rabotnicki_skopje));
		drawables.add(getResources().getDrawable(R.drawable.racing_genk));
		drawables.add(getResources().getDrawable(R.drawable.racing_santander));
		drawables.add(getResources().getDrawable(R.drawable.racing_union_luxembourg));
		drawables.add(getResources().getDrawable(R.drawable.racing_white));
		drawables.add(getResources().getDrawable(R.drawable.radnicki_nis));
		drawables.add(getResources().getDrawable(R.drawable.raith_rovers));
		drawables.add(getResources().getDrawable(R.drawable.randers_fc));
		drawables.add(getResources().getDrawable(R.drawable.randers_freja));
		drawables.add(getResources().getDrawable(R.drawable.rangers_fc));
		drawables.add(getResources().getDrawable(R.drawable.rapid_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.rapid_jc_heerlen));
		drawables.add(getResources().getDrawable(R.drawable.rapid_wien));
		drawables.add(getResources().getDrawable(R.drawable.rayo_vallecano));
		drawables.add(getResources().getDrawable(R.drawable.rayo_vallecano_2_mascotte_pica_pica));
		drawables.add(getResources().getDrawable(R.drawable.rc_lens));
		drawables.add(getResources().getDrawable(R.drawable.rc_paris));
		drawables.add(getResources().getDrawable(R.drawable.rc_strasbourg));
		drawables.add(getResources().getDrawable(R.drawable.real_betis));
		drawables.add(getResources().getDrawable(R.drawable.real_madrid));
		drawables.add(getResources().getDrawable(R.drawable.real_mallorca));
		drawables.add(getResources().getDrawable(R.drawable.real_oviedo));
		drawables.add(getResources().getDrawable(R.drawable.real_sociedad));
		drawables.add(getResources().getDrawable(R.drawable.real_valladolid));
		drawables.add(getResources().getDrawable(R.drawable.real_zaragoza));
		drawables.add(getResources().getDrawable(R.drawable.red_boys_differdange));
		drawables.add(getResources().getDrawable(R.drawable.red_star_belgrade));
		drawables.add(getResources().getDrawable(R.drawable.red_star_bratislava));
		drawables.add(getResources().getDrawable(R.drawable.red_star_brno));
		drawables.add(getResources().getDrawable(R.drawable.reipas_lahti));
		drawables.add(getResources().getDrawable(R.drawable.renova_dzepciste));
		drawables.add(getResources().getDrawable(R.drawable.rhyl_fc));
		drawables.add(getResources().getDrawable(R.drawable.rnk_split));
		drawables.add(getResources().getDrawable(R.drawable.roda_jc_kerkrade));
		drawables.add(getResources().getDrawable(R.drawable.romania));
		drawables.add(getResources().getDrawable(R.drawable.romar_mazeikiai));
		drawables.add(getResources().getDrawable(R.drawable.rops_rovaniemi));
		drawables.add(getResources().getDrawable(R.drawable.rosenborg_bk));
		drawables.add(getResources().getDrawable(R.drawable.rotor_volgograd));
		drawables.add(getResources().getDrawable(R.drawable.rot_weib_essen));
		drawables.add(getResources().getDrawable(R.drawable.rot_weiss_erfurt));
		drawables.add(getResources().getDrawable(R.drawable.royal_antwerp));
		drawables.add(getResources().getDrawable(R.drawable.rubin_kazan));
		drawables.add(getResources().getDrawable(R.drawable.ruch_chorzow));
		drawables.add(getResources().getDrawable(R.drawable.rudar_pljevlja));
		drawables.add(getResources().getDrawable(R.drawable.rudar_velenje));
		drawables.add(getResources().getDrawable(R.drawable.russia));
		drawables.add(getResources().getDrawable(R.drawable.rwd_molenbeek));
		drawables.add(getResources().getDrawable(R.drawable.saarland));
		drawables.add(getResources().getDrawable(R.drawable.sachsenring_zwickau));
		drawables.add(getResources().getDrawable(R.drawable.sadam_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.sakaryaspor));
		drawables.add(getResources().getDrawable(R.drawable.salamis_famagusta));
		drawables.add(getResources().getDrawable(R.drawable.salgotarjan_btc));
		drawables.add(getResources().getDrawable(R.drawable.sampdoria));
		drawables.add(getResources().getDrawable(R.drawable.samtredia));
		drawables.add(getResources().getDrawable(R.drawable.sant_julia));
		drawables.add(getResources().getDrawable(R.drawable.san_marino));
		drawables.add(getResources().getDrawable(R.drawable.sarpsborg_fk));
		drawables.add(getResources().getDrawable(R.drawable.sartid_smederevo));
		drawables.add(getResources().getDrawable(R.drawable.schalke_04));
		drawables.add(getResources().getDrawable(R.drawable.scotland));
		drawables.add(getResources().getDrawable(R.drawable.sco_angers));
		drawables.add(getResources().getDrawable(R.drawable.scp_ruzomberok));
		drawables.add(getResources().getDrawable(R.drawable.sc_bastia));
		drawables.add(getResources().getDrawable(R.drawable.sc_freiburg));
		drawables.add(getResources().getDrawable(R.drawable.sc_krems));
		drawables.add(getResources().getDrawable(R.drawable.sc_leipzig));
		drawables.add(getResources().getDrawable(R.drawable.sc_salgueiros));
		drawables.add(getResources().getDrawable(R.drawable.seraing));
		drawables.add(getResources().getDrawable(R.drawable.serbia));
		drawables.add(getResources().getDrawable(R.drawable.servette_fc_geneve));
		drawables.add(getResources().getDrawable(R.drawable.sevilla));
		drawables.add(getResources().getDrawable(R.drawable.shafa_baku));
		drawables.add(getResources().getDrawable(R.drawable.shakhtar_donetsk));
		drawables.add(getResources().getDrawable(R.drawable.shakhtior_saligorsk));
		drawables.add(getResources().getDrawable(R.drawable.shakhtyor_karaganda));
		drawables.add(getResources().getDrawable(R.drawable.shamrock_rovers));
		drawables.add(getResources().getDrawable(R.drawable.sheffield_wednesday));
		drawables.add(getResources().getDrawable(R.drawable.shelbourne));
		drawables.add(getResources().getDrawable(R.drawable.sheriff_tiraspol));
		drawables.add(getResources().getDrawable(R.drawable.shirak_gyumri));
		drawables.add(getResources().getDrawable(R.drawable.shkendija_tetovo));
		drawables.add(getResources().getDrawable(R.drawable.sibir_novosibirsk));
		drawables.add(getResources().getDrawable(R.drawable.sigma_olomouc));
		drawables.add(getResources().getDrawable(R.drawable.sileks_kratovo));
		drawables.add(getResources().getDrawable(R.drawable.silkeborg_if));
		drawables.add(getResources().getDrawable(R.drawable.simurq_zaqatala));
		drawables.add(getResources().getDrawable(R.drawable.siofoki_banyasz));
		drawables.add(getResources().getDrawable(R.drawable.sioni_bolnisi));
		drawables.add(getResources().getDrawable(R.drawable.siroki_brijeg));
		drawables.add(getResources().getDrawable(R.drawable.sivasspor));
		drawables.add(getResources().getDrawable(R.drawable.skala_if));
		drawables.add(getResources().getDrawable(R.drawable.ska_rostov_na_donu));
		drawables.add(getResources().getDrawable(R.drawable.skeid_oslo));
		drawables.add(getResources().getDrawable(R.drawable.skenderbeu_korce));
		drawables.add(getResources().getDrawable(R.drawable.skonto_riga));
		drawables.add(getResources().getDrawable(R.drawable.sk_beveren));
		drawables.add(getResources().getDrawable(R.drawable.sk_brann_bergen));
		drawables.add(getResources().getDrawable(R.drawable.sk_tirana));
		drawables.add(getResources().getDrawable(R.drawable.sk_voest_linz));
		drawables.add(getResources().getDrawable(R.drawable.slask_wroclaw));
		drawables.add(getResources().getDrawable(R.drawable.slaven_koprivnica));
		drawables.add(getResources().getDrawable(R.drawable.slavia_mozyr));
		drawables.add(getResources().getDrawable(R.drawable.slavia_praha));
		drawables.add(getResources().getDrawable(R.drawable.slavia_sofia));
		drawables.add(getResources().getDrawable(R.drawable.slavija_sarajevo));
		drawables.add(getResources().getDrawable(R.drawable.sliema_wanderers));
		drawables.add(getResources().getDrawable(R.drawable.sligo_rovers));
		drawables.add(getResources().getDrawable(R.drawable.sloboda_tuzla));
		drawables.add(getResources().getDrawable(R.drawable.sloga_skopje));
		drawables.add(getResources().getDrawable(R.drawable.slovakia));
		drawables.add(getResources().getDrawable(R.drawable.slovan_bratislava));
		drawables.add(getResources().getDrawable(R.drawable.slovan_liberec));
		drawables.add(getResources().getDrawable(R.drawable.slovenia));
		drawables.add(getResources().getDrawable(R.drawable.sm_caen));
		drawables.add(getResources().getDrawable(R.drawable.southampton_fc));
		drawables.add(getResources().getDrawable(R.drawable.southampton_fc_4_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.soviet_union));
		drawables.add(getResources().getDrawable(R.drawable.spain));
		drawables.add(getResources().getDrawable(R.drawable.spartak_brno));
		drawables.add(getResources().getDrawable(R.drawable.spartak_kps_brno));
		drawables.add(getResources().getDrawable(R.drawable.spartak_moscow));
		drawables.add(getResources().getDrawable(R.drawable.spartak_plovdiv));
		drawables.add(getResources().getDrawable(R.drawable.spartak_trnava));
		drawables.add(getResources().getDrawable(R.drawable.spartak_varna));
		drawables.add(getResources().getDrawable(R.drawable.spartak_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.spartak_zlatibor_voda));
		drawables.add(getResources().getDrawable(R.drawable.sparta_praha));
		drawables.add(getResources().getDrawable(R.drawable.sparta_rotterdam));
		drawables.add(getResources().getDrawable(R.drawable.sporting_braga));
		drawables.add(getResources().getDrawable(R.drawable.sporting_cp_lisbon));
		drawables.add(getResources().getDrawable(R.drawable.sporting_fingal));
		drawables.add(getResources().getDrawable(R.drawable.sporting_gijon));
		drawables.add(getResources().getDrawable(R.drawable.sportul_studentesc));
		drawables.add(getResources().getDrawable(R.drawable.sp_domagnano));
		drawables.add(getResources().getDrawable(R.drawable.stabaek_if));
		drawables.add(getResources().getDrawable(R.drawable.stade_de_reims));
		drawables.add(getResources().getDrawable(R.drawable.stade_dudelange));
		drawables.add(getResources().getDrawable(R.drawable.stade_francais));
		drawables.add(getResources().getDrawable(R.drawable.stade_lavallois));
		drawables.add(getResources().getDrawable(R.drawable.stade_rennais));
		drawables.add(getResources().getDrawable(R.drawable.stahl_brandenburg));
		drawables.add(getResources().getDrawable(R.drawable.stal_mielec));
		drawables.add(getResources().getDrawable(R.drawable.stal_rzeszow));
		drawables.add(getResources().getDrawable(R.drawable.standard_liege));
		drawables.add(getResources().getDrawable(R.drawable.start_kristiansand));
		drawables.add(getResources().getDrawable(R.drawable.steagul_rosu_brasov));
		drawables.add(getResources().getDrawable(R.drawable.steaua_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.stiinta_cluj));
		drawables.add(getResources().getDrawable(R.drawable.stoke_city));
		drawables.add(getResources().getDrawable(R.drawable.stoke_city_5_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.strphymsgodset_if));
		drawables.add(getResources().getDrawable(R.drawable.sturm_graz));
		drawables.add(getResources().getDrawable(R.drawable.st_johnstone));
		drawables.add(getResources().getDrawable(R.drawable.st_mirren));
		drawables.add(getResources().getDrawable(R.drawable.st_patrick_s_athletic));
		drawables.add(getResources().getDrawable(R.drawable.suduva_marijampole));
		drawables.add(getResources().getDrawable(R.drawable.sunderland_afc));
		drawables.add(getResources().getDrawable(R.drawable.sunderland_afc_6_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.sutjeska_niksic));
		drawables.add(getResources().getDrawable(R.drawable.sv_mattersburg));
		drawables.add(getResources().getDrawable(R.drawable.sv_pasching));
		drawables.add(getResources().getDrawable(R.drawable.sv_ried));
		drawables.add(getResources().getDrawable(R.drawable.sv_stockerau));
		drawables.add(getResources().getDrawable(R.drawable.sv_winterslag));
		drawables.add(getResources().getDrawable(R.drawable.swansea_city));
		drawables.add(getResources().getDrawable(R.drawable.sweden));
		drawables.add(getResources().getDrawable(R.drawable.swift_hesperange));
		drawables.add(getResources().getDrawable(R.drawable.switzerland));
		drawables.add(getResources().getDrawable(R.drawable.szombierki_bytom));
		drawables.add(getResources().getDrawable(R.drawable.tampere_united));
		drawables.add(getResources().getDrawable(R.drawable.tasmania_berlin));
		drawables.add(getResources().getDrawable(R.drawable.tatabanya));
		drawables.add(getResources().getDrawable(R.drawable.tatran_presov));
		drawables.add(getResources().getDrawable(R.drawable.tavria_simferopol));
		drawables.add(getResources().getDrawable(R.drawable.tekstilschik_kamyshin));
		drawables.add(getResources().getDrawable(R.drawable.tenerife));
		drawables.add(getResources().getDrawable(R.drawable.terek_grozny));
		drawables.add(getResources().getDrawable(R.drawable.teteks_tetovo));
		drawables.add(getResources().getDrawable(R.drawable.teuta_durres));
		drawables.add(getResources().getDrawable(R.drawable.the_new_saints));
		drawables.add(getResources().getDrawable(R.drawable.thor_akureyri));
		drawables.add(getResources().getDrawable(R.drawable.thor_waterschei));
		drawables.add(getResources().getDrawable(R.drawable.tiligul_tiraspol));
		drawables.add(getResources().getDrawable(R.drawable.tj_gottwaldov));
		drawables.add(getResources().getDrawable(R.drawable.tj_internacional));
		drawables.add(getResources().getDrawable(R.drawable.tj_skoda_plzen));
		drawables.add(getResources().getDrawable(R.drawable.tj_vitkovice));
		drawables.add(getResources().getDrawable(R.drawable.tobol_kustanai));
		drawables.add(getResources().getDrawable(R.drawable.tomori_berat));
		drawables.add(getResources().getDrawable(R.drawable.torino));
		drawables.add(getResources().getDrawable(R.drawable.torpedo_kutaisi));
		drawables.add(getResources().getDrawable(R.drawable.torpedo_moscow));
		drawables.add(getResources().getDrawable(R.drawable.torpedo_zhodino));
		drawables.add(getResources().getDrawable(R.drawable.tottenham_hotspur));
		drawables.add(getResources().getDrawable(R.drawable.toulouse_fc));
		drawables.add(getResources().getDrawable(R.drawable.tps_turku));
		drawables.add(getResources().getDrawable(R.drawable.tpv_tampere));
		drawables.add(getResources().getDrawable(R.drawable.trabzonspor));
		drawables.add(getResources().getDrawable(R.drawable.trakia_plovdiv));
		drawables.add(getResources().getDrawable(R.drawable.trans_narva));
		drawables.add(getResources().getDrawable(R.drawable.trelleborgs_ff));
		drawables.add(getResources().getDrawable(R.drawable.tresnjevka_zagreb));
		drawables.add(getResources().getDrawable(R.drawable.tre_fiori));
		drawables.add(getResources().getDrawable(R.drawable.tre_penne));
		drawables.add(getResources().getDrawable(R.drawable.tromsphy_il));
		drawables.add(getResources().getDrawable(R.drawable.troyes_ac));
		drawables.add(getResources().getDrawable(R.drawable.tsv_1860_munchen));
		drawables.add(getResources().getDrawable(R.drawable.tulevik_viljandi));
		drawables.add(getResources().getDrawable(R.drawable.turan_tauz));
		drawables.add(getResources().getDrawable(R.drawable.turkey));
		drawables.add(getResources().getDrawable(R.drawable.tvmk_tallinn));
		drawables.add(getResources().getDrawable(R.drawable.udinese));
		drawables.add(getResources().getDrawable(R.drawable.ud_las_palmas));
		drawables.add(getResources().getDrawable(R.drawable.uefa));
		drawables.add(getResources().getDrawable(R.drawable.ue_santa_coloma));
		drawables.add(getResources().getDrawable(R.drawable.ukraine));
		drawables.add(getResources().getDrawable(R.drawable.ulisses_yerevan));
		drawables.add(getResources().getDrawable(R.drawable.uniao_de_leiria));
		drawables.add(getResources().getDrawable(R.drawable.union_berlin));
		drawables.add(getResources().getDrawable(R.drawable.union_luxembourg));
		drawables.add(getResources().getDrawable(R.drawable.union_saint_gilloise));
		drawables.add(getResources().getDrawable(R.drawable.union_teplice));
		drawables.add(getResources().getDrawable(R.drawable.unirea_urziceni));
		drawables.add(getResources().getDrawable(R.drawable.universitatea_cluj));
		drawables.add(getResources().getDrawable(R.drawable.universitatea_craiova));
		drawables.add(getResources().getDrawable(R.drawable.universitate_riga));
		drawables.add(getResources().getDrawable(R.drawable.uni_college_dublin));
		drawables.add(getResources().getDrawable(R.drawable.un_kaerjeng));
		drawables.add(getResources().getDrawable(R.drawable.usv_eschen_mauren));
		drawables.add(getResources().getDrawable(R.drawable.us_rumelange));
		drawables.add(getResources().getDrawable(R.drawable.utrecht_xi));
		drawables.add(getResources().getDrawable(R.drawable.ut_arad));
		drawables.add(getResources().getDrawable(R.drawable.valencia));
		drawables.add(getResources().getDrawable(R.drawable.valerengen_if));
		drawables.add(getResources().getDrawable(R.drawable.valletta_fc));
		drawables.add(getResources().getDrawable(R.drawable.valur_reykjavik));
		drawables.add(getResources().getDrawable(R.drawable.vanlphyse_if));
		drawables.add(getResources().getDrawable(R.drawable.vardar_skopje));
		drawables.add(getResources().getDrawable(R.drawable.varteks_varazdin));
		drawables.add(getResources().getDrawable(R.drawable.vasas_budapest));
		drawables.add(getResources().getDrawable(R.drawable.vb_vagur));
		drawables.add(getResources().getDrawable(R.drawable.vejle_bk));
		drawables.add(getResources().getDrawable(R.drawable.velez_mostar));
		drawables.add(getResources().getDrawable(R.drawable.vetra_vilnius));
		drawables.add(getResources().getDrawable(R.drawable.vfb_stuttgart));
		drawables.add(getResources().getDrawable(R.drawable.vfl_bochum));
		drawables.add(getResources().getDrawable(R.drawable.vfl_wolfsburg));
		drawables.add(getResources().getDrawable(R.drawable.viborg_ff));
		drawables.add(getResources().getDrawable(R.drawable.vicenza));
		drawables.add(getResources().getDrawable(R.drawable.victoria_bucuresti));
		drawables.add(getResources().getDrawable(R.drawable.vikingur));
		drawables.add(getResources().getDrawable(R.drawable.vikingur_reykjavik));
		drawables.add(getResources().getDrawable(R.drawable.viking_stavanger));
		drawables.add(getResources().getDrawable(R.drawable.viktoria_koln));
		drawables.add(getResources().getDrawable(R.drawable.viktoria_plzen));
		drawables.add(getResources().getDrawable(R.drawable.viktoria_zizkov));
		drawables.add(getResources().getDrawable(R.drawable.villarreal));
		drawables.add(getResources().getDrawable(R.drawable.vitesse_arnhem));
		drawables.add(getResources().getDrawable(R.drawable.vitoria_guimaraes));
		drawables.add(getResources().getDrawable(R.drawable.vitoria_setubal));
		drawables.add(getResources().getDrawable(R.drawable.vllaznia_shkoder));
		drawables.add(getResources().getDrawable(R.drawable.vojvodina_novi_sad));
		drawables.add(getResources().getDrawable(R.drawable.voros_lobogo_budapest));
		drawables.add(getResources().getDrawable(R.drawable.vorskla_poltava));
		drawables.add(getResources().getDrawable(R.drawable.vorwarts_berlin));
		drawables.add(getResources().getDrawable(R.drawable.vps_vaasa));
		drawables.add(getResources().getDrawable(R.drawable.vss_kosice));
		drawables.add(getResources().getDrawable(R.drawable.wacker_innsbruck));
		drawables.add(getResources().getDrawable(R.drawable.wales));
		drawables.add(getResources().getDrawable(R.drawable.waregem));
		drawables.add(getResources().getDrawable(R.drawable.waterford));
		drawables.add(getResources().getDrawable(R.drawable.watford_fc));
		drawables.add(getResources().getDrawable(R.drawable.werder_bremen));
		drawables.add(getResources().getDrawable(R.drawable.west_berlin_xi));
		drawables.add(getResources().getDrawable(R.drawable.west_bromwich_albion));
		drawables.add(getResources().getDrawable(R.drawable.west_bromwich_albion_4_town_crest));
		drawables.add(getResources().getDrawable(R.drawable.west_ham_united));
		drawables.add(getResources().getDrawable(R.drawable.widzew_lodz));
		drawables.add(getResources().getDrawable(R.drawable.wiener_neustadt));
		drawables.add(getResources().getDrawable(R.drawable.wiener_sportklub));
		drawables.add(getResources().getDrawable(R.drawable.willem_ii_tilburg));
		drawables.add(getResources().getDrawable(R.drawable.wisla_krakow));
		drawables.add(getResources().getDrawable(R.drawable.wisla_plock));
		drawables.add(getResources().getDrawable(R.drawable.wismut_aue));
		drawables.add(getResources().getDrawable(R.drawable.wismut_karl_marx_stadt));
		drawables.add(getResources().getDrawable(R.drawable.wolverhampton_wanderers));
		drawables.add(getResources().getDrawable(R.drawable.wrexham));
		drawables.add(getResources().getDrawable(R.drawable.wuppertaler_sv));
		drawables.add(getResources().getDrawable(R.drawable.xamax_neuchatel));
		drawables.add(getResources().getDrawable(R.drawable.xanthi_fc));
		drawables.add(getResources().getDrawable(R.drawable.young_boys));
		drawables.add(getResources().getDrawable(R.drawable.yugoslavia));
		drawables.add(getResources().getDrawable(R.drawable.zaglebie_lubin));
		drawables.add(getResources().getDrawable(R.drawable.zaglebie_sosnowiec));
		drawables.add(getResources().getDrawable(R.drawable.zaglebie_walbrzych));
		drawables.add(getResources().getDrawable(R.drawable.zagreb_xi));
		drawables.add(getResources().getDrawable(R.drawable.zalaegerszeg_te));
		drawables.add(getResources().getDrawable(R.drawable.zalgiris_vilnius));
		drawables.add(getResources().getDrawable(R.drawable.zaria_voroshilovgrad));
		drawables.add(getResources().getDrawable(R.drawable.zbrojovka_brno));
		drawables.add(getResources().getDrawable(R.drawable.zeleznik_belgrade));
		drawables.add(getResources().getDrawable(R.drawable.zeljeznicar_sarajevo));
		drawables.add(getResources().getDrawable(R.drawable.zenit_leningrad));
		drawables.add(getResources().getDrawable(R.drawable.zenit_st_petersburg));
		drawables.add(getResources().getDrawable(R.drawable.zeta_golubovci));
		drawables.add(getResources().getDrawable(R.drawable.zhetysu_taldykorgan));
		drawables.add(getResources().getDrawable(R.drawable.zimbru_chisinau));
		drawables.add(getResources().getDrawable(R.drawable.zrinjski_mostar));
		drawables.add(getResources().getDrawable(R.drawable.zulte_waregem));
		drawables.add(getResources().getDrawable(R.drawable.zurrieq_fc));
		drawables.add(getResources().getDrawable(R.drawable.zvartnots_yerevan));
		
		

	}

	private void setSelectedImage(int selectedImagePosition) {

		BitmapDrawable bd = (BitmapDrawable) drawables.get(selectedImagePosition);
		//Toast.makeText(this, namelist.get(selectedImagePosition)+"",Toast.LENGTH_LONG).show();
		Bitmap b = Bitmap.createScaledBitmap(bd.getBitmap(), (int) (bd.getIntrinsicHeight() * 0.9), (int) (bd.getIntrinsicWidth() * 0.7), false);
		selectedImageView.setImageBitmap(b);
		selectedImageView.setScaleType(ScaleType.FIT_XY);
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setSelection(selectedImagePosition);
		
	    

	}
	
	
}