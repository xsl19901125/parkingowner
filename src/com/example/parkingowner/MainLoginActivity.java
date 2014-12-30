package com.example.parkingowner;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.user.bean.User;
import com.util.MD5Util;
import com.util.NetConnectionUtil;

public class MainLoginActivity extends Activity {

	/**
	 * 登录的用户名
	 */
	private String userName = null;
	/**
	 * 登录密码（MD5加密后）
	 */
	private String userPwd = null;
	/**
	 * 用户登录用户名框
	 */
	private EditText editText;
	/**
	 * 用户登录密码框
	 */
	private EditText editPwd;
	// 登录控件
	private Button buttonLogin;
	// 注册控件
	private Button buttonReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化窗口
		initWindows();
		setContentView(R.layout.user_login_main);
		// 初始化控件
		initButton();
		// 给按钮添加监听
		addButtonListener();

	}

	/**
	 * 
	 * @Title: addButtonListener
	 * @Description:(给按钮添加监听)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void addButtonListener() {
		// 给登录按钮添加监听
		buttonLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 网络检测
				boolean isConnected = checkNetWork();
				if (!isConnected) {
					return;
				}
				// 获取登录信息
				getLoginInfo();
				// 检测登录信息是否完整
				boolean dataIsOk = checkLoginInfo();
				if (dataIsOk) {
					// 对用户弹出提示框
					showTip("用户名或者密码不能为空");
					return;
				}
				// 将用户密码用MD5加密
				try {
					userPwd = MD5Util.getMD5(userPwd);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				//包装登录信息
				User user = setLoginInfo();
				// 登录验证
				/*LoginQuery(user);*/

			}
			/**
			 * 
			 * @Title: setLoginInfo 
			 * @Description: 包装登录信息
			 * @param @return    设定文件 
			 * @return User    包装后用户提交服务器的userBean
			 * @throws 
			**/
			private User setLoginInfo() {
				User user = new User();
				user.setUserName(userName);
				user.setUserPwdMD5(userPwd);
				return user;
			}

			/**
			 * 
			 * @Title: getLoginInfo
			 * @Description: 从界面获取登录信息
			 * @param 设定文件
			 * @return void 返回类型
			 * @throws
			 **/
			private void getLoginInfo() {
				userName = editText.getText().toString().trim();
				userPwd = editPwd.getText().toString().trim();
			}

			/**
			 * 
			 * @Title: checkLoginInfo
			 * @Description: 检测用户输入是否有效
			 * @param @return 设定文件
			 * @return boolean 检测结果
			 * @throws
			 * 
			 */
			private boolean checkLoginInfo() {
				boolean dataIsOk = userName.isEmpty() || userPwd.isEmpty();
				return dataIsOk;
			}

			/**
			 * 
			 * @Title: showTip
			 * @Description: 对用户输出提示
			 * @param @param Tips 输入的提示内容
			 * @return void 返回类型
			 * @throws
			 * 
			 */
			private void showTip(String Tips) {
				Toast.makeText(MainLoginActivity.this, Tips, Toast.LENGTH_SHORT)
						.show();
			}

			/**
			 * 
			 * @Title: checkNetWork
			 * @Description: 检测网络链接的方法
			 * @param @return 设定文件
			 * @return boolean 是否链接成功
			 * @throws
			 * 
			 */
			private boolean checkNetWork() {
				boolean isConnect = NetConnectionUtil
						.checkNetworkAvailable(MainLoginActivity.this);
				if (!isConnect) {
					// 输出提示框
					showTip("请链接网络");
					return false;
				} else {
					return true;
				}
			}

			/**
			 * 
			 * @Title: LoginQuery
			 * @Description: 用户登录验证
			 * @param @param user 验证用户bean
			 * @return void 返回类型
			 * @throws
			 * 
			 */
			/*private void LoginQuery(User user) {
				AVQuery<AVObject> query = new AVQuery<AVObject>("UserInfo");
				query.whereEqualTo("userName", user.getUserName());
				query.whereEqualTo("userPwdMD5", user.getUserPwdMD5());
				query.findInBackground(new FindCallback<AVObject>() {

					@Override
					public void done(List<AVObject> list, AVException e) {
						if (list.size() != 0) {
							// TODO（最好能记录用户登录信息）
							Intent intent = new Intent(MainLoginActivity.this,
									MainUI.class);
							startActivity(intent);
						} else {
							editText.setText("");
							editPwd.setText("");
						}
					}
				});
			}*/
		});
		// 注册按钮的监听
		buttonReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到注册界面
				Intent intent = new Intent(MainLoginActivity.this,
						UserRegister.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * @Title: initWindows
	 * @Description:(初始化窗口)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	private void initWindows() {
		// 去掉标题栏，必须在setCOntentView之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 第一个参数是需要添加的新的窗口属性的标志位（相当于值）
		// 第二个 参数是窗口的哪一个特性标志位需要修改（相当于开关）
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * 
	 * @Title: initButton
	 * @Description:(初始化，用户登录框，密码框，登录，注册的按钮)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	private void initButton() {
		editText = (EditText) findViewById(R.id.userLoginNameTxt);
		editPwd = (EditText) findViewById(R.id.userPwdTxt);
		buttonLogin = (Button) findViewById(R.id.btLogin);
		buttonReg = (Button) findViewById(R.id.btRegist);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop() {
		super.onStop();
		MainLoginActivity.this.finish();
	}
}
