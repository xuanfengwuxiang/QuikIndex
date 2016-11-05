package com.itheima.quikindex;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ListView;

import com.itheima.quikindex.adapter.HaoHanAdapter;
import com.itheima.quikindex.domain.HaoHan;
import com.itheima.quikindex.ui.QuickIndexBar;
import com.itheima.quikindex.util.Utils;


public class MainActivity extends Activity {

	private ListView lv;
	private ArrayList<HaoHan> persons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		// View
		lv = (ListView) findViewById(R.id.lv);
		
		// Model
		persons = new ArrayList<HaoHan>();
		fillAndSortData(persons);
		
		// Controller
		lv.setAdapter(new HaoHanAdapter(persons, this));
		
		QuickIndexBar bar = (QuickIndexBar) findViewById(R.id.bar);
		bar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {
			
			@Override
			public void onLetterUpdate(String letter) {
				Utils.showToast(MainActivity.this, letter);
				for (int i = 0; i < persons.size(); i++) {
					String l = persons.get(i).getPinyin().charAt(0) + "";
					if(TextUtils.equals(letter, l)){
						// 找到第一个首字母是letter条目.
						lv.setSelection(i);
						break;
					}
				}
			}
		});
		

	}

	/**
	 * 填充并排序数据
	 * @param persons
	 */
	private void fillAndSortData(ArrayList<HaoHan> persons) {
		// 填充
		for (int i = 0; i < Cheeses.NAMES.length; i++) {
			String s = Cheeses.NAMES[i];
			persons.add(new HaoHan(s));
		}
		
		// 排序
		Collections.sort(persons);
	}

}
