package com.example.vip.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * 省市区三级联动
 */
public class ProvinceCityCounty extends AppCompatActivity {
	private Spinner province;
	private Spinner city;
	private Spinner county;
	private TextView show;
	private ArrayAdapter<String> provinceAdapter;
	private ArrayAdapter<String> cityAdapter;
	private ArrayAdapter<String> countyAdapter;
	private int provincePosition;
	private int cityPosition;


	private String[] provinces = {"广东省", "北京市", "上海市"};
	private String[][] citys = {{"湛江", "广州", "深圳", "东莞"}, {"东城区", "西城区", "海淀区", "朝阳区", "丰台区",
		   "石景山区", "通州区", "顺义区", "房山区", "大兴区", "昌平区"}, {"黄埔区", "卢湾区", "徐汇区", "长安区", "静安区",
		   "普陀区", "闸北区", "虹口区", "宝山区", "嘉定区", "闵兴区", "松江区", "青浦区", "金山区", "南汇区", "奉贤区", "崇明县"}};

	private String[][][] countys = {{{"霞山区", "赤坎区", "麻章区", "坡头区", "雷州市", "廉江市", "吴川市", "遂溪县",
		   "徐闻县"}, {"越秀区", "东山区", "海珠区", "荔湾区", "天河区", "白云区", "黄埔区", "芳村区", "花都区", "番禹区", "从化市",
		   "增城市"}, {"福田区", "罗湖区", "盐田区", "南山区", "宝安区", "龙岗区"}, {""}}, {{""}, {""}, {""}, {""},
		   {""}, {""}, {""}, {""}, {""}, {""}, {""}}, {{""}, {""}, {""}, {""}, {""}, {""}, {""},
		   {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}}};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.province_city_county);
		province = (Spinner) findViewById(R.id.province);
		city = (Spinner) findViewById(R.id.city);
		county = (Spinner) findViewById(R.id.county);
		show = (TextView) findViewById(R.id.show);

		provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
			   provinces);
		province.setAdapter(provinceAdapter);
		province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long
				   id) {
				cityAdapter = new ArrayAdapter<String>(ProvinceCityCounty.this, android.R
					   .layout
					   .simple_spinner_item, citys[position]);
				city.setAdapter(cityAdapter);
				provincePosition = position;
				show.setText(provinces[position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long
				   id) {
				countyAdapter = new ArrayAdapter<String>(ProvinceCityCounty.this, android.R
					   .layout.simple_spinner_item, countys[provincePosition][position]);
				county.setAdapter(countyAdapter);
				cityPosition = position;
				show.setText(provinces[provincePosition] + citys[provincePosition][cityPosition]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		county.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long
				   id) {
				show.setText(provinces[provincePosition] +
					   citys[provincePosition][cityPosition]
					   + countys[provincePosition][cityPosition][position]);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


	}


}
