package com.example.vip.alertdialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 测试AertDialog和ProgressDialog
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private Button showAlertDialog, showProDialog,showSngleChoice,showMultiChoice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showAlertDialog = (Button) findViewById(R.id.button1);
		showProDialog = (Button) findViewById(R.id.button2);
		showSngleChoice= (Button) findViewById(R.id.button3);
		showMultiChoice= (Button) findViewById(R.id.button4);
		showProDialog.setOnClickListener(this);
		showAlertDialog.setOnClickListener(this);
		showMultiChoice.setOnClickListener(this);
		showSngleChoice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				createAlertDialog();
				break;
			case R.id.button2:
				createProgressDialog();
				break;
			case R.id.button3:
				createChoiceDialog();
				break;
			case R.id.button4:
				createListDialog();
				break;
		}
	}

	/**
	 * 对话框
	 */
	private void createAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("试论名与器");
		builder.setMessage("唯名与器不可假手于人，出师须有名，执器当杨其威");
		builder.setCancelable(false);//设置对话框是否可取消，即点击空白处即可取消,默认为可
		//添加图片
		builder.setIcon(R.mipmap.ic_launcher);
		//对话框内view所留空间很小，故只能容纳一个控件，如EditText，Imageview等控件
		//builder.setView(new EditText(this));
		builder.setView(R.layout.dialog_view);

		//添加按键，只能有正中反三个按键
		builder.setNeutralButton("不确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“不确定“", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//在此可获取结果
				Toast.makeText(MainActivity.this, "选择了“确定“", Toast.LENGTH_SHORT).show();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“取消”", Toast.LENGTH_SHORT).show();
			}
		});

		builder.show();
	}

	/**
	 * 单选框和多选框对话框
	 */
	private void createChoiceDialog(){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("单选框和多选框");
		dialog.setIcon(R.mipmap.ic_launcher);
		//单选框,若要显示单选框，则不可再设置view和Message
		String[] strs = new String[]{"选项1", "选项2", "选项3", "选项4"};
		//单选框
//		dialog.setSingleChoiceItems(strs, 1, new
//			   DialogInterface.OnClickListener() {
//				   @Override
//				   public void onClick(DialogInterface dialog, int which) {
//					   Toast.makeText(MainActivity.this, "选择了选项" + (which + 1), Toast
//							 .LENGTH_SHORT).show();
//				   }
//			   });
		//多选框
		dialog.setMultiChoiceItems(strs, null, new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				Toast.makeText(MainActivity.this, "选择了选项" + (which + 1), Toast
					   .LENGTH_SHORT).show();
			}
		});

		dialog.setNeutralButton("中立", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“中立“", Toast.LENGTH_SHORT).show();
			}
		});

		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“确定“", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“取消“", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.show();

	}

	/**
	 * 列表对话框
	 */
	private void createListDialog(){
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("列表框");
		dialog.setIcon(R.mipmap.ic_launcher);
		//列表框，则不可再设置view和Message
		String[] strs = new String[]{"选项1", "选项2", "选项3", "选项4"};
		dialog.setItems(strs, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了选项" + (which + 1), Toast
					   .LENGTH_SHORT).show();
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		dialog.show();

	}

	/**
	 * 进度条对话框
	 */
	private void createProgressDialog() {
		final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		dialog.setTitle("正在加载中·····");
		dialog.setMessage("请稍候");
		//ProgressDialog.STYLE_SPINNER为圆形进度条，ProgressDialog.STYLE_HORIZONTAL为条状进度条
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(true);
		//按键只能有三个
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface
			   .OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“取消“", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "中立", new DialogInterface
			   .OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“中立“", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface
			   .OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "选择了“确定“", Toast.LENGTH_SHORT).show();
			}
		});
		/**
		 * 监听Key
		 */
		dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				return false;
			}
		});

		/**
		 * 无论哪一种方式取消，始终回调该函数
		 */
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				Toast.makeText(MainActivity.this, "已通过cancel取消", Toast.LENGTH_SHORT).show();
			}
		});
		/**
		 * 以按键取消才会回调
		 *
		 */
		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				Toast.makeText(MainActivity.this, "已通过dismiss取消", Toast.LENGTH_SHORT).show();
			}
		});
		dialog.setMax(100);
		dialog.show();
		new Thread() {
			@Override
			public void run() {
				int i = 0;
				try {
					while (i < 100) {
						dialog.setProgress(i);
						//dialog.incrementProgressBy(1);//每一次加一
						i++;
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//cancel和dismiss方法本质都是一样的，都是从屏幕中删除Dialog,唯一的区别是：
				// 调用cancel方法会回调DialogInterface.OnCancelListener如果注册的话,
				// dismiss则回调DialogInterface.OnDismissListener
				dialog.cancel();
				//dialog.dismiss();

			}
		}.start();

	}



}
