package com.th.android.spf;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private SpfBar bar;
	int[] colors = { Color.RED, Color.GREEN, Color.BLUE };
	private ArrayList<Integer> spfList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar = (SpfBar) findViewById(R.id.bar);
	}

	public void update(View view) {
		spfList.clear();
        for (int i = 0, length = 10; i < length; i++) {
            spfList.add(new Random().nextInt(3));
        }
		bar.setValues(colors, spfList);
	}
}
