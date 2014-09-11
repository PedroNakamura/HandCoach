package com.example.handcoach.telaPartidas.Scouting;

import android.app.Activity;
/*import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast; */

public class MainActivity extends Activity {

	/*
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Add action item
		ActionItem addAction = new ActionItem();

		addAction.setTitle("Phone");
		addAction.setIcon(getResources().getDrawable(R.drawable.phone));

		// Accept action item
		ActionItem accAction = new ActionItem();

		accAction.setTitle("Gmail");
		accAction.setIcon(getResources().getDrawable(R.drawable.gmail));

		// Upload action item
		ActionItem upAction = new ActionItem();

		upAction.setTitle("Talk");
		upAction.setIcon(getResources().getDrawable(R.drawable.talk));

		final QuickAction mQuickAction = new QuickAction(this);

		mQuickAction.addActionItem(addAction);
		mQuickAction.addActionItem(accAction);
		mQuickAction.addActionItem(upAction);

		// setup the action item click listener
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					public void onItemClick(int pos) {

						if (pos == 0) { // Add item selected
							Toast.makeText(MainActivity.this,
									"PHONE item selected", Toast.LENGTH_SHORT)
									.show();
						} else if (pos == 1) { // Accept item selected
							Toast.makeText(MainActivity.this,
									"GMAIL item selected", Toast.LENGTH_SHORT)
									.show();
						} else if (pos == 2) { // Upload item selected
							Toast.makeText(MainActivity.this, "TALK selected",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		ImageView ivPic1 = (ImageView) this.findViewById(R.id.ivPic1);
		ivPic1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});

		Button btClickMe = (Button) this.findViewById(R.id.button1);
		btClickMe.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
} 
